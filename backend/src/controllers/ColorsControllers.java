package controllers;

import dao.ColorDAO;
import webserver.WebServerContext;

public class ColorsControllers {
    private WebServerContext context; 
  

    public  ColorsControllers(){
        this.context=context;
    }

    public static void findAll(WebServerContext context) {
        //context.getResponse().ok("Tous les mots");
        ColorDAO card = new ColorDAO();
        context.getResponse().json(card.findAll());
           
           
    }
}
