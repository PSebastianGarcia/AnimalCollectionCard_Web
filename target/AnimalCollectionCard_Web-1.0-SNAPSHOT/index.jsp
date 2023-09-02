<jsp:include page="WEB-INF/repeatedCode/htmlBeginning1.jsp"/>
<jsp:include page="WEB-INF/repeatedCode/htmlBeginning2.jsp">
    <jsp:param name="title" value="Animal Collection"/>
</jsp:include>
<jsp:include page="WEB-INF/repeatedCode/navbar.jsp"/>
<Header class="bg-success py-5">
    <div class="container px-4 px-lg-5">
        <div class="text-center text-white">
            <h1 class="fw-bolder">Animal Collection</h1>
            <p class="fw-normal fs-5">Un lugar donde reunir informaci�n sobre los miembros del reino animal que te agradan de forma entretenida y educativa</p>
        </div>
    </div>
</Header>
<main class="bg-warning py-5 mt-4 mb-3">
    <section class="container px-4 px-lg-5">
        <div class="text-center text-dark">
            <h1 class="fw-bold">Galer�a</h1>
            <p class="fw-normal fs-3">En este sitio encontrar�s una serie de tarjetas de animales para coleccionar.</p>
            <p class="fs-4">Podr�s ver tanto una im�gen como las caracter�sticas taxon�micas m�s habituales de encontrar en fuentes de divulgaci�n</p>
        </div>
    </section>
</main>
<jsp:include page="WEB-INF/repeatedCode/footer.jsp"/>
<jsp:include page="WEB-INF/repeatedCode/htmlEnding.jsp"/>