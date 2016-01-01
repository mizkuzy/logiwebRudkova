<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.tsystems.logiweb.entities.Request" %><%--
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Current requests - LOGIWEB</title>
</head>
<body>

<h1>Current requests</h1>

<div>
    <form name="home" action="main_manager" method="get">
        <input type="submit" value="HOME">
    </form>
</div>

<div>
    <form action="create_order" method="get">
        <input type="hidden" name="currentRoutLabel" value="yellow"/>
        YELLOW ROUT <br/>
        WAIT:${yellowRoutRequestsSize} requests<br/>

        <input type="submit" title="Handle" value="HANDLE">
    </form>
</div>

<div>
    <form action="create_order" method="get">
        <input type="hidden" name="currentRoutLabel" value="green"/>
        GREEN ROUT <br/>
        WAIT:${greenRoutRequestsSize} requests <br/>
        <input type="submit" title="Handle" value="HANDLE">
    </form>
</div>
<div>
    <form action="create_order" method="get">
        <input type="hidden" name="currentRoutLabel" value="purple"/>
        PURPLE ROUT <br/>
        WAIT: ${purpleRoutRequestsSize} requests <br/>
        <input type="submit" title="Handle" value="HANDLE">
    </form>
</div>
<div>
    <form action="create_order" method="get">
        <input type="hidden" name="currentRoutLabel" value="blue"/>
        BLUE ROUT <br/>
        WAIT:${blueRoutRequestsSize} requests <br/>
        <input type="submit" title="Handle" value="HANDLE">
    </form>
</div>
</body>
</html>