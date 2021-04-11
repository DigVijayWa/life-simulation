package network;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

public class ServerConnect {

  private static WebSocketClient webSocketClient;

  public static void main(String args[]) throws URISyntaxException {
    webSocketConnection(UUID.randomUUID().toString());
  }

  private static void webSocketConnection(String state) throws URISyntaxException {
      String socketUrl = "ws://localhost:8080/connect?id=";
      String webSocketURL = socketUrl + state;
      System.out.print("trying to connect : " + webSocketURL + "\n");
      webSocketClient = new WebSocketClient(new URI(webSocketURL)) {
        @Override
        public void onMessage(String message) {
          JSONObject jsonObject = new JSONObject(message);
        }

        @Override
        public void onOpen(ServerHandshake handshake) {
          System.out.println("Connection Opened");
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
          System.out.print("\n Code : " + code + "\n Reason : " + reason + "remote : " + remote);
          System.out.println("Connection Closed");
        }

        @Override
        public void onError(Exception ex) {
          ex.printStackTrace();
        }
      };
      webSocketClient.connect();
  }
}
