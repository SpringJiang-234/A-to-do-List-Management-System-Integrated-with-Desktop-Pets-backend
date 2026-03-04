package com.backend.controller.admin;

import com.backend.bean.PageBean;
import com.backend.bean.ResultBean;
import com.backend.converter.UserConverter;
import com.backend.domain.details.UserDetails;
import com.backend.domain.dto.UserDTO;
import com.backend.domain.entity.User;
import com.backend.domain.excel.UserExcel;
import com.backend.domain.query.UserQuery;
import com.backend.domain.vo.UserVO;
import com.backend.ex.GlobalException;
import com.backend.service.UserService;
import com.backend.utils.MinioUtil;
import com.backend.utils.excel.EasyExcelListener;
import com.backend.utils.excel.EasyExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 用户控制层
 *
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private MinioUtil minioUtil;

    /**
     * 获取用户分页数据
     *
     * @param userQuery 查询参数
     * @return 用户分页数据
     */
    @PostMapping("/page")
    public ResultBean<PageBean<UserVO>> page(@RequestBody UserQuery userQuery) {
        final PageBean<User> userPageBean = userService.getPage(userQuery);
        final PageBean<UserVO> pageBean = userConverter.userPageBean2userVOPageBean(userPageBean);
        return ResultBean.success(pageBean);
    }

    /**
     * 获取用户详情
     *
     * @param id 用户ID
     * @return 用户详情
     */
    @GetMapping("/details/{id}")
    public ResultBean<UserDetails> getDetails(@PathVariable("id") Long id) {
        final User user = userService.getById(id);
        final UserDetails userDetails = userConverter.user2userDetails(user);
        return ResultBean.success(userDetails);
    }

    /**
     * 修改用户信息
     *
     * @param userDTO 用户信息
     * @return 修改结果
     */
    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody UserDTO userDTO) {
        final User user = userConverter.userDTO2user(userDTO);
        userService.insertOrUpdate(user);
        return ResultBean.success("修改成功!", null);
    }

    /**
     * 添加用户
     *
     * @param userDTO 用户信息
     * @return 添加结果
     */
    @PostMapping("/insert")
    public ResultBean<Void> insert(@RequestBody UserDTO userDTO) {
        final User user = userConverter.userDTO2user(userDTO);
        userService.insertOrUpdate(user);
        return ResultBean.success("添加成功!", null);
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 删除结果
     */
//    @GetMapping("/delete/{id}")
//    public ResultBean<Void> delete(@PathVariable("id") Long id) {
//        userService.deleteById(id);
//        return ResultBean.success("删除成功!", null);
//    }

    /**
     * 批量删除用户
     *
     * @param ids 用户ID列表
     * @return 批量删除结果
     */
//    @GetMapping("/batchDelete/{ids}")
//    public ResultBean<Void> batchDelete(@PathVariable("ids") String ids) {
//        userService.deleteByIds(ids);
//        return ResultBean.success("批量删除成功!", null);
//    }

    /**
     * 导出用户数据
     *
     * @param userQuery 查询参数
     */
    @PostMapping("/export")
    public void exportData(HttpServletResponse response, @RequestBody UserQuery userQuery) {
        final List<User> userList = userService.getAll(userQuery);

        final List<UserExcel> list = userConverter.userList2userExcelList(userList);
        try {
            EasyExcelUtil.writeWithSheetsWeb(response, "user列表")
                    .writeModel(UserExcel.class, list, "user")
                    .finish();
        } catch (IOException e) {
            throw new GlobalException("用户信息导出失败！");
        }
    }

    /**
     * 导入用户数据
     *
     * @param file 用户数据文件
     * @return 导入结果
     */
    @PostMapping("/import")
    public ResultBean<Void> importData(@RequestPart(value = "file", required = true) MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            final EasyExcelListener<UserExcel> listener = new EasyExcelListener<UserExcel>() {
                @Override
                protected void exec(List<UserExcel> list) {
                    final List<User> userList = userConverter.userExcelList2userList(list);
                    userService.batchInsert(userList);
                }
            };
            EasyExcelUtil.asyncReadModel(is, listener, UserExcel.class);
        } catch (IOException e) {
            throw new GlobalException("用户信息导入失败！");
        }
        return ResultBean.success("用户信息导入成功!", null);
    }

    /**
     * 下载用户导入模板
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        try {
            EasyExcelUtil.writeWithSheetsWeb(response, "user导入模板")
                    .writeModel(UserExcel.class, null, "user")
                    .finish();
        } catch (IOException e) {
            throw new GlobalException("用户信息模板下载失败！");
        }
    }

    /**
     * 注销用户
     *
     * @param id 用户ID
     * @return 注销结果
     */
    @GetMapping("/cancel/{id}")
    public ResultBean<Void> cancel(@PathVariable("id") Long id) {
        userService.cancelById(id);
        return ResultBean.success("注销成功!", null);
    }

    /**
     * 批量注销用户
     *
     * @param ids 用户ID列表
     * @return 批量注销结果
     */
    @GetMapping("/batchCancel/{ids}")
    public ResultBean<Void> batchCancel(@PathVariable("ids") String ids) {
        userService.cancelByIds(ids);
        return ResultBean.success("批量注销成功!", null);
    }

    /**
     * 获取用户总数
     *
     * @return 用户总数
     */
    @GetMapping("/total")
    public ResultBean<Integer> getTotalUsers() {
        final int total = userService.countTotalUsers();
        return ResultBean.success(total);
    }

    /**
     * 获取近七天用户数新增趋势
     *
     * @return 近七天每天的新用户数
     */
    @GetMapping("/trend")
    public ResultBean<java.util.List<java.util.Map<String, Object>>> getNewUsersTrend() {
        final java.util.List<java.util.Map<String, Object>> trend = userService.getNewUsersTrend();
        return ResultBean.success(trend);
    }

    /**
     * 上传用户头像
     *
     * @param file 头像文件
     * @return 头像文件URL
     */
    @PostMapping("/uploadAvatar")
    public ResultBean<String> uploadAvatar(@RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new GlobalException("请选择要上传的文件");
        }
        
        // 上传文件到MinIO，指定文件夹为avatars
        String avatarUrl = minioUtil.putObject(file, "avatars", null);
        
        if (avatarUrl == null) {
            throw new GlobalException("头像上传失败");
        }
        
        return ResultBean.success(avatarUrl);
    }

    /**
     * 获取用户头像
     *
     * @param filename 头像文件名
     * @param response 响应对象
     */
    @GetMapping("/avatar/{filename}")
    public void getAvatar(@PathVariable("filename") String filename, HttpServletResponse response) {
        minioUtil.getObject("avatars/" + filename, response);
    }
}
