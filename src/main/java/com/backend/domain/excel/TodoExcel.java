package com.backend.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.backend.bean.base.BaseExcel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 待办事项表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoExcel extends BaseExcel {
    /**
    * 用户id
    */
    @ExcelProperty(value = "用户id")
    private Long userId;

    /**
    * 标题
    */
    @ExcelProperty(value = "标题")
    private String title;

    /**
    * 内容
    */
    @ExcelProperty(value = "内容")
    private String content;

    /**
    * 类别id
    */
    @ExcelProperty(value = "类别id")
    private Long categoryId;

    /**
     * 优先级：4-重要且紧急，3-重要不紧急，2-不重要但紧急，1-不重要不紧急，默认为不重要不紧急
     */
    @ExcelProperty(value = "优先级")
    private String priority;

    /**
    * 开始时间
    */
    @ExcelProperty(value = "开始时间")
    private LocalDateTime startTime;

    /**
    * 截止时间(结束时间)
    */
    @ExcelProperty(value = "截止时间")
    private LocalDateTime endTime;

    /**
    * 状态：1-未完成 2-完成 3-放弃
    */
    @ExcelProperty(value = "状态")
    private String status;

    /**
    * 完成/放弃时间
    */
    @ExcelProperty(value = "完成/放弃时间")
    private LocalDateTime finishTime;

    /**
    * 是否已删除：1-未删除 2-已删除
    */
    @ExcelProperty(value = "是否已删除")
    private String isDelete;

    /**
    * 是否置顶：1-未置顶 2-置顶
    */
    @ExcelProperty(value = "是否置顶")
    private String isTop;

    /**
     * 专注时间：用户完整使用计时器积累的时间（秒）
     */
    @ExcelProperty(value = "专注时间（秒）")
    private Long focusTime;
}