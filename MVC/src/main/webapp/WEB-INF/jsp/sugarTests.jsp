<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>

<h1>Sugar Tests</h1>

<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Date</th>
        <th scope="col">deviceId</th>
        <th scope="col">Glucose</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="test" items="${tests}">
        <tr>
            <td scope="row">${test.id}</td>
            <td>${test.time}</td>
            <td>${test.device_id}</td>
            <td>${test.glucose}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="footer.jsp"/>