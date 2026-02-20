package com.backend.domain.vo;

import com.backend.bean.base.BaseVO;
import lombok.*;

/**
 * 用户标签关联表：这个关联表应该不会出现在管理后台VO里
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserTagVO extends BaseVO {
    /**
    * 用户id
    */
    private Long userId;

    /**
    * 标签id
    */
    private Long tagId;
}