package programme;

import java.io.IOException;

import modele.satelliteRelai.SatelliteRelai;
import modele.centreControle.CentreControle;
import modele.rover.Rover;

public class ProgrammePrincipal {

    /**
     * Programme principale, instancie les éléments de la simulation,
     * les lie entre eux, puis lance la séquence de test.
     *
     * @param args, pas utilisé
     */
    public static void main(String[] args) {
        SatelliteRelai satellite = new SatelliteRelai();
        satellite.start();

        CentreControle centreControle = new CentreControle(satellite);
        Rover rover = new Rover(satellite);

        satellite.lierCentrOp(centreControle);
        satellite.lierRover(rover);
    }
}
