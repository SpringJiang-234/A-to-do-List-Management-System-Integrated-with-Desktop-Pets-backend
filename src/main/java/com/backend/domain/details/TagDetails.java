package com.backend.domain.details;

import com.backend.bean.base.BaseDetails;
import lombok.*;

/**
 * 标签表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TagDetails extends BaseDetails {
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