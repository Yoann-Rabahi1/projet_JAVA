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
    
    
    @Override
    public String versFichier(double prix) {
        return getCategorie() + " : " + (int)prix;
    }

}
