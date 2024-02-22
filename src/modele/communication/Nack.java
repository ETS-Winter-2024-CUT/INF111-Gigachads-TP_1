package modele.communication;

/**
 * Classe représentant un message de type Nack.
 * Il s'agit d'un message de retour indiquant qu'un message a été reçu
 * incorrectement ou qu'une action n'a pas été effectuée correctement.
 * Il hérite de la classe Message et transmet l'identifiant au constructeur
 * de la classe parente.
 */
public class Nack extends Message {
    // paramètre "id" transmis au constructeur de la classe parente "Message"
    public Nack(int id) {
        super(id);
    }
}
