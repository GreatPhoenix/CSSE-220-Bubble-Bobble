import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Enemy2 {

	private int x;
	private int y;
	private int width;
	private int height;
	protected Boolean inBubble;
	private ArrayList<Projectile> projectiles;
	protected Boolean isDead;

	public Enemy2(int x, int y, int width, int height) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.inBubble = false;
		this.projectiles = new ArrayList<Projectile>();
		this.isDead = false;
	}

	public void drawOn(Graphics2D g2) {
		g2.setColor(Color.ORANGE);
		g2.fillRect(this.x, y, width, height);
	}

	public void move() {
		if (inBubble) {
			bubbleMove();
		} else {

			if (this.x == 500) {
				if (this.y > 50) {
					this.y -= 5;

				}
			}
			if (this.x == 50) {
				if (this.y < 500) {
					this.y += 5;

				}
			}

			if (this.y == 500) {
				if (this.x <= 500) {
					this.x += 5;

				}
			}
			if (this.y == 50) {
				if (this.x > 50) {
					this.x -= 5;

				}
			}
			if (this.y == 10) {
				this.y = 50;
			}

		}
	}

	public void delete() {
		this.width = 0;
		this.height = 0;
		this.projectiles = new ArrayList<Projectile>();
		this.isDead = true;
	}

	public void Shoot() {
		if (!this.isDead) {
			if (!this.inBubble) {
				Projectile pew = new Projectile(this.x, this.y, 10);
				this.projectiles.add(pew);
			}
		}
	}

	public ArrayList<Projectile> getProjectiles() {
		return this.projectiles;
	}

	public void bubbleMove() {
		if (this.y > 10) {
			this.y -= 5;
		}
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
