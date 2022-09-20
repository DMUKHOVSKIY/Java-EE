package by.tms.practice24.configuration;




import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {  //Простая конфигурация для Security


    private final TokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //cross-site request forgery(«межсайтовая подделка запросов»)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Говорим, что не используем сессию.Без этого работает, но плохо
        http.authorizeRequests()
                .antMatchers("/user/signin").permitAll() //все запросы под защитой, кроме регистрации и аутентификации (эти пути открыты без токена)
                .antMatchers("/users/signup").permitAll()
                .antMatchers("/h2-console/**/**").permitAll() //если мы работаем с h2 консолью, то к ней надо иметь доступ. Без этого поля никуда не пропустит
                .anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/login"); //если клиент отправит запрос с каким-то не таким токеном, то ему будет выдана страница логин
        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider)); //самое важное - фильтр
    }

    @Override
    public void configure(WebSecurity web) throws Exception {//Web Security дополнительно игнорируем некоторые пути. Желательно пути вынести в application properties, чтобы их можно было добавлять, когда нужен путь к которому имеют все доступ
        web.ignoring().antMatchers("/v2/api-docs") //Документация свагера.Благодаря игнорингу мы говорим, что эти пути ниже мы тоже не защищаем
                .antMatchers("/swagger-resources/**")
                .antMatchers("/swagger-ui.html") //так как swagger тоже использует доп. пути, а jwt получить неоткуда
                .antMatchers("/configuration/**")
                .antMatchers("/webjars/**") //тут находятся всякие картинки, иконки и тд.
                .antMatchers("/public")
                .and()
                .ignoring()
                .antMatchers("/h2-console/**/**");
    }

    @Bean //бин для кодирования пароля
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    } //пока что нигде не нужен

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception { //нужен для того, чтобы сделать
        // более нативную аутентификацию в Spring Security. На прошлом занятии мы сами создавали Authentication и
        // засовывали его в Security Context. Тут добавили менеджер, который будет делать это автоматически. Вообще
        // большой разницы нет, но так лучше
        return super.authenticationManagerBean();
    }
}
