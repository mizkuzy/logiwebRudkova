<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../header_menu.jsp">
    <jsp:param name="title" value="Edit van - LOGIWEB"/>
</jsp:include>

<div class="container">
    <form:form class="form-horizontal" role="form" name="edit_van_form" action="editVan" method="post">
        <div class="form-group">
            <div class="col-sm-5">
                <b>Registration Number:</b><br>
                <input type="text" name="vanNumber" class="form-control" placeholder="Registration Number"
                       value="${selectedVan.vanNumber}" pattern="[A-Z]{2}\d{5}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-5">
                <b>Drivers capacity:</b><br>
                <input type="text" name="driversAmount" class="form-control" placeholder="Drivers capacity"
                       value="${selectedVan.driversAmount}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-5">
                <b>Capacity (ton):</b><br>
                <input type="text" name="capacity" class="form-control" placeholder="Capacity"
                       value="${selectedVan.capacity}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-5">
                <button type="submit" class="btn btn-success" title="edit van" value="EDIT">EDIT</button>
            </div>
        </div>
    </form:form>
</div>

</div>
</div>
</body>
</html>
