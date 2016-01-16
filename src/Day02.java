import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day02 {

	public static void main(String[] args) {
		
		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream("input/day2.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		int cpt = 0;
		int ruban = 0;
		while(scan.hasNext()){
			
			String input = scan.nextLine();
			String[] tab = input.split("x");
			int[] tabint = new int[3];
			
			for (int i = 0; i < tab.length; i++) {
				tabint[i] = Integer.parseInt(tab[i]);
			}
			
			cpt+= calcul(tabint[0], tabint[1], tabint[2]);
			ruban+= calculruban(tabint[0], tabint[1], tabint[2]);
		}
		
		System.out.println(cpt);
		System.out.println(ruban);
	}

	private static int calculruban(int i, int j, int k) {
		int res = i*j*k + 2*i+2*j+2*k - 2* Math.max(i, Math.max(j, k));
		return res;
	}

	private static int calcul(int i, int j, int k) {
		int res = 2*i*j + 2*j*k + 2*i*k;
		int tmp = i*j*k / Math.max(i, Math.max(j, k));
		return res + tmp;
	}

}
