package com.helmes.citymanagement.vo;


import jakarta.persistence.criteria.Order;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PageInfo implements Serializable {

    private static final long serialVersionUID = -1649937693841091299L;
    private static final int PAGE_SIZE = 500;

    private int pageNumber = 0;
    private int pageSize = PAGE_SIZE;
    private List<Order> sort;

    public PageInfo(int pageNumber, int pageSize, List<Order> sort) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }

        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }

        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = Optional.ofNullable(sort).orElse(Collections.emptyList());
    }

    public PageInfo next() {
        return new PageInfo(getPageNumber() + 1, getPageSize(), getSort());
    }

    public PageInfo previous() {
        return getPageNumber() == 0 ? this : new PageInfo(getPageNumber() - 1, getPageSize(), getSort());
    }

    public PageInfo first() {
        return new PageInfo(0, getPageSize(), getSort());
    }

    public boolean hasPrevious() {
        return pageNumber > 0;
    }

    public PageInfo previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    public boolean hasSort() {
        return true;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Order> getSort() {
        return sort;
    }

    public void setSort(List<Order> sort) {
        this.sort = sort;
    }
}
