package com.backend.domain.dto;

import lombok.*;

/**
 * 标签表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO {
    /**
     * 主键
     */
    private Long id;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 标签名
    */
    private String name;

    /**
    * 颜色：六位HEX
    */
    private String color;

    /**
    * 排序顺序：越大越前
    */
    private Integer sortOrder;
}