/*
 * TP   : Projet fin de semestre
 * Class: prestationResolution | Station de lavage
 * Name : Stéphane SINGERY, Yoann RABAHI, Teddy RAKOTOARIVELO
 * Group: ING1-APP-BDML2
 * Date : 2026-01-10
 */

// Import packages
package com.mycompany.station_de_lavage;

/**
 * Classe additionnelle qui permet de reconstruire une prestation à partir
 * d'une ligne lue depuis un fichier texte.
 */
class prestationResolution {

    /**
     * Construit une prestation à partir de sa représentation textuelle.
     *
     * @param ligne ligne du fichier décrivant la prestation
     * @return      prestation correspondante
     */
    public static Prestation depuisFichier(String ligne) {

        // Sépare les différentes informations contenues dans la ligne
        String[] infos = ligne.split(" : ");
        char categorie = infos[0].charAt(0);

        // Prestation Express, format : "A : true : 60"
        if (infos.length == 3
                && (infos[1].equals("true") || infos[1].equals("false"))
        ) {
            boolean interieur = Boolean.parseBoolean(infos[1]);
            return new PrestationExpress(categorie, interieur);
        }

        // Prestation Sale, format : "B : 78"
        if (infos.length == 2) {
            return new PrestationSale(categorie);
        }

        // Prestation Très Sale, format : "A : 1,3,4 : 95"
        String[] tabTypes = infos[1].split(",");
        
        int[] typesSalissure = new int[tabTypes.length];
        for (int i = 0; i < tabTypes.length; i++) {
            typesSalissure[i] = Integer.parseInt(tabTypes[i]);
        }
        
        return new PrestationTresSale(categorie, typesSalissure);
    }
}
