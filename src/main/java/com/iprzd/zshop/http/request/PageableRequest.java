package com.iprzd.zshop.http.request;

import java.io.Serializable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class PageableRequest<T> implements Serializable {

    private final static long serialVersionUID = 1540280257493L;

    public PageableRequest() {}

    public PageableRequest(T entity, int page, int size) {
        this.entity = entity;
        this.page = page;
        this.size = size;
    }

    public PageableRequest(T entity, int page, int size, String orderBy) {
        this.entity = entity;
        this.page = page;
        this.size = size;
        this.orderBy = orderBy;
        this.order = "asc";
    }

    public PageableRequest(T entity, int page, int size, String orderBy, String order) {
        this.entity = entity;
        this.page = page;
        this.size = size;
        this.orderBy = orderBy;
        this.order = order;
    }

    @Getter
    @Setter
    private T entity;
    @Setter
    private int page;
    @Setter
    private int size;
    @Setter
    private String orderBy;
    @Setter
    private String order;

    public Pageable pageable() {
        if (order != null && order != "") {
            return PageRequest.of(page, size, order.toLowerCase() == "desc" ? Direction.DESC : Direction.ASC,
            orderBy);
        } else {
            return PageRequest.of(page, size);
        }
    }
}