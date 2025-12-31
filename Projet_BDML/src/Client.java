/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rabah
 */

// un numéro de client, son nom, son numéro de téléphone et, éventuellement, son adresse électronique.
public class Client {
    
    private int numero_client;
    private String nom_client;
    private String tel_client;
    private String mail_client;
    
    // constructeur sans mail
    
    public Client(int numero_client, String nom_client, String tel_client)
    {
        this.numero_client = numero_client;
        this.nom_client = nom_client;
        this.tel_client = tel_client;
        this.mail_client = null;
    }
    
    // constructeur avec mail
    
    public Client(int numero_client, String nom_client, String tel_client, String mail_client)
    {
        this.numero_client = numero_client;
        this.nom_client = nom_client;
        this.tel_client = tel_client;
        this.mail_client = mail_client;
    }
    
    // Getters
    
    public int getNumeroClient()
    {
        return numero_client;
    }
    
    public String getNomClient()
    {
        return nom_client;
    }
    
    public String getTelClient()
    {
        return tel_client;
    }
    
    public String getMailClient()
    {
        return mail_client;
    }
    
    // Setters
    
    public void setNumeroClient(int numero_client)
    {
        this.numero_client = numero_client;
    }
    
    public void setNomClient(String nom_client)
    {
        this.nom_client = nom_client;
    }
    
    public void setTelClient(String tel_client)
    {
        this.tel_client = tel_client;
    }
    
    public void setMailClient(String mail_client)
    {
        this.mail_client = mail_client;
    }
    
    // méthode toString()
    
    @Override
    public String toString()
    {
        return "["
                + "numero client : " + getNumeroClient()
                + ", nom client : " + getNomClient()
                + ", telephone client : " + getTelClient()
                + ", mail client : " + getMailClient()
                + "]";
    }
    
}
