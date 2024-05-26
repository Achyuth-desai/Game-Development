package framed;

import javax.naming.spi.DirectoryManager;
import javax.swing.*;
import java.awt.*;

public class LightButton extends JButton {
    private static final long serialVersionUID = 1L;
    private static final int MAXSIZE = 100;
    private int row = 0, col = 0;


    public LightButton(int row, int col){
        Dimension size = new Dimension(MAXSIZE, MAXSIZE);
        this.row = row;
        this.col = col;
        setBackground(Color.BLACK);
        setPreferredSize(size);
    }
    public void turnOn(){
        setBackground(Color.RED);
    }
    public void turnOff(){
        setBackground(Color.BLACK);
    }
    public boolean isLit(){
        Color color = getBackground();
        boolean isLit = color.equals(Color.RED);
        return isLit;
    }
    public void toggle(){
        if(isLit())
            turnOff();
        else
            turnOn();
    }
    public int getRow(){
        return this.row;
    }
    public int getCol(){
        return this.col;
    }
}
