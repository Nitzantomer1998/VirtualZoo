package graphics;

import DesignPatterns.Controller;
import DesignPatterns.ThreadPool;
import DesignPatterns.ZooMemento;
import animals.Animal;
import foodSupply.AvailableFood;
import foodSupply.Cabbage;
import foodSupply.Lettuce;
import foodSupply.Meat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Vector;


/**
 * a Class that defines the GUI panels of the program, inherit from JPanel
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see JPanel
 */
public class ZooPanel extends JPanel {

    private static volatile ZooPanel instance = null;
    private volatile AvailableFood availableFood;
    private final ThreadPool threadPool;
    private final Controller controller;
    private final ArrayList<ZooMemento> mementoArrayList;
    private final Vector<Animal> animalVector;


    /**
     * The constructor of the ZooPanel object, Sets the fields of the object
     * Note : ZooPanel contain two panels, one for control buttons, and the other for visual board
     */
    private ZooPanel() {
        this.setLayout(new BorderLayout());

        this.availableFood = null;
        this.animalVector = new Vector<>();
        this.mementoArrayList = new ArrayList<>();

        JPanel controlPanel = new JPanel(new GridLayout(0,5,5,5));
        JButton addAnimalButton = new JButton("Add Animal");
        JButton saveStateButton = new JButton("Save State");
        JButton foodButton = new JButton("Food");
        JButton wakeUpButton = new JButton("Wake up");
        JButton clearButton = new JButton("Clear");
        JButton changeColorButton = new JButton("Change Color");
        JButton restoreStateButton = new JButton("Restore State");
        JButton infoButton = new JButton("Info");
        JButton sleepButton = new JButton("Sleep");
        JButton exitButton = new JButton("Exit");

        addAnimalButton.setFocusable(false);
        saveStateButton.setFocusable(false);
        foodButton.setFocusable(false);
        wakeUpButton.setFocusable(false);
        clearButton.setFocusable(false);
        changeColorButton.setFocusable(false);
        restoreStateButton.setFocusable(false);
        infoButton.setFocusable(false);
        sleepButton.setFocusable(false);
        exitButton.setFocusable(false);

        addAnimalButton.addActionListener(e -> this.addAnimal());
        saveStateButton.addActionListener(e -> this.saveState());
        foodButton.addActionListener(e -> this.addFood());
        wakeUpButton.addActionListener(e -> this.wakeupAll());
        clearButton.addActionListener(e -> this.clearPanel());
        changeColorButton.addActionListener(e -> this.changeColor());
        restoreStateButton.addActionListener(e -> this.restoreState());
        infoButton.addActionListener(e -> this.showInfo());
        sleepButton.addActionListener(e -> this.goSleep());
        exitButton.addActionListener(e -> System.exit(0));

        controlPanel.setBackground(Color.CYAN);
        controlPanel.add(addAnimalButton);
        controlPanel.add(saveStateButton);
        controlPanel.add(foodButton);
        controlPanel.add(wakeUpButton);
        controlPanel.add(clearButton);
        controlPanel.add(changeColorButton);
        controlPanel.add(restoreStateButton);
        controlPanel.add(infoButton);
        controlPanel.add(sleepButton);
        controlPanel.add(exitButton);

        this.add(controlPanel, BorderLayout.PAGE_END);
        this.threadPool = new ThreadPool();
        this.controller = new Controller();
    }


    /**
     * Static method for ensuring there's maximum of one instance type of this class,
     * If there's no instance it will create one, else it will return the existing instance,
     * Note: Singleton DP + little tweaks
     *
     * @return Instance of this class
     */
    public static ZooPanel getInstance() {
        if (ZooPanel.instance == null)
            synchronized (ZooPanel.class) {
                if (ZooPanel.instance == null)
                    ZooPanel.instance = new ZooPanel();
            }

        return ZooPanel.instance;
    }


    /**
     * Getter methods for the fields of the object / class
     *
     * @return The wanted field
     */
    public AvailableFood getAvailableFood() { return this.availableFood; }
    public ThreadPool getThreadPool() { return this.threadPool; }
    public Observer getController() { return this.controller; }


    /**
     * Boolean methods for checking the fields condition of the object / class
     *
     * @return true or false depend on the condition
     */
    public boolean isAvailableFood() { return this.availableFood != null; }


    /**
     * Helpful methods in order to update our program accordingly to the action preformed by the user
     */
    private void addAnimal() {
        if (this.animalVector.size() < 15)
            synchronized (this.animalVector) {
                if (this.animalVector.size() < 15)
                    new AddAnimalDialog(this.animalVector);
            }

        else
            JOptionPane.showMessageDialog(this, "Zoo capacity in his full", "Message", JOptionPane.ERROR_MESSAGE);
    }
    private void saveState() {
        if (this.mementoArrayList.size() == 3)
            synchronized (this.mementoArrayList) {
                if (this.mementoArrayList.size() == 3)
                    this.mementoArrayList.remove(0);
            }

        this.mementoArrayList.add(new ZooMemento(this.animalVector, this.availableFood, this.getBackground()));
    }
    private void addFood() {
        synchronized (AvailableFood.class) {
            int option = JOptionPane.showOptionDialog(this, "Add food", "Animal food", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Lettuce", "Cabbage", "Meat"}, null);

            if (option == 0)
                this.availableFood = Lettuce.getInstance();

            else if (option == 1)
                this.availableFood = Cabbage.getInstance();

            else if (option == 2)
                this.availableFood = Meat.getInstance();
        }

        repaint();
    }
    private void wakeupAll() {
        if (this.animalVector.size() > 0)
            synchronized (this.animalVector) {
                if (this.animalVector.size() > 0)
                    for (Animal animal : this.animalVector)
                        animal.setThreadResumed();
            }
    }
    private void clearPanel() {
        if (this.animalVector.size() > 0)
            synchronized (this.animalVector) {
                if (this.animalVector.size() > 0)
                    this.animalVector.removeAllElements();
            }
        this.availableFood = null;
    }
    private void changeColor() {
        if (this.animalVector.size() > 0)
            synchronized (this.animalVector) {
                if (this.animalVector.size() > 0)
                    new ChangeColorDialog(this.animalVector);
            }

        else
            JOptionPane.showMessageDialog(this, "The zoo is empty", "Message", JOptionPane.ERROR_MESSAGE);

    }
    private void restoreState() {
        if (this.mementoArrayList.size() > 0)
            synchronized (this.mementoArrayList) {
                if (this.mementoArrayList.size() > 0) {
                    ZooMemento restoredState = this.mementoArrayList.remove(this.mementoArrayList.size() - 1);

                    this.threadPool.cleanThreadPool();
                    this.setBackground(restoredState.getBackgroundBackUp());
                    this.availableFood = restoredState.getAvailableFoodBackUp();

                    this.animalVector.removeAllElements();
                    for (Animal animal : restoredState.getAnimalVectorBackUp()) {
                        this.animalVector.addElement(animal);
                        this.threadPool.addToThreadPool(animal);
                    }
                }
            }

        else
            JOptionPane.showMessageDialog(this, "Back up slots are empty.", "Message", JOptionPane.ERROR_MESSAGE);
    }
    private void showInfo() {
        if (this.animalVector.size() > 0)
            synchronized (this.animalVector) {
                if (this.animalVector.size() > 0)
                    new InfoAnimalFrame(this.animalVector);
            }

        else
            JOptionPane.showMessageDialog(this, "The zoo is empty", "Message", JOptionPane.ERROR_MESSAGE);
    }
    private void goSleep() {
        if (this.animalVector.size() > 0)
            synchronized (this.animalVector) {
                if (this.animalVector.size() > 0)
                    for (Animal animal : this.animalVector)
                        animal.setThreadSuspended();
            }
    }


    /**
     * Printing all the components to our frame
     *
     * @param g the graphics context.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < Math.min(10, animalVector.size()); i++)
            animalVector.get(i).drawObject(g);

        if (this.availableFood != null)
            this.availableFood.drawObject(g);
    }


    /**
     * The controller for the program, follow after all the user actions and update the program accordingly
     */
    public void manageZoo() {
        synchronized (this.animalVector) {
            for (int i = 0; i < Math.min(10, animalVector.size()); i++) {
                for (int j = 0; j < Math.min(10, animalVector.size()); j++)
                    if (i != j)
                        if (this.animalVector.get(i).getDiet().canEat(this.animalVector.get(j).getFoodType()))
                            if (this.animalVector.get(i).getWeight() >= this.animalVector.get(j).getWeight() * 2)
                                if (Math.abs(this.animalVector.get(i).getLocation().getX() - this.animalVector.get(j).getLocation().getX()) <= this.animalVector.get(i).getEAT_DISTANCE())
                                    if (Math.abs(this.animalVector.get(i).getLocation().getY() - this.animalVector.get(j).getLocation().getY()) <= this.animalVector.get(i).getEAT_DISTANCE())
                                        if (!this.animalVector.get(i).isThreadSuspended()) {
                                            this.animalVector.get(j).setThreadSuspended();
                                            this.animalVector.get(i).eat(this.animalVector.get(j));
                                            this.animalVector.removeElement(this.animalVector.get(j));
                                            return;
                                        }
            }

            if (this.availableFood != null)
                synchronized (AvailableFood.class) {
                    if (this.availableFood != null)
                        for (int i = 0; i < Math.min(10, animalVector.size()); i++)
                            if (this.animalVector.get(i).getDiet().canEat(this.availableFood.getFoodType()))
                                if (Math.abs(this.animalVector.get(i).getLocation().getX() - this.availableFood.getLocation().getX()) <= this.animalVector.get(i).getEAT_DISTANCE())
                                    if (Math.abs(this.animalVector.get(i).getLocation().getY() - this.availableFood.getLocation().getY()) <= this.animalVector.get(i).getEAT_DISTANCE())
                                        if (!this.animalVector.get(i).isThreadSuspended()) {
                                            this.animalVector.get(i).eat(this.availableFood);
                                            this.availableFood = null;
                                            return;
                                        }
                }

            repaint();
        }
    }
}