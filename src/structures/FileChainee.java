package structures;

public class FileChainee<T> {
    private class Noeud {
        T valeur;
        Noeud suivant;

        public Noeud(T valeur) {
            this.valeur = valeur;
            this.suivant = null;
        }
    }

    private Noeud debut;
    private Noeud fin;

    public FileChainee() {
        this.debut = null;
        this.fin = null;
    }

    public void ajouterElement(T element) {
        Noeud nouveauNoeud = new Noeud(element);
        if (estVide()) {
            debut = nouveauNoeud;
            fin = nouveauNoeud;
        } else {
            fin.suivant = nouveauNoeud;
            fin = nouveauNoeud;
        }
    }

    public T enleverElement() {
        if (estVide()) {
            throw new IllegalStateException("La file est vide");
        }
        T valeurEnlevee = debut.valeur;
        debut = debut.suivant;
        if (debut == null) {
            fin = null;
        }
        return valeurEnlevee;
    }

    public boolean estVide() {
        return debut == null;
    }
}
