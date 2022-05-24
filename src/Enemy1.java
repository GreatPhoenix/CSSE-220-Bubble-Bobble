import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy1 {

	private int x;
	private int y;
	private int width;
	private int height;
	private Hero hero;
	private int grav;
	private Level level;
	private Bar currentBar;
	private int savedYGoal;
	private boolean isJumping;
	protected boolean inBubble;
	protected boolean isDead;

	public Enemy1(int x, int y, int width, int height, Hero hero, Level level) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.hero = hero;
		this.grav = 5;
		this.level = level;
		this.isJumping = true;
		this.inBubble = false;
	}

	public void drawOn(Graphics2D g2) {
		g2.setColor(Color.MAGENTA);
		g2.fillRect(this.x, y, width, height);
	}

	public void move() {
		if (!isDead) {
			if (this.inBubble) {
				bubbleMove();
			} else {

				int difference = this.x - this.hero.getX();
				if (difference > 0) {
					this.x -= 1;
				} else {
					this.x += 1;
				}
				if (this.y - this.hero.getY() > 0) {

					jump();
				}
			}
		}
	}

	public void bubbleMove() {
		if (this.y > 10) {
			this.y -= 5;
		}
	}

	public void jump() {
		if (this.isJumping == false) {
			this.isJumping = true;
			this.savedYGoal = this.y - 110;
			this.grav = -5;
		}
	}

	public void gravity() {
		if (!this.inBubble) {
			if (this.grav == 5) {
				if (this.y < 500) {
					if (this.y <= this.savedYGoal) {
						this.savedYGoal = 500;
						this.y += this.grav;
					} else {
						this.y -= this.grav;
					}

				}
			} else if (this.grav == -5) {
				this.y += this.grav;

			} else {

			}
		}

	}

	public void doesCollide() {
		// System.out.println(this.level.getBars());

		for (Bar b : this.level.getBars()) {
			Boolean xCond = b.getX() <= this.x && this.x <= b.getX() + b.getWidth();
			Boolean yCond = b.getY() == this.y + this.height; // && this.y <= b.getY() + b.getHeight();
			if (xCond && yCond) {
				this.currentBar = b;
				break;
			}
		}
		if (this.currentBar != null) {
			if (this.currentBar.getX() <= this.x && this.x <= this.currentBar.getX() + this.currentBar.getWidth()
					&& this.currentBar.getY() == this.y + this.height) {
				// && this.y <= this.currentBar.getY() + this.currentBar.getHeight()) {
				if (this.savedYGoal == 500) {
					this.isJumping = false;
					this.grav = 0;

				}

			} else
				this.grav = 5;
		}

	}

	public void delete() {
		this.width = 0;
		this.height = 0;
		this.isDead = true;

	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y; 
	}

}
