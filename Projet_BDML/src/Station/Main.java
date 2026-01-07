package Station;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author Teddy.R
 */
public class Main {
    public static void main(String[] args) {

        // === Test des clients ===
        System.out.println("--- Test Client --- (creation de clients et test du tri)");
        Client client1 = new Client(1, "Zango", "0707070707", "zango@gmail.com");
        Client client2 = new Client(2, "Chien", "0606060606");
        
        System.out.println(client1); // affichage client1
        System.out.println("client1 placerApres client2 ? " + client1.placerApres(client2)); 
        // attend true si client1 doit venir apres client2

        // === Test des prestations ===
        System.out.println("\n--- Prestation Express --- (test du calcul du nettoyage total)");
        PrestationExpress pe = new PrestationExpress(Prestation.CategVehicule.A, true);
        System.out.println("Nettoyage total : " + pe.nettoyage());

        System.out.println("\n--- Prestation Sale --- (test du calcul du nettoyage total)");
        PrestationSale ps = new PrestationSale(Prestation.CategVehicule.B);
        System.out.println("Nettoyage total : " + ps.nettoyage());

        System.out.println("\n--- Prestation Tres Sale --- (test du calcul du nettoyage total)");
        PrestationTresSale pts = new PrestationTresSale(Prestation.CategVehicule.C, 1);
        System.out.println("Nettoyage total : " + pts.nettoyage());

        // === Creation de l'etablissement ===
        System.out.println("\n=== Creation de l'etablissement === (capacite clients et planning)");
        Etablissement etab = new Etablissement("CHEF LAVAGE", 10, 16); // 16 creneaux par jour
        System.out.println("Etablissement cree : " + etab.getNom());

        // === Ajout de clients ===
        System.out.println("\n--- Ajout des clients --- (insertion triee automatique)");
        etab.ajouter("TSR", "0303030303");
        etab.ajouter("Chef", "0101010101");
        etab.ajouter("Stuart", "0202020202");

        System.out.println("Liste des clients apres insertion triee :");
        for (Client c : etab.getClients()) {
            if (c != null) System.out.println(c);
        }

        // === Test ajout de rendez-vous valides ===
        System.out.println("\n--- Ajout de rendez-vous valides --- (RDV corrects dans le planning)");
        LocalDateTime rdv1Date = LocalDateTime.of(2026, 1, 7, 10, 0); // 10h00, Jour 1
        RendezVous rdv1 = etab.ajouterRdv(etab.getClients()[0], rdv1Date,
                                          Prestation.CategVehicule.A, true);
        System.out.println("RDV ajoute : " + rdv1);

        LocalDateTime rdv2Date = LocalDateTime.of(2026, 1, 7, 11, 0); // 11h00, Jour 1
        RendezVous rdv2 = etab.ajouterRdv(etab.getClients()[1], rdv2Date,
                                          Prestation.CategVehicule.B);
        System.out.println("RDV ajoute : " + rdv2);

        LocalDateTime rdv3Date = LocalDateTime.of(2026, 1, 8, 10, 30); // 10h30, Jour 2
        RendezVous rdv3 = etab.ajouterRdv(etab.getClients()[2], rdv3Date,
                                          Prestation.CategVehicule.C, 1);
        System.out.println("RDV ajoute : " + rdv3);

        // === Test creneau dejà occupe ===
        System.out.println("\n--- Test creneau deja reserve --- (on tente de reserver un RDV sur un creneau occupe)");
        RendezVous rdvFail = etab.ajouterRdv(etab.getClients()[0], rdv1Date,
                                             Prestation.CategVehicule.A, true);
        System.out.println("Resultat : " + rdvFail); // attend null et message d'erreur

        // === Test creneau hors limites (heure invalide) ===
        System.out.println("\n--- Test creneau hors limites (18h30) --- (heure > dernier creneau)");
        LocalDateTime rdvHorsHeure = LocalDateTime.of(2026, 1, 7, 18, 30); // 18h30, pas dans les 16 creneaux
        RendezVous rdvErreur1 = etab.ajouterRdv(etab.getClients()[0], rdvHorsHeure,
                                                 Prestation.CategVehicule.A, true);
        System.out.println("Resultat : " + rdvErreur1); // attend null et message d'erreur

        // === Test creneau hors limites (jour > 7) ===
        System.out.println("\n--- Test creneau hors limites (jour 8) --- (jour inexistant dans planning)");
        LocalDateTime rdvHorsJour = LocalDateTime.of(2026, 1, 15, 10, 0); // Jour 15 > 7
        RendezVous rdvErreur2 = etab.ajouterRdv(etab.getClients()[1], rdvHorsJour,
                                                 Prestation.CategVehicule.B);
        System.out.println("Resultat : " + rdvErreur2); // attend null et message d'erreur

        // === Recherche creneaux par jour ===
        System.out.println("\n--- Recherche creneaux disponibles pour le jour 1 --- (verifie les creneaux libres restants)");
        etab.rechercherCreneauJour(1);

        // === Recherche creneaux par heure ===
        System.out.println("\n--- Recherche creneaux disponibles pour l'heure 10:00 --- (verifie quels jours ont ce creneau libre)");
        etab.rechercherCreneauHeure(LocalTime.of(10, 0));

        // === Affichage complet de l'etablissement ===
        System.out.println("\n--- Etat complet de l'etablissement --- (affiche clients + planning complet)");
        etab.afficherEtablissement();
    }
}