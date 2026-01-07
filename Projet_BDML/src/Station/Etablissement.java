package Station;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

/**
 *
 * @author Teddy.R
 */

public class Etablissement {
    private String nom; 
    private int nbClients;
    private Client[] clients;
    private RendezVous[][] planning;
    private int nbCreneaux;
    // Debut du planning
    private LocalDate debutPlanning = LocalDate.of(2026, 1, 7); 

    public Etablissement(String nom, int capaciteClients, int nbCreneauxParJour) {
        this.nom = nom;
        this.clients = new Client[capaciteClients];
        this.nbClients = 0;
        this.nbCreneaux = nbCreneauxParJour;
        this.planning = new RendezVous[nbCreneaux][7];
    }
    
    /*GETTERS*/
    public String getNom() {
        return nom;
    }

    public Client[] getClients() {
        return clients;
    }

    public RendezVous[][] getPlanning() {
        return planning;
    }

    public int getNbClients() {
        return nbClients;
    }

    public int getNbCreneaux() {
        return nbCreneaux;
    }
    
    /*SETTER*/
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void afficherEtablissement() {
        System.out.println("****** Etablissement : " + nom + " ******");
        System.out.println("Clients (" + nbClients + "/" + clients.length + ") :");
        for (int i = 0; i < nbClients; i++) {
            System.out.println(" - " + clients[i]);
        }

        System.out.println("\nPlanning des rendez-vous :");
        for (int jour = 0; jour < 7; jour++) {
            System.out.println("------------");
            System.out.println("Jour " + (jour + 1) + " :");
            for (int creneau = 0; creneau < nbCreneaux; creneau++) {
                System.out.print("Creneau " + (creneau + 1) + " : ");
                if (planning[creneau][jour] != null) {
                    System.out.println(planning[creneau][jour]);
                } else {
                    System.out.println("Libre");
                }
            }
        }
    }
    
    public Client rechercher(String nom, String numTel) {
        for (int i = 0; i < nbClients; i++) {
            if (clients[i].getNom().equals(nom) && clients[i].getNumTel().equals(numTel)) {
                return clients[i];
            }
        }
        return null;
    }
    
    private void TrierClient(Client c) {
        int i = nbClients - 1;
        while (i >= 0 && clients[i].placerApres(c)) {
            clients[i + 1] = clients[i];
            i--;
        }
        clients[i + 1] = c;
        nbClients++;
    }
    
    public Client ajouter(String nom, String numTel) {
        Client c = new Client(nbClients + 1, nom, numTel);
        TrierClient(c);

        return c;
    }
    
    public Client ajouter(String nom, String numTel, String email) {
        Client c = new Client(nbClients + 1, nom, numTel, email);
         TrierClient(c);
        return c;
    }

    
    
    
    
    public LocalDateTime rechercherCreneauJour(int jour) {
        Scanner sc = new Scanner(System.in);

        if (jour < 1 || jour > 7) {
            System.out.println("Jour invalide !!!!!");
            return null;
        }

        int indiceJour = jour - 1;
        System.out.println("Creneaux disponibles pour le jour " + jour + " :");
        int compteur = 1;
        int[] indicesLignes = new int[nbCreneaux];

        for (int i = 0; i < nbCreneaux; i++) {
            if (planning[i][indiceJour] == null) {
                LocalTime heure = LocalTime.of(10, 0).plusMinutes(i * 30);
                System.out.println(compteur + " : " + heure);
                indicesLignes[compteur - 1] = i;
                compteur++;
            }
        }

        if (compteur == 1) {
            System.out.println("Il n'y a pas de creneau disponible pour ce jour.");
            return null;
        }

        System.out.print("Choisir un creneau : ");
        int choix = sc.nextInt();
        if (choix < 1 || choix >= compteur) {
            System.out.println("Creneau invalide !!!!!");
            return null;
        }

        int ligne = indicesLignes[choix - 1];
        LocalTime heureChoisie = LocalTime.of(10, 0).plusMinutes(ligne * 30);
        return LocalDateTime.of(LocalDate.now().plusDays(indiceJour), heureChoisie);
    }

    public LocalDateTime rechercherCreneauHeure(LocalTime heure) {
        Scanner sc = new Scanner(System.in);

        int ligne = -1;
        for (int i = 0; i < nbCreneaux; i++) {
            if (heure.equals(LocalTime.of(10, 0).plusMinutes(i * 30))) {
                ligne = i;
                break;
            }
        }

        if (ligne == -1) {
            System.out.println("Heure pas disponible.");
            return null;
        }

        System.out.println("Jours disponibles pour l'heure " + heure + " :");
        int compteur = 1;
        int[] indicesColonnes = new int[7];
        for (int j = 0; j < 7; j++) {
            if (planning[ligne][j] == null) {
                System.out.println(compteur + " : Jour " + (j + 1));
                indicesColonnes[compteur - 1] = j;
                compteur++;
            }
        }

        if (compteur == 1) {
            System.out.println("Aucun jour disponible pour cette heure.");
            return null;
        }

        System.out.print("Choisir un jour : ");
        int choix = sc.nextInt();
        if (choix < 1 || choix >= compteur) {
            System.out.println("Creneau invalide !!!!!");
            return null;
        }

        int colonne = indicesColonnes[choix - 1];
        return LocalDateTime.of(LocalDate.now().plusDays(colonne), heure);
    }
    

    // Ajouter rdv pour Prestation Express
    public RendezVous ajouterRdv(Client client, LocalDateTime dateHeure, Prestation.CategVehicule categorie, boolean nettoyageInterieur) {
        // Calcul de la ligne dans le planning selon l'heure 
        int ligne = (dateHeure.getHour() - 10) * 2 + (dateHeure.getMinute() == 30 ? 1 : 0);

        // on calcul le jour dans le planning par rapport au debut
        int colonne = dateHeure.getDayOfMonth() - debutPlanning.getDayOfMonth();

        // on verifie si c'est pas au dessus ou en dessous de la limite
        if (ligne < 0 || ligne >= nbCreneaux || colonne < 0 || colonne >= 7) {
            System.out.println("Erreur : creneau hors limites !");
            return null;
        }

        // on verifie si le creneau est dejà occupe
        if (planning[ligne][colonne] != null) {
            System.out.println("Erreur : creneau dejà occupe !");
            return null;
        }

        // Creation de la prestation et du RDV
        PrestationExpress prestation = new PrestationExpress(categorie, nettoyageInterieur);
        RendezVous rdv = new RendezVous(client, prestation, dateHeure);
        planning[ligne][colonne] = rdv;

        return rdv;
    }

    //  Ajouter rdv pour Prestation Sale 
    public RendezVous ajouterRdv(Client client, LocalDateTime dateHeure, Prestation.CategVehicule categorie) {
        int ligne = (dateHeure.getHour() - 10) * 2 + (dateHeure.getMinute() == 30 ? 1 : 0);
        int colonne = dateHeure.getDayOfMonth() - debutPlanning.getDayOfMonth();

        if (ligne < 0 || ligne >= nbCreneaux || colonne < 0 || colonne >= 7) {
            System.out.println("Erreur : creneau hors limites !");
            return null;
        }

        if (planning[ligne][colonne] != null) {
            System.out.println("Erreur : creneau dejà occupe !");
            return null;
        }

        PrestationSale prestation = new PrestationSale(categorie);
        RendezVous rdv = new RendezVous(client, prestation, dateHeure);
        planning[ligne][colonne] = rdv;

        return rdv;
    }

    // --- Ajouter rdv pour Prestation Très Sale ---
    public RendezVous ajouterRdv(Client client, LocalDateTime dateHeure, Prestation.CategVehicule categorie, int typeSalissure) {
        int ligne = (dateHeure.getHour() - 10) * 2 + (dateHeure.getMinute() == 30 ? 1 : 0);
        int colonne = dateHeure.getDayOfMonth() - debutPlanning.getDayOfMonth();

        if (ligne < 0 || ligne >= nbCreneaux || colonne < 0 || colonne >= 7) {
            System.out.println("Erreur : creneau hors limites !");
            return null;
        }

        if (planning[ligne][colonne] != null) {
            System.out.println("Erreur : creneau dejà occupe !");
            return null;
        }

        PrestationTresSale prestation = new PrestationTresSale(categorie, typeSalissure);
        RendezVous rdv = new RendezVous(client, prestation, dateHeure);
        planning[ligne][colonne] = rdv;

        return rdv;
    }

}

