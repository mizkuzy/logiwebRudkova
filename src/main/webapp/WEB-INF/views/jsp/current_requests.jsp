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
    <form name="pick_yellow" action="/PickYellowRoutServlet" method="get">
        <% ArrayList<Request> yellowRequests = (ArrayList<Request>) request.getSession().getAttribute("yellowRoutRequests");%>
        YELLOW ROUT <br/>
        WAIT:<%=yellowRequests.size()%>  requests<br/>
        <input type="submit" title="Handle" value="HANDLE">
    </form>
</div>

<div>
    <form name="pick_green" action="" method="get">
        <% ArrayList<Request> greenRequests = (ArrayList<Request>) request.getSession().getAttribute("greenRoutRequests");%>
        GREEN ROUT <br/>
        WAIT: <%=greenRequests.size()%> requests <br/>
        <input type="submit" title="Handle" value="HANDLE">
    </form>
</div>
<div>
    <form name="pick_purple" action="" method="get">
        <% ArrayList<Request> purpleRequests = (ArrayList<Request>) request.getSession().getAttribute("purpleRoutRequests");%>
        PURPLE ROUT <br/>
        WAIT: <%=purpleRequests.size()%> requests <br/>
        <input type="submit" title="Handle" value="HANDLE">
    </form>
</div>
<div>
    <form name="pick_blue" action="" method="get">
        <% ArrayList<Request> blueRequests = (ArrayList<Request>) request.getSession().getAttribute("blueRoutRequests");%>
        BLUE ROUT <br/>
        WAIT: <%=blueRequests.size()%> requests <br/>
        <input type="submit" title="Handle" value="HANDLE">
    </form>
</div>
</body>
</html>