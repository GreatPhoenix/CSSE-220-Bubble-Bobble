import java.awt.Color;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {
	private ArrayList<Bar> bars;
	private int levelNumber; 

	public Level() {
		this.bars = new ArrayList<Bar>();
		this.levelNumber = 1; 
	}
	
	public int getLevel(){
		return this.levelNumber;
	}

	public void loadLevel(Scanner s, Graphics2D g2) {
		Integer[] params = new Integer[4];
		int i = 0;

		while (s.hasNextLine()) {
			String nextLine = s.nextLine();
			String in = "";
			if (s.hasNextLine() && !nextLine.equals(";")) {
				in = in + nextLine;
				params[i] = Integer.parseInt(in);
				if (i < 3) {
					i++;
				}
			} else {
				Bar b = new Bar(params[0], params[1], params[2], params[3], Color.RED);
				bars.add(b);
				i = 0;
				in = "";
			}
		}
		
		bars.add(new Bar());
	}

	public void loadFile(Graphics2D graphics2) {
		Scanner scanner;
		FileReader file;
		try {
			file = new FileReader(this.levelNumber +".txt");
			scanner = new Scanner(file);
			loadLevel(scanner, graphics2);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public ArrayList<Bar> getBars() {
		return this.bars;
	}
	
	public void incrementLevel(){
		if(this.levelNumber < 3){
			this.levelNumber += 1;
		} else {
			this.levelNumber = 1; 
		}
	}
	public void decrementLevel(){
		if(this.levelNumber == 1){
			this.levelNumber =  3; 
		} else {
			this.levelNumber -= 1; 
		}
	}
	
	public void setLevel(int L) {
		this.levelNumber = 1; 
	}
	
	public void barDeath(){
		this.bars = new ArrayList<Bar>();
	}

}
