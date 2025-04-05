package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.PolyNamesDatabase;
import models.Tour;



public class TourDAO {
    private PolyNamesDatabase Mydatabase;

    // Constructeur prenant en paramètre la connexion à la base de données
    public TourDAO() {
        try {
            this.Mydatabase=new PolyNamesDatabase("localhost", 3306, "polynames","root","");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public ArrayList findAll() {
        try {
          ArrayList tours = new ArrayList<>();
          String query = "SELECT * FROM tour ORDER BY ID_tour ASC";
          PreparedStatement statement = Mydatabase.prepareStatement(query);
          ResultSet resultSet = statement.executeQuery();
      
          while (resultSet.next()) {
            int ID_tour= resultSet.getInt("ID_tour");
            int number= resultSet.getInt("number");
            String indice = resultSet.getString("indice");
            int score= resultSet.getInt("score");
            int Id_party= resultSet.getInt("Id_party");
           
      
            Tour tour1 = new Tour(ID_tour,number,indice,score,Id_party);
             tours.add(tour1);
           }
         return tours;
      
        }catch(SQLException e) {
           System.err.println(e);
           return new ArrayList<>();
         }
      }
}