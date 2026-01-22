/*
 * TP   : Projet fin de semestre
 * Class: Etablissement | Station de lavage
 * Name : Stéphane SINGERY, Yoann RABAHI, Teddy RAKOTOARIVELO
 * Group: ING1-APP-BDML2
 * Date : 2026-01-10
 */

// Import packages
package com.mycompany.station_de_lavage;
import  java.time.temporal.ChronoUnit;
import  java.time.LocalDateTime;
import  java.time.LocalDate;
import  java.time.LocalTime;
import  java.util.Scanner;

// Import FileW/R packages
import  java.io.FileWriter;
import  java.io.FileReader;
import  java.io.BufferedReader;
import  java.io.IOException;


// Declare class
class Etablissement {
    
    // ----------------------------- CONSTANTES FICHIERS

    private static final String FICHIER_CLIENTS = "clients.txt";
    private static final String FICHIER_RDV     = "rendez_vous.txt";

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
    
    // ------------- AJOUT CLIENTS
    
    /**
     * NOM + TEL : Ajoute un client en fournissant son nom et son numéro de
     * téléphone.
     *
     * @param nom nom du client
     * @param telephone numéro de téléphone du client
     * @return le client ajouté
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
     * NOM + TEL + @ : Ajoute un client en fournissant son nom, son numéro de
     * téléphone et son adresse électronique.
     *
     * @param nom nom du client
     * @param telephone numéro de téléphone du client
     * @param email adresse électronique du client
     * @return objet du client ajouté
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
     * Insère un client dans le tableau en respectant l'ordre léxicographique.
     */
    private void insererClientTrie(Client client) {

        int i = nbClients - 1;

        // Décalage vers la droite des clients existants tant que nouveau client 
        // est à placer avant
        while (i >= 0 && clients[i].placerApres(client)) {
            clients[i + 1] = clients[i];
            i--;
        }

        // Insére le client
        clients[i + 1] = client;

        // Mise à jour du nomdre de clients
        nbClients++;
    }
    
    // ------------- RECHERCHE CLIENT
    
    /**
     * Tél / @ :
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
    
    // -----
    
    /**
     * NUMERO CLIENT :
     * Recherche un client à partir de son numéro client.
     *
     * @param numeroClient le numéro du client recherché
     * @return             le client trouvé ou null s'il n'existe pas
     */
    public Client rechercher(int numeroClient) {

        for (int i = 0; i < nbClients; i++) {
            
            Client client = clients[i];
            
            // Client trouvé
            if (client.getNumeroClient() == numeroClient) {
                return client;
            }
        }
        
        // Aucun client trouvé
        return null;
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
                    "Format heure fournie invalide. Exemple format attendu : 10:30"
                );
            }

        } while (
            !verifierFormatHeure(heureSaisie)
        );

        return LocalTime.parse(heureSaisie);
    }
    
    // -----
    
    /**
     * Vérifie si une chaîne de caractères respecte le format yyyy-MM-dd
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
        if (string_.charAt(4) != '-' || string_.charAt(7) != '-') {
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
    private LocalDate lireJour(Scanner sc) {

        String dateSaisie;

        do {
            System.out.print("Entrez une date (yyyy-MM-dd) : ");
            dateSaisie = sc.nextLine();

            if (!verifierFormatDate(dateSaisie)) {
                System.out.println(
                    "Format date fournie invalide. Exemple format attendu : 2026-01-01"
                );
            }

        } while (
            !verifierFormatDate(dateSaisie)
        );

        return LocalDate.parse(dateSaisie);
    }
    
    // -----
    
    /**
     * JOUR :
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

        // Aucun créneau indisponible
        System.out.println("Créneau indisponible.");
        return null;
    }
    
    // -----
    
    /**
     * HEURE :
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
        LocalDate jourSaisi = lireJour(sc);

        // Vérification que le créneau est libre (double check)
        for (int j = 0; j < NB_JOURS; j++) {
            if (jours[j].equals(jourSaisi)
                    && planning[indiceCreneau][j] == null) {
                return LocalDateTime.of(jourSaisi, heure);
            }
        }

        // Aucun créneau indisponible
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
        int[]         typesSalissure
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
                = new PrestationTresSale(categorieVehicule, typesSalissure);

        // Création du rendez-vous
        RendezVous rdv = new RendezVous(creneau, client, prestation);

        // Insertion du rendez-vous dans le planning
        planning[i][j] = rdv;

        return rdv;
    }
    
    // ------------- PLANIFICATION
    
    /**
     * Planifie un rendez-vous pour le nettoyage d'un véhicule. Cette méthode
     * orchestre différentes étapes : 
     * - identification du client
     * - choix du créneau
     * - choix de la prestation
     * - ajout du rendez-vous
     * - affichage du prix.
     */
    public void planifier() {

        Scanner sc = new Scanner(System.in);

        // ---------------- IDENTIFICATION DU CLIENT
        
        System.out.print("Nom du client : ");
        String nom = sc.nextLine();

        System.out.print("Numéro de téléphone : ");
        String telephone = sc.nextLine();

        Client client = this.rechercher(nom, telephone);

        // Si le client n'existe pas, il est créée
        if (client == null) {
            System.out.println("Nouveau client, création en cours...");
            client = this.ajouter(nom, telephone);
        }

        // ---------------- CHOIX DU CRENEAU (7 jours)
        
        System.out.println("Choix du créneau :");
        System.out.println("1 - Par jour");
        System.out.println("2 - Par heure");

        int choix = sc.nextInt();
        sc.nextLine();

        LocalDateTime creneau = null;

        if (choix == 1) {
            LocalDate jour = lireJour(sc);
            creneau = this.rechercher(jour);
        } else if (choix == 2) {
            LocalTime heure = lireHeure(sc);
            creneau = this.rechercher(heure);
        }

        if (creneau == null) {
            System.out.println("Impossible de planifier le rendez-vous.");
            return;
        }

        // ---------------- CHOIX TYPE DE PRESTATION
        
        System.out.println("Type de prestation :");
        System.out.println("1 - Prestation Express");
        System.out.println("2 - Véhicule Sale");
        System.out.println("3 - Véhicule Très Sale");

        int type = sc.nextInt();
        sc.nextLine();

        System.out.print("Catégorie du véhicule (A, B, C) : ");
        char categorie = sc.nextLine().charAt(0);

        RendezVous rdv = null;

        // ---------------- INFO SPÉCIFIQUE / AJOUT RENDEZ-VOUS
        
        if (type == 1) {
            System.out.print("Nettoyage intérieur (true/false) : ");
            boolean interieur = sc.nextBoolean();

            rdv = this.ajouter(client, creneau, categorie, interieur);

        } else if (type == 2) {

            rdv = this.ajouter(client, creneau, categorie);

        } else if (type == 3) {

            System.out.print("Nombre de types de salissure : ");
            int n = sc.nextInt();

            int[] types = new int[n];
            for (int i = 0; i < n; i++) {
                System.out.print("Type de salissure " + (i + 1) + " : ");
                types[i] = sc.nextInt();
            }

            rdv = this.ajouter(client, creneau, categorie, types);
        }

        // ---------------- AFFICHAGE DU RESULTAT
        
        if (rdv != null) {
            System.out.println("Rendez-vous confirmé.");
            System.out.println("Prix de la prestation : "
                + rdv.getPrestation().prixNettoyage() + " €");
        } else {
            System.out.println("Erreur lors de la planification.");
        }
    }
    
    // ------------- LECTURE / ECRITURE FICHIER
    
    /**
     * ECRITURE FICHIER CLIENTS :
     * Ecrit dans un fichier texte les informations relatives aux clients de
     * l'établissement. 
     * Utilise la méthode "Client.versFichier()"
     */
    public void versFichierClients() throws IOException {

        FileWriter fich = new FileWriter(FICHIER_CLIENTS);

        // Ecriture des info client par client
        for (int i = 0; i < nbClients; i++) {
            fich.write(clients[i].versFichier());
        }

        // Force fermeture du fichier
        fich.close();
    }
    
    // -----
    
    /**
     * LECTURE FICHIER / RECHARGE LISTE CLIENTS :
     * Recharge l’ensemble des clients de l'établissement à partir 
     * d’un fichier texte.
     */
    public void depuisFichierClients() throws IOException {

        FileReader fich = new FileReader(FICHIER_CLIENTS);
        BufferedReader br = new BufferedReader(fich);

        this.nbClients = 0;
        int maxNumero = 0;

        String ligne = br.readLine();

        while (ligne != null) {

            // Sépare les différentes informations contenues dans la ligne
            String[] tabIinfos = ligne.split(" : ");

            // Map chacune des informations avec la variable associée
            int    numero = Integer.parseInt(tabIinfos[0]);
            String nom    = tabIinfos[1];
            String tel    = tabIinfos[2];

            Client c;

            /** Gère les deux dégrés d'attribut à partir desquels un client
             * peut être créée (3 ou 4 attributs -> sans ou avec @).
             * 
             * Crée le client.
             */ 
            if (tabIinfos.length == 4) {
                c = new Client(numero, nom, tel, tabIinfos[3]);
            } else {
                c = new Client(numero, nom, tel);
            }

            // Incrémenter le nombre de clients
            clients[this.nbClients] = c;
            this.nbClients++;
            
            // 🔑 Mise à jour du plus grand numéro client
            if (numero > maxNumero) {
                maxNumero = numero;
            }

            // Lit la ligne suivante
            ligne = br.readLine();
        }

        // Force fermeture du fichier
        br.close();
        
        // 🔑 Numérotation continue après chargement
        this.prochainNumeroClient = maxNumero + 1;
        
    }

    // -----
    
    /**
     * ECRITURE FICHIER RENDEZ-VOUS:
     * Ecrit dans un fichier texte les informations relatives au rendez-vous de
     * l'etablissement.
     * Utilise la méthode "RendezVous.versFichier()"
     */
    public void versFichierRendezVous() throws IOException {

        FileWriter fich = new FileWriter(FICHIER_RDV);

        // Ecriture des info rdv par rdv
        for (int i = 0; i < NB_CRENEAUX; i++) {
            for (int j = 0; j < NB_JOURS; j++) {
                if (planning[i][j] != null) {
                    fich.write(planning[i][j].versFichier());
                }
            }
        }

        // Force fermeture du fichier
        fich.close();
    }

    // -----
    
    /**
     * LECTURE FICHIER / RECHARGE LISTE RENDEZ-VOUS :
     * Recharge l’ensemble des rendez-vous de l'établissement à partir 
     * d’un fichier texte.
     */
    
    public void depuisFichierRendezVous() throws IOException {

        FileReader fich = new FileReader(FICHIER_RDV);
        BufferedReader br = new BufferedReader(fich);

        String ligneRendezVous = br.readLine();

        while (ligneRendezVous != null) {

            // Lit le timestamp relatif au créneau
            LocalDateTime creneau = LocalDateTime.parse(ligneRendezVous);

            // Retrouve le client à partir de son numéro unique
            String ligneNumero = br.readLine();
            int numeroClient = Integer.parseInt(ligneNumero);
            Client client = rechercher(numeroClient);
            
            // ⚠️ TOUJOURS lire la ligne prestation
            String lignePrestation = br.readLine();
            
            if (client == null) {
                System.out.println("Client inconnu : " + numeroClient);
                ligneRendezVous = br.readLine();
                continue;
            }

            // Identifie le type de prestation
            Prestation prestation = prestationResolution.depuisFichier(
                lignePrestation
            );

            // Identifie jour et horaire du créneau
            int i = indiceCreneau(creneau.toLocalTime());
            int j = indiceJour(creneau.toLocalDate());

            // Crée le rendez-vous
            if (i != -1 && j != -1) {
                planning[i][j] = new RendezVous(creneau, client, prestation);
            }

            // Passe au rendez-vous suivant
            ligneRendezVous = br.readLine();
        }

        // Force fermeture du fichier
        br.close();
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
    
    // -----
    
    /**
     * AFFICHER PLANNING JOUR :
     * Affiche le planning des rendez-vous pour un jour donné.
     *
     * @param jour le jour pour lequel afficher le planning
     */
    public void afficher(LocalDate jour) {

        int j = indiceJour(jour);

        // Vérifie que le jour est dans les 7 jours du planning
        if (j == -1) {
            System.out.println("Jour hors planning.");
            return;
        }

        System.out.println("Planning pour le " + jour + " :");

        for (int i = 0; i < NB_CRENEAUX; i++) {
            System.out.print(creneaux[i] + " : ");

            if (planning[i][j] == null) {
                System.out.println("LIBRE");
            } else {
                System.out.println(
                        planning[i][j].getClient().getNom()
                );
            }
        }
    }
    
    // -----
    
    /**
     * Affiche les clients correspondant à un nom ou un numéro de téléphone.
     *
     * @param recherche le nom ou le numéro de téléphone recherché
     */
    public void afficher(String nomTelephone) {

        boolean clientTrouve = false;

        for (int i = 0; i < nbClients; i++) {

            Client c = clients[i];

            if (c.getNom().equalsIgnoreCase(nomTelephone)
                    || c.getTelephone().equals(nomTelephone)) {

                System.out.println(c);
                clientTrouve = true;
            }
        }

        if (!clientTrouve) {
            System.out.println("Aucun client correspondant.");
        }
    }
    
    // -----
    
    /**
     * Affiche les rendez-vous pris par un client donné.
     *
     * @param numeroClient le numéro du client
     */
    public void afficher(int numeroClient) {

        boolean clientTrouve = false;

        for (int i = 0; i < NB_CRENEAUX; i++) {
            for (int j = 0; j < NB_JOURS; j++) {

                if (planning[i][j] != null) {
                    
                    RendezVous rdv = planning[i][j];

                    Client c = rdv.getClient();

                    if (c.getNumeroClient() == numeroClient) {
                        System.out.println(rdv);
                        clientTrouve = true;
                    }
                }
            }
        }

        if (!clientTrouve) {
            System.out.println("Aucun rendez-vous pour ce client.");
        }
    }



}
