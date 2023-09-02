<jsp:include page="../../repeatedCode/htmlBeginning1.jsp"/>
<jsp:include page="../../repeatedCode/htmlBeginning2.jsp">
    <jsp:param name="title" value="Animal Collection"/>
</jsp:include>

<main class="container text-center my-5">
    <form class="col-md-8" action="${pageContext.request.contextPath}/card?action=insert&detail=user" method="post"">
        <div class="mb-3">
            <P>Quieres insertar:</P>
            <label for="id" class="form-label">Card ID</label>
            <input type="number" class="form-control" id="cardID" name="id" value="${cID}" aria-describedby="basic-addon1">            
        </div>
        <div class="mb-3">
            <P>En el usuario:</P>
            <label for="idUser" class="form-label">User ID</label>
            <input type="number" class="form-control" id="userID" name="idUser" value="${uID}" aria-describedby="basic-addon1">
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/user" class="btn bg-primary">Back</a>
            <button type="submit" class="btn btn-danger">Insert</button>
        </div>
    </form>
</main>
<jsp:include page="../../repeatedCode/htmlEnding.jsp"/>