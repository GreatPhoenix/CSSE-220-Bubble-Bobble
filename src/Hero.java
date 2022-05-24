import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Hero {

	private int x;
	private int y;
	private int height;
	private int width;
	private Color color;
	private Level level;
	private int grav;
	private Boolean xCond;
	private Boolean yCond;
	private Bar currentBar;
	private ArrayList<Bubble> bubbles;
	private boolean faceRight;
	private int savedYGoal;
	private boolean isJumping;
	protected boolean dead; 
	protected Integer lives; 

	public Hero(int x, int y, int height, int width, Level level) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.color = Color.BLACK;
		this.level = level;
		this.grav = 5;
		this.bubbles = new ArrayList<Bubble>();
		this.isJumping = false;
		this.dead = false; 
		this.lives = 3; 
	}

	public void drawOn(Graphics2D g2) {
		g2.setColor(this.color);
		g2.fillRect(this.x, this.y, this.width, this.height);
	}

	public void moveLeft() {
		this.faceRight = false;
		if (this.x > 0) {
			x -= 5;
		}
	}

	public void moveRight() {
		this.faceRight = true;
		if (this.x < 570) {

			if (this.x < 550) {

				x += 5;
			}
		}
	}

	public void jump() {
		if (this.isJumping == false){
			this.isJumping = true;
			this.savedYGoal = this.y - 110;
			this.grav = -5;
		}
		
		
	}

	public void gravity() {
		if (this.grav == 5) {
			if (this.y < 500) {
				if (this.y <= this.savedYGoal){
					this.savedYGoal = 500;
					this.y += this.grav;
				}
				else{
					this.y -= this.grav;
				}
				
			}
		} else if(this.grav == -5) {
			this.y += this.grav;
			
		} else {
			this.y += this.grav; 
		}

	}

	public void doesCollide() {
		// System.out.println(this.level.getBars());

		for (Bar b : this.level.getBars()) {
			this.xCond = b.getX() <= this.x + this.width && this.x <= b.getX() + b.getWidth();
			this.yCond = b.getY() == this.y + this.height; // && this.y <= b.getY() + b.getHeight();
			if (this.xCond && this.yCond) {
				this.currentBar = b;
				break;
			}
		}
		if (this.currentBar != null) {
			if (this.currentBar.getX() <= this.x + this.width && this.x <= this.currentBar.getX() + this.currentBar.getWidth()
					&& this.currentBar.getY() == this.y + this.height) {
				if (this.savedYGoal == 500){
					this.isJumping = false;
					this.grav = 0;
				}
				
			} else
				this.grav = 5;
		}
		}
	


	public void resetLocation() {
		this.x = 10;
		this.y = 500;
		this.dead = false; 
		this.grav = 5;
		this.isJumping = false;
	}

	public void shoot(int intensity) {
		Bubble bubble = new Bubble(this.x, this.y, 30, this.faceRight, intensity);
		this.bubbles.add(bubble);
	}


	public ArrayList<Bubble> getBubbles() {
		return this.bubbles;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public boolean isDead() {
		return this.dead; 
	}
	public void removeBubble() {
		this.bubbles.remove(1);
	}
	
	public void setColor(Color color) {
		this.color = color; 
	}
	
}
