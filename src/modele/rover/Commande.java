package modele.rover;

public class Commande {
    private eCommande commande;

    public Commande(eCommande commande) {
        this.commande = commande;
    }

    public eCommande getCommande() {
        return commande;
    }

    public void setCommande(eCommande commande) {
        this.commande = commande;
    }
}
