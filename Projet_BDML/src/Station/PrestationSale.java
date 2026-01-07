/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Station;

/**
 *
 * @author Teddy.R
 */
public class PrestationSale extends Prestation{
    
    public PrestationSale(CategVehicule categorie) {
        super(categorie);
    }
    
    @Override
    public String toString() {
        return "Prestation Vehicule Sale | Categorie : " + categorie + " | ";
    }
    
    @Override
    public double nettoyage() {
        return prelavage() + lavage() + sechage() + nettoyageInterieur();
    }
}
