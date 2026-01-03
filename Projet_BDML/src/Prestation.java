/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rabah
 */
public abstract class Prestation {
    
    // attribut
    
    public enum CategorieVehicule { A, B, C }

    private CategorieVehicule categorieVehicule;
    
    // constructeur
    
    public Prestation(CategorieVehicule categorieVehicule) {
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
    
    public abstract double lavage();
    
    public abstract double sechage();
    
    public abstract double prelavage();
    
    public abstract double nettoyage();
}
