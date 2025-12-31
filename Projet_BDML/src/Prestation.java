/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rabah
 */
public abstract class Prestation {

    public enum CategorieVehicule { A, B, C }

    private CategorieVehicule categorieVehicule;

    public Prestation(CategorieVehicule categorieVehicule) {
        this.categorieVehicule = categorieVehicule;
    }

    public CategorieVehicule getCategorieVehicule() {
        return categorieVehicule;
    }

    public void setCategorieVehicule(CategorieVehicule categorieVehicule) {
        this.categorieVehicule = categorieVehicule;
    }
    
    @Override
    public abstract String toString();
}
