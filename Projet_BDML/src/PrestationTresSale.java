/*
 * TP   : Projet fin de semestre
 * Class: PrestationTresSale | Station de lavage
 * Name : Stéphane SINGERY
 * Group: ING1-APP-BDML2
 * Date : 2026-01-01
 */

// Import packages
package com.mycompany.station_de_lavage;

// Declare class
class PrestationTresSale extends Prestation {

    // ----------------------------- ATTRIBUTE

    private int typeSalissure; // (1 : nourriture, 2 : boue, 3 : transpiration, 4 : graisse, etc.)

    // ----------------------------- CONSTRUCTOR

    public PrestationTresSale(
        char categorieVehicule, 
        int  typeSalissure
    ) {
        super(categorieVehicule);
        this.typeSalissure = typeSalissure;
    }

    // ----------------------------- GETTER

    public int getTypeSalissure() {
        return this.typeSalissure;
    }

    // ----------------------------- SETTER

    public void setTypeSalissure(int typeSalissure) {
        this.typeSalissure = typeSalissure;
    }

    // ----------------------------- METHOD
    
    // ------------- OVERRIDE

    /**
     * Surcoût lié au produit utilisé selon le type de salissure.
     * Paramétré à 0 € pour le moment.
     */
    @Override
    protected double surcoutProduit() {
        return 0.0;
    }

    /**
     * Prix total d'une prestation pour véhicule très sale :
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
     * Retourne un String composé des informations de la Prestation Très Sale.
     */
    @Override
    public String toString() {
        return "Prestation Véhicule Très Sale :\n"
            + " - Catégorie véhicule : " + this.categorieVehicule + "\n"
            + " - Type de salissure  : " + this.typeSalissure + "\n"
            + " - Prix pre-lavage    : " + super.prixPreLavage() + "\n"
            + " - Prix lavage        : " + super.prixLavage() + "\n"
            + " - Prix séchage       : " + super.prixSechage() + "\n"
            + " - Prix intérieur     : " + super.prixNettoyageInterieur() + "\n"
            + " - Prix total         : " + this.prixNettoyage();
    }
}
