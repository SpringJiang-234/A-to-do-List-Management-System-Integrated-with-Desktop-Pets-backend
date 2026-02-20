package com.backend.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResultBean<T> {
    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public static <T> ResultBean<T> success(T data) {
        return new ResultBean<>(200, "success", data);
    }

    public static <T> ResultBean<T> success(String msg, T data) {
        return new ResultBean<>(200, msg, data);
    }

    public static <T> ResultBean<T> error(T data) {
        return new ResultBean<>(400, "error", data);
    }

    public static <T> ResultBean<T> error(String msg, T data) {
        return new ResultBean<>(400, msg, data);
    }

    public static <T> ResultBean<T> error(Integer code, String msg, T data) {
        return new ResultBean<>(code, msg, data);
    }
}
