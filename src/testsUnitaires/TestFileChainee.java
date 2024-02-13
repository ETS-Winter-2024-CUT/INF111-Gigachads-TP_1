package testsUnitaires;

import structures.FileChainee;

public class TestFileChainee {

    public static void main(String[] args) {
        testFileChainee();
    }

    public static void testFileChainee() {
        // Création d'une file chaînée d'entiers
        FileChainee<Integer> file = new FileChainee<>();

        // Test de la méthode estVide après la création de la file
        System.out.println("La file est vide ? " + file.estVide()); // Devrait afficher true

        // Test d'ajout d'éléments
        file.ajouterElement(10);
        file.ajouterElement(20);
        file.ajouterElement(30);

        // Test de la méthode estVide après avoir ajouté des éléments
        System.out.println("La file est vide ? " + file.estVide()); // Devrait afficher false

        // Test de la méthode enleverElement
        System.out.println("Élément enlevé : " + file.enleverElement()); // Devrait afficher 10
        System.out.println("Élément enlevé : " + file.enleverElement()); // Devrait afficher 20

        // Test après avoir enlevé deux des trois éléments
        System.out.println("La file est vide ? " + file.estVide()); // Devrait afficher false

        // Test d'enlèvement du dernier élément
        System.out.println("Élément enlevé : " + file.enleverElement()); // Devrait afficher 30

        // Test après avoir enlevé tous les éléments
        System.out.println("La file est vide ? " + file.estVide()); // Devrait afficher true

        // Tentative d'enlever un élément d'une file vide (devrait lever une exception)
        try {
            System.out.println("Élément enlevé : " + file.enleverElement());
        } catch (IllegalStateException e) {
            System.out.println("Exception attrapée : " + e.getMessage());
        }
    }
}
