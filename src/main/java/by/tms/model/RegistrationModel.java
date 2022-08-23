package by.tms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //генерирует конструктор с одним параметром для каждого поля в классе
@NoArgsConstructor //генерирует конструктор без параметров
public class RegistrationModel {
    private String name;
    private String username;
    private String password;
    private String street;
    private String city;
    private String street2;
    private String city2;
    private String role1;
    private String role2;

}
