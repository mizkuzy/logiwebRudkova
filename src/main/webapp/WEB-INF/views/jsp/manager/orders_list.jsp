<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header_menu.jsp">
    <jsp:param name="title" value="Orders - LOGIWEB"/>
</jsp:include>

<table class="table table-hover my-settings">
    <caption>Orders list</caption>
    <c:forEach items="${ordersPROCESS}" var="order">
        <tr class="td-sp">
            <th>Order number:</th>
            <td>${order.number}</td>
            <td>
                <form name="finish" action="finishOrder" method="get">
                    <input type="hidden" name="selectedOrder" value="${order.idOrder}"/>
                    <button class="btn-success" type="submit" title="Finish" value="FINISH">FINISH</button>
                </form>
            </td>
        </tr>
        <tr class="td-sp">
            <th>Van number:</th>
            <td>${order.van}</td>
            <td></td>
        </tr>
        <tr class="td-sp">
            <th>Drivers list:</th>
            <td>
                <c:forEach items="${order.drivers}" var="driver">
                    ${driver}<br/>
                </c:forEach>
            </td>
            <td></td>
        </tr>
        <tr class="td-sp">
            <th>Routs points:</th>
            <td>
                <table>
                    <tr>
                        <th>GOOD</th>
                        <th>CITY FROM</th>
                        <th>CITY TO</th>
                    </tr>
                    <c:forEach items="${order.requests}" var="request">
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
                        <tr>
                            <td></td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
            <td></td>
        </tr>
        <tr>
            <td></td>
        </tr>
    </c:forEach>
</table>
</div>
</div>

</body>
</html>
