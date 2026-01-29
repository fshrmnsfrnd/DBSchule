/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ts.blogic;

/**
 *
 * @author ml
 */
public class Buch {
    private int id;
    private String isbn;
    private String titel;
    private String autor;
    private String schlagworte;

    public Buch(int buchId, String isbn, String titel,String autor, String schlagworte) {
        if (buchId >= 0 && isbn.length()==13 && titel.length() >= 1 && autor.length() >= 1){
            this.id = buchId;
            this.isbn = isbn;
            this.titel = titel; 
            this.autor = autor;
            this.schlagworte = schlagworte;
        }
        else{
            throw new RuntimeException("Buch kann nicht erstellt werden, Eigenschaften ungültig!");
        }
    }
        
    public int getId() {
        return id;
    }
    
    protected void setId(int id){
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitel() {
        return titel;
    }

    public String getAutor() {
        return autor;
    }

    public String getSchlagworte() {
        return schlagworte;
    }

    
    
    
}


