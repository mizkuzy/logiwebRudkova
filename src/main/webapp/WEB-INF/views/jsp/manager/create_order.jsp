<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../header_menu.jsp">
    <jsp:param name="title" value="Create order - LOGIWEB"/>
</jsp:include>

<table class="table table-hover my-settings">
    <caption>Create order</caption>
    <tr class="td-sp">
        <th>Order number</th>
        <td>${order.number} </td>
    </tr>
    <tr class="td-sp">
        <th>Status</th>
        <td>${order.status}</td>
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
                </c:forEach>
            </table>
        </td>
    </tr>
    <tr class="td-sp">
        <th>Total mass</th>
        <td>${mass}</td>
    </tr>
    <tr>
        <form:form class="form-horizontal" role="form" action="save_order" method="get">
            <td>
                <div class="form-group">
                    <div class="col-sm-5">
                        <b>Choose ${maxCheckboxSelections-1} drivers:</b><br>
                        <c:forEach items="${appropriateDrivers}" var="driver" varStatus="theCount">
                            <input id="driver" type="checkbox" class="checkbox_drivers" name="selectedDriver"
                                   value="${theCount.count}"
                                   max="3">${driver}<br>
                        </c:forEach>
                    </div>
                </div>
            </td>

            <td>
                <div class="form-group">
                    <div class="col-sm-5">
                        <b>Choose van:</b><br>
                        <c:forEach items="${appropriateVans}" var="van" varStatus="theCount">
                            <input id="van" type="checkbox" class="checkbox_vans" name="selectedVan"
                                   value="${theCount.count}"
                                   max="1">${van}<br>
                        </c:forEach>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-5">
                        <button type="submit" class="btn btn-success" title="Save order"
                                value="SAVE ORDER">SAVE ORDER
                        </button>
                    </div>
                </div>
            </td>
        </form:form>
    </tr>
</table>

</div>
</div>

<script>
    jQuery(function () {
        var max = <%=session.getAttribute("maxCheckboxSelections")%>;
        var checkboxes = $('input[type="checkbox"]');

        checkboxes.change(function () {
            var current = checkboxes.filter(':checked').length;
            checkboxes.filter(':not(:checked)').prop('disabled', current >= max);
        });
    });
</script>

</body>
</html>