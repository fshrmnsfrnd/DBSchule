/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hibernate.blogic;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Mitglied {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "ort_id")
    private Ort ort;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "mitglied_sportart",
            joinColumns = @JoinColumn(name = "mitglied_id"),
            inverseJoinColumns = @JoinColumn(name = "sportart_id")
    )
    private Set<Sportart> sportarten = new HashSet<>();
  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ort getOrt() {
        return ort;
    }

    public void setOrt(Ort ort) {
        this.ort = ort;
    }

    public Set<Sportart> getSportarten() {
        return sportarten;
    }

}
