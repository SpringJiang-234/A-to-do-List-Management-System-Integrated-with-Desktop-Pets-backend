package com.backend.domain.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 邮箱账号
     */
    private String account;

    /**
     * 加密后的密码
     */
    private String passwordHash;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别：1-男 2-女 3-未知
     */
    private Integer gender;

    /**
     * 生日
     */
    private LocalDate birth;

    /**
     * 状态：1-正常 2-已注销
     */
    private Integer status;
}
