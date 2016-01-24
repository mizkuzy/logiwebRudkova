<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../header_menu.jsp">
    <jsp:param name="title" value="Current requests - LOGIWEB"/>
</jsp:include>
<div>
    <h1>Current requests</h1>

    <div class="row">
        <div class="col-md-6">
            <div class="make-disabled-if-request-zero">
                <form action="create_order" method="get">
                    <input type="hidden" name="currentRoutLabel" value="yellow"/>
                    <button class="btn-yellow" type="submit"
                    ${yellowRoutRequestsSize eq 0 ? 'disabled="disabled"' : ''}
                            title="Saint-Petersburg - Velikiy Novgorod - Pskov - Kaliningrad"
                            value="${yellowRoutRequestsSize} REQUESTS">
                        ${yellowRoutRequestsSize} REQUESTS
                    </button>

                </form>
            </div>
        </div>

        <div class="col-md-6">
            <form action="create_order" method="get">
                <input type="hidden" name="currentRoutLabel" value="green"/>
                <button class="btn-green" type="submit"
                ${greenRoutRequestsSize eq 0 ? 'disabled="disabled"' : ''}
                        title="Saint-Petersburg - Petrozavodsk - Murmansk"
                        value="${greenRoutRequestsSize} REQUESTS">
                    ${greenRoutRequestsSize} REQUESTS
                </button>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <form action="create_order" method="get">
                <input type="hidden" name="currentRoutLabel" value="purple"/>
                <button class="btn-purple" type="submit"
                ${purpleRoutRequestsSize eq 0 ? 'disabled="disabled"' : ''}
                        title="Saint-Petersburg - Cherepovec - Arhangelsk - Naryan-Mar"
                        value="${purpleRoutRequestsSize} REQUESTS">
                    ${purpleRoutRequestsSize} REQUESTS
                </button>
            </form>
        </div>
        <div class="col-md-6">
            <form action="create_order" method="get">
                <input type="hidden" name="currentRoutLabel" value="blue"/>
                <button class="btn-blue" type="submit"
                ${blueRoutRequestsSize eq 0 ? 'disabled="disabled"' : ''}
                        title="Saint-Petersburg - Vologda - Siktivkar"
                        value="${blueRoutRequestsSize} REQUESTS">
                    ${blueRoutRequestsSize} REQUESTS
                </button>
            </form>
        </div>
    </div>
</div>
</div>
</div>
</body>
</html>