package by.tms.practice22.dao;

import by.tms.practice22.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

//Repository - компоненты для доступа к БД. Это то же Dao, тот же Storage
//Нам не нужно создавать никаких классов имплементирующих этот интерфейс
public interface UserRepository extends JpaRepository<User,Long> { //лучше наследоваться именно от JpaRepository.
    // Дополнительно указываем 2 дженерика. Тип над которым будем производить все операции - "User" и
    // тип id внутри User - "Long". Это нужно для того, чтобы методы знали, какие параметры в них передавать

    //Пишем запрос в виде DSL
    Optional<User> findByUsername(String username); //Это запрос для Spring Data JPA и он будет странслирован из Query DSL в обычный SQL запрос
    Optional<User> findByUsername(String a, Pageable pageable); //Если мы хотим пагинацию на наш метод DSL

    //Если нам не достаточно QueryDSL и мы хотим написать свой собственный запрос
//    @Query(value = "select u from User u where u.username = :username ")//По умолчанию NativeQuery  стоит false. Запрос пишем на JPQL/именованные параметры/язык JPQL
    @Query(value = "select u from User u where u.username =?1") //2 способ - говорим достать 1-ый параметр из скобок/пронумерованные параметры/язык JPQL
    Optional<User> myFindByUsername(/*@Param("username")-для указания точных параметров для переменной, но у нас названия "username" совпадают */String username);

    //Если мы хотим нативный SQL запрос:
    @Query(value = "select * from users where username = :username", nativeQuery = true) //SQL
    Optional<User> mYFindBYUsername(String username);
    //Джоины во всех запросах работают автоматически и все автоматически транслируются в обычный SQL
    //Query DSL или JPA язык более безопасны. Запросы компилируется и получается так, что у них сразу идет проверка. Если есть ошибки, то приложение просто не запускается

    List<User> findAllByNameOrderByNameAsc(String name); //можем прямо в DSL указать, что хотим отсортировать некую последовательность имя по возрастанию/не надо передавать сортировку(она не будет меняться)
    List<User> findAllByName(String name, Sort sort);//если мы хотим передавать сортировку(она будет меняться от запроса к запросу)

}
