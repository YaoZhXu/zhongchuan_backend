package com.shu.backend.vo.response;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

@Data
public class PageInfo<T> {

    private int pageNo;

    private int pageSize;

    private long totalPages;

    private long totalCount;

    private List<T> records;

    public void fill(Page page) {
        this.setPageNo((int) page.getCurrent());
        this.setPageSize((int) page.getSize());
        this.setTotalPages(page.getPages());
        this.setTotalCount(page.getTotal());
    }
}
