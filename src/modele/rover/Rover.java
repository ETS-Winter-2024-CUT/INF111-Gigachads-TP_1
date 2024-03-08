package modele.rover;

/**
 * Classe qui simule le Rover.
 * Est une extention de la classe abstraite TransporteurMessage
 * @Author: Maxim Dmitriev, Vianney Veremme, Leonard Marcoux
 * @Date: Mars 2024
 */

import modele.communication.TransporteurMessage;
import modele.satelliteRelai.SatelliteRelai;
import modele.communication.Message;
import modele.communication.Nack;
import testsUnitaires.testMessage;

public class Rover extends TransporteurMessage {

    private final SatelliteRelai satelliteRelai;

    /**
     * Constructeur par defaut
     * @param satelliteRelai satelite a relier a cette instance de Rover
     */
    public Rover(SatelliteRelai satelliteRelai) {
        this.satelliteRelai = satelliteRelai;
    }

    /**
     * Envoie un message au satellite Relais pour ensuite l'envoyer au centre de controle
     * @param msg Message a envoyer.
     */
    public void envoyerMessage(Message msg) {
        satelliteRelai.envoyerMessageVersCentrOp(msg); // Appelle la méthode envoyerMessageVersCentrOp du satellite
                                                       // relai pour envoyer le message
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
        System.out.println("\n--ROVER--------------------------\n| Message reçu - " + msg.getCompte()
                + "\t\t|\n| Traitement du message...\t|\n| Message traité.\t\t|\n---------------------------------\n");
        System.out.println("type de message: " + msg.getClass().getSimpleName());
    }

    /**
     * Fonction de test TODO: enlever cette fonciton.
     */
    public void testCommunicationRover() {
        testMessage msgTest = new testMessage(compteurMsg.getCompteActuel(), "Message test provenant du Rover");
        envoyerMessage(msgTest);
    }
}
