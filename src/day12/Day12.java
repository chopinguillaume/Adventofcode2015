package day12;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;

public class Day12 {

//	public static void main(String[] args) {
//
//		Scanner scan = null;
//		try {
//			scan = new Scanner(new FileInputStream("input/day12test.txt"));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			System.exit(0);
//		}
//		
//		String input = scan.nextLine();
//		
//		Pattern p = Pattern.compile("\\{(.*)\\}");
//		Matcher m = p.matcher(input);
//		
//		printRec(input);
//		
////		int cpt=0;
////		while(m.find()){
////			
////			int n = Integer.parseInt(m.group());
////			cpt += n;
////		}
////		System.out.println(cpt);
//		
//	}
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		ANTLRFileStream input = new ANTLRFileStream("input/day12.txt");
		day12Lexer lexer = new day12Lexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		day12Parser parser = new day12Parser(tokens);
		int res = parser.doc();
		System.out.println(res);
	}
	
	private static void printRec(String s){
		Pattern p = Pattern.compile("\\{([^,]*)(,(.*))*\\}");
		Matcher m = p.matcher(s);
		
		while(m.find()){
			System.out.println(m.group());
			for(int i=0; i<=m.groupCount(); i++){
				System.out.println(i+" = "+m.group(i));
			}
			System.out.println(m.group(1).contains("\"red\""));
			printRec(m.group(1));
		}
	}

}
