package by.tms.practice24.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority { //GrantedAuthority - это коллекция ролей
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
