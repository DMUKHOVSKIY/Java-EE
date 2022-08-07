package by.tms.service;

import by.tms.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service //расширяет аннотацию компонент
public class UserService {
    private final List<User> users = new ArrayList();

    public void save(User user) {
        users.add(user);
    }
}
