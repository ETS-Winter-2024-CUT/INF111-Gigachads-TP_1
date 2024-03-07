package programme;

import modele.satelliteRelai.SatelliteRelai;
import modele.centreControle.CentreControle;
import modele.rover.Rover;
import testsUnitaires.testMessage;

public class ProgrammePrincipal {

    /**
     * Programme principale, instancie les éléments de la simulation,
     * les lie entre eux, puis lance la séquence de test.
     *
     * @param args, pas utilisé
     */
    public static void main(String[] args) {
        SatelliteRelai satellite = new SatelliteRelai();
        CentreControle centreControle = new CentreControle(satellite);
        Rover rover = new Rover(satellite);

        satellite.lierCentrOp(centreControle);
        satellite.lierRover(rover);

        satellite.start();
        rover.start();
        centreControle.start();

        // Test de communication avec testMessage
        // Envoi d'un message du centre de contrôle au rover
        testMessage message1 = new testMessage(1, "Message du centre au rover");
        centreControle.envoyerMessage(message1);
        rover.envoyerMessage(message1); // Réponse du rover

        // Envoi d'un message du rover au centre de contrôle
        testMessage message2 = new testMessage(2, "Message du rover au centre");
        rover.envoyerMessage(message2);
        centreControle.envoyerMessage(message2); // Réponse du centre de contrôle

        // Attente pour permettre le traitement des messages
        try {
            Thread.sleep(5000); // Attendre 5 secondes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
