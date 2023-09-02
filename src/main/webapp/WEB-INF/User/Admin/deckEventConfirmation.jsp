<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../repeatedCode/htmlBeginning1.jsp"/>
<jsp:include page="../../repeatedCode/htmlBeginning2.jsp">
    <jsp:param name="title" value="Delete User"/>
</jsp:include>

<main class="container text-center my-5">
    <form action="${pageContext.request.contextPath}/user?action=deckEvent" method="post">
        <div>
            <p>¿Ejecutas el evento de mezcla y reparto de cartas?</p>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/user" class="btn btn-primary">Back</a>        
            <button type="submit" class="btn btn-danger">Execute</button>
        </div>
    </form>
</main>

<jsp:include page="../../repeatedCode/htmlEnding.jsp"/>
