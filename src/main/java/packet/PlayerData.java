package packet;

public class PlayerData {

  private double xPosition;
  private double yPosition;

  private String name;

  public PlayerData(double xPosition, double yPosition, String name) {
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.name = name;
  }

  public double getxPosition() {
    return xPosition;
  }

  public void setxPosition(double xPosition) {
    this.xPosition = xPosition;
  }

  public double getyPosition() {
    return yPosition;
  }

  public void setyPosition(double yPosition) {
    this.yPosition = yPosition;
  }

  @Override
  public String toString() {
    return "{" +
        "\"xPosition\":" + xPosition +
        ", \"yPosition\":" + yPosition +
        ", \"name\":\"" + name + "\""+
        "}";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
