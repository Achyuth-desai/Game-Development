package watchyourstep;
import mycomponents.TitleLabel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class WatchYourStep extends JFrame{
    private static final long serialVersionUID = 1L;
    private static final int GRIDSIZE = 15;
    private static final int NUMBEROFHOLES = 40;
    private TerrainButton[][] terrain = new TerrainButton[GRIDSIZE][GRIDSIZE];
    private int totalRevealed = 0;

    public WatchYourStep(){
        initGUI();
        setTitle("Watch Your Step");
        setVisible(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initGUI(){
        TitleLabel titleLabel = new TitleLabel("WatchYourStep");   
        add(titleLabel, BorderLayout.PAGE_START);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE) );
        add(centerPanel, BorderLayout.CENTER);
        for(int row=0; row<GRIDSIZE; row++){
            for(int col=0; col<GRIDSIZE; col++){
                terrain[row][col] = new TerrainButton(row, col);
                centerPanel.add(terrain[row][col]);
                terrain[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TerrainButton button = (TerrainButton) e.getSource();
                        int row = button.getRow();
                        int col = button.getCol();
                        clickedTerrain(row, col);
                    }
                });
            }
        }
        setHoles();
    }

    public void setHoles(){
        Random rand = new Random();
        for(int i=0;i<NUMBEROFHOLES;i++){
            int pickRow = rand.nextInt(GRIDSIZE);
            int pickCol = rand.nextInt(GRIDSIZE);
            while(terrain[pickRow][pickCol].hasHole()){
                pickRow = rand.nextInt(GRIDSIZE);
                pickCol = rand.nextInt(GRIDSIZE);
            }
            terrain[pickRow][pickCol].setHole(true);
            addToNeighboursHoleCount(pickRow, pickCol);
            //terrain[pickRow][pickCol].reveal(true);
        }
    }

    public void addToNeighboursHoleCount(int row, int col){
         addToHoleCount(row-1, col-1);
         addToHoleCount(row-1, col);
         addToHoleCount(row-1, col+1);
         addToHoleCount(row, col-1);
         addToHoleCount(row, col+1);
         addToHoleCount(row+1, col-1);
         addToHoleCount(row+1, col);
         addToHoleCount(row+1, col+1);
    }
    public void addToHoleCount(int row, int col){
        if(row > -1 && col > -1 && row < GRIDSIZE && col < GRIDSIZE){
            terrain[row][col].increaseHoleCount();
            //terrain[row][col].reveal(true);
        }
    }
    private void clickedTerrain(int row, int col){
        if(!terrain[row][col].hasHole()) {
            check(row, col);
            checkNeighbours(row, col);
            if(totalRevealed == (GRIDSIZE*GRIDSIZE) - NUMBEROFHOLES){
                String message = "Congatulations! You Won! Do you want to play again?";
                promptNewGame(message);
            }
        }
        else{
            String message = "Stepped on a hole. Game Over! Do you want to play again?";
            promptNewGame(message);
        }
    }
    private void promptNewGame(String message){
        showHoles();
        int option = JOptionPane.showConfirmDialog(this, message, "Play Again?", JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION){
            newGame();
        }
        else
            System.exit(0);
    }
    private void showHoles(){
        for(int i=0;i<GRIDSIZE;i++)
            for(int j=0;j<GRIDSIZE;j++)
                if(terrain[i][j].hasHole())
                    terrain[i][j].reveal(true);
    }
    private void newGame(){
        for(int i=0;i<GRIDSIZE;i++){
            for(int j=0;j<GRIDSIZE;j++){
                terrain[i][j].reset();
            }
        }
        totalRevealed = 0;
        setHoles();
    }
    private void check(int row, int col){
        if(row > -1 && col > -1 && row < GRIDSIZE && col < GRIDSIZE
                && !terrain[row][col].hasHole() && !terrain[row][col].isRevealed()){
            terrain[row][col].reveal(true);
            totalRevealed++;
            if(!terrain[row][col].isNextToHoles()){
                checkNeighbours(row, col);
            }
        }
    }
    private void checkNeighbours(int row, int col){
        check(row-1, col-1);
        check(row-1, col);
        check(row-1, col+1);
        check(row, col-1);
        check(row, col+1);
        check(row+1, col-1);
        check(row+1, col);
        check(row+1, col+1);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WatchYourStep();
            }
        });
    }
}
