package com.backend.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.backend.bean.base.BaseExcel;
import lombok.*;

/**
 * 公告表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementExcel extends BaseExcel {
    /**
     * 公告标题
     */
    @ExcelProperty(value = "公告标题")
    private String title;

    /**
     * 公告内容
     */
    @ExcelProperty(value = "公告内容")
    private String content;

    /**
     * 是否置顶：1-否 2-是
     */
    @ExcelProperty(value = "是否置顶")
    private String isTop;
}