package network;

import com.google.gson.Gson;
import java.net.URI;
import java.util.Date;
import java.util.UUID;
import object.AutonomousObject;
import object.Player;
import object.Player.PlayerType;
import object.PlayerHandler;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import packet.Packet;
import packet.PlayerData;

public class MessageProcessor extends WebSocketClient {

  private PlayerHandler playerHandler;

  public enum PacketType {
    PACKET("PACKET"), PLAYER_JOINED("PLAYER_JOINED"), PLAYER_LEFT("PLAYER_LEFT");

    String value;

    private PacketType(String value) {
      this.value = value;
    }
  }

  public MessageProcessor(URI uri, PlayerHandler playerHandler) {
    super(uri);
    this.playerHandler = playerHandler;
  }

  @Override
  public void onOpen(ServerHandshake handshakedata) {
    System.out.println("Connection Opened");
    Player localPlayer = this.playerHandler.getLocalPlayer().orElse(null);
    this.send(new Packet(new PlayerData(localPlayer.getAutonomousObject().getPosition().getX(),
        localPlayer.getAutonomousObject().getPosition().getY(),
        localPlayer.getAutonomousObject().getName()),
        UUID.randomUUID().toString(), 10, new Date().getTime(), PacketType.PLAYER_JOINED.value).toString());
  }

  @Override
  public void onMessage(String message) {
    Packet packet = new Gson().fromJson(message, Packet.class);

    if (packet.getType().compareTo(PacketType.PACKET.value) == 0) {
      processPacketMessage(packet);
    } else if (packet.getType().compareTo(PacketType.PLAYER_JOINED.value) == 0) {
      processPlayerJoinedMessage(packet);
    } else if (packet.getType().compareTo(PacketType.PLAYER_LEFT.value) == 0) {
      processPlayerLeftMessage(packet);
    }
  }

  @Override
  public void onClose(int code, String reason, boolean remote) {

  }

  @Override
  public void onError(Exception ex) {

  }

  private void processPlayerJoinedMessage(Packet packet) {
    playerHandler.addPlayer(new Player(
        new AutonomousObject(packet.getData().getxPosition(), packet.getData().getyPosition(),
            packet.getData().getName()), 0,
        packet.getId(), PlayerType.REMOTE));
  }

  private void processPlayerLeftMessage(Packet packet) {
    playerHandler.removePlayer(packet.getId());
  }

  private void processPacketMessage(Packet packet) {

  }
}
