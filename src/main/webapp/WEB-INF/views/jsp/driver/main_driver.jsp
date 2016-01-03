<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOGIWEB</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/common.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/dashboard.css"/>" rel="stylesheet">

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<%--<h1>DRIVER'S APP</h1>--%>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">LOGIWEB</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Hello, ${driverName}</a></li>
                <li><a href="/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</div>


<div class="container text-center">
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <div class="background-set">
            <img class="img-responsive" src="/resources/img/truck1.jpg" alt="truck1">
        </div>

        <table class="table table-hover my-settings">
            <tr>
                <th>Personal number</th>
                <td>${driverPersonalNumber}</td>
            </tr>
            <tr>
                <th> Co-Drivers</th>
                <td>
                    <c:forEach items="${currentOrder.drivers}" var="driver">
                        ${driver}<br/>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <th>Van</th>
                <td>${currentOrder.van}</td>
            </tr>
            <tr>
                <th>Order</th>
                <td> ${currentOrder.number} </td>
            </tr>
            <tr>
                <td>Routs points</td>
                <td>
                    <table>
                        <tr>
                            <th>GOOD</th>
                            <th>CITY FROM</th>
                            <th>CITY TO</th>
                        </tr>
                        <c:forEach items="${currentOrder.requests}" var="request">
                            <tr>
                                <td>
                                        ${request.goodForRequest.name}
                                </td>
                                <td>
                                        ${request.routForRequest.city1}
                                </td>
                                <td>
                                        ${request.routForRequest.city2}
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
