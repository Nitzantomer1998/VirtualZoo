package designPatterns;

import graphics.ZooPanel;
import java.util.Observable;
import java.util.Observer;


/**
 * a Class that defines the Observer Design pattern,
 * Using the observer in order to update the main frame everytime something has changed
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see mobility.Mobile
 */
public class Controller implements Observer {

    /**
     * The constructor of the Controller object, Sets the fields of the object
     */
    public Controller() {}


    /**
     * Method to update all the observers, in our case update the program frame
     * @param o     the observable object.
     * @param arg   an argument passed to the method.
     */
    @Override
    public void update(Observable o, Object arg) {
        ZooPanel.getInstance().manageZoo();
    }
}
