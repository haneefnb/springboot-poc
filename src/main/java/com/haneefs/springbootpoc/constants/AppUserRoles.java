package com.haneefs.springbootpoc.constants;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.haneefs.springbootpoc.constants.AppUserPermission.*;

public enum AppUserRoles {
    CUSTOMER(Sets.newHashSet(CUSTOMER_ADD,CUSTOMER_MODIFY,CUSTOMER_READ)),
    EMPLOYEE(Sets.newHashSet(CUSTOMER_READ,CUSTOMER_MODIFY,PRODUCT_READ)),
    ADMIN(Sets.newHashSet(CUSTOMER_ADD,CUSTOMER_READ,PRODUCT_ADD,PRODUCT_READ));

    private final Set<AppUserPermission> permissions;

    AppUserRoles(Set<AppUserPermission> permissions){
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissionCollection = permissions.stream()
                .map((permission) -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
        permissionCollection.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissionCollection;
    }

}
