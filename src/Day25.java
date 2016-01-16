
public class Day25 {

	public static void main(String[] args) {
		

		int x = 2981;
		int y = 3075;
		
		long[][] grille = new long[x+y-1][x+y-1];
		
		for (int i = 0; i < grille.length; i++) {
			for(int j = 0; j <= i; j++){
				grille[i-j][j] = next(cpt);
			}
			if(cpt > Long.MAX_VALUE){
				System.exit(0);
			}
		}
		
		
//		for (int i = 0; i < grille.length; i++) {
//			System.out.print((i+1)+"  |  ");
//			for (int j = 0; j < grille.length; j++) {
//				if(grille[i][j] != 0){
//					System.out.print(grille[i][j]+"    \t");
//				}
//			}
//			System.out.println();
//		}
		System.out.println("\nCase voulue x,y = "+grille[x-1][y-1]);

	}

	static long cpt = 20151125;

	private static long next(long cur){
		long tmp = cur;
		cpt = (cur * 252533) % 33554393;
		return tmp;
	}
	
	

}
