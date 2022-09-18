package by.tms.practice23.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING) //чтобы enum был в качестве String
    @ElementCollection(fetch = FetchType.EAGER) //Аннотация нужна чтобы просто сохранялось.
    // Так как при запросе user-а нам надо достать и его roles из БД, то мы ставим жадный фетч-тайп
    private Set<Role> roles = new HashSet<>();//пользователь может иметь несколько ролей

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {  //все методы мы хардкодим. Поля потом можно добавить
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {  //учетные данные не просрочены
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
