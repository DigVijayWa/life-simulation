package object;

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
