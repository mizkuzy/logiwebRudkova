<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authentication - LOGIWEB</title>
</head>
<body>

<c:if test="${not empty param.error}">
    ERROR VALIDATION
</c:if>
<form method="POST" action="<c:url value="/j_spring_security_check" />">
    <table>
        <tr>
            <td align="right"></td>
            <td><input type="text" name="username" /></td>
        </tr>
        <tr>
            <td align="right"></td>
            <td><input type="password" name="password" /></td>
        </tr>
        <%--<tr>
            <td align="right"><spring:message code="label.remember" /></td>
            <td><input type="checkbox" name="_spring_security_remember_me" /></td>
        </tr>--%>
        <tr>
            <td colspan="2" align="right"><input type="submit" value="Login" />
                <%--<input type="reset" value="Reset" /></td>--%>
        </tr>
    </table>
</form>
</body>