package com.backend.domain.vo;

import com.backend.bean.base.BaseVO;
import lombok.*;

import java.time.LocalDate;

/**
 * 待办事项表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoVO extends BaseVO {
    // 部分属性为隐私，不给管理员看

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 开始日期
    */
    private LocalDate startDate;

    /**
    * 截止日期(结束日期)
    */
    private LocalDate endDate;

    /**
    * 状态：1-未完成 2-完成 3-放弃
    */
    private String status;

}