<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../repeatedCode/htmlBeginning1.jsp"/>
<jsp:include page="../../repeatedCode/htmlBeginning2.jsp">
    <jsp:param name="title" value="Delete User"/>
</jsp:include>

<main class="container text-center my-5">
    <c:choose>
        <c:when test="${type eq 'tempUser'}">
            <c:set var="det" value="temporal"/>
            <c:set var="userType" value="temporal"/>
        </c:when>
        <c:when test="${type eq 'permUser'}">
            <c:set var="det" value="permanent"/>
            <c:set var="userType" value="permanente"/>
        </c:when>
    </c:choose>
    <form class="col-md-8" action="${pageContext.request.contextPath}/user?action=delete&detail=${det}&id=${user.getId()}" method="post">
        <p class="fw-normal fs-3">Estás por eliminar al usuario ${userType}:</p>
        <div class="mb-3">
            <label for="fName" class="form-label">${user.getName()}</label>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">${user.getEmail()}</label>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/user" class="btn btn-primary">Back</a>        
            <button type="submit" class="btn btn-danger">Delete</button>
        </div>
    </form>
</main>    
<jsp:include page="../../repeatedCode/htmlEnding.jsp"/>
