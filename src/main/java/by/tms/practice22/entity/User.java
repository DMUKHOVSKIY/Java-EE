package by.tms.practice22.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
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
    @OneToMany(cascade = CascadeType.ALL) //нам нет смысла писать addressRepository так как каскад помогает все делать вместе с user-ом
    private List<Address> address;
}
