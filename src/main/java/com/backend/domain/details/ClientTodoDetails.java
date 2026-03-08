package com.backend.domain.details;

import com.backend.bean.base.BaseDetails;
import lombok.*;

import java.util.List;

/**
 * 客户端待办事项详情
 */
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ClientTodoDetails extends BaseDetails {
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
    * 开始时间
    */
    private String startTime;

    /**
    * 截止时间(结束时间)
    */
    private String endTime;

    /**
    * 状态：1-未完成 2-完成 3-放弃
    */
    private String status;

    /**
    * 是否置顶：1-未置顶 2-置顶
    */
    private String isTop;

    /**
    * 分类名称
    */
    private String categoryName;

    /**
     * 优先级：4-重要且紧急，3-重要不紧急，2-不重要但紧急，1-不重要不紧急
     */
    private Integer priority;

    /**
    * 标签列表
     */
    private List<TagDetails> tags;
}
