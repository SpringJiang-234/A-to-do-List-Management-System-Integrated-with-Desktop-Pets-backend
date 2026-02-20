package com.backend.domain.entity;

import java.util.Date;

import com.backend.bean.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 类别表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {
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