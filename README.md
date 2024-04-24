#PROJ631_2- Compression de données par codage de Huffman

## INTRO
Le codage de Huffman, du nom de son concepteur, est une méthode statistique de compression de
données. Son principe est de remplacer un caractère (ou symbole) par une suite de bits de longueur
variable. L'idée sous-jacente est de coder ce qui est fréquent sur peu de bits et au contraire ce qui est
rare sur des séquences de bits plus longues. Il permet une compression sans perte, c’est-à-dire qu’une
suite de bits strictement identique à l’originale est restituée par décompression. Il nécessite cependant
que soit connues (ou estimées) les fréquences d’apparition des différents symboles à coder. Il existe
ainsi plusieurs variantes de l’algorithme de Huffman (statique, semi-adaptatif ou adaptatif)
aujourd’hui utilisées dans des algorithmes de compression de fichiers tels que gzip.

Le sujet complet est également disponible [ici](Sujet_codage_Huffman.pdf)

## ASPECT TECHNIQUE
Le programme se décompose en plusieurs parties:   
    - [AlphabetFrequence.java](java\java_1\AlphabetFrequence.java) qui contient principalement les fonctions 
    de traitement des chaines de caractères et des fréquences, le codage binaire avec les sauvegardes des fichiers.  
    -[Arbre.java](java\java_1\Arbre.java) est la classe qui contient la structure de l'arbre de Huffman.  
    -[ConstructionArbre.java](java\java_1\ConstructionArbre.java) est la classe qui permet de générer l'arbre de Huffman et de créer le code binaire de chaque caractère.  
    -[LecteurFichierTexte.java](java\java_1\LecteurFichierTexte.java) est la classe qui va nous permettre de convertir un fichier .txt en String.  
    -[Main.java](java\java_1\Main.java) est la classe dans laquelle on va pouvoir insérer le nom de notre fichier que l'on veut compresser.  
    -[ValueThenKeyComparator.java](java\java_1\ValueThenKeyComparator.java) est la classe qui contient le comparateur utilisé pour trier par valeur puis par clé dans une liste.  
    
Liste des modules/bibliothèques utilisées:
```java
import java.util.*;
import java.io.*;
```

## INSTALLATION
Vous devez avoir java installé sur votre machine, utilisez un logiciel comme Eclipse ou VSCODE.  
Téléchargez le contenu du dossier [java](java).


## MODE D'EMPLOI
Dans le fichier ```Main.java```, remplacer à la ligne ```String cheminFichier = "donnees\\extraitalice.txt";``` ```"donnees\\extraitalice.txt"``` par le chemin relatif du fichier que vous voulez compresser (de préférence mettez le dans [donnees](donnees)), les deux fichiers générés se trouverons alors dans [donnees](donnees).

## CONCLUSION
Un projet très sympathique que j'ai eut l'occasion auparavant de le coder en Ocaml (ce qui n'était pas une partie de plaisir pour les connaisseurs), mais tester avec java a donné un petit défi car je trouve que l'utilisation de classe n'est pas vraiment adaptée pour ce genre de programme, avec parfois des fonctions qui paraissaient simple en python, mais qui se révèlent être des petits casse-têtes en java. Une raison de plus de préférer python à java.