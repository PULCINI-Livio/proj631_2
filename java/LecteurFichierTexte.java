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

            // Supprimer le dernier caractère de nouvelle ligne s'il y en a un
            if (contenu.length() > 0 && contenu.charAt(contenu.length() - 1) == '\n') {
                contenu.setLength(contenu.length() - 1);
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

