<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>

<h1>Used devices</h1>
<sec:authorize access="hasRole('USER')">
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/addUserDevice" role="button">Add new device</a>
</sec:authorize>

<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Id</th>
        <th scope="col">serialNumber</th>
        <th scope="col">use from</th>
        <th scope="col">end use date</th>
        <sec:authorize access="hasRole('USER')">
            <th scope="col">off device</th>
        </sec:authorize>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="device" items="${device}">
        <tr>
            <td scope="row"><a
                    href="${pageContext.request.contextPath}/device-catalog/item/${device.devicesDetails.id}">${device.id}</a></td>
            <td><a
                    href="${pageContext.request.contextPath}/device-catalog/item/${device.devicesDetails.id}">${device.serialNumber}</a>
            </td>
            <td>
                    ${device.startUse}
            </td>
            <td>
                    ${device.endUse}
            </td>
            <sec:authorize access="hasRole('USER')">
                <td>

                    <c:choose>
                        <c:when test="${device.endUse==null}">
                            <a class="btn btn-primary"
                               href="${pageContext.request.contextPath}/device-catalog/item/disable/${device.id}"
                               role="button">Disable
                                device</a></c:when>
                        <c:otherwise><p class="h6">Disabled</p></c:otherwise>
                    </c:choose>
                        <%--                    <c:if test="${device.endUse==null}">--%>
                        <%--                        <a class="btn btn-primary"--%>
                        <%--                           href="${pageContext.request.contextPath}/device-catalog/item/disable/${device.id}"--%>
                        <%--                           role="button">Disable--%>
                        <%--                            device</a>--%>
                        <%--                    </c:if>--%>

                        <%--                    <c:if test="${device.endUse!=null}">--%>
                        <%--                        <p class="h6">Disabled</p>--%>
                        <%--                    </c:if>--%>
                </td>
            </sec:authorize>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="footer.jsp"/>