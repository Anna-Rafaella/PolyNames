package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import database.PolyNamesDatabase;
import models.Card;
import models.Word;
import models.Color;




public class CardDAO {
    private PolyNamesDatabase Mydatabase;

    // Constructeur prenant en paramètre la connexion à la base de données
    public CardDAO() {
        try {
            this.Mydatabase=new PolyNamesDatabase("localhost", 3306, "polynames","root","");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public ArrayList findAll() {
        try {
          ArrayList cards = new ArrayList<>();
          String query = "SELECT * FROM card ORDER BY ID_card ASC";
          PreparedStatement statement = Mydatabase.prepareStatement(query);
          ResultSet resultSet = statement.executeQuery();
      
          while (resultSet.next()) {
            int ID_card= resultSet.getInt("ID_card");
            int ID_party  = resultSet.getInt("ID_party");
            int ID_word  = resultSet.getInt("ID_word");
            int ID_color  = resultSet.getInt("ID_color");
            
            Card card1 = new Card(ID_card, ID_party, ID_word,ID_color); 
          
            cards.add(card1);
           }
        return cards;
      
        }catch(SQLException e) {
           System.err.println(e);
           return new ArrayList<>();
         }
      }
      public List<Card> generateCards() {
        try {
            WordDAO wordDAO = new WordDAO();
            ColorDAO colorDAO = new ColorDAO();
            List<Word> words = wordDAO.findAll();
            List<Color> colors = colorDAO.findAll();

            List<Card> cards = new ArrayList<>();
            Random random = new Random();

            for (int i = 0; i < words.size(); i++) {
                int idCard = i + 1;
                int idWord = words.get(i).ID_word();
                int idColor = colors.get(random.nextInt(colors.size())).ID_color();
                cards.add(new Card(idCard, 0, idWord, idColor));
            }

            return cards;
        } catch (Exception e) {
            System.err.println(e);
            return new ArrayList<>();
        }
    }
}
