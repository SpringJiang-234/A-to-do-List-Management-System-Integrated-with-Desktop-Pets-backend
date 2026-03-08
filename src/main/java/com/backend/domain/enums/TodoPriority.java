package com.backend.domain.enums;

import com.backend.bean.base.BaseEnum;
import com.backend.utils.EnumUtil;
import lombok.Getter;

/**
 * 优先级：4-重要且紧急，3-重要不紧急，2-不重要但紧急，1-不重要不紧急，默认为不重要不紧急
 */
@Getter
public enum TodoPriority implements BaseEnum {
    NOT_IMPORTANT_NOT_URGENT(1, "不重要不紧急"),
    NOT_IMPORTANT_URGENT(2, "不重要但紧急"),
    IMPORTANT_NOT_URGENT(3, "重要不紧急"),
    IMPORTANT_URGENT(4, "重要且紧急");

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
        return EnumUtil.getTextByCode(TodoPriority.class, code);
    }

    /**
     * 根据text获取对应的枚举值
     * @param text 状态文本
     * @return 对应的枚举值，如果未找到返回null
     */
    public static Integer getCodeByText(String text) {
        return EnumUtil.getCodeByText(TodoPriority.class, text);
    }
}
