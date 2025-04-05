document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.tile, .extra-tile').forEach(tile => {
        tile.addEventListener('click', () => {
            tile.style.backgroundColor = tile.style.backgroundColor === 'yellow' ? '#f0f0f0' : 'yellow';
        });
    });
});
// Fonction pour obtenir les données du backend
function fetchData() {
    // URL du backend qui renvoie les données JSON
    const url = 'https://example.com/api/tiles'; // Remplacez cette URL par celle de votre backend

    fetch(url)
        .then(response => response.json())
        .then(data => fillTiles(data))
        .catch(error => console.error('Error fetching data:', error));
}

// Fonction pour remplir les éléments du tableau avec les données reçues
function fillTiles(data) {
    const tiles = document.querySelectorAll('.tile');
    tiles.forEach((tile, index) => {
        if (data[index]) {
            tile.textContent = data[index];
        } else {
            tile.textContent = '';
        }
    });
}

// Appeler la fonction fetchData au chargement de la page
document.addEventListener('DOMContentLoaded', fetchData);