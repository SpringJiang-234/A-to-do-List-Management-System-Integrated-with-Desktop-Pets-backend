package com.backend.domain.query;

import com.backend.bean.base.BaseQuery;
import lombok.*;

/**
 * 待办标签关联表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoTagQuery extends BaseQuery {
    /**
     * 待办事项id
     */
    private Long todoId;

    /**
     * 标签id
     */
    private Long tagId;
}