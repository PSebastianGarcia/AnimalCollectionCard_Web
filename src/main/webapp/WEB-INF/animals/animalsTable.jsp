<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach items="${animalsList}" var="animal">
    <tr>
        <th scope="row">${animal.id}</th>
        <td>${animal.name}</td>
        <td>${animal.genus}</td>
        <td>${animal.species}</td>
    </tr>
</c:forEach>