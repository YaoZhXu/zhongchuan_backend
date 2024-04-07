package com.shu.backend.vo.request.doc;

import com.shu.backend.vo.request.PageCommonReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageListAllDocReq extends PageCommonReq {

    private String docName;
}
