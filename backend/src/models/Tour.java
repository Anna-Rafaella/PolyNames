package models;

public record Tour(int ID_tour,int number,String indice, int score,int Id_party)
{
    public Tour(int ID_tour,int number,String indice, int score,int Id_party) {
        this.ID_tour= ID_tour;
        this.number= number;
        this.indice= indice;
        this.score= score;
        this.Id_party= Id_party;
        
    }
    
}
