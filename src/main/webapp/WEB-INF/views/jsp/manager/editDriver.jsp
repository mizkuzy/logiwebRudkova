<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../header_menu.jsp">
    <jsp:param name="title" value="Edit driver - LOGIWEB"/>
</jsp:include>

<div class="container">
    <form:form class="form-horizontal" role="form" name="edit_driver_form" action="editDriver" method="post">
        <div class="form-group">
            <div class="col-sm-5">
                <b>Name:</b><br>
                <input type="text" name="driverName" class="form-control" placeholder="Name"
                       value="${selectedDriver.name}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-5">
                <b>Surname:</b><br>
                <input type="text" name="driverSurname" class="form-control" placeholder="Surname"
                       value="${selectedDriver.surname}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-5">
                <button type="submit" class="btn btn-success" title="edit driver" value="EDIT">EDIT</button>
            </div>
        </div>
    </form:form>
</div>

</div>
</div>

</body>
</html>
