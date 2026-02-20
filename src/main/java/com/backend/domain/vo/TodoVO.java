package com.backend.domain.vo;

import com.backend.bean.base.BaseVO;
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
public class TodoVO extends BaseVO {
    // 部分属性为隐私，不给管理员看

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 开始时间
    */
    private LocalDateTime startTime;

    /**
    * 截止时间(结束时间)
    */
    private LocalDateTime endTime;

    /**
    * 状态：1-未完成 2-完成 3-放弃
    */
    private String status;

}