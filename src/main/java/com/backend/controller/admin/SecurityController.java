package com.backend.controller.admin;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin/security")
public class SecurityController {
    private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 检测账户是否已存在
     *
     * @param account 账户
     * @return 账户是否已存在
     */
    @GetMapping("/accountExist/{account}")
    public ResultBean<Void> accountExist(@PathVariable("account") String account) {
        int count = userService.isAccountExist(account);
        if (count > 0) {
            return ResultBean.error("账户已存在!", null);
        } else {
            return ResultBean.success("账户可用!", null);
        }
    }

    /**
     * 注册
     *
     * @param registerDTO 注册信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public ResultBean<Void> register(@RequestBody RegisterDTO registerDTO) {
        int result = userService.register(registerDTO);
        if (result > 0) {
            return ResultBean.success("注册成功!", null);
        } else {
            return ResultBean.error("注册失败，账号可能已存在!", null);
        }
    }

    /**
     * 登录
     *
     * @param loginDTO 登录信息
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResultBean<UserInfo> login(@RequestBody LoginDTO loginDTO) {
        final User user = userService.login(loginDTO);
        if (user != null) {
            final UserInfo userInfo = userConverter.user2userInfo(user);
            final String token = StringUtil.genStr(16);
            userInfo.setToken(token);
            try {
                redisUtil.set("token:" + token, user.getId(), 86400);
            } catch (Exception e) {
                logger.warn("Redis连接失败，登录功能将不使用Redis缓存，token: {}", token);
            }
            return ResultBean.success("登录成功!", userInfo);
        } else {
            return ResultBean.error("用户名或密码错误!", null);
        }
    }

    /**
     * 登出
     *
     * @param request 请求
     * @return 登出结果
     */
    @GetMapping("/logout")
    public ResultBean<Void> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            try {
                redisUtil.delete("token:" + token);
            } catch (Exception e) {
                logger.warn("Redis连接失败，登出功能将不使用Redis缓存，token: {}", token);
            }
            return ResultBean.success("登出成功!", null);
        } else {
            return ResultBean.error("登出失败!", null);
        }
    }
}

