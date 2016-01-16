package day7;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day7 {

	public static void main(String[] args) {

		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream("input/day7.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		HashMap<String,Expr> symTab = new HashMap<String, Expr>();

		while(scan.hasNext()){

			String input = scan.nextLine();

			String[] tokens = input.split(" -> ");

			String outCmd = tokens[1];

			String inCmd = tokens[0];
			Expr inputExpr = convertInput(inCmd);

			symTab.put(outCmd, inputExpr);
		}
		
		System.out.println("resultat = ");
		System.out.println(symTab.get("a").getValue(symTab,"a",0));

	}

	static Expr convertInput(String input){
		String[] tokens = input.split("[ ]+");
		Expr res=null;
		Terme tA = null;
		Terme tB = null;
		
		if(tokens.length == 1){
			//Assign,Valeur
			if(Character.isDigit(tokens[0].charAt(0))){
				//Valeur
				int n = Integer.parseInt(tokens[0]);
				res = new Expr(Operateur.VALEUR, null, null, n, 0);
			}
			else{
				//Assign
				tA = new Terme(tokens[0]);
				res = new Expr(Operateur.ASSIGN, tA, null, 0, 0);
			}
		}
		else if(tokens.length == 2){
			//Not
			tA = new Terme(tokens[1]);
			res = new Expr(Operateur.NOT, tA, null, 0, 0);
		}
		else if(tokens.length == 3){
			//And, Or, LShift, RShift
			if(Character.isDigit(tokens[0].charAt(0))){
				tA = new Terme(Integer.parseInt(tokens[0]));
			}
			else{
				tA = new Terme(tokens[0]);
			}
			if(Character.isDigit(tokens[2].charAt(0))){
				tB = new Terme(Integer.parseInt(tokens[2]));
			}
			else{
				tB = new Terme(tokens[2]);
			}
			switch(tokens[1]){
			case "AND" :
				res = new Expr(Operateur.AND, tA, tB, 0, 0);
				break;
			case "OR" :
				res = new Expr(Operateur.OR, tA, tB, 0, 0);
				break;
			case "LSHIFT" :
				res = new Expr(Operateur.LSHIFT, tA, null, 0, Integer.parseInt(tokens[2]));
				break;
			case "RSHIFT" :
				res = new Expr(Operateur.RSHIFT, tA, null, 0, Integer.parseInt(tokens[2]));
				break;
			default : 
				System.err.println("Operateur inconnu : "+tokens[1]); System.exit(0);
			}
		}
		else{
			System.err.println("Input de taille non attendue"); System.exit(0);
		}
		
		return res;
	}

	static int flipBits(int n, int k) {
		int mask = (1 << k) - 1;
		return ~n & mask;
	}
}
