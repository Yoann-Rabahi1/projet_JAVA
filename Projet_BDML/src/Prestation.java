/*
 * TP   : Projet
 * Class: Prestation | Station de lavage
 * Name : Stéphane SINGERY
 * Group: ING1-APP-BDML2
 * Date : 2026-01-01
 */

// Import packages
package com.mycompany.station_de_lavage;

// Declare class
abstract class Prestation {

    // ----------------------------- ATTRIBUTE

    protected char categorieVehicule; // (A : citadines, B : berlines, C : monospaces et 4x4)

    // ----------------------------- CONSTRUCTOR

    public Prestation(char categorieVehicule) {
        this.categorieVehicule = categorieVehicule;
    }

    // ----------------------------- GETTER

    public char getCategorieVehicule() {
        return this.categorieVehicule;
    }

    // ----------------------------- SETTER

    public void setCategorieVehicule(char categorieVehicule) {
        this.categorieVehicule = categorieVehicule;
    }

    // ----------------------------- METHODS
    
    // ------------- MAJORATION / SURCOÛT

    /**
     * Retourne le coefficient de majoration pour lavage/prélavage :
     * A : +0%, B : +50%, C : +75%
     */
    protected double coefMajorationLavagePrelavage() {
        if (categorieVehicule == 'B') {
            return 1.50;
        }
        if (categorieVehicule == 'C') {
            return 1.75;
        }
        return 1.00; // 'A' par défaut
    }
    
    // -----

    /**
     * Retourne le coefficient de majoration pour séchage :
     * A : +0%, B : +5%, C : +10%
     */
    protected double coefMajorationSechage() {
        if (categorieVehicule == 'B') {
            return 1.05;
        }
        if (categorieVehicule == 'C') {
            return 1.10;
        }
        return 1.00; // 'A' par défaut
    }
    
    // -----

    /**
     * Surcoût "produit" si le véhicule est très sale.
     * Par défaut : 0 (pas de surcoût).
     * Surcoût appliqué dans la classe PrestationTresSale.
     */
    protected double surcoutProduit() {
        return 0.0;
    }
        
    // ------------- PRIX PRESTATION

    /**
     * Prix du lavage (base A = 20€) + éventuel surcoût produit.
     */
    public double prixLavage() {
        double base = 20.0 * coefMajorationLavagePrelavage();
        return base + surcoutProduit();
    }

    /**
     * Prix du séchage (base A = 10€).
     */
    public double prixSechage() {
        return 10.0 * coefMajorationSechage();
    }

    /**
     * Prix du prélavage (base A = 5€) + éventuel surcoût produit.
     */
    public double prixPreLavage() {
        double base = 5.0 * coefMajorationLavagePrelavage();
        return base + surcoutProduit();
    }

    /**
     * Prix du nettoyage intérieur :
     * A ou B : 30€, C : 40€
     */
    protected double prixNettoyageInterieur() {
        if (categorieVehicule == 'C') {
            return 40.0;
        }
        return 30.0;
    }
    
    // ------------- ABSTRACT

    /**
     * Prix total de la prestation (dépend du type de prestation).
     */
    public abstract double prixNettoyage();

    /** 
     * Afficher information
     */
    @Override
    public abstract String toString();
}
