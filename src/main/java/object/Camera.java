package object;

import Constats.GameConstant;
import utility.Vector;

public class Camera {

  private Vector position;

  private Vector velocity;

  private Vector accelaration;

  private Player player;



  public Camera(Vector position, Player player) {
    this.position = position;
    this.accelaration = new Vector(0,0);
    this.velocity = new Vector(0,0);
    this.player = player;
  }

  public void update(double timePassed) {
      double actualTimePassed = 0.01666666666;
      position.setXandY(player.getAutonomousObject().getPosition().getX() - GameConstant.VIEW_PORT_SIZE_X / 2,
            player.getAutonomousObject().getPosition().getY() - GameConstant.VIEW_PORT_SIZE_Y / 2);
  }

  public Vector getPosition() {
    return position;
  }

  public void setPosition(Vector position) {
    this.position = position;
  }

  public Vector getVelocity() {
    return velocity;
  }

  public void setVelocity(Vector velocity) {
    this.velocity = velocity;
  }

  public Vector getAccelaration() {
    return accelaration;
  }

  public void setAccelaration(Vector accelaration) {
    this.accelaration = accelaration;
  }
}
