package com.backend.domain.query;

import com.backend.bean.base.BaseEntity;
import com.backend.bean.base.BaseQuery;
import lombok.*;

/**
 * 用户标签关联表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserTagQuery extends BaseQuery {
    /**
    * 用户id
    */
    private Long userId;

    /**
    * 标签id
    */
    private Long tagId;
}