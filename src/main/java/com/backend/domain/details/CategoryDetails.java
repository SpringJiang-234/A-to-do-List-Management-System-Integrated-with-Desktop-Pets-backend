package com.backend.domain.details;

import com.backend.bean.base.BaseDetails;
import lombok.*;

/**
 * 类别表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDetails extends BaseDetails {
    /**
    * 创建时间
    */
    private String createTime;

    /**
    * 修改时间
    */
    private String updateTime;

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
    private String isDefault;

    /**
    * 排序顺序：越大越前
    */
    private Integer sortOrder;
}