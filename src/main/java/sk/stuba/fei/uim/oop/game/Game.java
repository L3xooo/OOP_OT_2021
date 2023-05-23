package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.logic.Logic;

import javax.swing.*;
import java.awt.*;

public class Game {
    public Game() {
        JFrame frame = new JFrame("Game");
        frame.setSize(700,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Logic logic = new Logic();

        JButton tree = new JButton("Tree");
        tree.addActionListener(logic);
        JButton house = new JButton("House");
        house.addActionListener(logic);
        JButton road = new JButton("Road");
        road.addActionListener(logic);

        JPanel menu = new JPanel();
        menu.setBackground(Color.lightGray);
        menu.setLayout(new GridLayout(1,4));
        menu.add(tree);
        menu.add(house);
        menu.add(road);
        menu.add(logic.getActiveStatusLabel());

        frame.add(logic.getBoard());
        frame.add(menu,BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
