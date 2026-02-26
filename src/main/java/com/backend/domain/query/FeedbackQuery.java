package com.backend.domain.query;

import com.backend.bean.LocalDateTimeRange;
import com.backend.bean.base.BaseQuery;
import lombok.*;

/**
 * 反馈表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackQuery extends BaseQuery {
    /**
    * 用户id
    */
    private Long userId;

    /**
    * 反馈标题
    */
    private String title;

    /**
    * 反馈内容
    */
    private String content;

    /**
    * 处理状态1-未受理 2-已受理 3-已解决
    */
    private Integer status;

    /**
    * 回复内容
    */
    private String replyContent;

    /**
    * 创建时间
    */
    private LocalDateTimeRange createTime;

    /**
    * 更新时间
    */
    private LocalDateTimeRange updateTime;
}