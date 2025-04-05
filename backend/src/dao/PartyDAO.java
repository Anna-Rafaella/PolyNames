package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import database.PolyNamesDatabase;
import models.Party;
import models.Party_Player;
import models.Player;

public class PartyDAO {
    private PolyNamesDatabase Mydatabase;

    public PartyDAO() {
        try {
            this.Mydatabase = new PolyNamesDatabase("localhost", 3306, "polynames", "root", "");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public ArrayList<Party> findAll() {
        try {
            ArrayList<Party> parties = new ArrayList<>();
            String query = "SELECT * FROM party ORDER BY ID_party ASC";
            PreparedStatement statement = Mydatabase.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ID_party = resultSet.getInt("ID_party");
                int total_score = resultSet.getInt("total_score");
                int unique_code = resultSet.getInt("unique_code");
                String status = resultSet.getString("status");

                Party party1 = new Party(ID_party, total_score, unique_code, status);

                parties.add(party1);
            }
            return parties;

        } catch (SQLException e) {
            System.err.println(e);
            return new ArrayList<>();
        }
    }

    public boolean createParty(Party_Player party_Player) {
        try {
            int uniqueCode = generateUniqueCode();
            String query = "INSERT INTO party (total_score, unique_code, status) VALUES (?, ?, ?)";
            
            // Préparation de la requête avec RETURN_GENERATED_KEYS
            PreparedStatement statement = Mydatabase.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, 0); // total_score initial
            statement.setInt(2, uniqueCode);
            statement.setString(3, "encours");

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating party failed, no rows affected.");
            }

            // Récupération des clés générées (ID_party)
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int ID_party = generatedKeys.getInt(1);
                    addPlayer(party_Player.name(),party_Player.rule(),ID_party); // Ajout du joueur à la partie
         
                } else {
                    throw new SQLException("Creating party failed, no ID obtained.");
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    private void addPlayer(String name,String rule,int ID_party) throws SQLException {
        String query = "INSERT INTO player (name, rule, party_ID) VALUES (?, ?, ?)";
        PreparedStatement statement = Mydatabase.prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, rule);
        statement.setInt(3, ID_party);

        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Adding player failed, no rows affected.");
        }
    }

    private int generateUniqueCode() {
        Random random = new Random();
        return 10000000 + random.nextInt(90000000);
    }
}
