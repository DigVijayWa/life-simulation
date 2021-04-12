package network;

import object.AutonomousObject;
import object.Player;
import object.Player.PlayerType;
import object.PlayerHandler;
import packet.Packet;

public class MessageProcessor {

  public static void processPlayerJoinedMessage(Packet packet, PlayerHandler playerHandler) {
    playerHandler.addPlayer(new Player(
        new AutonomousObject(packet.getData().getxInput(), packet.getData().getyInput(),
            packet.getData().getName()), 0,
        packet.getId(), PlayerType.REMOTE));
  }

  public static void processPlayerLeftMessage(Packet packet, PlayerHandler playerHandler) {
    playerHandler.removePlayer(packet.getId());
  }

  public static void processPacketMessage(Packet packet, PlayerHandler playerHandler) {

    //TODO:(Digvijay) apply the input to correct the remote player.

  }
}
