package ts.dbvereinclient;

public class Sportart {
    private int sport_Id;
    private String sportart;
    private float beitrag;

    public Sportart(int sport_Id, String sportart, float beitrag){
        this.setSport_Id(sport_Id);
        this.setSportart(sportart);
        this.setBeitrag(beitrag);
    }

    public Sportart(String sportart, float beitrag){
        this.setSport_Id(0);
        this.setSportart(sportart);
        this.setBeitrag(beitrag);
    }

    public int getSport_Id() {return sport_Id;}
    public void setSport_Id(int sport_Id) {this.sport_Id = sport_Id;}

    public String getSportart() {return sportart;}
    public void setSportart(String sportart) {this.sportart = sportart;}

    public float getBeitrag() {return beitrag;}
    public void setBeitrag(float beitrag) {this.beitrag = beitrag;}
}
