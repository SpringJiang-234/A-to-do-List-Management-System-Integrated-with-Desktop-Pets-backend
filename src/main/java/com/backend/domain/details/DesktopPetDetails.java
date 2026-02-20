package com.backend.domain.details;

import com.backend.bean.base.BaseEntity;
import lombok.*;

/**
 * 桌宠表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DesktopPetDetails extends BaseEntity {
    /**
    * 用户id
    */
    private Long userId;

    /**
    * 昵称
    */
    private String nickname;

    /**
    * 活力值（养成数据）
    */
    private Integer energy;

    /**
    * 心情值（养成数据）
    */
    private Integer mood;

    /**
    * 亲密度（养成数据）
    */
    private Integer intimacy;

    /**
    * 成长值
    */
    private Integer exp;

    /**
    * 成长等级
    */
    private Integer level;
}