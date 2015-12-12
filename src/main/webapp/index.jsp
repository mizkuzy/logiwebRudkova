<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Аутентификация - LOGIWEB</title>
</head>
<body>
<form role="form" name="login_form" action="/LoginServlet" method="post">
    <div class="form-group">
        <label for="email">Пользователь</label>
        <input name="user" type="email" class="form-control" id="email" placeholder="Введите email">
    </div>
    <div class="form-group">
        <label for="pass">Пароль</label>
        <input name="pswd" type="password" class="form-control" id="pass" placeholder="Пароль">
    </div>
    <button type="submit" class="btn btn-success">Войти</button>
</form>
</body>
</html>
