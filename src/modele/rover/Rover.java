package modele.rover;

import modele.communication.TransporteurMessage;
import modele.satelliteRelai.SatelliteRelai;
import modele.communication.Message;

import java.util.List;

import modele.communication.Message;

public class Rover extends TransporteurMessage {
    private SatelliteRelai relaiSatellite;

    public Rover(SatelliteRelai relaiSatellite) {
        this.relaiSatellite = relaiSatellite;
        System.out.println("test rover");
    }

    // Implémentation de la méthode abstraite pour Rover
    @Override
    public void envoyerMessage(Message msg) {
        try {
            relaiSatellite.envoyerMessageVersCentrOp((msg));
            messagesEnvoyes.add(msg);
        } catch (Exception e) {
            System.err.println("Rover: Erreur lors de l'envoi du message");
        }
    }

    // Implémentation de la méthode abstraite pour Rover
    @Override
    public void gestionnaireMessage(Message msg) {
        System.out.println("Rover: Message reçu - " + msg.getCompte());
        System.out.println("Rover: Traitement du message...");
        System.out.println("Rover: Message traité.");
    }
}
