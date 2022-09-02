package by.tms.practice22.controller;

import by.tms.practice22.dao.InMemoryUserDao;
import by.tms.practice22.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private InMemoryUserDao inMemoryUserDao;

    @PostMapping
    public User save(/*@Valid - тоже можно*/ @RequestBody User user) { //так мы можем сказать, чтобы Jackson принял JSON и из него сделал user-а. Эта аннотация (@RequestBody) говорит, что в теле запроса пришел JSON и из него надо создать user-а
        User save = inMemoryUserDao.save(user);
        return save;
    }

    @GetMapping
    public List<User> findAll() {
        List<User> all = inMemoryUserDao.findAll();
        return all; //Этот лист отдается не сразу, он будет обработан Jackson-ом и вернется JSON
        //Мы работаем с томкэтом, так как он работает с HTTP, мы можем отправлять json либо html - все равно и то и то текст, только разные форматы
        //Больше нет понятия MVC
        //Теперь у нашей программы на java стало намного больше клиентов
        //Выходит нам фронтенд пишет свое приложение и оно отправляет HTTP запрос на наш API, а мы отвечаем со своего
        //бэкэнда им JSON-ом
        //API - бэкэнд приложение, которое отвечает JSON-ом на запрос
    }

    @GetMapping("/id")
    public User findById(long id) {
        Optional<User> byId = inMemoryUserDao.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }

    @GetMapping("/username")
    public User findByUsername(String username) {
        Optional<User> byUsername = inMemoryUserDao.getByUsername(username);
        if (byUsername.isPresent()) {
            return byUsername.get();
        }
        return null;
    }

    @PutMapping
    public User update(@RequestBody User user) {
        Optional<User> update = inMemoryUserDao.update(user);
        if(update.isPresent()){
            return update.get();
        }
        return null;
    }

    @DeleteMapping
    public void delete(@RequestBody User user){
        inMemoryUserDao.delete(user);
    }


    @DeleteMapping("/id")
    public User deleteById(long id) {
        Optional<User> user = inMemoryUserDao.deleteById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }
}
