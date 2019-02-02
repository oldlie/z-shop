package com.iprzd.zshop.http.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResponse<T> extends ListResponse<T> {
    private long total;
    private int pages;
}
