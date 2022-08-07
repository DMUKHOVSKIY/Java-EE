package by.tms.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//Так как Interceptor-ы - более узко специализированные компоненты, то регистрировать их на определённом контроллере или методе сложнее
@Component
//В Interceptor-е более детальная обработка всех запросов, в отличие от Filter(один метод doFilter)
public class HelloInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.sendRedirect("/"); //можем так
        return false ; //запрос дальше не идёт, если true - запрос идёт дальше
    }

//    @Override //Этот метод получает (ModelAndView), который возвращает метод anotherHelloWord и мы можем ещё что-то доработать и даже поменять
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override //Этот метод вызывается, когда мы хотим зарегистрировать какие-то вещи. Например залогировать, что запрос прошёл или залогировать ошибку
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
}
