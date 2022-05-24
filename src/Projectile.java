import java.awt.Color;
import java.awt.Graphics2D;

public class Projectile {

	private int x;
	private int y;
	private int radius;
	private int startingX;
	private int startingY; 

	public Projectile(int x, int y, int radius) {
		// TODO Auto-generated constructor stub

		this.x = x;
		this.y = y;
		this.startingX = x;
		this.startingY = y; 
		this.radius = radius;
	}

	public void drawOn(Graphics2D g2) {
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillOval(this.x, this.y, this.radius, this.radius);
	}

	public void move() {
		if(this.startingX == 50) {
			this.x += 5; 
		}
		if(this.startingX == 500) {
			this.x -= 5; 
		}
		
		if(this.startingY == 50) {
			this.y += 5; 
		}
		if(this.startingY == 500) {
			this.y-= 5; 
		}
	}
	
	public void setSize(int radius){
		this.radius = radius; 
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y; 
	}
}
