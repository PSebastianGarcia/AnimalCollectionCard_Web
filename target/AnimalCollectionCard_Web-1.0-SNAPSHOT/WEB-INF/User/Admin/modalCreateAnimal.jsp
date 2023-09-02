<div class="modal fade" id="modalCreateAnimal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Create Animal</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form id="formCreateAnimal" action="${pageContext.request.contextPath}/card?action=insert&detail=animal"
                  method="post" class="was-validated">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-6 mb-3">
                            <label for="name" class="form-label">Name</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <label for="genus" class="form-label">Genus</label>
                            <input type="text" class="form-control" id="genus" name="genus" required>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <label for="species" class="form-label">Species</label>
                            <input type="text" class="form-control" id="species" name="species" required>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <label for="foto" class="form-label">Foto</label>
                            <input type="text" class="form-control" id="foto" name="foto" required>
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