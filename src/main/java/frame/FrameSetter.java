
package frame;

import app.App;
import javax.swing.JFrame;


public class FrameSetter {

  private JFrame jframe;

  public FrameSetter(int width, int height, App app) {
    this.jframe = new JFrame("GameModeOn");

    jframe.setSize(width, height);
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jframe.add(app);
    jframe.setVisible(true);

    app.start();
  }
}

