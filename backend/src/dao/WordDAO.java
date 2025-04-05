package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.PolyNamesDatabase;
import models.Word;

public class WordDAO {
    private PolyNamesDatabase Mydatabase;

    // Constructeur prenant en paramètre la connexion à la base de données
    public WordDAO() {
        try {
            this.Mydatabase=new PolyNamesDatabase("localhost", 3306, "polynames","root","");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public ArrayList findAll() {

        try {
            ArrayList words = new ArrayList<>();
            String query = "SELECT * FROM word ORDER BY value ASC";
            PreparedStatement statement = Mydatabase.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ID_word = resultSet.getInt("ID_word");
                String value = resultSet.getString("value");

                Word word1 = new Word(ID_word,value );
                words.add(word1);
            }
            return words;
        } catch(SQLException e) {
             System.err.println(e);
             return new ArrayList<>();
         }
     }
}