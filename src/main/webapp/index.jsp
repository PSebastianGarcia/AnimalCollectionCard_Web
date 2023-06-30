<!DOCTYPE html>
<html>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <title>Animal Collection</title>

    <body>
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
    </body>

</html>