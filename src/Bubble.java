import java.awt.Color;
import java.awt.Graphics2D;

public class Bubble {

	private int x;
	private int y;
	private int radius;
	private Boolean faceRight;
	private Boolean first;
	private int startX;
	private int counter;
	private Boolean isDead; 
	protected Boolean isEmpty; 
	private int intensity; 

	public Bubble(int x, int y, int radius, Boolean faceRight, int intensity) {
		// TODO Auto-generated constructor stub

		this.x = x;
		this.y = y;
		this.startX = x;
		this.radius = radius;
		this.faceRight = faceRight;
		this.first = true;
		this.counter = 0; 
		this.isDead = false; 
		this.isEmpty = true; 
		this.intensity = intensity; 
	}

	public void drawOn(Graphics2D g2) {
		g2.setColor(Color.CYAN);
		g2.fillOval(this.x, this.y, this.radius, this.radius);
	}

	public void move() {
		if (first) {
			if (faceRight) {
				int distance = this.startX + 60 + this.intensity;
				if (this.x < distance) {
					if(this.intensity > 75) {
						this.x += 15; 
					} else {
						this.x += 5;
					}
				} else {
					this.first = false;
				}
			} else {
				int distance2 = this.startX - 60 - this.intensity;
				if (this.x > distance2) {
					if(this.intensity > 75) {
						this.x -= 15; 
					} else {
						this.x -= 5;
					}
				} else {
					this.first = false;
				}
			}
		} else {
			if (this.y > 10) {
				this.y -= 5;
			}
		}
		this.counter += 1; 
		if(this.counter > 150) {
			delete();
			this.counter = 0; 
		}
	}
	
	public void delete() {
		this.radius = 0; 
		this.isDead = true; 
		this.isEmpty = true; 
	}
	
	public Boolean getIsDead() {
		return this.isDead; 
		
	}
	
	public int getX() {
		return this.x; 
	}
	public int getY() {
		return this.y; 
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y  = y; 
	}

}
