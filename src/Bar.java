import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bar {

	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	
	public Bar(){
		this.x = 0;
		this.y = 530;
		this.height = 50;
		this.width = 600;
		this.color = Color.RED;
	}

	public Bar(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.color = color; 
	}

	public void drawOn(Graphics2D g2) {
		Rectangle box = new Rectangle(this.x, this.y, this.width, this.height);
		g2.setColor(color);
		g2.fill(box);
		g2.draw(box);
	}
//mmm
	public String toString() {
		String tmp = "Bar's variables: " + this.x + ", " + this.y + ", " + this.width + ", " + this.height;
		return tmp;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.y;
	}

}
