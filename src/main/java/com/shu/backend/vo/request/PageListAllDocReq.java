package com.shu.backend.vo.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageListAllDocReq extends PageCommonReq {

    private String docName;
}
