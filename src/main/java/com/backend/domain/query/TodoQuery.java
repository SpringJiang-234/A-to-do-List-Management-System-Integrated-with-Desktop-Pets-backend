package com.backend.domain.query;

import com.backend.bean.LocalDateRange;
import com.backend.bean.LocalDateTimeRange;
import com.backend.bean.base.BaseQuery;
import lombok.*;

/**
 * 待办事项表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoQuery extends BaseQuery {
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
     * 优先级：4-重要且紧急，3-重要不紧急，2-不重要但紧急，1-不重要不紧急，默认为不重要不紧急
     */
    private Integer priority;

    /**
    * 开始日期
    */
    private LocalDateRange startDate;

    /**
    * 截止日期(结束日期)
    */
    private LocalDateRange endDate;

    /**
    * 状态：1-未完成 2-完成 3-放弃
    */
    private Integer status;

    /**
    * 完成/放弃时间
    */
    private LocalDateTimeRange finishTime;

    /**
    * 是否已删除：1-未删除 2-已删除
    */
    private Integer isDelete;

    /**
    * 是否置顶：1-未置顶 2-置顶
    */
    private Integer isTop;

    /**
     * 标签ID
     */
    private Long tagId;
}