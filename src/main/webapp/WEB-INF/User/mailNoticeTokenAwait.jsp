<jsp:include page="../repeatedCode/htmlBeginning1.jsp"/>
<jsp:include page="../repeatedCode/htmlBeginning2.jsp">
    <jsp:param name="title" value="Animal Collection Notice"/>
</jsp:include>
<section class="container my-5">
    <form action="${pageContext.request.contextPath}/user" method="get">
        <h1>Gracias por registrarte, falta un paso m�s y termin�s</h1>
        <p>Busca en la direcci�n de e-mail un correo y sigue los pasos o</p>
        <p>ingres� el token suministrado en el mismo mail</p>
        <input type="text" class="form-control" id="token" name="token" aria-describedby="basic-addon1">
        <button type="submit" class="btn btn-primary" name="action" value="move">Submit</button>
    </form>
</section>
<jsp:include page="../repeatedCode/htmlEnding.jsp"/>
