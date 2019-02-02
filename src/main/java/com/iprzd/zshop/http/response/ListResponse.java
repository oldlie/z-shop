package com.iprzd.zshop.http.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResponse<T> extends BaseResponse {
    private List<T> list;
}
