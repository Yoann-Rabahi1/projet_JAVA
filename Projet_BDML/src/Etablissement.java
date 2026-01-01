/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rabah
 */

import java.util.Arrays;

public class Etablissement {
    
    private String nomEtablissement;
    private int nombreClients;
    private Client[] clients;
    private RendezVous[][] planningRDV;
    
    public Etablissement(String nomEtablissement, int nombreClients, int nbCreneaux)
    {
        this.nomEtablissement = nomEtablissement;
        this.nombreClients = nombreClients;
        this.clients = new Client[nombreClients];
        this.planningRDV = new RendezVous[nbCreneaux][7];
    }
    
    // getter

    public String getNomEtablissement()
    {
        return nomEtablissement;
    }
    
    public int getNombreClients()
    {
        return nombreClients;
    }
    
    public Client[] getClients()
    {
        return clients;
    }
    
    public RendezVous[][] getPlanningRDV()
    {
        return planningRDV;
    }
    
    // setter
    
    public void setNomEtablissement(String nomEtablissement)
    {
        this.nomEtablissement = nomEtablissement;
    }
    
    
    @Override
    public String toString()
    {
        return "[ "
                + "nom etablissement : " + getNomEtablissement()
                + ", nombre clients : " + getNombreClients()
                + ", listes des clients : " + Arrays.toString(clients)
                + ", planning des rdv : " + Arrays.deepToString(planningRDV)
                + " ]";
    }
    
    
    // nouvelles méthodes à implémenter
    
    /*
    
    méthode rechercher permettant de rechercher, dans le
    tableau de clients, le client correspondant à un nom et un numéro de téléphone donnés. La
    méthode doit retourner le client qu’elle a trouvé ou null s’il n’y en a pas.
    
    */
    
    public Client rechercher(Client[] clients, String nom, String numTel)
    {
        for (Client c : clients)
        {
            if(c != null && c.getNomClient().equals(nom) && c.getTelClient().equals(numTel))
            {
                return c;
            }
        } return null;
    }
           
}   
