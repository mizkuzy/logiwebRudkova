<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authentication - LOGIWEB</title>
</head>
<body>
<form role="form" name="login_form" action="/static/spring_security_check" method="post">
    <div class="form-group">
        <label for="email">E-mail</label>
        <input name="username" type="email" class="form-control" id="email" placeholder="Type email">
    </div>
    <div class="form-group">
        <label for="pass">Password</label>
        <input name="password" type="password" class="form-control" id="pass" placeholder="Password">
    </div>
    <button type="submit" class="btn btn-success">Log in</button>
</form>
</body>
</html>
