package graphics;

import javax.swing.*;
import java.awt.*;


public class ZooFrame extends JFrame {

    public ZooFrame() {
        super("Zoo");

        JMenuBar menuBar = new JMenuBar();

        JMenu tabFile = new JMenu("File");
        JMenu tabBackground = new JMenu("Background");
        JMenu tabHelp = new JMenu("Help");

        JMenuItem exitButton = new JMenuItem("Exit");
        JMenuItem noneButton = new JMenuItem("None");
        JMenuItem blackButton = new JMenuItem("Black");
        JMenuItem blueButton = new JMenuItem("Blue");
        JMenuItem cyanButton = new JMenuItem("Cyan");
        JMenuItem magentaButton = new JMenuItem("Magenta");
        JMenuItem greenButton = new JMenuItem("Green");
        JMenuItem helpButton = new JMenuItem("Help");

        tabFile.add(exitButton);
        tabBackground.add(noneButton);
        tabBackground.add(blackButton);
        tabBackground.add(blueButton);
        tabBackground.add(cyanButton);
        tabBackground.add(magentaButton);
        tabBackground.add(greenButton);
        tabHelp.add(helpButton);

        menuBar.add(tabFile);
        menuBar.add(tabBackground);
        menuBar.add(tabHelp);

        exitButton.addActionListener(e -> System.exit(0));
        noneButton.addActionListener(e -> ZooPanel.getInstance().setBackground(null));
        blackButton.addActionListener(e -> ZooPanel.getInstance().setBackground(Color.BLACK));
        blueButton.addActionListener(e -> ZooPanel.getInstance().setBackground(Color.BLUE));
        cyanButton.addActionListener(e -> ZooPanel.getInstance().setBackground(Color.CYAN));
        magentaButton.addActionListener(e -> ZooPanel.getInstance().setBackground(Color.MAGENTA));
        greenButton.addActionListener(e -> ZooPanel.getInstance().setBackground(Color.GREEN));
        helpButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "GUI Project"));

        this.add(menuBar, BorderLayout.PAGE_START);
        this.add(ZooPanel.getInstance());

        this.setSize(800, 700);
        this.setLocation(300,30);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public static void main(String[] args) { new ZooFrame(); }
}