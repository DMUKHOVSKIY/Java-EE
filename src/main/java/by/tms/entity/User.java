package by.tms.entity;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//Имена колонок в сгенерированной таблице совпадают с названием полей класса
@Entity
//теперь можно сохранять юзера через гибернейт. Аннотация говорить о том, что это консистентная сущность (гибернейт будет делать с ней то, что мы скажем)
@Table(name = "users")//так как гибернейт дает название таблице такое же как и название сущности,только
// с маленькой буквы, а название таблицы user -  зарезервировано, то нам надо дополнительно указать название.
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Эта стратегия говорит, что она будет заниматься инкрементом нашего id
    private long id; //обязательное поле
    @NotNull
    @NotBlank // "" - true(пустая), " " - true(пустая), "a" - false(не пустая)
    @NotEmpty // "" - true(пустая), " " - false(не пустая)
    // @Column(name = "u.name", unique = true /*name - поле уникальное*/) //можно изменить название колонки
    private String name;
    @NotNull(message = "Must not be null")//вместо defaultMessage будет писать наш код
    @NotBlank
    @NotEmpty
    private String username;

    @NotNull
    @NotBlank
    @NotEmpty
    private String password;

    //В этом классе используются стандартные типы: String, int, long, которые могут легко в реляционной БД
    //в качестве значения колонки использоваться, но если есть класс Address
    //private Address adsress; //этот адрес невозможно вставить как значение колонки (адрес состоит из 2 полей - 2 отдельные колонки)
    //Выход - использовать механизм связей Hibernate
    //Виды связи: (с помощью их, мы можем настраивать связи в Hibernate) (на каждый из типов есть аннотация, которую надо повесить на поле и сказать, что это будет связь)
    //OneToOne - (1 user - 1 address)
    //OneToMany - (1 user - множество адресов) private List<Address> addresses
    //ManyToOne - (в обратную сторону: мн-во users - владеют 1 адресом)
    //ManyToMany - (мн-во users - мн-во адресов)
    //Эти аннотации работают, когда мы работаем с кастомными типами(нашими)

//    @OneToOne(cascade = CascadeType.ALL) //1 user - 1 address
    //Но когда мы так указываем @OneToOne, то на момент любой операции над этим user-ом, этот адрес обязан быть в БД
    //То есть надо дополнительно создать AddressDao и в UserService-е перед сохранением user-а дополнительно сохранять адрес
    //@OneToOne справедлива, только тогда, когда когда User - книга, а Address - категория книги (уже давно есть в таблице категорий)
    //Мы хотим, чтобы гибернейт имел какую-либо функцию, связанную с переприменением методов. Такая функция есть - это каскады
    //cascade = CascadeType.ALL - это значит, что над адресом будут применяться все операции, которые применяются над user-ом
    //То есть, если мы сохраняем user-а, то гибернейт будет автоматически сохранять адрес, если обновляем user-а, то гибернейт будет
    //автоматически обновлять и адрес
    //Каскады(каскадное распространение) - по факту просто переприменение операций над целевой сущностью (save(user), значит save(address))
    //Но с книгой и категорией каскады не пойдут
//    private Address address;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER /*- это плохо*/)//если у юзера не 1 адрес при регистрации, а 2
    private List<Address> address;

    //коллекции, как обычный базовый тип, тоже не работает без аннотации:
    //У нас есть лист обычного типа
    @ElementCollection //когда мы вешаем эту аннотацию, то это считается как обычная коллекция с базовыми типами: лист int, лист String
    private List<String> roles;

//    @Enumerated(EnumType.STRING) //так мы будем видеть в таблице String, а не порядковый номер
//    private Role role; //гибернейт не ругается, потому что enum по факту стринг. Но мы увидим не String, а порядковый номер енама
//
//    private LocalDateTime localDateTime; //ничего не надо навешивать, сразу понятно, что это дата
//    private LocalDate localDate; //ничего не надо навешивать, сразу понятно, что это дата
//
//    @Temporal(TemporalType.DATE)
//    private Date date; //но если мы для старого варианта нужно дополнительно указывать, что мы будем хранить под видом Date через аннотацию @Temporal

//    public static void main(String[] args) {
//        String name1 = Role.USER.name();//стрирнговое представление user(вместо name1 будет "USER")
//        int ordinal = Role.USER.ordinal(); //порядковый номер enum в классе (счет начинается с 0)
//        Role admin = Role.valueOf("ADMIN");//воссоздаём role ADMIN
//        Role value = Role.values()[Role.USER.ordinal()]; //можем достать по порядковому номеру
//    }

}
