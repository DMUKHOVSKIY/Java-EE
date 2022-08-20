<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s"
          uri="http://www.springframework.org/tags/form" %><%--при подключении спринг веб MVC уже есть библиотека спринг форм тегов для jsp. Это не JSTL--%>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<s:form method="post" action="/user/registration"
        modelAttribute="newUser"> <%--Из modelAttribute будут тащится все названия филдов--%>
    <s:input path="name" placeholder="Name"/>
    <br>
    <s:errors
            path="name"/> <%--указываем поле, которое возможно привело к ошибке. И выводятся все ошибки, связанные с этим полем (name)--%>
    <br>
    <s:input path="username" placeholder="Username"/>
    <br>
    <s:errors path="username"/>
    <br>
    <s:input path="password" placeholder="Password "/>
    <br>
    <s:errors path="password"/>
    <br>
    <s:button>Submit</s:button>
</s:form>
<%--&lt;%&ndash;<form action="/user/registration" method="post">--%>
<%--&lt;%&ndash;--%>
<%--    надо указывать поля в точности, как и в классе User--%>
<%--&ndash;%&gt;--%>
<%--    <br>--%>
<%--    <p>${name}</p>  &lt;%&ndash;проблема в том, что если ошибок много - их надо всех вывести как-то циклом&ndash;%&gt;--%>
<%--    <input type="text" name="name" placeholder="Name">--%>
<%--    <br>--%>
<%--    <p>${username}</p>--%>
<%--    <input type="text" name="username" placeholder="username">--%>
<%--    <br>--%>
<%--    <p>${password}</p>--%>
<%--    <input type="text" name="password" placeholder="Password">--%>
<%--    <button>Submit</button>--%>
<%--</form>&ndash;%&gt;--%>
</body>
</html>