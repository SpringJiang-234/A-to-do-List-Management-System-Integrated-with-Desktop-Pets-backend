package com.backend.domain.dto;

import lombok.*;

/**
 * 类别表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    /**
     * 主键
     */
    private Long id;

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

    /**
    * 排序顺序：越大越前
    */
    private Integer sortOrder;
}