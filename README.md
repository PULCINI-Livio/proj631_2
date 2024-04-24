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

Le sujet est également disponible [ici](Sujet codage Huffman.pdf)

## ASPECT TECHNIQUE
Le programme se décompose en plusieurs parties:   
    - [AlphabetFrequence.java](java\java_1\AlphabetFrequence.java) qui contient principalement les fonctions 
    de traitement des chaines de caractères et des fréquences, le codage binaire avec les sauvegardes des fichiers.  
    -[Arbre.java](java\java_1\Arbre.java) est la classe qui contient la structure de l'arbre de Huffman.  
    -[ConstructionArbre.java](java\java_1\ConstructionArbre.java) est la classe qui permet de générer l'arbre de Huffman et de créer le code binaire de chaque caractère.  

    
Liste des modules/bibliothèques utilisées:
```java
import java.util.*;
import java.io.*;
```

## INSTALLATION



## MODE D'EMPLOI


## CONCLUSION
