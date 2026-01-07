package Station;

/**
 *
 * @author Teddy.R
 */
public class PrestationTresSale extends Prestation {
    private final int salissure; /*un int et non un énum car il y a des salissures non défniti dans le sujet*/

    public PrestationTresSale(CategVehicule categorie, int salissure) {
        super(categorie);
        this.salissure=salissure;
    }
    
    /*GETTER*/
    public int getSalissure(){
        return salissure;
    }
    
    @Override
    public String toString() {
        String typeSalissureStr;
        switch (salissure) {/*mais un switch pour au moin dans le main avoir un meilleur visuel*/
            case 1:
                typeSalissureStr = "Graisse";
                break;
            case 2:
                typeSalissureStr = "Boue";
                break;
            case 3:
                typeSalissureStr = "Poussière";
                break;
            default:
                typeSalissureStr = "Inconnu";
        }
        return "Prestation Vehicule Très Sale | Categorie : " + categorie +
               " | Type de salissure : " + typeSalissureStr + " | ";
    }
    
    @Override
    public double nettoyage() {
        return prelavage() + lavage() + sechage() + nettoyageInterieur();
    }
}
