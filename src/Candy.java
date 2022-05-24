import java.awt.Color;
import java.awt.Graphics2D;

public class Candy {

	private int x;
	private int y;
	private int radius;
	private Level level;
	private Bar currentBar;
	private Boolean stop; 

	public Candy(int x, int y, int radius, Level level) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.level = level;
		this.stop = false; 
	}

	public void drawOn(Graphics2D g2) {
		g2.setColor(Color.GREEN);
		g2.fillOval(this.x, this.y, this.radius, this.radius);
	}

	public void doesCollide() {
		// System.out.println(this.level.getBars());

		for (Bar b : this.level.getBars()) {
			Boolean xCond = b.getX() <= this.x && this.x <= b.getX() + b.getWidth();
			Boolean yCond = b.getY() == this.y + this.radius; // && this.y <= b.getY() + b.getHeight();
			if (xCond && yCond) {
				this.currentBar = b;
				break;
			}
		}
		if (this.currentBar != null) {
			if (this.currentBar.getX() <= this.x && this.x <= this.currentBar.getX() + this.currentBar.getWidth()
					&& this.currentBar.getY() == this.y + this.radius) {
				this.stop = true; 

			}

		}
	}
	
	public void Move() {
		if(!this.stop) {
			this.y += 5; 
		}
	}
	
	public void delete() {
		this.radius =  0; 
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y; 
	}

}
