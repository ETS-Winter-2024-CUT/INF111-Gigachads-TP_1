package modele.centreControle;

import modele.communication.TransporteurMessage;
import modele.satelliteRelai.SatelliteRelai;
import modele.communication.Message;

public class CentreControle extends TransporteurMessage {

    private final SatelliteRelai satelliteRelai;

    public CentreControle(SatelliteRelai satelliteRelai) {
        this.satelliteRelai = satelliteRelai;
    }

    // Méthode permettant d'envoyer un message vers le satellite relai
    public void envoyerMessage(Message msg) {
        satelliteRelai.envoyerMessageVersRover(msg); // Appelle la méthode envoyerMessageVersRover du satellite relai
                                                     // pour envoyer le message
    }

    // Méthode protégée pour gérer la réception d'un message
    protected void gestionnaireMessage(Message msg) {
        // Affiche un message indiquant la réception et le traitement du message
        System.out.println("\n");
        System.out.println("Centre de controle: Message reçu - " + msg.getCompte());
        System.out.println("Centre de controle: Traitement du message...");
        System.out.println("Centre de controle: Message traité.");
        System.out.println("\n");
    }
}
