<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>

<h1>Devices</h1>
<sec:authorize access="hasRole('ADMIN')">
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/addDeviceDetails" role="button">Add new device</a>
</sec:authorize>

<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Name</th>
        <th scope="col">Picture</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="device" items="${device}">
        <tr>
            <td scope="row"><a
                    href="${pageContext.request.contextPath}/device-catalog/item/${device.id}">${device.id}</a></td>
            <td><a
                    href="${pageContext.request.contextPath}/device-catalog/item/${device.id}">${device.name}</a></td>
            <td>
                <img src="${pageContext.request.contextPath}/device-catalog/item/${device.id}/image"
                     class="card-img-top"
                     alt="..."
                     style="width:30px;height:30px;">
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="footer.jsp"/>