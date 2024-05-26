package slidingtiles;
import mycomponents.TitleLabel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class slidingTiles extends JFrame{
    private static final long serialVersionUID = 1L;
    private static final String FILENAME = "C:\\Users\\Achyuth Desai\\IdeaProjects\\Game Dev\\src\\slidingtiles\\SlidingTilesImage.jpg";
    private int tileSize = 50;
    private int gridSize = 4;
    BufferedImage image = null;
    private TileButton[][] tile = new TileButton[gridSize][gridSize];
    private JPanel centerPanel = new JPanel();

    public slidingTiles(){
        try{
            image = ImageIO.read(new File(FILENAME));
            TileButton.setTileSizeAndMaxTiles(tileSize, gridSize);
            initGUI();
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
            setLocationRelativeTo(null);
            pack();
            }catch (IOException e){
                String message = "The file could not be opened";
                JOptionPane.showMessageDialog(this, message);
        }
    }
    private void initGUI(){
        TitleLabel titleLabel = new TitleLabel("Sliding Tiles");
        add(titleLabel, BorderLayout.PAGE_START);
        //main Panel
        divideImage();
        //button Panel

    }
    private void divideImage(){
        centerPanel.setLayout(new GridLayout(gridSize, gridSize));
        add(centerPanel, BorderLayout.CENTER);
        centerPanel.removeAll();
        int imageID = 0;
        for(int row=0;row<gridSize;row++){
            for(int col=0;col<gridSize;col++){
                int x = col*tileSize;
                int y = row*tileSize;
                BufferedImage subImage = image.getSubimage(x, y, tileSize, tileSize);
                ImageIcon imageIcon = new ImageIcon(subImage);
                tile[row][col] = new TileButton(imageIcon, imageID, row, col);
                centerPanel.add(tile[row][col]);
                imageID++;
            }
        }
        centerPanel.revalidate();
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new slidingTiles();
            }
        });
    }
}
