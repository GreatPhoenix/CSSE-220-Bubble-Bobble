import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

	private boolean moveLeft = false;
	private boolean moveRight = false;
	private boolean jump = false; 
	private int intensity; 
	private GameEngine sprite;

	public KeyboardListener(GameEngine sprite) {
		// TODO Auto-generated constructor stub
		this.sprite = sprite;
		this.intensity = 0; 
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.moveRight = true;
			//update(); 
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.moveLeft = true;
			//update();
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			this.jump = true; 
			//update();
		}
		if(e.getKeyCode() == KeyEvent.VK_U){
			this.sprite.level.incrementLevel();
			this.sprite.setNewGame();
			
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			this.sprite.level.decrementLevel();
			this.sprite.setNewGame();
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(this.intensity < 200) {
				this.intensity += 5;
			}
			if(this.intensity > 75) {
				this.sprite.hero.setColor(Color.ORANGE);
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.moveRight = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.moveLeft = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			this.jump = false; 
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.sprite.hero.shoot(this.intensity);
			this.sprite.hero.setColor(Color.BLACK);
			this.intensity = 0; 
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	public void update() {
		if(this.moveRight) {
			this.sprite.hero.moveRight();
		}
		if(this.moveLeft) {
			this.sprite.hero.moveLeft();
		}
		if(this.jump) {
			this.sprite.hero.jump();; 
		}
	}

}
