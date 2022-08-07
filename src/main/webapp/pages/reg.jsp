<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="/user/registration" method="post">
<%--
    надо указывать поля в точности, как и в классе User
--%>
    <input type="text" name="name" placeholder="Name">
    <input type="text" name="username" placeholder="username">
    <input type="text" name="password" placeholder="Password">
    <button>Submit</button>
</form>
</body>
</html>
