package java_1;


public class Arbre {
    protected int freq;
    protected String car;
    protected Arbre filsDroit;
    protected Arbre filsGauche;

    //Constructors
    public Arbre(int freq, String car) {
        this.car = car;
        this.freq = freq;
    }

    public Arbre(int freq, Arbre filsGauche, Arbre filsDroit) {
        this.freq = freq;
        this.filsGauche = filsGauche;
        this.filsDroit = filsDroit;
    }   

    //Methods
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

    public boolean estFeuille() {
        return (this.filsDroit == null && this.filsGauche == null);
    }
}