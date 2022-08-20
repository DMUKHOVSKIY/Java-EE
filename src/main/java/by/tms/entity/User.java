package by.tms.entity;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.*;
//Имена колонок в сгенерированной таблице совпадают с названием полей класса
@Data
@Table(name="hibernateUsers")//так как гибернейт дает название таблице такое же как и название сущности,только
// с маленькой буквы, а название таблицы user -  зарезервировано, то нам надо дополнительно указать название.
@Entity //теперь можно сохранять юзера через гибернейт. Аннотация говорить о том, что это консистентная сущность (гибернейт будет делать с ней то, что мы скажем)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Эта стратегия говорит, что она будет заниматься инкрементом нашего id
    private long id; //обязательное поле
    @NotNull
    @NotBlank // "" - true(пустая), " " - true(пустая), "a" - false(не пустая)
    @NotEmpty // "" - true(пустая), " " - false(не пустая)
    private String name;
    @NotNull(message = "Must not be null")//вместо defaultMessage будет писать наш код
    @NotBlank
    @NotEmpty
    private String username;
    @NotNull
    @NotBlank
    @NotEmpty
    private String password;
}
