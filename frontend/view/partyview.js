import { Partyservices } from "../service/partyservices.js";

export class PartyView {
  
    async createParty(name, rule){
        const result= await Partyservices.createParty(name, rule);
        result.forEach(element => {
            displayParty(element);
        });
    }

    displayParty(party){
        console.log(party);
    }

}