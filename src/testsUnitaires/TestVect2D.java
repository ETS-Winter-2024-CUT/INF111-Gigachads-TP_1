package testsUnitaires;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import utilitaires.Vect2D;

public class TestVect2D {

    @Test
    public void testConstructeurs() {
        // Test du constructeur par défaut
        Vect2D vect1 = new Vect2D();
        assertEquals(0, vect1.getX(), "La coordonnée x devrait être 0.");
        assertEquals(0, vect1.getY(), "La coordonnée y devrait être 0.");

        // Test du constructeur par paramètres
        Vect2D vect2 = new Vect2D(3, 4);
        assertEquals(3, vect2.getX(), "La coordonnée x devrait être 3.");
        assertEquals(4, vect2.getY(), "La coordonnée y devrait être 4.");

        // Test du constructeur par copie
        Vect2D vect3 = new Vect2D(vect2);
        assertEquals(vect2.getX(), vect3.getX(), "Les coordonnées x devraient être les mêmes.");
        assertEquals(vect2.getY(), vect3.getY(), "Les coordonnées y devraient être les mêmes.");
    }

    @Test
    public void testGetLongueur() {
        // Test de la méthode getLongueur
        Vect2D vect = new Vect2D(3, 4);
        assertEquals(5, vect.getLongueur(), "La longueur devrait être 5.");
    }

    @Test
    public void testGetAngle() {
        // Test de la méthode getAngle
        Vect2D vect = new Vect2D(3, 4);
        assertEquals(Math.atan2(4, 3), vect.getAngle(), "L'angle devrait être atan(4/3).");
    }

    @Test
    public void testCalculerDiff() {
        // Test de la méthode calculerDiff
        Vect2D vect1 = new Vect2D(5, 8);
        Vect2D vect2 = new Vect2D(3, 4);
        Vect2D diff = vect1.calculerDiff(vect2);
        assertEquals(2, diff.getX(), "La différence en x devrait être 2.");
        assertEquals(4, diff.getY(), "La différence en y devrait être 4.");
    }

    @Test
    public void testDiviser() {
        // Test de la méthode diviser
        Vect2D vect = new Vect2D(6, 8);
        vect.diviser(2);
        assertEquals(3, vect.getX(), "La coordonnée x devrait être divisée par 2.");
        assertEquals(4, vect.getY(), "La coordonnée y devrait être divisée par 2.");
    }

    @Test
    public void testAjouter() {
        // Test de la méthode ajouter
        Vect2D vect = new Vect2D(3, 4);
        vect.ajouter(1, 2);
        assertEquals(4, vect.getX(), "La coordonnée x devrait être ajoutée de 1.");
        assertEquals(6, vect.getY(), "La coordonnée y devrait être ajoutée de 2.");
    }

    @Test
    public void testToString() {
        // Test de la méthode toString
        Vect2D vect = new Vect2D(3, 4);
        assertEquals("(3.0, 4.0)", vect.toString(), "La représentation sous forme de chaîne devrait être correcte.");
    }

    @Test
    public void testEquals() {
        // Test de la méthode equals
        Vect2D vect1 = new Vect2D(3, 4);
        Vect2D vect2 = new Vect2D(3, 4);
        assertTrue(vect1.equals(vect2), "Les deux vecteurs devraient être égaux.");

        Vect2D vect3 = new Vect2D(3, 5);
        assertFalse(vect1.equals(vect3), "Les deux vecteurs ne devraient pas être égaux.");
    }
}
