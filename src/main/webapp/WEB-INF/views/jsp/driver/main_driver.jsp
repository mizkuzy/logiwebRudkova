<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOGIWEB</title>
</head>
<body>
<h1>DRIVER'S APP</h1>

<div>
    <h3> Hello, ${driverName}</h3>
</div>

<table>
    <tr>
        <td>Personal number</td>
        <td>${driverPersonalNumber}</td>
    </tr>
    <tr>
        <td> Co-Drivers</td>
        <td>
            <c:forEach items="${order.drivers}" var="driver">
                ${driver}<br/>
            </c:forEach>
        </td>
    </tr>
    <tr>
        <td>Van</td>
        <td>${currentOrder.van}</td>
    </tr>
    <tr>
        <td>Order</td>
        <td> ${currentOrder.number} </td>
    </tr>
    <tr>
        <td>Routs points</td>
        <td>
            <table cellspacing="2" cellpadding="2">
                <tr>
                    <th>GOOD</th>
                    <th>CITY FROM</th>
                    <th>CITY TO</th>
                </tr>
                <c:forEach items="${currentOrder.requests}" var="request">
                    <tr>
                        <td>
                                ${request.goodForRequest.name}
                        </td>
                        <td>
                                ${request.routForRequest.city1}
                        </td>
                        <td>
                                ${request.routForRequest.city2}
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>

</body>
</html>
