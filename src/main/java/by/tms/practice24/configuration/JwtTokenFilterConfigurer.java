package by.tms.practice24.configuration;


import by.tms.practice24.filter.JwtFilter;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {//Адаптер. Стоит между фильтром и Security configuration. Без него тоже можно
    // обойтись, но он добавляет доп. прослойку, благодаря которой в будущем потом будет можно менять реализации
    // фильтров и не запариваться над этим
    private final TokenProvider jwtTokenProvider;

    public JwtTokenFilterConfigurer(TokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtFilter customFilter = new JwtFilter(jwtTokenProvider); //Берем фильтр, которому нужен токен провайдер
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class); //добавляем к http наш кастомный фильтр
    }
}
