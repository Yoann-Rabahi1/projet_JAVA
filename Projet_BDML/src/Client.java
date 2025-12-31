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
    private String num_tel_client;
    private String mail_client;
    
    // constructeur sans mail
    
    public Client(int numero_client, String nom_client, String num_tel_client)
    {
        this.numero_client = numero_client;
        this.nom_client = nom_client;
        this.num_tel_client = num_tel_client;
        this.mail_client = null;
    }
    
    // constructeur avec mail
    
    public Client(int numero_client, String nom_client, String num_tel_client, String mail_client)
    {
        this.numero_client = numero_client;
        this.nom_client = nom_client;
        this.num_tel_client = num_tel_client;
        this.mail_client = mail_client;
    }
    
    
}
