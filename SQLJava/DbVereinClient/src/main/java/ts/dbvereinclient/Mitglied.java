package ts.dbvereinclient;

import java.util.Date;

public class Mitglied {
    long id;
    String vorname;
    String nachname;
    Date geburtsdatum;
    char geschlecht;
    String strasse;
    Ort ort;
    String telefonnummer;
    Date eintrittsdatum;

    public Mitglied(long id,
                    String vorname,
                    String nachname,
                    Date geburtsdatum,
                    char geschlecht,
                    String strasse,
                    Ort ort,
                    String telefonnummer,
                    Date eintrittsdatum) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.geschlecht = geschlecht;
        this.strasse = strasse;
        this.ort = ort;
        this.telefonnummer = telefonnummer;
        this.eintrittsdatum = eintrittsdatum;
    }

    public long getId() {
        return id;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public char getGeschlecht() {
        return geschlecht;
    }

    public String getStrasse() {
        return strasse;
    }

    public Ort getOrt() {
        return ort;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public Date getEintrittsdatum() {
        return eintrittsdatum;
    }


}
