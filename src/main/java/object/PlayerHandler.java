package object;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;
import object.Player.PlayerType;

public class PlayerHandler {

  List<Player> playerList = new ArrayList<>();


  public PlayerHandler() {
    playerList.add(
        new Player(new AutonomousObject(100, 100, "Dominator"), 0, UUID.randomUUID().toString(), PlayerType.LOCAL));
  }

  public void render(Graphics2D graphics2D) {
    playerList.forEach(player -> player.render(graphics2D));
  }

  public void update(long timePassed) {
    playerList.forEach(player -> player.update(timePassed));
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
}
