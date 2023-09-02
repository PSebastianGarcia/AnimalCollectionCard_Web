const deck = document.getElementById('reverse_deck');
const aBodyCards = document.getElementsByClassName('animal_body_card');
const cContainer = document.getElementById('cards_container');
const addLinkList = document.getElementsByClassName('add_card');
const cardTitleList = document.getElementsByClassName('card-title');

deck.onclick = showCards;

function showCards() {
    if (deckWork()) {
        turnVisibleTotalList(aBodyCards);
        turnVisible(cContainer);
        turnInvisible(deck);
    }
}

function deckWork(){
    let work = false;
    const deckClass = deck.classList.value;
    if(deckClass === "it_is_work"){
        work = true;
    }
    return work;
}

function turnInvisibleTotalList(list) {
    for (let link of list) {
        turnInvisible(link);
    }
}

function turnVisibleTotalList(list) {
    for (let link of list) {
        turnVisible(link);
    }
}

function turnVisible(element) {

    element.classList.remove("disp_none");
}

function turnInvisible(element) {

    element.classList.add("disp_none");
}

for (let i = 0; i < addLinkList.length; i++) {
    addLinkList[i].onclick = disableLinks;
}

function disableLinks() {
    for (let i = 0; i < addLinkList.length; i++) {
        turnInvisibleTotalList(addLinkList);
    }
}

