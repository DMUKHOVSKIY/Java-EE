package by.tms.practice24;

public class Class {
    //На прошлом занятии мы подключали базовую аутентификацию и настраивали Spring Security в Web проекте
    //На этом мы будем подключать защиту к REST проекту
    //В базовой защите, которую мы подключали на прошлом занятии мы конфигурировали Security двумя аспектами:
    //1)Защита самих урлов
    //2)Настройка аутентификации. Так как Spring Security берет на себя эту обязанность. Он может сам пойти в БД,
    //достать какого-то пользователя и потом его засунуть в Security context. После чего будет считаться, что
    // пользователь вошел в систему (если все данные правильные)
    //Если мы хотим защитить свой рест проект. Чтобы на нее нельзя было отправлять запросы не будучи авторизованным
    //или просто чтобы идентифицировать себя. Но у нас нет сессии. Мы можем воспользоваться сторонним Authentication
    // Server (например  google). Мы вводим даннные и пароль и получаем от сервера JWT обратно на наше клиентское
    // приложение (IOS, Android), где этот токен запоминается и при каждом последовательном запросе пользователя на
    // Application Server будет отсылаться гуглововский токен. В этом токене содержится какая-то полезная информация,
    // с помощью которой мы авторизируем пользователя (мы идем в гугл и проверяем/валидируем)
    //JWT хранится в закодированном виде. У него есть хэдер (header), данные(payload:data) - самое главное(внутри
    // хранит  токен), сигнатура(signature).
    //Если мы хотим иметь Authentification Server на нашем API, то мы сами должны генерировать JWT токены.
    //Токен живет какое-то время. Стандартное время - 1 час. Так как если токен лежит у клиента на видном месте - его
    // могут украсть. Поэтому токен надо рефрешить. Новый токен должен отличаться, но данные в нем  хранятся одни и те
    // же, кроме даты создания токена и даты истечения срока действия этого токена
    //Основные пункты обеспечения безопасного доступа к API:
    //● Ограничение кол-ва запросов для пользователя
    //● Проверка входных данных
    //● Не раскрывайте параметры в URL
    //Конфигурация Spring Security в JWT:
    //Java-класс, который является Security конфигурацией, должен быть наследником класса WebSecurityConfigAdapter
    //JWt - JSON Web Token. это открытый стандарт для создания токенов доступа, основанный на формате JSON. Как правило,
    // используется для передачи данных для аутентификации в клиент-серверных приложениях
}
