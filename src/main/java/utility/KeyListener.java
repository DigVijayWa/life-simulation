package utility;

import java.awt.event.KeyEvent;
import object.Handler;

public class KeyListener implements java.awt.event.KeyListener {

  private Handler handler;

  public KeyListener(Handler handler) {
    this.handler = handler;
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

    //TODO:(Digvijay) here we want to apply the input to the local player
    switch(e.getKeyCode()) {
      case KeyEvent.VK_W:
      case KeyEvent.VK_UP:
        break;
      case KeyEvent.VK_A:
      case KeyEvent.VK_LEFT:
        break;
      case KeyEvent.VK_S:
      case KeyEvent.VK_DOWN:
        break;
      case KeyEvent.VK_D:
      case KeyEvent.VK_RIGHT:
        break;
      default:
    }

  }
}
