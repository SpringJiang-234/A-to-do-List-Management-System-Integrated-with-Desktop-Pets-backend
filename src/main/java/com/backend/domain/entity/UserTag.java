package com.backend.domain.entity;

import java.util.Date;

import com.backend.bean.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户标签关联表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserTag extends BaseEntity {
    /**
    * 用户id
    */
    private Long userId;

    /**
    * 标签id
    */
    private Long tagId;
}