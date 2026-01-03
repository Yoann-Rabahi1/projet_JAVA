/*
 * TP   : Projet fin de semestre
 * Class: PrestationSale | Station de lavage
 * Name : Stéphane SINGERY
 * Group: ING1-APP-BDML2
 * Date : 2026-01-01
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
    
    // ------------- OVERRIDE
    
    /**
     * Prix total d'une prestation pour véhicule sale :
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
