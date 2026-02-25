package com.backend.domain.excel;

import com.backend.bean.base.BaseExcel;
import lombok.*;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 审计日志表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogExcel extends BaseExcel {
    /**
     * 操作人id
     */
    @ExcelProperty(value = "操作人id")
    private Long userId;

    /**
     * 操作人
     */
    @ExcelProperty(value = "操作人")
    private String username;

    /**
     * 操作内容
     */
    @ExcelProperty(value = "操作内容")
    private String operation;

    /**
     * 操作模块
     */
    @ExcelProperty(value = "操作模块")
    private String module;

    /**
     * IP地址
     */
    @ExcelProperty(value = "IP地址")
    private String ipAddress;
}