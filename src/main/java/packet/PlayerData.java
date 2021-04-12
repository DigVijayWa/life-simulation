package packet;

public class PlayerData {

  private double xInput;
  private double yInput;

  private String name;

  public PlayerData(double xInput, double yInput, String name) {
    this.xInput = xInput;
    this.yInput = yInput;
    this.name = name;
  }

  public double getxInput() {
    return xInput;
  }

  public void setxInput(double xInput) {
    this.xInput = xInput;
  }

  public double getyInput() {
    return yInput;
  }

  public void setyInput(double yInput) {
    this.yInput = yInput;
  }

  @Override
  public String toString() {
    return "{" +
        "\"xPosition\":" + xInput +
        ", \"yPosition\":" + yInput +
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
