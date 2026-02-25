package com.backend.domain.excel;

import com.backend.bean.base.BaseExcel;
import lombok.*;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 待办标签关联表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoTagExcel extends BaseExcel {
    /**
     * 待办事项id
     */
    @ExcelProperty(value = "待办事项id")
    private Long todoId;

    /**
     * 标签id
     */
    @ExcelProperty(value = "标签id")
    private Long tagId;
}