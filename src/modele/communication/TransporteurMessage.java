package modele.communication;

/**
 * Classe qui implémente le protocol de communication entre le Rover
 * et le Centre d'opération.
 *
 * Il se base sur une interprétation libre du concept de Nack:
 * 	https://webrtcglossary.com/nack/
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
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public abstract class TransporteurMessage extends Thread {

    // compteur de message
    protected CompteurMessage compteurMsg;
    // lock qui protège la liste de messages reçu
    private ReentrantLock lock = new ReentrantLock();

    // Liste pour stocker les messages reçus
    protected List<Message> messagesRecus;
    // Liste pour stocker les messages envoyés
    protected LinkedList<Message> messagesEnvoyes; // apparement I need that for peek

    /**
     * Constructeur, initialise le compteur de messages unique
     */
    public TransporteurMessage() {
        compteurMsg = new CompteurMessage();
        messagesRecus = new ArrayList<>();
        messagesEnvoyes = new LinkedList<>();
    }

    /**
     * Méthode gérant les messages reçu du satellite. La gestion se limite
     * à l'implémentation du Nack, les messages spécialisé sont envoyés
     * aux classes dérivés
     *
     * @param msg, message reçu
     */
    // 6.3.3
    public void receptionMessageDeSatellite(Message msg) {
        lock.lock();

        try {
            if (msg instanceof Nack) {
                // Si c'est un Nack, ajoutez le message au début de la liste des messages reçus
                messagesRecus.add(0, msg);
            } else {
                // Sinon, évaluez la position du message dans la liste en fonction du compte du
                // message
                int position = 0;
                for (Message m : messagesRecus) {
                    if (msg.getCompte() < m.getCompte()) {
                        break;
                    }
                    position++;
                }
                // Ajoutez le message à la position trouvée
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
    // 6.3.4
    public void run() {
        // Initialise le compteur de message courant
        int compteCourant = 0;

        while (true) { // Boucle infinie

            lock.lock();

            try {
                // Indique si un Nack a été envoyé
                boolean nackEnvoye = false;
                // Tant qu'il y a des messages et qu'aucun Nack n'a été envoyé
                while (!messagesRecus.isEmpty() && !nackEnvoye) {
                    // Obtient le prochain message à gérer (début de la liste)
                    Message prochainMessage = messagesRecus.get(0);

                    if (prochainMessage instanceof Nack) {
                        // S'il s'agit d'un Nack
                        int compteManquant = ((Nack) prochainMessage).getCompte(); // Obtient le compte du message
                                                                                   // manquant

                        for (int i = 0; i < messagesEnvoyes.size(); i++) {
                            Message messageEnvoyer = messagesEnvoyes.get(i);

                            if (messageEnvoyer.getCompte() != compteManquant) {
                                messagesEnvoyes.remove(i);
                            }
                        }

                        Message messageAEnvoyer = messagesEnvoyes.peek();

                        envoyerMessage(messageAEnvoyer);

                        // Enlève le message Nack de la liste des reçus
                        messagesRecus.remove(0);

                    } else if (prochainMessage.getCompte() > compteCourant) {
                        // S'il y a un message manquant (comparer le compteCourant)
                        // Envoie un Nack avec la valeur du message manquant (compteCourant)
                        envoyerMessage(new Nack(compteCourant));
                        // Marque qu'un Nack a été envoyé (pour quitter la boucle)
                        nackEnvoye = true;
                    } else if (prochainMessage.getCompte() < compteCourant) {
                        // Si le compte du message est supérieur à compteCourant
                        // Rejete le message, car il s'agit d'un duplicat
                        messagesRecus.remove(prochainMessage);
                    } else {
                        // Si le compte du message est égal à compteCourant
                        // Fait suivre le message au gestionnaireMessage
                        gestionnaireMessage(prochainMessage);
                        // Défile le message
                        messagesRecus.remove(prochainMessage);
                        // Incrémente le compteCourant
                        compteCourant++;
                    }
                }

                // Envoi un message NoOp
                envoyerMessage(new NoOp(compteurMsg.getCompteActuel()));
            } finally {
                lock.unlock();
            }

            // Pause, cycle de traitement de messages
            try {
                Thread.sleep(650);
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
