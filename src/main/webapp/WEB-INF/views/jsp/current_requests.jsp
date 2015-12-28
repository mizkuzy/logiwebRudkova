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
        <% ArrayList<Request> yellowRequests = (ArrayList<Request>) request.getSession().getAttribute("yellowRoutRequests");%>
        YELLOW ROUT <br/>
        WAIT:<%=yellowRequests.size()%>  requests<br/>
        <input type="submit" title="Handle" value="HANDLE">
    </form>
</div>

<div>
    <form action="create_order" method="get">
        <input type="hidden" name="currentRoutLabel" value="green"/>
        <% ArrayList<Request> greenRequests = (ArrayList<Request>) request.getSession().getAttribute("greenRoutRequests");%>
        GREEN ROUT <br/>
        WAIT: <%=greenRequests.size()%> requests <br/>
        <input type="submit" title="Handle" value="HANDLE">
    </form>
</div>
<div>
    <form action="create_order" method="get">
        <input type="hidden" name="currentRoutLabel" value="purple"/>
        <% ArrayList<Request> purpleRequests = (ArrayList<Request>) request.getSession().getAttribute("purpleRoutRequests");%>
        PURPLE ROUT <br/>
        WAIT: <%=purpleRequests.size()%> requests <br/>
        <input type="submit" title="Handle" value="HANDLE">
    </form>
</div>
<div>
    <form action="create_order" method="get">
        <input type="hidden" name="currentRoutLabel" value="blue"/>
        <% ArrayList<Request> blueRequests = (ArrayList<Request>) request.getSession().getAttribute("blueRoutRequests");%>
        BLUE ROUT <br/>
        WAIT: <%=blueRequests.size()%> requests <br/>
        <input type="submit" title="Handle" value="HANDLE">
    </form>
</div>
</body>
</html>