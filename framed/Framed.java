package framed;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import mycomponents.*;
import java.util.Random;

public class Framed extends JFrame{
    private static final long serialVersionUID = 1L;
    private static final int GRIDSIZE = 3;

    private LightButton[][] lightButton = new LightButton[GRIDSIZE][GRIDSIZE];

    public Framed(){
        initGUI();
        setTitle("Framed");
        setVisible(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        newGame();
    }
    public void initGUI(){
        TitleLabel titleLabel = new TitleLabel("Framed");
        add(titleLabel, BorderLayout.PAGE_START);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
        add(centerPanel, BorderLayout.CENTER);

        for(int row=0;row<GRIDSIZE;row++){
            for(int col=0;col<GRIDSIZE;col++){
                lightButton[row][col] = new LightButton(row, col);
                lightButton[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        LightButton button = (LightButton) e.getSource();
                        int row = button.getRow();
                        int col = button.getCol();
                        toggleLights(row, col);
                        endIfGameDone();
                    }
                });
                centerPanel.add(lightButton[row][col]);
            }
        }
    }

    private void newGame(){
        Random random = new Random();
        int numberOfTimes = random.nextInt(10) + 10;
        for(int row=0;row<GRIDSIZE;row++){
            for(int col=0;col<GRIDSIZE;col++){
                lightButton[row][col].turnOn();
            }
            lightButton[1][1].turnOff();
        }
        for(int i=0;i<numberOfTimes;i++){
            int row = random.nextInt(GRIDSIZE);
            int col = random.nextInt(GRIDSIZE);
            toggleLights(row, col);
        }

    }

    private void toggleLights(int row, int col){
        if(row==0 && col==0){
            lightButton[0][1].toggle();
            lightButton[1][1].toggle();
            lightButton[1][0].toggle();
        }
        else if(row==2 && col==0){
            lightButton[1][0].toggle();
            lightButton[1][1].toggle();
            lightButton[2][1].toggle();
        }
        else if(row==0 && col==2){
            lightButton[0][1].toggle();
            lightButton[1][1].toggle();
            lightButton[1][2].toggle();
        }
        else if(row==2 && col==2){
            lightButton[1][2].toggle();
            lightButton[1][1].toggle();
            lightButton[2][1].toggle();
        }
        else if(row==0 && col==1){
            lightButton[0][0].toggle();
            lightButton[0][2].toggle();
        }
        else if(row==1 && col==0){
            lightButton[0][0].toggle();
            lightButton[2][0].toggle();
        }
        else if(row==1 && col==2){
            lightButton[0][2].toggle();
            lightButton[2][2].toggle();
        }
        else if(row==2 && col==1){
            lightButton[2][0].toggle();
            lightButton[2][2].toggle();
        }
        else if(row==1 && col==1){
            lightButton[0][1].toggle();
            lightButton[1][0].toggle();
            lightButton[1][2].toggle();
            lightButton[2][1].toggle();
        }
    }

    public void endIfGameDone(){
        boolean isDone = lightButton[0][0].isLit() &&
                        lightButton[0][1].isLit() &&
                        lightButton[0][2].isLit() &&
                        lightButton[1][0].isLit() &&
                        !lightButton[1][1].isLit() &&
                        lightButton[1][2].isLit() &&
                        lightButton[2][0].isLit() &&
                        lightButton[2][1].isLit() &&
                        lightButton[2][2].isLit();
        if(isDone){
            String message = "Congratulations! You won. Do you wish to play again?";
            int option = JOptionPane.showConfirmDialog(this, message, "Play again?", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
                newGame();
            else
                System.exit(0);
        }
    }
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Framed();
            }
        });
    }

}
