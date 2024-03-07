package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import structures.FileChainee;

public class TestFileChainee {

    @Test
    public void testFileChainee() {
        // Création d'une file chaînée d'entiers
        FileChainee<Integer> file = new FileChainee<>();

        // Test de la méthode estVide après la création de la file
        assertTrue(file.estVide(), "La file devrait être vide.");

        // Test d'ajout d'éléments
        file.ajouterElement(10);
        file.ajouterElement(20);
        file.ajouterElement(30);

        // Test de la méthode estVide après avoir ajouté des éléments
        assertFalse(file.estVide(), "La file ne devrait pas être vide après avoir ajouté des éléments.");

        // Test de la méthode enleverElement
        assertEquals(10, file.enleverElement(), "L'élément enlevé devrait être 10.");
        assertEquals(20, file.enleverElement(), "L'élément enlevé devrait être 20.");

        // Test après avoir enlevé deux des trois éléments
        assertFalse(file.estVide(), "La file ne devrait pas être vide après avoir enlevé des éléments.");

        // Test d'enlèvement du dernier élément
        assertEquals(30, file.enleverElement(), "L'élément enlevé devrait être 30.");

        // Test après avoir enlevé tous les éléments
        assertTrue(file.estVide(), "La file devrait être vide après avoir enlevé tous les éléments.");

        // Tentative d'enlever un élément d'une file vide (devrait lever une exception)
        assertThrows(IllegalStateException.class, () -> {
            file.enleverElement();
        }, "Devrait lever une exception lorsque l'on essaie d'enlever un élément d'une file vide.");
    }
}
