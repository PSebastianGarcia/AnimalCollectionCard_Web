<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../repeatedCode/htmlBeginning1.jsp"/>
<jsp:include page="../repeatedCode/htmlBeginning2.jsp">
    <jsp:param name="title" value="Confirmation"/>
</jsp:include>

<main class="container text-center my-5">
    <h1>E-mail confirmado con éxito</h1>
    <c:choose>
        <c:when test="${admin == true}">
            <p>Usuario habilitado</p>
            <a href="${pageContext.request.getContextPath()}/user">Back</a>
        </c:when>
        <c:otherwise>
            <p>Por favor, ingresa con tu usuario y contraseña</p>
            <a href="${pageContext.request.getContextPath()}/index.jsp">Inicio</a>
        </c:otherwise>
    </c:choose>
</main>


<jsp:include page="../repeatedCode/htmlEnding.jsp"/>
