<%@ page import="entities.Van" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=8,9,10" >
    <meta charset="UTF-8">
    <title>Фуры - LOGIWEB</title>
   <!-- <link href="css/bootstrap.min.css" rel="stylesheet">-->
</head>
<body>
<div>
    <form name="home" action="main_manager.jsp">
        <input type="submit" value="ДОМОЙ">
    </form>
</div>
<table class="table-bordered" border="2" width="2" cellspacing="2" cellpadding="2">
    <caption>СПИСОК ФУР</caption>
    <tr>
        <th>
            Рег. номер
        </th>
        <th>
            Размер смены водителей
        </th>
        <th>
            Вместимость (тонн)
        </th>
        <th>
            Состояние
        </th>
        <th>
            Статус
        </th>
        <td>
            Настройки
        </td>
    </tr>
    <% ArrayList<Van> vans = (ArrayList<Van>) request.getSession().getAttribute("vansList");
        for (int i = 0; i < vans.size(); i++) {
    %>
    <tr>
        <td>
            <%=vans.get(i).getVanNumber()%>
        </td>
        <td>
            <%=vans.get(i).getDriversAmount()%>
        </td>
        <td>
            <%=vans.get(i).getCapacity()%>
        </td>
        <td>
            <%=vans.get(i).getStateVan()%>
        </td>
        <td>
            <%=vans.get(i).getStatusVan()%>
        </td>
        <td>
            <div>
                <form name="edit" action="/EditAndDeleteVan" method="get">

                    <input type="submit" title="Редактировать фуру" value="РЕДАКТИРОВАТЬ">
                </form>
            </div>
            <div>
                <a href="/EditAndDeleteVan?id=<%=vans.get(i)%>"><span>${language.JSP_CONTRACTS_ACTION_CHANGE}</span> <br></a>
                <form name="delete" action="/EditAndDeleteVan" method="post">
                    <input type="submit" title="Удалить фуру" value="УДАЛИТЬ">
                </form>
            </div>
        </td>
    </tr>
    <%} %>
</table>

<div>
    <form name="add" action="addVan.jsp" method="get">
        <input type="submit" title="Добавить новую фуру" value="НОВАЯ ФУРА">
    </form>
</div>
</body>
</html>