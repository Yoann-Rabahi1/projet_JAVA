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
    
    @Override
    public double lavage()
    {
        
       double prixLavage;
        
        switch(this.getCategorieVehicule())
        {
            case A:
                prixLavage = 20;
                return prixLavage;
            
            case B:
                prixLavage = 20 + (20 * 0.5); 
                return prixLavage;
            
            case C:
                prixLavage = 20 + (20 * 0.75);
                return prixLavage;
        }
        return 0;
    }
    
    @Override
    public double sechage()
    {
        double prixSechage;
        
        switch(this.getCategorieVehicule())
        {
            case A:
                prixSechage = 10 ;
                return prixSechage;
            
            case B:
                prixSechage = 10  + (10  * 0.05); 
                return prixSechage;
            
            case C:
                prixSechage = 10  + (10 * 0.1);
                return prixSechage;
        }
        return 0;
    }
    
    @Override
    public double prelavage()
    {
        double prixPrelavage;
        
        switch(this.getCategorieVehicule())
        {
            case A:
                prixPrelavage = 5 ;
                return prixPrelavage;
            
            case B:
                prixPrelavage = 5  + (5  * 0.5); 
                return prixPrelavage;
            
            case C:
                prixPrelavage = 5  + (5 * 0.75);
                return prixPrelavage;
        }
        return 0;
    }
    
    @Override
    public double nettoyage()
    {
        return a_nettoyer ? super.nettoyage() : 0;
    }
            
    
}
