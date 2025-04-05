document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('joinRoomForm');
    const errorMessageDiv = document.getElementById('error-message');

    form.addEventListener('joinTheRoomBtn', (event) => {
        event.preventDefault(); // Empêche l'envoi par défaut du formulaire

        const username = document.getElementById('username').value.trim();
        const role = document.getElementById('mySelect').value;

        // Vérifier que le nom d'utilisateur n'est pas vide
        if (username === "") {
            displayErrorMessage("Le nom d'utilisateur est requis.");
            return;
        }

        // Vérifier que l'utilisateur a sélectionné un rôle valide
        if (role === "choisir un role") {
            displayErrorMessage("Veuillez choisir un rôle.");
            return;
        }

        // Si toutes les vérifications passent, envoyer les données au serveur
        const socket = io(); // Assurez-vous que socket.io est chargé correctement

        socket.emit('joinRoom', { user: username, id: 'defaultRoom', pass: 'defaultPass' });

        socket.on('joinSuccess', () => {
            // Rediriger vers la salle de jeu ou afficher un message de succès
            window.location.href = '/board.html';
        });

        socket.on('error-message', (message) => {
            // Afficher le message d'erreur retourné par le serveur
            displayErrorMessage(message);
        });
        document.getElementById('joinTheRoomBtn').addEventListener('click', () => { window.location.href = 'board.html'});
    });

    function displayErrorMessage(message) {
        errorMessageDiv.textContent = message;
        errorMessageDiv.style.color = 'red';
    }
});

