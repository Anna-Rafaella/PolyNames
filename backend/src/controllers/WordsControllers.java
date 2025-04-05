package controllers;

import dao.WordDAO;
import webserver.WebServerContext;

public class WordsControllers {
    private WebServerContext context; 
  

    public WordsControllers (){
        this.context=context;
    }

    public static void findAll(WebServerContext context) {
        //context.getResponse().ok("Tous les mots");
        WordDAO word = new WordDAO();
        context.getResponse().json( word.findAll());
           
           
    }
}
