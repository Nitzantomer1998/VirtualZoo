package graphics;

import java.awt.*;


public interface IDrawable {

    String PICTURE_PATH = System.getProperty("user.dir").concat("\\Pictures\\");
    void loadImages(String imageName);
    void drawObject (Graphics g);
}
