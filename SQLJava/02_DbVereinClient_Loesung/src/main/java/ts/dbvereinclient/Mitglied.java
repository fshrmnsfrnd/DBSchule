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
public class Mitglied {
    long mitgliedId;
    String vorname;
    String nachname;
    String geburtsdatum;
    String geschlecht;
    String strasse;
    long ortId;
    String telefonnr;
    String eintrittsdatum;
    
    public Mitglied(long mId, String vorname, String nachname, String geburtsdatum, 
                    String geschlecht, String strasse, long ortId, 
                    String telefonnr, String eintrittsdatum) {
        this.mitgliedId = mId;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.geschlecht = geschlecht;
        this.strasse = strasse;
        this.ortId = ortId;
        this.telefonnr = telefonnr;
        this.eintrittsdatum = eintrittsdatum;
    }

    public long getMitgliedId() {
        return mitgliedId;
    }

    public void setMitgliedId(long mId) {
        this.mitgliedId = mId;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public long getOrtId() {
        return ortId;
    }

    public void setOrtId(long ortId) {
        this.ortId = ortId;
    }

    public String getTelefonnr() {
        return telefonnr;
    }

    public void setTelefonnr(String telefonnr) {
        this.telefonnr = telefonnr;
    }

    public String getEintrittsdatum() {
        return eintrittsdatum;
    }

    public void setEintrittsdatum(String eintrittsdatum) {
        this.eintrittsdatum = eintrittsdatum;
    }
    public Mitglied clone()
    {
        return new Mitglied(mitgliedId, vorname, nachname, geburtsdatum, 
                    geschlecht, strasse, ortId, 
                    telefonnr, eintrittsdatum);
    }
  
}
