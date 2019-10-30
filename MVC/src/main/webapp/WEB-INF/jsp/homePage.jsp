<jsp:include page="header.jsp"/>
<h1>Welcome to home page</h1>

<sec:authorize access="hasRole('ADMIN')">
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/add" role="button">Add new product</a>
</sec:authorize>

<sec:authorize access="!isAuthenticated()">
    <tr>
        <td>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/register">Register</a>
            </li>
        </td>
        <td>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
            </li>
        </td>
    </tr>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
    </li>
</sec:authorize>

<jsp:include page="footer.jsp"/>