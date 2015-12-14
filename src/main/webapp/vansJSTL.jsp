<%@ page import="ru.tsystems.logiweb.entities.Van" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=8,9,10">
    <meta charset="UTF-8">
    <title>Фуры - LOGIWEB</title>
</head>
<body>
<div>
    <form name="home" action="jsp/main_manager.jsp">
        <input type="submit" value="ДОМОЙ">
    </form>
</div>

<table border="2" width="2" cellspacing="2" cellpadding="2">
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
    <c:forEach var="vansList" items="${vansList}">
        <tr>
            <td>
                <%=vansList.getVanNumber()%>
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
                <div id="${vansList.id}">
                    <form name="edit" action="/EditAndDeleteVan" method="get">

                        <input type="submit" title="Редактировать фуру" value="РЕДАКТИРОВАТЬ">
                    </form>
                </div>
                <div d="${vansList.id}">
                    <form name="delete" action="/EditAndDeleteVan" method="post">
                        <input type="submit" title="Удалить фуру" value="УДАЛИТЬ">
                    </form>
                </div>
            </td>
        </tr>
    </c:forEach>
    </c:forEach>
</table>

<div>
    <form name="add" action="jsp/addVan.jsp" method="get">
        <input type="submit" title="Добавить новую фуру" value="НОВАЯ ФУРА">
    </form>
</div>
</body>
</html>