package com.backend.domain.dto;

import com.backend.bean.base.BaseEntity;
import lombok.*;

/**
 * 公告表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDTO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 是否置顶：1-否 2-是
     */
    private Integer isTop;
}