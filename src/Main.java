import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Main {

	public static final int DELAY = 50;

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setSize(600, 600);
		Level level = new Level();
		JLabel label = new JLabel();
		GameEngine sprite1 = new GameEngine(level, label);
		
		
		frame.add(sprite1); 
		frame.add(label, BorderLayout.NORTH);
		
		KeyboardListener listener = new KeyboardListener(sprite1);
		AL updater = new AL(sprite1, listener);
		
		Timer timer = new Timer(DELAY, updater); 
		timer.start();
		
		frame.addKeyListener(listener);
		
		
		frame.setTitle("Arcade Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		while(true){
			JLabel gameOverLabel = sprite1.gameOverScreen();
			
			if (sprite1.isGameOver()){
				frame.add(sprite1.gameOverScreen());
				break;
			}
			if (sprite1.isWin()){
				frame.add(sprite1.gameWinScreen());
				break; 
			}
		
		}
		
	}

}
