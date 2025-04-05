
package models;

public record Discover(int ID_discover,int Tour_id,int Word_id) {

    public Discover(int ID_discover,int Tour_id,int Word_id)  {
        this.ID_discover=  ID_discover;
        this.Tour_id = Tour_id;
        this.Word_id= Word_id;
        
    }
}