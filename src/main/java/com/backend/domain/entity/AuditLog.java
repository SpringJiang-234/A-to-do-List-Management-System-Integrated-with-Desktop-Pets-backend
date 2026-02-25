package com.backend.domain.entity;

import java.time.LocalDateTime;
import java.util.Date;

import com.backend.bean.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 审计日志表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuditLog extends BaseEntity {
    /**
     * 操作人id
     */
    private Long userId;

    /**
     * 操作人
     */
    private String username;

    /**
     * 操作内容
     */
    private String operation;

    /**
     * 操作模块
     */
    private String module;

    /**
     * IP地址
     */
    private String ipAddress;
}