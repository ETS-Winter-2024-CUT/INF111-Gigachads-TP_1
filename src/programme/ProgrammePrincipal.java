package programme;

import modele.centreControle.CentreControle;
import modele.communication.Message;
import modele.rover.Rover;
import modele.satelliteRelai.SatelliteRelai;
import utilitaires.Vect2D;
import structures.FileChainee;
import testsUnitaires.MessageDeTest;

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

        testVect();
        testFile();
        testCommunication(centreControle, rover, satellite);

        satellite.start();
        rover.start();
        centreControle.start();
    }

    // Méthode de test statique
    public static void testVect() {
        // Création d'une instance de Vect2D pour les tests
        Vect2D vecteur1 = new Vect2D(3, 4);

        // Test des méthodes de la classe Vect2D
        System.out.println("Test getLongueur:");
        System.out.println("Valeur attendue: 5.0");
        System.out.println("Valeur obtenue: " + vecteur1.getLongueur());

        System.out.println("Test getAngle:");
        System.out.println("Valeur attendue: 0.93 radians (environ 53.13 degrés)");
        System.out.println("Valeur obtenue: " + vecteur1.getAngle());

        // Autres tests à ajouter selon les méthodes de la classe Vect2D
    }

    public static void testFile() {
        FileChainee<Integer> file = new FileChainee<>();

        // Test ajouterElement et estVide
        System.out.println("Test ajouterElement et estVide:");
        System.out.println("File est vide: " + file.estVide()); // Doit retourner true
        file.ajouterElement(10);
        file.ajouterElement(20);
        System.out.println("File est vide: " + file.estVide()); // Doit retourner false

        // Test enleverElement
        System.out.println("\nTest enleverElement:");
        System.out.println("Élément enlevé: " + file.enleverElement()); // Doit retourner 10
        System.out.println("Élément enlevé: " + file.enleverElement()); // Doit retourner 20
        System.out.println("File est vide: " + file.estVide()); // Doit retourner true

        // Test enleverElement sur une file vide
        System.out.println("\nTest enleverElement sur une file vide:");
        try {
            file.enleverElement();
            System.out.println("Test échoué : aucune exception levée");
        } catch (IllegalStateException e) {
            System.out.println("Test réussi : exception levée car la file est vide");
        }
    }

    public static void testCommunication(CentreControle centreControle, Rover rover, SatelliteRelai satellite) {
        // Envoyez des messages entre le rover et le centre de contrôle
        for (int i = 0; i < 10; i++) {
            MessageDeTest message = new MessageDeTest(i);
            centreControle.envoyerMessage(message);
            System.out.println("Message envoyé depuis le centre de contrôle : " + message);
        }

        // Attendre un certain temps pour que les messages soient traités par le
        // satellite relai
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Afficher le nombre de messages reçus par le rover
        System.out.println("Nombre de messages reçus par le rover : " + rover.getCompte());
    }
}
