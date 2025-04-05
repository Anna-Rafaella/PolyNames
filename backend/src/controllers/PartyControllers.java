package controllers;

import dao.PartyDAO;
import models.Party;
import models.Party_Player;
import webserver.WebServerContext;

public class PartyControllers {
    private WebServerContext context; 
  

    public  PartyControllers (){
        this.context=context;
    }

    public static void findAll(WebServerContext context) {
        //context.getResponse().ok("Tous les mots");
        PartyDAO card = new PartyDAO();
        context.getResponse().json(card.findAll());      
           
    }
    public static void createParty(WebServerContext context) {
        //context.getResponse().ok("Tous les mots");
        PartyDAO party = new PartyDAO();
        Party_Player party_Player= context.getRequest().extractBody(Party_Player.class);     
        
        System.out.println(party_Player);
        boolean part = party.createParty(party_Player);     
    }

}
