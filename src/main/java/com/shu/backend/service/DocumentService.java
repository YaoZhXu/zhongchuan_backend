package com.shu.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.backend.po.Document;

public interface DocumentService extends IService<Document> {

    Page<Document> list(int pageNo, int pageSize, Long corpusId, String corpusName);

    Long add(Long corpusId, String docName, String path);

    boolean delete(Long docId);
}
