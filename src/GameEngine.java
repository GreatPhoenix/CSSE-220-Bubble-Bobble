
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class GameEngine extends JComponent {

	Hero hero;
	Level level;
	// private Bubble enemiesBubble;
	private HashMap<Enemy2, Bubble> enemiesBubble2;
	private HashMap<Enemy1, Bubble> enemiesBubble;
	private ArrayList<Enemy1> firstEnemies;
	private ArrayList<Enemy2> secondEnemies;
	private ArrayList<Candy> candies;
	private Graphics2D g2;
	private Integer score;
	private JLabel label;
	private JLabel gameOverLabel;
	private boolean win;
	private JLabel gameWinLabel;
	private boolean stopNewLevel;

	public GameEngine(Level level, JLabel label) {
		this.level = level;
		this.hero = new Hero(10, 500, 30, 30, this.level);
		this.firstEnemies = new ArrayList<Enemy1>();
		this.secondEnemies = new ArrayList<Enemy2>();
		this.candies = new ArrayList<Candy>();
		this.enemiesBubble = new HashMap<Enemy1, Bubble>();
		this.enemiesBubble2 = new HashMap<Enemy2, Bubble>();
		this.loadEnemies();
		this.label = label;
		this.gameOverLabel = new JLabel();
		this.gameWinLabel = new JLabel();
		this.score = 0;
		this.win = false;
		this.stopNewLevel = false;
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		this.g2 = (Graphics2D) graphics;
		this.level.loadFile(g2);
		if (!stopNewLevel) {
			for (Bar b : this.level.getBars()) {
				b.drawOn(g2);
			}

			this.hero.drawOn(g2);
			for (Enemy1 enemy : this.firstEnemies) {
				enemy.drawOn(g2);
			}
			for (Enemy2 enemy : this.secondEnemies) {
				enemy.drawOn(g2);
				for (Projectile pew : enemy.getProjectiles()) {
					pew.drawOn(g2);
				}
			}
			for (Candy candy : this.candies) {
				candy.drawOn(g2);
			}
			if (this.hero.getBubbles() != null) {
				for (Bubble bub : this.hero.getBubbles()) {
					bub.drawOn(g2);
				}
			}
		}
	}

	public void GameOver() {
		if (this.hero.lives == 0) {
			// this.level.setLevel(1);
			// this.hero.lives = 3;
			// this.score = 0;
			// setNewGame();
		}
	}

	public ArrayList<Enemy1> getEnemies() {
		return this.firstEnemies;
	}

	public ArrayList<Enemy2> getSecondEnemies() {
		return this.secondEnemies;
	}

	public ArrayList<Candy> getCandies() {
		return this.candies;
	}

	public void setNewGame() {
		this.level.barDeath();
		this.enemiesBubble = new HashMap<Enemy1, Bubble>();
		this.enemiesBubble2 = new HashMap<Enemy2, Bubble>();
		this.hero.resetLocation();

		for (Candy candy : this.candies) {
			candy.delete();
		}
		for (Enemy1 enemy : this.getEnemies()) {
			enemy.delete();
		}
		for (Enemy2 enemy : this.getSecondEnemies()) {
			enemy.delete();
		}
		for (Bubble bubble : this.hero.getBubbles()) {
			bubble.delete();
		}
		this.deleteEnemies();
		this.loadEnemies();

	}

	public void drawScreen() {
		repaint();
	}

	public void deleteEnemies() {
		this.firstEnemies = new ArrayList<Enemy1>();
		this.secondEnemies = new ArrayList<Enemy2>();
	}

	public void loadEnemies() {
		Enemy1 e1 = new Enemy1(280, 100, 30, 30, this.hero, this.level);
		this.firstEnemies.add(e1);

		Enemy2 e2 = new Enemy2(500, 300, 30, 30);
		this.secondEnemies.add(e2);
		Enemy2 e3 = new Enemy2(100, 50, 30, 30);
		this.secondEnemies.add(e3);
		Enemy2 e4 = new Enemy2(50, 200, 30, 30);
		this.secondEnemies.add(e4);

	}

	public void isShot() {
		for (Enemy2 enemy : this.secondEnemies) {
			for (Projectile pew : enemy.getProjectiles())
				if (pew.getX() > this.hero.getX() && pew.getY() > this.hero.getY() && pew.getX() < this.hero.getX() + 30
						&& pew.getY() < this.hero.getY() + 30) {
					this.hero.dead = true;
					this.hero.lives -= 1;
				}
		}
		for (Enemy1 enemy : this.firstEnemies) {
			if (enemy.getX() + 15 > this.hero.getX() && enemy.getY() + 15 > this.hero.getY()
					&& enemy.getX() + 15 < this.hero.getX() + 30 && enemy.getY() + 15 < this.hero.getY() + 30
					&& !enemy.inBubble && !enemy.isDead) {
				this.hero.dead = true;
				this.hero.lives -= 1;
			}
		}
	}

	public void enemyHit() {

		for (Enemy1 enemy : this.firstEnemies) {
			for (Bubble bubble : this.hero.getBubbles()) {
				if (!enemy.inBubble) {
					if (bubble.getX() + 5 > enemy.getX() && bubble.getX() - 5 < enemy.getX() + 30
							&& bubble.getY() + 5 > enemy.getY() && bubble.getY() - 5 < enemy.getY() + 30) {
						enemy.inBubble = true;
						this.enemiesBubble.put(enemy, bubble);
					}
				}
			}
			if (enemiesBubble.get(enemy) != null) {
				if (enemy.inBubble && enemiesBubble.get(enemy).getIsDead()) {
					enemy.inBubble = false;
				}
				if (enemy.inBubble) {
					enemy.inBubble = true;
					enemiesBubble.get(enemy).setPosition(enemy.getX(), enemy.getY());
					enemiesBubble.get(enemy).isEmpty = false;
				}
			}
		}

		for (Enemy2 enemy : this.secondEnemies) {
			for (Bubble bubble : this.hero.getBubbles()) {
				if (!enemy.inBubble) {

					if (bubble.getX() + 5 >= enemy.getX() && bubble.getX() - 5 <= enemy.getX() + 30
							&& bubble.getY() + 5 >= enemy.getY() && bubble.getY() - 5 <= enemy.getY() + 30) {
						if (!this.enemiesBubble2.containsValue(bubble)) {
							enemy.inBubble = true;
							this.enemiesBubble2.put(enemy, bubble);
						}

					}
				}
			}

			if (enemiesBubble2.get(enemy) != null) {
				if (enemy.inBubble && enemiesBubble2.get(enemy).getIsDead()) {
					enemy.inBubble = false;
					enemiesBubble2.remove(enemy);
				}

				else if (enemy.inBubble) {
					enemy.inBubble = true;
					enemiesBubble2.get(enemy).isEmpty = false;
					enemiesBubble2.get(enemy).setPosition(enemy.getX(), enemy.getY());
				}

			}
		}
	}

	public void nextLevel() {
		if (this.firstEnemies.size() == 0 && this.secondEnemies.size() == 0) {
			if (this.level.getLevel() == 3) {
				this.win = true;
			} else if (this.win == false) {
				this.level.incrementLevel();
				this.setNewGame();
			}

		}
	}

	public void killEnemy() {
		ArrayList<Enemy1> remove1 = new ArrayList<Enemy1>();
		ArrayList<Enemy2> remove2 = new ArrayList<Enemy2>();
		for (Enemy1 enemy : this.firstEnemies) {
			if (enemy.inBubble && hero.getX() > enemy.getX() && hero.getX() < enemy.getX() + 30
					&& hero.getY() > enemy.getY() && hero.getY() < enemy.getY() + 30) {
				enemy.delete();
				remove1.add(enemy);
				Candy candy = new Candy(enemy.getX(), enemy.getY(), 10, this.level);
				this.candies.add(candy);
				enemy.setLocation(700, 700);
				this.score += 100;

			}
		}
		for (Enemy1 enemy : remove1) {
			this.firstEnemies.remove(enemy);
		}

		for (Enemy2 enemy : this.secondEnemies) {
			if (enemy.inBubble && hero.getX() > enemy.getX() && hero.getX() < enemy.getX() + 30
					&& hero.getY() > enemy.getY() && hero.getY() < enemy.getY() + 30) {
				enemy.delete();
				remove2.add(enemy);
				enemy.isDead = true;
				Candy candy = new Candy(enemy.getX(), enemy.getY(), 10, this.level);
				this.candies.add(candy);
				enemy.setLocation(700, 700);
				this.score += 100;
			}
		}
		for (Enemy2 enemy : remove2) {
			this.secondEnemies.remove(enemy);
		}
	}

	public void pickupLines() {
		ArrayList<Candy> remove = new ArrayList<Candy>();
		for (Candy candy : this.candies) {
			if (candy.getX() > this.hero.getX() && candy.getX() < this.hero.getX() + 30
					&& candy.getY() > this.hero.getY() && candy.getY() < this.hero.getY() + 30) {
				candy.delete();
				remove.add(candy);
				this.score += 200;
			}

		}
		for (Candy candy : remove) {
			this.candies.remove(candy);
		}
	}

	public JLabel gameOverScreen() {
		this.gameOverLabel.setText("GAME OVER");
		this.gameOverLabel.setFont(new Font("Arial", Font.PLAIN, 90));
		return this.gameOverLabel;

	}

	public JLabel gameWinScreen() {
		this.gameWinLabel.setText("YOU WIN!");
		this.gameWinLabel.setFont(new Font("Arial", Font.PLAIN, 120));
		return this.gameWinLabel;
	}

	public boolean isGameOver() {

		if (this.hero.lives == 0) {
			this.stopNewLevel = true;
			this.level.barDeath();
			this.deleteEnemies();
			return true;
		} else {
			return false;
		}
	}

	public boolean isWin() {
		if (this.win) {
			this.stopNewLevel = true;
			this.level.barDeath();
			this.deleteEnemies();
			return true;
		} else {
			return false;
		}
	}

	public void updateLabel() {
		this.label
				.setText("Score: " + this.score.toString() + "    " + "Lives remaining: " + this.hero.lives.toString());

	}
}
