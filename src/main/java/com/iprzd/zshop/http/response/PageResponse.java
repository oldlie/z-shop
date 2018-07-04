package com.iprzd.zshop.http.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResponse extends BaseResponse {
    private long total;
    private int pages;
}
