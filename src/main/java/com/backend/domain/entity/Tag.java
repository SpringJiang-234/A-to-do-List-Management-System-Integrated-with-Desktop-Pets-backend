package com.backend.domain.entity;

import java.util.Date;

import com.backend.bean.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 标签表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Tag extends BaseEntity {
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