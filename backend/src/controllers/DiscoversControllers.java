package controllers;

import dao.DiscoverDAO;
import webserver.WebServerContext;

public class DiscoversControllers {
    private WebServerContext context; 
  

    public  DiscoversControllers (){
        this.context=context;
    }

    public static void findAll(WebServerContext context) {
        //context.getResponse().ok("Tous les mots");
        DiscoverDAO word = new DiscoverDAO();
        context.getResponse().json( word.findAll());
           
           
    }
}
