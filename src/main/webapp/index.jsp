<jsp:include page="WEB-INF/repeatedCode/htmlBeginning1.jsp"/>
<jsp:include page="WEB-INF/repeatedCode/htmlBeginning2.jsp">
    <jsp:param name="title" value="Animal Collection"/>
</jsp:include>
<jsp:include page="WEB-INF/repeatedCode/navbar.jsp"/>
<Header class="bg-success py-5">
    <div class="container px-4 px-lg-5">
        <div class="text-center text-white">
            <h1 class="fw-bolder">Animal Collection</h1>
            <p class="fw-normal fs-5">Un lugar donde reunir información sobre los miembros del reino animal que te agradan de forma entretenida y educativa</p>
        </div>
    </div>
</Header>
<main class="bg-warning py-5 mt-4 mb-3">
    <section class="container px-4 px-lg-5">
        <div class="text-center text-dark">
            <h1 class="fw-bold">Galería</h1>
            <p class="fw-normal fs-3">En este sitio encontrarás una serie de tarjetas de animales para coleccionar.</p>
            <p class="fs-4">Podrás ver tanto una imágen como las características taxonómicas más habituales de encontrar en fuentes de divulgación</p>
        </div>
    </section>
</main>
<jsp:include page="WEB-INF/repeatedCode/footer.jsp"/>
<jsp:include page="WEB-INF/repeatedCode/htmlEnding.jsp"/>