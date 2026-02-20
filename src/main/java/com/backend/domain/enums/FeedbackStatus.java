package com.backend.domain.enums;

import com.backend.bean.base.BaseEnum;
import com.backend.utils.EnumUtil;
import lombok.Getter;

/**
 * 处理状态1-未受理 2-已受理 3-已解决
 */
@Getter
public enum FeedbackStatus implements BaseEnum {
    PENDING(1, "未受理"),
    PROCESSING(2, "已受理"),
    RESOLVED(3, "已解决");

    private final Integer code;
    private final String text;

    FeedbackStatus(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    /**
     * 根据code获取对应的枚举值
     * @param code 状态码
     * @return 对应的枚举值，如果未找到返回null
     */
    public static String getTextByCode(Integer code) {
        return EnumUtil.getTextByCode(UserGender.class, code);
    }

    /**
     * 根据text获取对应的枚举值
     * @param text 状态码
     * @return 对应的枚举值，如果未找到返回null
     */
    public static Integer getCodeByText(String text) {
        return EnumUtil.getCodeByText(UserGender.class, text);
    }

}
