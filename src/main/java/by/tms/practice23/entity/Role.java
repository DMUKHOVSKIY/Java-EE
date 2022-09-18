package by.tms.practice23.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name(); //Возвращаем стринговое представление каждой роли
    }
}
