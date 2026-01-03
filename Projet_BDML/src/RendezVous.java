/*
 * TP   : Projet fin de semestre
 * Class: RendezVous | Station de lavage
 * Name : Stéphane SINGERY
 * Group: ING1-APP-BDML2
 * Date : 2026-01-01
 */

// Import packages
package com.mycompany.station_de_lavage;
import java.time.LocalDateTime;

// Declare class
class RendezVous {

    // ----------------------------- ATTRIBUTES

    private LocalDateTime creneau;    // 
    private Client        client;     // Object Client
    private Prestation    prestation; // Object Prestation
    private double        prix;       // (ex. 65.75) Prix € calculé du nettoyage

    // ----------------------------- CONSTRUCTOR

    public RendezVous(
        LocalDateTime creneau, 
        Client        client, 
        Prestation    prestation
    ) {
        this.creneau    = creneau;
        this.client     = client;
        this.prestation = prestation;

        // On calcule le prix une seule fois à la création du RDV
        this.prix = prestation.prixNettoyage();
    }

    // ----------------------------- GETTERS

    public LocalDateTime getCreneau() {
        return this.creneau;
    }

    public Client        getClient() {
        return this.client;
    }

    public Prestation    getPrestation() {
        return this.prestation;
    }

    public double        getPrix() {
        return this.prix;
    }

    // ----------------------------- SETTERS

    public void setCreneau(LocalDateTime creneau) {
        this.creneau    = creneau;
    }

    public void setClient(Client client) {
        this.client     = client;
    }

    public void setPrestation(Prestation prestation) {
        this.prestation = prestation;

        // Si on change la prestation, le prix doit être recalculé
        this.prix       = prestation.prixNettoyage();
    }

    // ----------------------------- METHOD
    
    // ------------- OVERRIDE

    /**
     * Retourne un String composé des informations relatives à un rendez-vous.
     */
    @Override
    public String toString() {
        return "Rendez-vous :\n"
            + " - Créneau    : " + creneau + "\n"
            + client + "\n"
            + " - Prestation : " + prestation.getClass().getSimpleName() + "\n"
            + " - Prix total : " + prix;
    }
}
