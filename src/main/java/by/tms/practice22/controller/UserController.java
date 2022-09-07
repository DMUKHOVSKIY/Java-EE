package by.tms.practice22.controller;

import by.tms.practice22.dao.InMemoryUserDao;
import by.tms.practice22.entity.User;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
//@Api(tags = {"Users"}) //так можем настроить вид контроллера в документации (сделать красивее). По умолчанию
//контроллер называется как и класс, а так будет называться "Users"
public class UserController {
    @Autowired
    private InMemoryUserDao inMemoryUserDao;

    @PostMapping//? говорит, что может возвращаться любой тип
//    @ApiOperation(value = "Save User")//меняем название метода
//    @ApiResponse() TODO
    public ResponseEntity<User> save(@Valid @RequestBody User user, BindingResult bindingResult) { //так мы можем сказать, чтобы Jackson принял JSON и из него сделал user-а. Эта аннотация (@RequestBody) говорит, что в теле запроса пришел JSON и из него надо создать user-а
        User save = inMemoryUserDao.save(user);
        return new ResponseEntity<>(save, HttpStatus.CREATED); //не совсем правильно возвращать user-а. Вдруг мы захотим еще запихнуть сюда что-то в header поэтому надо использовать обертку ResponseEntity (в нее можно завернуть что угодно)
//        HttpStatus.CREATED - это уже не 200, а 201; body - сущность, которая будет преобразована в JSON
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> all = inMemoryUserDao.findAll();
        if (all.isEmpty()) {
            return ResponseEntity.noContent().build(); //204 - все хорошо, только нет user-ов в БД
        }
        return ResponseEntity.ok(all);

//        return all; //Этот лист отдается не сразу, он будет обработан Jackson-ом и вернется JSON
        //Мы работаем с томкэтом, так как он работает с HTTP, мы можем отправлять json либо html - все равно и то и то текст, только разные форматы
        //Больше нет понятия MVC
        //Теперь у нашей программы на java стало намного больше клиентов
        //Выходит нам фронтенд пишет свое приложение и оно отправляет HTTP запрос на наш API, а мы отвечаем со своего
        //бэкэнда им JSON-ом
        //API - бэкэнд приложение, которое отвечает JSON-ом на запрос
    }

    @GetMapping("/id")
    public ResponseEntity<User> findById(long id, HttpServletResponse response) throws IOException {
        Optional<User> byId = inMemoryUserDao.findById(id);
        if(id==555){
            throw new RuntimeException("Bad ID"); //будет выводить вместо стэк трэйса с ненужной иноформацией "Bad ID", благодаря нашему классу ExController
        }
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
//        response.sendRedirect("/user/username?username=test2"); //пример 3ХХ кода
        return ResponseEntity.badRequest().build();
//        return ResponseEntity.notFound().build(); //с таким id - не было найдено. Ошибка 404
//        return ResponseEntity.notContent().build();
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
        if (update.isPresent()) {
            return update.get();
        }
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody User user) { //Возвращать мы будем только код. Никаких данных в body у нас не будет. Мы не знаем что там будет <?>
        if (inMemoryUserDao.delete(user)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
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
