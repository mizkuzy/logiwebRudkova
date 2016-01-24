<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../header_menu.jsp">
    <jsp:param name="title" value="Create driver - LOGIWEB"/>
</jsp:include>

    <div class="container">
        <form:form class="form-horizontal" role="form" name="add_driver_form" action="addDriver" method="post">
            <div class="form-group">
                <div class="col-sm-5">
                    <b>Name:</b><br>
                    <input type="text" name="driverName" class="form-control" placeholder="Name">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-5">
                    <b>Surname:</b><br>
                    <input type="text" name="driverSurname" class="form-control" placeholder="Surname">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-5">
                    <b>E-mail:</b><br>
                    <input type="email" name="email" class="form-control" placeholder="e-mail">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-5">
                    <b>Password:</b><br>
                    <input type="password" name="password" class="form-control" placeholder="Password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-5">
                    <button type="submit" class="btn btn-success" title="add new driver" value="ADD">ADD</button>
                </div>
            </div>
        </form:form>
    </div>

</div>
</div>
</body>
</html>