<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>

<h1>Add Device Page</h1>

<form action="http://localhost:8080/MedMvc/addDeviceDetails/add" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label for="deviceName">Product name</label>
        <input type="text" name="name" class="form-control" id="deviceName" aria-describedby="emailHelp" placeholder="Enter name">
    </div>
    <div class="form-group">
        <label for="deviceDescription">Product name</label>
        <input type="text" name="description" class="form-control" id="deviceDescription" aria-describedby="emailHelp" placeholder="Enter description">
    </div>

    <div class="form-group">
        <label for="exampleFormControlFile1">Device picture</label>
        <input type="file" name="image" class="form-control-file" id="exampleFormControlFile1">
    </div>
    <div class="form-group">
        <select name="deviceTypes" id="deviceTypes" class="form-control">
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