package java_1;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class ConstructionArbre {
    // Attributes
    protected LinkedHashMap<String, Integer> dicoFreq;
    protected ArrayList<Arbre> listeArbres;
    protected Arbre arbreHuffman;
    protected LinkedHashMap<String, String> dicoCodeBinaire;
    

    // Getters and setters

    /** 
     * @return LinkedHashMap<String, Integer>
     */
    public LinkedHashMap<String, Integer> getDicoFreq() {
        return dicoFreq;
    }

    public void setDicoFreq(LinkedHashMap<String, Integer> dicoFreq) {
        this.dicoFreq = dicoFreq;
    }

    public ArrayList<Arbre> getlisteArbres() {
        return listeArbres;
    }

    public void setlisteArbres(ArrayList<Arbre> listeArbres) {
        this.listeArbres = listeArbres;
    }

    // Constructeur

    /**
     * Initialise également certains attributs
     * @param dicoFreq
     *      le dictionnaire des caractères et leur fréquence
    */
    public ConstructionArbre(LinkedHashMap<String, Integer> dicoFreq) {
        this.dicoFreq = dicoFreq;
        this.listeArbres = new ArrayList<Arbre>();
        creerListeArbres();
        this.dicoCodeBinaire = new LinkedHashMap<String, String>();
        initDicoCodeBinaire();
    }

    public void initDicoCodeBinaire() { 
        for (Map.Entry<String, Integer> entry : dicoFreq.entrySet()) {
            String key = entry.getKey();
            dicoCodeBinaire.put(key, null);
        }
    }

    

    /** 
     * Parcours le dictionnaire dicoFreq pour remplir la liste avec des feuilles
     */
    public void creerListeArbres() { 
        for (Map.Entry<String, Integer> entry : dicoFreq.entrySet()) {
            String car = entry.getKey();
            Integer valeur = entry.getValue();
            listeArbres.add(new Arbre(valeur, car));
        }
    }

    /*
     * Construit l'arbre de Huffman de manière récursive à partir de la liste d'arbres triée
     * Le fait que la liste soit trié même après la création d'un nouvelle arbre
     * permet de toujours sélectionner les deux premiers arbres
    */
    public void construireArbre() {
        if (listeArbres.size() == 1) { // si il ne reste plus que la racine dans la liste d'arbre
            //System.out.println("bonjour");
            arbreHuffman = listeArbres.get(0);
            
        } else {
            Arbre first = listeArbres.get(0);
            Arbre second = listeArbres.get(1);

            int sommeFreq = first.freq + second.freq;
            
            Arbre newTree = new Arbre(sommeFreq, first, second);
            ajouterArbreDansListeArbres(newTree);
            listeArbres.remove(first);
            listeArbres.remove(second);
            
            construireArbre();
        }
    }

    /**
     * Ajoute tree dans listeArbres qui est trié par fréquence, si il y a égalité tree sera ajouté après
     * @param tree
     *      l'arbre à ajouter dans la liste
    */
    public void ajouterArbreDansListeArbres(Arbre tree) {
        
        int i=0;
        boolean fini=false;
        float supFreq = tree.freq + 0.5f; // si il y a égalité tree sera ajouté après
        
        while (i<listeArbres.size() && !fini) {
            
            if (supFreq > listeArbres.get(i).freq && i == listeArbres.size()-1  ) { // tree 
                fini = true;
                listeArbres.add(tree);
            } else if (supFreq > listeArbres.get(i).freq && supFreq < listeArbres.get(i+1).freq ) { // deux condition pour bien placer tree en dernier des arbre de meme fréquence
                fini = true;
                listeArbres.add(i+1, tree);
            }
            i++;
        }
    }


    /**
     * Remplie dicoCodeBinaire en parcourant récursivement l'arbre de Huffman en ajoutant 0 pour les branches gauche 
     * et 1 pour celles de droite, quand il arrive sur une feuille il ajoute code dans le dictionnaire au caractère de la feuille
     * 
     * @param arbre
     *      l'arbre de Huffman
     * @param code
     *      le code de chaque feuille
     * 
    */
    
    public void creerCodeBinaire(Arbre arbre, String code) {
        if (arbre.estFeuille()) {
            dicoCodeBinaire.put(arbre.car, code);
        } else {
            creerCodeBinaire(arbre.filsGauche, code + "0");
            creerCodeBinaire(arbre.filsDroit, code + "1");
        }
    }     
}