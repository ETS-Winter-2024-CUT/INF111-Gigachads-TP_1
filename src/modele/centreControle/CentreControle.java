package modele.centreControle;

import modele.communication.TransporteurMessage;

import java.util.List;

import modele.communication.Message;
import modele.satelliteRelai.SatelliteRelai;

public class CentreControle extends TransporteurMessage {
    private SatelliteRelai relaisSatellite;

    public CentreControle(SatelliteRelai relaiSatelitte) {
        this.relaisSatellite = relaiSatelitte;
        System.out.println("test centre controle");
    }

    // Implémentation de la méthode abstraite pour CentreControle
    @Override
    public void envoyerMessage(Message msg) {
        relaisSatellite.envoyerMessageVersRover(msg);
        messagesEnvoyes.add(msg);
    }

    // Implémentation de la méthode abstraite pour CentreControle
    @Override
    public void gestionnaireMessage(Message msg) {
        System.out.println("Centre de controle: Message reçu - " + msg.getCompte());
        System.out.println("Centre de controle: Traitement du message...");
        System.out.println("Centre de controle: Message traité.");
    }
}
