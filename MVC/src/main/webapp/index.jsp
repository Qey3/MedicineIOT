<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<jsp:include page="WEB-INF/jsp/header.jsp"/>

<%
    response.sendRedirect("http://localhost:8080/MedMvc/homePage");
%>

<h1 align="center">Welcome to home page</h1>

<sec:authorize access="!isAuthenticated()">

    <h1>Please login first</h1>
    <tr>
        <th>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/register" align="center">Register</a>
        </th>
        <th>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/login" align="center">Login</a>
        </th>
    </tr>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/logout" align="center">Logout</a>
</sec:authorize>

<jsp:include page="WEB-INF/jsp/footer.jsp"/>