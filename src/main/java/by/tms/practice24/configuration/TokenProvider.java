package by.tms.practice24.configuration;


import by.tms.practice24.entity.AppUserRole;
import by.tms.practice24.exceptions.CustomException;
import by.tms.practice24.service.MyUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenProvider { //это класс помощник. Нужен для того, чтобы использовать служебные методы, связанные с токеном

    @Value("${security.jwt.token.secret-key:secret-key}") //достаем ключ. Не должен попасть в руки сторонним людям
    private String secretKey;

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000;

    @Autowired
    private MyUserDetails myUserDetails;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, List<AppUserRole> appUserRoles) { //делали на прошлом занятии

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", appUserRoles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        //По факту:
        //Мы получаем токен и нам нужно сформировать детали, чтобы засунуть их в Security Context. Токен же мы не будем туда засовывать, хотя так можно
        // А сформировать UsernamePasswordAuthenticationToken без деталей невозможно
        //Поэтому мы должны их получить
        UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token)); //достаем user-а из БД по username под видом UserDetails, которое мы достаем из токена
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()); //создаем UsernamePasswordAuthenticationToken, который нужен самому Spring Security и достаем его роли
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization"); //достаем из request header "Authorization"
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) { //уничтожаем префикс "Bearer "
            return bearerToken.substring(7);//достаем токен
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);//берем токен и парсим его, используя секретный ключ. Парсим все данные jwt
            return true; //Если нет ошибок, то возвращаем true. Так как метод "parseClaimsJws" возвращает exception (если истек строк годности или не та сигнатура)
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR); //если есть ошибка, то создаем exception, который просто перерезает все и нам возвращается 401 или 403
        }
    }
}
