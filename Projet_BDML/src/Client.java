/*
 * TP     : Projet
 * Classe : Client | Station de lavage
 * Nom    : Stéphane SINGERY
 * Groupe : ING1-APP-BDML2
 * Année  : 2026-01-01
 */

// Import packages
package com.mycompany.station_de_lavage;

// Declare class
class Client {

    // ----------------------------- ATTRIBUTE

    private int    numeroClient; // (ex. 1)
    private String nom;          // (ex. QUIPROQUO)
    private String telephone;    // (ex. 0605040302)
    private String email;        // (nom@mail.fr) Peut être null si non renseigné

    // ----------------------------- CONSTRUCTOR

    /**
     * Constructeur d'un client sans adresse électronique
     */
    public Client(
        int    numeroClient, 
        String nom, 
        String telephone
    ) {
        this.numeroClient = numeroClient; 
        this.nom          = nom;          
        this.telephone    = telephone;    
        this.email        = null;         
    }

    /**
     * Constructeur d'un client avec adresse électronique
     */
    public Client(
        int    numeroClient,
        String nom, 
        String telephone, 
        String email
    ) {
        this.numeroClient = numeroClient;
        this.nom          = nom;
        this.telephone    = telephone;
        this.email        = email;
    }
    
    // ----------------------------- GETTER
    
    public String getTelephone() {
        return this.telephone;
    }

    public String getNom() {
        return this.nom;
    }

    // ----------------------------- SETTER

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public void setNom(String nom) {
        this.nom       = nom;
    }

    // ----------------------------- METHOD 
    
    // ------------- COMPARAISON CLIENT
    
    /**
     * Indique si le client courant doit être placé après l'autre client dans le
     * tableau des clients.
     *
     * @param autre autre objet client avec lequel comparer
     * @return      boolean true si le client courant doit être placé après l'autre, 
     *              false sinon
     */
    public boolean placerApres(Client autreClient) {

        // Comparaison des deux noms
        int compareNom = this.nom.compareTo(autreClient.nom);

        // Nom à placer après
        if (compareNom > 0) {
            return true;    
        }
        
        // Nom à placer avant
        if (compareNom < 0) {
            return false;
        }

        // Si les noms sont identiques : comparaison des numéros de téléphone
        return this.telephone.compareTo(autreClient.telephone) > 0;
    }
    
    // ------------- OVERRIDE / AFFICHER

    @Override
    /**
     * Retourne un String composé des informations du client.
     */
    public String toString() {

        String stringResult = " - Numéro client : " + numeroClient + "\n"
                        + " - Nom           : " + nom + "\n"
                        + " - Téléphone     : " + telephone + "\n";

        // Gèrer client sans adresse mail fournie
        if (email != null) {
            stringResult += " - Email         : " + email + "\n";
        }

        return stringResult;
    }
}
