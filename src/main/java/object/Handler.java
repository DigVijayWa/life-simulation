package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import utility.GameUtility;
import utility.Vector;

public class Handler {

  List<AutonomousObject> autonomousObjectList = new LinkedList<>();

  private Vector mouseTarget;

  private List<Vector> targets = new LinkedList<>();

  public Handler() {
    autonomousObjectList = IntStream.range(0, 4).mapToObj(item -> new AutonomousObject()).collect(
        Collectors.toList());

    targets = IntStream.range(0, 50).mapToObj(
        item -> new Vector(GameUtility.mapRandomValue(Math.random()),
            GameUtility.mapRandomValue(Math.random()))).collect(
        Collectors.toList());
  }

  public void render(Graphics2D graphics2D) {
    autonomousObjectList.forEach(item -> item.render(graphics2D, Color.CYAN));
    Color prevColor = graphics2D.getColor();
    graphics2D.setColor(Color.white);
    targets.forEach(item -> graphics2D.fillOval((int)item.getX(), (int)item.getY(), 5,5));
    graphics2D.setColor(prevColor);
  }

  public void update(double timePassed) {

    for (int i = autonomousObjectList.size() - 1; i >= 0; i--) {

      /* world is not prepared for greatness
      int finalI = i;
      Vector targetToSeek = targets.stream().reduce(new Vector(99999999, 99999999), (acc, item) ->
          item.distance(autonomousObjectList.get(finalI).getPosition()) < acc
              .distance(autonomousObjectList.get(finalI).getPosition()) ?
              item : acc
      );*/

      Vector targetToSeek = null;
      double minDist = 9999999;
      for(int k=targets.size()-1; k>=0; k--) {
        double distance = autonomousObjectList.get(i).getPosition().distance(targets.get(k));
        if(distance <= 3) {
          targets.remove(k);
          break;
        }
        if(distance < minDist) {
          minDist = distance;
          targetToSeek = targets.get(k);
        }
      }

      if (targetToSeek != null) {

        double scalar = GameUtility
            .mapRange(autonomousObjectList.get(i).getPosition().distance(targetToSeek),
                0, 500, AutonomousObject.minForce, AutonomousObject.maxSpeed);

        Vector velocity = seek(autonomousObjectList.get(i).getPosition(), targetToSeek,
            autonomousObjectList.get(i).getVelocity(), scalar);//AutonomousObject.maxSpeed);

        autonomousObjectList.get(i).update(timePassed, velocity);
      }

    }
  }

  public static Vector seek(Vector position, Vector target, Vector currentVelocity, double scalar) {
    Vector desired = GameUtility
        .calculateEffectiveVector(position, target)
        .normalize()
        .multiplyByScalar(scalar);

    return GameUtility
        .calculateEffectiveVector(currentVelocity, desired).limitVector(AutonomousObject.maxForce);
  }

  public Vector getMouseTarget() {
    return mouseTarget;
  }

  public void setMouseTarget(Vector mouseTarget) {
    this.mouseTarget = mouseTarget;
  }
}
