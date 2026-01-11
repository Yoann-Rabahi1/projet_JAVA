/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rabah
 */
public class PrestationTresSale extends Prestation {
    
    public enum TypeSalissure {nourriture, boue, transpiration, graisse};
    
    public TypeSalissure type_salissure;
    
    public PrestationTresSale(CategorieVehicule categorieVehicule, TypeSalissure type_salissure)
    {
        super(categorieVehicule);
        this.type_salissure = type_salissure;
    }
    
    // getter
    
    public TypeSalissure getTypeSalissure()
    {
        return type_salissure;
    }
    
    // setter
    
    public void setTypeSalissure(TypeSalissure type_salissure)
    {
        this.type_salissure = type_salissure;
    }
    
    
    @Override
    public String toString()
    {
        return "["
                + "categorie véhicule : " + getCategorieVehicule()
                + ", type salissure : " + getTypeSalissure()
                + " ]";
    }
    
    @Override
    public String versFichier()
    {
        String separator = System.lineSeparator();
        
        return this.getCategorieVehicule() + " : "
                + this.getTypeSalissure() + " : "
                + this.calculerPrix() 
                + separator;
    }
    
}
