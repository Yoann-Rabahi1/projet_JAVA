/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rabah
 */

import java.time.*;
        
public class RendezVous {
    
    private LocalDateTime creneau;
    private Client client;
    private Prestation prestation;
    private double prix;
    
    public RendezVous(Client client, Prestation prestation, LocalDateTime creaneau)
    {
        this.client = client;
        this.prestation = prestation;
        this.creneau = creneau;
        this.prix = prestation.lavage()
          + prestation.sechage()
          + prestation.prelavage()
          + prestation.nettoyage();

    }
    
    // getter
    
    public Client getClient()
    {
        return client;
    }
    
    public Prestation getPrestation()
    {
        return prestation;
    }
    
    public double getPrix()
    {
        return prix;
    }
    
    // setter
    
    public void setClient(Client client)
    {
        this.client = client;
    }
    
    public void setPrestation(Prestation prestation)
    {
        this.prestation = prestation;
    }
    
    public void setPrix(double prix)
    {
        this.prix = prix;
    }
    
    @Override
    public String toString()
    {
        return "["
                + " client : " + getClient()
                + ", prestation : " + getPrestation()
                + ", prix : " + getPrix()
                + " ]";
    }
    
    public String versFichier()
    {
        String separator = System.lineSeparator();
        
        return this.creneau + separator
                + this.client.getNumeroClient() + separator
                + this.prestation.versFichier();
    }
    
}
