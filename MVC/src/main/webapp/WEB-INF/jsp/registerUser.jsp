<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>

<h1>Register New User</h1>

<form action="${pageContext.request.contextPath}/register" method="post">
    <div class="form-group">
        <label for="name">Last and Second Name</label>
        <input type="text" name="name" class="form-control" id="name" placeholder="Enter your name">
    </div>
    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" name="email" class="form-control" id="email" placeholder="Enter your email">
    </div>
    <div class="form-group">
        <label for="login">User Login</label>
        <input type="text" name="login" class="form-control" id="login" placeholder="Enter your login">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" name="password" class="form-control" id="password" placeholder="Enter your password">
    </div>
    <div>
        <label for="birthday">Your birthday</label>
        <input type="date" name="birthday" id="birthday" min="1945-05-09" max="2001-01-01" value="1995-05-09"><br>
    </div>
    <div class="form-group">
        <select name="role" id="role" class="form-control">
            <c:forEach var="role" items="${roles}">
                <option>${role}</option>
            </c:forEach>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>

<c:forEach var="error" items="${errors}">
    <h4 style="color:red;">${error.defaultMessage}</h4>
</c:forEach>

<jsp:include page="footer.jsp"/>