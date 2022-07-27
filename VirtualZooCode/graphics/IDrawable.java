package graphics;

import java.awt.*;


/**
 * Interface to describe the draw functionality
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 */
public interface IDrawable {

    String PICTURE_PATH = System.getProperty("user.dir").concat("\\Pictures\\");
    void loadImages(String imageName);
    void drawObject (Graphics g);
}
