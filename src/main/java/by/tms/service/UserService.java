package by.tms.service;

import by.tms.dao.HibernateUserDao;
import by.tms.dao.UserDao;
import by.tms.entity.User;
import org.hibernate.cache.spi.entry.StructuredCacheEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service //расширяет аннотацию компонент
@Transactional //+ если у нас есть какой-то кастомный эксепшн, который не страшен для транзакции
//мы можем дополнительно указать (rollbackFor(); noRollbackFor()) - дополнительно указать, какие ошибки
//не должны влиять на rollback и тд.
//Мы пометили этой аннотацией именно сервис, потому, что именно в сервисах само часто вызываются
//различные методы разных Dao
public class UserService {

    //Если у нас есть 2 реализации Dao с аннотацией @Repository, а тут указан интерфейс, то не понятно, какую
    //реализацию использовать. Есть 2 варианта:
    //1)Оставить 1 аннотацию @Repository
    //2)Аннотация @Qualifire:
    @Autowired
//    @Qualifier("hibernateUserDao")
    @Qualifier("jpaUserDao")
    private UserDao userDao;

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

    @Transactional(readOnly =true)
    public void trigger() {
       User byId = userDao.findById(1);
        User test = userDao.findByUsername("test");
        System.out.println(test);
        System.out.println(byId.getRoles());
        System.out.println(byId.getAddress());
    }
}
