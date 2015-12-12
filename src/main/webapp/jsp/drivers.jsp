<%@ page import="entities.Driver" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Lucy
  Date: 27.11.2015
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Водители - LOGIWEB</title>
</head>
<body>
<div>
    <form name="home" action="main_manager.jsp">
        <input type="submit" value="ДОМОЙ">
    </form>
</div>

<table border="2" width="2" cellspacing="2" cellpadding="2">
    <caption>СПИСОК ВОДИТЕЛЕЙ</caption>
    <tr>
        <th>
            Личный номер
        </th>
        <th>
            Имя
        </th>
        <th>
            Фамилия
        </th>
        <th>
            Отработано (часы)
        </th>
        <th>
            Состояние
        </th>
        <td>
            Статус
        </td>
        <td>
            Настройки
        </td>
    </tr>
    <% ArrayList<Driver> drivers = (ArrayList<Driver>) request.getSession().getAttribute("drivers");
        for (Driver driver : drivers) {
    %>
    <tr>
        <td>
            <%=driver.getDriverID().getPersonalNumber()%>
        </td>
        <td>
            <%=driver.getName()%>
        </td>
        <td>
            <%=driver.getSurname()%>
        </td>
        <td>
            <%=driver.getWorkHours()%>
        </td>
        <td>
            <%=driver.getState()%>
        </td>
        <td>
            <%=driver.getStatusDriver()%>
        </td>
        <td>
            <div>
                <form name="edit" action="/EditAndDeleteDriver" method="get">
                    <input type="submit" title="Редактировать водителя" value="РЕДАКТИРОВАТЬ">
                </form>
            </div>
            <div>
                <form name="delete" action="" method="get">
                    <input type="submit" title="Удалить водителя" value="УДАЛИТЬ">
                </form>
            </div>
        </td>
    </tr>
    <%} %>
</table>

<div>
    <form name="add" action="addDriver.jsp" method="get">
        <input type="submit" title="Добавить нового водителя" value="НОВЫЙ ВОДИТЕЛЬ">
    </form>
</div>
</body>
</html>