package AdapterPattern;

public class User {
    private String name;
    private String username;
    private String password;

    public static class Builder{
        private final User user;

        public Builder(){
            user = new User();
        }

        public Builder name(String name){
            user.name = name;
            return this;
        }

        public Builder username(String username){
            user.username= username;
            return this;
        }

        public Builder password(String password){
            user.password = password;
            return this;
        }

        public User build(){
            return user;
        }

    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
