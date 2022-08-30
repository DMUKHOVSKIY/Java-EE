package by.tms.dao;

import by.tms.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JpaUserDao implements UserDao {

    //центральное место:
    //EntityManagerFactory - аналог SessionFactory, но ее не надо инжектить
    //EntityManager - аналог  session

    @PersistenceContext //эта аннотация работает, как аннотация @Autowired, но имеет под капотом еще больше механизмов
    //и когда мы вызываем какой-то метод из dao будут разные entityManager
    private EntityManager entityManager; //он ассоциирован с контекстом

//    @PersistenceContext(unitName = ) мы можем иметь несколько @PersistenceContext (фабрик/областей, где располагаются наши кэши и тд.). Это для того, чтобы работать с несколькими БД одновременно
    @Override
    public void save(User user) {
        entityManager.persist(user); //аналог .save() в Hibernate
    }

    @Override
    public User findById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) { //У JPA нет HQL, но есть JPQL
        TypedQuery<User> query = entityManager.
                createQuery("select u from User u where u.username = :username", User.class); //select u - говорит, что мы хотим достать все поля User (надо писать через псевдонимы)
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> select_user_from_user = entityManager.createNamedQuery("User.findAll", User.class);//именованный запрос
        return select_user_from_user.getResultList();
    }

    @Override
    public void delete(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void update(User user) {
        //можем использовать persist в качестве update, но там есть нюансы
        User user1 = entityManager.find(User.class, user.getId());
        entityManager.merge(user1);
        //можно еще использовать update через запрос
    }
}
