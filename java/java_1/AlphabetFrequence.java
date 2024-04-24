package java_1;

import java.util.*;
import java.io.*;

public class AlphabetFrequence {
    protected static String txt;                               // ~ 'bonjour'
    protected static ArrayList<String> choppedTxtList;         // ~ ['b','o','n','j','o','u','r']
    protected static ArrayList<String> txtList;                // ~ ['b','o','n','j','u','r']
    protected static ArrayList<Integer> freqList;              // ~ [ 1 , 2 , 1 , 1 , 1 , 1 ]
    protected static LinkedHashMap<String, Integer> dicoFreq;  // ~ {b=1,j=1,n=1,r=1,u=1,o=2,}
    protected static String cheminFichier;
    protected static String txtCode;
    protected static String txtCodeOpti8Bits;

 
    //Methods
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

    public static void createTxtList() {
    //Une liste sans les doublons de choppedTxtList
        Set<String> hashTemp = new HashSet<String>(choppedTxtList);
        txtList = new ArrayList<String>(hashTemp);
    }

    public static void createFreqList() {
        //comparer choppedTxtList et txtList pour avoir les fréquences (genre pour chaque str du 1er tu regarde 
        //le nb de fois que t'as le char ds le deuxieme)
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

    public static void codageTexte(LinkedHashMap<String, String> dicoCodeBinaire) {
        txtCode = "";
        for (int i = 0; i < txt.length(); i++) {
            char caractere = txt.charAt(i);
            String caractereString = String.valueOf(caractere);
            txtCode += dicoCodeBinaire.get(caractereString);
        }
    }

    public static void creerFichierFreq() {
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


    

    public static String sur8Bits() {
        txtCodeOpti8Bits = txtCode;
        int reste = txtCodeOpti8Bits.length() % 8;
        
        for (int index = 0; index < 8-reste; index++) {
            txtCodeOpti8Bits += "0";
        }
        return txtCodeOpti8Bits;
    }
    
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

    public static void tauxCompression() {
        File initFile = new File(cheminFichier);
        File compressFile = new File(cheminFichier.substring(0, cheminFichier.length() - 4)+"_comp.bin");

        if (initFile.exists() && compressFile.exists()) {
            float initSizeInBytes = initFile.length();
            float compressSizeInBytes = compressFile.length();
            float tauxComp = 1 - (compressSizeInBytes/initSizeInBytes);

            //System.out.println(initSizeInBytes);
            //System.out.println(compressSizeInBytes);
            System.out.println("Taux de compression : " + tauxComp + "%");
        } else {
            System.out.println("Fichier manquant");
        }
    }

    public static void nbMoyenBitsParCar() {
        float avgBitsPerChar = (float) txtCode.length() / (float) txt.length();
        System.out.println("Nombre moyen de bits de stockage d’un caractère du texte compressé: " + avgBitsPerChar);
    }
}
