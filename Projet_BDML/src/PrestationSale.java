/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rabah
 */
public class PrestationSale extends Prestation{
    
    public PrestationSale(CategorieVehicule categorieVehicule)
    {
        super(categorieVehicule);
    }
    
    @Override
    public String toString()
    {
        return "["
                + " categorie véhicule : " + getCategorieVehicule()
                + " ]";
    }
    
    @Override
    public String versFichier()
    {
        String separator = System.lineSeparator();
        
        return this.getCategorieVehicule() + " : "
                + this.calculerPrix()
                + separator;
    }
}
