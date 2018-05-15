package com.iprzd.zshop.http;

import com.iprzd.zshop.http.response.BaseResponse;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class StatusCode {

    private static HashMap<Integer, String> map = new HashMap<>();

    public static final int SUCCESS = 0;
    public static final int LOGIN_FAILED = 1;
    public static final int SAVE_COMMODITY_MENU_FAILED = 2;
    public static final int SAVE_TAG_FAILED = 3;
    public static final int DELETE_COMMODITY_MENU_FAILED = 4;
    public static final int DELETE_COMMODITY_SPEC_FAILED = 5;
    public static final int CANNOT_FIND_COMMODITY_MENU_EXCEPTION = 2001;

    static {
        map.put(SUCCESS, "操作成功。");
        map.put(LOGIN_FAILED, "登录操作未成功。");
        map.put(SAVE_COMMODITY_MENU_FAILED, "保存商品栏目操错未成功。");
        map.put(CANNOT_FIND_COMMODITY_MENU_EXCEPTION, "根据ID找不到商品栏目异常。");
    }

    public static String getMessage(int code) {
        return map.get(code);
    }

    public static BaseResponse successResponse(@NotNull BaseResponse response) {
        response.setStatus(SUCCESS);
        response.setMessage("操作成功。");
        return response;
    }
}
