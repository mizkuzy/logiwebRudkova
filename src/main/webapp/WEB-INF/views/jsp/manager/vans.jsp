<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=8,9,10">
    <meta charset="UTF-8">
    <title>Vans - LOGIWEB</title>

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
                <li><a href="/logout/">Logout</a></li>
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

        <%--<h1> Vans </h1>--%>
        <table class="table table-hover my-settings">
            <caption>VANS LIST</caption>
            <tr>
                <th class="td-sett">
                    Number
                </th>
                <th class="td-sett">
                    Drivers amount
                </th>
                <th class="td-sett">
                    Capacity (ton)
                </th>
                <th class="td-sett">
                    State
                </th>
                <th class="td-sett">
                    Work status
                </th>
                <td class="td-sett">

                </td>
            </tr>

            <c:forEach items="${vansList}" var="van" varStatus="theCount">
                <tr>
                    <td class="td-sett">
                            ${van}
                    </td>
                    <td class="td-sett">
                            ${van.driversAmount}
                    </td>
                    <td class="td-sett">
                            ${van.capacity}
                    </td>
                    <td class="td-sett">
                            ${van.stateVan}
                    </td>
                    <td class="td-sett">
                            ${van.statusVan}
                    </td>
                    <td class="td-sett">
                        <div>
                            <form name="edit" action="getVanForEdit" method="get">
                                <input type="hidden" name="selectedVan" value="${van.idVan}"/>
                                <button class="btn-info" type="submit" title="Edit" value="EDIT">EDIT</button>
                            </form>
                        </div>
                        <div>
                            <form name="delete" action="deleteVan" method="get">
                                <input type="hidden" name="selectedVan" value="${van.idVan}"/>
                                <button class="btn-danger" type="submit" title="Delete" value="DELETE">DELETE</button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>

        </table>

        <div class="new-btn">
            <form name="add" action="createVan" method="get">
                <button class="btn-success" type="submit" title="Add new van" value="NEW">NEW</button>
            </form>
        </div>

    </div>

</div>
</body>
</html>