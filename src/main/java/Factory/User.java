package Factory;

public class User {
    private String name;
    private String username;
    private String password;

    public User(Role role) {
        this.role = role;
    }

    private Role role;

    //Мы не можем поменять того, что сделала фабрика(сеттеры и геттеры не нужны)
    /*public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", role'" + role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
