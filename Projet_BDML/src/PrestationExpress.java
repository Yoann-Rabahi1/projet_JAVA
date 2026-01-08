/*
 * TP   : Projet fin de semestre
 * Class: PrestationExpress | Station de lavage
 * Name : Stéphane SINGERY, Yoann RABAHI, Teddy RAKOTOARIVELO
 * Group: ING1-APP-BDML2
 * Date : 2026-01-01
 */

// Import packages
package com.mycompany.station_de_lavage;

// Declare class
class PrestationExpress extends Prestation {

    // ----------------------------- ATTRIBUTE

    private boolean nettoyerInterieur; // (ex. TRUE)

    // ----------------------------- CONSTRUCTOR

    public PrestationExpress(
        char    categorieVehicule,
        boolean nettoyerInterieur
    ) {
        super(categorieVehicule);
        this.nettoyerInterieur = nettoyerInterieur;
    }

    // ----------------------------- GETTER

    public boolean getNettoyerInterieur() {
        return this.nettoyerInterieur;
    }

    // ----------------------------- SETTER

    public void setNettoyerInterieur(boolean nettoyerInterieur) {
        this.nettoyerInterieur = nettoyerInterieur;
    }

    // ----------------------------- METHOD
    
    // ------------- OVERRIDE

    @Override
    /**
     * Retourne le prix du nettoyage intérieur pour une prestation express.
     * Si le nettoyage intérieur n'est pas demandé => 0
     * Sinon, on utilise la règle tarifaire définie dans la classe Prestation.
     */
    public double prixNettoyageInterieur() {
        if (!this.nettoyerInterieur) {
            return 0.0;         
        }
        // Méthode héritée de la classe Prestation
        return super.prixNettoyageInterieur();
    }
    
    @Override
    /**
     * Prix total d'une prestation express.
     */
    public double prixNettoyage() {
        return 
              super.prixLavage() 
            + super.prixSechage() 
            + this.prixNettoyageInterieur();
    }

    @Override
    /**
     * Retourne un String composé des informations de la prestation express.
     */
    public String toString() {
        return "Prestation Express :\n"
            + " - Catégorie véhicule     : " + this.categorieVehicule + "\n"
            + " - Nettoyage intérieur    : " + this.nettoyerInterieur + "\n"
            + " - Prix lavage            : " + super.prixLavage() + "\n"
            + " - Prix séchage           : " + super.prixSechage() + "\n"
            + " - Prix intérieur         : " + this.prixNettoyageInterieur() + "\n"
            + " - Prix total (nettoyage) : " + this.prixNettoyage();
    }
}
