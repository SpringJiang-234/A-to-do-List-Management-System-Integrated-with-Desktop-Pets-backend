package com.backend.domain.vo;

import com.backend.bean.base.BaseVO;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 审计日志表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogVO extends BaseVO {
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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}