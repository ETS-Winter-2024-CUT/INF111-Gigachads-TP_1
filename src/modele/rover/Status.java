package modele.rover;

import utilitaires.Vect2D;

public class Status {
    private Vect2D position;

    public Status(Vect2D position) {
        this.position = position;
    }

    public Vect2D getPosition() {
        return position;
    }

    public void setPosition(Vect2D position) {
        this.position = position;
    }
}
