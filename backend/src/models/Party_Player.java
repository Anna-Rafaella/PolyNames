package models;

public record Party_Player(
    int ID_player, 
    String name, 
    String rule, 
    int party_ID,
    int ID_party,
    int total_score,
    int unique_code,
    String status) {

}
