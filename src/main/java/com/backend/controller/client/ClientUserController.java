package com.backend.controller.client;

import com.backend.bean.ResultBean;
import com.backend.converter.UserConverter;
import com.backend.domain.dto.UserDTO;
import com.backend.domain.entity.User;
import com.backend.domain.info.UserInfo;
import com.backend.service.UserService;
import com.backend.utils.AuthUtil;
import com.backend.utils.MinioUtil;
import com.backend.utils.PasswordUtil;
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
    private AuthUtil authUtil;

    /**
     * 获取当前用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/info")
    public ResultBean<UserInfo> getUserInfo(HttpServletRequest request) {
        Long userId = authUtil.getCurrentUserId(request);
        if (userId == null) {
            return ResultBean.error("未登录或登录已过期", null);
        }

        User user = userService.getById(userId);
        if (user == null) {
            return ResultBean.error("用户不存在", null);
        }

        UserInfo userInfo = userConverter.user2userInfo(user);
        userInfo.setToken(request.getHeader("Authorization"));
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
        Long userId = authUtil.getCurrentUserId(request);
        if (userId == null) {
            return ResultBean.error("未登录或登录已过期", null);
        }

        User existingUser = userService.getById(userId);
        if (existingUser == null) {
            return ResultBean.error("用户不存在", null);
        }

        if (userDTO.getNickname() != null) {
            existingUser.setNickname(userDTO.getNickname());
        }

        if (userDTO.getGender() != null) {
            existingUser.setGender(userDTO.getGender());
        }

        if (userDTO.getBirth() != null) {
            existingUser.setBirth(userDTO.getBirth());
        }

        if (userDTO.getAvatar() != null) {
            existingUser.setAvatar(userDTO.getAvatar());
        }

        if (userDTO.getPasswordHash() != null && !userDTO.getPasswordHash().isEmpty()) {
            String hashedPassword = PasswordUtil.hashPassword(userDTO.getPasswordHash());
            existingUser.setPasswordHash(hashedPassword);
        }

        int result = userService.insertOrUpdate(existingUser);
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
        if (file.isEmpty()) {
            return ResultBean.error("请选择要上传的文件", null);
        }
        
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return ResultBean.error("只支持上传图片格式", null);
        }
        
        long maxSize = 5 * 1024 * 1024;
        if (file.getSize() > maxSize) {
            return ResultBean.error("图片大小不能超过 5MB", null);
        }
        
        String avatarUrl = minioUtil.putObject(file);
        
        if (avatarUrl == null) {
            return ResultBean.error("头像上传失败", null);
        }
        
        return ResultBean.success(avatarUrl, avatarUrl);
    }
}
