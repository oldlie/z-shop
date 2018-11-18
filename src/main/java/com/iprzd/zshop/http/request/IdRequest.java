package com.iprzd.zshop.http.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class IdRequest implements Serializable {
    private final static long serialVersionUID = 1542112235256L;
    private long id;
}
