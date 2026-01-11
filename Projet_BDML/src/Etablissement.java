/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.time.*;
import java.util.Scanner;

/**
 *
 * @author rabah
 */

import java.util.Arrays;

public class Etablissement {
    
    private final int nbMaxClients = 100;
    
    private String nomEtablissement;
    private int nombreClients;
    private Client[] clients;
    private RendezVous[][] planningRDV;
    
    public Etablissement(String nomEtablissement, int nbCreneaux)
    {
        this.nomEtablissement = nomEtablissement;
        this.nombreClients = 0;
        this.clients = new Client[nbMaxClients];
        this.planningRDV = new RendezVous[nbCreneaux][7];
    }
    
    // getter

    public String getNomEtablissement()
    {
        return nomEtablissement;
    }
    
    public int getNombreClients()
    {
        return nombreClients;
    }
    
    public Client[] getClients()
    {
        return clients;
    }
    
    public RendezVous[][] getPlanningRDV()
    {
        return planningRDV;
    }
    
    // setter
    
    public void setNomEtablissement(String nomEtablissement)
    {
        this.nomEtablissement = nomEtablissement;
    }
    
    
    @Override
    public String toString()
    {
        return "[ "
                + "nom etablissement : " + getNomEtablissement()
                + ", nombre clients : " + getNombreClients()
                + ", listes des clients : " + Arrays.toString(clients)
                + ", planning des rdv : " + Arrays.deepToString(planningRDV)
                + " ]";
    }
    
    
    // --------------- nouvelles méthodes à implémenter ---------------
    
    /*
    
    méthode rechercher permettant de rechercher, dans le
    tableau de clients, le client correspondant à un nom et un numéro de téléphone donnés. La
    méthode doit retourner le client qu’elle a trouvé ou null s’il n’y en a pas.
    
    */
    
    public Client rechercher(Client[] clients, String nom, String numTel)
    {
        for (Client c : clients)
        {
            if(c != null && c.getNomClient().equals(nom) && c.getTelClient().equals(numTel))
            {
                return c;
            }
        } return null;
    }
    
    /*
    
    écrire deux méthodes ajouter permettant :
     d’ajouter un client en donnant son nom et son numéro de téléphone,
     d’ajouter un client en donnant, en plus, son adresse électronique.
    
    */
    
    public Client ajouter(int numeroClient, String nom, String numTel)
    {
        
    if (this.nombreClients >= nbMaxClients)
        return null;

    Client nouveauClient = new Client(0, nom, numTel); // numéro temporaire

    int position = 0;

    // Trouver la position d'insertion
    while (position < this.nombreClients &&
           nouveauClient.placerApres(this.clients[position])) {
        position++;
    }

    // Décaler vers la droite
    for (int i = this.nombreClients; i > position; i--) {
        this.clients[i] = this.clients[i - 1];
    }

    // Insérer le client
    this.clients[position] = nouveauClient;

    this.nombreClients++;

    // Renumérotation continue
    for (int i = 0; i < this.nombreClients; i++) {
        this.clients[i].setNumeroClient(i + 1);
    }

    return nouveauClient;
        
    }
    

    
    public Client ajouter(int numeroClient, String nom, String numTel, String mail)
    {
        
    if (this.nombreClients >= nbMaxClients)
        return null;

    Client nouveauClient = new Client(0, nom, numTel, mail); // numéro temporaire

    int position = 0;

    // Trouver la position d'insertion
    while (position < this.nombreClients &&
           nouveauClient.placerApres(this.clients[position])) {
        position++;
    }

    // Décaler vers la droite
    for (int i = this.nombreClients; i > position; i--) {
        this.clients[i] = this.clients[i - 1];
    }

    // Insérer le client
    this.clients[position] = nouveauClient;

    this.nombreClients++;

    // Renumérotation continue
    for (int i = 0; i < this.nombreClients; i++) {
        this.clients[i].setNumeroClient(i + 1);
    }

    return nouveauClient;
        
    }
    
    /*
    
    de 'rechercher' un créneau pour un jour donné. La méthode doit afficher toutes les heures
    disponibles pour le jour souhaité, puis en faire choisir une au client et retourner le créneau
    (date et heure) correspondant,
    
    */
    
   public void rechercher(int jour)
    {
        if (jour >= 1 && jour <= 7)
        {
            jour = jour - 1;

            for (int i = 0; i < planningRDV.length; i++)
            {
                if (planningRDV[i][jour] != null)
                {
                    System.out.println(i + " -> " + planningRDV[i][jour]);
                }
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Choisissez un créneau (numéro de ligne) : ");
            int heure = scanner.nextInt();

            if (heure < 0 || heure >= planningRDV.length || planningRDV[heure][jour] == null)
            {
                System.out.println("Créneau invalide !");
                return;
            }

            System.out.println("L'heure choisie est : " + planningRDV[heure][jour] + " !");
        }
        else
        {
            System.out.println("Journée invalide !");
        }
    }

    
    /*
    
    de 'rechercher' un créneau pour une heure donnée. La méthode doit afficher tous les jours
    disponibles pour l’heure souhaitée, puis en faire choisir un au client et retourner le créneau
    (date et heure) correspondant.

    */
    
    public int getIndex(LocalTime heure) 
    {
        int minutes = heure.getHour() * 60 + heure.getMinute();
        int minutesDebut = 9 * 60;
        return (minutes - minutesDebut) / 30;
    }


    
    public void rechercher(LocalTime heure)
    {
        int h = getIndex(heure);

        if (h < 0 || h >= planningRDV.length) {
            System.out.println("L'heure entrée n'est pas compatible.");
            return;
        }

        // Afficher les jours disponibles
        for (int i = 0; i < planningRDV[h].length; i++) {
            if (planningRDV[h][i] != null) {
                System.out.println(i + " -> " + planningRDV[h][i]);
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez un jour (numéro de colonne) : ");
        int jour = scanner.nextInt();

        if (jour < 0 || jour >= planningRDV[h].length || planningRDV[h][jour] == null) {
            System.out.println("Créneau invalide !");
            return ;
        }

        System.out.println("Le créneau choisi est : " + planningRDV[h][jour] + " !");
    }
    
    
    /*
    
    'ajouter' un rendez-vous pour une prestation express en donnant le client, le créneau choisi
    (date et heure), la catégorie du véhicule et en indiquant si l’intérieur du véhicule doit être
    nettoyé ou non
    
    */
    
    public RendezVous ajouter(Client client,
                          LocalDateTime creneau,
                          Prestation.CategorieVehicule categorie,
                          boolean nettoyageInterieur)
    {
        int jour = creneau.getDayOfWeek().getValue() - 1;
        int heure = getIndex(creneau.toLocalTime());

        if (planningRDV[heure][jour] != null) {
            System.out.println("Créneau déjà réservé.");
            return null;
        }

        PrestationExpress prestation = new PrestationExpress(categorie, nettoyageInterieur);
        RendezVous rdv = new RendezVous(client, prestation, creneau);

        planningRDV[heure][jour] = rdv;

        return rdv;
    }
    
    
    public RendezVous ajouter(Client client,
                          LocalDateTime creneau,
                          Prestation.CategorieVehicule categorie)
    {
        int jour = creneau.getDayOfWeek().getValue() - 1;
        int heure = getIndex(creneau.toLocalTime());

        if (planningRDV[heure][jour] != null) {
            System.out.println("Créneau déjà réservé.");
            return null;
        }

        PrestationSale prestation = new PrestationSale(categorie);
        RendezVous rdv = new RendezVous(client, prestation, creneau);

        planningRDV[heure][jour] = rdv;

        return rdv;
    }
    
    
    public RendezVous ajouter(Client client,
                          LocalDateTime creneau,
                          Prestation.CategorieVehicule categorie,
                          PrestationTresSale.TypeSalissure typeSalissure)
    {
        int jour = creneau.getDayOfWeek().getValue() - 1;
        int heure = getIndex(creneau.toLocalTime());

        if (planningRDV[heure][jour] != null) {
            System.out.println("Créneau déjà réservé.");
            return null;
        }

        PrestationTresSale prestation = new PrestationTresSale(categorie, typeSalissure);
        RendezVous rdv = new RendezVous(client, prestation, creneau);

        planningRDV[heure][jour] = rdv;

        return rdv;
    }
    
    
    /*
    
    'planifier' permettant de planifier un rendez-vous
    pour le nettoyage d’un véhicule. Le programme doit :
     demander le nom et le numéro de téléphone du client et, s’il s’agit d’un nouveau client, l’ajouter
    dans le tableau de clients de l’établissement,
     lui faire choisir un créneau dans les 7 jours suivants,
     lui faire choisir le type de prestation et lui demander les informations correspondantes,
     ajouter le rendez-vous dans le tableau de rendez-vous de l’établissement et indiquer le prix de
    la prestation au client.
    Utiliser les méthodes précédemment écrites.
    
    */
    
    
    public void planifier()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez votre nom : ");
        String nom = scanner.nextLine();

        System.out.println("Entrez votre numéro de téléphone : ");
        String numTel = scanner.nextLine();

        Client client = this.rechercher(clients, nom, numTel);

        if(client == null)
        {
            System.out.println("Vous êtes un nouveau client");
            client = this.ajouter(nombreClients, nom, numTel);
        }

        System.out.println("Choisissez un jour parmi les 7 qui arrivent");
        int jour = scanner.nextInt();

        // Création du créneau
        LocalDateTime creneau = LocalDateTime.now().plusDays(jour);

        System.out.println("Choisissez le type de prestation que vous voulez");
        System.out.println("1 : Prestation express");
        System.out.println("2 : Prestation sale");
        System.out.println("3 : Prestation très sale");

        int numPrestation = scanner.nextInt();

        while(numPrestation != 1 && numPrestation != 2 && numPrestation != 3)
        {
            System.out.println("Vous devez choisir 1, 2 ou 3");
            numPrestation = scanner.nextInt();
        }

        System.out.println("Quel est le type de votre véhicule ?");
        System.out.println("1 : Citadine");
        System.out.println("2 : Berline");
        System.out.println("3 : Monospace ou 4x4");

        int numCategorieVehicule = scanner.nextInt();

        while(numCategorieVehicule != 1 && numCategorieVehicule != 2 && numCategorieVehicule != 3)
        {
            System.out.println("Vous devez choisir 1, 2 ou 3");
            numCategorieVehicule = scanner.nextInt();
        }

        Prestation.CategorieVehicule categorieVehicule = null;

        switch (numCategorieVehicule)
        {
            case 1: categorieVehicule = Prestation.CategorieVehicule.A; break;
            case 2: categorieVehicule = Prestation.CategorieVehicule.B; break;
            case 3: categorieVehicule = Prestation.CategorieVehicule.C; break;
        }

        Prestation p = null;
        
        switch (numPrestation)
        {
            // prestation Express
            case 1:

                System.out.println("Faut-il nettoyer l'intérieur de la voiture ?");
                System.out.println("1 : Oui");
                System.out.println("0 : Non");

                int aNettoyer = scanner.nextInt();

                while(aNettoyer != 0 && aNettoyer != 1)
                {
                    System.out.println("Vous devez choisir 0 ou 1");
                    aNettoyer = scanner.nextInt();
                }

                if(aNettoyer == 0)
                {
                    p = new PrestationExpress(categorieVehicule, false);
                }
                else
                {
                    p = new PrestationExpress(categorieVehicule, true);
                }

                // AJOUT DU RENDEZ-VOUS EXPRESS
                this.ajouter(client, creneau, categorieVehicule, (aNettoyer == 1));

                break;

            // prestation sale
            case 2:
                p = new PrestationSale(categorieVehicule);

                // AJOUT DU RENDEZ-VOUS SALE
                this.ajouter(client, creneau, categorieVehicule);

                break;

            // prestation très sale
            case 3:

                System.out.println("Quel est le type de salissure ?");
                System.out.println("1 : nourriture");
                System.out.println("2 : boue");
                System.out.println("3 : transpiration");
                System.out.println("4 : graisse");

                int salissure = scanner.nextInt();

                while(salissure < 1 || salissure > 4)
                {
                    System.out.println("Vous devez choisir 1, 2, 3 ou 4");
                    salissure = scanner.nextInt();
                }

                PrestationTresSale.TypeSalissure typeSalissure = switch (salissure) {
                    case 1 -> PrestationTresSale.TypeSalissure.nourriture;
                    case 2 -> PrestationTresSale.TypeSalissure.boue;
                    case 3 -> PrestationTresSale.TypeSalissure.transpiration;
                    default -> PrestationTresSale.TypeSalissure.graisse;
                };

                p = new PrestationTresSale(categorieVehicule, typeSalissure);

                // AJOUT DU RENDEZ-VOUS TRES SALE
                this.ajouter(client, creneau, categorieVehicule, typeSalissure);

                break;
        }

        System.out.println("Votre rendez-vous a été enregistré.");
        System.out.println("Prix total : " + p.calculerPrix() + " euros");
    }
    
    /*
    
    Etablissement, ajouter des méthodes 'afficher' permettant :
     d’afficher le planning des rendez-vous pour un jour donné.
     d’afficher le(s) client(s) de l’établissement correspondant à un nom ou à un numéro de
    téléphone donné,
     d’afficher le(s) rendez-vous pris par le client correspondant à un numéro de client donné,
    
    */
    
    
    public void afficher(int jour)
    {
        if (jour < 1 || jour > 7) {
            System.out.println("Journée invalide");
            return;
        }

        int indexJour = jour - 1;

        System.out.println("Planning du jour " + jour + " :");

        for (int i = 0; i < planningRDV.length; i++)
        {
            if (planningRDV[i][indexJour] != null)
            {
                System.out.println("Créneau " + i + " -> " + planningRDV[i][indexJour]);
            }
        }
    }

    
    public void afficher(Client[] clients, String nom, String numTel)
    {
        boolean trouve = false;

        for (Client c : clients)
        {
            if (c != null && (c.getNomClient().equals(nom) || c.getTelClient().equals(numTel)))
            {
                System.out.println(c);
                trouve = true;
            }
        }

        if (!trouve)
            System.out.println("Aucun client ne correspond.");
    }

    
    
    public void afficher(Client client)
    {
        System.out.println("Rendez-vous du client : " + client.getNomClient());

        boolean trouve = false;

        for (int i = 0; i < planningRDV.length; i++)
        {
            for (int j = 0; j < planningRDV[i].length; j++)
            {
                if (planningRDV[i][j] != null && planningRDV[i][j].getClient().equals(client))
                {
                    System.out.println("Jour " + (j+1) + ", créneau " + i + " -> " + planningRDV[i][j]);
                    trouve = true;
                }
            }
        }

        if (!trouve)
            System.out.println("Aucun rendez-vous trouvé pour ce client.");
    }

   
}   
