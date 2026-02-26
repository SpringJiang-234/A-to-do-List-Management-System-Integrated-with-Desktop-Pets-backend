package com.backend.domain.vo;

import com.backend.bean.base.BaseVO;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 反馈表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackVO extends BaseVO {
    /**
    * 用户id
    */
    private Long userId;

    /**
    * 反馈标题
    */
    private String title;

    /**
    * 反馈内容
    */
    private String content;

    /**
    * 处理状态1-未受理 2-已受理 3-已解决
    */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}