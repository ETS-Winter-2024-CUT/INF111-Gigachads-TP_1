package modele.rover;

import modele.communication.TransporteurMessage;
import modele.satelliteRelai.SatelliteRelai;
import modele.communication.Message;

public class Rover extends TransporteurMessage {
    private SatelliteRelai relaiSatellite;

    public Rover(SatelliteRelai relaiSatellite) {
        this.relaiSatellite = relaiSatellite;
    }

    // Implémentation de la méthode abstraite pour Rover
    @Override
    protected void envoyerMessage(Message msg) {
        try {
            relaiSatellite.envoyerMessageVersCentrOp((msg));
            messagesEnvoyes.add(msg);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi du message");
        }
    }

    // Implémentation de la méthode abstraite pour Rover
    @Override
    protected void gestionnaireMessage(Message msg) {
        System.out.println("Rover: Message reçu - " + msg.getCompte());
    }
}
