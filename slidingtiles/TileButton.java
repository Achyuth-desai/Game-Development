package slidingtiles;

import javax.swing.*;
import java.awt.*;

public class TileButton extends JButton {
    private static final long serialVersionUID = 1L;
    private static int tileSize = 0;
    private static int maxTiles = 0;

    private ImageIcon imageIcon;
    private int imageID = 0;
    private int row = 0;
    private int col = 0;

    public TileButton(ImageIcon imageIcon, int imageID, int row, int col){
        this.row = row;
        this.col = col;
        setImage(imageIcon, imageID);
        setBackground(Color.WHITE);
        setBorder(null);
        Dimension size = new Dimension(tileSize, tileSize);
        setPreferredSize(size);
        setFocusPainted(false);
    }
    public void setImage(ImageIcon imageIcon, int imageID){
        this.imageIcon = imageIcon;
        this.imageID = imageID;
        if (this.imageID == maxTiles*maxTiles - 1) {
            setIcon(null);
        } else {
            setIcon(imageIcon);
        }
    }
    public static void setTileSizeAndMaxTiles(int size, int max){
        tileSize = size;
        maxTiles = max;
    }

    private int getImageID(){
        return this.imageID;
    }
    private ImageIcon getImage(){
        return this.imageIcon;
    }
    private int getRow(){
        return this.row;
    }
    private int getCol(){
        return this.col;
    }

    public boolean hasNoImage(){
        boolean hasNoImage = false;
        if(getIcon()==null){
            hasNoImage = true;
        }
        return hasNoImage;
    }

    public void swap(TileButton otherTile){
        ImageIcon otherTileIcon = otherTile.getImage();
        int otherImageId = otherTile.getImageID();
        otherTile.setImage(imageIcon, imageID);
        setImage(otherTileIcon, otherImageId);
    }

    public void showImage(){
        //setImage(imageIcon);
    }
}
