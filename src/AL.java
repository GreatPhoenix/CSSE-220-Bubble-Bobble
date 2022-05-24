import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AL implements ActionListener {

	GameEngine engine;
	KeyboardListener k;
	private int counter;

	public AL(GameEngine sprite, KeyboardListener k) {
		// TODO Auto-generated constructor stub
		this.engine = sprite;
		this.k = k;
		counter = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		engine.drawScreen();
		engine.hero.gravity();
		engine.hero.doesCollide();
		k.update();
		engine.isShot();
		engine.enemyHit();
		engine.killEnemy();
		engine.pickupLines();
		engine.nextLevel();
		engine.updateLabel();
		engine.gameOverScreen();
		//engine.hero.deleteBubbles();
		if(this.engine.hero.isDead()) {
			this.engine.setNewGame(); 
		}
		for(Candy candy : engine.getCandies()) {
			candy.doesCollide();
			candy.Move();
		}
		for (Bubble bubble : engine.hero.getBubbles()) {
			bubble.move();
		}
		for (Enemy1 enemy : engine.getEnemies()) {
			enemy.move();
			enemy.doesCollide();
			enemy.gravity();
		}
		for (Enemy2 enemy : engine.getSecondEnemies()) {
			enemy.move();
			if (this.counter == 30) {
				enemy.Shoot();
			}
			for (Projectile pew : enemy.getProjectiles()) {
				pew.move();
			}
			

		}
		if(this.counter == 30) {
			this.counter = 0; 
		}

		this.counter += 1;
		engine.GameOver();
	}
}
