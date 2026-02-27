package com.backend;

import com.backend.domain.dto.LoginDTO;
import com.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginTest {

    @Autowired
    private UserService userService;

    @Test
    void testLogin() {
        // 创建登录DTO
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setAccount("winxp");
        loginDTO.setPassword("123456abc");

        // 调用登录方法
        var user = userService.login(loginDTO);

        // 输出登录结果
        if (user != null) {
            System.out.println("Login success! User: " + user.getAccount());
        } else {
            System.out.println("Login failed!");
        }
    }
}
