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
    
    public PrestationExpress(CategorieVehicule categorieVehicule, boolean a_nettoyer)
    {
        super(categorieVehicule);
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
            
    
}
