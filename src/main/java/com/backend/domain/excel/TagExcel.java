package com.backend.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.backend.bean.base.BaseExcel;
import lombok.*;

/**
 * 标签表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TagExcel extends BaseExcel {
    /**
    * 用户id
    */
    @ExcelProperty(value = "用户id")
    private Long userId;

    /**
    * 标签名
    */
    @ExcelProperty(value = "标签名")
    private String name;

    /**
    * 颜色：六位HEX
    */
    @ExcelProperty(value = "颜色")
    private String color;

    /**
    * 排序顺序：越大越前
    */
    @ExcelProperty(value = "排序顺序")
    private Integer sortOrder;
}