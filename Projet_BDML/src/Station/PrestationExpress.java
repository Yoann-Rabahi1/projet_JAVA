/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Station;

/**
 *
 * @author Teddy.R
 */
public class PrestationExpress extends Prestation{
    private boolean nettoyerInterieur;
    public PrestationExpress(CategVehicule categorie,  boolean nettoyerInterieur) {
        super(categorie);
        this.nettoyerInterieur=nettoyerInterieur;
    }
    
    @Override
    public String toString() {
        return "Prestation Express | Categorie : " + categorie +
               " | Nettoyage interieur : " + (nettoyerInterieur ? "oui" : "non") + " |";
    }
    
    @Override
    public double nettoyage() {
        double total = sechage() + lavage();
        if (nettoyerInterieur) {
            total += nettoyageInterieur();
        }
        return total;
    }
    
}
