package by.tms.practice24.filter;

import by.tms.practice24.configuration.TokenProvider;
import by.tms.practice24.exceptions.CustomException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//Фильр - это самый основной компонент, когда мы работаем с JWT
@Component //Это наш фильтр, а не Security и он будет отрабатывать каждый раз в контексте Spring Security
public class JwtFilter extends OncePerRequestFilter { //так в Spring добавляют фильтр. Его не нужно помечать аннотацией Web фильтр

    private final TokenProvider jwtTokenProvider;

    public JwtFilter(TokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override //первым делом запрос с Insomnia попадает прямо сюда
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(httpServletRequest); //передаем на проверку токен и получаем его
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) { //проверка на null и валидация. Если успешно - то мы прошил аутентификацию
                Authentication auth = jwtTokenProvider.getAuthentication(token); //получаем Authentication
                SecurityContextHolder.getContext().setAuthentication(auth); //заносим внутрь Security context.
                // Если все сделали правильно, то уже полностью аутентифицировали запрос с user-ом. Дальше запрос
                // уже пойдет на контроллеры
            }
        } catch (CustomException ex) { //если произошла ошибка
            SecurityContextHolder.clearContext(); //очищаем  SecurityContextHolder. Сбрасываем всю аутентификацию
            httpServletResponse.sendError(ex.getHttpStatus().value(), ex.getMessage()); //Отправляем в response ошибку
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse); //отправляем запрос дальше
    }
}
