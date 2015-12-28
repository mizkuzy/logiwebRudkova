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
<div>
    <form name="add_van_form" action="addVan" method="post">
        Registration Number<input name="vanNumber" type="text" required><br>
        Drivers capacity<input name="driversAmount" type="text" required><br>
        Capacity (ton)<input name="capacity" type="text" required><br>
        <input type="submit" title="add" value="ADD"><br>
    </form>
</div>
</body>
</html>
