package controllers;

import dao.TourDAO;
import webserver.WebServerContext;

public class ToursControllers {
    private WebServerContext context; 
  

    public ToursControllers  (){
        this.context=context;
    }

    public static void findAll(WebServerContext context) {
        //context.getResponse().ok("Tous les mots");
        TourDAO player = new TourDAO();
        context.getResponse().json( player.findAll());
           
           
    }
}
