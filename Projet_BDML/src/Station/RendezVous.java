package Station;

import java.time.LocalDateTime;

/**
 *
 * @author Teddy.R
 */

public class RendezVous {
    private Client client;
    private Prestation prestation;
    private LocalDateTime dateHeure;
    private double prix;

    public RendezVous(Client client, Prestation prestation, LocalDateTime dateHeure) {
        this.client = client;
        this.prestation = prestation;
        this.dateHeure = dateHeure;
        this.prix = (prestation != null) ? prestation.nettoyage() : 0.0;
    }

    /*GETTERS*/
    public Client getClient() {
        return client;
    }

    public Prestation getPrestation() {
        return prestation;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public double getPrix() {
        return prix;
    }
    
    /*SETTER*/
    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    @Override
    public String toString() {
        return "RendezVous | Client : " + client.getNom() +
               " | Prestation : " + prestation +
               " | Date/Heure : " + dateHeure +
               " | Prix : " + prix + "€]";
    }
}