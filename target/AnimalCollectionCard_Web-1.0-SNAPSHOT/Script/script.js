const cardButton = document.getElementById('show_button_body_card');
const formatButton = document.getElementById('toggle_format_button');
const animalBodyCards = document.getElementsByClassName('animal_body_card');
const animalTable = document.getElementById('animal_table');
const cardsContainer = document.getElementById('cards_container');

const formatButtonObject = {

    card: false,

    isCard: function () {
        this.card = !this.card;
    }
};

const cardButtonObject = {

    visible: false,

    isVisible: function () {
        this.visible = !this.visible;
    }
};

const cardBody = {

    show: false,

    isShow: function () {
        this.show = !this.show;
    }
};

formatButton.onclick = toggleFormat;

function toggleFormat() {
    toggleButtonSection();
    toggleFormatInfo();
}

function toggleButtonSection() {

    toggleCardButtonVisibility();
    toggleNameFormatButton();
}

function toggleCardButtonVisibility() {

    if (!cardButtonObject.visible) {
        turnVisible(cardButton);
    } else {
        turnInvisible(cardButton);
    }
    cardButtonObject.isVisible();
}

function toggleNameFormatButton() {

    if (!formatButtonObject.card) {
        formatButton.innerHTML = "Formato Tabla";
    } else {
        formatButton.innerHTML = "Formato Carta";
    }
    formatButtonObject.isCard();
}

function toggleFormatInfo() {

    if (formatButtonObject.card) {
        turnInvisible(animalTable);
        turnVisible(cardsContainer);
    } else {
        turnInvisible(cardsContainer);
        turnVisible(animalTable);
    }
}

cardButton.onclick = toggleBodyCardVisibility;

function toggleBodyCardVisibility() {

    toggleNameCardButton();
    toggleVisibilityList(animalBodyCards);
    cardBody.isShow();
}

function toggleNameCardButton() {

    if (!cardBody.show) {
        cardButton.innerHTML = "Ocultar detalles";
    } else {
        cardButton.innerHTML = "Mostrar detalles";
    }
}

function toggleVisibilityList(list) {

    if (!cardBody.show) {
        for (let i = 0; i < list.length; i++) {
            turnVisible(list[i]);
        }
    } else {
        for (let i = 0; i < list.length; i++) {
            turnInvisible(list[i]);
        }
    }
}

function turnVisible(element) {

    element.classList.remove("disp_none");
}

function turnInvisible(element) {

    element.classList.add("disp_none");
}
