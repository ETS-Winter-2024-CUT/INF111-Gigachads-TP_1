package testsUnitaires;

/**
 * Classe de test unitaire
 * @Author: Maxim Dmitriev, Vianney Veremme, Leonard Marcoux
 * @Date: Mars 2024
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import modele.communication.Message;

public class vraiTestMessage {

    @Test
    public void testGetTempsEnvoi() {
        // Création d'un message avec un compte unique
        Message message = new MessageTestImpl(1);

        // Vérification du temps de l'envoi initial
        assertTrue(message.getTempsEnvoi() > 0, "Le temps d'envoi initial devrait être positif.");

        // Mise à jour du temps d'envoi
        long nouveauTempsEnvoi = System.currentTimeMillis();
        message.setTempsEnvoi(nouveauTempsEnvoi);

        // Vérification du temps d'envoi après la mise à jour
        assertEquals(nouveauTempsEnvoi, message.getTempsEnvoi(), "Le temps d'envoi devrait être mis à jour.");
    }

    private static class MessageTestImpl extends Message {
        public MessageTestImpl(int compte) {
            super(compte);
        }
    }
}
