import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import controllers.WordsControllers;
import dao.PartyDAO;
import models.Party;
import controllers.PlayersControllers;
import controllers.CardsControllers;
import controllers.ToursControllers;
import controllers.PartyControllers;
import controllers.ColorsControllers;
import controllers.DiscoversControllers;
import webserver.WebServer;
import webserver.WebServerContext;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Connection connection = null;
        try {
            //Connexion à la base de donnée
             // Établir une connexion à la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/polynames", "root", "");
            //PolyBayDatabase MyDatabase = PolyBayDatabase.getInstance();
            System.out.println("Connexion à la base donnée polynames réussie avec succès");
            // ProductDAO produits2 = new ProductDAO();
            // // Appeler la méthode findAll pour récupérer tous les produits
            // System.out.println(produits2.findAll());  

            WebServer webserver= new WebServer();
            webserver.listen(8080);
            
    
            PartyControllers partyControllers= new PartyControllers();

            // Créer une route GET pour /party
            webserver.getRouter().post("/party", (WebServerContext context) -> {
            // Appeler la méthode findAll de PsController
            PartyControllers.createParty(context);}
            );

        } catch (SQLException e) {
            System.err.println();
            e.printStackTrace();
        }
       
    }
}
