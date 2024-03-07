package testsUnitaires;

import utilitaires.Vect2D;

public class TestVect2D {
    public static void main(String[] args) {
        testVect2D();
    }

    public static void testVect2D() {
        // Création de vecteurs
        Vect2D vecteur1 = new Vect2D();
        Vect2D vecteur2 = new Vect2D(3.0, 4.0);

        // Test des accesseurs après la création des vecteurs
        System.out.println("Vecteur 1 : x = " + vecteur1.getLongueurX() + ", y = " + vecteur1.getLongueurY());
        System.out.println("Vecteur 2 : x = " + vecteur2.getLongueurX() + ", y = " + vecteur2.getLongueurY());

        // Test du calcul de la différence
        Vect2D diff = vecteur1.calculerDIFF(vecteur2);
        System.out.println("Différence : x = " + diff.getLongueurX() + ", y = " + diff.getLongueurY());

        // Test de la méthode diviser
        vecteur2.diviser(2);
        System.out.println("Vecteur 2 après division : x = " + vecteur2.getLongueurX() + ", y = " + vecteur2.getLongueurY());

        // Test de la méthode ajouter
        vecteur1.ajouter(2.0, 1.0);
        System.out.println("Vecteur 1 après ajout : x = " + vecteur1.getLongueurX() + ", y = " + vecteur1.getLongueurY());

        // Test de la méthode equals
        Vect2D vecteur3 = new Vect2D(3.0, 4.0);
        System.out.println("Vecteur 2 = Vecteur 3 ? " + vecteur2.equals(vecteur3));

        // Test de la méthode toString
        System.out.println("Vecteur 1 : " + vecteur1);
        System.out.println("Vecteur 2 : " + vecteur2);
    }
}
