<%@ page import="ru.tsystems.logiweb.entities.Order" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Orders list - LOGIWEB</title>
</head>
<body>
<div>
    <form name="home" action="main_manager" method="get">
        <input type="submit" value="HOME">
    </form>
</div>
<div>
    <table border="2" width="2" cellspacing="2" cellpadding="2">
        <caption><h1>ORDERS LIST</h1></caption>
        <tr>
            <th>
                NUMBER
            </th>
            <th>
                VAN
            </th>
            <!--   <th>
                   ВОДИТЕЛИ
               </th>-->
            <th>
                GOODS
            </th>
            <th>
                CITY1
            </th>
            <th>
                CITY2
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
                <%--<%=o.getRequest().getGoodForRequest().getName()%>--%>
                <%=o.getRequests() %>
            </td>
            <td>
                <%--<%=o.getRequest().getRoutForRequest().getCity1()%>--%>
            </td>
            <td>
                <%--<%=o.getRequest().getRoutForRequest().getCity2()%>--%>
            </td>
        </tr>

        <% } %>
    </table>

</div>


</body>
</html>
