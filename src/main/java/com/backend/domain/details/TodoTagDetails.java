package com.backend.domain.details;

import com.backend.bean.base.BaseDetails;
import lombok.*;

/**
 * 待办标签关联表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoTagDetails extends BaseDetails {
    /**
     * 待办事项id
     */
    private Long todoId;

    /**
     * 标签id
     */
    private Long tagId;
}