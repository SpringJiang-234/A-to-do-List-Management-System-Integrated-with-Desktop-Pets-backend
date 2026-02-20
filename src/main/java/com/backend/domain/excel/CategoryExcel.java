package com.backend.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.backend.bean.base.BaseExcel;
import lombok.*;

/**
 * 类别表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryExcel extends BaseExcel {
    /**
    * 用户id
    */
    @ExcelProperty(value = "用户id")
    private Long userId;

    /**
    * 类别名
    */
    @ExcelProperty(value = "类别名")
    private String name;

    /**
    * 是否系统默认：1-是 2-不是
    */
    @ExcelProperty(value = "是否系统默认")
    private String isDefault;

    /**
    * 排序顺序：越大越前
    */
    @ExcelProperty(value = "排序顺序")
    private Integer sortOrder;
}