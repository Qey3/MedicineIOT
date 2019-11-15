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
            <td>${test.analysisTime}</td>
            <td>${test.device.id}</td>
            <td>${test.glucose}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <c:if test="${page>1}">
        <li class="page-item">
            </c:if>
            <c:if test="${page<=1}">
        <li class="page-item disabled">
            </c:if>
            <a class="page-link" href="${pageContext.request.contextPath}/sugar-page/${page-1}" tabindex="-1"
               aria-disabled="${page<2}">Previous</a>
        </li>
        <li class="page-item">
            <a class="page-link" href="${pageContext.request.contextPath}/sugar-page/${page+1}">Next</a>
        </li>
    </ul>
</nav>
<%--<c:if test="${page>1}">--%>
<%--    <a class="btn btn-primary"--%>
<%--       href="${pageContext.request.contextPath}/sugar-page/${page-1}"--%>
<%--       role="button"><c:out value="${page-1}"/></a>--%>
<%--</c:if>--%>
<%--<c:if test="${tests.size()==21}">--%>
<%--    <a class="btn btn-primary"--%>
<%--       href="${pageContext.request.contextPath}/sugar-page/${page+1}"--%>
<%--       role="button"><c:out value="${page+1}"/></a>--%>
<%--</c:if>--%>

<jsp:include page="footer.jsp"/>