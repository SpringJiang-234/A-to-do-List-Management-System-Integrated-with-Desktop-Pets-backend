package com.backend.constant;

public interface Constant {
    /**
     * 每页显示的记录数
     */
    Integer PAGE_SIZE = 16;

    /**
     * 未分类的类别ID
     */
    Long UNCLASSIFIED_CATEGORY_ID = 1L;

    /**
     * 桌宠数值上限
     */
    Integer DESKTOP_PET_MAX_VALUE = 100;

    /**
     * 桌宠活力值增加量（新建/完成待办）
     */
    Integer DESKTOP_PET_ENERGY_INCREASE = 5;

    /**
     * 桌宠成长值增加量（完成待办）
     */
    Integer DESKTOP_PET_EXP_INCREASE = 10;

    /**
     * 桌宠成长值升级阈值
     */
    Integer DESKTOP_PET_EXP_LEVEL_UP = 100;

    /**
     * 桌宠心情值按时完成增加量
     */
    Integer DESKTOP_PET_MOOD_ON_TIME_INCREASE = 5;

    /**
     * 桌宠心情值逾期减少量
     */
    Integer DESKTOP_PET_MOOD_OVERDUE_DECREASE = 3;

    /**
     * 桌宠心情值默认值
     */
    Integer DESKTOP_PET_MOOD_DEFAULT = 60;

    /**
     * 桌宠亲密度登录增加量
     */
    Integer DESKTOP_PET_INTIMACY_INCREASE = 2;

    /**
     * 桌宠连续登录天数初始值
     */
    Integer DESKTOP_PET_CONSECUTIVE_DAYS_INIT = 1;
}
