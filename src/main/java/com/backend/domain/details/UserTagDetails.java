package com.backend.domain.details;

import com.backend.bean.base.BaseDetails;
import lombok.*;

/**
 * 用户标签关联表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserTagDetails extends BaseDetails {
    /**
    * 创建时间
    */
    private String createTime;

    /**
    * 修改时间
    */
    private String updateTime;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 标签id
    */
    private Long tagId;
}