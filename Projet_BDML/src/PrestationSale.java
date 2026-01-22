/*
 * TP   : Projet fin de semestre
 * Class: PrestationSale | Station de lavage
 * Name : Stéphane SINGERY, Yoann RABAHI, Teddy RAKOTOARIVELO
 * Group: ING1-APP-BDML2
 * Date : 2026-01-10
 */

// Import packages
package com.mycompany.station_de_lavage;

// Declare class
class PrestationSale extends Prestation {

    // ----------------------------- CONSTRUCTOR

    public PrestationSale(char categorieVehicule) {
        super(categorieVehicule);
    }

    // ----------------------------- METHOD 
    
    // ------------- OVERRIDE / PRIX
    
    /**
     * Calcul du prix total d'une prestation pour véhicule sale.
     * Aucun surcoût produit n'est prévu pour cette prestation.
     */
    @Override
    public double prixNettoyage() {
        return 
              super.prixPreLavage()
            + super.prixLavage()
            + super.prixSechage()
            + super.prixNettoyageInterieur();
    }
    
    // ------------- OVERRIDE / LECTURE ECRITURE FICHIER
    
    /**
     * Forme une chaîne de caractère composées des informations relatives à
     * une Prestation Sale.
     */
    @Override
    public String versFichier() {

        // Format : "B : 78"
        return this.categorieVehicule
            + " : "
            + (int) this.prixNettoyage();
    }
    
    // ------------- OVERRIDE / AFFICHE
    
    /**
     * Retourne un String composé des informations de la Prestation Sale.
     */
    @Override
    public String toString() {
        return "Prestation Sale :\n"
            + " - Catégorie véhicule : " + this.categorieVehicule + "\n"
            + " - Prix pre-lavage    : " + super.prixPreLavage() + "\n"
            + " - Prix lavage        : " + super.prixLavage() + "\n"
            + " - Prix séchage       : " + super.prixSechage() + "\n"
            + " - Prix intérieur     : " + super.prixNettoyageInterieur() + "\n"
            + " - Prix total         : " + this.prixNettoyage();
    }
}
