package BlockBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class mapGenerator {
	public int map[][];
	public int brickwidth;
	public int brickheight;
	public mapGenerator(int row, int col) {
		map = new int[row][col];
		for(int i=0;i<map.length;i++) {
			for(int j=0; j<map[0].length;j++) {
				map[i][j] = 1; // '1' denotes block to be shown, '0' denotes block hit and shouldn't be shown
			}
		}
		brickwidth = 540/col;
		brickheight = 150/row;
	}
	
	public void draw(Graphics2D g) {
		for(int i=0;i<map.length;i++) {
			for(int j=0; j<map[0].length;j++) {
				if(map[i][j] > 0) {
					g.setColor(Color.white);
					g.fillRect(j * brickwidth + 80, i * brickheight + 50, brickwidth, brickheight);
					
					//border for blocks
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickwidth + 80, i * brickheight + 50, brickwidth, brickheight);
				}
			}
		}
	}
	
	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
	}
}
