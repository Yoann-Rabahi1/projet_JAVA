package Station;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    public RendezVous ajouterRdv(Client client, LocalDateTime dateHeure,Prestation.CategVehicule categorie, boolean nettoyageInterieur) {

        int ligne = (dateHeure.getHour() - 10) * 2 + (dateHeure.getMinute() == 30 ? 1 : 0);

        int colonne = (int) LocalDate.now().until(dateHeure.toLocalDate(),
                       java.time.temporal.ChronoUnit.DAYS);

        if (ligne < 0 || ligne >= nbCreneaux || colonne < 0 || colonne >= 7) {
            System.out.println("Erreur : creneau hors limites !");
            return null;
        }

        if (planning[ligne][colonne] != null) {
            System.out.println("Erreur : creneau dejà occupe !");
            return null;
        }

        PrestationExpress prestation = new PrestationExpress(categorie, nettoyageInterieur);
        RendezVous rdv = new RendezVous(client, prestation, dateHeure);
        planning[ligne][colonne] = rdv;

        return rdv;
    }


    //  Ajouter rdv pour Prestation Sale 
    public RendezVous ajouterRdv(Client client, LocalDateTime dateHeure, Prestation.CategVehicule categorie) {

        int ligne = (dateHeure.getHour() - 10) * 2 + (dateHeure.getMinute() == 30 ? 1 : 0);

        int colonne = (int) LocalDate.now().until(dateHeure.toLocalDate(),
                       java.time.temporal.ChronoUnit.DAYS);

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

        int colonne = (int) LocalDate.now().until(dateHeure.toLocalDate(),
                       java.time.temporal.ChronoUnit.DAYS);

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

    
    
    public void planifier() {
    Scanner sc = new Scanner(System.in);

    // --- 1. Demander infos client ---
    System.out.print("Ton nom ? : ");
    String nom = sc.nextLine();

    System.out.print("Ton num de tel ?: ");
    String tel = sc.nextLine();

    // --- 2. Chercher le client ---
    Client client = rechercher(nom, tel);

    // --- 3. Créer si nouveau ---
    if (client == null) {
        System.out.println("Tu es nouveaux dans la liste");
        client = ajouter(nom, tel); 
    }

    // --- 4. Choix du créneau ---
    System.out.println("\nComment veux tu chercher un créneau? ");
    System.out.println("(1) - Choisir un jour puis une heure");
    System.out.println("(2) - Choisir une heure puis un jour");
    int choixRecherche = sc.nextInt();

    LocalDateTime dateHeure = null;
    if (choixRecherche == 1) {
        System.out.print("Choisi un jour de 1 à 7 : ");
        int jour = sc.nextInt();
        dateHeure = rechercherCreneauJour(jour);
    } else if (choixRecherche == 2) {
        System.out.print("Heure (ex : 10 pour 10h00) : ");
        int heure = sc.nextInt();
        dateHeure = rechercherCreneauHeure(LocalTime.of(heure, 0));
    }

    if (dateHeure == null) {
        System.out.println("Aucun créneau sélectionné. Annulation.");
        return;
    }

    // --- 5. Choix prestation ---
    System.out.println("\nType de prestation : ");
    System.out.println("(1) -> Express");
    System.out.println("(2) -> Sale");
    System.out.println("(3) ->Très sale");
    
    int choix = sc.nextInt();

    System.out.println("Catégorie du véhicule : (1) -> A, (2) -> B, (3) -> C)");
    int cat = sc.nextInt();
    Prestation.CategVehicule categorie = Prestation.CategVehicule.values()[cat - 1];

    RendezVous rdv = null;

    // --- 6. Ajout RDV selon type ---
    switch (choix) {
        case 1:
            System.out.print("Nettoyage intérieur ? (1) -> oui, (0) -> non) : ");
            int inter = sc.nextInt();
            boolean nettoyerInterieur = (inter == 1);
            rdv = ajouterRdv(client, dateHeure, categorie, nettoyerInterieur);
            break;

        case 2:
            rdv = ajouterRdv(client, dateHeure, categorie);
            break;

        case 3:
            System.out.print("Type de salissure (1 -> Graisse, 2 -> Boue, 3 -> Poussière) : ");
            int salissure = sc.nextInt();
            rdv = ajouterRdv(client, dateHeure, categorie, salissure);
            break;

        default:
            System.out.println("Inconnu");
            return;
    }

    // --- 7. Affichage du résultat ---
    if (rdv != null) {
        System.out.println("\nTon rdv a été créé !!!!!!");
        System.out.println("Prix total : " + rdv.getPrix() + "€");
    } 
    else 
        System.out.println("Erreur durant la création du rendez-vous.");
    
    }
    
    
     // afficher : planning d’un jour
    public void afficher(int jour) {
        Scanner sc = new Scanner(System.in);

        if (jour < 1 || jour > 7) {
            System.out.println("Jour invalide.");
            return;
        }

        int indiceJour = jour - 1;
        System.out.println("\n--- Planning du jour " + jour + " ---");

        for (int i = 0; i < nbCreneaux; i++) {
            LocalTime heure = LocalTime.of(10, 0).plusMinutes(i * 30);
            System.out.print(heure + " : ");

            if (planning[i][indiceJour] == null) {
                System.out.println("Libre");
            } else {
                System.out.println(planning[i][indiceJour]);
            }
        }
    }
    
    // afficher : nom ou un num de téléphone
    public void afficher(String rech) {

        boolean found = false;

        for (int i = 0; i < nbClients; i++) {
            Client c = clients[i];
            if (c.getNom().equalsIgnoreCase(rech) || c.getNumTel().equals(rech)) {
                System.out.println(c);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Aucun client trouvé.");
        }
    }

    // afficher : num client
    public void afficher(int numClient, boolean rdv) {

        boolean found = false;

        for (int jour = 0; jour < 7; jour++) {
            for (int ligne = 0; ligne < nbCreneaux; ligne++) {
                RendezVous rdvObj = planning[ligne][jour];
                if (rdvObj != null && rdvObj.getClient().getNumClient() == numClient) {
                    System.out.println(rdvObj);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Ce client n'a pas de rendez-vous.");
        }
    }
    
    
    
    public void versFichierClients(String nomF) {
        try {
            FileWriter fw = new FileWriter(nomF, false); 

            for (int i = 0; i < nbClients; i++) {
                fw.write(clients[i].versFichier() + System.lineSeparator());
            }

            fw.close();
            System.out.println("Clients sauvegardés dans le fichier.");
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture du fichier clients.");
        }
    }
    
    
    public void depuisFichierClients(String nomF) {
            try {
                FileReader fr = new FileReader(nomF);
                BufferedReader br = new BufferedReader(fr);
                nbClients = 0;
                String ligne;
                while ((ligne = br.readLine()) != null) {
                    String[] parties = ligne.split(" : ");
                    int num = Integer.parseInt(parties[0]);
                    String nom = parties[1];
                    String tel = parties[2];
                    Client c;

                    // si ya le mail
                    if (parties.length == 4) {
                        String email = parties[3];
                        c = new Client(num, nom, tel, email);
                    } else {
                        c = new Client(num, nom, tel);
                    }
                    clients[nbClients] = c;
                    nbClients+=1;
                }
                br.close();
                fr.close();
                System.out.println("Clients chargé");
            } catch (IOException | NumberFormatException e) {
                System.out.println("Erreur ");
            }
        }
        
        
        
        public void versFichierRDV(String nomF) {
            try {
                FileWriter fw = new FileWriter(nomF, false);
                for (int jour = 0; jour < 7; jour++) {
                    for (int ligne = 0; ligne < nbCreneaux; ligne++) {
                        RendezVous rdv = planning[ligne][jour];
                        if (rdv != null) {
                            fw.write(rdv.versFichier() + System.lineSeparator());
                        }
                    }
                }
                fw.close();
                System.out.println("c'est sauvegardé");
            } catch (IOException e) {
                System.out.println("Erreur");
            }
        }
        
     private Client rechercherParNumero(int num) {
        for (int i = 0; i < nbClients; i++) {
            if (clients[i].getNumClient() == num) 
                return clients[i];
        }
        return null;
     }
     
    public void depuisFichierRDV(String nomFichier) {
        try {
            FileReader fr = new FileReader(nomFichier);
            BufferedReader br = new BufferedReader(fr);

            // On vide le planning
            for (int j = 0; j < 7; j++) {
                for (int l = 0; l < nbCreneaux; l++) {
                    planning[l][j] = null;
                }
            }

            String dateStr;
            while ((dateStr = br.readLine()) != null) {

                String numStr = br.readLine();
                String prestStr = br.readLine();

                LocalDateTime dateHeure = LocalDateTime.parse(dateStr);
                int numClient = Integer.parseInt(numStr);

                Client client = rechercherParNumero(numClient);

                String[] parts = prestStr.split(" : ");

              
                Prestation.CategVehicule categorie = Prestation.CategVehicule.valueOf(parts[0]);
                Prestation prestation = null;

                // PrestationExpress 
                if (parts.length == 3 && (parts[1].equals("true") || parts[1].equals("false"))) {
                    boolean inter = Boolean.parseBoolean(parts[1]);
                    prestation = new PrestationExpress(categorie, inter);
                }

                //PrestationSale
                else if (parts.length == 2) {
                    prestation = new PrestationSale(categorie);
                }

                //PrestationTresSale
                else if (parts.length == 3) {
                    int salissure = Integer.parseInt(parts[1]);
                    prestation = new PrestationTresSale(categorie, salissure);
                }

                else {
                    System.out.println("Format rdv incorrect: " + prestStr);
                    continue;
                }

                double prix = prestation.nettoyage();
                RendezVous rdv = new RendezVous(client, prestation, dateHeure);
                placerRdvDansPlanning(rdv);
            }

            br.close();
            fr.close();
            System.out.println("RDV chargés depuis le fichier.");

        } catch (Exception e) {
            System.out.println("Erreur lecture fichier RDV.");
        }
    }

    
    private void placerRdvDansPlanning(RendezVous rdv) {
        LocalDateTime d = rdv.getDateHeure();

        int jour = (int) LocalDate.now().until(d.toLocalDate(), java.time.temporal.ChronoUnit.DAYS);
        int ligne = (int) LocalTime.of(10, 0).until(d.toLocalTime(), java.time.temporal.ChronoUnit.MINUTES) / 30;

        if (jour >= 0 && jour < 7 && ligne >= 0 && ligne < nbCreneaux) {
            planning[ligne][jour] = rdv;
        }
    }

}
