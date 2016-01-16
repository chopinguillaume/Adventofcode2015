import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day18 {

	public static void main(String[] args) {

		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream("input/day18.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

		Pattern p = Pattern.compile("(.)");

		boolean[][] grille = new boolean[100][100];
		int i=0;
		while(scan.hasNextLine()){
			int j=0;
			String input = scan.nextLine();
			Matcher m = p.matcher(input);
			while(m.find()){
				String cas = m.group();
				grille[i][j] = cas.equals("#");
				j++;
			}
			i++;
		}

		grille = forceCornersON(grille);
		printGrille(grille);

		for(int n=0; n<100; n++){
			grille = nextStep(grille);
			grille = forceCornersON(grille);
			printGrille(grille);
		}

	}
	
	private static boolean[][] forceCornersON(boolean[][] grille){
		int n = grille.length-1;
		grille[0][0]=true;
		grille[0][n]=true;
		grille[n][0]=true;
		grille[n][n]=true;
		return grille;
	}

	private static void printGrille(boolean[][] grille){
		for(int ii=0; ii<grille.length; ii++){
			for(int jj=0; jj<grille.length; jj++){
				if(grille[ii][jj]){
					System.out.print("#");
				}else{
					System.out.print(".");
				}
			}
			System.out.println();
		}
		System.out.println(compteON(grille)+"\n");
	}

	private static boolean[][] nextStep(boolean[][] grille){
		boolean[][] res = new boolean[grille.length][grille.length];

		for(int i=0; i<grille.length; i++){
			for (int j = 0; j < grille.length; j++) {
				int nb = nbVoisinsON(grille,i,j);
				if(grille[i][j]){// ON : reste ON si 2 ou 3 voisins ON, passe OFF sinon
					res[i][j] = nb==2 || nb==3;
				}
				else{//OFF : passe ON si 3 voisins ON, reste OFF sinon
					res[i][j] = nb==3;
				}
			}
		}
		return res;
	}

	private static int nbVoisinsON(boolean[][] grille, int i, int j) {
		int nb=0;
		for(int ii=i-1; ii<=i+1; ii++){
			for(int jj=j-1; jj<=j+1; jj++){
				if(ii>=0 && ii<grille.length && jj>=0 && jj<grille.length && grille[ii][jj]){
					if(ii != i || jj!=j){
						nb++;
					}
				}
			}
		}
		return nb;
	}
	
	private static int compteON(boolean[][] grille){
		int cpt=0;
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille.length; j++) {
				if(grille[i][j]){
					cpt++;
				}
			}
		}
		return cpt;
	}

}
