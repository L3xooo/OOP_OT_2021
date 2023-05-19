package sk.stuba.fei.uim.oop.board;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.shapes.House;
import sk.stuba.fei.uim.oop.shapes.MyShape;
import sk.stuba.fei.uim.oop.shapes.Road;
import sk.stuba.fei.uim.oop.shapes.Tree;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
public class Board extends JPanel {
    ArrayList<MyShape> shapes;
    MyShape activeShape;
    public static final float OPACITY = 0.5f;

    public Board(){
        this.activeShape = null;
        this.shapes = new ArrayList<>();
        this.setBackground(Color.cyan);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        super.paintComponent(g);
        for(MyShape shape : shapes) {
            shape.draw(g);
        }
        if (activeShape != null && activeShape instanceof Road) {
            activeShape.draw(g);
        }
        if (activeShape != null && (activeShape instanceof Tree || activeShape instanceof House)) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, OPACITY));
            activeShape.draw(g2d);
        }
    }
}
