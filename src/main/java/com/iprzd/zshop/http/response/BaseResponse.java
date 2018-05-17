package com.iprzd.zshop.http.response;

public class BaseResponse {
    private int status;
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
