package com.backend.controller;

import com.backend.bean.ResultBean;
import com.backend.converter.UserConverter;
import com.backend.domain.info.UserInfo;
import com.backend.domain.dto.LoginDTO;
import com.backend.domain.dto.RegisterDTO;
import com.backend.domain.entity.User;
import com.backend.service.UserService;
import com.backend.utils.RedisUtil;
import com.backend.utils.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class SecurityController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/accountExist/{account}")
    public ResultBean<Void> accountExist(@PathVariable("account") String account) {
        int count = userService.isAccountExist(account);
        if (count > 0) {
            return ResultBean.error("账户已存在!", null);
        } else {
            return ResultBean.success("账户可用!", null);
        }
    }

    @PostMapping("/register")
    public ResultBean<Void> register(@RequestBody RegisterDTO registerDTO) {
        int result = userService.register(registerDTO);
        if (result > 0) {
            return ResultBean.success("注册成功!", null);
        } else {
            return ResultBean.error("注册失败，账号可能已存在!", null);
        }
    }

    //TODO 脑袋晕了先休息了，刚刚测试到这里
    @PostMapping("/login")
    public ResultBean<UserInfo> login(@RequestBody LoginDTO loginDTO) {
        final User user = userService.login(loginDTO);
        if (user != null) {
            final UserInfo userInfo = userConverter.user2userInfo(user);
            final String token = StringUtil.genStr(16);
            userInfo.setToken(token);
            // 将token--user id缓存到Redis中，设置过期时间为24小时
            redisUtil.set("token:" + token, user.getId(), 86400);
            return ResultBean.success("登录成功!", userInfo);
        } else {
            return ResultBean.error("用户名或密码错误!", null);
        }
    }

    @GetMapping("/logout")
    public ResultBean<Void> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            // 从Redis中删除token
            redisUtil.delete("token:" + token);
            return ResultBean.success("登出成功!", null);
        } else {
            return ResultBean.error("登出失败!", null);
        }
    }
}

