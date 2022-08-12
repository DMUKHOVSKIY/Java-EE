package by.tms.practice19.service;

import by.tms.practice19.dao.InMemoryUserDao;
import by.tms.practice19.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService { //Сервис - это такая прослойка между контроллером и хранилищем, где мы можем инжектить
    // дополнительные операции. Тут пишем основную бизнес логику
    //Никаких prepare statements, connection в этом классе быть не должно
    @Autowired
    private InMemoryUserDao userDao;

    public void save(User user){
        user.setRegistered(LocalDate.now());
        userDao.save(user);
    }

    public Optional<User> findByUsername(String username){
       //Делаем этот проброс для того, что если добавится какая-то функциональность, то ее можно будет дописать здесь. Например обработку ошибок, если не нашли юзера
        return userDao.findByUsername(username);
    }

}
