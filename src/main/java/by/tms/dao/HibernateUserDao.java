package by.tms.dao;

import by.tms.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository //аннотация такая же как и @Component. Нужна, чтобы вешать на классы Dao
public class HibernateUserDao {
    @Autowired//инжектим сюда ту самую фабрику, которую создавали в конфигурации (sessionFactory)
    private SessionFactory sessionFactory;//В гибернейт есть 2 простых понятия - это фабрика сессий и сама сессия

    //Сессия - это основной объект в гибернейт, с которым мы работаем в контексте работы с реляционной БД
    //Через сессию мы сохраняем, обновляем, находим
    //Все операции в контексте какой-то реляционной БД мы делаем с сессией
    //Но чтобы достать эту сессию нужна фабрика
    @Transactional //для getCurrentSession() надо указать
    public void save(User user) {
        //user is transient
        Session session = sessionFactory.getCurrentSession(); //открываем новую сессию
        session.save(user);
        //user is persistent
        //Метод save() - не сохраняет в базу сразу, он лишь отдаем по управление гибернейту, а сохраняется все, когда выполняется commit
        //но все это работает, как настроена транзакция (как работает коммит)
        //session.close();//закрываем сессию (для getCurrentSession() нам не надо ее закрывать)
        //user is detached
    }

    public User findById(long id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);//метод get возвращает proxy(заместитель) user-а, а не самого user-а
        //У proxy изменён геттер. И этот геттер идет в БД, чтобы достать все адреса из БД
        //мы передаем токен, чтобы гибернейт привел к типу User
        //persistent
        session.close();
        //detached
        return user;//не нужно ни запросов ни resultSet, сразу готовый юзер на руках

    }

    public User findByUsername(String username) {
        Session session = sessionFactory.openSession();//select * form user where username = 'admin' like = 'b%'
        List list = session.createCriteria(User.class).list(); //так мы можем с помощью КРИТЕРИЯ API достать все данные
        List list1 = session.createCriteria(User.class).add(org.hibernate.criterion.Restrictions.eq("username", username)).list(); //мы можем добавить ограничения (мы достаем из таблицы только поля с определённым usernaem-ом)
//        session.getCriteriaBuilder().like(,"b%"); like паттерн помогает достать все, что начинается на b
        Query<User> query = session.createQuery("from User where username = :username", User.class); //:username - именованный параметр в PrepareStatement мы писали "?"
//        Query<User> query = session.createQuery("from User where username = :username and age = :a" , User.class); //можно дополнительно указывать "and"
        User user = query.setParameter("username", username).getSingleResult();
        session.close();
        return user;
    }

    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        //List<User> users = session.createQuery("from User", User.class).getResultList();//если мы знаем, что этот
        // запрос вернёт 2 и больше юзеров, то мы должны вызывать метод getResultList()|если запрос возвращает 1, то
        //getSingleResult();|list() - аналог getResultList();
        //Мы писали select * from users
        List<User> users = session.createQuery("from User", User.class).getResultList(); //from User сразу определяет все поля этого user-а и не надо никаких "*"
        //+ нам не надо никаких join - гибернейт автоматически сам связывает все, что связано с этим юзером
        //Мы используем HQL(язык запросов). Основная фишка HQL - объектно ориентированные запросы
        //так как в session нет метода "findAll()"
        //NativeQuery select_all_from_hibernateUsers_ = session.createNativeQuery("select all from HibernateUsers ");//но можно использовать и нативные sql запросы
        List<User> resultList = session.createNativeQuery("select * from users where username = :un") //если мы захотели сделать NativeQuery (нативный запрос/обычный SQL),
                //то даже тогда join под капотом включены. Но в реальности нативные запросы не нужны. HQL достаточно
                .setParameter("un", "username") //Можно даже указывать именованные параметры в нативных запросах
                .getResultList();
        session.close();
        return users;
    }

    public void delete(long id) {
        Session session = sessionFactory.openSession();
        //session.delete(); //Но delete() идёт по объекту -> хорошо когда мы принимаем User user в параметрах
        //Гибернейт смотрит по equals() и понимает, что ему надо удалить. Но если на руках нет user, а есть long id:
        session.createQuery("delete from User where id= :id")
                .setParameter("id", id)
                .executeUpdate();
        User user = session.get(User.class, id);
        session.delete(user);
        session.close();
    }

    public void update(User user) {
        Session session = sessionFactory.openSession();
        session.createQuery("update User set username = :un where id = :id")
                .setParameter("un", user.getUsername())
                .setParameter("id", user.getId())
                .executeUpdate();
        session.update(user);//как понимает кого и что обновить:
        //гибернейт берет этого юзера и находит по id внутри него,
        // а затем все остальные поля обновляет на те, что будут в этом юзере
        //Поэтому у юзера должен быть обязательно id, но и поля тоже должны быть правильно заполнены
        //Потому, что если придет юзер с пустыми строками, то он все поля заменит на пустые строки.
        //Все пустые строки должны быть заполнены старыми значениями
        session.close();
    }

}
