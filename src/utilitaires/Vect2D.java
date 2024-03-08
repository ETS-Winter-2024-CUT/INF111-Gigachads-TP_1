package utilitaires;

import java.util.Objects;

public class Vect2D {
    private double x;
    private double y;

    // Constructeur par défaut
    public Vect2D() {
        this(0, 0);
    }

    // Constructeur par paramètres
    public Vect2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Constructeur par copie
    public Vect2D(Vect2D vect) {
        this.x = vect.x;
        this.y = vect.y;
    }

    // Accesseur informateur pour toutes variables membres
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Accesseur informateur getLongueur
    public double getLongueur() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    // Accesseur informateur getAngle
    public double getAngle() {
        return Math.atan2(y, x);
    }

    // Méthode pour calculer la différence entre deux Vect2D
    public Vect2D calculerDiff(Vect2D posFin) {
        return new Vect2D(this.x - posFin.x, this.y - posFin.y);
    }

    // Méthode pour diviser les variables membres par un facteur scalaire
    public void diviser(double a) {
        this.x /= a;
        this.y /= a;
    }

    // Méthode pour ajouter deux coordonnées à la classe
    public void ajouter(double x, double y) {
        this.x += x;
        this.y += y;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // Méthode equals
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Vect2D vect2D = (Vect2D) o;
        return Double.compare(vect2D.x, x) == 0 && Double.compare(vect2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
