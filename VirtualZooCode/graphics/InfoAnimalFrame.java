package graphics;

import animals.Animal;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;


public class InfoAnimalFrame extends JFrame {

    public InfoAnimalFrame (Vector<Animal> animalVector) {
        super("Zoo Information");

        Object[][] data = new Object[(animalVector.size() <= 10) ? animalVector.size() + 1 : animalVector.size() + 3][7];
        int totalEatCounter = 0;

        for (int i = 0; Math.min(10, animalVector.size()) > i; i++) {
            data[i][0] = animalVector.get(i).getName();
            data[i][1] = animalVector.get(i).getColor();
            data[i][2] = animalVector.get(i).getSize();
            data[i][3] = animalVector.get(i).getWeight();
            data[i][4] = animalVector.get(i).getHorizontalSpeed();
            data[i][5] = animalVector.get(i).getVerticalSpeed();
            data[i][6] = animalVector.get(i).getEatCounter();
            totalEatCounter = totalEatCounter + animalVector.get(i).getEatCounter();

            data[i + 1][0] = "Total: ";
            data[i + 1][6] = totalEatCounter;
        }

        if (animalVector.size() > 10) {
            data[12][0] = "Animals";
            data[12][1] = "that";
            data[12][2] = "currently";
            data[12][3] = "waiting";
            data[12][4] = "in";
            data[12][5] = "queue";

            for (int i = 10; Math.min(15, animalVector.size()) > i; i++) {
                data[i + 3][0] = animalVector.get(i).getName();
                data[i + 3][1] = animalVector.get(i).getColor();
                data[i + 3][2] = animalVector.get(i).getSize();
                data[i + 3][3] = animalVector.get(i).getWeight();
                data[i + 3][4] = animalVector.get(i).getHorizontalSpeed();
                data[i + 3][5] = animalVector.get(i).getVerticalSpeed();
                data[i + 3][6] = "";
            }
        }

        JTable table = new JTable(data, new String[]{ "Animal", "Color", "Size", "Weight", "Hor. Speed", "Ver. Speed", "Eat Counter" });
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        panel.add(table, BorderLayout.CENTER);

        this.add(panel);
        this.setSize(500, 350);
        this.setLocation(450,205);
        this.setResizable(false);
        this.setVisible(true);
    }
}