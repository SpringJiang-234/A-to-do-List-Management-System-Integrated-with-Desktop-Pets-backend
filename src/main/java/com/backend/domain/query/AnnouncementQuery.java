package com.backend.domain.query;

import com.backend.bean.LocalDateTimeRange;
import com.backend.bean.base.BaseQuery;
import lombok.*;

/**
 * 公告表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementQuery extends BaseQuery {
    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容：显示部分
     */
    private String content;

    /**
     * 是否置顶：1-否 2-是
     */
    private Integer isTop;

    /**
     * 创建时间（发布时间）
     */
    private LocalDateTimeRange createTime;

    /**
     * 更新时间
     */
    private LocalDateTimeRange updateTime;
}