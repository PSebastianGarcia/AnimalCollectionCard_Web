<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../repeatedCode/htmlBeginning1.jsp"/>
<jsp:include page="../repeatedCode/htmlBeginning2.jsp">
    <jsp:param name="title" value="Edit Card"/>
</jsp:include>

<main class="container text-center my-5">
    <form class="col-md-8" action="${pageContext.request.contextPath}/card?action=update" method="post">
        <div class="mb-3">
            <label for="IDAnim" class="form-label">Animal ID</label>
            <input type="number" class="form-control" id="IDAnim" name="IDAnim" value="${animal.getId() != null ? animal.getId() : ''}" aria-describedby="basic-addon1">
        </div>
        <div class="mb-3">
            <a href="${pageContext.request.contextPath}/user" class="btn bg-primary">Back</a>
            <button type="submit" class="btn btn-danger">Edit</button>
        </div>
    </form>
</main>
<jsp:include page="../repeatedCode/htmlEnding.jsp"/>
