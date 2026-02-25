package com.backend.domain.dto;

import lombok.*;

/**
 * 待办标签关联表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoTagDTO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 待办事项id
     */
    private Long todoId;

    /**
     * 标签id
     */
    private Long tagId;
}