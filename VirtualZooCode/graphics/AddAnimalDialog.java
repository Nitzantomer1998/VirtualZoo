package graphics;

import DesignPatterns.DietFactory;
import animals.Animal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.*;
import java.awt.*;
import java.lang.*;


public class AddAnimalDialog extends JDialog {

    private final Vector<Animal> animalVector;
    private final Map<String, String[]> dietMap;
    private final JComboBox<String> dietTypeComboBox;
    private final JComboBox<String> animalTypeComboBox;
    private final JComboBox<String> animalColorComboBox;
    private final JTextField animalNameTextField;
    private final JSlider animalSizeSlider;
    private final JSlider horSpeedSlider;
    private final JSlider verSpeedSlider;
    private final JLabel chosenDietLabel;
    private final JLabel chosenAnimalLabel;
    private final JLabel chosenColorLabel;
    private final JLabel animalNameLabel;
    private final JLabel animalSizeLabel;
    private final JLabel horSpeedLabel;
    private final JLabel verSpeedLabel;


    public AddAnimalDialog(Vector<Animal> animalVector) {
        this.setTitle("Creating An Animal");
        this.setLayout(new GridLayout(0,2, 30,20));

        this.animalVector = animalVector;

        this.dietMap = new LinkedHashMap<>();
        this.dietMap.put("Carnivore", new String[]{ "Lion" });
        this.dietMap.put("Herbivore", new String[]{ "Elephant", "Giraffe", "Turtle" });
        this.dietMap.put("Omnivore", new String[]{ "Bear" });

        this.dietTypeComboBox = new JComboBox<>(new String[]{ "Carnivore", "Herbivore", "Omnivore" });
        this.chosenDietLabel = new JLabel("Diet Type: Carnivore");
        this.add(this.chosenDietLabel);
        this.add(this.dietTypeComboBox);
        this.dietTypeComboBox.addActionListener(e -> this.updateDietTypeComboBox());

        this.animalTypeComboBox = new JComboBox<>(dietMap.get((String) this.dietTypeComboBox.getSelectedItem()));
        this.chosenAnimalLabel = new JLabel("Animal Type: " + dietMap.get((String) this.dietTypeComboBox.getSelectedItem())[0]);
        this.add(this.chosenAnimalLabel);
        this.add(this.animalTypeComboBox);
        this.animalTypeComboBox.addActionListener(e -> this.updateAnimalLabel());

        this.animalColorComboBox = new JComboBox<>(new String[]{ "Natural", "Red", "Blue" });
        this.chosenColorLabel = new JLabel("Animal Color: Natural");
        this.add(this.chosenColorLabel);
        this.add(this.animalColorComboBox);
        this.animalColorComboBox.addActionListener(e -> this.updateColorLabel());

        this.animalNameTextField = new JTextField("");
        this.animalNameLabel = new JLabel("Animal Name: ");
        this.add(this.animalNameLabel);
        this.add(this.animalNameTextField);
        this.animalNameTextField.addActionListener(e -> this.updateNameLabel());

        this.animalSizeSlider = new JSlider(50, 300, 175);
        this.animalSizeSlider.setMajorTickSpacing(50);
        this.animalSizeSlider.setPaintLabels(true);
        this.animalSizeLabel = new JLabel ("Animal Size: " + this.animalSizeSlider.getValue());
        this.add(this.animalSizeLabel);
        this.add(this.animalSizeSlider);
        this.animalSizeSlider.addChangeListener(e -> this.updateSizeLabel());

        this.horSpeedSlider = new JSlider(1, 10, 5);
        this.horSpeedSlider.setMajorTickSpacing(3);
        this.horSpeedSlider.setPaintLabels(true);
        this.horSpeedLabel = new JLabel("Horizontal Speed: " + this.horSpeedSlider.getValue());
        this.add(this.horSpeedLabel);
        this.add(this.horSpeedSlider);
        this.horSpeedSlider.addChangeListener(e -> this.updateHorSpeedLabel());

        this.verSpeedSlider = new JSlider(1,10,5);
        this.verSpeedSlider.setMajorTickSpacing(3);
        this.verSpeedSlider.setPaintLabels(true);
        this.verSpeedLabel = new JLabel("Vertical Speed: " + this.verSpeedSlider.getValue());
        this.add(this.verSpeedLabel);
        this.add(this.verSpeedSlider);
        this.verSpeedSlider.addChangeListener(e -> this.updateVerSpeedLabel());

        JButton addAnimalButton = new JButton("Add Animal");
        this.add(addAnimalButton);
        addAnimalButton.addActionListener(e -> this.addingAnimal());

        this.setSize(500,400);
        this.setLocation(450,180);
        this.setResizable(false);
        this.setVisible(true);
    }


    private void updateAnimalLabel() { this.chosenAnimalLabel.setText("Animal Type: " + this.animalTypeComboBox.getSelectedItem()); }
    private void updateColorLabel() { this.chosenColorLabel.setText("Animal Color: " + this.animalColorComboBox.getSelectedItem()); }
    private void updateNameLabel() { this.animalNameLabel.setText("Animal Name: " + this.animalNameTextField.getText()); }
    private void updateSizeLabel() { this.animalSizeLabel.setText("Animal Size: " + this.animalSizeSlider.getValue()); }
    private void updateHorSpeedLabel() { this.horSpeedLabel.setText("Horizontal Speed: " + this.horSpeedSlider.getValue()); }
    private void updateVerSpeedLabel() { this.verSpeedLabel.setText("Vertical Speed: " + this.verSpeedSlider.getValue()); }
    private void updateDietTypeComboBox() {
        this.chosenDietLabel.setText("Diet Type: " + this.dietTypeComboBox.getSelectedItem());

        this.animalTypeComboBox.removeAllItems();
        for (String animal : dietMap.get((String) this.dietTypeComboBox.getSelectedItem()))
            this.animalTypeComboBox.addItem(animal);
    }
    private void addingAnimal() {
        String diet = (String) this.dietTypeComboBox.getSelectedItem();
        String type = (String) this.animalTypeComboBox.getSelectedItem();
        String color = (String) this.animalColorComboBox.getSelectedItem();
        String name = this.animalNameTextField.getText();
        int size = this.animalSizeSlider.getValue();
        int horizontalSpeed = this.horSpeedSlider.getValue();
        int verticalSpeed = this.verSpeedSlider.getValue();

        boolean isNameAlreadyTaken = false;
        for (Animal animal : this.animalVector)
            if (animal.getName().equalsIgnoreCase(name)) {
                isNameAlreadyTaken = true;
                break;
            }

        if ((!isNameAlreadyTaken) && (diet != null)) {
            dispose();

            this.animalVector.addElement(new DietFactory().getFactory(diet).createAnimal(type, name, size, horizontalSpeed, verticalSpeed, color));
            ZooPanel.getInstance().getThreadPool().addToThreadPool(this.animalVector.lastElement());

            if (animalVector.size() > 10)
                JOptionPane.showMessageDialog(this, "This animal enter to the waiting queue", "Message", JOptionPane.INFORMATION_MESSAGE);
        }

        else
            JOptionPane.showMessageDialog(this, "Animal name already taken, try Again", "Message", JOptionPane.ERROR_MESSAGE);
    }
}