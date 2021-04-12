package network;

import object.AutonomousObject;
import object.Player;
import object.Player.PlayerType;
import object.PlayerHandler;
import packet.Packet;
import utility.Vector;

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

    /**
     * Side effects at its best.
     * */
    Player remotePlayer = playerHandler.getRemotePlayers(packet.getId()).orElse(null);

    if (remotePlayer != null) {
      remotePlayer.getAutonomousObject()
          .setAccelaration(new Vector(packet.getData().getxInput(), packet.getData().getyInput()));
    }
  }
}
