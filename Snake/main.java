package Snake;

import java.awt.Color;

import javax.swing.JFrame;

public class main {

	public static void main(String[] args) {
		JFrame obj = new JFrame();
		gameplay game = new gameplay();
		obj.setBounds(10, 10, 905, 700);
		obj.setFocusable(false);
		obj.setResizable(false);
		obj.setBackground(Color.DARK_GRAY);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(game);
	}

}
