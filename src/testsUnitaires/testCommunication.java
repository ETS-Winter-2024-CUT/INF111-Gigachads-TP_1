// package testsUnitaires;

// import modele.communication.Message;
// import modele.rover.Rover;
// import modele.satelliteRelai.SatelliteRelai;
// import modele.centreControle.CentreControle;

// public class testCommunication extends Message {
//     public testCommunication(int compte) {
//         super(compte);
//     }

//     public static void main(String[] args) {
//         // Création d'un rover et d'un centre de contrôle
//         SatelliteRelai satellite = new SatelliteRelai();
//         CentreControle centreControle = new CentreControle(satellite);
//         Rover rover = new Rover(satellite);

//         // Lier le rover et le centre de contrôle au satellite relai
//         satellite.lierRover(rover);
//         satellite.lierCentrOp(centreControle);

//         // Démarrer le satellite relai
//         satellite.start();

//         // Envoyer des messages du centre de contrôle vers le rover
//         for (int i = 0; i < 10; i++) {
//             Message message = new Message(i);
//             centreControle.envoyerMessage(message);
//             System.out.println("Message envoyé depuis le centre de contrôle : " + message);
//         }

//         // Attendre un certain temps pour que les messages soient traités par le
//         // satellite relai
//         try {
//             Thread.sleep(5000);
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }

//         // Afficher le nombre de messages reçus par le rover
//         System.out.println("Nombre de messages reçus par le rover : " + rover.getCompte());
//     }
// }
