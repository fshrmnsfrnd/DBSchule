package ts.dbvereinclient;

import java.util.Date;

public class Mitglied {
    long id;
    String vorname;
    String nachname;
    Date geburtsdatum;
    char geschlecht;
    String strasse;
    long ort_id;
    String telefonnummer;
    Date eintrittsdatum;

    public Mitglied(long id,
    String vorname,
    String nachname,
    Date geburtsdatum,
    char geschlecht,
    String strasse,
    long ort_id,
    String telefonnummer,
    Date eintrittsdatum) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.geschlecht = geschlecht;
        this.strasse = strasse;
        this.ort_id = ort_id;
        this.telefonnummer = telefonnummer;
        this.eintrittsdatum = eintrittsdatum;
    }
}
