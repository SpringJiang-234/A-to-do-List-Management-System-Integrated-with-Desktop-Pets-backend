package com.backend.domain.entity;

import java.util.Date;

import com.backend.bean.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 反馈表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Feedback extends BaseEntity {
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
}