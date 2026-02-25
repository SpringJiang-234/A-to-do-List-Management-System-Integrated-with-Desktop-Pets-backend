package com.backend.domain.query;

import com.backend.bean.LocalDateRange;
import com.backend.bean.base.BaseQuery;
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
public class UserQuery extends BaseQuery {
    /**
     * 邮箱账号
     */
    private String account;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别：1-男 2-女 3-未知
     */
    private Integer gender;

    /**
     * 生日
     */
    private LocalDateRange birth;

    /**
     * 状态：1-正常 2-已注销
     */
    private Integer status;

    /**
     * 用户类型：1-管理员 2-普通用户
     */
    private Integer type;
}