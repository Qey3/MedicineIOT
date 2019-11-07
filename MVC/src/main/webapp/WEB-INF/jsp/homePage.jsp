<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<h1>Welcome to home page</h1>


<sec:authorize access="!isAuthenticated()">
    <tr>
        <th>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/register">Register</a>
        </th>
        <th>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/login">Login</a>
        </th>
    </tr>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/logout">Logout</a>
</sec:authorize>

<jsp:include page="footer.jsp"/>