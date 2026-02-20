package com.backend.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.backend.bean.base.BaseExcel;
import lombok.*;

/**
 * 桌宠表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DesktopPetExcel extends BaseExcel {
    /**
    * 用户id
    */
    @ExcelProperty(value = "用户id")
    private Long userId;

    /**
    * 昵称
    */
    @ExcelProperty(value = "昵称")
    private String nickname;

    /**
    * 活力值（养成数据）
    */
    @ExcelProperty(value = "活力值")
    private Integer energy;

    /**
    * 心情值（养成数据）
    */
    @ExcelProperty(value = "心情值")
    private Integer mood;

    /**
    * 亲密度（养成数据）
    */
    @ExcelProperty(value = "亲密度")
    private Integer intimacy;

    /**
    * 成长值
    */
    @ExcelProperty(value = "成长值")
    private Integer exp;

    /**
    * 成长等级
    */
    @ExcelProperty(value = "成长等级")
    private Integer level;
}