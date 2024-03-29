package com.shu.backend.vo.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CommonPageResponse<T> {

    private int code;

    private String msg;

    private PageInfo<T> data;

    public CommonPageResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonPageResponse(int code, String msg, PageInfo<T> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> CommonPageResponse<T> success(PageInfo<T> data) {
        return new CommonPageResponse<>(HttpStatus.OK.value(), "操作成功", data);
    }

    public static <T> CommonPageResponse<T> fail(int code, String msg) {
        return new CommonPageResponse<>(code, msg);
    }
}
