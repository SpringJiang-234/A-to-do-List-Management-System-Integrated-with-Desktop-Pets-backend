package com.backend.domain.info;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserInfo {
    private Long id;
    private String username;
    private String account;
    private String role;
    private String token;
    private String avatar;
    private Integer gender;
    private LocalDate birth;
}
