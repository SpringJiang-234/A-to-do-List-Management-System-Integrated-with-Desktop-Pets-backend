package com.backend.domain.excel;

import java.time.LocalDate;

import com.alibaba.excel.annotation.ExcelProperty;
import com.backend.bean.base.BaseExcel;
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
public class UserExcel extends BaseExcel {
    /**
     * 邮箱账号
     */
    @ExcelProperty(value = "邮箱")
    private String account;

    /**
     * 加密后的密码
     */
    @ExcelProperty(value = "密码")
    private String passwordHash;

    /**
     * 昵称
     */
    @ExcelProperty(value = "昵称")
    private String nickname;

    /**
     * 头像
     */
    @ExcelProperty(value = "头像")
    private String avatar;

    /**
     * 性别：1-男 2-女 3-未知
     */
    @ExcelProperty(value = "性别")
    private String gender;

    /**
     * 生日
     */
    @ExcelProperty(value = "生日")
    private LocalDate birth;

    /**
     * 状态：1-正常 2-已注销
     */
    @ExcelProperty(value = "状态")
    private String status;

    /**
     * 用户类型：1-管理员 2-普通用户
     */
    @ExcelProperty(value = "用户类型")
    private String type;
}