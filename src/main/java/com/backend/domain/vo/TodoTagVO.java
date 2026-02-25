package com.backend.domain.vo;

import com.backend.bean.base.BaseVO;
import lombok.*;

/**
 * 待办标签关联表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoTagVO extends BaseVO {
    /**
     * 待办事项id
     */
    private Long todoId;

    /**
     * 标签id
     */
    private Long tagId;
}