document.addEventListener('DOMContentLoaded', (event) => {
    const socket = io();

    document.getElementById('createRoomForm').addEventListener('createTheRoomBtn', (event) => {
        event.preventDefault();
        
        const username = document.getElementById('username').value;
        document.getElementById('createRoomForm').addEventListener('submit', function (e) {
            e.preventDefault();
            const username = document.getElementById('username').value;
            const role = document.getElementById('mySelect').value;
            
            socket.emit('create_room.html', { username, role });
        });
        
        socket.on('createTheRoomBtn', (data) => {
            const { roomId, username, role } = data;
            window.location.href = `/board.html?roomId=${roomId}&username=${username}&role=${role}`;
        });

        if (username) {
            socket.emit('createTheRoomBtn', { user: username, });
        } else {
            document.getElementById('error-message').textContent = "Toutes les informations sont requises pour créer une salle.";
        }
    });

   socket.on('error-message', (message) => {
        document.getElementById('error-message').textContent = message;
    });

    socket.on('putInGame', (roomID) => {
        window.location.href = `/game.html?room=${roomID}`;
    });
    document.getElementById('createTheRoomBtn').addEventListener('click', () => { window.location.href = 'board.html'});
});