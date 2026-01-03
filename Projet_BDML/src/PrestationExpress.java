/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rabah
 */
public class PrestationExpress extends Prestation {
    
    private boolean a_nettoyer;
    
    public PrestationExpress(double prixSechage, double prixLavage, double prixNettoyage, double prixPrelavage, CategorieVehicule categorieVehicule, boolean a_nettoyer)
    {
        super(prixSechage, prixLavage, prixNettoyage, prixPrelavage, categorieVehicule);
        this.a_nettoyer = a_nettoyer;
    }
    
    // getter
    
    public boolean getANettoyer()
    {
        return a_nettoyer;
    }
    
    // setter
    
    public void setANettoyer(boolean a_nettoyer)
    {
        this.a_nettoyer = a_nettoyer;
    }
    
    
    @Override
    public String toString()
    {
        return "["
                +"categorie vehicule : " + getCategorieVehicule()
                +", à nettoyer : " + getANettoyer()
                + " ]";
    }
    
    
    public double lavage(double prix_lavage)
    {
        return prix_lavage;
    }
    
    public double sechage(double prix_sechage)
    {
        return prix_sechage;
    }
    
    public double prelavage(double prix_prelavage)
    {
        return prix_prelavage;
    }
    
    public double nettoyage(double prix_nettoyage)
    {
        return prix_nettoyage;
    }
    
    
    
            
    
}
