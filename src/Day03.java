import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day03 {

	public static void main(String[] args) {
		
		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream("input/day3.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		int cpt=0;
		int maisons = 1;
		int x1=0,y1=0,x2=0,y2=0;
		String dejaVisite = "("+0+","+0+")";

		while(scan.hasNext()){
			//Santa
			char ch = scan.findInLine(".").charAt(0);
			cpt++;
		
			switch(ch){
			case '^' : y1++; break;
			case 'v' : y1--; break;
			case '<' : x1--; break;
			case '>' : x1++; break;
			}
			
			if(dejaVisite.indexOf("("+x1+","+y1+")")==-1){
				maisons++;
				dejaVisite += "("+x1+","+y1+")";
			}
			//Robo
			ch = scan.findInLine(".").charAt(0);
			cpt++;
		
			switch(ch){
			case '^' : y2++; break;
			case 'v' : y2--; break;
			case '<' : x2--; break;
			case '>' : x2++; break;
			}
			
			if(dejaVisite.indexOf("("+x2+","+y2+")")==-1){
				maisons++;
				dejaVisite += "("+x2+","+y2+")";
			}
			
		}
		
		System.out.println(cpt);
		System.out.println(dejaVisite);
		System.out.println(maisons);

	}

}
