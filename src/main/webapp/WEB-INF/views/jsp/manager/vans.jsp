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
    <title>Vans - LOGIWEB</title>
</head>
<body>

<h1> Vans </h1>

<div>
    <form name="home" action="main_manager" method="get">
        <input type="submit" value="HOME">
    </form>
</div>
<table class="table-bordered" border="2" width="2" cellspacing="2" cellpadding="2">
    <caption>VANS LIST</caption>
    <tr>
        <th>
            Number
        </th>
        <th>
            Drivers amount
        </th>
        <th>
            Capacity (ton)
        </th>
        <th>
            State
        </th>
        <th>
            Work status
        </th>
        <td>

        </td>
    </tr>

    <c:forEach items="${vansList}" var="van" varStatus="theCount">
        <tr>
            <td>
                    ${van}
            </td>
            <td>
                    ${van.driversAmount}
            </td>
            <td>
                    ${van.capacity}
            </td>
            <td>
                    ${van.stateVan}
            </td>
            <td>
                    ${van.statusVan}
            </td>
            <td>
                <div>
                    <form name="edit" action="getVanForEdit" method="get">
                        <input type="hidden" name="selectedVan" value="${van.idVan}"/>
                        <input type="submit" title="Edit" value="EDIT">
                    </form>
                </div>
                <div>
                    <form name="delete" action="deleteVan" method="get">
                        <input type="hidden" name="selectedVan" value="${van.idVan}"/>
                        <input type="submit" title="Delete" value="DELETE">
                    </form>
                </div>
            </td>
        </tr>
    </c:forEach>

</table>

<div>
    <form name="add" action="createVan" method="get">
        <input type="submit" title="Add new van" value="NEW">
    </form>
</div>
</body>
</html>