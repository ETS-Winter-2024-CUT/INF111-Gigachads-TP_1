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
    public List<Message> messagesRecus;
    // liste de message envoyer par le transporteur
    public Queue<Message> messagesEnvoyes;

    protected Queue<Message> fileMessages = new LinkedList<>(); // File de messages reçus
    protected boolean nackEnvoye = false; // Indique si un Nack a été envoyé

    /**
     * Constructeur, initialise le compteur de messages unique
     */
    public TransporteurMessage() {
        compteurMsg = new CompteurMessage();
        this.messagesRecus = new ArrayList<>();
        this.messagesEnvoyes = new LinkedList<>();
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
            if (msg instanceof Nack) {
                // Si le message reçu est un Nack, on le met au début de la liste
                messagesRecus.add(0, msg);
            } else {
                // Sinon, on calcule sa position selon le compteur
                int position = 0;
                for (int i = 0; i < messagesRecus.size(); i++) {
                    Message currentMsg = messagesRecus.get(i);
                    if (msg.getCompte() < currentMsg.getCompte()) {
                        break;
                    }
                    position++;
                }
                // On ajoute le message à la position voulue
                messagesRecus.add(position, msg);
            }
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
                boolean nackEnvoyer = false;

                // Tant qu'il y a des messages reçus et qu'aucun Nack n'a été envoyé
                while (!messagesRecus.isEmpty() && !nackEnvoyer) {
                    Message msg = messagesRecus.get(0);// Récupération du premier message reçu

                    if (msg instanceof Nack) {
                        int compteManquant = ((Nack) msg).getCompte(); // on vas chercher le compte manquant
                        Set<Message> messagesManquants = new HashSet<>(); // ensemble pour stocker les messages
                                                                          // manquants
                        for (int i = 0; i < messagesEnvoyes.size(); i++) {
                            Message messageEnvoye = messagesEnvoyes.poll(); // Récupération et suppression du premier
                                                                            // message
                            if (messageEnvoye instanceof Nack || messageEnvoye.getCompte() < compteManquant) {
                                continue;
                            } else if (messageEnvoye.getCompte() == compteManquant) { // ajout du message dans les
                                                                                      // messages manquant
                                messagesManquants.add(messageEnvoye);
                            } else { // ajoue du message envoyer a la fin de la liste
                                messagesEnvoyes.add(messageEnvoye);
                                break;
                            }
                        }

                        // si ya des message manquant, on les renvoie
                        if (!messagesManquants.isEmpty()) {
                            for (Message manquant : messagesManquants) {
                                envoyerMessage(manquant);
                            }
                            messagesRecus.remove(msg); // on enleve le nack
                        } else {
                            nackEnvoyer = true; // un nack a ete envoyer
                        }
                    } else if (msg.getCompte() != compteCourant) { // si le nb de message != compte courant, on envoie
                                                                   // un nack
                        envoyerMessage(new Nack(compteCourant));
                        nackEnvoyer = true;
                    } else if (msg.getCompte() < compteCourant) {
                        System.out.println("Message dupliqué rejeté : " + msg);
                        messagesRecus.remove(msg);
                    } else {
                        gestionnaireMessage(msg);
                        messagesRecus.remove(msg);
                        compteCourant++;
                    }
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
