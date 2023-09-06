<div class="modal fade" id="modalCreateUser" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Create User</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form id="formCreateUser" action="${pageContext.request.contextPath}/signIn?id=${idAdmin}"
                  method="post" class="was-validated">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-6 mb-3">
                            <label for="fName" class="form-label">First Name</label>
                            <input type="text" class="form-control" id="fName" name="fName" required>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <label for="lName" class="form-label">Last Name</label>
                            <input type="text" class="form-control" id="lName" name="lName" required>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="text" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <label for="uName" class="form-label">User Name</label>
                            <input type="text" class="form-control" id="uName" name="uName" required>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <label for="uPass" class="form-label">Password</label>
                            <input type="text" class="form-control" id="uPass" name="uPass" required>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <label for="eCard" class="form-label">¿Con carta vacía?</label>
                            <select class="form-control" id="eCard" name="eCard" required>
                                <option value="true">Sí</option>
                                <option value="false">No</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-success">Create</button>
                </div>
            </form>
        </div>
    </div>
</div>