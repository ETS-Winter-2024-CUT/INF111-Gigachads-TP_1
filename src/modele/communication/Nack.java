package modele.communication;

/**
 * Classe représentant un message de type Nack.
 * Il s'agit d'un message de retour indiquant qu'un message a été reçu
 * incorrectement ou qu'une action n'a pas été effectuée correctement.
 * Il hérite de la classe Message et transmet l'identifiant au constructeur
 * de la classe parente.
 * @Author: Maxim Dmitriev, Vianney Veremme, Leonard Marcoux
 * @Date: Mars 2024
 */
public class Nack extends Message {

    /**
     * Constructeur par defaut
     * @param compte
     */
    public Nack(int compte) {
        super(compte); // Appelle le constructeur de la classe mere (Message)
    }
}
