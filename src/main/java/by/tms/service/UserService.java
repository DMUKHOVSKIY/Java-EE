package by.tms.service;

import by.tms.dao.HibernateUserDao;
import by.tms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service //расширяет аннотацию компонент
@Transactional //+ если у нас есть какой-то кастомный эксепшн, который не страшен для транзакции
//мы можем дополнительно указать (rollbackFor(); noRollbackFor()) - дополнительно указать, какие ошибки
//не должны влиять на rollback и тд.
//Мы пометили этой аннотацией именно сервис, потому, что именно в сервисах само часто вызываются
//различные методы разных Dao
public class UserService {

    @Autowired
    private HibernateUserDao userDao;
    public void save(User user) {
        //Представим ситуацию. У нас есть userDao и orderDao. Мы хотим,
        //чтобы эти 2 операции выполнились в 1 транзакции. Если хотя бы
        //1 из них не выполнится, то никто не пойдет в базу. Как сделать так,
        //чтобы они работали в контексте 1 транзакции? -> Spring+Hibernate
        //дают нам аннотацию @Transactional и все методы сервиса работают в контексте
        //транзакции
        userDao.save(user);
        //orderDao.save(user);
    }//тут выполнился commit и юзер сохранился в БД

    public void trigger() {
        System.out.println(userDao.findAll());
        System.out.println(userDao.findByUsername("a"));
        System.out.println(userDao.findById(1));
    }
}
