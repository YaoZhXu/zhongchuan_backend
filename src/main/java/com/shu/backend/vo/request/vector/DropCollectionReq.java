package com.shu.backend.vo.request.vector;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DropCollectionReq {

    @NotBlank(message = "collectionName不能为空")
    private String collectionName;
}
