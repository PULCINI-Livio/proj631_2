import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConstructionArbre {
    protected LinkedHashMap<String, Integer> dicoFreq;
    protected ArrayList<Arbre> listeArbres;
    protected Arbre arbreHuffman;

    public ConstructionArbre(LinkedHashMap<String, Integer> dicoFreq) {
        this.dicoFreq = dicoFreq;
        this.listeArbres = new ArrayList<Arbre>();
        creerListeArbres();
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
            System.out.println("bonjour");
            arbreHuffman = listeArbres.get(0);
            
        } else {
            System.out.println("liste freq=");
            for (Arbre arbre:listeArbres){
                //System.out.println(arbre.car);
                System.out.println(arbre.freq);
            }
            
            System.out.println("Taille liste arbre");
            System.out.println(listeArbres.size());
            Arbre first = listeArbres.get(0);
            Arbre second = listeArbres.get(1);

            int sommeFreq = first.freq + second.freq;
            System.out.println("sommeFreq=");
            System.out.println(sommeFreq);

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
}
