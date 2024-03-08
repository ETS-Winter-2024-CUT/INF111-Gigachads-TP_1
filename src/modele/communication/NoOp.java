package modele.communication;

/**
 * Classe représentant un message de type NoOp.
 * Il s'agit d'un message de type "No Operation" qui est généralement
 * utilisé pour maintenir une communication active sans effectuer d'action
 * significative. Il hérite de la classe Message et transmet l'identifiant
 * au constructeur de la classe parente.
 * @Author: Maxim Dmitriev, Vianney Veremme, Leonard Marcoux
 * @Date: Mars 2024
 */
public class NoOp extends Message {

    /**
     * Constructeur par defaut
     * @param compte
     */
    public NoOp(int compte) {
        super(compte); // Appelle le constructeur de la classe mere (Message)
    }
}
