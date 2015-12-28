<%--
  Created by IntelliJ IDEA.
  User: Lucy
  Date: 29.11.2015
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOGIWEB</title>
</head>
<body>
<h1>DRIVER'S APP</h1>

<div class="form-group">
    <table>
        <tr>
            <td>Personal number: &nbsp;</td>
            <td><%=request.getSession().getAttribute("personalNumber")%></td>
        </tr>
    </table>
</div>
</body>
</html>
