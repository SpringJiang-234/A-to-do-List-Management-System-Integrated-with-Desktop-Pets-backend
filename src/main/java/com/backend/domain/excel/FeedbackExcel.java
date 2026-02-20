package com.backend.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.backend.bean.base.BaseExcel;
import lombok.*;

/**
 * 反馈表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackExcel extends BaseExcel {
    /**
    * 用户id
    */
    @ExcelProperty(value = "用户id")
    private Long userId;

    /**
    * 反馈标题
    */
    @ExcelProperty(value = "反馈标题")
    private String title;

    /**
    * 反馈内容
    */
    @ExcelProperty(value = "反馈内容")
    private String content;

    /**
    * 处理状态1-未受理 2-已受理 3-已解决
    */
    @ExcelProperty(value = "处理状态")
    private String status;

    /**
    * 回复内容
    */
    @ExcelProperty(value = "回复内容")
    private String replyContent;
}