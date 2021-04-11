package object;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import object.Player.PlayerType;

public class PlayerHandler {

  List<Player> playerList = new ArrayList<>();

  public void render(Graphics2D graphics2D) {
    playerList.forEach(player -> player.render(graphics2D));
  }

  public void update(long timePassed) {
    playerList.forEach(player -> player.update(timePassed));
  }

  public boolean addPlayer(Player player) {
    return playerList.add(player);
  }

  public boolean removePlayer(Player player) {
    return playerList.remove(player);
  }

  public Optional<Player> getLocalPlayer() {
    return playerList.stream().filter(item -> item.getPlayerType() == PlayerType.LOCAL).findFirst();
  }
}
