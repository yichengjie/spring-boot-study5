package com.yicj.study.mvc.hello.util;

import lombok.Data;

public class UserContextHolder {

    private static ThreadLocal<UserContext> threadLocal = ThreadLocal.withInitial(UserContext::new) ;

    public static void setUserName(String userName){
        threadLocal.get().setUsername(userName);
    }

    public static void setToken(String token){
        threadLocal.get().setToken(token);
    }

    public static void setXToken(String xtoken){
        threadLocal.get().setXtoken(xtoken);
    }

    public static String getUserName(){
        return threadLocal.get().getUsername() ;
    }
    public static String getToken(){
        return threadLocal.get().getToken() ;
    }
    public static String getXToken(){
        return threadLocal.get().getXtoken() ;
    }

    @Data
    static class UserContext{
        private String username ;
        private String token ;
        private String xtoken ;
    }
}
