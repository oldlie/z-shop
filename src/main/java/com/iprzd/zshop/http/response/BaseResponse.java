package com.iprzd.zshop.http.response;

import java.io.Serializable;

public class BaseResponse implements Serializable {
    private int status = 0;
    private String message = "SUCCESS";

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
