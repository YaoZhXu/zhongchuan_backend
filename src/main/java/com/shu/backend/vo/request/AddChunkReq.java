package com.shu.backend.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddChunkReq {

    @NotNull(message = "corpusId不能为空")
    private Long corpusId;

    @NotNull(message = "docId不能为空")
    private Long docId;

    @NotBlank(message = "title不能为空")
    private String title;

    @NotBlank(message = "content不能为空")
    private String content;

    @NotNull(message = "pagination不能为空")
    private Long pagination;

    private String imageUrl;
}
