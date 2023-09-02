<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../repeatedCode/htmlBeginning1.jsp"/>
<jsp:include page="../../repeatedCode/htmlBeginning2.jsp">
    <jsp:param name="title" value="Edit User"/>
</jsp:include>

<main class="container text-center my-5">
    <form class="col-md-8" action="${pageContext.request.contextPath}/user?action=update" method="post">
        <jsp:include page="../../repeatedCode/userFormBeginning.jsp"/>
        <div class="mb-3">
            <label for="uPass" class="form-label">Password</label>
            <input type="text" class="form-control" id="UserPassword" value="${user != null ? user.getPassword() : ''}" name="uPass">
        </div>
        <div class="mb-3">
            <label for="uAdmin" class="form-label">Admin</label>
            <input type="text" class="form-control" id="Admin" name="uAdmin" value="${user != null ? user.isAdmin() : ''}" aria-describedby="basic-addon1">
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/user" class="btn bg-primary">Back</a>
            <button type="submit" class="btn btn-danger">Edit</button>
        </div>    
    </form>
</main>
<jsp:include page="../../repeatedCode/htmlEnding.jsp"/>
