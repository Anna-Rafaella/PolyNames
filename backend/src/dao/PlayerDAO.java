package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.PolyNamesDatabase;
import models.Player;



public class PlayerDAO {
    private PolyNamesDatabase Mydatabase;

    // Constructeur prenant en paramètre la connexion à la base de données
    public PlayerDAO() {
        try {
            this.Mydatabase=new PolyNamesDatabase("localhost", 3306, "polynames","root","");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public ArrayList findAll() {
        try {
          ArrayList players = new ArrayList<>();
          String query = "SELECT * FROM player ORDER BY name ASC";
          PreparedStatement statement = Mydatabase.prepareStatement(query);
          ResultSet resultSet = statement.executeQuery();
      
          while (resultSet.next()) {
            int ID_player= resultSet.getInt("ID_player");
            String name = resultSet.getString("name");
            String role = resultSet.getString("role");
            int party_ID = resultSet.getInt("party_ID");
           
      
            Player player1 = new Player(ID_player, name, role, party_ID);
             players.add(player1);
           }
         return players;
      
        }catch(SQLException e) {
           System.err.println(e);
           return new ArrayList<>();
         }
      }
}
