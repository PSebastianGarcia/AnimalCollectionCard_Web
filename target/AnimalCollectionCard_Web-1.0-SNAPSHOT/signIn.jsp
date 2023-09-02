<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="WEB-INF/repeatedCode/htmlBeginning1.jsp"/>
<jsp:include page="WEB-INF/repeatedCode/htmlBeginning2.jsp">
    <jsp:param name="title" value="Sign In"/>
</jsp:include>
<jsp:include page="WEB-INF/repeatedCode/navbar.jsp"/>

<main class="container ms-2">
    <form class="col-md-8" action="${pageContext.request.contextPath}/signIn" method="post">
        <jsp:include page="WEB-INF/repeatedCode/userFormBeginning.jsp"/>
        <div class="mb-3">
            <label for="uPass" class="form-label">Password</label>
            <input type="password" class="form-control" id="UserPassword" value="${user != null ? user.getPassword() : ''}" name="uPass">
        </div>
        <c:if test="${not empty param.alert and param.alert eq 'true'}">
            <p>Usuario existente</p>
        </c:if>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</main>
<jsp:include page="WEB-INF/repeatedCode/footer.jsp"/>
<jsp:include page="WEB-INF/repeatedCode/htmlEnding.jsp"/>