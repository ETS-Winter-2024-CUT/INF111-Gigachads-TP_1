package modele.rover;

import modele.communication.TransporteurMessage;
import modele.satelliteRelai.SatelliteRelai;
import modele.communication.Message;

public class Rover extends TransporteurMessage {

    private final SatelliteRelai satelliteRelai;

    // test
    private int compte; // Variable pour stocker le nombre de messages reçus

    public Rover(SatelliteRelai satelliteRelai) {
        this.satelliteRelai = satelliteRelai;

        // testtttttttttt
        compte = 0; // Initialise le nombre de messages reçus à zéro
    }

    // Méthode pour augmenter le compteur de messages reçus
    public void incrementerCompte() {
        compte++;
    }

    // Méthode pour obtenir le nombre de messages reçus
    public int getCompte() {
        return compte;
    }
    // test

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
    }
}
