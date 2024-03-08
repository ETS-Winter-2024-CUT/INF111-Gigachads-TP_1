package testsUnitaires;

/**
 * Classe de test unitaire
 * @Author: Maxim Dmitriev, Vianney Veremme, Leonard Marcoux
 * @Date: Mars 2024
 */

import modele.communication.Message;

public class testMessage extends Message {
    private String contenuMsg;

    public testMessage(int compte, String contenuMsg) {
        super(compte);
        this.contenuMsg = contenuMsg;
    }

    public String getContenu() {
        return contenuMsg;
    }

    public void setContenu(String contenuMsg) {
        this.contenuMsg = contenuMsg;
    }
}
