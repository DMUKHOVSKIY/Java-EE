package by.tms.practice24.service;

import by.tms.practice24.dao.UserRepository;
import by.tms.practice24.entity.Role;
import by.tms.practice24.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//
//    public void registration(User user){
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.getRoles().add(Role.USER);
//        userRepository.save(user);
//    }
//
//    public Optional<User> findByUsername(String username){
//        return userRepository.findByUsername(username);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(username);
        return byUsername.orElse(null);
    }
}
