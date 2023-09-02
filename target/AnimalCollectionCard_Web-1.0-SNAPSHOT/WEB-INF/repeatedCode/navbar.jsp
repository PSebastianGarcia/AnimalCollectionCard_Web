<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<nav class="navbar bg-dark border-bottom border-bottom-dark navbar-expand-md bg-body-tertiary mb-2"
     data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="assets/planet.png" alt="" width="30" height="24">Animal-Collection
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse d-md-flex justify-content-end" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${not empty sessionScope.pUser}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/logIn?action=logOut">Log Out</a>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${not fn:endsWith(pageContext.request.servletPath, '/logIn.jsp')}">
                                <a class="nav-link" href="logIn.jsp">Log In</a>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
        </div>
    </div>
</nav>