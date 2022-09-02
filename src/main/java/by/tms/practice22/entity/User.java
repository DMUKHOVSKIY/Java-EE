package by.tms.practice22.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {
    private long id;
    private String name;
    private String username;
    private String password;
    private Telephone telephone;
    private List<Address> address;
}
