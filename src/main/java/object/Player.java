package object;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {

  private AutonomousObject autonomousObject;

  private int score;

  public enum PlayerType {
    LOCAL, REMOTE;
  }

  private PlayerType playerType;

  private String playerId;

  public Player(AutonomousObject autonomousObject, int score, String playerId, PlayerType playerType) {
    this.autonomousObject = autonomousObject;
    this.score = score;
    this.playerType = playerType;
    this.playerId = playerId;
  }

  public void render(Graphics2D graphics2D) {
    autonomousObject.render(graphics2D, Color.CYAN);
  }

  public void update(double timepassed) {
    autonomousObject.update(timepassed);
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

  public PlayerType getPlayerType() {
    return playerType;
  }

  public void setPlayerType(PlayerType playerType) {
    this.playerType = playerType;
  }

  public String getPlayerId() {
    return playerId;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }
}
