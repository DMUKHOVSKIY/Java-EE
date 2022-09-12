package by.tms.practice22.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExController extends ResponseEntityExceptionHandler { //по умолчанию ResponseEntityExceptionHandler не отрабатывает,только если мы его вот так расширяем
    @ExceptionHandler(RuntimeException.class)
    //если мы наследуемся от ResponseEntityExceptionHandler, то не нужно ставить эту аннотацию на переопределяемых методах
    public ResponseEntity<?> re(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR /*5XX коды - ошибки сервера*/);
    }

    @Override
    //так мы можем не отрабатывать больше в контроллере ошибки валидации. Мы можем перенести их в этот метод и готово. Так как код с обработкой ошибок валидации может повторятся, а так мы убираем одинаковый код
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult(); //можем достать результат нашего связывания
        Map<String, String> messages = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            messages.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }

}
