package controllers;

import dao.PlayerDAO;
import webserver.WebServerContext;

public class PlayersControllers {
    private WebServerContext context; 
  

    public PlayersControllers (){
        this.context=context;
    }

    public static void findAll(WebServerContext context) {
        //context.getResponse().ok("Tous les mots");
        PlayerDAO player = new PlayerDAO();
        context.getResponse().json( player.findAll());     
    }
    
    
}
