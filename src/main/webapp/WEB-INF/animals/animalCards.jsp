<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${param.type eq 'Collection'}">
        <c:set var="itemList" value="${cardList}" />
    </c:when>
    <c:when test="${param.type eq 'Deck'}">
        <c:set var="itemList" value="${deck}" />
    </c:when>
</c:choose>

<c:forEach items="${itemList}" var="card">
    <div class="col mb-5">
        <div class="card h-100">
            <img src="assets/animals/${card.getAnimal().getFoto()}" class="card-img-top" alt="Carta de ${card.getAnimal().getName()}">
            <div class="card-body disp_none animal_body_card">
                <h5 class="card-title">${card.getAnimal().getName()}</h5>
                <p class="card-text">${card.getAnimal().getGenus()}</p>
                <p class="card-text">${card.getAnimal().getSpecies()}</p>
                <c:if test="${param.cardChoise eq 'true'}">
                    <a href="${pageContext.request.contextPath}/card?action=insert&detail=user&id=${card.getId()}" class="add_card btn btn-warning w-100"><i class="bi bi-plus-square-fill"></i></a>
                    </c:if>
            </div>
        </div>
    </div>
</c:forEach>
