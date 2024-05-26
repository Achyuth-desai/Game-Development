package mycomponents;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
public class TitleLabel extends JLabel{
    private static final long serialVersionUID = 1L;

    public TitleLabel(String title){
        Font font = new Font(Font.SERIF, Font.BOLD, 32);

        setFont(font);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setOpaque(true);
        setHorizontalAlignment(JLabel.CENTER);
        setText(title);
    }
}
