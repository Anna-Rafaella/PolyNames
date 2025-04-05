document.addEventListener('DOMContentLoaded', (event) => {
   // const socket = io();

    document.getElementById('createRoomBtn').addEventListener('click', () => {
        const username = prompt("Entrez votre nom d'utilisateur:");
        const roomID = prompt("Entrez l'ID de la salle:");
        const password = prompt("Entrez le mot de passe:");

        if (username && roomID && password) {
            socket.emit('createRoom', { user: username, id: roomID, pass: password });
        } else {
            alert("Toutes les informations sont requises pour créer une salle.");
        }
    });

    document.getElementById('joinRoomBtn').addEventListener('click', () => {
        const username = prompt("Entrez votre nom d'utilisateur:");
        const roomID = prompt("Entrez l'ID de la salle:");
        //
        
        const password = prompt("Entrez le mot de passe:");

        if (username ) {
            socket.emit('joinRoom', { user: username });
        } else {
            alert("Toutes les informations sont requises pour rejoindre une salle.");
        }
    });

    socket.on('error-message', (message) => {
        document.getElementById('error-message').textContent = message;
    });

    socket.on('putInGame', (roomID) => {
        window.location.href = `/game.html?room=${roomID}`;
    });

    socket.on('joinGame', (roomID, gameData) => {
        window.location.href = `/room.html?room=${roomID}`;
    });
});
