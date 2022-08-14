package by.tms.practice20.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class User {
    @NotEmpty
    @NotBlank
    private String name;
    @NotEmpty
    @NotBlank
    private String  username;
    @NotEmpty
    @NotBlank
    private String password;
    private LocalDate registered;
}
