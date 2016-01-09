<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function (e) {
            $("#select1").change(function () {
                var x = $('select option:selected').attr('name');
                $('#step2').find('select').css('display', 'none');
                $('#' + x).css('display', 'block');
            })
        });
    </script>
    <style>
        #step2 select {
            display: none;
        }
    </style>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/common.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/dashboard.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/common.js"/>"></script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>


    <title>New request - LOGIWEB</title>

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


<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


    <div class="background-set">
        <img src="/resources/img/truck1.jpg" alt="truck1">
    </div>

    <div class="container">
        <form:form class="form-horizontal" role="form" action="addNewRequest" method="post">
            <div class="form-group">
                <div class="col-sm-5">
                    <b>Goods:</b><br>
                    <input type="text" name="goods_name" class="form-control" placeholder="Goods" required>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-5">
                    <b>Mass:</b><br>
                    <input type="text" name="mass" class="form-control" placeholder="Mass" required>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-5">
                    <b>City from:</b><br>
                    <select class="form-control" id="select1" name="city1" required>
                        <option selected disabled>Choose</option>
                        <c:forEach items="${cities}" var="city1">
                            <option name="${city1}">${city1}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-5">
                    <div id="step2">
                        <b>City to:</b><br>
                        <select class="form-control" id="Saint-Petersburg" name="city2" required>
                            <option selected disabled>Choose</option>
                            <c:forEach items="${spb}" var="city2">
                                <option name="${city2}">${city2}</option>
                            </c:forEach>
                        </select>
                        <select class="form-control" id="Veliky_Novgorod" name="city2" required>
                            <option selected disabled>Choose</option>
                            <c:forEach items="${velikyNovgorod}" var="city2">
                                <option>${city2}</option>
                            </c:forEach>
                        </select>
                        <select class="form-control" id="Pskov" name="city2" required>
                            <option selected disabled>Choose</option>
                            <c:forEach items="${pskov}" var="city2">
                                <option>${city2}</option>
                            </c:forEach>
                        </select>
                        <select class="form-control" id="Petrozavodsk" name="city2" required>
                            <option selected disabled>Choose</option>
                            <c:forEach items="${petrozavodsk}" var="city2">
                                <option>${city2}</option>
                            </c:forEach>
                        </select>
                            <%--<select class="form-control" id="Arhangelsk" name="city2" required>
                                <option selected disabled>Choose</option>
                                <c:forEach items="${arhangelsk}" var="city2">
                                    <option>${city2}</option>
                                </c:forEach>
                            </select>
                            <select class="form-control" id="Vologda" name="city2" required>
                                <option selected disabled>Choose</option>
                                <c:forEach items="${vologda}" var="city2">
                                    <option>${city2}</option>
                                </c:forEach>
                            </select>
                            <select class="form-control" id="Siktivkar" name="city2" required>
                                <option selected disabled>Choose</option>
                                <c:forEach items="${siktivkar}" var="city2">
                                    <option>${city2}</option>
                                </c:forEach>
                            </select>
                            <select class="form-control" id="Naryan-Mar" name="city2" required>
                                <option selected disabled>Choose</option>
                                <c:forEach items="${naryan-Mar}" var="city2">
                                    <option>${city2}</option>
                                </c:forEach>
                            </select>
                            <select class="form-control" id="Murmansk" name="city2" required>
                                <option selected disabled>Choose</option>
                                <c:forEach items="${murmansk}" var="city2">
                                    <option>${city2}</option>
                                </c:forEach>
                            </select>
                            <select class="form-control" id="Kaliningrad" name="city2" required>
                                <option selected disabled>Choose</option>
                                <c:forEach items="${kaliningrad}" var="city2">
                                    <option>${city2}</option>
                                </c:forEach>
                            </select>
                            <select class="form-control" id="Cherepovec" name="city2" required>
                                <option selected disabled>Choose</option>
                                <c:forEach items="${cherepovec}" var="city2">
                                    <option>${city2}</option>
                                </c:forEach>
                            </select>--%>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-5">
                    <button type="submit" id="btn" class="btn btn-success" title="Pick up requests" value="ADD">ADD
                    </button>
                </div>
            </div>

        </form:form>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function (e) {
        $("#select1").change(function () {
            var x = $('select option:selected').attr('name');
            $('#step2').find('select').css('display', 'none');
            $('#' + x).css('display', 'block');
        })
    });
</script>

<style>
    #step2 select {
        display: none;
    }
</style>

</body>
</html>