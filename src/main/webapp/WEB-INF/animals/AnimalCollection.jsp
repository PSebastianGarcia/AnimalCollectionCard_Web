<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<link rel="stylesheet" href="../../CSS/style.css"/>
<title>Animal Collection</title>

<body class="green_bg">
    <jsp:include page="../repeatedCode/navbar.jsp"/>
    <Header class="bg-warning py-5 mb-3">
        <div class="container px-4 px-lg-5">
            <div class="text-center text-white">
                <h1 class="fw-bolder">Galería</h1>
            </div>
        </div>
    </Header>
    <section class="container bg-success text-center mb-2">
        <p class="col-10 fw-normal fs-3 mx-auto">Estas son las tarjetas que podrás visualizar en este momento</p>
    </section>
    <section class="py-3">
        <div class="container px-4 px-lg-5 mb-3">
            <div class="text-center my-3">
                <button id="show_button_body_card" class="btn btn-success mx-auto" type="button">Mostrar detalles</button>
            </div>
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                <jsp:include page="animalCards.jsp"/>
            </div>
        </div>
    </section>
    <jsp:include page="../repeatedCode/footer.jsp"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
        <script src="../../Script/script.js"></script>
</body>

</html>