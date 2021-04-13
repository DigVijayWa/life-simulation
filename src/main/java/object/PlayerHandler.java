package object;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;
import object.Player.PlayerType;
import utility.Vector;

public class PlayerHandler {

  List<Player> playerList = new ArrayList<>();

  Camera camera;


  public PlayerHandler() {
    playerList.add(
        new Player(new AutonomousObject(100, 100, "Dominator"), 0, UUID.randomUUID().toString(),
            PlayerType.LOCAL));
    playerList.add(new Player(new AutonomousObject(), 0, UUID.randomUUID().toString(),
        PlayerType.REMOTE));

    playerList.add(new Player(new AutonomousObject(), 0, UUID.randomUUID().toString(),
        PlayerType.REMOTE));

    camera = new Camera(new Vector(0,0), null);
  }

  public void render(Graphics2D graphics2D) {
    graphics2D.translate(-camera.getPosition().getX(), -camera.getPosition().getY());
    playerList.forEach(player -> player.render(graphics2D));
  }

  public void update(long timePassed) {

    playerList.forEach(player -> player.update(timePassed));

    camera.update(getLocalPlayer().orElse(null));
  }

  public boolean addPlayer(Player player) {
    return playerList.add(player);
  }

  public boolean removePlayer(String playerId) {
    int tobeDeletedIndex = IntStream.range(0, playerList.size())
        .reduce((acc, index) -> playerList.get(index).getPlayerId().compareTo(playerId) == 0 ? index
            : acc).orElse(playerList.size());

    if (tobeDeletedIndex < playerList.size()) {
      return playerList.remove(tobeDeletedIndex) != null;
    }
    return false;
  }

  public Optional<Player> getLocalPlayer() {
    return playerList.stream().filter(item -> item.getPlayerType() == PlayerType.LOCAL).findFirst();
  }

  public Optional<Player> getRemotePlayers(String id) {
    return playerList.stream().filter(item -> item.getPlayerId() == id).findFirst();
  }

  public List<Player> getPlayerList() {
    return playerList;
  }

  public void setPlayerList(List<Player> playerList) {
    this.playerList = playerList;
  }
}
