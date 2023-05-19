package sk.stuba.fei.uim.oop.logic;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.shapes.House;
import sk.stuba.fei.uim.oop.shapes.MyShape;
import sk.stuba.fei.uim.oop.shapes.Road;
import sk.stuba.fei.uim.oop.shapes.Tree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

@Getter
@Setter
public class Logic extends UniversalAdapter {
    private static final String TREE_BUTTON_NAME = "Tree";
    private static final String HOUSE_BUTTON_NAME = "House";
    private static final String ROAD_BUTTON_NAME = "Road";
    private JFrame frame;
    private Board board;
    private JLabel activeStatusLabel;
    private Color activeColor;
    private ArrayList<Color> colors;
    private MyShape activeShape;
    private MyShape startShape;
    public Logic(JFrame frame) {
        this.frame = frame;
        initBoard();
        initColors();
        frame.add(board);
        this.activeStatusLabel = new JLabel();
        this.activeStatusLabel.setOpaque(true);
        this.getActiveStatusLabel().setForeground(Color.white);
        updateActiveStatusLabel("sadasda");
    }

    private void initBoard(){
        board = new Board();
        board.addMouseListener(this);
        board.addMouseMotionListener(this);
    }

    private void updateColor(){
        int colorIndex = this.getColors().indexOf(this.getActiveColor());
        if (colorIndex+1 >= this.getColors().size()) {
            colorIndex = 0;
        } else {
            colorIndex +=1;
        }
        this.setActiveColor(this.getColors().get(colorIndex));
        this.updateActiveStatusLabelBackground();
    }


    private void initColors(){
        this.colors = new ArrayList<>();
        this.colors.add(Color.BLUE);
        this.colors.add(Color.BLACK);
        this.colors.add(Color.RED);
        this.setActiveColor(colors.get(0));
    }

    public void updateActiveStatusLabel(String text){
        this.getActiveStatusLabel().setText(text);
        this.updateActiveStatusLabelBackground();
    }
    private void updateActiveStatusLabelBackground() {
        this.getActiveStatusLabel().setBackground(this.getActiveColor());
        this.getBoard().revalidate();
        this.getBoard().repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton){
            if (((JButton)source).getText().equals(TREE_BUTTON_NAME)) {
                updateActiveStatusLabel("Tree");
                this.setActiveShape(new Tree());
            }
            if (((JButton)source).getText().equals(HOUSE_BUTTON_NAME)) {
                updateActiveStatusLabel("House");
                this.setActiveShape(new House());
            }
            if (((JButton)source).getText().equals(ROAD_BUTTON_NAME)) {
                updateActiveStatusLabel("Road");
                this.setActiveShape(new Road());
            }
        }
    }



    @Override
    public void mousePressed(MouseEvent e) {
        if (this.getBoard().getActiveShape() != null)  {
            if (this.getActiveShape() instanceof Tree) {
                Tree newTree = new Tree(e.getX(),e.getY(),this.getActiveColor());
                this.getBoard().getShapes().add(newTree);
                this.updateColor();
                this.getBoard().revalidate();
                this.getBoard().repaint();
            }
            if (this.getActiveShape() instanceof House) {
                House newTree = new House(e.getX(),e.getY(),this.getActiveColor());
                this.getBoard().getShapes().add(newTree);
                this.updateColor();
                this.getBoard().revalidate();
                this.getBoard().repaint();
            }
            if (this.getActiveShape() instanceof Road) {
                for (MyShape shape : this.getBoard().getShapes()) {
                    if (shape.contains(e.getX(),e.getY())){
                        if (shape instanceof Tree) {
                            this.setStartShape(new Tree());
                        }
                        if (shape instanceof House) {
                            this.setStartShape(new House());
                        }
                        Road newRoad = new Road(e. getX(),e.getY());
                        this.getBoard().setActiveShape(newRoad);
                    }
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.getBoard().getActiveShape() instanceof Road) {
            ((Road) this.getBoard().getActiveShape()).setEndX(e.getX());
            ((Road) this.getBoard().getActiveShape()).setEndY(e.getY());
        }
        this.getBoard().revalidate();
        this.getBoard().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.getBoard().getActiveShape() instanceof Road) {
            boolean result = false;
            ((Road) this.getBoard().getActiveShape()).setEndX(e.getX());
            ((Road) this.getBoard().getActiveShape()).setEndY(e.getY());
            for (MyShape shape : this.getBoard().getShapes()) {
                if (shape.contains(e.getX(),e.getY())){
                    if (startShape instanceof Tree && shape instanceof House) {
                        result = true;
                        break;
                    }
                    if (startShape instanceof House && shape instanceof Tree) {
                        result = true;
                        break;
                    }
                }
            }
            if (result) {
                this.getBoard().getShapes().add(this.getBoard().getActiveShape());
            } else {
                this.getBoard().setActiveShape(new Road());
            }
        }
        this.getBoard().revalidate();
        this.getBoard().repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (this.activeShape instanceof Tree) {
            Color color = new Color(this.getActiveColor().getRGB(),true);
            Tree tmpTree = new Tree(e.getX(),e.getY(),color);
            this.getBoard().setActiveShape(tmpTree);
        }
        if (this.activeShape instanceof House) {
            Color color = new Color(this.getActiveColor().getRGB(),true);
            House tmpHouse = new House(e.getX(),e.getY(),color);
            this.getBoard().setActiveShape(tmpHouse);
        }
        this.getBoard().revalidate();
        this.getBoard().repaint();
    }
}
