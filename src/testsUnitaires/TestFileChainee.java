package testsUnitaires;

import structures.FileChainee;

public class TestFileChainee {

    public static void main(String[] args) {
        // Appel à la méthode de test de la file chaînée
        testFileChainee();
    }

    // Méthode de test pour la file chaînée
    public static void testFileChainee() {
        // Création d'une file chaînée d'entiers
        FileChainee<Integer> file = new FileChainee<>();

        System.out.println("La file est vide ? " + file.estVide()); // Devrait afficher true

        // Test d'ajout d'éléments
        file.ajouterElement(10);
        file.ajouterElement(20);
        file.ajouterElement(30);

        // Test de la méthode estVide
        System.out.println("La file est vide ? " + file.estVide()); // Devrait afficher false

        // Test de la méthode enleverElement
        System.out.println("Élément enlevé : " + file.enleverElement()); // Devrait afficher 10
        System.out.println("Élément enlevé : " + file.enleverElement()); // Devrait afficher 20

        // Test après enlèvement d'éléments
        System.out.println("La file est vide ? " + file.estVide()); // Devrait afficher false

        // Test d'enlèvement d'un dernier élément
        System.out.println("Élément enlevé : " + file.enleverElement()); // Devrait afficher 30

        // Test après enlèvement de tous les éléments
        System.out.println("La file est vide ? " + file.estVide()); // Devrait afficher true

        // Tentative d'enlever un élément d'une file vide (devrait lever une exception)
        try {
            System.out.println("Élément enlevé : " + file.enleverElement());
        } catch (IllegalStateException e) {
            System.out.println("Exception attrapée : " + e.getMessage());
        }
    }
}
