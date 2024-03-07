package modele.rover;

import modele.communication.TransporteurMessage;
import modele.satelliteRelai.SatelliteRelai;
import modele.communication.Message;

public class Rover extends TransporteurMessage {

    private final SatelliteRelai satelliteRelai;

    public Rover(SatelliteRelai satelliteRelai) {
        this.satelliteRelai = satelliteRelai;
    }

    // Méthode permettant d'envoyer un message vers le satellite relai
    public void envoyerMessage(Message msg) {
        satelliteRelai.envoyerMessageVersCentrOp(msg); // Appelle la méthode envoyerMessageVersCentrOp du satellite
                                                       // relai pour envoyer le message
    }

    // Méthodepour gérer la réception d'un message
    protected void gestionnaireMessage(Message msg) {
        // Affiche un message indiquant la réception et le traitement du message
        System.out.println("Rover: Message reçu - " + msg.getCompte());
        System.out.println("Rover: Traitement du message...");
        System.out.println("Rover: Message traité.");
        System.out.println("\n");
    }
}
