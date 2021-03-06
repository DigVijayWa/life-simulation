package object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import utility.GameUtility;
import utility.Vector;

public class Handler {

  List<AutonomousObject> autonomousObjectList;

  private Vector mouseTarget;

  private List<Vector> targets;

  private int blockHeight = 5;
  private int blockWidth = 65;
  private int scorePosition = 800 - 60;

  public Handler() {
    autonomousObjectList = IntStream.range(0, 5).mapToObj(item -> new AutonomousObject()).collect(
        Collectors.toList());

    targets = IntStream.range(0, 65).mapToObj(
        item -> new Vector(GameUtility.mapRandomValue(Math.random()),
            GameUtility.mapRandomValue(Math.random()))).collect(
        Collectors.toList());
  }

  public void render(Graphics2D graphics2D) {
    final int offset = 10;
    for (int index = 0; index < autonomousObjectList.size(); index++) {

      AutonomousObject item = autonomousObjectList.get(index);
      item.render(graphics2D, Color.CYAN);
      graphics2D.setColor(Color.white);
      graphics2D.setFont(new Font("Monospace", Font.PLAIN, 12));
      graphics2D.drawString(item.getName(), (index * blockWidth + offset), scorePosition + 10);

      graphics2D.drawString(""+item.getEatenParticles(),index*blockWidth+offset, scorePosition - 15);
      /*for (int k = 0; k < item.getEatenParticles(); k++) {
        graphics2D.drawRect(index * blockWidth + offset, scorePosition - 15 - (k * blockHeight),
            blockWidth, blockHeight);
      }*/
    }
    Color prevColor = graphics2D.getColor();
    graphics2D.setColor(Color.white);

    targets.forEach(item -> graphics2D.fillOval((int) item.getX(), (int) item.getY(), 5, 5));
    graphics2D.setColor(prevColor);
  }

  public void update(double timePassed) {

    for (int i = autonomousObjectList.size() - 1; i >= 0; i--) {

      int finalI = i;

      targets = targets.stream().filter(item ->
          !(item.distance(autonomousObjectList.get(finalI).getPosition()) < 3
              &&
              autonomousObjectList.get(finalI)
                  .setEatenParticles(autonomousObjectList.get(finalI).getEatenParticles() + 1) >= 0)
      ).collect(Collectors.toList());


      Vector targetToSeek = targets.stream().reduce(new Vector(99999999, 99999999), (acc, item) ->
          item.distance(autonomousObjectList.get(finalI).getPosition()) < acc
              .distance(autonomousObjectList.get(finalI).getPosition()) ?
              item : acc
      );


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
