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

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import modele.communication.Message;
import structures.FileChainee;

public class SatelliteRelai extends Thread {
    static final int TEMPS_CYCLE_MS = 500;
    static final double PROBABILITE_PERTE_MESSAGE = 0.15;

    ReentrantLock lock = new ReentrantLock();

    private Random rand = new Random();

    private FileChainee<Message> fcControle = new FileChainee<Message>();
    private FileChainee<Message> fcRover = new FileChainee<Message>();

    /**
     * Méthode permettant d'envoyer un message vers le centre d'opération
     *
     * @param msg, message à envoyer
     */
    public void envoyerMessageVersCentrOp(Message msg) {
        lock.lock();

        try {
            //Declarer le num random et verifier si il peut envoyer le message
            double randNum = rand.nextDouble();
            if(randNum >PROBABILITE_PERTE_MESSAGE){

                //Ajouter msg a la file qui va vers le rover
                fcControle.ajouterElement(msg);
            }else{
                //Declarer que le message est perdu si l'interference l'emporte
                System.out.println("Message Perdu!");
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
            //Declarer le num random et verifier si il peut envoyer le message
            double randNum = rand.nextDouble();
            if(randNum >PROBABILITE_PERTE_MESSAGE){

                //Ajouter msg a la file qui va vers le rover
                fcRover.ajouterElement(msg);
            }else{
                //Declarer que le message est perdu si l'interference l'emporte
                System.out.println("Message Perdu!");
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        while (true) {

            //Enlever les elements des 2 files
            fcControle.enleverElement();
            fcRover.enleverElement();

            // attend le prochain cycle
            try {
                Thread.sleep(TEMPS_CYCLE_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
