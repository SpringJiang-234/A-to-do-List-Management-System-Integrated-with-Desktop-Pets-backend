package com.backend.domain.enums;

import com.backend.bean.base.BaseEnum;
import com.backend.utils.EnumUtil;
import lombok.Getter;

/**
 * 是否已删除：1-未删除 2-已删除
 */
@Getter
public enum TodoIsDelete implements BaseEnum {
    NOT_DELETED(1, "未删除"),
    DELETED(2, "已删除");

    private final Integer code;
    private final String text;

    TodoIsDelete(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    /**
     * 根据code获取对应的枚举值
     * @param code 状态码
     * @return 对应的枚举值，如果未找到返回null
     */
    public static String getTextByCode(Integer code) {
        return EnumUtil.getTextByCode(TodoIsDelete.class, code);
    }

    /**
     * 根据text获取对应的枚举值
     * @param text 状态文本
     * @return 对应的枚举值，如果未找到返回null
     */
    public static Integer getCodeByText(String text) {
        return EnumUtil.getCodeByText(TodoIsDelete.class, text);
    }
}
