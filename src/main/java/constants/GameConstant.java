package constants;

public class GameConstant {
  public static final int GAME_WIDTH = 800;
  public static final int GAME_HEIGHT = 800;

  public static final int WORLD_WIDTH = 3000;
  public static final int WORLD_HEIGHT = 3000;

  public static final int VIEW_PORT_SIZE_X = 800;
  public static final int VIEW_PORT_SIZE_Y = 800;

  public static final int OFFSET_MAX_X = WORLD_WIDTH - VIEW_PORT_SIZE_X;
  public static final int OFFSET_MAX_Y = WORLD_HEIGHT - VIEW_PORT_SIZE_Y;

  public static final int OFFSET_MIN_X = 0;
  public static final int OFFSET_MIN_Y = 0;

  public static final double forceX = 20;
  public static final double forceY = 20;

  public static final double angleChange = Math.PI / 16;

  public static final double magnitudeChange = 5;
}
