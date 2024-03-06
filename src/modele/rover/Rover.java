package modele.rover;

import modele.communication.TransporteurMessage;
import modele.satelliteRelai.SatelliteRelai;
import modele.communication.Message;

public class Rover extends TransporteurMessage {
    private SatelliteRelai relaiSatelitte;

    public Rover(SatelliteRelai relaiSatellite){
        this.relaiSatelitte = relaiSatellite;
    }

    // Implémentation de la méthode abstraite pour Rover
    @Override
    protected void envoyerMessage(Message msg) {
        relaiSatelitte.envoyerMessageVersCentrOp((msg));
        messageEnvoyes.add(msg);
    }

    // Implémentation de la méthode abstraite pour Rover
    @Override
    protected void gestionnaireMessage(Message msg) {
        System.out.println("Rover: Message reçu - " + msg.getNumero());
    }
}
