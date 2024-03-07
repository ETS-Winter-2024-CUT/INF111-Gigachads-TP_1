package testsUnitaires;

// Importez la classe Message
import modele.communication.Message; // Import correct

// Créez votre classe de message
public class MessageDeTest extends Message {
    // Constructeur
    public MessageDeTest(int compte) {
        super(compte);
    }

    // // Implémentez la méthode abstraite de la classe Message pour traiter le message
    // @Override
    // public void traiterMessage() {
    //     // Vous pouvez mettre ici la logique pour traiter le message
    //     System.out.println("Message reçu et traité par le rover : " + this);
    // }
}
