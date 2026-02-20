package com.backend.controller;

import com.backend.bean.PageBean;
import com.backend.bean.ResultBean;
import com.backend.converter.UserTagConverter;
import com.backend.domain.details.UserTagDetails;
import com.backend.domain.dto.UserTagDTO;
import com.backend.domain.entity.UserTag;
import com.backend.domain.excel.UserTagExcel;
import com.backend.domain.query.UserTagQuery;
import com.backend.domain.vo.UserTagVO;
import com.backend.ex.GlobalException;
import com.backend.service.UserTagService;
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
 * 用户标签关联控制层
 *
 */
@RestController
@RequestMapping("/userTag")
public class UserTagController {
    @Autowired
    private UserTagService userTagService;
    @Autowired
    private UserTagConverter userTagConverter;


    @PostMapping("/page")
    public ResultBean<PageBean<UserTagVO>> page(@RequestBody UserTagQuery userTagQuery) {
        final PageBean<UserTag> userTagPageBean = userTagService.getPage(userTagQuery);
        final PageBean<UserTagVO> pageBean = userTagConverter.userTagPageBean2userTagVOPageBean(userTagPageBean);
        return ResultBean.success(pageBean);
    }

    @GetMapping("/details/{id}")
    public ResultBean<UserTagDetails> getDetails(@PathVariable("id") Long id) {
        final UserTag userTag = userTagService.getById(id);
        final UserTagDetails userTagDetails = userTagConverter.userTag2userTagDetails(userTag);
        return ResultBean.success(userTagDetails);
    }

    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody UserTagDTO userTagDTO) {
        final UserTag userTag = userTagConverter.userTagDTO2userTag(userTagDTO);
        userTagService.insertOrUpdate(userTag);
        return ResultBean.success("修改成功!", null);
    }

    @PostMapping("/insert")
    public ResultBean<Void> insert(@RequestBody UserTagDTO userTagDTO) {
        final UserTag userTag = userTagConverter.userTagDTO2userTag(userTagDTO);
        userTagService.insertOrUpdate(userTag);
        return ResultBean.success("添加成功!", null);
    }

    @GetMapping("/delete/{id}")
    public ResultBean<Void> delete(@PathVariable("id") Long id) {
        userTagService.deleteById(id);
        return ResultBean.success("删除成功!", null);
    }

    @GetMapping("/batchDelete/{ids}")
    public ResultBean<Void> batchDelete(@PathVariable("ids") String ids) {
        userTagService.deleteByIds(ids);
        return ResultBean.success("批量删除成功!", null);
    }

    @PostMapping("/export")
    public void exportData(HttpServletResponse response, @RequestBody UserTagQuery userTagQuery) {
        final PageBean<UserTag> pageBean = userTagService.getPage(userTagQuery);

        final List<UserTagExcel> list = userTagConverter.userTagList2userTagExcelList(pageBean.getRecords());
        try {
            EasyExcelUtil.writeWithSheetsWeb(response, "userTag列表")
                    .writeModel(UserTagExcel.class, list, "userTag")
                    .finish();
        } catch (IOException e) {
            throw new GlobalException("用户标签关联信息导出失败！");
        }
    }

    @PostMapping("/import")
    public ResultBean<Void> importData(@RequestPart(value = "file", required = true) MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            final EasyExcelListener<UserTagExcel> listener = new EasyExcelListener<UserTagExcel>() {
                @Override
                protected void exec(List<UserTagExcel> list) {
                    final List<UserTag> userTagList = userTagConverter.userTagExcelList2userTagList(list);
                    userTagService.batchInsert(userTagList);
                }
            };
            EasyExcelUtil.asyncReadModel(is, listener, UserTagExcel.class);
        } catch (IOException e) {
            throw new GlobalException("用户标签关联信息导入失败！");
        }
        return ResultBean.success("用户标签关联信息导入成功!", null);
    }
}
