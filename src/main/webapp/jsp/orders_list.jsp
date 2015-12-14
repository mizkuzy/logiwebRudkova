<%@ page import="ru.tsystems.logiweb.entities.Order" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Список заказов - LOGIWEB</title>
</head>
<body>
<div>
    <form name="home" action="main_manager.jsp">
        <input type="submit" value="ДОМОЙ">
    </form>
</div>
<div>
    <table border="2" width="2" cellspacing="2" cellpadding="2">
        <caption><h1>СПИСОК НЕВЫПОЛНЕННЫХ ЗАКАЗОВ</h1></caption>
        <tr>
            <th>
                НОМЕР
            </th>
            <th>
                ФУРА
            </th>
            <!--   <th>
                   ВОДИТЕЛИ
               </th>-->
            <th>
                ТОВАР
            </th>
            <th>
                ПУНКТ ОТПРАВЛЕНИЯ
            </th>
            <th>
                ПУНКТ НАЗНАЧЕНИЯ
            </th>
        </tr>
        <% ArrayList<Order> ordersPROCESS = (ArrayList<Order>) request.getSession().getAttribute("ordersPROCESS");
            for (Order o : ordersPROCESS) {
        %>
        <tr>
            <td>
                <%=o.getNumber()%>
            </td>
            <td>
                <%=o.getVan().getVanNumber()%>
            </td>
            <!--  <td>
                <table>
                    <tr>
                        <td>driver1</td>
                        <td>driver2</td>
                    </tr>
                </table>
                <%--ЗДЕСЬ ПОЛУЧИТЬ СПИСОК ВОДИТЕЛЕЙ--%>
            </td>-->
            <td>
                <%=o.getRequest().getGoodForRequest().getName()%>
            </td>
            <td>
                <%=o.getRequest().getRoutForRequest().getCity1()%>
            </td>
            <td>
                <%=o.getRequest().getRoutForRequest().getCity2()%>
            </td>
        </tr>

        <% } %>
    </table>

</div>


</body>
</html>
