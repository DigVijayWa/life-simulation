package network;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import object.PlayerHandler;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

public class ServerConnect {

  private static MessageProcessor webSocketClient;

  private PlayerHandler playerHandler;

  public ServerConnect(PlayerHandler playerHandler) {
    this.playerHandler = playerHandler;
  }

  public static void main(String args[]) throws URISyntaxException {

    ServerConnect serverConnect = new ServerConnect(new PlayerHandler());
    serverConnect.webSocketConnection(UUID.randomUUID().toString());
  }

  private void webSocketConnection(String state) throws URISyntaxException {
      String socketUrl = "ws://localhost:8080/connect?id=";
      String webSocketURL = socketUrl + state;
      System.out.print("trying to connect : " + webSocketURL + "\n");
      webSocketClient = new MessageProcessor(new URI(webSocketURL), this.playerHandler);
      webSocketClient.connect();
  }
}
