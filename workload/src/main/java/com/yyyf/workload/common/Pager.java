package com.yyyf.workload.common;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Objects;

@Data
public class Pager implements Serializable {
    private static final long serialVersionUID = 402439276460356073L;
    public static final int DEFAULT_PAGE_NUM = 1;
    public static final int DEFAULT_PAGE_SIZE = 20;
    private int pageSize;
    private int pageNum;
    private int count;

    public static Pager defaultPager() {
        Pager pager = new Pager();
        pager.pageNum = 1;
        pager.pageSize = 20;
        return pager;
    }

    public static Pager getOrDefaultPager(Pager pager) {
        if (Objects.isNull(pager) || Objects.isNull(pager.getPageSize()) || Objects.isNull(pager.getPageNum())) {
            return defaultPager();
        }
        if (pager.getPageSize() <= 0 || pager.getPageSize() > 100 || pager.getPageNum() <= 0 || pager.getPageNum() >= 50) {
            return defaultPager();
        }
        return pager;
    }


    public Pager newResultPager(int count) {
        Pager pager = new Pager();
        BeanUtils.copyProperties(this, pager);
        pager.setCount(count);
        return pager;
    }

    public int getOffset() {
        return (this.pageNum - 1) * this.pageSize;
    }


}
