package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import modele.communication.CompteurMessage;

public class TestCompteurMessage {

    @Test
    public void testGetCompteActuel() {
        // Création d'un compteur de messages
        CompteurMessage compteur = new CompteurMessage();

        // Test pour vérifier que le premier compte est 0
        assertEquals(0, compteur.getCompteActuel(), "Le premier compte devrait être 0.");

        // Test pour vérifier l'incrémentation du compte
        assertEquals(1, compteur.getCompteActuel(), "Le compte devrait être incrémenté à 1.");

        // Test pour vérifier plusieurs incréments successifs
        for (int i = 2; i <= 10; i++) {
            assertEquals(i, compteur.getCompteActuel(), "Le compte devrait être incrémenté à " + i + ".");
        }
    }
}
