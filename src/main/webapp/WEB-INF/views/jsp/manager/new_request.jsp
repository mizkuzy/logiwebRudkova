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

        <script type="text/javascript">
            $(document).ready(function (e) {
                $("#select1").change(function () {
                    var x = $('select option:selected').attr('name');
                    $.ajax({
                        url: 'get_corresponding_cities/' + x,
                        type: 'GET',
                        data: ({city1: x}),
                        success: showCitiesTo
                    });

                });
            });

            function showCitiesTo(data) {
                var select2 = $('#select2');
                select2.empty();
                var cities = JSON.parse(data);
                if (data == "") {
                    console.log("Empty list");
                } else {
                    $.each(cities, function (i, value) {
                        select2.append($('<option>').text(value).attr('value', value));
                    });
                }
            }
        </script>

        <div class="form-group">
            <div class="col-sm-5">
                <b>City from:</b><br>
                <select name="city1" class="form-control" id="select1" required>
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
                    <select class="form-control" id="select2" name="city2" required>

                    </select>
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


</body>
</html>