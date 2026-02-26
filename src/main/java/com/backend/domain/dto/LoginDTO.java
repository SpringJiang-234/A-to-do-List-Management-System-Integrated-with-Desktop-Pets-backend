package com.backend.domain.dto;

import lombok.Data;

/**
 * 登录DTO
 */
@Data
public class LoginDTO {
    /**
     * 账号
     */
    private String account;
    
    /**
     * 密码
     */
    private String password;
}
