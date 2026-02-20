package com.backend.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.backend.bean.base.BaseExcel;
import lombok.*;

/**
 * 用户标签关联表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserTagExcel extends BaseExcel {
    /**
    * 用户id
    */
    @ExcelProperty(value = "用户id")
    private Long userId;

    /**
    * 标签id
    */
    @ExcelProperty(value = "标签id")
    private Long tagId;
}