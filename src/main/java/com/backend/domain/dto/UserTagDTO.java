package com.backend.domain.dto;

import lombok.*;

/**
 * 用户标签关联表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserTagDTO {
    /**
     * 主键
     */
    private Long id;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 标签id
    */
    private Long tagId;
}