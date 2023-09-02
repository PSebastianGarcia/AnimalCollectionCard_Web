<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${param.list eq 'CardList'}">
        <c:forEach items="${cardList}" var="card">
            <tr>
                <th scope="row">${card.getId()}</th>
                <td>${card.getAnimal().getName()}</td>
                <td>${card.getAnimal().getGenus()}</td>
                <td>${card.getAnimal().getSpecies()}</td>
                <td>${card.getAnimal().getFoto()}</td>
                <td>
                    <div class="d-flex justify-content-center">
                        <div class="col-5 col-lg-4">
                            <a href="${pageContext.request.contextPath}/card?action=edit&detail=card&id=${card.getId()}" class="btn bg-warning w-100 d-flex align-items-center justify-content-center"><i class="bi bi-pencil-fill"></i></a>
                        </div>
                        <div class="col-5 col-lg-4">
                            <a href="${pageContext.request.contextPath}/card?action=delete&detail=card&id=${card.getId()}" class="btn bg-danger w-100 d-flex align-items-center justify-content-center"><i class="bi bi-trash3-fill"></i></a>
                        </div>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </c:when>
    <c:when test="${param.list eq 'pUserList'}">
        <c:forEach items="${userList}" var="user">
            <tr>
                <th scope="row">${user.getId()}</th>
                <td>${user.getName()}</td>
                <td>${user.getPassword()}</td>
                <td>${user.getFirstName()}</td>
                <td>${user.getLastName()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.isAdmin()}</td>
                <td>
                    <c:if test="${not user.isAdmin()}">
                        <div class="d-flex justify-content-center">
                            <div class="col-5 col-lg-4">
                                <a href="${pageContext.request.contextPath}/user?action=edit&id=${user.getId()}" class="btn bg-warning w-100 d-flex align-items-center justify-content-center"><i class="bi bi-pencil-fill"></i></a>
                            </div>
                            <div class="col-5 col-lg-4">
                                <a href="${pageContext.request.contextPath}/user?action=delete&detail=permanent&id=${user.getId()}" class="btn bg-danger w-100 d-flex align-items-center justify-content-center"><i class="bi bi-trash3-fill"></i></a>
                            </div>
                        </div>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </c:when>
    <c:when test="${param.list eq 'temporalUserList'}">
        <c:forEach items="${tempUserList}" var="user">
            <tr>
                <th scope="row">${user.getId()}</th>
                <td>${user.getName()}</td>
                <td>${user.getPassword()}</td>
                <td>${user.getFirstName()}</td>
                <td>${user.getLastName()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getToken()}</td>
                <td>
                    <div class="d-flex justify-content-center">
                        <div class="col-5 col-lg-4">
                            <a href="${pageContext.request.contextPath}/user?action=move&token=${user.getToken()}" class="btn bg-success w-100 d-flex align-items-center justify-content-center"><i class="bi bi-plus-square-fill"></i></a>
                        </div>
                        <div class="col-5 col-lg-4">
                            <a href="${pageContext.request.contextPath}/user?action=delete&detail=temporal&id=${user.getId()}" class="btn bg-danger w-100 d-flex align-items-center justify-content-center"><i class="bi bi-trash3-fill"></i></a>
                        </div>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </c:when>
    <c:when test="${param.list eq 'cByUser'}">
        <c:forEach items="${cardsByUser}" var="rel">
            <tr>
                <th scope="row">${rel.getUserId()}</th>
                    <c:forEach items="${cardList}" var="card">
                        <c:choose>
                            <c:when test="${rel.getCardIds().contains(card.getId())}">
                            <td>
                                <div class="d-flex justify-content-center">
                                    <div class="col-5 col-lg-4">
                                        <a href="${pageContext.request.contextPath}/card?action=delete&detail=user&id=${card.getId()}&idUser=${rel.getUserId()}" class="btn bg-danger w-100 d-flex align-items-center justify-content-center"><i class="bi bi-trash3-fill"></i></a>
                                    </div>
                                </div>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <div class="row justify-content-center">
                                    <div class="col-5 col-lg-4">
                                        <a href="${pageContext.request.contextPath}/card?action=insert&detail=user&id=${card.getId()}&idUser=${rel.getUserId()}" class="btn bg-warning w-100 d-flex align-items-center justify-content-center"><i class="bi bi-plus-square-fill"></i></a>
                                    </div>
                                </div>
                            </td>
                        </c:otherwise>
                    </c:choose>                    
                </c:forEach>
            </tr>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p>Algo salió mal</p>
    </c:otherwise>
</c:choose>