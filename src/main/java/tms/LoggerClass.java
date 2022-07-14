package tms;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.logging.Level;

public class LoggerClass {

    //JDK Logger
    //private Logger logger = Logger.getLogger("LoggerClass");

    //Log4j  //Но для него нужно написать конфигурацию, для того, чтобы логгер смог работать и писать в консоль
    //private Logger logger = Logger.getLogger(this.getClass());

    //Slf4j //Но его нужно кофигурировать, в зависмости какой логер мы подрубили (тут Log4j)
    private Logger logger = LoggerFactory.getLogger(LoggerClass.class);


    protected void method() {
        //JDK Logger
        // log("Use this") //Сервлетовский логгер
        // logger.log(Level.INFO, "Use this"); //вставляем уровень енамом

        //Log4j
        //logger.info("Use this"); //уровень фигурирует как название метода
        //logger.error("Error", new Throwable());

        //Slf4j
        //logger.info("Use this"); //уровень фигурирует как название метода

    }
}
