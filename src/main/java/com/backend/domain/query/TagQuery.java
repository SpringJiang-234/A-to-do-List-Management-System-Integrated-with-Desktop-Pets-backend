package com.backend.domain.query;

import com.backend.bean.base.BaseEntity;
import com.backend.bean.base.BaseQuery;
import lombok.*;

/**
 * 标签表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TagQuery extends BaseQuery {
    /**
    * 用户id
    */
    private Long userId;

    /**
    * 标签名
    */
    private String name;

}