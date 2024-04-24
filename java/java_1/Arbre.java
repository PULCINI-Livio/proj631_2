package java_1;

public class Arbre {
    protected int freq;
    protected String car;
    protected Arbre filsDroit;
    protected Arbre filsGauche;

    //Constructors

    /** 
     * Constructeur pour les feuilles
     */
    public Arbre(int freq, String car) {
        this.car = car;
        this.freq = freq;
    }

    /** 
     * Constructeur pour les noeuds qui ne sont pas des feuilles
     */
    public Arbre(int freq, Arbre filsGauche, Arbre filsDroit) {
        this.freq = freq;
        this.filsGauche = filsGauche;
        this.filsDroit = filsDroit;
    }   

    //Methods

    
    /** 
     * Permet un affichage sommaire de l'arbre
     */
    public void afficherArbre() {
        System.out.println("Fréquence : " + freq + ", Caractère : " + car);
        if (filsGauche != null) {
            System.out.println("Fils gauche : ");
            filsGauche.afficherArbre();
        }
        if (filsDroit != null) {
            System.out.println("Fils droit : ");
            filsDroit.afficherArbre();
        }
    }

    public void afficherArbreASCII() {
        afficherArbreASCII("", "", false);
    }

    
    /** 
     * Permet un affichage plus graphique de l'arbre
     * (en collaboration avec Le Chat Mistral)
     */
    private void afficherArbreASCII(String prefixe, String suffixe, boolean estFilsDroit) {
        System.out.println(prefixe + (estFilsDroit ? "├── " : "└── ") + car + " (" + freq + ")");
        String nouveauPrefixe = prefixe + (estFilsDroit ? "│   " : "    ");
        if (filsGauche != null) {
            filsGauche.afficherArbreASCII(nouveauPrefixe, "", true);
        }
        if (filsDroit != null) {
            filsDroit.afficherArbreASCII(nouveauPrefixe, "", false);
        }
    }

    
    /** 
     * @return boolean
     */
    public boolean estFeuille() {
        return (this.filsDroit == null && this.filsGauche == null);
    }
}
