package com.backend.domain.vo;

import com.backend.bean.base.BaseVO;
import lombok.*;

import java.time.LocalDate;

/**
 * 客户端待办事项表
 */
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ClientTodoVO extends BaseVO {
    /**
    * 用户id
    */
    private Long userId;

    /**
    * 标题
    */
    private String title;

    /**
    * 内容
    */
    private String content;

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
    private Integer status;

    /**
    * 是否置顶：1-未置顶 2-置顶
    */
    private Integer isTop;

    /**
     * 优先级：4-重要且紧急，3-重要不紧急，2-不重要但紧急，1-不重要不紧急
     */
    private Integer priority;
}
