<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../../repeatedCode/htmlBeginning1.jsp"/>    
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
<link rel="stylesheet" href="CSS/style.css"/>
<title>Admin Page</title>

<body>
    <jsp:include page="../../repeatedCode/navbar.jsp"/>

    <section class="py-3">
        <div class="container text-center">
            <h1 class="fw-normal fs-3">Temporal Users</h1>
        </div>
        <div class="text-center my-3">
            <a href="#" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#modalCreateUser">
                <i class="bi bi-person-add"></i>Create User</a>
        </div>
        <c:choose>
            <c:when test="${not empty tempUserList}">
                <div id="temporal_user_table" class="container table-responsive col-10 px-4 px-lg-5 mb-5">
                        <table class="table table-success table-hover table-bordered table-sm my-5">
                            <thead>
                                <tr>
                                    <th scope="col">id</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Password</th>
                                    <th scope="col">First Name</th>
                                    <th scope="col">Last Name</th>
                                    <th scope="col">E-mail</th>
                                    <th scope="col">Token</th>
                                    <th scope="col">Actions</th>
                                </tr>
                            </thead>
                            <tbody class="table-group-divider">
                                <jsp:include page="../../animals/table.jsp">
                                    <jsp:param name="list" value="temporalUserList"/>
                                </jsp:include>
                            </tbody>
                        </table>  
                </div>
            </c:when>
            <c:otherwise>
                <div class="container text-center col-10 fw-normal fs-3 mx-auto my-3">
                    <p>Aún no hay ningún usuario temporal</p>
                </div>            
            </c:otherwise>
        </c:choose>
    </section>
    <section class="py-3">
        <div class="container text-center">
            <h1 class="fw-normal fs-3">Permanent Users</h1>
        </div>
        <c:choose>
            <c:when test="${not empty userList}">
                <div id="user_table" class="container table-responsive col-10 px-4 px-lg-5 mb-5">
                    <table class="table table-success table-hover table-bordered table-sm my-5">
                        <thead>
                            <tr>
                                <th scope="col">id</th>
                                <th scope="col">Name</th>
                                <th scope="col">Password</th>
                                <th scope="col">First Name</th>
                                <th scope="col">Last Name</th>
                                <th scope="col">E-mail</th>
                                <th scope="col">Admin</th>
                                <th scope="col">Actions</th>
                            </tr>
                        </thead>
                        <tbody class="table-group-divider">
                            <jsp:include page="../../animals/table.jsp">
                                <jsp:param name="list" value="pUserList"/>
                            </jsp:include>
                        </tbody>
                    </table>   
                </div>
            </c:when>
            <c:otherwise>
                <div class="container text-center col-10 fw-normal fs-3 mx-auto my-3">
                    <p>Aún no hay ningún usuario permanente</p>
                </div>            
            </c:otherwise>
        </c:choose>
    </section>
    <section class="py-1">
        <div class="container text-center">
            <h1 class="fw-normal fs-3">Cards</h1>
        </div>
        <div class="text-center my-3">
            <a href="#" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#modalCreateCard">
                <i class="bi bi-postcard"></i>Create Card</a>
        </div>
        <c:choose>
            <c:when test="${not empty cardList}">
                <div class="text-center my-3">
                    <button id="toggle_format_button" class="btn btn-success mx-auto" type="button">Card Format</button>
                    <button id="show_button_body_card" class="btn btn-success disp_none mx-auto" type="button">Show details</button>
                </div>
                <div id="animal_table" class="container table-responsive col-10 px-4 px-lg-5 mb-5">
                    <table class="table table-success table-hover table-bordered table-sm my-5">
                        <thead>
                            <tr>
                                <th scope="col">id</th>
                                <th scope="col">Name</th>
                                <th scope="col">Genus</th>
                                <th scope="col">Species</th>
                                <th scope="col">Foto</th>
                                <th scope="col">Actions</th>
                            </tr>
                        </thead>
                        <tbody class="table-group-divider">
                            <jsp:include page="../../animals/table.jsp">
                                <jsp:param name="list" value="CardList"/>
                            </jsp:include>
                        </tbody>
                    </table>
                </div>
                <div id="cards_container" class="container disp_none px-4 px-lg-5 mb-3">
                    <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                        <jsp:include page="../../animals/animalCards.jsp">
                            <jsp:param name="type" value="Collection"/>
                            <jsp:param name="cardChoise" value="false"/>
                        </jsp:include>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="container text-center col-10 fw-normal fs-3 mx-auto my-3">
                    <p>Aún no hay ninguna carta creada</p>
                </div>            
            </c:otherwise>
        </c:choose>
    </section>
    <section>
        <c:choose>
            <c:when test="${not empty cardsByUser}">
                <div class="container text-center">
                    <h1 class="fw-normal fs-3">Cards By User</h1>
                </div>
                <div class="text-center my-3">
                    <a href="${pageContext.request.contextPath}/user?action=deckEvent" class="btn btn-danger">
                        <i class="bi bi-exclamation-circle"></i>Execute Deck Event</a>
                </div>
                <div id="card_by_user_table" class="container table-responsive col-10 px-4 px-lg-5 mb-5">
                    <table class="table table-success table-hover table-bordered table-sm my-5">
                        <thead>
                            <tr>
                                <th scope="col">userId</th>
                                    <c:forEach items="${cardList}" var="card">
                                    <th scope="col">${card.getId()}</th>
                                    </c:forEach>
                            </tr>
                        </thead>
                        <tbody class="table-group-divider">
                            <jsp:include page="../../animals/table.jsp">
                                <jsp:param name="list" value="cByUser"/>
                            </jsp:include>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div class="container text-center col-10 fw-normal fs-3 mx-auto my-3">
                    <p>Aún no hay ninguna carta asignada a un usuario</p>
                </div>            
            </c:otherwise>
        </c:choose>

    </section>
    <jsp:include page="../../repeatedCode/footer.jsp"/>
    <jsp:include page="modalCreateUser.jsp"/>
    <jsp:include page="modalCreateAnimal.jsp"/>
    <jsp:include page="modalCreateCard.jsp"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
    crossorigin="anonymous"></script>
    <script src="Script/script.js"></script>
</body>
</html>
