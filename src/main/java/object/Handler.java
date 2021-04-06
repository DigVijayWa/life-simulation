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

  public Handler() {
    autonomousObjectList = IntStream.range(0, 1).mapToObj(item -> new AutonomousObject()).collect(
        Collectors.toList());
  }

  public void render(Graphics2D graphics2D) {
    autonomousObjectList.forEach(item -> item.render(graphics2D, Color.CYAN));
  }

  public void update(double timePassed) {

    for (int i = autonomousObjectList.size() - 1; i >= 0; i--) {

      if (mouseTarget != null) {

        double scalar = GameUtility
            .mapRange(autonomousObjectList.get(i).getPosition().distance(mouseTarget),
                0, 100, AutonomousObject.minForce, AutonomousObject.maxSpeed);

        Vector velocity = seek(autonomousObjectList.get(i).getPosition(), mouseTarget,
            autonomousObjectList.get(i).getVelocity(), scalar);

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
