package com.iprzd.zshop.http.response;

import com.iprzd.zshop.entity.home.Carousel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CarouselListResponse extends BaseResponse {

    private List<Carousel> list;
}
