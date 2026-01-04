/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.time.*;
/**
 *
 * @author rabah
 */
public class Main {

    public static void main(String[] args) {

        Client client1 = new Client(1, "Yoann", "0763931098", "rabahiyoann@gmail.com");
        Client client2 = new Client(2, "Yoan", "0763931093", "rabahiyo@gmail.com");
        
        
        System.out.println(client1.toString()); // validé 
        
        System.out.println(client1.placerApres(client2)); // validé car renvoie true
        
        // Test prestation express
    
        PrestationExpress p1 = new PrestationExpress(Prestation.CategorieVehicule.A, true);

        System.out.println("=== Prestation Express A ==="); 
        System.out.println("Lavage : " + p1.lavage()); 
        System.out.println("Séchage : " + p1.sechage()); 
        System.out.println("Prélavage : " + p1.prelavage()); 
        System.out.println("Nettoyage intérieur : " + p1.nettoyage()); 
        System.out.println();
        
        // Test PrestationSale  
        PrestationSale p2 = new PrestationSale( Prestation.CategorieVehicule.B ); 
        System.out.println("=== Prestation Sale B ==="); 
        System.out.println("Lavage : " + p2.lavage()); 
        System.out.println("Séchage : " + p2.sechage()); 
        System.out.println("Prélavage : " + p2.prelavage()); 
        System.out.println("Nettoyage intérieur : " + p2.nettoyage()); 
        System.out.println();
        
        // Test PrestationTresSale 
        PrestationTresSale p3 = new PrestationTresSale( Prestation.CategorieVehicule.C, PrestationTresSale.TypeSalissure.graisse ); 
        System.out.println("=== Prestation Très Sale C ==="); 
        System.out.println("Lavage : " + p3.lavage()); 
        System.out.println("Séchage : " + p3.sechage()); 
        System.out.println("Prélavage : " + p3.prelavage()); 
        System.out.println("Nettoyage intérieur : " + p3.nettoyage());
        
        // === Test RendezVous === 
        RendezVous rdv = new RendezVous(client1, p1); 
        System.out.println("\n--- RendezVous ---"); 
        System.out.println(rdv);
        
        
        
        // === Création de l'établissement === 
        Etablissement e = new Etablissement("CarWash Paris", 20); 
        
        System.out.println("Établissement créé : " + e.getNomEtablissement()); 
        
        System.out.println("Taille du tableau clients = " + e.getClients().length);
        // === Ajout de clients === 
        Client c1 = e.ajouter(0, "Yoann", "0763931098"); 
        Client c2 = e.ajouter(0, "Alice", "0612345678", "alice@mail.com"); 
        Client c3 = e.ajouter(0, "Bob", "0699988877"); 
        System.out.println("\n--- Liste des clients après insertion triée ---"); 
        for (Client c : e.getClients()) 
        { 
            if (c != null) System.out.println(c); 
        } 
        // === Recherche d’un client === 
        
        System.out.println("\n--- Recherche d’un client ---");
        Client recherche = e.rechercher(e.getClients(), "Alice", "0612345678");
        System.out.println("Résultat recherche : " + recherche);
        
        // === Ajout de rendez-vous ===
        
        System.out.println("\n--- Ajout de rendez-vous ---");
        
        LocalDateTime rdv1Date = LocalDateTime.of(2025, 1, 10, 10, 0);
        RendezVous rdv1 = e.ajouter(c1, rdv1Date, Prestation.CategorieVehicule.A, true, 0);
        
        System.out.println("RDV ajouté : " + rdv1);
        
        LocalDateTime rdv2Date = LocalDateTime.of(2025, 1, 10, 11, 0);
        RendezVous rdv2 = e.ajouter(c2, rdv2Date, Prestation.CategorieVehicule.B, 0);
        
        System.out.println("RDV ajouté : " + rdv2); LocalDateTime rdv3Date = LocalDateTime.of(2025, 1, 11, 9, 30);
        
        RendezVous rdv3 = e.ajouter(c3, rdv3Date, Prestation.CategorieVehicule.C, PrestationTresSale.TypeSalissure.graisse, 0);
        
        System.out.println("RDV ajouté : " + rdv3); 
        
        // === Test créneau déjà réservé === 
        
        System.out.println("\n--- Test créneau déjà réservé ---");
        
        RendezVous rdvFail = e.ajouter(c1, rdv1Date, Prestation.CategorieVehicule.A, true, 0); 
        
        System.out.println("Résultat : " + rdvFail); 
        
        // === Recherche de créneaux par jour === 
        
        System.out.println("\n--- Recherche des créneaux pour un jour (10 janvier = vendredi = 5) ---"); 
        
        e.rechercher(5); 
        
        // Vendredi // === Recherche de créneaux par heure === 
        
        System.out.println("\n--- Recherche des créneaux pour une heure (10h00) ---"); 
        
        e.rechercher(LocalTime.of(10, 0)); 
        
        // === Affichage complet de l’établissement === 
        
        System.out.println("\n--- État complet de l’établissement ---"); 
        
        System.out.println(e);
    }
    
}

