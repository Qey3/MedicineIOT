<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>

<h1>${device.name}</h1>


<%--    <img src="${pageContext.request.contextPath}/product/itemImage/${.id}?id=${.id}" class="card-img-top" alt="...">--%>
<tbody>
<tr>
    <th scope="col">
        <div>
            <img src="${pageContext.request.contextPath}/device-catalog/item/${device.id}/image"
                 style="width:180px;height:240px;"
                 class="card-img-top" alt="..."></div>
    </th>
    <th scope="col">
        <div class="card-body">
            <p class="card-text">Description: ${device.description}</p>
        </div>
    </th>
</tr>
</tbody>

<sec:authorize access="hasRole('ADMIN')">
    <a href="${pageContext.request.contextPath}/device-catalog/item/${device.id}/del"
       class="btn btn-primary">Del</a>
</sec:authorize>


<jsp:include page="footer.jsp"/>