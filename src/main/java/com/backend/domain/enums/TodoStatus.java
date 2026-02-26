package com.backend.domain.enums;

import com.backend.bean.base.BaseEnum;
import com.backend.utils.EnumUtil;
import lombok.Getter;

/**
 * 状态：1-未完成 2-完成 3-放弃
 */
@Getter
public enum TodoStatus implements BaseEnum {
    PENDING(1, "未完成"),
    COMPLETED(2, "完成"),
    ABANDONED(3, "放弃");

    private final Integer code;
    private final String text;

    TodoStatus(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    /**
     * 根据code获取对应的枚举值
     * @param code 状态码
     * @return 对应的枚举值，如果未找到返回null
     */
    public static String getTextByCode(Integer code) {
        return EnumUtil.getTextByCode(TodoStatus.class, code);
    }

    /**
     * 根据text获取对应的枚举值
     * @param text 状态文本
     * @return 对应的枚举值，如果未找到返回null
     */
    public static Integer getCodeByText(String text) {
        return EnumUtil.getCodeByText(TodoStatus.class, text);
    }
}
