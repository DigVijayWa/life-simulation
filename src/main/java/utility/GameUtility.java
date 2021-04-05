package utility;

import java.awt.Color;
import object.AutonomousObject;

public class GameUtility {

  public static long getTimeInSeconds(long timePassed) {
    return (long) (timePassed / 1000_000_000.0);
  }

  public static long getTimeInMiliSeconds(long timePassed) {
    return (long) (timePassed / 1000_000.0);
  }

  public static double mapRandomValue(double randomValue) {
    return 0 + ((640)) * (randomValue - 0);
  }

  public static double mapRange(double input, double inputStart, double inputEnd, double outputStart,
      double outputEnd) {
    if (input > inputEnd) {
      return AutonomousObject.minForce;
    }
    return outputStart + ((outputEnd - outputStart) / (inputEnd - inputStart)) * (input - inputStart);
  }

  public static Vector calculateEffectiveVector(Vector currentPosition, Vector targetPosition) {
    return targetPosition.subtractionVector(currentPosition);
  }

  public static double calculateScalarOnDistance(double distance, double maxVelocity) {
    //
    if (distance > AutonomousObject.attractionDistance) {
      return AutonomousObject.minForce;
    }
    return maxVelocity + ((AutonomousObject.minForce - maxVelocity) / AutonomousObject.attractionDistance) * (distance);
  }

  public static Color getColorOnSpeed(double magnitude) {
    if(magnitude < 60){
      return new Color(50,255,150);
    }else if(magnitude < 70){
      return new Color(155,184,255);
    }else if(magnitude < 80){
      return new Color(0,85,255);
    }else if(magnitude < 90){
      return new Color(0,144,255);
    }else if(magnitude < 100) {
      return new Color(0,120,255);
    }else if(magnitude < 150) {
      return new Color(189,0,255);
    } else if(magnitude < 160) {
      return new Color(255,154,0);
    } else if(magnitude < 170) {
      return new Color(1,255,31);
    } else if(magnitude < 180) {
      return new Color(227,255,0);
    } else if(magnitude < 190){
      return new Color(13,0,255);
    }else if(magnitude < 200){
      return new Color(85,23,255);
    }else{
      return new Color(101,100,2);
    }
  }
}
