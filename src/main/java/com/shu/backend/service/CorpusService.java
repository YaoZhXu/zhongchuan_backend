package com.shu.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.backend.po.Corpus;

public interface CorpusService extends IService<Corpus> {

    Page<Corpus> list(int pageNo, int pageSize);

    Long insert(String corpusName, String corpusDesc);

    boolean edit(Long corpusId, String corpusName, String corpusDesc);

    Corpus queryById(Long corpusId);

    boolean delete(Long corpusId);
}
