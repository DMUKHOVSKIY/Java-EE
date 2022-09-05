package by.tms.practice22.dao;

import by.tms.practice22.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryUserDao {
    private final List<User> users = new ArrayList<>();
    private final AtomicLong atomicLong = new AtomicLong(0);

    public User save(User user) {
        //У нас тут должен генерироваться id и мы должны возвращать этого user-а с id
        //То есть к нам приходит user без id а возвращать мы его должны с id
        //В REST есть правило - быть более категоричным к тому, что приходит и быть проще к тому, что мы отдаем (мы должны отдавать больше информации)
        user.setId(atomicLong.incrementAndGet()); //это авто генерация id
        users.add(user);
        return user;
    }

    public List<User> findAll(){
        return new ArrayList<>(users); //мы клонируем старый лист, так как если передаем старый, то в месте, где метод вызывается можно будет как-то менять этот лист
    }

    public Optional<User> getByUsername(String username){
        for (User user : users) {
            if(user.getUsername().equals(username)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public Optional<User> findById(long id) {
        for (User user : users) {
            if(user.getId() == id){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }


    public Optional<User> update(User user) {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId() == user.getId()){
                users.set(i, user);
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public boolean delete(User user){
        return users.remove(user);
    }

    public Optional<User> deleteById(long id) {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId() == id){
                User remove = users.remove(i);
                return Optional.of(remove);
            }
        }
        return Optional.empty();
    }
}
