<%@ page import="java.time.LocalDateTime" %><%--
  Created by IntelliJ IDEA.
  User: Герман
  Date: 10.06.2022
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>

<%--JSP ЯВЛЯЕТСЯ СЕРВЛЕТОМ!!!--%>

<%--JSP-Java Server Pages - спецификация, технология позволяющая создатвать динамические веб сраницы
Шаблонизация - прогаммное обеспечение, позволяющее использовать html-шаблоны для генерации конечных html-страниц--%>

<%--SSR - Server-Side Rendering--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%--(page-это директива)/некая информация, которую подключают к нашей jsp страничке--%>
<html>
<head>
    <title>Hello</title>
</head>
<body>

<%!
    int f = 10; /*это уже не переменная, а поле(field)/f-глобальная/(конструкция называется declaration) declaration
    можно объявлять в любом месте и она будет видна,
    в отличие от переменной "a"(императивный стиль(сверху-вниз))*/

    double sum(double a, double b) {
        return a + b;
    }
%>

<ul>
    <% /*скриплет - блок, где можно писать джава код*/
        for (int i = 0; i < 10; i++) {
            out.println("<li>Test" + i + "</li>");
        }
        int a = 10; /*объявляем переменную здесь*/
    %>

    <%
        /*в этом блоке переменная "a" доступна (все скриплеты это один метод/одно единое простарнство)*/
        out.println(sum(a,10));
    %>
</ul>

<h1>Hello ${username}</h1> <%--специальный язык выражений(EL) или интерполяция/ищет во всех скоупах снизу вверх
(page scope->request scope->session scope->application scope)--%>
<h1>Hello ${requestScope.get("username")}</h1> <%--если ошибка в запросе, то просто ничего не выведет(null не выводит)--%>
<h1>Hello ${requestScope.username}</h1> <%--ищет конкретно в requestScope--%>
<h1>Hello <%=request.getAttribute("username")%></h1> <%--экспрешен(по факту print)--%>
<h1>Hello <%=LocalDateTime.now()%></h1>
<h1>Hello <%=2+2%></h1> <%--ответ - 4--%>
</body>
</html>


