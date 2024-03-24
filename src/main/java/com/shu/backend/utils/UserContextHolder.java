package com.shu.backend.utils;

public class UserContextHolder {

    private static final ThreadLocal<UserInfoContext> userContext = new ThreadLocal<>();

    public static void setUserInfo(UserInfoContext userInfo) {
        userContext.set(userInfo);
    }

    public static UserInfoContext getUserInfo() {
        return userContext.get();
    }

    public static void clear() {
        userContext.remove();
    }
}
