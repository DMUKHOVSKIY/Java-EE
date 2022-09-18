package by.tms.practice23.service;

import by.tms.practice23.entity.Role;
import by.tms.practice23.entity.User;
import by.tms.practice23.repository.UserRepository;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    public void registration(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.USER);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {  //этот метод будет автоматически вызываться Security и сюда передается username, который введен в форме
        Optional<User> byUsername = userRepository.findByUsername(username);
        if(byUsername.isPresent()){
            return byUsername.get();//надо чтобы user был как UserDetails. Поэтому мы наследуем наше entity "User" от UserDetails
        }
        return null;
//        return byUsername.orElse(null); можно сделать так (если user не упакован внутри, то возвращаем null)
    }
}
