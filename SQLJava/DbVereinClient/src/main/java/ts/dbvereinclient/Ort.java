package ts.dbvereinclient;

public class Ort {
    long ort_id;
    String plz;
    String ort;

    public Ort(
            long ort_id,
            String plz,
            String ort
    ){
        this.ort_id = ort_id;
        this.plz = plz;
        this.ort = ort;
    }

    public long getOrt_id() {
        return ort_id;
    }

    public String getPlz() {
        return plz;
    }

    public String getOrt() {
        return ort;
    }
}
