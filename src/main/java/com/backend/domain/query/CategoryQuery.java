package com.backend.domain.query;

import com.backend.bean.base.BaseEntity;
import com.backend.bean.base.BaseQuery;
import lombok.*;

/**
 * 类别表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryQuery extends BaseQuery {
    /**
    * 用户id
    */
    private Long userId;

    /**
    * 类别名
    */
    private String name;

    /**
    * 是否系统默认：1-是 2-不是
    */
    private Integer isDefault;
}