package com.backend.domain.enums;

import com.backend.bean.base.BaseEnum;
import com.backend.utils.EnumUtil;
import lombok.Getter;

/**
 * 优先级：1-非常低 2-低 3-中 4-高 默认值为非常低
 */
@Getter
public enum TodoPriority {
    VERY_LOW(1, "非常低"),
    LOW(2, "低"),
    MEDIUM(3, "中"),
    HIGH(4, "高");

    private final Integer code;
    private final String text;

    TodoPriority(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    /**
     * 根据code获取对应的枚举值
     * @param code 状态码
     * @return 对应的枚举值，如果未找到返回null
     */
    public static String getTextByCode(Integer code) {
        return EnumUtil.getTextByCode(UserStatus.class, code);
    }

    /**
     * 根据text获取对应的枚举值
     * @param text 状态文本
     * @return 对应的枚举值，如果未找到返回null
     */
    public static Integer getCodeByText(String text) {
        return EnumUtil.getCodeByText(UserStatus.class, text);
    }
}
