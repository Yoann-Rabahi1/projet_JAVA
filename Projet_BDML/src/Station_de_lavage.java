/*
 * TP    : Projet
 * Classe: main | Station de lavage
 * Nom   : Stéphane SINGERY, Yoann RABAHI, Teddy RAKOTOARIVELO
 * Groupe: ING1-APP-BDML2
 * Année : 2026-01-10
 */

// Import packages
package com.mycompany.station_de_lavage;
import java.time.LocalDate;
import java.io.IOException;
import java.util.Scanner;

// Declare class
public class Station_de_lavage {

    public static void main(String[] args) throws IOException {
        
        // Scanner pour les saisies utilisateur
        Scanner sc = new Scanner(System.in);

        // 1. Création de l’établissement
        Etablissement etab = new Etablissement("Station EFREI");

        // 2. Chargement des données depuis les fichiers texte
        //    (clients et rendez-vous)
        etab.depuisFichierClients();
        etab.depuisFichierRendezVous();        

        // 3. Boucle menu principal et choix utilisateur
        int choixUtilisateur;
        
        do {
            System.out.println();
            System.out.println(" > MENU STATION DE LAVAGE < ");
            System.out.println("1 - Ajouter un client");
            System.out.println("2 - Rechercher un client (nom ou téléphone)");
            System.out.println("3 - Planifier un rendez-vous");
            System.out.println("4 - Afficher le planning complet");
            System.out.println("5 - Afficher le planning d’un jour");
            System.out.println("6 - Afficher les rendez-vous d’un client");
            System.out.println("0 - Quitter");
            System.out.print("Votre choix : ");

            // a. Lecture du choix utilisateur
            choixUtilisateur = sc.nextInt();
            sc.nextLine(); // Vidage du buffer

            // b. Traitement du choix utilisateur
            switch (choixUtilisateur) {

                // ---------- AJOUT CLIENT 
                case 1: {
                    System.out.print("Nom du client : ");
                    String nom = sc.nextLine();

                    System.out.print("Numéro de téléphone : ");
                    String telephone = sc.nextLine();

                    System.out.print("Email (laisser vide si aucun) : ");
                    String email = sc.nextLine();

                    // Appel de la bonne méthode selon la présence de l’email
                    if (email.isEmpty()) {
                        etab.ajouter(nom, telephone);
                    } else {
                        etab.ajouter(nom, telephone, email);
                    }
                    break;
                }

                // ---------- RECHERCHE CLIENT 
                case 2: {
                    System.out.print("Nom ou numéro de téléphone : ");
                    String recherche = sc.nextLine();
                    etab.afficher(recherche);
                    break;
                }

                // ---------- PLANIFICATION RDV 
                case 3:
                    etab.planifier();
                    break;

                // ---------- AFFICHAGE PLANNING COMPLET
                case 4:
                    etab.afficher();
                    break;

                // ---------- AFFICHAGE PLANNING D'UN JOUR 
                case 5: {
                    System.out.print("Date (yyyy-MM-dd) : ");
                    LocalDate jour = LocalDate.parse(sc.nextLine());
                    etab.afficher(jour);
                    break;
                }

                // ---------- AFFICHAGE RDV D’UN CLIENT
                case 6: {
                    System.out.print("Numéro du client : ");
                    int numeroClient = sc.nextInt();
                    sc.nextLine();
                    etab.afficher(numeroClient);
                    break;
                }

                // ---------- SORTIE DU PROGRAMME
                case 0:
                    System.out.println("Sauvegarde des données avant fermeture...");
                    break;

                // ---------- CHOIX INVALIDE ----------
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }

        } while (choixUtilisateur != 0);

        // 4. Sauvegarde des données dans les fichiers texte
        //    avant de quitter le programme
        etab.versFichierClients();
        etab.versFichierRendezVous();

        System.out.println("Données sauvegardées. Fin du programme.");

    } 
}
