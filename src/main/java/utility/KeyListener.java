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

  public enum Direction {
    LEFT(-1), RIGHT(1), NO_CHANGE(0);

    int value;

    private Direction(int value) {
      this.value = value;
    }
  }

  ;

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_W:
      case KeyEvent.VK_UP:
        changeDirection(Direction.NO_CHANGE, GameConstant.magnitudeChange);
        break;
      case KeyEvent.VK_A:
      case KeyEvent.VK_LEFT:
        changeDirection(Direction.LEFT, 0);
        break;
      case KeyEvent.VK_S:
      case KeyEvent.VK_DOWN:
        changeDirection(Direction.NO_CHANGE, -GameConstant.magnitudeChange);
        break;
      case KeyEvent.VK_D:
      case KeyEvent.VK_RIGHT:
        changeDirection(Direction.RIGHT, 0);
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
    for (Player player : playerHandler.getPlayerList()) {
      if (player.getPlayerType() == PlayerType.LOCAL) {
        player.getAutonomousObject().setAccelaration(accelaration);
        break;
      }
    }
  }

  public void changeDirection(Direction direction, double magnitudeChange) {
    for (Player player : playerHandler.getPlayerList()) {
      if (player.getPlayerType() == PlayerType.LOCAL) {
        double magnitude = player.getAutonomousObject().getVelocity().getMagnitude();
        double angle = player.getAutonomousObject().getVelocity().getAngle();
        player.getAutonomousObject().getVelocity()
            .setAngleAndMagnitude(angle + GameConstant.angleChange * direction.value,
                magnitude + magnitudeChange);
        break;
      }
    }
  }
}
