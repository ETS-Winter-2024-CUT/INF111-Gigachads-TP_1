package programme;

import modele.centreControle.CentreControle;
import modele.communication.Message;
import modele.rover.Rover;
import modele.satelliteRelai.SatelliteRelai;
import testsUnitaires.testMessage;
import utilitaires.Vect2D;
import structures.FileChainee;

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
        // System.out.println("Test Vecteurs:\n");
        // testVect();
        // System.out.println("\nTest Files:\n");
        // testFile();
        // System.out.println("\n");

        satellite.start();
        rover.start();
        centreControle.start();

        for (int i = 1; i <= 10; i++) {
            testMessage test = new testMessage(i, "Contenu : " + i);
            centreControle.envoyerMessage(test);
        }

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

}
