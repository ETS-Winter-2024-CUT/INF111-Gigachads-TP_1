package modele.satelliteRelai;

/**
 * Classe simulant le satellite relai
 *
 * Le satellite ne se contente que de transferer les messages du Rover vers le centre de contrôle
 * et vice-versa.
 *
 * Le satellite exécute des cycles à intervale de TEMPS_CYCLE_MS. Période à
 * laquelle tous les messages en attente sont transmis. Ceci est implémenté à
 * l'aide d'une tâche (Thread).
 *
 * Le relai satellite simule également les interférence dans l'envoi des messages.
 *
 * Services offerts:
 *  - lierCentrOp
 *  - lierRover
 *  - envoyerMessageVersCentrOp
 *  - envoyerMessageVersRover
 *
 * @author Frederic Simard, ETS
 * @version Hiver, 2024
 */

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import modele.centreControle.CentreControle;
import modele.communication.Message;
import modele.rover.Rover;
import structures.FileChainee;

public class SatelliteRelai extends Thread {

    /**
     * Declaration des variables
     */
    static final int TEMPS_CYCLE_MS = 500;
    static final double PROBABILITE_PERTE_MESSAGE = 0.15;

    ReentrantLock lock = new ReentrantLock();

    private final FileChainee<Message> fileVersCentrOp;
    private final FileChainee<Message> fileVersRover;

    private Random rand = new Random();

    private CentreControle centreControle;
    private Rover rover;

    /**
     * Methode pour lier un centre de controle au satellite
     * @param centreControle centre de controle a lier a ce satellite
     */
    public void lierCentrOp(CentreControle centreControle) {
        this.centreControle = centreControle;
    }
    /**
     * Methode pour lier un rover au satellite
     * @param rover rover a lier a ce satellite
     */
    public void lierRover(Rover rover) {
        this.rover = rover;
    }

    /**
     * Constructeur par defaut
     */
    public SatelliteRelai() {
        fileVersCentrOp = new FileChainee<>();
        fileVersRover = new FileChainee<>();
    }

    /**
     * Méthode permettant d'envoyer un message vers le centre d'opération
     *
     * @param msg, message à envoyer
     */
    public void envoyerMessageVersCentrOp(Message msg) {
        lock.lock();

        try {
            if (rand.nextDouble() > PROBABILITE_PERTE_MESSAGE) {
                fileVersCentrOp.ajouterElement(msg);
            } else {
                System.out.println("SatelliteRelai: Message vers CentreControle perdu. Message #" + msg.getCompte());
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Méthode permettant d'envoyer un message vers le rover
     *
     * @param msg, message à envoyer
     */
    public void envoyerMessageVersRover(Message msg) {
        lock.lock();

        try {
            if (rand.nextDouble() > PROBABILITE_PERTE_MESSAGE) {
                fileVersRover.ajouterElement(msg);
            } else {
                System.out.println("SatelliteRelai: Message vers Rover perdu. Message #" + msg.getCompte());
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("\nSatelliteRelai: Traitement des messages...\n");

            //Envoyer les messages dans la file en direction du centre de commandes
            while (!fileVersCentrOp.estVide()) {
                Message msg = fileVersCentrOp.enleverElement();
                centreControle.receptionMessageDeSatellite(msg);
            }

            //Envoyer les messages dans la file en direction du rover
            while (!fileVersRover.estVide()) {
                Message msg = fileVersRover.enleverElement();
                rover.receptionMessageDeSatellite(msg);
            }

            //Delai d'operation
            try {
                Thread.sleep(TEMPS_CYCLE_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
