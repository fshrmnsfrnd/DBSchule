/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package hibernate.blogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.Set;

public class App {

    public static void main(String[] args) {
        try{
            //====================== Datenbank zurücksetzen
            Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbverein_orm","root","kennwort1");
            cnn.createStatement().execute("DROP DATABASE IF EXISTS dbverein_orm");
            cnn.createStatement().execute("CREATE DATABASE dbverein_orm");
            cnn.close();
        }
        catch(SQLException e){
            System.err.println(e);
        }
        
        
        //Ab hier hibernate
        SessionFactory factory
                = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        try {

            
            // ===================== INSERT =====================
            session.beginTransaction();
            Ort berlin = new Ort();
            berlin.setName("Berlin");

            Ort hamburg = new Ort();
            hamburg.setName("Hamburg");

            Sportart fussball = new Sportart();
            fussball.setName("Fußball");

            Sportart schwimmen = new Sportart();
            schwimmen.setName("Schwimmen");

            Mitglied anna = new Mitglied();
            anna.setName("Anna");
            anna.setOrt(berlin);
            anna.getSportarten().add(fussball);
            anna.getSportarten().add(schwimmen);

            Mitglied ben = new Mitglied();
            ben.setName("Ben");
            ben.setOrt(hamburg);
            ben.getSportarten().add(fussball);

            berlin.getMitglieder().add(anna);
            hamburg.getMitglieder().add(ben);

            session.persist(berlin);
            session.persist(hamburg);
            
            session.getTransaction().commit();
            
            // ===================== SELECT =====================
            session.beginTransaction();
            System.out.println("=== Alle Mitglieder ===");
            List<Mitglied> alle = session.createQuery(
                    "from Mitglied", Mitglied.class).list();
            for (Mitglied m : alle) {
                System.out.println("Mitglied: " + m.getName());
                System.out.println("Wohnort: " + m.getOrt().getName());
                System.out.println("Sportarten: ");        
                for (Sportart s : m.getSportarten()){
                    System.out.print("\n" + s.getName());
                }
            }
            session.getTransaction().commit();

            // ===================== FILTER =====================
            session.beginTransaction();
            System.out.println("=== Mitglieder aus Berlin ===");
            List<Mitglied> filtered = session.createQuery("from Mitglied m where m.ort.name= :ortName", Mitglied.class)
                    .setParameter("ortName", "Berlin").list();
            filtered.forEach(m -> System.out.println(m.getName()));
            session.getTransaction().commit();

            // ===================== UPDATE =====================
            session.beginTransaction();
            anna = session.get(Mitglied.class, anna.getId());
            anna.setName("Anna Müller");
            session.getTransaction().commit();

            // ===================== DELETE =====================
            session.beginTransaction();

            for (Sportart s : ben.getSportarten()) {
                s.getMitglieder().remove((ben));
            }

            ben.getSportarten().clear();

            ben.getOrt().getMitglieder().remove(ben);
            ben.setOrt(null);

            session.remove(ben); // Mitglied Ben löschen

            session.getTransaction().commit();

        } catch (Exception e) {
            System.err.println(e);

        } finally {
            session.close();
            factory.close();
        }
    }
}
