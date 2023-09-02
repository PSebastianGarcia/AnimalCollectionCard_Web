<div class="mb-3">
    <label for="uName" class="form-label">User Name</label>
    <input type="text" class="form-control" id="UserName" name="uName" value="${user != null ? user.getName() : ''}" aria-describedby="basic-addon1">
</div>
<div class="mb-3">
    <label for="uPass" class="form-label">Password</label>
    <input type="password" class="form-control" id="UserPassword" value="${user != null ? user.getPassword() : ''}" name="uPass">
</div>
