<%--
  Created by IntelliJ IDEA.
  User: Герман
  Date: 01.07.2022
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>

<%--
"_" показывает, что это не полноценная JSP страница, а фрагмент кода, который мы можем вставлять, где нам понадобится
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--надо подкючать в каждой JSP--%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
          crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <c:if test="${sessionScope.currentUser == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="/reg">Registration</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/auth">Authorization</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.currentUser != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="/calc">Calculator</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/logout">Logout</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/findHistory">My History</a>
                    </li>
                </c:if>
                <%--<%
                    if (session.getAttribute("currentUser") == null) {
                        out.println(" <li class=\"nav-item\">\n" +
                                "                    <a class=\"nav-link\" href=\"/reg\">Registration</a>\n" +
                                "                </li>\n" +
                                "                <li class=\"nav-item\">\n" +
                                "                    <a class=\"nav-link\" href=\"/auth\">Authorization</a>\n" +
                                "                </li>");
                    } else {
                        out.println("  <li class=\"nav-item\">\n" +
                                "                    <a class=\"nav-link\" href=\"/calc\">Calculator</a>\n" +
                                "                </li>\n" +
                                "                <li class=\"nav-item\">\n" +
                                "                    <a class=\"nav-link\" href=\"/logout\"\n" +
                                "                    >Logout</a>\n" +
                                "                </li>" +
                                "                 <li class=\"nav-item\">\n" +
                                "                    <a class=\"nav-link\" href=\"/findHistory\">My history</a>\n" +
                                "                </li>\n");
                    }
                %>--%>

            </ul>
            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
</body>
</html>
