package modele.rover;

import modele.communication.TransporteurMessage;
import utilitaires.Vect2D;
import modele.communication.Message;

public class Rover extends TransporteurMessage {
    private static final double VITESSE_MPARS = 0.5;
    private Vect2D position;

    // Implémentation de la méthode abstraite pour Rover
    @Override
    protected void envoyerMessage(Message msg) {
        // too complete
    }

    // Implémentation de la méthode abstraite pour Rover
    @Override
    protected void gestionnaireMessage(Message msg) {
        // too complete
    }

    public Rover(Vect2D position) {
        this.position = position;
    }

    private void deplacerRover(Vect2D destination) {
        Vect2D deplacement = destination.calculerDIFF(position);
        double distance = deplacement.getLongueur();
        double tempsRequis = distance / VITESSE_MPARS;
        double angle = deplacement.getAngle();

        System.out.println("Position initiale du Rover : " + position);

        for (int i = 0; i < (int) tempsRequis; i++) {
            position.ajouter(Math.cos(angle) * VITESSE_MPARS, Math.sin(angle) * VITESSE_MPARS);

            System.out.println("Position du Rover : " + position);
        }

        double fraction = tempsRequis % 1.0;

        if (fraction > 0) {
            position.ajouter(Math.cos(angle) * VITESSE_MPARS * fraction, Math.sin(angle) * VITESSE_MPARS * fraction);

            System.out.println("Position finale du Rover : " + position);
        }
    }

    public void gestionnaireCommande(eCommande commande, Vect2D destination) {
        switch (commande) {
            case NULLE:
                break;
            case DEPLACER_ROVER:
                deplacerRover(destination);
                break;
            case PRENDRE_PHOTOS:
                break;
            default:
                break;
        }
    }

    public Vect2D getPosition() {
        return position;
    }
}
