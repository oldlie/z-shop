package com.iprzd.zshop.http.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResponse<T> extends BaseResponse {
    private T item;
}
