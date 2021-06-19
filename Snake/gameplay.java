package Snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class gameplay extends JPanel implements ActionListener, KeyListener {
	
	private ImageIcon titleImage;
	private int [] snakeXlength = new int[750];
	private int [] snakeYlength = new int[750];
	private int moves = 0;
	
	private int[] enemyXpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int[] enemyYpos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
	private ImageIcon enemyimage;
	private Random random = new Random();
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;

	private int lengthofsnake = 3;
	private ImageIcon rightface;
	private ImageIcon leftface;
	private ImageIcon upface;
	private ImageIcon downface;
	
	private Timer timer;
	private int delay = 100;
	private int score = 0;
	
	private ImageIcon snakeimage;
	
	public gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this); 
		timer.start();
	}
	public void paint(Graphics g) {
		if(moves==0) {
			snakeXlength[2]=50;
			snakeXlength[1]=75;
			snakeXlength[0]=100;
			
			snakeYlength[2]=100;
			snakeYlength[1]=100;
			snakeYlength[0]=100;
		}
		 //title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		//title image
		titleImage = new ImageIcon("Snake/assets/snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//gameplay
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		//score
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Score : "+score, 780, 30); 
		
		//length
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Length : "+lengthofsnake, 780, 50); 
		
		rightface = new ImageIcon("Snake/assets/rightmouth.png");
		rightface.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
		 
		for(int i=0;i<lengthofsnake;i++) {
			if(i==0 && right) {
				rightface = new ImageIcon("Snake/assets/rightmouth.png");
				rightface.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0 && left) {
				leftface = new ImageIcon("Snake/assets/leftmouth.png");
				leftface.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0 && up) {
				upface = new ImageIcon("Snake/assets/upmouth.png");
				upface.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0 && down) {
				downface = new ImageIcon("Snake/assets/downmouth.png");
				downface.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			
			if(i!=0) {
				snakeimage = new ImageIcon("Snake/assets/snakeimage.png");
				snakeimage.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}  
		}
		
		enemyimage = new ImageIcon("Snake/assets/enemy.png");
		if(enemyXpos[xpos] == snakeXlength[0] && enemyYpos[ypos] == snakeYlength[0]) {
			score += 50;
			lengthofsnake++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23); 
		}
		enemyimage.paintIcon(this, g, enemyXpos[xpos], enemyYpos[ypos]);
		
		for(int i=1; i<lengthofsnake; i++) {
			if(snakeXlength[i] == snakeXlength[0] && snakeYlength[i]==snakeYlength[0]) {
				right = false;
				left = false;
				up = false;
				down = false;
				
				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 50)); 
				g.drawString("Game Over", 300, 300);
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Space to restart", 350, 340);
			}
		}

		g.dispose();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			right = true;
			if(!left) {
				right = true;
			}
			else {
				right = false;
				left = true;
			}
			up = false;
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			left = true;
			if(!right) {
				left = true;
			}
			else {
				left = false;
				right = true;
			}
			up = false;
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			up = true;
			if(!down) {
				up = true;
			}
			else {
				up = false;
				down = true;
			}
			right = false;
			left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			down = true;
			if(!up) {
				down = true;
			}
			else {
				down = false;
				up = true;
			}
			right = false;
			left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			moves = 0;
			score = 0;
			lengthofsnake = 3;
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right) {
			for(int i=lengthofsnake-1; i>=0; i--) {
				snakeYlength[i+1] = snakeYlength[i];
			} 
			for(int i=lengthofsnake; i>=0; i--) {
				if(i==0) {
					snakeXlength[i] = snakeXlength[i] + 25;
				}
				else {
					snakeXlength[i] = snakeXlength[i-1];
				}
				if(snakeXlength[i]>850) {
					snakeXlength[i] = 25;
				}
			}
			repaint();
		}
		if(left) {
			for(int i=lengthofsnake-1; i>=0; i--) {
				snakeYlength[i+1] = snakeYlength[i];
			} 
			for(int i=lengthofsnake; i>=0; i--) {
				if(i==0) {
					snakeXlength[i] = snakeXlength[i] - 25;
				}
				else {
					snakeXlength[i] = snakeXlength[i-1];
				}
				if(snakeXlength[i]<25) {
					snakeXlength[i] = 850;
				}
			}
			repaint();
			
		}
		if(down) {
			for(int i=lengthofsnake-1; i>=0; i--) {
				snakeXlength[i+1] = snakeXlength[i];
			} 
			for(int i=lengthofsnake; i>=0; i--) {
				if(i==0) {
					snakeYlength[i] = snakeYlength[i] + 25;
				}
				else {
					snakeYlength[i] = snakeYlength[i-1];
				}
				if(snakeYlength[i]> 625) {
					snakeYlength[i] = 75;
				}
			}
			repaint();	
		}
		if(up) {
			for(int i=lengthofsnake-1; i>=0; i--) {
				snakeXlength[i+1] = snakeXlength[i];
			} 
			for(int i=lengthofsnake; i>=0; i--) {
				if(i==0) {
					snakeYlength[i] = snakeYlength[i] - 25;
				}
				else {
					snakeYlength[i] = snakeYlength[i-1];
				}
				if(snakeYlength[i] < 75) {
					snakeYlength[i] = 625;
				}
			}
			repaint();	
		}
	}
	
}
