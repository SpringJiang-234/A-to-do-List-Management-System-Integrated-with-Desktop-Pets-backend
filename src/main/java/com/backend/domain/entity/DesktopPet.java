package com.backend.domain.entity;

import java.time.LocalDateTime;

import com.backend.bean.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 桌宠表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DesktopPet extends BaseEntity {
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

    /**
    * 最后登录日期
    */
    private LocalDateTime lastLoginDate;

    /**
    * 活力值最后重置日期
    */
    private LocalDateTime lastEnergyResetDate;

    /**
    * 心情值最后重置日期
    */
    private LocalDateTime lastMoodResetDate;

    /**
    * 连续登录天数
    */
    private Integer consecutiveDays;
}