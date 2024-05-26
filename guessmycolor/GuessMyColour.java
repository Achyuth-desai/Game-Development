package guessmycolor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import mycomponents.*;

public class GuessMyColour extends JFrame{
    private int targetRed = 0;
    private int targetGreen = 0;
    private int targetBlue = 0;
    private int red = 0;
    private int blue = 0;
    private int green = 0;

    private JPanel samplePanel = new JPanel();
    private JPanel targetPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    public GuessMyColour(){
        initGUI();
        setTitle("GuessMyColour");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        generateTargetColour();
    }
    private void initGUI(){
        TitleLabel titleLabel = new TitleLabel("Guess My Colour");
        add(titleLabel, BorderLayout.PAGE_START);
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        add(centerPanel, BorderLayout.CENTER);

        Dimension size = new Dimension(50,50);
        samplePanel.setBackground(Color.BLACK);
        samplePanel.setPreferredSize(size);
        centerPanel.add(samplePanel);
        targetPanel.setBackground(Color.CYAN);
        targetPanel.setPreferredSize(size);
        centerPanel.add(targetPanel);

        buttonPanel.setBackground(Color.PINK);
        add(buttonPanel, BorderLayout.PAGE_END);

        setButtons();
    }

    private void generateTargetColour(){
        Random random = new Random();
        targetRed = 15 * random.nextInt(17);
        targetBlue = 15 * random.nextInt(17);
        targetGreen = 15 * random.nextInt(17);
        Color targetColor = new Color(targetRed, targetGreen, targetBlue);
        targetPanel.setBackground(targetColor);
    }

    private void setButtons(){
        Font font = new Font(Font.DIALOG, Font.BOLD, 20);

        JButton moreRedButton = new JButton();
        moreRedButton.setBackground(Color.RED);
        moreRedButton.setText("+");
        moreRedButton.setFont(font);
        moreRedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                increaseRed();
            }
        });
        buttonPanel.add(moreRedButton);
        JButton lessRedButton = new JButton();
        lessRedButton.setBackground(Color.RED);
        lessRedButton.setText("-");
        lessRedButton.setFont(font);
        lessRedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decreaseRed();
            }
        });
        buttonPanel.add(lessRedButton);

        JButton moreBlueButton = new JButton();
        moreBlueButton.setBackground(Color.BLUE);
        moreBlueButton.setText("+");
        moreBlueButton.setFont(font);
        moreBlueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                increaseBlue();
            }
        });
        buttonPanel.add(moreBlueButton);
        JButton lessBlueButton = new JButton();
        lessBlueButton.setBackground(Color.BLUE);
        lessBlueButton.setText("-");
        lessBlueButton.setFont(font);
        lessBlueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decreaseBlue();
            }
        });
        buttonPanel.add(lessBlueButton);

        JButton moreGreenButton = new JButton();
        moreGreenButton.setBackground(Color.GREEN);
        moreGreenButton.setText("+");
        moreGreenButton.setFont(font);
        moreGreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                increaseGreen();
            }
        });
        buttonPanel.add(moreGreenButton);
        JButton lessGreenButton = new JButton();
        lessGreenButton.setBackground(Color.GREEN);
        lessGreenButton.setText("-");
        lessGreenButton.setFont(font);
        lessGreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decreaseGreen();
            }
        });
        buttonPanel.add(lessGreenButton);
    }

    private void updateSampleColor(){
        Color color = new Color(red, green, blue);
        samplePanel.setBackground(color);
        if(red==targetRed && blue==targetBlue && green==targetGreen){
            java.lang.String message = "Congratulations! The colours are matched! :)";
            JOptionPane.showMessageDialog(this, message);
        }
        System.out.println("Sample values : R:" + red + " G:" + green + " B:" + blue);
        System.out.println("Target values : R:" + targetRed + " G:" + targetGreen + " B:" + targetBlue + "\n");
    }

    private void increaseRed(){
        if(red <= 240) {
            red += 15;
            updateSampleColor();
        }
    }
    private void decreaseRed(){
        if(red >= 15){
            red -= 15;
            updateSampleColor();
        }
    }
    private void increaseBlue(){
        if(blue <= 240) {
            blue += 15;
            updateSampleColor();
        }
    }
    private void decreaseBlue(){
        if(blue >= 15){
            blue -= 15;
            updateSampleColor();
        }
    }
    private void increaseGreen(){
        if(green <= 240) {
            green += 15;
            updateSampleColor();
        }
    }
    private void decreaseGreen(){
        if(green >= 15){
            green -= 15;
            updateSampleColor();
        }
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {public void run() {
            new GuessMyColour();
        }});
    }
}
