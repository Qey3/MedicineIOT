<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>

<h1>Add Device Page</h1>

<form action="http://localhost:8080/MedMvc/addUserDevice/add" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label for="serialNumber">Serial number</label>
        <input type="text" name="serialNumber" class="form-control" id="serialNumber" aria-describedby="emailHelp" placeholder="Enter serial number">
    </div>
    <div class="form-group">
        <select name="type" id="type" class="form-control">
            <c:forEach var="type" items="${types}">
                <option>${type}</option>
            </c:forEach>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>

<c:forEach var="error" items="${errors}">
    <h4 style="color:red;">${error.defaultMessage}</h4>
</c:forEach>

<jsp:include page="footer.jsp"/>