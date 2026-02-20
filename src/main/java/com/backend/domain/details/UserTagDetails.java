package com.backend.domain.details;

import com.backend.bean.base.BaseEntity;
import lombok.*;

/**
 * 用户标签关联表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserTagDetails extends BaseEntity {
    /**
    * 用户id
    */
    private Long userId;

    /**
    * 标签id
    */
    private Long tagId;
}