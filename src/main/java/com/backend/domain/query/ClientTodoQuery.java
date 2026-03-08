package com.backend.domain.query;

import lombok.*;

import java.util.List;

/**
 * 客户端待办事项查询条件
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClientTodoQuery extends TodoQuery {
    /**
    * 标签ID列表
    */
    private List<Long> tagIdList;
}
