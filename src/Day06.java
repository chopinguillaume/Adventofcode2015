import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day06 {

	int[][] grille;

	public Day06() {
		grille = new int[1000][1000];

		for(int i=0; i<grille.length; i++){
			for(int j=0; j<grille[0].length; j++){
				grille[i][j]=0;
			}
		}
	}

	public static void main(String[] args) {

		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream("input/day6.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

		Day06 grille = new Day06();

		while(scan.hasNext()){
			String input = scan.nextLine();
			StructDay6 struct = convertInput(input);
			grille.parcoursGrille(struct);
		}
		
		
		System.out.println(grille.countBrightness());

	}
	
	private int countBrightness(){
		int count = 0;
		for(int i=0; i<grille.length; i++){
			for(int j=0; j<grille[0].length; j++){
				count += grille[i][j];
			}
		}
		return count;
	}

	private void parcoursGrille(StructDay6 struct){
		switch(struct.change){
		case Toggle : parcoursToggle(struct); break;
		case TurnOn : parcoursOn(struct); break;
		case TurnOff : parcoursOff(struct); break;
		}
	}

	private void parcoursOff(StructDay6 struct) {
		for(int i=struct.x1; i<=struct.x2; i++){
			for(int j=struct.y1; j<=struct.y2; j++){
				grille[i][j] = Math.max(0,grille[i][j]-1);
			}
		}
	}

	private void parcoursOn(StructDay6 struct) {
		for(int i=struct.x1; i<=struct.x2; i++){
			for(int j=struct.y1; j<=struct.y2; j++){
				grille[i][j]= grille[i][j] +1;
			}
		}
	}

	private void parcoursToggle(StructDay6 struct) {
		for(int i=struct.x1; i<=struct.x2; i++){
			for(int j=struct.y1; j<=struct.y2; j++){
				grille[i][j]= grille[i][j] +2;
			}
		}
	}

	private static StructDay6 convertInput(String input){
		String delims = "[ ,]+";
		String[] tokens = input.split(delims);
		int i=0;

		LightChange change;
		if(tokens[i].equals("turn")){
			i++;
			if(tokens[i].equals("on")){
				i++;
				change = LightChange.TurnOn;
			}
			else{
				i++;
				change = LightChange.TurnOff;
			}
		}
		else{
			i++;
			change = LightChange.Toggle;
		}
		int x1 = Integer.parseInt(tokens[i]); i++;
		int y1 = Integer.parseInt(tokens[i]); i++;
		i++;
		int x2 = Integer.parseInt(tokens[i]); i++;
		int y2 = Integer.parseInt(tokens[i]); i++;

		return new StructDay6(change, x1, y1, x2, y2);
	}

	private static class StructDay6{
		LightChange change;
		int x1, y1, x2, y2;

		public StructDay6(LightChange change, int x1,int y1,int x2,int y2) {
			this.change = change;
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
		}

		@Override
		public String toString() {
			return change+" "+x1+","+y1+" through "+x2+","+y2;
		}
	}

	private static enum LightChange {
		Toggle,TurnOn,TurnOff;

		@Override
		public String toString() {
			String res = "";
			switch(this){
			case Toggle : res = "toggle"; break;
			case TurnOn : res = "turn on"; break;
			case TurnOff : res = "turn off"; break;
			}
			return res;
		}
	}
}