
export class Partyservices {
    Partyservices(){
    }
    static async createParty(name, rule) {
        
        const headers = new Headers();
        headers.append("Content-Type", "application/json");

        const response = await fetch("http://localhost:8080/party",{
            method:"POST",
            headers: headers,
            body: JSON.stringify({name: name, rule: rule})
        });
        if(response.status === 200){
        const party = await response.json();
        return party;
        }
    
    }
}