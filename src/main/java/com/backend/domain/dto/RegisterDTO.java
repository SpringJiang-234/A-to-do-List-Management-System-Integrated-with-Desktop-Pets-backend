package com.backend.domain.dto;

import lombok.Data;

/**
 * 注册DTO
 */
@Data
public class RegisterDTO {
    /**
     * 账号
     */
    private String account;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 用户名
     */
    private String username;
}
