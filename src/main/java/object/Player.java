package object;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {

  private AutonomousObject autonomousObject;

  private int score;

  public Player(AutonomousObject autonomousObject, int score) {
    this.autonomousObject = autonomousObject;
    this.score = score;
  }

  public void render(Graphics2D graphics2D) {
    graphics2D.translate((int)autonomousObject.getPosition().getX(), (int)autonomousObject.getPosition().getY());
    autonomousObject.render(graphics2D, Color.CYAN);
    graphics2D.translate(-(int)autonomousObject.getPosition().getX(), -(int)autonomousObject.getPosition().getY());
  }

  public void update(double timepassed) {

  }

  public AutonomousObject getAutonomousObject() {
    return autonomousObject;
  }

  public void setAutonomousObject(AutonomousObject autonomousObject) {
    this.autonomousObject = autonomousObject;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }
}
