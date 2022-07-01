<%--
  Created by IntelliJ IDEA.
  User: Герман
  Date: 11.06.2022
  Time: 2:15
  To change this template use File | Settings | File Templates.
--%>

<%--
JSTL - стандартная библиотека тегов для работы в JSP(в JSP есть стандартные синтаксические конструкции: сриплеты(<%...%>), экспрешены(<%=...%>), дикларейшены(<%!...%>), директивы(<%@...%>), Expression language(${...}})
JSTL-стандартная библиотека тегов для JSP; расширяет возможности JSP в принципе добавив туда теги
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--подключаем теги с помощью директивы taglib + нужно придумать какой-то префикс(c)(чтобы не путать просранство имен обычных тегов и пространсво имен тегов JSTL )--%>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %><%--подключаем тег форматирования--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Home</title>
</head>
<body>
<jsp:include page="_header.jsp"/><%--вставляем фрагмент--%>

<%--SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss") создаем simpleDateFormat, передаем туда паттерн
 в каком виде хотим отформатировать дату(hh:mm:ss), после чего вызываем simpleDateFormat.format и передаем туда саму дату.
 new Date - это количество секунд прошедших с полуночи 1 января 1970 года.
String format = simpleDateFormat.format(new Date());


req.setAttribute("testDate", new Date());
--%>
<fm:formatDate value="${testDate}" pattern="HH:MM:SS"/>

<c:if test="true"> <%--без "с" не работает--%>
    <%-- тут помещаем любой HTML--%>
</c:if>

<%--
Тег forEach(var-итеративная переменная(меняется), items-коллекция или массив по которому мы можем итерироваться)
for(String s : list){}
--%>
<c:forEach items="${operations}" var="operation">
    <li class="list-group-item">${operation}</li>
</c:forEach>

<c:if test="${requestScope.result !=null}">
    <%-- тут помещаем любой HTML--%>
</c:if>
</body>
</html>
