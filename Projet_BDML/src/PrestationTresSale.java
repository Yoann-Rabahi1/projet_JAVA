/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rabah
 */
public class PrestationTresSale extends Prestation {
    
    private enum TypeSalissure {niveau1, niveau2, niveau3, niveau4};
    
    private TypeSalissure type_salissure;
    
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
    
}
