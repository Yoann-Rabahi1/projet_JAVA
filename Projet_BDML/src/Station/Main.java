package Station;

import java.util.Scanner;

/**
 *
 * @author Teddy.R
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Etablissement etab = new Etablissement("LavagePro", 100, 16);

        // --- Chargement fichiers ---
        etab.depuisFichierClients("clients.txt");
        etab.depuisFichierRDV("rdv.txt");

        int choix = 0;

        while (choix != 5) {

            System.out.println("\n==== MENU ====");
            System.out.println("(1) - Planifier un rendez-vous");
            System.out.println("(2) - Afficher planning du jour");
            System.out.println("(3) - Rechercher un client avec son nom ou son num de tel");
            System.out.println("(4) - Afficher les RDV d'un client");
            System.out.println("(5) - Quitter");
            System.out.print("Choix : ");

            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {

                case 1:
                    etab.planifier();
                    break;

                case 2:
                    System.out.print("Jour (1 à 7) : ");
                    int jour = sc.nextInt();
                    sc.nextLine();
                    etab.afficher(jour); // affiche planning du jour
                    break;

                case 3:
                    System.out.print("Nom ou tél : ");
                    String rech = sc.nextLine();
                    etab.afficher(rech); // affiche client(s)
                    break;

                case 4:
                    System.out.print("Numéro client : ");
                    int numc = sc.nextInt();
                    sc.nextLine();
                    etab.afficher(numc, true); // affiche RDV du client
                    break;

                case 5:
                    System.out.println("Sauvegarde des fichiers...");
                    etab.versFichierClients("clients.txt");
                    etab.versFichierRDV("rdv.txt");
                    System.out.println("FIINNNN");
                    break;

                default:
                    System.out.println("Choix invalide.");
                    break;
            }
        }
    }
}
