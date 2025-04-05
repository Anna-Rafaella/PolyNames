import { PartyView } from "./view/partyview.js";

async function run() {
    const partyForm = document.getElementById('createRoomForm');

    partyForm.addEventListener('submit', async (event) => {
        event.preventDefault(); // Empêche le rechargement de la page

        const name = document.getElementById('username').value;
        const rule= document.getElementById('mySelect').value;
    

        // Création de la partie avec PartyView
        try {
            let partyView = new PartyView();
            await partyView.createParty(name,rule); // Supposons que PartyView ait une méthode createParty()
            alert('Partie créée avec succès!');
            // Réinitialiser le formulaire ou effectuer d'autres actions après la création
        } catch (error) {
            console.error('Erreur lors de la création de la partie :', error);
            alert('Erreur lors de la création de la partie. Veuillez réessayer.');
        }
    });
}

window.addEventListener("load", () => {
    run();
});
