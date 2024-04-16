import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConstructionArbre {
    protected LinkedHashMap<String, Integer> dicoFreq;
    protected ArrayList<Arbre> listeFeuilles;

    public ConstructionArbre(LinkedHashMap<String, Integer> dicoFreq) {
        this.dicoFreq = dicoFreq;
        this.listeFeuilles = new ArrayList<Arbre>();
        creerListeFeuilles();
    }

    public LinkedHashMap<String, Integer> getDicoFreq() {
        return dicoFreq;
    }

    public void setDicoFreq(LinkedHashMap<String, Integer> dicoFreq) {
        this.dicoFreq = dicoFreq;
    }

    public ArrayList<Arbre> getListeFeuilles() {
        return listeFeuilles;
    }

    public void setListeFeuilles(ArrayList<Arbre> listeFeuilles) {
        this.listeFeuilles = listeFeuilles;
    }

    public void creerListeFeuilles() {
        // Parcour du dictionnaire dicoFreq pour remplir la liste de feuilles
        for (Map.Entry<String, Integer> entry : dicoFreq.entrySet()) {
            String car = entry.getKey();
            Integer valeur = entry.getValue();
            listeFeuilles.add(new Arbre(valeur, car));
        }
    }
}
