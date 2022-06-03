package tms.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//Слушаетли - компоненты внутренней системы Tomcat, которые событийно ориентированные
//в любом языке программирования есть события(происходят при определенных обстоятельствах)
// не касаются Http

@WebListener
public class Listener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) { // вызываются автоматически при определенном событии
        ServletContextListener.super.contextInitialized(sce);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) { // init и destroy - общие методы, они касаются контейнера
        ServletContextListener.super.contextDestroyed(sce);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
    }

    // Атрибуты - данные (пары ключ-значение)
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        HttpSessionAttributeListener.super.attributeAdded(event);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        HttpSessionAttributeListener.super.attributeRemoved(event);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        HttpSessionAttributeListener.super.attributeReplaced(event);
    }
}
//Скоуп - хранит каку-то информацию (область для хранения атрибутов). Например, чтобв отдавать данные на JSP или чтобы передвать данные между сервлетами

// Application scope / Servlet Context - эта область живет, сколько и живет Tomcat, может хранить атрибуты
// Session Scope - тоже можно хранить атрибуты, живет, пока живет активна сессия определенного клиента
// Request Scope - тоже можно хранить атрибуты, живет само мало (до исполнения запроса-ответа). Один из самых популярных.

//ключ - строка
//значение - объект