<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header_menu.jsp">
    <jsp:param name="title" value="Drivers - LOGIWEB"/>
</jsp:include>

<table class="table table-hover my-settings">
    <caption>DRIVERS LIST</caption>
    <tr>
        <th>
            Personal Number
        </th>
        <th>
            Name
        </th>
        <th>
            Surname
        </th>
        <th>
            Work hours
        </th>
        <th>
            Current state
        </th>
        <td>
            Work Status
        </td>
        <td>

        </td>
    </tr>

    <c:forEach items="${drivers}" var="driver" varStatus="theCount">
        <tr>
            <td>
                    ${driver.employee.personalNumber}
            </td>
            <td>
                    ${driver.name}
            </td>
            <td>
                    ${driver.surname}
            </td>
            <td>
                    ${driver.workHours}
            </td>
            <td>
                    ${driver.state}
            </td>
            <td>
                    ${driver.statusDriver}
            </td>
            <td>
                <div>
                    <form name="edit" action="getDriverForEdit" method="get">
                        <input type="hidden" name="selectedDriver" value="${driver.id}"/>
                        <button class="btn-info" type="submit"
                            ${driver.statusDriver eq "BUSY" ? 'disabled="disabled"' : ''}
                                title="Edit" value="EDIT">EDIT
                        </button>
                    </form>
                </div>
                <div>
                    <form name="delete" action="deleteDriver" method="get">
                        <input type="hidden" name="selectedDriver" value="${driver.id}"/>
                        <button class="btn-danger" type="submit"
                            ${driver.statusDriver eq "BUSY" ? 'disabled="disabled"' : ''}
                                title="Delete" value="DELETE">DELETE
                        </button>
                    </form>
                </div>
            </td>
        </tr>
    </c:forEach>
</table>

<div class="new-btn">
    <form name="add" action="createDriver" method="get">
        <button class="btn-success" type="submit" title="Add new driver" value="NEW">NEW</button>
    </form>
</div>

</div>
</div>

</body>
</html>