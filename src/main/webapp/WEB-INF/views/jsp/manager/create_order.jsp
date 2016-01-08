<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order - LOGIWEB</title>

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
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="main_manager">LOGIWEB</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><security:authentication property="principal.username"/></a></li>
                <li><a href="/j_spring_security_logout">Logout</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="#">Menu</a></li>
                <li><a href="new_request">New Request</a></li>
                <li><a href="pick_up_requests">Pick up requests</a></li>
                <li><a href="orders_list">Orders list</a></li>
                <li><a href="vans">Vans</a></li>
                <li><a href="drivers">Drivers</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container text-center">
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

        <div class="background-set">
            <img class="img-responsive" src="/resources/img/truck1.jpg" alt="truck1">
        </div>
        <div class="container text-center">
            <table class="table table-hover my-settings">
                <caption>Create order</caption>
                <tr class="td-sp">
                    <th>Order number</th>
                    <td>${order.number} </td>
                </tr>
                <tr class="td-sp">
                    <th>Status</th>
                    <td>${order.status}</td>
                </tr>
                <tr class="td-sp">
                    <th>Routs points:</th>
                    <td>
                        <table>
                            <tr>
                                <th>GOOD</th>
                                <th>CITY FROM</th>
                                <th>CITY TO</th>
                            </tr>
                            <c:forEach items="${order.requests}" var="request">
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
                <tr class="td-sp">
                    <th>Total mass</th>
                    <td>${mass}</td>
                </tr>
                <tr>
                    <form:form class="form-horizontal" role="form" action="save_order" method="get">
                        <td>
                            <div class="form-group">
                                <div class="col-sm-5">
                                    <b>Choose ${maxCheckboxSelections-1} drivers:</b><br>
                                    <c:forEach items="${appropriateDrivers}" var="driver" varStatus="theCount">
                                        <input type="checkbox" class="checkbox_drivers" name="selectedDriver"
                                               value="${theCount.count}"
                                               max="3">${driver}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </td>

                        <td>
                            <div class="form-group">
                                <div class="col-sm-5">
                                    <b>Choose van:</b><br>
                                    <c:forEach items="${appropriateVans}" var="van" varStatus="theCount">
                                        <input type="checkbox" class="checkbox_vans" name="selectedVan"
                                               value="${theCount.count}"
                                               max="1">${van}<br>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-5">
                                    <button type="submit" class="btn btn-success" title="Save order"
                                            value="SAVE ORDER">SAVE ORDER
                                    </button>
                                </div>
                            </div>
                        </td>
                    </form:form>
                </tr>
            </table>

        </div>
    </div>
</div>

<script>
    jQuery(function () {
        var max = <%=session.getAttribute("maxCheckboxSelections")%>;
        var checkboxes = $('input[type="checkbox"]');
        /*добавить jQuery selector*/
        checkboxes.change(function () {
            var current = checkboxes.filter(':checked').length;
            checkboxes.filter(':not(:checked)').prop('disabled', current >= max);
        });
    });
</script>
</body>
</html>