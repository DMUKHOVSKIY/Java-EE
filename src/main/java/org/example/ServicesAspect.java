package org.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component //Может быть проблема. В некоторых версиях Aspect - это и есть компонент
public class ServicesAspect {
    Logger logger = Logger.getLogger(this.getClass().getName());
//Пример:
    //Joint point - точка, к которой мы присоединяем нашу основную функциональность. (По факту - метод read())
    //Target point - объект, к которой мы присоединяем нашу основную функциональность

    //Получается все базируется на прокси
//    @Pointcut("ConsoleReader.read()") //Чтобы применить к определённому методу. Pointcut(срез) - несколько объединённых точек к которым мы прикрепляем нашу сквозную функциональность
//    @Before()//Чтобы применялось до основного метода Read. Называется advice(совет)
//
//    public void logging(){
//        log.info("User this");
//    }

    //    @Around() - тоже вид совета
//   // @After() - also advice
//    public void logging(PointCut pc){ //pc - основной метод
//        log.info("Start");
//        pc.process(); //вызываем основной метод
//        log.info("End");
//    }
//    @Pointcut("execution(public * com.example.demoAspects.MyService.*(..))")
//    1-ая звёздочка(*) - любой тип возвращающего значения +
//    не важно какие аргументы и какое их количество(..) +
//    этот поинткат применяется ко всем методам сервиса(*) +
    @Pointcut("execution(public * org.example.CalculatorService.*(..))") //все методы с любыми возвращаемыми значениями
    public void loggingPointcut() {
    }//не имеет тела, метод как точка
//Далее основная сквозная функциональность

    @Before("loggingPointcut()")
    public void logging(JoinPoint joinPoint/*чтобы понимать, где будет вызван метод*/) {
        logger.log(Level.INFO, "Invoke method" + joinPoint.toString());
    }

}
