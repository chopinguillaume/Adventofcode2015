import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day23 {

	public static void main(String[] args) {

		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream("input/day23.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

		ArrayList<String> instrList = new ArrayList<>();

		while(scan.hasNextLine()){
			instrList.add(scan.nextLine());
		}
		
		System.out.println(instrList+"\n");

		instrTab = instrList.toArray(new String[0]);

		execute();
		System.out.println();
		System.out.println("a = "+a);
		System.out.println("b = "+b);
		System.out.println("PC = "+PC);

	}

	static int a=1;
	static int b=0;
	static int PC=0;
	static String[] instrTab;

	private static void execute(){
		if(PC >= instrTab.length){
			return;
		}
		String instr = instrTab[PC];

		String[] split = instr.split(" ");
		switch(split[0]){
		case "hlf": 
			half(split[1]); break;
		case "tpl": 
			triple(split[1]); break;
		case "inc": 
			incrementer(split[1]); break;
		case "jmp": 
			jump(Integer.parseInt(split[1])); break;
		case "jie": 
			jumpie(split[1].substring(0, 1),Integer.parseInt(split[2])); break;
		case "jio":
			jumpio(split[1].substring(0, 1),Integer.parseInt(split[2])); break;
		default: System.err.println("Instruction inconnue"); System.exit(0); break;
		}
		execute();

	}

	private static void jumpio(String substring, int parseInt) {
		boolean cond = false;
		switch(substring){
		case "a" : cond = a==1; break;
		case "b" : cond = b==1; break;
		}
		if(cond){
			PC += parseInt;
		}
		else{
			PC++;
		}
	}

	private static void jumpie(String substring, int parseInt) {
		boolean cond = false;
		switch(substring){
		case "a" : cond = a%2==0; break;
		case "b" : cond = b%2==0; break;
		}
		if(cond){
			PC += parseInt;
		}
		else{
			PC++;
		}
	}

	private static void jump(int parseInt) {
		PC += parseInt;
	}

	private static void triple(String string) {
		switch(string){
		case "a" : a *= 3; break;
		case "b" : b *= 3; break;
		}
		PC++;
	}

	private static void half(String string) {
		switch(string){
		case "a" : a /= 2; break;
		case "b" : b /= 2; break;
		}
		PC++;
	}

	private static void incrementer(String string) {
		switch(string){
		case "a" : a++; break;
		case "b" : b++; break;
		}
		PC++;
	}

}
