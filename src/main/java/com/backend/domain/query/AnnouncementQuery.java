package com.backend.domain.query;

import com.backend.bean.base.BaseEntity;
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
     * 是否置顶：1-未置顶 2-置顶
     */
    private Integer isTop;
}