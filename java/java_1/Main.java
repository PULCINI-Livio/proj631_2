package java_1;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String cheminFichier = "donnees\\alice.txt";

        //On récupère le contenu d'un fichier .txt pour le mettre dans un String 
        String txt = LecteurFichierTexte.lireFichier(cheminFichier);
        System.out.println(txt);

        AlphabetFrequence alpha = new AlphabetFrequence(txt, cheminFichier);
        alpha.createChoppedTxtList();
        //System.out.println(alpha.choppedTxtList);

        alpha.createTxtList();
        //System.out.println(alpha.txtList);

        alpha.createFreqList();
        //System.out.println(alpha.freqList);

        alpha.createDicoFreq();
        System.out.println(alpha.dicoFreq);
   
        ConstructionArbre huffman = new ConstructionArbre(alpha.dicoFreq);
        //System.out.println(huffman.listeArbres);
        huffman.construireArbre();
        System.out.println(huffman.arbreHuffman);
        huffman.arbreHuffman.afficherArbreASCII();  

        String code = "";
        huffman.creerCodeBinaire(huffman.arbreHuffman, code);
        System.out.println(huffman.dicoCodeBinaire);

        alpha.codageTexte(huffman.dicoCodeBinaire);
        System.out.println(alpha.txtCode);
        
        
        alpha.creerFichierFreq();
        alpha.sur8Bits();
        alpha.creerFichierCompression();
        alpha.tauxCompression();
        alpha.nbMoyenBitsParCar();
    }   
}
