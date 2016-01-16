import java.util.LinkedList;

public class Day20 {

	public static void main(String[] args) {
		
		int input = 36000000;
		
		//ragequit
		
//		int ancien = 831600;
//		int maison = 1;
//		int nbC = nbCadeaux(maison);
//		int trouve=0;
////		while(maison > 0 && trouve<100000){
//		while(nbC<input){
//			trouve++;
////			if(nbC>=input){
////				System.out.println("Maison "+maison+" : "+nbC+" cadeaux");
////				trouve=0;
////			}
//			maison+=100;
//			nbC = nbCadeaux(maison);
//		}
//		System.out.println("Fin");
////		System.out.println("Rien trouvé depuis "+trouve);
//		System.out.println("Maison "+maison+" : "+nbC+" cadeaux");
		
		int[] maisons = new int[1000000];
		for(int elve = 1; elve < maisons.length; elve++){
			int cpt = 0;
			for(int maison = elve; maison < maisons.length; maison += elve){
				maisons[maison] += elve*11;
				cpt++;
				if(cpt==50){
					break;
				}
			}
		}
		
		for(int maison = 1; maison < maisons.length; maison += 1){
			if(maisons[maison] >= input){
				System.out.println("Maison "+maison+" : "+maisons[maison]+" cadeaux.");
				break;
			}
		}
		
	}
	
	private static int nbCadeaux(int maison){
		int cadeaux=0;
		LinkedList<Integer> elves = listElves(maison);
		
		for (Integer in : elves) {
			cadeaux += in*11;
		}
		return cadeaux;
	}
	
	private static LinkedList<Integer> listElves(int maison){
		LinkedList<Integer> res = new LinkedList<>();
		res.add(new Integer(maison));
		
		int start = maison/2;
		while(start>=1){
			if((maison % start) == 0){
				res.add(new Integer(start));
			}
			start--;
		}
		
		return res;
	}
}
