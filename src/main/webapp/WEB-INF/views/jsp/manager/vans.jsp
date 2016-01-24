<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header_menu.jsp">
    <jsp:param name="title" value="Vans - LOGIWEB"/>
</jsp:include>

<table class="table table-hover my-settings">
    <caption>VANS LIST</caption>
    <tr>
        <th>
            Number
        </th>
        <th>
            Drivers amount
        </th>
        <th>
            Capacity (ton)
        </th>
        <th>
            State
        </th>
        <th>
            Work status
        </th>
        <td>

        </td>
    </tr>

    <c:forEach items="${vansList}" var="van" varStatus="theCount">
        <tr>
            <td>
                    ${van}
            </td>
            <td>
                    ${van.driversAmount}
            </td>
            <td>
                    ${van.capacity}
            </td>
            <td>
                    ${van.stateVan}
            </td>
            <td>
                    ${van.statusVan}
            </td>
            <td>
                <div>
                    <form name="edit" action="getVanForEdit" method="get">
                        <input type="hidden" name="selectedVan" value="${van.idVan}"/>
                        <button class="btn-info" type="submit"
                            ${van.statusVan eq "BUSY" ? 'disabled="disabled"' : ''}
                                title="Edit" value="EDIT">EDIT
                        </button>
                    </form>
                </div>
                <div>
                    <form name="delete" action="deleteVan" method="get">
                        <input type="hidden" name="selectedVan" value="${van.idVan}"/>
                        <button class="btn-danger" type="submit"
                            ${van.statusVan eq "BUSY" ? 'disabled="disabled"' : ''}
                                title="Delete" value="DELETE">DELETE
                        </button>
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