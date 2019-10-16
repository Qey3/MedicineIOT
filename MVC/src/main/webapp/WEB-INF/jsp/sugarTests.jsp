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
    <c:forEach var="tests" tests="${tests}">
        <tr>
            <th scope="row">${tests.id}</th>
            <td><a href="${pageContext.request.contextPath}/product/item/${tests.id}">${tests.id}</a></td>
            <td>${tests.time}</td>
            <td>${tests.device_id}</td>
            <td>${tests.glucose}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="footer.jsp"/>