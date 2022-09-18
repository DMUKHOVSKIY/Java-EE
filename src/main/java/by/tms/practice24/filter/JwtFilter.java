package by.tms.practice24.filter;

import by.tms.practice24.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component //Это наш фильтр, а не Security и он будет отрабатывать каждый раз в контексте Spring Security
public class JwtFilter extends OncePerRequestFilter { //так в Spring добавляют фильтр. Его не нужно помечать аннотацией Web фильтр

    private static final String JWT_SECRET = "jkjgnworgowri";
    @Autowired//этот юзер сервис должен нам вернуть user, который реализует UserDetailsService
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException { //тут проверяем токен/валидируем. Каждый запрос обрабатывает этот метод
        String token1 = getToken(request); //достаем токен, помещенный в запрос
        Claims body = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token1).getBody(); //достаем из токена body. (парсим jwt)
        String username = body.getSubject();//тот String, который мы передавали, когда генерировали токен. По факту наш username из токена
        UserDetails userDetails = userService.loadUserByUsername(username); //вызываем метод у нашего User Service и из
        // БД получаем  UserDetails. userDetails - голые данные нашего пользователя, который в будущем преобразуется в объект аутентификации
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        //сюда нужно передать principal and credential. Мы создали token, который нужно будет потом использовать, чтобы создать аутентификацию.
        // UsernamePasswordAuthenticationToken - основной токен, который включает в себя
        // того user-а, который пришел к нам с этим токеном и мы его организовываем как полноценного principal
        // (тот пользователь, который вошел в систему) + передаем детали и роли (крэды не нужны)
        WebAuthenticationDetails webAuthenticationDetails = new WebAuthenticationDetailsSource().buildDetails(request); //создали для веб аутентификации детали и передали туда конкретный request. Чтобы запрос работал, чтобы он был авторизованный запрос
        token.setDetails(webAuthenticationDetails);//в этот токен мы вставляем детали веб запроса

        SecurityContextHolder.getContext().setAuthentication(token); //без этого запросы проходить дальше не будут. В этом SecurityContextHolder находится пользователь, который ввел правильно логин и пароль. Мы засовываем туда аутентификацию в качестве токена
        filterChain.doFilter(request, response); //чтобы дальше пошел запрос

        //В итоге:
        //1)Получаем токен из каждого запроса
        //2)Парсим его (токен)
        //3)Получаем username
        //4)Идем в БД и достаем детали
        //5)Аутентифицируемся
        //6)Создаем детали
        //7)Собираем все вместе (как одну аутентификацию)
        //8)Засовываем все вручную и отправляем запрос дальше
    }

    private String getToken(HttpServletRequest request) {  //метод, который будет доставать токен из request
        String header = request.getHeader(HttpHeaders.AUTHORIZATION); //достаем токен из нативного хедера
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) { //Клиент отправляет нам запрос. На клиенте
            // есть jwt. Http запрос делится на 2 части: шапка и тело. Уже существует специальный header "AUTHORIZATION" - это ключ. А значение состоит из специального префикса "Bearer" и jwt
            return header.substring(7);//достаем токен без префикса
        }
        return null;
    }
}
