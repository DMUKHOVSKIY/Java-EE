package by.tms.practice23.configuration;

import by.tms.practice23.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  /*уже устарел. На сегодняшний день используют цепочку без наследования*/ {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {  //тут указываем что защищать
        http    //доступ к калькулятору имеет только user прошедший аутентификацию
                .authorizeHttpRequests()
                .antMatchers("/", "/user/reg").permitAll() //доступ всем. Все эти String можно выделить потом в properties, чтобы тут не писать
                .anyRequest().authenticated() //все остальные запросы защищены
                .and()
                .formLogin()
                .loginPage("/user/login") //теперь будет своя собственная форма регистрации
                .permitAll() //форма для логина
                .and()
                .logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { //тут указываем источник данных для входа пользователей
//        auth.inMemoryAuthentication()
//                .withUser("test")
//                .password(passwordEncoder().encode("test"))
//                .authorities("ROLE_USER"); //Это была аутентификация in Memory
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder()); // userDetailsService - тот же самый UserService с единственным методом loadByUsername()
        // userDetails - голые данные нашего пользователя, который в будущем преобразуется в объект аутентификации
    }

    @Bean
    public PasswordEncoder passwordEncoder() {  //метод для хеширования пароля
        return new BCryptPasswordEncoder(10);
    }
}
