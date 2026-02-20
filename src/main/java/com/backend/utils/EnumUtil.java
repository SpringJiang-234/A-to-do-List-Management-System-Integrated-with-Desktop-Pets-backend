package com.backend.utils;

import com.backend.bean.base.BaseEnum;
import java.util.Arrays;

public class EnumUtil {

    /**
     * 根据code获取枚举的文本描述
     * @param clazz 枚举类class
     * @param code code值
     * @param <E> 实现BaseEnum接口的枚举类型
     * @return 文本描述
     */
    public static <E extends Enum<E> & BaseEnum> String getTextByCode(Class<E> clazz, Integer code) {
        if (code == null) {
            return null;
        }

        return Arrays.stream(clazz.getEnumConstants())
                .filter(item -> code.equals(item.getCode()))
                .map(BaseEnum::getText)
                .findFirst()
                .orElse(null);
    }

    /**
     * 根据text获取对应的枚举值
     * @param text 状态码
     * @return 对应的枚举值，如果未找到返回null
     */
    public static <E extends Enum<E> & BaseEnum> Integer getCodeByText(Class<E> clazz, String text) {
        if (text == null) {
            return null;
        }

        return Arrays.stream(clazz.getEnumConstants())
                .filter(item -> text.equals(item.getText()))
                .map(BaseEnum::getCode)
                .findFirst()
                .orElse(null);
    }

}
