import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Day17 {

	public static void main(String[] args) {

		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream("input/day17.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

		LinkedList<Integer> liste = new LinkedList<>();
		
		while(scan.hasNextLine()){
			Integer in = new Integer(scan.nextLine());
			liste.add(in);
		}
		
		Day17 day17 = new Day17(liste);
		
		int res = day17.recherche(0,150);
		System.out.println();
		System.out.println("Res1 = "+res+" combinaisons possibles");
		System.out.println("Res2 = "+day17.nbmin+" combinaisons de taille mini = "+day17.taillemin);
		
	}
	
	//global
	int taillemin = Integer.MAX_VALUE;
	int nbmin = 0;
	LinkedList<Integer> res = new LinkedList<>();
	LinkedList<Integer> liste = new LinkedList<>();
	
	public Day17(LinkedList<Integer> liste) {
		this.liste = liste;
	}
	
	@SuppressWarnings("unchecked")
	private int recherche(int j, int max){
		
		int cpt=0;
		LinkedList<Integer> copy = (LinkedList<Integer>) liste.clone();
		
		for (int i = j; i<liste.size(); i++) {
			Integer xi = liste.get(i);
			int tmpsum = somme(res)+xi;
			if(tmpsum <= max){
				res.addLast(xi);
				copy.remove(xi);
				if(tmpsum < max){
					cpt += recherche(i+1,max);
				}
				else{
					cpt++;
					if(res.size() < taillemin){
						taillemin = res.size();
						nbmin = 1;
						System.out.println("nouveau min de taille "+taillemin);
						for (Integer in : res) {
							System.out.print(in+".");
						}
						System.out.println();
					}
					else if(res.size() == taillemin){
						nbmin++;
						System.out.println("meme taille "+taillemin);
						for (Integer in : res) {
							System.out.print(in+".");
						}
						System.out.println();
					}
				}
				res.remove(xi);
			}
		}
		
		return cpt;
	}
	
	private static int somme(LinkedList<Integer> liste){
		int somme=0;
		for (Integer in : liste) {
			somme += in;
		}
		return somme;
	}
	
	
}
