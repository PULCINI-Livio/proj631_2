import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class LecteurFichierTexte {

    public static String lireFichier(String cheminFichier) {
        StringBuilder contenu = new StringBuilder();

        File fichier = new File(cheminFichier);

        try {
            Scanner scanner = new Scanner(fichier);

            // Lire le contenu du fichier ligne par ligne
            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                contenu.append(ligne).append("\n");
            }

            // Fermer le scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Erreur : le fichier n'a pas été trouvé.");
            e.printStackTrace();
        }

        return contenu.toString();
    }
}

