/*
 * TP   : Projet fin de semestre
 * Class: PrestationTresSale | Station de lavage
 * Name : Stéphane SINGERY, Yoann RABAHI, Teddy RAKOTOARIVELO
 * Group: ING1-APP-BDML2
 * Date : 2026-01-10
 */

// Import packages
package com.mycompany.station_de_lavage;

// Declare class
class PrestationTresSale extends Prestation {

    // ----------------------------- ATTRIBUTE

    /** 
     * (1 : nourriture, 2 : boue, 3 : transpiration, 4 : graisse, etc.)
     * Tableau : permet le choix de plusieurs salissures à la fois
     */
    private int[] typesSalissure; 

    // ----------------------------- CONSTRUCTOR

    public PrestationTresSale(
        char  categorieVehicule, 
        int[] typesSalissure   
    ) {
        super(categorieVehicule);
        this.typesSalissure = typesSalissure;
    }

    // ----------------------------- GETTER

    public int[] getTypeSalissure() {
        return this.typesSalissure;
    }

    // ----------------------------- SETTER

    public void setTypeSalissure(int[] typesSalissure) {
        this.typesSalissure = typesSalissure;
    }

    // ----------------------------- METHOD
    
    // ------------- SALISSURES
    
    /**
     * Retourne le label associé au type de salissure.
     */
    private String libelleSalissure(int type) {
        switch (type) {
            case 1:
                return "nourriture";
            case 2:
                return "transpiration";
            case 3:
                return "boue";
            case 4:
                return "graisse";
            default:
                return "inconnu";
        }
    }
    
    // -----
    
    /**
     * Construit une chaîne de caractères composée des labels associés aux 
     * salissures sélectionnées.
     */
    private String typesSalissureToString() {

        String result = "";

        for (int i = 0; i < typesSalissure.length; i++) {
            result += libelleSalissure(typesSalissure[i]);
            if (i < typesSalissure.length - 1) {
                result += ", ";
            }
        }
        return result;
    }
    
    // ------------- PRIX
    
    /**
     * Retourne le montant du surcoût associé au type de salissure.
     */
    protected double calculerSurcout(int type) {
        switch (type) {
            
            // taches de nourriture
            case 1:
                return 5.0;
                
            // taches de transpiration
            case 2: 
                return 10.0;
                
            // taches de boue
            case 3: 
                return 15.0;                      
                
            // taches de graisse
            case 4:
                return 20.0;
                
            default:
                return 0.0;
        }
    }
    
    // ------------- OVERRIDE / PRIX
    
    /**
     * Calcul le surcoût total lié au produit utilisé selon le type de salissure.
     * Paramétré à 0 € par défaut.
     */
    @Override
    protected double surcoutProduit() {

        double total = 0.0;

        for (int type : typesSalissure) {
            total += calculerSurcout(type);
        }

        return total;
    }      
    
    // -----

    /**
     * Forme une chaîne de caractère composées des informations relatives à
     * une Prestation Très Sale, avec plusieurs salissures possibles.
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
    
    @Override
    public String versFichier() {

        // Construction d'une chaîne de caractère composée des types de salissure.
        String salissures = "";

        if (typesSalissure != null && typesSalissure.length > 0) {

            for (int i = 0; i < typesSalissure.length; i++) {
                salissures += typesSalissure[i];
                if (i < typesSalissure.length - 1) {
                    salissures += ",";
                }
            }
        }

        // Format : "A : 1,3,4 : 95"
        return this.categorieVehicule
                + " : "
                + salissures
                + " : "
                + (int) this.prixNettoyage();
    }
    
    // ------------- OVERRIDE / AFFICHE

    /**
     * Retourne un String composé des informations de la Prestation Très Sale.
     */
    @Override
    public String toString() {
        return "Prestation Véhicule Très Sale :\n"
            + " - Catégorie véhicule : " + this.categorieVehicule + "\n"
            + " - Type de salissure  : " + this.typesSalissureToString() + "\n"
            + " - Prix pre-lavage    : " + super.prixPreLavage() + "\n"
            + " - Prix lavage        : " + super.prixLavage() + "\n"
            + " - Prix séchage       : " + super.prixSechage() + "\n"
            + " - Prix intérieur     : " + super.prixNettoyageInterieur() + "\n"
            + " - Prix total         : " + this.prixNettoyage();
    }
}
