package com.backend.domain.details;

import com.backend.bean.base.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 待办事项表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoDetails extends BaseEntity {
    /**
    * 用户id
    */
    private Long userId;

    /**
    * 标题
    */
    private String title;

    /**
    * 内容
    */
    private String content;

    /**
    * 类别id
    */
    private Long categoryId;

    /**
    * 优先级：1-非常低 2-低 3-中 4-高 默认值为非常低
    */
    private String priority;

    /**
    * 开始时间
    */
    private LocalDateTime startTime;

    /**
    * 截止时间(结束时间)
    */
    private LocalDateTime endTime;

    /**
    * 状态：1-未完成 2-完成 3-放弃
    */
    private String status;

    /**
    * 完成/放弃时间
    */
    private LocalDateTime finishTime;

    /**
    * 是否已删除：1-未删除 2-已删除
    */
    private String isDelete;

    /**
    * 是否置顶：1-未置顶 2-置顶
    */
    private String isTop;
}