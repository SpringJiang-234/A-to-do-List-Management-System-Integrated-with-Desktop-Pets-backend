package com.backend.domain.dto;

import lombok.*;

import java.time.LocalDate;

/**
 * 桌宠表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DesktopPetDTO {
    /**
     * 主键
     */
    private Long id;

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
    private LocalDate lastLoginDate;

    /**
    * 活力值最后重置日期
    */
    private LocalDate lastEnergyResetDate;

    /**
    * 心情值最后重置日期
    */
    private LocalDate lastMoodResetDate;

    /**
    * 连续登录天数
    */
    private Integer consecutiveDays;
}