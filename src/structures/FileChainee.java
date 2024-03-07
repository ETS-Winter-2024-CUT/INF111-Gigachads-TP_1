package structures;

public class FileChainee<T> {

    private class Noeud {
        T element;
        Noeud suivant;

        public Noeud(T element) {
            this.element = element;
            this.suivant = null;
        }
    }

    private Noeud debut; // Référence vers le premier nœud de la file
    private Noeud fin; // Référence vers le dernier nœud de la file

    /**
         * Constructeur de la file.
         */
        public File() {
            this.debut = null;
            this.fin = null;
        }

    /**
     * Méthode pour ajouter un élément à la fin de la file.
     *
     * @param element L'élément à ajouter.
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
     * Méthode pour enlever et retourner l'élément en tête de la file.
     *
     * @return L'élément enlevé.
     * @throws IllegalStateException Si la file est vide.
     */
    public T enleverElement() {
        if (estVide()) {
            throw new IllegalStateException("La file est vide");
        }

        T elementEnleve = debut.element;
        debut = debut.suivant;
        if (debut == null) {
            fin = null;
        }
        return elementEnleve;
    }

    /**
     * Méthode pour vérifier si la file est vide.
     *
     * @return true si la file est vide, sinon false.
     */
    public boolean estVide() {
        return debut == null;
    }
}
