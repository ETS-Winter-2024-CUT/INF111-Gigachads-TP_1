package modele.communication;

/**
 * Classe qui implémente le protocol de communication entre le Rover
 * et le Centre d'opération.
 *
 * Il se base sur une interprétation libre du concept de Nack:
 *     https://webrtcglossary.com/nack/
 *
 * Les messages envoyés sont mémorisé. À l'aide du compte unique
 * le transporteur de message peut identifier les Messages manquant
 * dans la séquence et demander le renvoi d'un Message à l'aide du Nack.
 *
 * Pour contourner la situation ou le Nack lui-même est perdu, le Nack
 * est renvoyé periodiquement, tant que le Message manquant n'a pas été reçu.
 *
 * C'est également cette classe qui gère les comptes unique.
 *
 * Les messages reçu sont mis en file pour être traité.
 *
 * La gestion des messages reçu s'effectue comme une tâche s'exécutant indépendamment (Thread)
 *
 * Quelques détails:
 *  - Le traitement du Nack a priorité sur tout autre message.
 *  - Un message NoOp est envoyé périodiquement pour s'assurer de maintenir
 *    une communication active et identifier les messages manquants en bout de séquence.
 *
 * Services offerts:
 *  - TransporteurMessage
 *  - receptionMessageDeSatellite
 *  - run
 *
 * @author Frederic Simard, ETS
 * @version Hiver, 2024
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import modele.communication.*;

public abstract class TransporteurMessage extends Thread {
    // compteur de message
    protected CompteurMessage compteurMsg;
    // lock qui protège la liste de messages reçu
    private ReentrantLock lock = new ReentrantLock();

    // liste de message recu par le transporteur
    private List<Message> messagesRecus;
    // liste de message envoyer par le transporteur
    private List<Message> messagesEnvoyes; // is gonna be usefull later

    protected Queue<Message> fileMessages = new LinkedList<>(); // File de messages reçus
    protected boolean nackEnvoye = false; // Indique si un Nack a été envoyé

    /**
     * Constructeur, initialise le compteur de messages unique
     */
    public TransporteurMessage() {
        compteurMsg = new CompteurMessage();
    }

    /**
     * Méthode gérant les messages reçu du satellite. La gestion se limite
     * à l'implémentation du Nack, les messages spécialisé sont envoyés
     * aux classes dérivés
     *
     * @param msg, message reçu
     */
    public void receptionMessageDeSatellite(Message msg) {
        lock.lock();

        try {
            /*
             * (6.3.3) Insérer votre code ici
             */
        } finally {
            lock.unlock();
        }
    }

    @Override
    /**
     * Tâche effectuant la gestion des messages reçu
     */
    public void run() {
        int compteCourant = 0;

        while (true) {
            lock.lock();
            try {
                Message msg = fileMessages.poll(); //recupere le dernier message de la file

                if (msg == null || nackEnvoye) {   //si la file est vide, ou un cacj a ete envoyer, aucune action necessaire
                    break;
                }

                // Si le message est un Nack
                if (msg instanceof Nack) {
                    Nack nack = (Nack) msg;
                    int compteManquant = nack.getCompte();

                    //parcour la liste des message
                    while (!messagesEnvoyes.isEmpty()) {
                        Message messageEnvoye = messagesEnvoyes.peek();

                        // quand on trouve le message manquant, on delete tt les message d'apres
                        if (messageEnvoye instanceof Nack || messageEnvoye.getCompte() < compteManquant) {
                            messagesEnvoyes.poll();
                        } else {
                            break;
                        }
                    }

                    // Message à envoyer
                    Message messageAEnvoyer = messagesEnvoyes.peek();
                    envoyerMessage(messageAEnvoyer);
                    messagesRecus.remove(nack);    // on eneleve le nack
                } else if (msg.getCompte() != compteCourant) {
                    //renvoie un nack qui dit le nombre de message manquant
                    envoyerMessage(new Nack(compteCourant));
                    nackEnvoye = true;
                } else if (msg.getCompte() < compteCourant) {
                    System.out.println("Message dupliqué rejeté : " + msg);
                } else {
                    gestionnaireMessage(msg);
                    fileMessages.poll();          //Can you comment this part?!
                    compteCourant++;
                }
            } finally {
                lock.unlock();
            }

            // pause, cycle de traitement de messages
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * méthode abstraite utilisé pour envoyer un message
     *
     * @param msg, le message à envoyer
     */
    abstract protected void envoyerMessage(Message msg);

    /**
     * méthode abstraite utilisé pour effectuer le traitement d'un message
     *
     * @param msg, le message à traiter
     */
    abstract protected void gestionnaireMessage(Message msg);
}
