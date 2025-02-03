
/**
 * Function which update the error layout of the hangman game
 * @param {numeric} nb_error 
 * @returns nb_error updated 
 */
function UpdateErrorLayout(nb_error){
    let error_cpt = document.getElementById("nb_error");
    let hangman_picture = document.getElementById("hangman");
    nb_error++;
    error_cpt.textContent = "Error " + nb_error + "/6";
    hangman_picture.src = `../images/hangman-${nb_error}.svg`; // Change la source de l'image
    return nb_error;
}

/**
 * Function that browse the entire word to find where is the letter
 * @param {string} word 
 * @param {char} letter 
 * @param {numeric} nb_letter_to_discover 
 * @returns numeric
 */
function SearchAllReferences(word, letter, nb_letter_to_discover){
    for (let i = 0; i < word.length; i++) {
        if (word[i] === letter) {
            const letter_html = document.getElementById(`letter-${i}`);
            if (letter_html) {
                letter_html.style.color = "black";
                nb_letter_to_discover--;
            }  
        } 
    }
    return nb_letter_to_discover;
}


/**
 * @param {string} letter 
 * This function is checking if the letter chosen by the user is in the mystery word or not
 */
function VerifyLetter(letter) {
    const container = document.getElementById("container");
    const word = container.getAttribute("data-mystery-word").toLowerCase();
    let nb_letter_to_discover = container.getAttribute("data-leaving-letters");
    let nb_error = container.getAttribute("data-nb_error");
    letter = letter.toLowerCase();
    if (!word.includes(letter)){ // check si la lettre n'est pas présente dans le mot
        nb_error = UpdateErrorLayout(nb_error);
    } else { // sinon parcours le mot pour trouver les emplacements
        nb_letter_to_discover = SearchAllReferences(word, letter, nb_letter_to_discover);
    }
    if (nb_letter_to_discover === 0){ // si on a trouvé toutes les lettres
        generatePopUp("victory");
    } else if  (nb_error >= 6) // si on fait plus de 6 erreurs
        generatePopUp("defeat");

    container.setAttribute("data-leaving-letters", nb_letter_to_discover);
    container.setAttribute("data-nb_error", nb_error);
}

/**
 * @param {string} event either victory or defeat to show and load the correct message and gif
 */
function generatePopUp(event) {
    const dict_info = {
        "victory" : ["Congratulations !", "../images/victory.gif"],
        "defeat" : ["Too bad you loose !", "../images/lost.gif"]
    };
    // Créer le conteneur du pop-up
    let popup = document.createElement("div");
    popup.className = "popup";
    
    let popup_content = document.createElement("div")
    popup_content.className = "popup_content";

    const message = document.createElement("p");
    message.textContent = dict_info[event][0];

    const image = document.createElement("img");
    image.src = dict_info[event][1];
    image.alt = dict_info[event][0] + ".gif";

    // croix pour fermer la pop-up
    let closeButton = document.createElement("span");
    closeButton.className = "close";
    closeButton.innerHTML = "&times;";
    closeButton.onclick = function() {
        location.reload();
    };

    popup_content.appendChild(closeButton);
    popup_content.appendChild(message);
    popup_content.appendChild(image);

    popup.appendChild(popup_content);
    
    // Ajoute la pop up au body
    document.body.appendChild(popup);
}



function handleLetterClick(button) {
    button.style.backgroundColor = 'gray'; 
    button.style.cursor = "default";
    VerifyLetter(button.textContent);
    button.removeEventListener("click", handleLetterClickWrapper); // Supprime l'écouteur d'événement après la première exécution
}

function handleLetterClickWrapper(event) {
    handleLetterClick(event.currentTarget);
}

function showHint() {
    const definition = document.getElementById("definition");
    definition.style.display = "block";
}

function handleKeyPress(event) {
    const letter = event.key.toUpperCase(); // récupération de la touche entrée
    const buttons = document.getElementsByClassName("letters");
    for (let button of buttons) { // vérification si cetet touche correspond à une lettre du clavier virtuel
        if (button.textContent === letter) {
            handleLetterClick(button);
            break;
        }
    }
}
// JS vanilla
document.addEventListener("DOMContentLoaded", () => {
    const buttons = document.getElementsByClassName("letters");
    for (let button of buttons) {
        button.addEventListener("click", handleLetterClickWrapper);
    }
    document.addEventListener("keydown", handleKeyPress); // Ajoute l'écouteur d'événements pour les touches du clavier
});



// // JQuery
// $(document).ready(function() {
//     $(".letters").click(function() {
//         handleLetterClick(this);
//     });
// });