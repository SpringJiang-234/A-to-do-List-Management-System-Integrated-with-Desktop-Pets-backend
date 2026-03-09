package com.backend.controller.client;

import com.backend.bean.ResultBean;
import com.backend.converter.UserConverter;
import com.backend.domain.dto.UserDTO;
import com.backend.domain.entity.User;
import com.backend.domain.info.UserInfo;
import com.backend.service.UserService;
import com.backend.utils.MinioUtil;
import com.backend.utils.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户控制层
 */
@RestController
@RequestMapping("/client/user")
public class ClientUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取当前用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/info")
    public ResultBean<UserInfo> getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return ResultBean.error("未登录", null);
        }
        
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        
        Object userIdObj = redisUtil.get("token:" + token);
        if (userIdObj == null) {
            return ResultBean.error("登录已过期，请重新登录", null);
        }
        
        Long userId = Long.valueOf(userIdObj.toString());
        
        User user = userService.getById(userId);
        if (user == null) {
            return ResultBean.error("用户不存在", null);
        }
        
        UserInfo userInfo = userConverter.user2userInfo(user);
        userInfo.setToken(token);
        return ResultBean.success(userInfo);
    }

    /**
     * 修改用户信息
     *
     * @param userDTO 用户信息
     * @return 修改结果
     */
    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return ResultBean.error("未登录", null);
        }
        
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        
        Object userIdObj = redisUtil.get("token:" + token);
        if (userIdObj == null) {
            return ResultBean.error("登录已过期，请重新登录", null);
        }
        
        Long userId = Long.valueOf(userIdObj.toString());
        
        final User user = userConverter.userDTO2user(userDTO);
        user.setId(userId);
        int result = userService.insertOrUpdate(user);
        if (result > 0) {
            return ResultBean.success("修改成功!", null);
        } else {
            return ResultBean.error("修改失败", null);
        }
    }

    /**
     * 上传用户头像
     *
     * @param file 头像文件
     * @return 头像文件URL
     */
    @PostMapping("/uploadAvatar")
    public ResultBean<String> uploadAvatar(@RequestPart("file") MultipartFile file) {
        return null;
    }
}
