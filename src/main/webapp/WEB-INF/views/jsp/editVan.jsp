<%@ page import="ru.tsystems.logiweb.entities.Van" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Van - LOGIWEB</title>
</head>
<body>
<div>
    <form name="home" action="main_manager" method="get">
        <input type="submit" value="HOME">
    </form>
</div>
<div>
    <form name="edit_van_form" action="editVan" method="post">
        <% Van van = (Van) request.getSession().getAttribute("selectedVan");%>
        <br>
        Registration Number<input name="vanNumber" type="text" value="<%=van.getVanNumber()%>" required><br>
        Drivers capacity<input name="driversAmount" type="text" value="<%=van.getDriversAmount()%>" required><br>
        Capacity (ton)<input name="capacity" type="text" value="<%=van.getCapacity()%>" required><br>
        <input type="submit" title="edit" value="EDIT"><br>
    </form>
</div>
</body>
</html>
