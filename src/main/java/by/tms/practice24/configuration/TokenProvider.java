package by.tms.practice24.configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Component
public class TokenProvider { //это класс помощник

    private static final String JWT_Secret = "jkjgnworgowri"; //jwt секрет (любая строка, которую мы захотим).
    // Его вообще надо вынести константным значение на уровень проекта (в properties)
    public String generateToken(String username){ //генерируем токен на основании username
       return  Jwts.builder() //builder, который нам создаст токен
                .setSubject(username) //то, что хотим закодировать в токене
                .setExpiration(new Date(new Date().getTime() + 3600)) //время существования токена/дата, когда токен истечет
                .signWith(SignatureAlgorithm.HS512, JWT_Secret)//стандартный алгоритм и jwt секрет
                .compact(); //генерируем токен
    }
}
