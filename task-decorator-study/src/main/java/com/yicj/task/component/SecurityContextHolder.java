package com.yicj.task.component;

public class SecurityContextHolder {

    private static final ThreadLocal<SecurityContext> local = new ThreadLocal<>() ;

    public static SecurityContext getContext() {
        return local.get();
    }

    public static void setContext(SecurityContext securityContext) {
        local.set(securityContext);
    }

    public static void clearContext() {
        local.remove();
    }
}
