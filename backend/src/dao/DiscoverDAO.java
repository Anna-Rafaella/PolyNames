package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.PolyNamesDatabase;
import models.Discover;

public class DiscoverDAO {
    private PolyNamesDatabase Mydatabase;

    // Constructeur prenant en paramètre la connexion à la base de données
    public DiscoverDAO() {
        try {
            this.Mydatabase=new PolyNamesDatabase("localhost", 3306, "polynames","root","");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public ArrayList findAll() {

        try {
            ArrayList discovers = new ArrayList<>();
            String query = "SELECT * FROM discover ORDER BY ID_discover ASC";
            PreparedStatement statement = Mydatabase.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ID_discover = resultSet.getInt("ID_discover");
                int Tour_id = resultSet.getInt("Tour_id");
                int Word_id = resultSet.getInt("Word_id");

                Discover discover1 = new Discover(ID_discover,Tour_id,Word_id );
                discovers.add(discover1);
            }
            return discovers;
        } catch(SQLException e) {
             System.err.println(e);
             return new ArrayList<>();
         }
     }
}

