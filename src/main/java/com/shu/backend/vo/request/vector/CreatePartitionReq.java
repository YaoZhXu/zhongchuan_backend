package com.shu.backend.vo.request.vector;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreatePartitionReq {

    @NotBlank(message = "collectionName不能为空")
    private String collectionName;

    @NotBlank(message = "partitionName不能为空")
    private String partitionName;
}
