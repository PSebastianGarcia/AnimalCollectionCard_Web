<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../repeatedCode/htmlBeginning1.jsp"/>
<jsp:include page="../repeatedCode/htmlBeginning2.jsp">
    <jsp:param name="title" value="Delete Card"/>
</jsp:include>

<main class="container text-center my-5">
    <form action="${pageContext.request.contextPath}/card?action=delete&id=${cardList[0].getId()}&detail=card" method="post">
        <div class="mb-3">
            <label for="id" class="form-label">${cardList[0].getId()}</label>
        </div>
        <div class="container px-4 px-lg-5 mb-3">
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <jsp:include page="animalCards.jsp">
                <jsp:param name="type" value="Collection"/>
            </jsp:include>
        </div>
        </div>
        <div class="mb-3">
            <a href="${pageContext.request.contextPath}/user" class="btn bg-primary">Back</a>        
            <button type="submit" class="btn btn-danger">Delete</button>
        </div>
    </form>
</main>
<jsp:include page="../repeatedCode/htmlEnding.jsp"/>
