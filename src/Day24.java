import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Day24 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream("input/day24.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

		while(scan.hasNextLine()){
			weightList.add(new Integer(scan.nextLine()));
		}
		
		//calcul du poids
		totalSum = somme(weightList) / 4;
		System.out.println("Les groupes doivent avoir un poids total de "+totalSum);
		
		//calcul des arrangements
		recherche(0);
		
		Collections.sort(solutions, new Comparator<ArrayList>(){
			@Override
			public int compare(ArrayList o1, ArrayList o2) {
				return o1.size() - o2.size();
			}
		});
		
		int tailleMini = solutions.get(0).size();
		
		ArrayList<ArrayList<Integer>> minSolutions = new ArrayList<>();
		
		for (int i = 0; i < solutions.size(); i++) {
			if(solutions.get(i).size() == tailleMini){
				minSolutions.add(solutions.get(i));
			}
			else{
				break;
			}
		}
		
		System.out.println("Listes minimales");
		
		long minQE = Long.MAX_VALUE;
		
		for (ArrayList<Integer> list : minSolutions) {
			System.out.println(QE(list)+" : "+list);
			minQE = Math.min(minQE, QE(list));
		}
		
		System.out.println("QE min = "+minQE);
		
		
		
		
	}
	
	static ArrayList<Integer> weightList = new ArrayList<>();
	static ArrayList<Integer> curSol = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> solutions = new ArrayList<>();
	static int totalSum;

	
	@SuppressWarnings("unchecked")
	private static void recherche(int j){
		
		ArrayList<Integer> copy = (ArrayList<Integer>) weightList.clone();
		
		for (int i=j; i<weightList.size(); i++){
			Integer w = weightList.get(i);
			
			int curSum = somme(curSol) + w;
			
			if(curSum <= totalSum){
				curSol.add(w);
				copy.remove(w);
				if(curSum < totalSum){//on continue
					recherche(i+1);
				}
				else{//on a une sequence solution
					solutions.add((ArrayList<Integer>) curSol.clone());
				}
				curSol.remove(w);
			}
			
		}
		
	}
	
	private static int somme(ArrayList<Integer> liste){
		int somme=0;
		for (Integer in : liste) {
			somme += in;
		}
		return somme;
	}
	
	private static long QE(ArrayList<Integer> liste){
		long qe=1;
		for (Integer in : liste) {
			qe *= in;
		}
		return qe;
	}
	

	private static int[] toIntArray(ArrayList<Integer> list){
		int[] ret = new int[list.size()];
		for(int i = 0;i < ret.length;i++)
			ret[i] = list.get(i);
		return ret;
	}

}
