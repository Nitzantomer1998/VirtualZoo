package designPatterns;

import graphics.ZooPanel;
import java.util.Observable;
import java.util.Observer;


public class Controller implements Observer {

    public Controller() {}


    @Override
    public void update(Observable o, Object arg) {
        ZooPanel.getInstance().manageZoo();
    }
}
