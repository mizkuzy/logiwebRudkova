<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Authentication - LOGIWEB</title>

    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/common.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<%--<c:if test="${not empty param.error}">
    ERROR VALIDATION
</c:if>--%>

<div class="container">
    <div class="col-sm-6">
        <h1 class="header">Please sign in</h1>

        <form class="form-horizontal" role="form" method="POST" action="/j_spring_security_check">
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">Email</label>

                <div class="col-sm-10">
                    <input type="email" name="username" class="form-control" id="inputEmail3" placeholder="Email">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">Пароль</label>

                <div class="col-sm-10">
                    <input type="password" name="password" class="form-control" id="inputPassword3"
                           placeholder="Password">
                </div>
            </div>
            <%--<div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> Запомнить меня
                        </label>
                    </div>
                </div>
            </div>--%>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-success">Войти</button>
                </div>
            </div>
        </form>
    </div>
</div>

</body>

</html>


<%--
<form class="form-signin" role="form" method="POST" action="<c:url value="/j_spring_security_check" />">

    <table>
        <tr>
            <td align="right"></td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td align="right"></td>
            <td><input type="password" name="password"/></td>
        </tr>
        &lt;%&ndash;<tr>
            <td align="right"><spring:message code="label.remember" /></td>
            <td><input type="checkbox" name="_spring_security_remember_me" /></td>
        </tr>&ndash;%&gt;
        <tr>
            <td colspan="2" align="right"><input type="submit" value="Login"/>
                &lt;%&ndash;<input type="reset" value="Reset" /></td>&ndash;%&gt;
        </tr>
    </table>
</form>--%>
