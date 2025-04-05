package models;

public record Word(int ID_word,String value)
{
    public Word(int ID_word, String value) {
        this.ID_word= ID_word;
        this.value= value;
   
    }
    
}
