package com.shu.backend.vo.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CommonResponse<T> {

    private int code;

    private String msg;

    private T data;

    public CommonResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>(HttpStatus.OK.value(), "操作成功");
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(HttpStatus.OK.value(), "操作成功", data);
    }

    public static <T> CommonResponse<T> fail(int code, String message) {
        return new CommonResponse<>(code, message);
    }
}
