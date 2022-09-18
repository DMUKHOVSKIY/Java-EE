package by.tms.practice24.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    @NotBlank
    private String name;
    @NotEmpty
    @NotBlank
    private String username;
    @NotEmpty
    @NotBlank
    private String password;
    @NotNull //можем просто в телефоне навешать аннотаций
    @OneToOne(cascade = CascadeType.ALL)//1 user - 1 telephone
    private Telephone telephone;
    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    //нам нет смысла писать addressRepository так как каскад помогает все делать вместе с user-ом
    private List<Address> address;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

//    @Enumerated(EnumType.STRING)
//    @ElementCollection(fetch = FetchType.EAGER)
//    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null /*roles*/;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired; //срок Действия Учетной Записи Не Истек
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked; //Учетная Запись Не Заблокирована
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired; //срок Действия Учетных Данных Не Истек
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled; //включен
    }
}
