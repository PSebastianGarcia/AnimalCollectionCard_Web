<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach items="${animalsList}" var="animal">    
    <div class="col mb-5">
        <div class="card h-100">
            <img src="assets/animals/${animal.foto}" class="card-img-top" alt="...">
            <div class="card-body animal_body_card">
                <h5 class="card-title">${animal.name}</h5>
                <p class="card-text">${animal.genus}</p>
                <p class="card-text">${animal.species}</p>
            </div>
        </div>
    </div>
</c:forEach>