package com.backend.domain.details;

import java.time.LocalDate;

import com.backend.bean.base.BaseDetails;
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
public class UserDetails extends BaseDetails {
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
    private String gender;

    /**
     * 生日
     */
    private LocalDate birth;

    /**
     * 状态：1-正常 2-已注销
     */
    private String status;

    /**
     * 用户类型：1-管理员 2-普通用户
     */
    private String type;
}
