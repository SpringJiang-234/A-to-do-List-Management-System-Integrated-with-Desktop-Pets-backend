package com.backend.domain.query;

import com.backend.bean.LocalDateTimeRange;
import com.backend.bean.base.BaseQuery;
import lombok.*;

import java.util.List;

/**
 * 客户端待办事项查询条件
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ClientTodoQuery extends BaseQuery {
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
     * 类别ID列表
     */
    private List<Long> categoryIdList;

    /**
     * 优先级列表
     */
    private List<Integer> priorityList;

    /**
     * 开始时间
     */
    private LocalDateTimeRange startTime;

    /**
     * 截止时间(结束时间)
     */
    private LocalDateTimeRange endTime;

    /**
     * 状态列表
     */
    private List<Integer> statusList;

    /**
     * 是否已删除：1-未删除 2-已删除
     */
    private Integer isDelete;

    /**
     * 置顶列表
     */
    private List<Integer> isTopList;

    /**
     * 标签ID列表
     */
    private List<Long> tagIdList;
}
