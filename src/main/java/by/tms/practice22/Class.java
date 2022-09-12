package by.tms.practice22;

public class Class {
//    Spring Boot, REST/REST API:
//    Тема: Сервисы
//    Если у нас есть сайт https://www.gismeteo.by, то введя https://www.gismeteo.by/api/ мы можем найти документацию для разработчиков
//    Мы до сих пор писали веб приложения (server-side HTML application).
//    Веб приложение - это клиент серверное приложение у которого клиент в 99% случае - браузер.
//    Поэтому мы всегда отдавали назад HTML - язык гипертекстовой разметки, который понимает сам браузер. Но дэсктопные приложения не браузер и им тоже нужно получать данные. И если gismeteo.by не имело бы никакого api, то  gismeteo.by будет отдавать HTML, а десктопное приложение этого не понимает.
//    Если веб приложение - это клиент-сервер и бэк всегда отдает клиенту HTML, то сервис - это слежебная программа в сети интернет, которая по запросу отдает не HTML, а какие-то данные
//    в каком-то виде
//    Теперь мы пишем приложения, которые на запрос отдают данные
//1 Вариант:
//    Сервис-ориетнитрованная архитектура (SOAP) Simple Object Access Protocol - ПРОТОКОЛ для передачи и обмена данными. SOAP работает поверх HTTP (в SOAP-е есть IP, TCP, сокеты и тд.)
//    HTTP - протокол, который позволяет передавать текст
//    SOAP - протокол, который передает определённый набор согласованных данных. Он имеет строгую структуру, и требует согласования с 2 сторон - на клиенте и на сервере и использует внутри себя структуру документа в виде xml. SOAP весит больше
//    То есть мы созадем не веб приложение, а Web Service - у веб сервиса может быть много разных клиентов (телфон, телевизор), а с веб приложением общается только браузер, потому что веб приложения отдают HTML.
//    Десктопные приложения могут отправлять HTTP запросы, а HTML ему попросту не нужен
//2 Вариант:
//    Rest - это уже не протокол, а архитектурный стиль. Так как мы все равно используем HTTP протокол, то зачем усложнять. Так как у нас есть понятие url, то зачем в Soap/xml впихивать ту точку, которую я хочу вызвать на сервере, когда я могу указать это в url.
//    Rest - это упрощение, это архитектурный стиль (это не протокол) который говорит, что давайте использовать обычные понятия, такие как HTTP(как протокол для передачи данных), URL(как идентификатор ресурсов, которые мы хотим вызвать на сервере) и XML/JSON(в качестве формата обмена данными)
//    В ресте:
//    Если мы знаем основной url + данные + метод (например Get()) - мы знаем все, что требуется для отправки HTTP запроса на сервер
//
//    Поэтому если мы говорим про HTTP и про REST, это не значит что это работа с браузером, потому что HTTP запрос можно отправить практически с любого языка программирования на другой язык программирования. HTTP позволяет предавать не только HTML, но и другие данные
//
//    API - набор каких-то открытых точек, куда мы можем отправить HTTP запрос и получить какие-то данные/все что открыто для взаимодействия
//
//    На самом деле для реализации API достаточно реализовать сервлеты без JSP (отправлять JSON)
//    Но если мы хотим построить на сервлетах Rest API, то нам надо добавить библиотеку для работы с JSON. Но мы так не делаем, так как у нас есть Spring
//
    //Postman, Insomnia - программы для тестирования наших методов API. Можно сказать это клиент для тестирования
    //В REST важен тип метода. В вэбе мы использовали 2 типа методов - get(), post(). В вэбе url очень много значит
    //Если мы напишем localhost:8080/user и будет указан тип метода - GET, то это значит, что мы хотим получить всех user-ов
    //Если мы напишем localhost:8080/user?id=2 и будет указан тип метода - GET, то это значит, что мы хотим получить 1 user-а с id = 2
    //Если мы напишем localhost:8080/user и будет указан тип метода - POST, то это значит, что мы хотим сохранить/создать user-а
    //Если мы напишем localhost:8080/user и будет указан тип метода - PUT, то это значит, что мы хотим обновить user-а
    //GET - получить
    //POST - создать, сохранить
    //PUT - обновить
    //DELETE - удалить
 //В классическом Spring надо добавить Jackson в конфигурацию и зарегистрировать его в качестве конвертера
    //Если мы пишем свой REST API, то он должен поддерживать несколько ограничений
    //И если мы соблюдаем все 5 ограничений - то наш REST - RESTFULL (полноценный)
    //В REST есть несколько ограничений:
    //Одно из них: нет состояний (нет такого понятия, как сессия). Мы не должны сохранять состояние определённого пользователя

    //Новое занятие:
    //В одном приложении можно сочетать как REST Controller-ы так и обычные Controller-ы
    //Проект не пишется только REST или только MVC
    //Но сейчас бэк - это чистый API без MVC
    //REST - архитектурный стиль, который регламентирует использование стандартных 3 вещей:
    //HTTP протокола, URL - как идентификатор ресурсов (что мы хотим вызвать), и JSON - как формат
    //передачи данных
    //Масштабирование с API - намного удобнее + приложение быстрее
    //Мы не должны хранить состояние нашего клиента в наешей сессии, так как при масштабировании проекта во втором API
    //бедет другой Tomcat(контейнер сревлетов) и там не будет этой сессии
    //В веб приложении у нас подразумевалась монолитная архтектура. Мы могли засунуть в сессию
    //юзера и деражать (у нас была кука в браузере и мы могли конеретно понимать, кто к нам обратился)
    //Теперь конкретный клиент должен хранить внутри себя данные. Наш сервер собирает Jason web token со всей информацией
    //о клиенте и отправляет его обратно приложению. И потом клиент при КАЖДОМ ЗАПРОСЕ отправляет это токен серверу и
    //сервер знает с кем работает. Этот токен обновляется каждый например час
    //В REST очень важно обращать внимание на коды состояния HTTP, которые отправляет наш бэк
    //В браузере мы видели эти коды только тогда, когда была какая-то ошибка
    //Статусы много чего говорят клиенту, что было не так сделано
    //Коды:
    //1XX - информационные. Тоже не так часто используются
    //2XX - успешное выполнение.
    //3XX - перенаправление. Не сильно используется в мире REST
    //4XX - ошибки клиента.
    //5XX - ошибки сервера.
    //Но кодов для реальных ситуаций мало
    //Клиент может видеть 4ХХ коды, но 5ХХ коды мы должны обрабатывать и клиент их видеть не должен
    //ResponseEntity<> помогает нам вернуть не только объект, который Jackson потом приведет в JSON, но и код + header
    //Файлы:
    //Обычно медиа файлы хранятся на сторонних хранилищах/сервисах со своим API

    //Новое занятие:
    //Документирование API/Документация к API нашего проекта
    //Как фронтендерам узнать где какие методы вызывать, где они находятся (url, port)
    //Каждый метод нашего REST контроллера должен быть задокументирован, модели тоже должны быть задокументированы
    //Можно наши типо тестики в Insomnia экспортировать в JSON, но на самом деле описания методов и вообще всего не
    //будет, конечно мы можем написать документацию вручную, но это долго +
    //каждое изменение какого-то параметра в контроллере, добавление нового поля в нашу сущность
    //надо идти в документацию и опять вручную все изменять
    //Есть сервис Swager, который автоматически будет генерировать нам документацию, которая имеет
    //визуальный UI вид
    //В Petsotre swagger - магазин Swaggr-а/тестовая документация Swagger, который показывает как визуально будет и
    //пользоваться ли нам этим сервисом
    //выглядеть документация
    //Там есть коллекции ресурсов - это контроллеры с методами этого контроллера
    //Так же есть модели - это наши entity
    //При каждом запуске нашего приложения Swagger генерирует документацию и для нее есть
    //отдельный url - localhost:8080/swagger-ui/index.html.Его можно поменять
    //Получается для Swagger-а генерируется отдельный контроллер, который возвращает HTML
    //Но так как мы запускаем проект на локальной машине - для swagger-а толку ноль. Поэтому,
    //когда мы минимально написали наше приложение - мы разворачиваем его на отдельном сервере в
    //глобальном интернете и даем url на проект
    //Эта документация имеет смысл только для ребят, которые будт пользоваться нашим API
    //Swagger - сервис для генерации документации к нашему API (и управления им)/набор инструментов
    //связанных с документацией по API. Он подходит для многих языков программирования
    //Файл с документацией - swagger.json
    //В Maven много неофициальных Spring Boot стартеров
    //Baeldung.com - более-менее авторитетный сайт. И там говорится, что Springfox - основная зависимость Swagger for Spring
    //Swagger может работать и в оффлайн режиме (надо его скачать). Но в продакшене Swagger не нужен и его нужно удалить
    //У Swagger основная аннотация API. Все с префиксом API - аннотации Swagger-а
    //Что делать, если мы хотим использовать Swagger или что-то другое в новых версиях Spring
    //Есть такой проект/библиотека под названием Springdoc и его даже лучше использовать со Spring, чем Swagger
    //Поэтому, если мы хотим подключить API документацию к нашему проекту, то лучше использовать не напрямую Springfox
    //со Swagger, а Springdoc со Swagger

    //Тема сегодняшнего занятия - Spring Data JPA (Один из самых крутых модулей, которые Spring нам предоставляет
    // из коробки)(Модуль, который даже иногда является основным аргументом использования Spring)
    //Если у нас проект Spring Data и в этом проекте сосредоточены подпроекты, которые упрощают работу с какой-то БД в
    // конексте фреймворка спринг
    //Spring Data JPA - упрощает работу с JPA(спецификация ORM, которая использует внутри себя
    //какого-то имплементатора - например Hibernate)
    //Spring Data JPA - библиотека, которая работает поверх Hibernate+JPA и еще круче упрощает работу с этим всем
    //Практически любой проект Spring Data использует понятие репозиториев.
    //Репозитории в контексте Spring Data - некие интерфейсы, которые мы будем использовать внутри наших сервисов.
    // Реализацию этих интерфейсов писать не нужно. Они есть внутри модулей, которые мы подключаем из Spring Data.
    // Наша задача, чтобы написать слой Dao - нам просто нужно написать стандартные интерфейсы. Нам не нужно теперь
    // создавать имплементации.
    //Когда мы в Hibernate or JPA хотели что-то определенное достать, нам приходилось писать
    //запросы. Spring Data JPA - поддерживает QueryDSL (Domain Specific Language) - такой язык,
    //который программисты могут сами для себя разрабатывать (для какого-то модуля), чтобы он чем-то обрабатывался и
    // какие-то действия делал. Например мы пишем библиотеку и хотим, чтобы с этой библиотекой пользователь мог общаться
    // через набор каких-то разных команд и в методы их не сконвертировать - мы придумываем свой собственный язык
    // (с помощью DSL), который будет что-то выполнять
    //QueryDSL - язык, с помощью которого мы можем описывать запросы к БД, только под видом названия методов в интерфейсе.
    //Если нам это не подходит и мы хотим использовать обычный SQL запрос, то мы используем аннотацию @Query
    //Spring Data JPA работает как в Spring Boot, так и в обычном Spring Framework
    //
    //Когда мы разрабатываем приложение на своем ПК мы не всегда устанавливаем MySQL, PostgreSQL на ПК. На начальном
    // этапе это не всегда нужно. Поэтому существуют InMemory БД - она не установлена на ПК, она запущена в памяти
    // (как Tomcat в SpringBoot). Одна из этих БД - h2
    //К h2 нам даже не надо конфигурировать url, username, password, диалект для БД, так как Data JPA автоматически
    // обнаружит БД и будет использовать дефолтные настройки для присоединения к ней
    //У h2 есть собственная консоль, где мы можем смотреть созданные таблицы, делать запросы по адресу: localhost:8080/h2-console
    //
    //Чтобы общаться с реляционной БД у нас в распоряжении обычный SQL. Hibernate, JPA, ORM, Spring Data JPA - это все нужно
    // для того, чтобы упростить разработку. Но в результате, что бы мы не использовали в мире реляционных БД у нас
    // всегда будет в конце будет обычный SQL запрос. А если запрос очень сложный, то нам просто не обойтись без стандартного SQL
    // От JPA и Hibernate в Spring Data JPA остается только стандартный маппинг (@Entity, @Table, @Id, @GeneratedValue, @OneToOne, @OneToMany)
    //Мы не пишем Dao реализации просто используем интерфейсы-репозитории
    //Получается Spring Data JPA работает поверх JPA работает поверх Hibernate работает поверх JDBC - слои абстракции
}
