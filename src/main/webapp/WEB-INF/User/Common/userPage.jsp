<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../repeatedCode/htmlBeginning1.jsp"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
<link rel="stylesheet" href="CSS/style.css"/>
<title>My Page</title>
</head>
<body class="green_bg">
    <jsp:include page="../../repeatedCode/navbar.jsp"/>
    <Header class="bg-warning py-5 mb-3">
        <div class="container px-4 px-lg-5">
            <div class="text-center text-dark">
                <h1 class="fw-bolder">Galería</h1>
            </div>
        </div>
    </Header>
    <section class="container d-flex justify-content-center py-3 mb-2">
        <div id="reverse_deck" class="${work}">
            <img src="assets/cards/reverse_card_00.png" id="deck" class="img-fluid max-width-10 max-height-10" alt="Reverse Card"/>
        </div>
        <div id="cards_container" class="container disp_none px-4 px-lg-5 mb-3">
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                <jsp:include page="../../animals/animalCards.jsp">
                    <jsp:param name="type" value="Deck"/>
                    <jsp:param name="cardChoise" value="true"/>
                </jsp:include>
            </div>
        </div>
    </section>
    <section class="container bg-success text-center mb-2">
        <p class="col-10 fw-normal fs-3 text-white mx-auto">Estas son los animales que podrás visualizar en este momento</p>
    </section>

    <c:choose>
        <c:when test="${!cardList.isEmpty()}">
            <section class="py-3">
                <div class="text-center my-3">
                    <button id="show_button_body_card" class="btn btn-success mx-auto" type="button">Mostrar detalles</button>
                </div>
                <div id="cards_container" class="container px-4 px-lg-5 mb-3">
                    <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                        <jsp:include page="../../animals/animalCards.jsp">
                            <jsp:param name="type" value="Collection"/>
                        </jsp:include>
                    </div>
                </div>
            </section>
        </c:when>
        <c:otherwise>
            <section class="container text-center py-3">
                <div classs="col-10 fw-normal fs-6 text-white mx-auto my-3">
                    <h1 class="my-5">Aún no has incorporado ninguna carta</h1>
                </div>
            </section>            
        </c:otherwise>
    </c:choose>

    <jsp:include page="../../repeatedCode/footer.jsp"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
    crossorigin="anonymous"></script>
    <script src="Script/script2.js"></script>
    <script src="Script/userScript.js"></script>
</body>
</html>