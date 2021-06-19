package PingPong;

import javax.swing.JFrame;

public class Main {
	public static void main(String args[]) {
		JFrame obj = new JFrame();
		gameplay game = new gameplay();
		obj.setBounds(10, 10, 1000, 600);
		obj.setTitle("Ping Pong");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(game);
	}
}
