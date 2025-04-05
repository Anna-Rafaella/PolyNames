const express = require("express");
const path = require("path");
const app = express();
const port = process.env.PORT || 5501;
const http = require('http').Server(app);
const io = require("socket.io")(http);



let rooms = {};
let gameMap = {};
let userMap = undefined;

const homePage = __dirname + '/index.html';
const gamePage = __dirname + '/game.html';
const createRoomPage = __dirname + '/create_room.html';
const joinRoomPage = __dirname + '/join_room.html';
const roomPage = __dirname + '/board.html';

app.use(express.static(path.join(__dirname, 'public')));

app.get('/', (req, res) => {
    res.sendFile(homePage);
});
app.get('/create_room.html', (req, res) => {
    res.sendFile(createRoomPage);
});
app.get('/join_room.html', (req, res) => {
    res.sendFile(joinRoomPage);
});
app.get('/board.html', (req, res) => {
    res.sendFile(roomPage);
});
app.get('/game.html', (req, res) => {
    res.sendFile(gamePage);
});


io.on('connection', (socket) => {
    console.log('A new user has connected.');
    socket.emit('message', 'Welcome to Codenames!');

    socket.on("disconnect", () => {
        io.emit('message', 'A user has left the server.');
    });

    socket.on('joinRoom', ({ user, id, pass }) => {
        console.log(`User ${user} wants to join room ${id} with password ${pass}`);
        let roomToJoin = rooms[id];
        if ((user === '') || (id === '')) {
            socket.emit('error-message', 'Please enter a username and room ID.');
        } else if (roomToJoin === undefined) {
            socket.emit('error-message', 'Room not found.');
        } else if (roomToJoin.pass !== pass) {
            socket.emit('error-message', 'Password incorrect.');
        } else if (roomToJoin.sockets.find(userName => user == userName) !== undefined) {
            socket.emit('error-message', 'Username taken.');
        } else {
            socket.join(id);
            socket.to(id).emit('message', 'A user has joined the server.');
            console.log(id);
            console.log(`User ${user} has joined room `);
            roomToJoin.sockets.push(user);
            console.log(roomToJoin.sockets);
            console.log(roomToJoin);
            console.log("gameMap: ", gameMap[id]);
            socket.emit('joinGame', id, gameMap[id]);
            socket.emit('updateUsers', rooms[id].sockets);
            socket.to(id).emit('updateUsers', rooms[id].sockets);
            socket.emit('createUserMap', socket.id, user);
            socket.emit('joinSuccess'); // Ajouter cet événement pour indiquer un succès de jointure
        }
    });

    socket.on('createRoom', ({ user, id, pass }) => {
        if ((user === '') || (id === '')) {
            socket.emit('error-message', 'You have left something empty.');
        } else if (rooms[id] !== undefined) {
            socket.emit('error-message', 'This room already exists.');
        } else {
            let newRoom = {
                id,
                pass,
                sockets: [],
            };
            rooms[newRoom.id] = newRoom;
            console.log(`Client ${user} wants to create ${id} with password ${pass}`);
            socket.join(id);
            newRoom.sockets.push(user);
            socket.emit('putInGame', id);
            socket.emit('updateUsers', rooms[id].sockets);
            socket.emit('createUserMap', socket.id, user);
        }
    });

    socket.on('game', (currentGame, roomID) => {
        gameMap[roomID] = currentGame;
        socket.broadcast.to(roomID).emit('addData', currentGame);
    });

    socket.on('gamePieceClick', (gamePieceID, gameRoom, userID) => {
        socket.emit('clickGamePiece', gamePieceID, userID);
        socket.to(gameRoom).emit('clickGamePiece', gamePieceID, userID);
    });

    socket.on('userMap', (roomID, userIDs) => {
        console.log('USERID: ', userIDs);
        console.log('ROOMID: ', roomID);
        if (userMap !== undefined) {
            let newUserMap = { ...userMap, ...userIDs };
            userMap = newUserMap;
        } else {
            userMap = userIDs;
        }
        console.log('userMap: ', userMap);
        socket.emit('updateUserMap', userMap);
        socket.to(roomID).emit('updateUserMap', userMap);
    });

    socket.on('userJoinTeam', (team, userID, roomID) => {
        socket.emit('userJoinTeam', team, userID);
        socket.to(roomID).emit('userJoinTeam', team, userID);
    });

    socket.on('userChangeRole', (user, role, roomID) => {
        console.log(user, role, roomID);
        socket.emit('userRoleChange', user, role);
        socket.to(roomID).emit('userRoleChange', user, role);
    });

    socket.on('endTurn', roomID => {
        socket.to(roomID).emit('endTurn');
    });

    socket.on('new-game', function() {
        socket.emit('new-game');
    });
});
app.use(express.static(path.join(__dirname, 'public')));

app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'create_room.html'));
});

app.get('/board.html', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'room.html'));
});

io.on('connection', (socket) => {
    console.log('Un utilisateur s\'est connecté');

    socket.on('create room', (data) => {
        // Logique de création de salle, par exemple, générer un ID de salle unique
        const roomId = 'room-id' + Math.floor(Math.random() * 10000);
        socket.join(roomId);
        socket.emit('room created', { roomId, username: data.username, role: data.role });
    });

    socket.on('disconnect', () => {
        console.log('Un utilisateur s\'est déconnecté');
    });
});

http.listen(port, function() {
    console.log("Server is listening on port: ", port);
});
