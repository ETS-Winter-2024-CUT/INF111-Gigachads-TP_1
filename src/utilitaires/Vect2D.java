package utilitaires;

/**
 * Classe Vect2D qui implémente un vecteur à deux dimensions.
 */
public class Vect2D {

    // Variables membres représentant les longueurs en x et y
    private double longueurX;
    private double longueurY;

    /**
     * Constructeur par défaut.
     */
    public Vect2D() {
        this.longueurX = 0;
        this.longueurY = 0;
    }

    /**
     * Constructeur par paramètres.
     *
     * @param x La longueur en x.
     * @param y La longueur en y.
     */
    public Vect2D(double x, double y) {
        this.longueurX = x;
        this.longueurY = y;
    }

    /**
     * Constructeur par copie.
     *
     * @param other L'autre Vect2D à copier.
     */
    public Vect2D(Vect2D other) {
        this.longueurX = other.getLongueurX();
        this.longueurY = other.getLongueurY();
    }

    /**
     * Accesseur informateur pour la longueur en x.
     *
     * @return La longueur en x.
     */
    public double getLongueurX() {
        return this.longueurX;
    }

    /**
     * Accesseur informateur pour la longueur en y.
     *
     * @return La longueur en y.
     */
    public double getLongueurY() {
        return this.longueurY;
    }

    /**
     * Accesseur informateur pour la longueur du vecteur.
     *
     * @return La longueur du vecteur.
     */
    public double getLongueur() {
        return Math.sqrt(this.longueurX * this.longueurX + this.longueurY * this.longueurY);
    }

    /**
     * Accesseur informateur pour l'angle du vecteur.
     *
     * @return L'angle du vecteur.
     */
    public double getAngle() {
        return Math.atan2(this.longueurY, this.longueurX);
    }

    /**
     * Calcule la différence entre ce vecteur et un autre.
     *
     * @param posFinal L'autre Vect2D.
     * @return Un nouveau Vect2D qui est la différence.
     */
    public Vect2D calculerDIFF(Vect2D posFinal) {
        return new Vect2D(posFinal.getLongueurX() - this.longueurX, posFinal.getLongueurY() - this.longueurY);
    }

    /**
     * Divise ce vecteur par un nombre.
     *
     * @param a Le nombre par lequel diviser.
     */
    public void diviser(double a) {
        this.longueurX /= a;
        this.longueurY /= a;
    }

    /**
     * Ajoute des coordonnées à ce vecteur.
     *
     * @param x La coordonnée x à ajouter.
     * @param y La coordonnée y à ajouter.
     */
    public void ajouter(double x, double y) {
        this.longueurX += x;
        this.longueurY += y;
    }

    /**
     * Retourne une représentation de ce vecteur sous forme de chaîne de caractères.
     *
     * @return Une représentation de ce vecteur.
     */
    @Override
    public String toString() {
        return "Vect2D [ x = " + longueurX + ", y = " + longueurY + " ]";
    }

    /**
     * Compare ce vecteur à un autre objet.
     *
     * @param vecteur L'autre objet.
     * @return Vrai si les deux objets sont égaux, faux sinon.
     */
    @Override
    public boolean equals(Object vecteur) {
        if (this == vecteur)
            return true;
        if (vecteur == null || getClass() != vecteur.getClass())
            return false;
        Vect2D vect2D = (Vect2D) vecteur;
        return Double.compare(vect2D.longueurX, longueurX) == 0 &&
                Double.compare(vect2D.longueurY, longueurY) == 0;
    }
}
