import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day05 {
	
	static boolean contains3vowels(String s){
		int cpt =0;
		int i=0;
		while(cpt < 3 && i<s.length()){
			switch(s.charAt(i)){
			case 'a' :
			case 'e' :
			case 'i' :
			case 'o' :
			case 'u' : cpt++; break;
			}
			i++;
		}
		return cpt>=3;
	}
	
	static boolean contains2inarow(String s){
		int i=1;
		boolean res = false;
		while(!res && i<s.length()){
			res = s.charAt(i) == s.charAt(i-1);
			i++;
		}
		return res;
	}

	static boolean containsbad(String s){
		return s.contains("ab") || s.contains("cd") || s.contains("pq") || s.contains("xy");
	}
	
	static boolean isnice(String s){
		return contains3vowels(s) && contains2inarow(s) && ! containsbad(s);
	}
	
	
	static boolean isnice2(String s){
		return containsrepeat(s) && contains2spaced(s);
	}

	static boolean contains2spaced(String s) {
		int i=2;
		boolean res = false;
		while(!res && i<s.length()){
			res = s.charAt(i) == s.charAt(i-2);
			i++;
		}
		return res;
	}

	static boolean containsrepeat(String s) {
		int i=1;
		boolean res = false;
		while(!res && i<s.length()){
			String sub = s.substring(i-1,i+1);
			res = s.substring(i+1).contains(sub);
			i++;
		}
		return res;
	}

	public static void main(String[] args) {
		
		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream("input/day5.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		int cpt=0;
		int cpt2=0;
		
		while(scan.hasNext()){
			String input = scan.nextLine();
			if(isnice(input)){
				cpt ++;
			}
			if(isnice2(input)){
				cpt2 ++;
				System.out.println(input);
			}
		}

		System.out.println(cpt);
		System.out.println(cpt2);
		
		System.out.println(containsrepeat("ieodomkazucvgmuy"));
		System.out.println(containsrepeat("aaa"));
	}
}
