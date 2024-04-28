package java_1;

import java.util.*;
import java.io.*;

public class AlphabetFrequence {
    // Attributes
    protected static String txt;                               // ~ 'bonjour'
    protected static ArrayList<String> choppedTxtList;         // ~ ['b','o','n','j','o','u','r']
    protected static ArrayList<String> txtList;                // ~ ['b','o','n','j','u','r']
    protected static ArrayList<Integer> freqList;              // ~ [ 1 , 2 , 1 , 1 , 1 , 1 ]
    protected static LinkedHashMap<String, Integer> dicoFreq;  // ~ {b=1,j=1,n=1,r=1,u=1,o=2,}
    protected static String cheminFichier;                     // ~ donnees\\extraitalice.txt
    protected static String txtCode;                           // ~ 011010
    protected static String txtCodeOpti8Bits;                  // ~ 01101000

 
    //Methods

    /** 
     * Appelle global de méthode pour au final creer le dictionnaire des fréquences pour chaque caractère de txt
     */
    public static void mainDicoFreq(String unTxt) {
        AlphabetFrequence.createChoppedTxtList(unTxt);
        AlphabetFrequence.createTxtList();
        AlphabetFrequence.createFreqList();
        AlphabetFrequence.createDicoFreq();

    }

    /** 
     * Convertit une chaine de caractères en une liste où chaque élément est un caractère:
     * <p>
     * ex: 'bonjour' -> ['b','o','n','j','o','u','r']
     * 
     * @param unTxt
     *  le texte à compresser
     */
    public static void createChoppedTxtList(String unTxt) {
    txt = unTxt;
    choppedTxtList = new ArrayList<String>();
    //hacher le txt en un char par elt de list (donc taille liste = taille du txt), avec des doublons
        for (int i = 0; i < txt.length(); i++) {
            char caractere = txt.charAt(i);
            String caractereString = String.valueOf(caractere);
            choppedTxtList.add(caractereString);
        }
        Collections.sort(choppedTxtList);//On trie la liste par ordre croissant
    }

    /** 
     * Retire les doublons d'une liste de caractères: 
     * <p>
     * ex:  ['b','o','n','j','o','u','r'] -> ['b','o','n','j','u','r']
     */
    public static void createTxtList() {
        Set<String> hashTemp = new HashSet<String>(choppedTxtList);
        txtList = new ArrayList<String>(hashTemp);
    }

    /** 
     * Créé une liste de fréquence indexée sur une liste de caractères 
     */
    public static void createFreqList() {
        //comparer choppedTxtList et txtList pour avoir les fréquences (pour chaque element de choppedTxtList on regarde 
        //le nb de fois que l'on a element dans txtList)
        freqList = new ArrayList<Integer>();
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

    /** 
     * Créé un dictionnaire String/Integer qui correspond à la fréquence de chaque caractères de txt  
     */
    public static void createDicoFreq() {
        dicoFreq = new LinkedHashMap<String, Integer>();
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

    
    /** 
     * En se servant de dicoCodeBinaire qui contient chaque caractère et son code binaire,
     * génère un String qui est la concaténation de chaque caractère de txt remplacé par son code binaire 
     * 
     * @param dicoCodeBinaire
     *      contient chaque caractère et son code binaire
     */
    public static void codageTexte(LinkedHashMap<String, String> dicoCodeBinaire) {
        txtCode = "";
        for (int i = 0; i < txt.length(); i++) {
            char caractere = txt.charAt(i);
            String caractereString = String.valueOf(caractere);
            txtCode += dicoCodeBinaire.get(caractereString);
        }
    }

    /** 
     * Génère un fichier <nom_texte>_freq.txt qui contient la taille de l'alphabet
     * ainsi que l'occurence de chaque caractère
     */
    public static void creerFichierFreq() {
        // Nommage du fichier à partir de son chemin
        cheminFichier = LecteurFichierTexte.cheminFichier;
        int tailleAlphabet = txtList.size();
        String nomFichier = cheminFichier.substring(0, cheminFichier.length() - 4)+"_freq.txt";
        
        try (PrintWriter writer = new PrintWriter(new File(nomFichier))) {
            writer.println(tailleAlphabet);
            for (Map.Entry<String, Integer> entry : dicoFreq.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                writer.println(key + " " + value);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Erreur : le fichier n'a pas été trouvé.");
            e.printStackTrace();
        }
    }

    /** 
     * Convertit txtCode qui est un String de 0 et de 1 en sa forme compatible à la sauvegarde de fichier binaire
     * c'est à dire en concaténant des 0 à la fin pour que (txtCodeOpti8Bits.length() % 8 == 0)
     */
    public static void sur8Bits() {
        txtCodeOpti8Bits = txtCode;
        int reste = txtCodeOpti8Bits.length() % 8;
        for (int index = 0; index < 8-reste; index++) {
            txtCodeOpti8Bits += "0";
        }
    }
    
    /** 
     * Génère un fichier <nom_texte>_comp.bin qui est le fichier compressé binaire
     */
    public static void creerFichierCompression() throws FileNotFoundException, IOException {
        String nomFichier = cheminFichier.substring(0, cheminFichier.length() - 4)+"_comp.bin";
        
        try {
            // Convertir le string en tableau de bytes
            byte[] bytes = new byte[txtCodeOpti8Bits.length() / 8];
            for (int i = 0; i < bytes.length; i++) {
                String byteString = txtCodeOpti8Bits.substring(i * 8, (i + 1) * 8);
                bytes[i] = (byte) Integer.parseInt(byteString, 2);
            }

            // Écrire les données dans le fichier binaire
            FileOutputStream outputStream = new FileOutputStream(nomFichier);
            outputStream.write(bytes);
            outputStream.close();

            System.out.println("Les données ont été écrites dans le fichier " + nomFichier);
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite lors de l'écriture dans le fichier.");
            e.printStackTrace();
        }
        
    }

    /** 
     * Calcul le taux de compression en connaissant les tailles des fichiers initial et compressé
     */
    public static void tauxCompression() {
        File initFile = new File(cheminFichier);
        File compressFile = new File(cheminFichier.substring(0, cheminFichier.length() - 4)+"_comp.bin");

        if (initFile.exists() && compressFile.exists()) {
            float initSizeInBytes = initFile.length();
            float compressSizeInBytes = compressFile.length();
            float tauxComp = 1 - (compressSizeInBytes/initSizeInBytes);
            System.out.println("Taux de compression : " + tauxComp + "%");
        } else {
            System.out.println("Fichier manquant");
        }
    }

    /**
     * Calcul le nombre moyen de bits de stockage d'un caractère du texte compressé:
     */
    public static void nbMoyenBitsParCar() {
        float avgBitsPerChar = (float) txtCode.length() / (float) txt.length();
        System.out.println("Nombre moyen de bits de stockage d'un caractère du texte compressé: " + avgBitsPerChar);
    }
}
