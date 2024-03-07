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

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import modele.centreControle.CentreControle;
import modele.communication.Message;
import modele.rover.Rover;

public class SatelliteRelai extends Thread {

    static final int TEMPS_CYCLE_MS = 500;
    static final double PROBABILITE_PERTE_MESSAGE = 0.15;

    ReentrantLock lock = new ReentrantLock();

    private final Queue<Message> fileVersCentrOp;
    private final Queue<Message> fileVersRover;

    private Random rand = new Random();

    private CentreControle centreControle;
    private Rover rover;

    // Méthode pour lier le Centre de contrôle
    public void lierCentrOp(CentreControle centreControle) {
        this.centreControle = centreControle;
    }

    // Méthode pour lier le Rover
    public void lierRover(Rover rover) {
        this.rover = rover;
    }

    public SatelliteRelai() {
        fileVersCentrOp = new LinkedList<>();
        fileVersRover = new LinkedList<>();
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
                fileVersCentrOp.offer(msg);
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
                fileVersRover.offer(msg);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("SatelliteRelai: Traitement des messages...");

            // Envoi des messages vers le Centre de contrôle
            if (!fileVersCentrOp.isEmpty()) {
                Message msg = fileVersCentrOp.poll();

                if (msg == null) {
                    System.out.println("SatelliteRelais: Message vers Centre de Controle perdu! " + msg);
                } else {
                    System.out.println("SatelliteRelais: Evoi message vers centre Controle! Message #" + msg.getCompte());
                    centreControle.receptionMessageDeSatellite(msg);
                }
            }
            if (!fileVersRover.isEmpty()) {
                Message msg = fileVersRover.poll();

                if (msg == null) {
                    System.out.println("SatelliteRelais: Message vers Rover Perdu! " + msg);
                } else {
                    System.out.println("SatelliteRelais: Evoi message vers Rover! Message #" + msg.getCompte());
                    rover.receptionMessageDeSatellite(msg);
                }
            }
            try {
                Thread.sleep(TEMPS_CYCLE_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
