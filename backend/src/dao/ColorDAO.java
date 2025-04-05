package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.PolyNamesDatabase;
import models.Color;

public class ColorDAO {
    private PolyNamesDatabase Mydatabase;

    // Constructeur prenant en paramètre la connexion à la base de données
    public ColorDAO() {
        try {
            this.Mydatabase=new PolyNamesDatabase("localhost", 3306, "polynames","root","");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public ArrayList findAll() {

        try {
            ArrayList colors = new ArrayList<>();
            String query = "SELECT * FROM color ORDER BY ID_color ASC";
            PreparedStatement statement = Mydatabase.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ID_color= resultSet.getInt("ID_color");
                String name = resultSet.getString("name");

                Color color1 = new Color(ID_color,name );
                colors.add(color1);
            }
            return colors;
        } catch(SQLException e) {
             System.err.println(e);
             return new ArrayList<>();
         }
     }
}