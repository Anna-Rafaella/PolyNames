package controllers;

import dao.CardDAO;
import dao.PartyDAO;
import models.Party_Player;
import webserver.WebServerContext;

public class CardsControllers {
    private WebServerContext context; 
  

    public CardsControllers (){
        this.context=context;
    }

    public static void findAll(WebServerContext context) {
        //context.getResponse().ok("Tous les mots");
        CardDAO card = new CardDAO();
        context.getResponse().json(card.findAll());
               
    }
  
}
