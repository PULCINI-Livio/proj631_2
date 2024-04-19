import java.util.Collections;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AlphabetFrequence {
    protected String txt;                               // ~ 'bonjour'
    protected ArrayList<String> choppedTxtList;         // ~ ['b','o','n','j','o','u','r']
    protected ArrayList<String> txtList;                // ~ ['b','o','n','j','u','r']
    protected ArrayList<Integer> freqList;              // ~ [ 1 , 2 , 1 , 1 , 1 , 1 ]
    protected LinkedHashMap<String, Integer> dicoFreq;  // ~ {b=1,j=1,n=1,r=1,u=1,o=2,}
    

    public AlphabetFrequence(String txt){
        this.txt = txt;
        this.choppedTxtList = new ArrayList<String>();
        this.txtList = new ArrayList<String>();
        this.freqList = new ArrayList<Integer>();
        this.dicoFreq = new LinkedHashMap<String, Integer>();
    }
    
    //Getters and Setters
    public String getTxt() {
        return this.txt;
    }

    public void setTxt(String new_txt) {
        this.txt = new_txt;
    }

    public ArrayList<String> getChoppedTxtList() {
        return this.choppedTxtList;
    }

    public void setChoppedTxtList(ArrayList<String> newChoppedTxtList) {
        this.txtList = newChoppedTxtList;
    }

    public ArrayList<String> getTxtList() {
        return this.txtList;
    }

    public void setTxtList(ArrayList<String> newTxtList) {
        this.txtList = newTxtList;
    }

    public ArrayList<Integer> getFreqList() {
        return this.freqList;
    }

    public void setFreqList(ArrayList<Integer> newFreqList) {
        this.freqList = newFreqList;
    }
 

 
    //Methods
    public void createChoppedTxtList() {
    //hacher le txt en un char par elt de list (donc taille liste = taille du txt), avec des doublons
        for (int i = 0; i < txt.length(); i++) {
            char caractere = txt.charAt(i);
            String caractereString = String.valueOf(caractere);
            choppedTxtList.add(caractereString);
        }
        Collections.sort(choppedTxtList);//On trie la liste par ordre croissant
    }

    public void createTxtList() {
    //Une liste sans les doublons de choppedTxtList
        Set<String> hashTemp = new HashSet<String>(choppedTxtList);
        txtList = new ArrayList<String>(hashTemp);
    }

    public void createFreqList() {
        //comparer choppedTxtList et txtList pour avoir les fréquences (genre pour chaque str du 1er tu regarde 
        //le nb de fois que t'as le char ds le deuxieme)

        for (String element : txtList) {
            int cpt = 0;
            for (int i = 0; i < choppedTxtList.size(); i++){
                if (choppedTxtList.get(i).equals(element)){
                    cpt++;
                }
            }
            freqList.add(cpt);
        }
    }


    public void createDicoFreq() {
        for (int i = 0; i < txtList.size(); i++){
            dicoFreq.put(txtList.get(i),freqList.get(i));
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(dicoFreq.entrySet());
        Collections.sort(list, new ValueThenKeyComparator<String, Integer>());

        // créer un nouveau dictionnaire vide
        LinkedHashMap<String, Integer> newDicoFreq = new LinkedHashMap<String, Integer>();

        // parcourir la liste triée et ajouter les entrées dans l'ordre souhaité au nouveau dictionnaire
        for (Map.Entry<String, Integer> entry : list) {
            newDicoFreq.put(entry.getKey(), entry.getValue());
        }

        // remplacer l'ancien dictionnaire par le nouveau dictionnaire trié
        dicoFreq = newDicoFreq;
    }

    public String codageTexte(LinkedHashMap<String, String> dicoCodeBinaire) {
        String txtCode = "";
        for (int i = 0; i < txt.length(); i++) {
            char caractere = txt.charAt(i);
            String caractereString = String.valueOf(caractere);
            txtCode += dicoCodeBinaire.get(caractereString);
            System.out.println(dicoCodeBinaire.get(caractereString));
        }
        return txtCode;
    }
}
