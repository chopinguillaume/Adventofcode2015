import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15 {

	public static void main(String[] args) {

		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream("input/day15.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

		LinkedList<Ingredient> ingrList = new LinkedList<>();
		Pattern p = Pattern.compile("-?(\\d)+");
		

		
		while(scan.hasNextLine()){
			String input = scan.nextLine();

			String name = input.split(":")[0];

			Matcher m = p.matcher(input);

			m.find();
			int cap = Integer.parseInt(m.group());
			m.find();
			int dur = Integer.parseInt(m.group());
			m.find();
			int fla = Integer.parseInt(m.group());
			m.find();
			int tex = Integer.parseInt(m.group());
			m.find();
			int cal = Integer.parseInt(m.group());

			Ingredient ingr = new Ingredient(name, cap, dur, fla, tex, cal);
			System.out.println(ingr);
			ingrList.add(ingr);
		}
		System.out.println("------------------------------");

//		//Generation des permutations de partage
//		LinkedList<Integer> nbIngr = new LinkedList<>();
//		for(int n=0; n<ingrList.size(); n++){
//			nbIngr.add(new Integer(n));
//		}
//		LinkedList<LinkedList<Integer>> perms = partage(100,nbIngr);

		int iMax = -1;
		int maxScore = 0;
		LinkedList<int[]> listeScores = new LinkedList<>();
		
		int cpt=0;
		for(int i=0; i<=100; i++){
			for(int j=0; j<= 100-i; j++){
				for(int k=0; k<= 100-i-j; k++){
					int l = 100-i-j-k;
					int [] s = {i,j,k,l};
					
					int[] tabCal = new int[ingrList.size()];
					for (int ing = 0; ing < ingrList.size(); ing++) {
						tabCal[ing] = ingrList.get(ing).getCalories(s[ing]);
					}
					
					int totalCalories = totalCalories(tabCal);
					if(totalCalories == 500){

						listeScores.clear();
						for (int ing = 0; ing < ingrList.size(); ing++) {
							int [] sc = ingrList.get(ing).getScores(s[ing]);
							listeScores.add(sc);
						}
						
						int score = totalScore(listeScores);
						if(score > maxScore){
							iMax = cpt;
							maxScore = score;
							System.out.println(cpt+" :\t"+i+","+j+","+k+","+l+" : "+score);
						}
					}
					
//					if(cpt % 500 == 0){System.out.println(cpt);}
					cpt++;
				}
			}
		}
		
		System.out.println(iMax+" : "+maxScore);
		
		
		
//		//Calcul des scores
//		int maxTotal = 0;
//		int iMax = -1;
//		LinkedList<int[]> listeScores = new LinkedList<>();
//		int i=0;
//		while(i<perms.size()){
//			
//			listeScores.clear();
//
//			for (int j = 0; j < ingrList.size(); j++) {
//				int [] s = ingrList.get(j).getScores(perms.get(i).get(j));
//				listeScores.add(s);
//			}
//
//			int total = totalScore(listeScores);
//
//			if(total>maxTotal){
//				maxTotal = total;
//				iMax = i;
//			}
//			if(i%500 == 0){
//				System.out.println(i);
//			}
//			i++;
//		}
//
//		System.out.println("iMax = "+iMax+", maxtotal = "+maxTotal);


	}
	
	private static int totalCalories(int[] caloriesIngr){
		int total=0;
		for(int i=0; i<caloriesIngr.length; i++){
			total += caloriesIngr[i];
		}
		return total;
	}

	@SuppressWarnings("unchecked")
	private static LinkedList<LinkedList<Integer>> partage(int max, LinkedList<Integer> nbIngr){

		LinkedList<LinkedList<Integer>> res = new LinkedList<>();

		if(nbIngr.size() == 1){
			LinkedList<Integer> tmp = new LinkedList<>();
			tmp.add(new Integer(max));
			res.add(tmp);
			return res;
		}

		for(int n=0; n<=max; n++){
			LinkedList<Integer> copy = (LinkedList<Integer>) nbIngr.clone();
			copy.removeFirst();

			LinkedList<LinkedList<Integer>> perms = partage(max-n,copy);
			for (LinkedList<Integer> perm : perms) {
				perm.addFirst(new Integer(n));
				res.add(perm);
			}
		}


		return res;
	}

	private static int totalScore(LinkedList<int[]> listeScores){
		int total=1;
		int[] endScores = new int[4];

		for(int i=0; i<4; i++){
			int score = 0;
			for(int j=0; j<listeScores.size(); j++){
				score += listeScores.get(j)[i];
			}
			endScores[i] = Math.max(0,score);
		}

		for (int i = 0; i < endScores.length; i++) {
			total *= endScores[i];
		}

		return total;
	}

	private static class Ingredient{
		String name;
		int capacity;
		int durability;
		int flavor;
		int texture;
		int calories;

		public Ingredient(String n, int c, int d, int f, int t, int cal) {
			name=n;
			capacity=c;
			durability=d;
			flavor=f;
			texture=t;
			calories=cal;
		}

		public int[] getScores(int n){
			int[] scores = new int[4];

			scores[0] = n*capacity;
			scores[1] = n*durability;
			scores[2] = n*flavor;
			scores[3] = n*texture;

			return scores;
		}
		
		public int getCalories(int n){
			return calories*n;
		}

		@Override
		public String toString() {
			return name+" : C = "+capacity+", D = "+durability+", F = "+flavor+", T = "+texture+", Cal = "+calories;
		}

	}
}
