package modele.centreControle;

import modele.communication.TransporteurMessage;
import modele.communication.Message;
import modele.rover.Status;

public class CentreControle extends TransporteurMessage {
    // Implémentation de la méthode abstraite pour CentreControle
    @Override
    protected void envoyerMessage(Message msg) {
        // too complete
    }

    // Implémentation de la méthode abstraite pour CentreControle
    @Override
    protected void gestionnaireMessage(Message msg) {
        // too complete
    }

    private void gererStatus(Status status) {
        System.out.printf("Status reçu - Position du Rover : x: %d y: %d\n",
                status.getPosition().getLongueurX(), status.getPosition().getLongueurY());
    }

    public void gestionnaireCommande(Object message) {
        if (message instanceof Status) {
            gererStatus((Status) message);
        } else {
            // A voir
        }
    }
}
