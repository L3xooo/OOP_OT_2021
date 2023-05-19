package sk.stuba.fei.uim.oop.shapes;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Tree extends MyShape{
    private static final int HEIGHT = 50;
    private static final int WIDTH = 50;

    public Tree(int x, int y, Color color) {
        super(x,y,color);
    }
    public Tree(){}

    public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.fillOval(this.getX(), this.getY(), WIDTH, HEIGHT / 3 * 2);
        g.fillRect(this.getX() + (WIDTH / 3), this.getY()-1 + (HEIGHT / 3) * 2, HEIGHT / 3, HEIGHT / 3+1);
    }
}