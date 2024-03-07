package modele.communication;

/**
 * Classe représentant un message de type NoOp.
 * Il s'agit d'un message de type "No Operation" qui est généralement
 * utilisé pour maintenir une communication active sans effectuer d'action
 * significative. Il hérite de la classe Message et transmet l'identifiant
 * au constructeur de la classe parente.
 */
public class NoOp extends Message {
    // paramètre "id" transmis au constructeur de la classe parente "Message"
    public NoOp(int compte) {
        super(compte);
    }
}
