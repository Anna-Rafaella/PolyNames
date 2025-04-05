package models;

public record Card(int ID_card,int ID_party,int ID_word,int ID_color) {

    public Card(int ID_card, int ID_party,int ID_word,int ID_color)  {
        this.ID_card=  ID_card;
        this.ID_party= ID_party;
        this.ID_word = ID_word;
        this.ID_color= ID_color;
    }
}