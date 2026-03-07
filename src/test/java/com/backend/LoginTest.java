package com.backend;

import com.backend.domain.dto.LoginDTO;
import com.backend.service.UserService;
import com.backend.domain.query.UserQuery;
import com.backend.mapper.UserMapper;
import com.backend.domain.entity.User;
import com.backend.utils.PasswordUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

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

    @Test
    void testAdminLogin() {
        System.out.println("========== 开始测试管理员登录 ==========");
        
        // 先查询数据库中 id=1 的用户信息（真正的管理员）
        User adminUser = userMapper.selectByPrimaryKey(1L);
        
        if (adminUser == null) {
            System.out.println("错误: 数据库中找不到 id=1 的用户!");
            return;
        }
        
        System.out.println("数据库中 id=1 的用户信息:");
        System.out.println("  - ID: " + adminUser.getId());
        System.out.println("  - 账号: " + adminUser.getAccount());
        System.out.println("  - 昵称: " + adminUser.getNickname());
        System.out.println("  - 用户类型: " + adminUser.getType() + " (1=管理员, 2=普通用户)");
        System.out.println("  - 状态: " + adminUser.getStatus() + " (1=正常, 2=已注销)");
        System.out.println("  - 密码哈希: " + adminUser.getPasswordHash());

        // 创建登录DTO，使用数据库中实际的账号
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setAccount(adminUser.getAccount());
        loginDTO.setPassword("123456abc");

        System.out.println("\n尝试登录:");
        System.out.println("  - 登录账号: " + loginDTO.getAccount());
        System.out.println("  - 登录密码: " + loginDTO.getPassword());

        // 测试密码验证
        boolean passwordMatch = PasswordUtil.checkPassword("123456abc", adminUser.getPasswordHash());
        System.out.println("密码验证结果: " + passwordMatch);

        // 生成新的密码哈希
        String newHash = PasswordUtil.hashPassword("123456abc");
        System.out.println("新生成的密码哈希: " + newHash);
        System.out.println("新生成的密码哈希验证: " + PasswordUtil.checkPassword("123456abc", newHash));

        // 调用管理员登录方法
        System.out.println("\n调用 adminLogin 方法...");
        var user = userService.adminLogin(loginDTO);

        // 输出登录结果
        if (user != null) {
            System.out.println("✓ 登录成功! 用户: " + user.getAccount());
        } else {
            System.out.println("✗ 登录失败!");
            System.out.println("可能的原因:");
            System.out.println("  1. 用户类型不是管理员 (type != 1)");
            System.out.println("  2. 密码不匹配");
            System.out.println("  3. 用户状态不正常 (status != 1)");
        }
    }
}
