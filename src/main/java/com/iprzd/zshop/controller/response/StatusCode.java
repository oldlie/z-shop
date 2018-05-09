package com.iprzd.zshop.controller.response;

import java.util.HashMap;

public class StatusCode {

    private static HashMap<Integer, String> map = new HashMap<>();

    public static final int SUCCESS = 0;
    public static final int LOGIN_FAILED = 1;
    public static final int SAVE_COMMODITY_MENU_FAILED = 2;
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
}