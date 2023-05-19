package sk.stuba.fei.uim.oop.shapes;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class House extends MyShape{
    private static final int HEIGHT = 50;
    private static final int WIDTH = 50;

    public House(int x,int y, Color color){
        super(x,y,color);
    }
    public House() {}
    @Override
    public void draw(Graphics g) {
        int[] xCords = {this.getX()+WIDTH/4,this.getX()+(WIDTH/4*3),this.getX()+WIDTH/2,this.getX()+WIDTH/4};
        int[] yCords = {this.getY()+HEIGHT/2,this.getY()+HEIGHT/2,this.getY(),this.getY()+HEIGHT/2};
        g.setColor(this.getColor());
        g.fillPolygon(xCords,yCords,3);
        g.fillRect(this.getX()+WIDTH/4,this.getY()+HEIGHT/2,WIDTH/2,HEIGHT/2);
    }
}
