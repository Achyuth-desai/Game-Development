package BlockBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class gameplay extends JPanel implements KeyListener, ActionListener {
	private boolean play = false;
	private int score = 0;
	private int totalbricks = 21;
	private Timer timer;
	private int delay = 8;
	
	private int playerX = 310;
	
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	private mapGenerator map;
	
	public gameplay() {
		map = new mapGenerator(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		//background
		g.setColor(Color.black);
		g.fillRect(1,1, 692, 592);
		
		//drawing map of blocks
		map.draw((Graphics2D)g);
		//borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		//scores
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("" +score, 590, 30);
		
		//paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		//ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		if(totalbricks <= 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Finished! Score : " +score, 150, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to start again", 200, 350);
		}
		
		if(ballposY > 570) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("GAME OVER, SCORE = " +score, 150, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to start again", 200, 350);
		}
		
		g.dispose();
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYdir = -ballYdir;
			}
			A :for(int i=0; i < map.map.length; i++) {
				for(int j=0; j<map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickX = j * map.brickwidth + 80;
						int brickY = i * map.brickheight + 50;
						int brickheight = map.brickheight;
						int brickwidth = map.brickwidth;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickwidth, brickheight);
						Rectangle ballrect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickrect = rect;
						
						if(ballrect.intersects(brickrect)) {
							map.setBrickValue(0, i, j);
							totalbricks--;
							score += 5;
							
							if(ballposX + 19 <= brickrect.x || ballposX + 1 >= brickrect.x + brickrect.width) {
								ballXdir = -ballXdir;
							}
							else {
								ballYdir = -ballYdir; 
							}
							break A;
						}
					}
				}
			}
			
			ballposX+= ballXdir;
			ballposY+= ballYdir;
			if(ballposX < 0)
				ballXdir = -ballXdir;
			if(ballposY < 0)
				ballYdir = -ballYdir;
			if(ballposX > 670)
				ballXdir = -ballXdir;
			
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX>=581)
				playerX = 581;
			else
				moveright();
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX<=10)
				playerX = 10;
			else
				moveleft();
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalbricks = 21;
				map = new mapGenerator(3,7);
				
				repaint();
			}
		}
	}
	
	public void moveright() {
		play = true;
		playerX+=20;
	}
	public void moveleft() {
		play = true;
		playerX-=20;
	}
}
