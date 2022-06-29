package com.haneefs.springbootpoc.constants;

public enum AppUserPermission {

    CUSTOMER_ADD("CUSTOMER:ADD"),
    CUSTOMER_MODIFY("CUSTOMER:MODIFY"),
    CUSTOMER_READ("CUSTOMER:READ"),

    PRODUCT_ADD("PRODUCT:ADD"),
    PRODUCT_MODIFY("PRODUCT:MODIFY"),
    PRODUCT_READ("PRODUCT:READ");


    private final String permission;
    AppUserPermission(String permission){
        this.permission = permission;
    }
    public String getPermission(){
        return permission;
    }
}
