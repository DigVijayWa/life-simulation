package utility;

import constants.GameConstant;
import java.awt.event.KeyEvent;
import object.Player;
import object.Player.PlayerType;
import object.PlayerHandler;

public class KeyListener implements java.awt.event.KeyListener {

  private PlayerHandler playerHandler;

  public KeyListener(PlayerHandler handler) {
    this.playerHandler = handler;
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_W:
      case KeyEvent.VK_UP:
        applyForce(new Vector(
            0, -GameConstant.forceY));
        break;
      case KeyEvent.VK_A:
      case KeyEvent.VK_LEFT:
        applyForce(new Vector(
            -GameConstant.forceX, 0));
        break;
      case KeyEvent.VK_S:
      case KeyEvent.VK_DOWN:
        applyForce(new Vector(
            0, GameConstant.forceY));
        break;
      case KeyEvent.VK_D:
      case KeyEvent.VK_RIGHT:
        applyForce(new Vector(
            GameConstant.forceX, 0));
        break;
      default:
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

    //TODO:(Digvijay) here we want to apply the input to the local player

    /**
     * Problem with this part of the code is we are actually not editing the object here.
     * */

  }

  public void applyForce(Vector accelaration) {

    for(Player player: playerHandler.getPlayerList()) {
      if(player.getPlayerType() == PlayerType.LOCAL) {
        player.getAutonomousObject().setAccelaration(accelaration);
        break;
      }
    }
  }
}
