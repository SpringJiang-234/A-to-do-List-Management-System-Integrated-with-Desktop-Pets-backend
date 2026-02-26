package com.backend.domain.info;

import lombok.Data;

@Data
public class UserInfo {
    private Long id;
    private String username;
    private String account;
    private String role;
    private String token;
}
