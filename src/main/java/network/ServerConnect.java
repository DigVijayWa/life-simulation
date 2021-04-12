package network;

import com.google.gson.Gson;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.UUID;
import object.Player;
import object.PlayerHandler;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import packet.Packet;
import packet.PlayerData;

public class ServerConnect extends WebSocketClient {

  private static ServerConnect webSocketClient;

  private PlayerHandler playerHandler;

  public enum PacketType {
    PACKET("PACKET"), PLAYER_JOINED("PLAYER_JOINED"), PLAYER_LEFT("PLAYER_LEFT");

    String value;

    private PacketType(String value) {
      this.value = value;
    }
  }

  public ServerConnect(URI uri, PlayerHandler playerHandler) {
    super(uri);
    this.playerHandler = playerHandler;
  }

  public static void main(String args[]) throws URISyntaxException {

    /*ServerConnect serverConnect = new ServerConnect(new PlayerHandler());
    serverConnect.webSocketConnection(UUID.randomUUID().toString());*/
  }

  private void webSocketConnection(String state) throws URISyntaxException {
      String socketUrl = "ws://localhost:8080/connect?id=";
      String webSocketURL = socketUrl + state;
      System.out.print("trying to connect : " + webSocketURL + "\n");
      webSocketClient = new ServerConnect(new URI(webSocketURL), this.playerHandler);
      webSocketClient.connect();
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
      MessageProcessor.processPacketMessage(packet, playerHandler);
    } else if (packet.getType().compareTo(PacketType.PLAYER_JOINED.value) == 0) {
      MessageProcessor.processPlayerJoinedMessage(packet, playerHandler);
    } else if (packet.getType().compareTo(PacketType.PLAYER_LEFT.value) == 0) {
      MessageProcessor.processPlayerLeftMessage(packet, playerHandler);
    }
  }

  @Override
  public void onClose(int code, String reason, boolean remote) {

  }

  @Override
  public void onError(Exception ex) {

  }
}
