package modele.centreControle;

import modele.communication.TransporteurMessage;
import modele.satelliteRelai.SatelliteRelai;
import testsUnitaires.testMessage;
import modele.communication.Message;
import modele.communication.Nack;

public class CentreControle extends TransporteurMessage {

    private final SatelliteRelai satelliteRelai;

    public CentreControle(SatelliteRelai satelliteRelai) {
        this.satelliteRelai = satelliteRelai;
    }

    // Méthode permettant d'envoyer un message vers le satellite relai
    public void envoyerMessage(Message msg) {
        satelliteRelai.envoyerMessageVersRover(msg); // Appelle la méthode envoyerMessageVersRover du satellite relai
                                                     // pour envoyer le message
        if (!(msg instanceof Nack)) {
            messagesEnvoyes.add(msg);
        }
    }

    // Méthode protégée pour gérer la réception d'un message
    protected void gestionnaireMessage(Message msg) {
        // Affiche un message indiquant la réception et le traitement du message
        System.out.println("\n--CENTRE DE CONTROLE-------------\n| Message reçu - " + msg.getCompte()
                + "\t\t|\n| Traitement du message...\t|\n| Message traité.\t\t|\n---------------------------------\n");
        System.out.println("type de message: " + msg.getClass().getSimpleName());
    }

    public void testCommunicationCC() {
        testMessage msgTest = new testMessage(compteurMsg.getCompteActuel(),
                "Message test provenant du Centre de Controle");
        envoyerMessage(msgTest);
    }
}
