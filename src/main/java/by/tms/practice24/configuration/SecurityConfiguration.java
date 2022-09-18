package by.tms.practice24.configuration;

import by.tms.practice24.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {  //Простая конфигурация для Security

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() //сообщает Spring, что не следует хранить информацию о сеансе для пользователей, поскольку это не нужно для API
                .csrf().disable() //cross-site request forgery(«межсайтовая подделка запросов»)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Говорим, что не используем сессию.Без этого работает, но плохо
                .and()
                .authorizeRequests()
                .antMatchers("/user/reg", "/user/login").permitAll() //все запросы под защитой, кроме регистрации и аутентификации (эти пути открыты без токена)
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class /*этот тип фильтра самый стандартный и он говорит, что будет обрабатывать username and password*/);//именно эта строка и добавляет фильтр(jwtFilter), который будет обрабатывать каждый запрос, который будет приходить к нам на API
        //по факту можно вообще не трогать jwt, а каждый раз отправлять username and password

    }

    @Bean //бин для кодирования пароля
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    } //пока что нигде не нужен
}
