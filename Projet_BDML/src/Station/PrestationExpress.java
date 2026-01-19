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
    
    @Override
    public String versFichier(double prix) {
        return getCategorie() + " : " + nettoyerInterieur + " : " + (int)prix;
    }

    
}
