package modele.centreControle;

import modele.communication.TransporteurMessage;
import modele.communication.Message;
import modele.satelliteRelai.SatelliteRelai;

public class CentreControle extends TransporteurMessage {
    private SatelliteRelai relaisSatellite;

    public CentreControle(SatelliteRelai relaiSatelitte) {
        this.relaisSatellite = relaiSatelitte;
    }

    // Implémentation de la méthode abstraite pour CentreControle
    @Override
    protected void envoyerMessage(Message msg) {
        relaisSatellite.envoyerMessageVersRover(msg);
        messagesEnvoyes.add(msg);
    }

    // Implémentation de la méthode abstraite pour CentreControle
    @Override
    protected void gestionnaireMessage(Message msg) {
        System.out.println("Centre de controle: Message reçu - " + msg.getNumero());
    }
}
