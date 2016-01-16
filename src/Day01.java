import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day01 {

	public static void main(String[] args) {
		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream("input/day1.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

		String input = scan.nextLine();
		char[] tab = input.toCharArray();
		
		int cpt = 0;
		
		for (char c : tab) {
			if(c == '('){
				cpt++;
			}
			else{
				cpt--;
			}
		}
		
		System.out.println(cpt);
		
		cpt = 0;
		int i = 0;
		while(cpt != -1 && i<tab.length){
			if(tab[i] == '('){
				cpt++;
			}
			else{
				cpt--;
			}
			i++;
		}
		
		System.out.println(i);
		
	}
}
