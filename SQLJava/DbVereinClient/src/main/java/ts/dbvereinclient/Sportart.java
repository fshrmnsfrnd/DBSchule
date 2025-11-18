package ts.dbvereinclient;

public class Sportart {
    private long sportId;
    private String sportart;
    private float beitrag;

    public Sportart(long sportId, String sportart, float beitrag) {
        setSportart(sportart);
        setBeitrag(beitrag);
        setSportId(sportId);
    }

    public long getSportId() {
        return sportId;
    }

    public void setSportId(long sportId) {
        this.sportId = sportId;
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

    public void setBeitrag(float beitrag) {
        this.beitrag = beitrag;
    }

    public Sportart clone(){
        return new Sportart(this.sportId, this.sportart, this.beitrag);
    }
}
