package java_1;

import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String cheminFichier = "donnees\\extraitalice.txt";

        //On récupère le contenu d'un fichier .txt pour le mettre dans un String 
        String txt = LecteurFichierTexte.lireFichier(cheminFichier);
        //System.out.println(txt);

        AlphabetFrequence.mainDicoFreq(txt);

        //System.out.println(AlphabetFrequence.choppedTxtList);
        //System.out.println(AlphabetFrequence.txtList);
        //System.out.println(AlphabetFrequence.freqList);
        //System.out.println(AlphabetFrequence.dicoFreq);
   
        ConstructionArbre huffman = new ConstructionArbre(AlphabetFrequence.dicoFreq);
        //System.out.println(huffman.listeArbres);
        huffman.construireArbre();
        //System.out.println(huffman.arbreHuffman);
        huffman.arbreHuffman.afficherArbreASCII();  
        //huffman.arbreHuffman.afficherArbre();

        String code = "";
        huffman.creerCodeBinaire(huffman.arbreHuffman, code);
        //System.out.println(huffman.dicoCodeBinaire);

        AlphabetFrequence.codageTexte(huffman.dicoCodeBinaire);
        //System.out.println(AlphabetFrequence.txtCode);
        
        AlphabetFrequence.creerFichierFreq();
        AlphabetFrequence.sur8Bits();
        AlphabetFrequence.creerFichierCompression();
        AlphabetFrequence.tauxCompression();
        AlphabetFrequence.nbMoyenBitsParCar();
    }   
}
