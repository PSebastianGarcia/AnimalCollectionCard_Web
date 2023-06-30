const showButtonBodyCard = document.getElementById('show_button_body_card');
const animalBodyCard = document.getElementsByClassName('animal_body_card');

const cardBody = {

    body: false,

    visibleBody: function () {
        this.body = !this.body;
    }
};

function visibility() {

    toggleVisibilityList(animalBodyCard);
    changeButton();
}

function changeButton() {

    cardBody.visibleBody();
    if (cardBody.body) {
        showButtonBodyCard.innerHTML = "Ocultar detalles";
    } else {
        showButtonBodyCard.innerHTML = "Mostrar detalles";
    }    
}

function toggleVisibilityList(list) {

    if (!cardBody.body) {
        for (let i = 0; i < list.length; i++) {
            turnVisible(list[i]);
        }
    } else {
        for (let i = 0; i < list.length; i++) {
            turnInvisible(list[i]);
        }
    }
}

//function turnVisibleFile() {
//    turnVisible(file);
//    turnInvisibleButton(createTemplateFileButton);
//}
//function turnInvisibleFile() {
//    turnInvisible(file);
//    turnVisibleButton(createTemplateFileButton);
//}
//
//function turnVisibleButton(button) {
//    turnVisible(button);
//}
//function turnInvisibleButton(button) {
//    turnInvisible(button);
//}

function turnVisible(element) {

    element.style.display = "block";
}
function turnInvisible(element) {

    element.style.display = "none";
}

showButtonBodyCard.onclick = visibility;
//cancelCreationFileButton.onclick = turnInvisibleFile;

