/*
 * TP   : Projet fin de semestre
 * Class: Etablissement | Station de lavage
 * Name : Stéphane SINGERY, Yoann RABAHI, Teddy RAKOTOARIVELO
 * Group: ING1-APP-BDML2
 * Date : 2026-01-01
 */

// Import packages
package com.mycompany.station_de_lavage;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;


// Declare class
class Etablissement {

    // ----------------------------- CONSTANTES HORAIRES

    private static final LocalTime HEURE_OUVERTURE   = LocalTime.of(10, 0);
    private static final LocalTime HEURE_FERMETURE   = LocalTime.of(18, 0);
    private static final int       DUREE_CRENEAU_MIN = 30;

    // ----------------------------- CONSTANTES PLANNING

    private static final int NB_JOURS    = 7;
    private static final int NB_CRENEAUX = 
        (int) (
            HEURE_OUVERTURE.until(
                HEURE_FERMETURE, 
                ChronoUnit.MINUTES
            ) / DUREE_CRENEAU_MIN
        );
    
    // ----------------------------- AUTRES
    
    private static final int MAX_CLIENTS = 100;
    private              int prochainNumeroClient;
    

    // ----------------------------- ATTRIBUTES

    private String         nom;
    private Client[]       clients;
    private int            nbClients;
    private RendezVous[][] planning;
    private LocalDate[]    jours;
    private LocalTime[]    creneaux;

    // ----------------------------- CONSTRUCTOR

    public Etablissement(String nom) {
        this.nom = nom;

        // Initialisation du tableau de clients
        this.clients = new Client[MAX_CLIENTS];
        this.nbClients = 0;

        // Initialisation du planning (vide au départ)
        this.planning = new RendezVous[NB_CRENEAUX][NB_JOURS];
        
        // Initialisation du premier numéro de client
        this.prochainNumeroClient = 1;
        
        // Initialisation en-tête colonnes planning : date
        LocalDate today = LocalDate.now();
        this.jours = new LocalDate[NB_JOURS];
        
        for (int i = 0; i < NB_JOURS; i++) {
            this.jours[i] = today.plusDays(i);
        }
        
        // Initialisation index lignes planning : créneau horaire
        this.creneaux = new LocalTime[NB_CRENEAUX];

        LocalTime heure = HEURE_OUVERTURE;

        for (int i = 0; i < NB_CRENEAUX; i++) {
            this.creneaux[i] = heure;
            heure = heure.plusMinutes(DUREE_CRENEAU_MIN);
        }

    }

    // ----------------------------- METHOD
    
    // ------------- RECHERCHE CLIENT
    
    /**
    * Recherche un client à partir de son nom et de son numéro de téléphone
    * 
    * @param nom       le nom du client recherché
    * @param telephone le numéro de téléphone du client recherché
    * @return          le client trouvé, ou null s'il n'existe pas
    */
    public Client rechercher(String nom, String telephone) {

        for (int i = 0; i < nbClients; i++) {

            Client client = clients[i];

            // Client trouvé avec la combinaison nom / tél
            if (client.getNom().equals(nom)
                    && client.getTelephone().equals(telephone)) {
                return client;
            }
        }

        // Aucun client trouvé avec la combinaison nom / tél
        return null;
    }
    
    // ------------- AJOUT CLIENTS
    
    /**
     * Ajoute un client en fournissant son nom et son numéro de téléphone.
     *
     * @param nom       nom du client
     * @param telephone numéro de téléphone du client
     * @return          le client ajouté
     */
    public Client ajouter(String nom, String telephone) {

        Client client = new Client(prochainNumeroClient, nom, telephone);
        
        // Incrémentation du prochain numéro de client
        prochainNumeroClient++;

        // Insérer le client selon ordre lexicographique
        insererClientTrie(client);
        
        return client;
        
    }    
        
    // -----

    /**
     * Ajoute un client en fournissant son nom, son numéro de téléphone et son
     * adresse électronique.
     *
     * @param nom       nom du client
     * @param telephone numéro de téléphone du client
     * @param email     adresse électronique du client
     * @return          objet du client ajouté
     */
    public Client ajouter(String nom, String telephone, String email) {

        Client client = new Client(prochainNumeroClient, nom, telephone, email);
        
        // Incrémentation numéro de client
        prochainNumeroClient++;

        // Insérer le client selon ordre lexicographique
        insererClientTrie(client);
        
        return client;
    }
    
    // -----

    /**
     * Insère un client dans le tableau en respectant l'ordre.
     */
    private void insererClientTrie(Client client) {

        int i = nbClients - 1;

        // Décalage vers la droite des clients existants tant que nouveau client 
        // est à placer avant
        while (i >= 0 && clients[i].placerApres(client)) {
            clients[i + 1] = clients[i];
            i--;
        }

        // Insérer le client
        clients[i + 1] = client;
        
        // Mise à jour du nomdre de clients
        nbClients++;
    }
    
    // ------------- RECHERCHE CRENEAU
    
    /**
     * Vérifie si une chaîne de caractères respecte le format hh:mm
     *
     * @param string_   chaîne de caractères
     * @return          boolean true ou false
     */
    private boolean verifierFormatHeure(String string_) {

        // Vérifie la longeur de la chaîne
        if (string_.length() != 5) {
            return false;
        }

        // Vérifie le sépérateur
        if (string_.charAt(2) != ':') {
            return false;
        }

        // Vérifier que les caractères sont biens des chiffres, à l'exception
        // du séparateur
        for (int i = 0; i < string_.length(); i++) {
            if (i != 2 && !Character.isDigit(string_.charAt(i))) {
                return false;
            }
        }

        // Vérifie que les chiffres sont bien bornés (heure, minute)
        int heure = Integer.parseInt(string_.substring(0, 2));
        int minute = Integer.parseInt(string_.substring(3, 5));       

        return 
                heure >= 0 
            &&  heure <= 23
            &&  minute >= 0 
            &&  minute <= 59;
    }
    
    // -----
        
    /**
     * Lecture et check de l'heure fournie par l'utilisateur.
     */
    private LocalTime lireHeure(Scanner sc) {

        String heureSaisie;

        do {
            System.out.print("Entrez une heure (hh:mm) : ");
            heureSaisie = sc.nextLine();

            if (!verifierFormatHeure(heureSaisie)) {    
                System.out.println(
                    "Format heure fournie invalide. Exemple attendu : 10:30"
                );
            }

        } while (
            !verifierFormatHeure(heureSaisie)
        );

        return LocalTime.parse(heureSaisie);
    }
    
    // -----
    
    /**
     * Vérifie si une chaîne de caractères respecte le format dd/MM/yyyy
     *
     * @param string_   chaîne de caractères
     * @return          boolean true ou false
     */
    private boolean verifierFormatDate(String string_) {

        // Vérifie la longeur de la chaîne
        if (string_.length() != 10) {
            return false;
        }

        // Vérifie les sépérateurs
        if (string_.charAt(2) != '/' || string_.charAt(5) != '/') {
            return false;
        }

        // Vérifier que les caractères sont biens des chiffres, à l'exception
        // des séparateurs
        for (int i = 0; i < string_.length(); i++) {
            if (i != 4 && i != 7 && !Character.isDigit(string_.charAt(i))) {
                return false;
            }
        }

        // Vérifie que les chiffres sont bien bornés (mois, jours), à l'exception
        // de celui relatif à l'année
        int annee = Integer.parseInt(string_.substring(0, 4));
        int mois  = Integer.parseInt(string_.substring(5, 7));
        int jour  = Integer.parseInt(string_.substring(8, 10));

        return 
                mois >= 1 
            &&  mois <= 12
            &&  jour >= 1 
            &&  jour <= 31;
    }
    
    // -----
    
    /**
     * Lecture et check d'une date fournie par l'utilisateur.
     */
    private LocalDate lireDate(Scanner sc) {

        String dateSaisie;

        do {
            System.out.print("Entrez une date (dd/MM/yyyy) : ");
            dateSaisie = sc.nextLine();

            if (!verifierFormatDate(dateSaisie)) {
                System.out.println(
                    "Format date fournie invalide. Exemple attendu : 01/01/2026"
                );
            }

        } while (
            !verifierFormatDate(dateSaisie)
        );

        return LocalDate.parse(dateSaisie);
    }
    
    // -----
    
    /**
     * Recherche les créneaux horaires disponibles dans une journée et retourne 
     * celui sélectionné par l'utilisateur
     * 
     * @param jour la date concernée
     * @return     (LocalTime) le créneau horaire sélectionné par l'utilisateur
     */
    public LocalDateTime rechercher(LocalDate jour) {

        // Initialisation du Scanner
        Scanner sc = new Scanner(System.in);

        // ------------- CRENEAUX DISPONIBLES
        
        int indiceJour = -1;
        for (int j = 0; j < NB_JOURS; j++) {
            if (jours[j].equals(jour)) {
                indiceJour = j;
                break;
            }
        }

        // Si aucun créneau n'est disponible le jour j
        if (indiceJour == -1) {
            System.out.println("Jour non disponible.");
            return null;
        }

        // Affichage des heures disponibles à l'utilisateur
        System.out.println("Créneaux disponibles pour le " + jour + " :");
        for (int i = 0; i < NB_CRENEAUX; i++) {
            if (planning[i][indiceJour] == null) {
                System.out.println(" - " + creneaux[i]);
            }
        }

        // ------------- CHOIX UTILISATEUR
        
        // Lecture du choix de l'utilisateur
        LocalTime heureSaisie = lireHeure(sc);

        // Vérification que le créneau horaire est libre (double check)
        for (int i = 0; i < NB_CRENEAUX; i++) {
            if (creneaux[i].equals(heureSaisie)
                    && planning[i][indiceJour] == null) {
                return LocalDateTime.of(jour, heureSaisie);
            }
        }

        System.out.println("Créneau indisponible.");
        return null;
    }
    
    // -----
    
    /**
     * Recherche les dates pour lesquelles un créneau horaire spécifique
     * est disponible.
     * 
     * @param heure le créneau horaire spécifique
     * @return     (LocalDate) la date sélectionnée par l'utilisateur
     */
    public LocalDateTime rechercher(LocalTime heure) {

        // Initialisation du Scanner
        Scanner sc = new Scanner(System.in);

        // ------------- CRENEAUX DISPONIBLES
        
        int indiceCreneau = -1;
        for (int i = 0; i < NB_CRENEAUX; i++) {
            if (creneaux[i].equals(heure)) {
                indiceCreneau = i;
                break;
            }
        }

        // Si aucun créneau horaire correspondant
        if (indiceCreneau == -1) {
            System.out.println("Heure non disponible.");
            return null;
        }

        // Affichage des jours disponibles à l'utilisateur
        System.out.println("Jours disponibles pour " + heure + " :");
        for (int j = 0; j < NB_JOURS; j++) {
            if (planning[indiceCreneau][j] == null) {
                System.out.println(" - " + jours[j]);
            }
        }

        // ------------- CHOIX UTILISATEUR
        
        // Lecture du choix de l'utilisateur
        LocalDate jourSaisi = lireDate(sc);

        // Vérification que le créneau est libre (double check)
        for (int j = 0; j < NB_JOURS; j++) {
            if (jours[j].equals(jourSaisi)
                    && planning[indiceCreneau][j] == null) {
                return LocalDateTime.of(jourSaisi, heure);
            }
        }

        System.out.println("Créneau indisponible.");
        return null;
    }

    // ------------- AJOUT RENDEZ-VOUS
    
    /**
     * Vérifie que la date donnée est incluse dans les n jours du planning
     */
    private int indiceJour(LocalDate jour) {
        for (int j = 0; j < NB_JOURS; j++) {
            if (jours[j].equals(jour)) {
                return j;
            }
        }
        return -1;
    }
    
    // -----

    /**
     * Vérifie que la créneau donné est dans les heures d'ouvertures
     */
        
    private int indiceCreneau(LocalTime heure) {
        for (int i = 0; i < NB_CRENEAUX; i++) {
            if (creneaux[i].equals(heure)) {
                return i;
            }
        }
        return -1;
    }

    // -----
    
    /**
     * PRESTATION EXPRESSE :
     * Ajoute un rendez-vous pour une prestation express. 
     * 
     * @param client  le client pour lequel le rendez-vous est pris
     * @param creneau la date et l'heure du rendez-vous (LocalDateTime)
     * @param categorieVehicule la catégorie du véhicule ('A', 'B', 'C')
     * @param nettoyerInterieur indique si le nettoyage intérieur est demandé
     * @return        le rendez-vous ajouté au planning, ou null si l'ajout est...
     *                impossible
     */
    public RendezVous ajouter(
        Client        client,
        LocalDateTime creneau,
        char          categorieVehicule,
        boolean       nettoyerInterieur
    ) {

        // Vérifie que la  date est pas dans les n jours du planning
        int j = indiceJour(creneau.toLocalDate());

        // Vérifie que la créneau donné est dans les heures d'ouverture
        int i = indiceCreneau(creneau.toLocalTime());

        // Vérification des bornes du planning et si créneau déjà occupé (triple check)
        if (j == -1 || i == -1 || planning[i][j] != null) {
            System.out.println(
                "Ajout impossible : date hors planning, heure invalide ou créneau déjà occupé."
            );
            return null;
        }

        // Création de la prestation express
        Prestation prestation
                = new PrestationExpress(categorieVehicule, nettoyerInterieur);

        // Création du rendez-vous
        RendezVous rdv = new RendezVous(creneau, client, prestation);

        // Insertion du rendez-vous dans le planning
        planning[i][j] = rdv;

        return rdv;
    }
    
    // -----
    
    /**
     * PRESTATION SALE :
     * Ajoute un rendez-vous pour une prestation pour véhicule sale.
     * 
     * @param client  le client pour lequel le rendez-vous est pris
     * @param creneau la date et l'heure du rendez-vous (LocalDateTime)
     * @param categorieVehicule la catégorie du véhicule ('A', 'B', 'C')
     * @return        le rendez-vous ajouté au planning, ou null si l'ajout est...
     *                impossible
     */
    public RendezVous ajouter(
        Client        client,
        LocalDateTime creneau,
        char          categorieVehicule
    ) {

        // Vérifie que la date est bien dans les n jours du planning
        int j = indiceJour(creneau.toLocalDate());

        // Vérifie que le créneau donné est dans les heures d'ouverture
        int i = indiceCreneau(creneau.toLocalTime());

        // Vérification des bornes du planning et si créneau déjà occupé (triple check)
        if (j == -1 || i == -1 || planning[i][j] != null) {
            System.out.println(
                    "Ajout impossible : date hors planning, heure invalide ou créneau déjà occupé."
            );
            return null;
        }

        // Création de la prestation pour véhicule sale
        Prestation prestation
                = new PrestationSale(categorieVehicule);

        // Création du rendez-vous
        RendezVous rdv = new RendezVous(creneau, client, prestation);

        // Insertion du rendez-vous dans le planning
        planning[i][j] = rdv;

        return rdv;
    }
    
    // -----

    /**
     * PRESTATION TRES SALE 
     * Ajoute un rendez-vous pour une prestation pour véhicule très sale.
     * 
     * @param client  le client pour lequel le rendez-vous est pris
     * @param creneau la date et l'heure du rendez-vous (LocalDateTime)
     * @param categorieVehicule la catégorie du véhicule ('A', 'B' ou 'C')
     * @param typeSalissure     le type de salissure à nettoyer
     * @return        le rendez-vous ajouté au planning, ou null si l'ajout est...
     *                impossible
     */
    public RendezVous ajouter(
        Client        client,
        LocalDateTime creneau,
        char          categorieVehicule,
        int           typeSalissure
    ) {

        // Vérifie que la date est bien dans les n jours du planning
        int j = indiceJour(creneau.toLocalDate());

        // Vérifie que le créneau donné est dans les heures d'ouverture
        int i = indiceCreneau(creneau.toLocalTime());

        // Vérification des bornes du planning et si créneau déjà occupé (triple check)
        if (j == -1 || i == -1 || planning[i][j] != null) {
            System.out.println(
                    "Ajout impossible : date hors planning, heure invalide ou créneau déjà occupé."
            );
            return null;
        }

        // Création de la prestation pour véhicule très sale
        Prestation prestation
                = new PrestationTresSale(categorieVehicule, typeSalissure);

        // Création du rendez-vous
        RendezVous rdv = new RendezVous(creneau, client, prestation);

        // Insertion du rendez-vous dans le planning
        planning[i][j] = rdv;

        return rdv;
    }
    
    // ------------- AFFICHE

    /**
     * Afficher les informations relatives à un établissement
     */
    public void afficher() {

        System.out.println("Etablissement :");
        System.out.println("Nom : " + nom);
        System.out.println();

        // ---------------- CLIENTS 
        
        System.out.println("Nombre de clients : " + nbClients);
        System.out.println("Liste des clients :");
        for (int i = 0; i < nbClients; i++) {
            System.out.println(clients[i]);
        }
        System.out.println();
        
        // ---------------- PLANNING 

        // Affichage du planning
        System.out.println("Planning des rendez-vous à 7 jours :");
        
        // En-tête des colonnes : dates
        System.out.print("Heure ");
        for (int j = 0; j < NB_JOURS; j++) {
            System.out.print(jours[j] + "  ");
        }
        System.out.println();

        // Index des lignes : créneaux horaires
        for (int i = 0; i < NB_CRENEAUX; i++) {

            // Heure du créneau
            System.out.print(creneaux[i] + "  ");

            for (int j = 0; j < NB_JOURS; j++) {
                if (planning[i][j] == null) {
                    System.out.print("[ LIBRE ]   ");
                } else {
                    System.out.print("[ "
                        + planning[i][j].getClient().getNom()
                        + " ] "
                    );
                }
            }
            System.out.println();
        }
    }
}
