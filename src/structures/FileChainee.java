package structures;

/**
 * Une classe représentant une file chaînée générique.
 * Une file est une structure de données qui suit le principe du premier entré,
 * premier sorti (FIFO).
 *
 * @param <T> Le type des éléments stockés dans la file.
 */
public class FileChainee<T> {

    /**
     * Une classe interne représentant un nœud dans la file chaînée.
     */
    private class Noeud {
        T valeur; // La valeur stockée dans le nœud
        Noeud suivant; // Le nœud suivant dans la file

        /**
         * Constructeur pour créer un nouveau nœud avec une valeur donnée.
         *
         * @param valeur La valeur à stocker dans le nœud.
         */
        public Noeud(T valeur) {
            this.valeur = valeur;
            this.suivant = null;
        }
    }

    private Noeud debut; // Le premier nœud de la file
    private Noeud fin; // Le dernier nœud de la file

    /**
     * Constructeur pour créer une file chaînée vide.
     */
    public FileChainee() {
        this.debut = null;
        this.fin = null;
    }

    /**
     * Ajoute un élément à la fin de la file.
     *
     * @param element L'élément à ajouter à la file.
     */
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

    /**
     * Enlève et retourne l'élément en tête de la file.
     *
     * @return L'élément en tête de la file.
     * @throws IllegalStateException si la file est vide.
     */
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

    /**
     * Vérifie si la file est vide.
     *
     * @return true si la file est vide, sinon false.
     */
    public boolean estVide() {
        return debut == null;
    }
}
