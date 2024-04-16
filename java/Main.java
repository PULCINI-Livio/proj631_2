public class Main {
    public static void main(String[] args) {
        //On récupère le contenu d'un fichier .txt pour le mettre dans un String 
        String txt = LecteurFichierTexte.lireFichier("donnees\\textesimple.txt");
        //System.out.println(txt);

        AlphabetFrequence alpha = new AlphabetFrequence(txt);
        alpha.createChoppedTxtList();
        //System.out.println(alpha.choppedTxtList);

        alpha.createTxtList();
        //System.out.println(alpha.txtList);

        alpha.createFreqList();
        //System.out.println(alpha.freqList);

        alpha.createDicoFreq();
        System.out.println(alpha.dicoFreq);
   
        ConstructionArbre huffman = new ConstructionArbre(alpha.dicoFreq);
        System.out.println(huffman.listeFeuilles);
    }
}
