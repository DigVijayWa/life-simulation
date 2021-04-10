package object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Double;
import java.util.Arrays;
import java.util.List;
import utility.GameUtility;
import utility.Vector;

public class AutonomousObject {

  private Vector position;
  private Vector accelaration;
  private Vector wind;
  private Vector velocity;

  private Vector gravity;

  private int size;

  private Vector[] vertices = new Vector[3];

  private boolean fixed = false;

  public static double maxSpeed = 200;

  public static double maxForce = 10;

  public static double attractionDistance = 100;

  public static double minForce = 80;

  private Path2D objectPath = new Double();

  private int eatenParticles = 0;

  private String name;

  private static List<String> names;
  static {
    names = GameUtility.readNames().orElse(null);
  }

  public AutonomousObject() {

    double offSet = GameUtility.mapRandomValue(Math.random());
    vertices[0] = new Vector(offSet, offSet);
    vertices[1] = new Vector(5*1.5+offSet, 19.319*1.5+offSet);
    vertices[2] = new Vector(10*1.5+offSet, offSet);

    position = calculateCentroid();

    calculatePath();

    accelaration = new Vector(0, 0);
    velocity = new Vector(0, 0);
    gravity = new Vector(0, 5);
    size = 2;

    name = names.get((int)GameUtility.mapRange(Math.random(), 0,1,0, names.size()-1));
  }

  public void render(Graphics2D graphics2D, Color color) {
    Color prevColor = graphics2D.getColor();
    graphics2D.setColor(color);
    graphics2D.setFont(new Font("Monospace", Font.PLAIN, 12));
    graphics2D.drawString(this.getName(), (int)vertices[1].getX()+3, (int)vertices[1].getY()+3);
    graphics2D.rotate( velocity.getAngle() - Math.PI/2, position.getX(), position.getY());
    graphics2D.draw(objectPath);
    graphics2D.rotate( -(velocity.getAngle() - Math.PI/2), position.getX(), position.getY());
    graphics2D.setColor(prevColor);
    objectPath.reset();
  }

  public void update(double timePassed, Vector accelaration) {
    if (!fixed) {

      double timePassedSeconds = 0.01666666666;
      this.accelaration = accelaration;
      velocity = velocity.additionVector(this.accelaration);
      velocity = velocity.limitVector(maxSpeed);

      updatePath(velocity, timePassedSeconds);
      calculatePath();
      position = calculateCentroid();

      accelaration.setXandY(0, 0);
    }
  }

  public void updatePath(Vector velocity, double timePassed) {
    vertices = Arrays.stream(vertices).map(item -> item.additionVector(velocity, timePassed))
        .toArray(Vector[]::new);
  }

  public void calculatePath() {
    objectPath.moveTo(vertices[0].getX(), vertices[0].getY());

    objectPath.lineTo(vertices[1].getX(), vertices[1].getY());
    objectPath.lineTo(vertices[2].getX(), vertices[2].getY());

    objectPath.closePath();
  }

  public Vector calculateCentroid() {

    return Arrays.stream(vertices).reduce(new Vector(0, 0), (item, acc) -> acc.additionVector(item))
        .divideByScalar(3);

  }

  public Vector getPosition() {
    return position;
  }

  public void setPosition(Vector position) {
    this.position = position;
  }

  public Vector getAccelaration() {
    return accelaration;
  }

  public void setAccelaration(Vector accelaration) {
    this.accelaration = accelaration;
  }

  public Vector getWind() {
    return wind;
  }

  public void setWind(Vector wind) {
    this.wind = wind;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public Vector getVelocity() {
    return velocity;
  }

  public void setVelocity(Vector velocity) {
    this.velocity = velocity;
  }

  public boolean isFixed() {
    return fixed;
  }

  public void setFixed(boolean fixed) {
    this.fixed = fixed;
  }

  public Vector getGravity() {
    return gravity;
  }

  public void setGravity(Vector gravity) {
    this.gravity = gravity;
  }

  public double getMaxSpeed() {
    return maxSpeed;
  }

  public void setMaxSpeed(double maxSpeed) {
    this.maxSpeed = maxSpeed;
  }

  public double getMaxForce() {
    return maxForce;
  }

  public void setMaxForce(double maxForce) {
    this.maxForce = maxForce;
  }

  public Vector[] getVertices() {
    return vertices;
  }

  public void setVertices(Vector[] vertices) {
    this.vertices = vertices;
  }

  public int getEatenParticles() {
    return eatenParticles;
  }

  public void setEatenParticles(int eatenParticles) {
    this.eatenParticles = eatenParticles;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

