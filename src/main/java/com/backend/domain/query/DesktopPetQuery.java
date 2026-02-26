package com.backend.domain.query;

import com.backend.bean.IntegerRange;
import com.backend.bean.LocalDateTimeRange;
import com.backend.bean.base.BaseQuery;
import lombok.*;

/**
 * 桌宠表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DesktopPetQuery extends BaseQuery {
    /**
    * 用户id
    */
    private Long userId;

    /**
    * 昵称
    */
    private String nickname;

    /**
    * 成长等级
    */
    private IntegerRange level;

    /**
    * 创建时间
    */
    private LocalDateTimeRange createTime;

    /**
    * 更新时间
    */
    private LocalDateTimeRange updateTime;
}