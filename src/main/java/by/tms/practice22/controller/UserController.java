package by.tms.practice22.controller;

import by.tms.practice22.dao.InMemoryUserDao;
import by.tms.practice22.dao.UserRepository;
import by.tms.practice22.entity.User;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@Tag(name = "User", description = "User Description")
public class UserController {
    @Autowired
    private InMemoryUserDao inMemoryUserDao;

    @Autowired
    private UserRepository userRepository;//инжектим
    //Spring не говорит, что этой реализации не написано, хотя там нет аннотации "@Repository"
    //Так как внутри Spring Data JPA есть имплементация для JPA репозитория - Simple JPA Repository(он прописан внутри
    //в модуле и работает из коробки), а значит и для любого нашего интерфейса


    @PostMapping//? говорит, что может возвращаться любой тип
//    @ApiOperation(value = "Save User")//меняем название метода
//    @ApiResponse()
    @Operation(summary = "Create new user", responses = {
            @ApiResponse(description = "User created", responseCode = "CREATED"),
            @ApiResponse(description = "User is invalid", responseCode = "BAD_REQUEST") //может быть несколько разных ответа
    })
    public ResponseEntity<User> save(@RequestBody @Valid User user) { //так мы можем сказать, чтобы Jackson принял JSON и из него сделал user-а. Эта аннотация (@RequestBody) говорит, что в теле запроса пришел JSON и из него надо создать user-а
        User save = userRepository.save(user);// у userRepository есть методы из интерфейса JpaRepository + возвращается user с сгенерированным id
        return new ResponseEntity<>(save, HttpStatus.CREATED); //не совсем правильно возвращать user-а. Вдруг мы захотим еще запихнуть сюда что-то в header поэтому надо использовать обертку ResponseEntity (в нее можно завернуть что угодно)
//        HttpStatus.CREATED - это уже не 200, а 201; body - сущность, которая будет преобразована в JSON
    }
    @GetMapping("/findAllByName")
    public ResponseEntity<List<User>> findAllByName(String name){
        List<User> name1 = userRepository.findAllByName(name, Sort.by(Sort.Direction.DESC, "name"));//находим по имени но свойство сортировки можем поменять - поэтому надо указать по чему сортируем "name"
        return ResponseEntity.ok(name1);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(/*int page*/) {
        userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));//сортировка по id по убыванию
//        Page<User> all1 = userRepository.findAll(Pageable.ofSize(10)); просто установили лимит на 10 пользователей
//        List<User> content = all1.getContent
        Page<User> all = userRepository.findAll(PageRequest.of(0 /*page*/, 10));//Врубаем пагинацию. Чтобы переходить через страницы нужно менять(1,2,3,4...); 10 - количество какой-то информации на страницы. Выдается Page, а не List чтобы мы легко могли посмотреть какие элементы, страницы (через методы)
        if (all.isEmpty()) {
            return ResponseEntity.noContent().build(); //204 - все хорошо, только нет user-ов в БД
        }
        return ResponseEntity.ok(all.getContent());

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
        Optional<User> byId = userRepository.findById(id);
        if (id == 555) {
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
        Optional<User> byUsername = userRepository.findByUsername(username, Pageable.ofSize(10)); //username относится к определенному св-ву какого-то определенного домена, поэтому из коробки этого метода нет
        if (byUsername.isPresent()) {
            return byUsername.get();
        }
        return null;
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody @Valid User user) {
        User save = userRepository.save(user);//Save() работает по такому же принципу, как и save() в Hibernate. То есть save() работает как save() и как  update()
        return ResponseEntity.ok(save); //save() понимает, что нужно обновить, а не сохранить если внутри user-а, который нам пришел есть id и этот id совпадает с id user-а в БД, то происходит обновление. Если id в БД - нет, либо он=0 - происходит сохранение
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Valid User user) { //Возвращать мы будем только код. Никаких данных в body у нас не будет. Мы не знаем что там будет <?>
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/id")
    public ResponseEntity<?> deleteById(long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
