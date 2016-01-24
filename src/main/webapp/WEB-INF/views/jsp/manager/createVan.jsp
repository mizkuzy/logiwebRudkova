<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../header_menu.jsp">
    <jsp:param name="title" value="Add new van - LOGIWEB"/>
</jsp:include>

<div class="container">
    <form:form class="form-horizontal" role="form" name="add_van_form" action="addVan" method="post">
        <div class="form-group">
            <div class="col-sm-5">
                <b>Registration Number:</b><br>
                <input type="text" name="vanNumber" class="form-control" placeholder="Registration Number"
                       title="pattern = XX12345" pattern="[A-Z]{2}\d{5}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-5">
                <b>Drivers capacity:</b><br>
                <input type="text" name="driversAmount" class="form-control" placeholder="Drivers capacity" required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-5">
                <b>Capacity (ton):</b><br>
                <input type="text" name="capacity" class="form-control" placeholder=">Capacity" required>
            </div>
        </div>
        <div class="form-group">
            <div class="checkbox" aria-required="true">
                <b>RouteLabel:</b><br>
                <label>
                    <input type="checkbox" name="routLabel" value="yellow"> yellow
                </label>
                <label>
                    <input type="checkbox" name="routLabel" value="green"> green
                </label>
                <label>
                    <input type="checkbox" name="routLabel" value="purple"> purple
                </label>
                <label>
                    <input type="checkbox" name="routLabel" value="blue"> blue
                </label>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-5">
                <button type="submit" class="btn btn-success" title="add new van" value="ADD">ADD</button>
            </div>
        </div>
    </form:form>
</div>
</div>
</div>

<script>
    jQuery(function () {
        var max = 1;
        var checkboxes = $('input[type="checkbox"]');

        checkboxes.change(function () {
            var current = checkboxes.filter(':checked').length;
            checkboxes.filter(':not(:checked)').prop('disabled', current >= max);
        });
    });
</script>
</body>
</html>
