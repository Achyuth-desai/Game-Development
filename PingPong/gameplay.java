package PingPong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class gameplay extends JPanel implements ActionListener, KeyListener{
	private boolean play = false;
	//private int player1_score = 0;
	//private int player2_score = 0;
	private Timer timer;
	private int delay = 7;
	
	private int player1 = 230;
	private int player2 = 230;
	private int score1 = 0;
	private int score2 = 0;
	
	private int ballposX = 485;
	private int ballposY = 275;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	//constructor
	public gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		//background
		g.setColor(Color.black);
		g.fillRect(1, 1, 992, 592);
		
		//borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 590);
		g.fillRect(0, 0, 990, 3);
		g.fillRect(990, 0, 3, 590);
		g.fillRect(0, 560, 990, 3);
		
		//paddle1
		g.setColor(Color.red);
		g.fillRect(10, player1, 8, 100);
		//paddle2
		g.setColor(Color.green);
		g.fillRect(980, player2, 8, 100);
		
		//court lines
		g.setColor(Color.white);
		g.fillRect(495, 0, 2, 592);
		g.drawOval(460, 250, 70, 70);
		
		//ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		//player1 win
		if(ballposX > 975) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Player 1 Wins!", 150, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to play again",200, 350);
		}
		//player2 win
		if(ballposX < 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Player 2 Wins!", 150, 300);
					
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to play again",200, 350);
		}
		
		g.dispose();
	}
	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		if(play) {
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(10, player1, 8, 100 ))) {
				ballXdir = -ballXdir;
			}
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(980, player2, 8, 100 ))) {
				ballXdir = -ballXdir;
			}
			ballposX += ballXdir;
			ballposY += ballYdir;
			if(ballposY < 0)
				ballYdir = -ballYdir;
			if(ballposY > 570)
				ballYdir = -ballYdir;
		}
		repaint();
	}	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(player2 < 10 )
				player2 = 10;
			else
				moveup2();
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(player2>450)
				player2 = 450;
			else
				movedown2();
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			if(player1<10)
				player1 = 10;
			else
				moveup1();
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			if(player1>450)
				player1 = 450;
			else
				movedown1();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballposX = 485;
				ballposY = 275;
				ballXdir = -1;
				ballYdir = -2;
				player1 = 230;
				player2 = 230;
				score1 = 0;
				score2 = 0;
				
				repaint();
			}
		}
	}
	
	public void moveup2() {
		play = true;
		player2 -= 20;
	}
	public void movedown2() {
		play = true;
		player2 += 20;
	}
	public void moveup1() {
		play = true;
		player1 -= 20;
	}
	public void movedown1() {
		play = true;
		player1 += 20;
	}
	
}
