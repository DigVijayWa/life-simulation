package utility;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import object.Handler;

public class MouseListener extends MouseAdapter implements MouseMotionListener {

  private Handler handler;

  public MouseListener(Handler handler) {
    this.handler = handler;
  }
  @Override
  public void mouseMoved(MouseEvent event) {
    handler.setMouseTarget(new Vector(event.getX(), event.getY()));
  }
}
