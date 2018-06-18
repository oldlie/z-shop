package com.iprzd.zshop.http.response;

import com.iprzd.zshop.entity.UploadFile;

import java.util.List;

public class FileResponse extends BaseResponse {
   private List<UploadFile> list;

    public List<UploadFile> getList() {
        return list;
    }

    public void setList(List<UploadFile> list) {
        this.list = list;
    }
}
