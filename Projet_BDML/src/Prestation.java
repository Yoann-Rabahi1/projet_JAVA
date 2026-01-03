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
    
    private double prixLavage;
    private double prixSechage;
    private double prixPrelavage;
    private double prixNettoyage;
   

    private CategorieVehicule categorieVehicule;
    
    // constructeur
    
    public Prestation(double prixSechage, double prixLavage,
                  double prixNettoyage, double prixPrelavage,
                  CategorieVehicule categorieVehicule) 
    {
    this.prixSechage = prixSechage;
    this.prixLavage = prixLavage;
    this.prixNettoyage = prixNettoyage;
    this.prixPrelavage = prixPrelavage;
    this.categorieVehicule = categorieVehicule;
    }

    
    // getter

    public CategorieVehicule getCategorieVehicule() {
        return categorieVehicule;
    }
    
    public double getPrixSechage()
    {
        return prixSechage;
    }
    
    public double getPrixLavage()
    {
        return prixLavage;
    }
    
    public double getPrixPrelavage()
    {
        return prixPrelavage;
    }
    
    public double getPrixNettoyage()
    {
        return prixNettoyage;
    }
    
    // setter

    public void setCategorieVehicule(CategorieVehicule categorieVehicule) {
        this.categorieVehicule = categorieVehicule;
    }
    
    public void setPrixSechage(double prixSechage)
    {
        this.prixSechage = prixSechage;
    }
    
    public void setPrixLavage(double prixLavage)
    {
        this.prixLavage = prixLavage;
    }
    
    public void setPrixNettoyage(double prixNettoyage)
    {
        this.prixNettoyage = prixNettoyage;
    }
    
    public void setPrixPrelavage(double prixPrelavage)
    {
        this.prixPrelavage = prixPrelavage;
    }
    
    // méthode toString() abstraite
    
    @Override
    public abstract String toString();
    
    public abstract double lavage();
    
    public abstract double sechage();
    
    public abstract double prelavage();
    
    public abstract double nettoyage();
}
