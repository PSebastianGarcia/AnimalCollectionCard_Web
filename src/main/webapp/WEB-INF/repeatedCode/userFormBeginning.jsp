<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="mb-3">
    <label for="fName" class="form-label">First Name</label>
    <input type="text" class="form-control" id="FirstName" name="fName" value="${user != null ? user.getFirstName() : ''}" aria-describedby="basic-addon1">
</div>
<div class="mb-3">
    <label for="lName" class="form-label">Last Name</label>
    <input type="text" class="form-control" id="LastName" name="lName" value="${user != null ? user.getLastName() : ''}" aria-describedby="basic-addon1">
</div>
<div class="mb-3">
    <label for="email" class="form-label">E-mail Address</label>
    <input type="email" class="form-control" id="userEmail" name="email" value="${user != null ? user.getEmail() : ''}" aria-describedby="emailHelp">
    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
</div>
<div class="mb-3">
    <label for="uName" class="form-label">User Name</label>
    <input type="text" class="form-control" id="UserName" name="uName" value="${user != null ? user.getName() : ''}" aria-describedby="basic-addon1">
</div>

