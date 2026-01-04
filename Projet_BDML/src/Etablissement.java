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
    
    de rechercher un créneau pour un jour donné. La méthode doit afficher toutes les heures
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
    
    de rechercher un créneau pour une heure donnée. La méthode doit afficher tous les jours
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
    
    ajouter un rendez-vous pour une prestation express en donnant le client, le créneau choisi
    (date et heure), la catégorie du véhicule et en indiquant si l’intérieur du véhicule doit être
    nettoyé ou non
    
    */
    
    public RendezVous ajouter(Client client,
                          LocalDateTime creneau,
                          Prestation.CategorieVehicule categorie,
                          boolean nettoyageInterieur,
                          double prix)
    {
        int jour = creneau.getDayOfWeek().getValue() - 1;
        int heure = getIndex(creneau.toLocalTime());

        if (planningRDV[heure][jour] != null) {
            System.out.println("Créneau déjà réservé.");
            return null;
        }

        PrestationExpress prestation = new PrestationExpress(categorie, nettoyageInterieur);
        RendezVous rdv = new RendezVous(client, prestation);

        planningRDV[heure][jour] = rdv;

        return rdv;
    }
    
    
    public RendezVous ajouter(Client client,
                          LocalDateTime creneau,
                          Prestation.CategorieVehicule categorie,
                          double prix)
    {
        int jour = creneau.getDayOfWeek().getValue() - 1;
        int heure = getIndex(creneau.toLocalTime());

        if (planningRDV[heure][jour] != null) {
            System.out.println("Créneau déjà réservé.");
            return null;
        }

        PrestationSale prestation = new PrestationSale(categorie);
        RendezVous rdv = new RendezVous(client, prestation);

        planningRDV[heure][jour] = rdv;

        return rdv;
    }
    
    
    public RendezVous ajouter(Client client,
                          LocalDateTime creneau,
                          Prestation.CategorieVehicule categorie,
                          PrestationTresSale.TypeSalissure typeSalissure,
                          double prix)
    {
        int jour = creneau.getDayOfWeek().getValue() - 1;
        int heure = getIndex(creneau.toLocalTime());

        if (planningRDV[heure][jour] != null) {
            System.out.println("Créneau déjà réservé.");
            return null;
        }

        PrestationTresSale prestation = new PrestationTresSale(categorie, typeSalissure);
        RendezVous rdv = new RendezVous(client, prestation);

        planningRDV[heure][jour] = rdv;

        return rdv;
    }

          
}   
