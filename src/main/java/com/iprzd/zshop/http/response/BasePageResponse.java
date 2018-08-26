package com.iprzd.zshop.http.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePageResponse extends BaseResponse {
    private long total;
    private int pages;
}
