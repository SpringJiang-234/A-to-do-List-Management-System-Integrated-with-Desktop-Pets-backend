package com.backend.controller.client;

import com.backend.bean.ResultBean;
import com.backend.converter.UserConverter;
import com.backend.domain.dto.UserDTO;
import com.backend.domain.entity.User;
import com.backend.domain.info.UserInfo;
import com.backend.service.UserService;
import com.backend.utils.MinioUtil;
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

    /**
     * 获取当前用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/info")
    public ResultBean<UserInfo> getUserInfo() {
        return null;
    }

    /**
     * 修改用户信息
     *
     * @param userDTO 用户信息
     * @return 修改结果
     */
    @PostMapping("/update")
    public ResultBean<Void> update(@RequestBody UserDTO userDTO) {
        return null;
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
