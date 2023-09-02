<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="WEB-INF/repeatedCode/htmlBeginning1.jsp"/>
<jsp:include page="WEB-INF/repeatedCode/htmlBeginning2.jsp">
    <jsp:param name="title" value="Log In"/>
</jsp:include>
<jsp:include page="WEB-INF/repeatedCode/navbar.jsp"/>

<main class="container ms-2">
    <form class="col-md-4" action="${pageContext.request.contextPath}/logIn" method="post">
        <jsp:include page="WEB-INF/User/userAndPassInputs.jsp"/>
        <button type="submit" class="btn btn-primary">Log In</button>
    </form>
    <c:if test="${not empty param.alert and param.alert eq 'true'}">
        <p>Usuario y/o contraseña incorrectos</p>
    </c:if>
    <div classs="my-3">
        <p class="fw-normal">Si aún no estás registrado haz click en el siguiente enlace <a href="signIn.jsp">Sign In</a></p>
        <p class="fw-normal">Si se te ha suministrado un token al registrarte puedes colocarlo aquí:</p>
        <form class="col-md-4" action="${pageContext.request.contextPath}/user" method="get">
            <input type="text" class="form-control" id="token" name="token" aria-describedby="basic-addon1">
            <button type="submit" class="btn btn-primary mt-2" name="action" value="move">Submit</button>
        </form>
    </div> 
</main>
<jsp:include page="WEB-INF/repeatedCode/footer.jsp"/>
<jsp:include page="WEB-INF/repeatedCode/htmlEnding.jsp"/>