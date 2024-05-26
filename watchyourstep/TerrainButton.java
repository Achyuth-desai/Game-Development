package watchyourstep;
import javax.swing.JButton;
import java.awt.*;

public class TerrainButton extends JButton{
    private static final long serialVersionUID = 1L;
    private static final int SIZE = 50;
    private int row = 0;
    private int col = 0;
    private int nextToHoles = 0;
    private boolean hole = false;
    private boolean revealed = false;
    private boolean flagged = false;

    public TerrainButton(int row, int col){
        this.row = row;
        this.col = col;
        Dimension size = new Dimension(SIZE, SIZE);
        setPreferredSize(size);
    }
    public int getRow(){
        return this.row;
    }
    public int getCol(){
        return this.col;
    }
    public boolean hasHole(){
        return this.hole;
    }
    public boolean isRevealed(){
        return this.revealed;
    }

    public void setHole(boolean hasHole){
        this.hole = hasHole;
    }
    public void increaseHoleCount(){
        this.nextToHoles++;
    }
    public boolean isNextToHoles(){
        return (nextToHoles > 0);
    }

    public void reveal(boolean reveal){
        this.revealed = reveal;
        if(revealed){
            if(hasHole()){
                setBackground(Color.BLACK);
            }
            else{
                setBackground(Color.CYAN);
                if(nextToHoles > 0){
                    setText("" + nextToHoles);
                }
            }
        }
        else{
            setBackground(null);
            setText("");
        }
        setFocusPainted(false);
    }

    public void flag(){
        if(!revealed)
            if(!flagged) {
                setBackground(Color.RED);
                flagged = true;
            }
            else {
                setBackground(null);
                flagged = false;
            }
    }
    public void reset(){
        hole = false;
        revealed = false;
        nextToHoles = 0;
        setText("");
        setBackground(null);
    }
}
