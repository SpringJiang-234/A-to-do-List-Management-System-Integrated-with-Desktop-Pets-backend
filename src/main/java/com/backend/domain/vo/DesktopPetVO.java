package com.backend.domain.vo;

import com.backend.bean.base.BaseVO;
import lombok.*;

/**
 * 桌宠表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DesktopPetVO extends BaseVO {
    // 部分属性为隐私，不给管理员看

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
    private Integer level;
}