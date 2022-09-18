package by.tms.practice24.dao;

import by.tms.practice24.entity.User;
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
}
