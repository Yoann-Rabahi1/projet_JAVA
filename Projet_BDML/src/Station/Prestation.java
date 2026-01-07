package Station;

/**
 *
 * @author Teddy.R
 */


public abstract class Prestation {
    public enum CategVehicule {A,B,C}
    public CategVehicule categorie;
    
    public Prestation (CategVehicule categorie){
        this.categorie = categorie;
    }
    
    /*GETTER*/
    public CategVehicule getCategorie(){
        return categorie;
    }
    
    /*SETTER*/
    public void setCategorie(CategVehicule categorie){
        this.categorie=categorie;
    }
    
    @Override
    public String toString(){
        return "Prestration " + getCategorie();
    }
    
        public double lavage() {
        double prix = 20; /*si c'est une voiture de catégorie A*/
        if (categorie == CategVehicule.B) 
            prix += prix *0.5;
        else if (categorie == CategVehicule.C)
            prix += prix *0.75;
        return prix;
    }

    public double sechage() {
        double prix = 10; /*si c'est une voiture de catégorie A*/
        if (categorie == CategVehicule.B)
            prix += prix* 0.05;
        else if (categorie == CategVehicule.C)
            prix +=prix*0.1;
        return prix;
    }

    public double prelavage() {
        double prix = 5; /*si c'est une voiture de catégorie A*/
        if (categorie == CategVehicule.B)
            prix += prix*0.5;
        else if (categorie == CategVehicule.C)
            prix += prix*0.75;
        return prix;
    }
    
    protected double nettoyageInterieur() {
        return (categorie == CategVehicule.C) ? 40 : 30;
    }
    
    public abstract double nettoyage();
}
