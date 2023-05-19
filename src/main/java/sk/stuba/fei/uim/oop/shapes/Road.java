package sk.stuba.fei.uim.oop.shapes;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Road extends MyShape{
    int endX;
    int endY;

    public Road(int startX,int startY){
        super(startX,startY,Color.BLACK);
    }
    public Road() {}
    @Override
    public void draw(Graphics g){
        g.setColor(this.getColor());
        g.drawLine(this.getX(),this.getY(),endX,endY);
    }
}
