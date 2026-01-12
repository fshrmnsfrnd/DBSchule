/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ts.dbvereinclient;

/**
 *
 * @author Nico
 */
public class Sportart {
    private int sportId;
    private double beitrag;
    private String sportart;

    public Sportart(int sportId, double beitrag, String sportart) {
        this.sportId = sportId;
        this.beitrag = beitrag;
        this.sportart = sportart;
    }
    
    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public double getBeitrag() {
        return beitrag;
    }

    public void setBeitrag(double beitrag) {
        this.beitrag = beitrag;
    }

    public String getSportart() {
        return sportart;
    }

    public void setSportart(String sportart) {
        this.sportart = sportart;
    }
    
    public Sportart clone(){
        return this;
    }
}
