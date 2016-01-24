<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../header_menu.jsp">
    <jsp:param name="title" value="New request - LOGIWEB"/>
</jsp:include>

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