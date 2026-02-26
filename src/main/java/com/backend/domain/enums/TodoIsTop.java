package com.backend.domain.enums;

import com.backend.bean.base.BaseEnum;
import com.backend.utils.EnumUtil;
import lombok.Getter;

/**
 * 是否置顶：1-未置顶 2-置顶
 */
@Getter public enum TodoIsTop implements BaseEnum {
    NOT_TOP(1, "未置顶"),
    TOPPED(2, "置顶");

    private final Integer code;
    private final String text;

    TodoIsTop(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    /**
     * 根据code获取对应的枚举值
     * @param code 状态码
     * @return 对应的枚举值，如果未找到返回null
     */
    public static String getTextByCode(Integer code) {
        return EnumUtil.getTextByCode(TodoIsTop.class, code);
    }

    /**
     * 根据text获取对应的枚举值
     * @param text 状态文本
     * @return 对应的枚举值，如果未找到返回null
     */
    public static Integer getCodeByText(String text) {
        return EnumUtil.getCodeByText(TodoIsTop.class, text);
    }
}
