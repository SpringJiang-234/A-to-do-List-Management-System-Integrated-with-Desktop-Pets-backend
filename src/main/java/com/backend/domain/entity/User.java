package com.backend.domain.entity;

import java.time.LocalDate;

import com.backend.bean.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
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