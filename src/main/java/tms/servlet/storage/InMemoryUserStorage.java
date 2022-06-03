package tms.servlet.storage;

import tms.servlet.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUserStorage {

    private final static List<User> userList = new ArrayList<>();

    public void save(User user) {
        userList.add(user);
    }

    public Optional<User> findByUserName(String username){
        for(User user: userList){
            if(user.getUsername().equals(username)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
