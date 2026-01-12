/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.dbvereinclient;

/**
 *
 * @author Meinhard.Lingo
 */
public class Sportart {
    private long sportId;
    private String sportart;
    private float beitrag;

    public Sportart(long sportId, String sportart, float beitrag) {
        this.sportId = sportId;
        this.sportart = sportart;
        this.beitrag = beitrag;
    }

    
    public long getSportId() {
        return sportId;
    }

    public void setSportId(long sportId) {
        if (this.sportId == 0){
            this.sportId = sportId;
        }
        
    }

    public String getSportart() {
        return sportart;
    }

    public void setSportart(String sportart) {
        this.sportart = sportart;
    }

    public float getBeitrag() {
        return beitrag;
    }

    public void setBeitrag(float betrag) {
        this.beitrag = betrag;
    }  
    
    public Sportart clone(){
        return new Sportart(sportId,sportart,beitrag);
    }
}
