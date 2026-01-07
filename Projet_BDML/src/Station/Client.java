package Station;

/**
 *
 * @author Teddy.R
 */
public class Client {
    private int numClient;
    private String nom;
    private String numTel;
    private String email;
    
    public Client (int numClient, String nom, String numTel){
            this.numClient=numClient;
            this.nom=nom;
            this.numTel=numTel;
            this.email=null;
    }
    
    public Client (int numClient, String nom, String numTel, String email){
        this.numClient=numClient;
        this.nom=nom;
        this.numTel=numTel;
        this.email=email;
    }
    
    /*GETTERS*/
    public int getNumClient(){
        return numClient;
    }
    
    public String getNom(){
        return nom;
    }
    
    public String getNumTel(){
        return numTel;
    }
        
    public String getEmail(){
        return email;
    }
    
    /*SETTERS*/
    public void setNumClient(int numClient){
        this.numClient=numClient;
    }
        
     public void setNom(String nom){
        this.nom=nom;
    }
    
     public void setNumTel(String numTel){
        this.numTel=numTel;
    }
     
     public void setEmail(String email){
        this.email=email;
    }
     

    @Override
    public String toString() {
        String affichage = 
                          "Client numero" + numClient +
                          " | Nom : " + nom +
                          " | Telephone : " +  numTel;

        if (email != null) {
             affichage += " | Email : " + email;
        }

        return affichage;
    }
    
    public boolean placerApres(Client autre) {
        int comp = this.nom.compareTo(autre.getNom());
        if (comp > 0)
            return true;
        else if (comp < 0)
            return false; 
        else 
            return this.numTel.compareTo(autre.getNumTel()) > 0;

    }
    
}
