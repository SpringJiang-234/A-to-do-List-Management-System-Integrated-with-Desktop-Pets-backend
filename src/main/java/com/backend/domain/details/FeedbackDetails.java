package com.backend.domain.details;

import com.backend.bean.base.BaseEntity;
import lombok.*;

/**
 * 反馈表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDetails extends BaseEntity {
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
    private String status;

    /**
    * 回复内容
    */
    private String replyContent;
}