package java_1;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class ConstructionArbre {
    protected LinkedHashMap<String, Integer> dicoFreq;
    protected ArrayList<Arbre> listeArbres;
    protected Arbre arbreHuffman;
    protected LinkedHashMap<String, String> dicoCodeBinaire;
    


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

    public void creerListeArbres() {
        // Parcours du dictionnaire dicoFreq pour remplir la liste de feuilles
        for (Map.Entry<String, Integer> entry : dicoFreq.entrySet()) {
            String car = entry.getKey();
            Integer valeur = entry.getValue();
            listeArbres.add(new Arbre(valeur, car));
        }
    }

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

    public void ajouterArbreDansListeArbres(Arbre tree) {
        // ajoute tree dans la listeArbres trié par fréquence, 
        // si il y a égalité tree sera ajouté après 
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


    /*def codeBinaire(arbre, dico, code=""):
        if arbre.estFeuille():
            dico[arbre.getCaractere()] = code
        else :
            codeBinaire(arbre.getGauche(), dico, code + "0")
            codeBinaire(arbre.getDroite(), dico, code + "1")   */
    
    public void creerCodeBinaire(Arbre arbre, String code) {
        if (arbre.estFeuille()) {
            dicoCodeBinaire.put(arbre.car, code);
        } else {
            creerCodeBinaire(arbre.filsGauche, code + "0");
            creerCodeBinaire(arbre.filsDroit, code + "1");
        }
    }     
}