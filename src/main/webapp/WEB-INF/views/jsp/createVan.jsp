<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new van - LOGIWEB</title>
</head>
<body>
<div>
    <form name="home" action="main_manager" method="get">
        <input type="submit" value="HOME">
    </form>
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
<div>
    <form name="add_van_form" action="addVan" method="post">
        Registration Number<input name="vanNumber" type="text" required><br>
        Drivers capacity<input name="driversAmount" type="text" required><br>
        Capacity (ton)<input name="capacity" type="text" required><br>
        RouteLabel
        <input type="checkbox" name="routLabel" value="yellow">yellow
        <input type="checkbox" name="routLabel" value="green">green
        <input type="checkbox" name="routLabel" value="purple">purple
        <input type="checkbox" name="routLabel"value="blue">blue

        <input type="submit" title="add" value="ADD"><br>
    </form>
</div>
</body>
</html>
