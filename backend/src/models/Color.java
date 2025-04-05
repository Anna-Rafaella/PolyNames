package models;

public record Color(int ID_color,String name)
{
    public Color(int ID_color,String name){
        this.ID_color= ID_color;
        this.name= name;
   
    }
    
}
