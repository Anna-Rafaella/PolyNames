
package models;

public record Player(int ID_player, String name, String rule, int party_ID) {
    

    public Player(int ID_player, String name, String rule,int party_ID) {
        this.ID_player= ID_player;
        this.name = name;
        this.rule = rule;
        this.party_ID = party_ID;
    }
}