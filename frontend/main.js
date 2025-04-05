// partyview.js
export class PartyView {
    async createParty(name, rule) {
        
        try {
            // Appel à l'API ou service backend pour enregistrer les données dans la base de données
            const response = await fetch('/party', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ name, rule })
            });
                console.log(JSON.stringify({ name, rule }));
            if (!response.ok) {
                throw new Error('Erreur lors de l\'enregistrement des données.');
            }

            const data = await response.json();
            return data; // Peut retourner quelque chose si nécessaire
       
        } catch (error) {
            throw new Error('Erreur lors de la création de la partie : ' + error.message);
        }
    }
}
