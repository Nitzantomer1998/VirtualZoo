package graphics;

import DesignPatterns.AnimalColorDecorator;
import animals.Animal;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;


/**
 * a Class that defines GUI Dialog in order to change animal color, inherit from JDialog
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see JDialog
 */
public class ChangeColorDialog extends JDialog{

    private final Vector<Animal> animalVector;
    private final JComboBox<String> existedAnimalComboBox;
    private final JComboBox<String> animalColorComboBox;
    private final JLabel chosenAnimalLabel;
    private final JLabel chosenColorLabel;


    /**
     * The constructor of the ChangeColorDialog object, change animal color from our zoo using GUI JDialog
     *
     * @param animalVector is a Vector representing all the animals in the zoo
     */
    public ChangeColorDialog(Vector<Animal> animalVector) {
        this.setTitle("Changing Animal Color");
        this.setLayout(new GridLayout(0,2, 30,20));

        this.animalVector = animalVector;

        this.existedAnimalComboBox = new JComboBox<>();
        for (Animal animal : this.animalVector)
            this.existedAnimalComboBox.addItem(animal.getName());
        this.chosenAnimalLabel = new JLabel("Animal Name: " + this.animalVector.get(0).getName());
        this.add(this.chosenAnimalLabel);
        this.add(this.existedAnimalComboBox);
        this.existedAnimalComboBox.addActionListener(e -> this.updateExistedAnimalComboBox());

        this.animalColorComboBox = new JComboBox<>(new String[]{ "Natural", "Red", "Blue" });
        this.animalColorComboBox.setSelectedItem(this.animalVector.get(this.existedAnimalComboBox.getSelectedIndex()).getColor());
        this.chosenColorLabel = new JLabel("Animal Color: " + this.animalVector.get(this.existedAnimalComboBox.getSelectedIndex()).getColor());
        this.add(this.chosenColorLabel);
        this.add(this.animalColorComboBox);
        this.animalColorComboBox.addActionListener(e -> this.updateColorLabel());

        JButton changeColorButton = new JButton("Change Color");
        this.add(changeColorButton);
        changeColorButton.addActionListener(e -> this.changeColor());

        this.setSize(500,150);
        this.setLocation(450,305);
        this.setResizable(false);
        this.setVisible(true);
    }


    /**
     * Helpful methods in order to update the JDialog accordingly to the action preformed by the user
     */
    private void updateColorLabel() { this.chosenColorLabel.setText("Animal Color: " + this.animalColorComboBox.getSelectedItem()); }
    private void updateExistedAnimalComboBox() {
        this.chosenAnimalLabel.setText("Animal Name: " + this.existedAnimalComboBox.getSelectedItem());
        this.animalColorComboBox.setSelectedItem(this.animalVector.get(this.existedAnimalComboBox.getSelectedIndex()).getColor());
    }
    private void changeColor() {
        Animal animal = this.animalVector.get(this.existedAnimalComboBox.getSelectedIndex());
        String color = (String) this.animalColorComboBox.getSelectedItem();

        if (!animal.getColor().equalsIgnoreCase(color))
            new AnimalColorDecorator(this.animalVector.get(this.animalVector.indexOf(animal))).PaintAnimal(color);

        dispose();
    }
}
