package modele.centreControle;

/**
 * Classe qui simule le centre de controle.
 * Est une extention de la classe abstraite TransporteurMessage
 * @Author: Maxim Dmitriev, Vianney Veremme, Leonard Marcoux
 * @Date: Mars 2024
 */

import modele.communication.TransporteurMessage;
import modele.satelliteRelai.SatelliteRelai;
import testsUnitaires.testMessage;
import modele.communication.Message;
import modele.communication.Nack;

public class CentreControle extends TransporteurMessage {

    private final SatelliteRelai satelliteRelai;

    /**
     * Constructeur par default
     * @param satelliteRelai satellite relai a relier a cet instance
     */
    public CentreControle(SatelliteRelai satelliteRelai) {
        this.satelliteRelai = satelliteRelai;
    }

    /**
     * Envoie un message au satellite pour ensuite le transferer au Rover
     * @param msg Message a envoyer
     */
    public void envoyerMessage(Message msg) {
        satelliteRelai.envoyerMessageVersRover(msg); // Appelle la méthode envoyerMessageVersRover du satellite relai
                                                     // pour envoyer le message
        if (!(msg instanceof Nack)) {
            messagesEnvoyes.add(msg);
        }
    }

    /**
     * Affiche le statut de la reception d'un message dans la console.
     * @param msg Message a afficher
     */
    protected void gestionnaireMessage(Message msg) {
        // Affiche un message indiquant la réception et le traitement du message
        System.out.println("\n--CENTRE DE CONTROLE-------------\n| Message reçu - " + msg.getCompte()
                + "\t\t|\n| Traitement du message...\t|\n| Message traité.\t\t|\n---------------------------------\n");
        System.out.println("type de message: " + msg.getClass().getSimpleName());
    }

    /**
     * Fonction de test TODO: enlever cette fonciton.
     */
    public void testCommunicationCC() {
        testMessage msgTest = new testMessage(compteurMsg.getCompteActuel(),
                "Message test provenant du Centre de Controle");
        envoyerMessage(msgTest);
    }
}
