/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rabah
 */
public abstract class Prestation {
    
    // attributs
    
    public enum CategorieVehicule { A, B, C }


    private CategorieVehicule categorieVehicule;
    
    // constructeur
    
    public Prestation(CategorieVehicule categorieVehicule) 
    {
    this.categorieVehicule = categorieVehicule;
    }

    
    // getter

    public CategorieVehicule getCategorieVehicule() {
        return categorieVehicule;
    }
    
    
    // setter

    public void setCategorieVehicule(CategorieVehicule categorieVehicule) {
        this.categorieVehicule = categorieVehicule;
    }
    

    
    // méthode toString() abstraite
    
    @Override
    public abstract String toString();
    
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
    
    public double nettoyage()
    {
        double prixNettoyage;
        
        if(this.getCategorieVehicule() == CategorieVehicule.A ||
                this.getCategorieVehicule() == CategorieVehicule.B)
        {
            prixNettoyage = 30;
            return prixNettoyage;
        }
        
        else
        {
            prixNettoyage = 40;
            return prixNettoyage;
        }
        
    }
    
}
