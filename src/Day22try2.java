
public class Day22try2 {

	public static void main(String[] args) {
		int pHP = 50;
		int pMana = 500;
		int bHP = 71;
		fight(pHP,pMana,bHP,0,0,0,0,0);
		System.out.println("minmana finale = "+minMana);

	}
	
	/*sorts : 
	 * 0 : missile, bHP -= 4.					cost : 53
	 * 1 : drain, bHP -= 2, pHP+=2;				cost : 73
	 * 2 : shield, armor = 7, shield = 6		cost : 113
	 * 3 : poison, poison = 6 (bHP -= 3)		cost : 173
	 * 4 : recharge, regen = 5 (pMana += 101)	cost : 229
	 * 
	 */
	
	static int[] costs = {53,73,113,173,229};
	static int minMana = Integer.MAX_VALUE;
	
	
	private static void fight(int pHP, int pMana, int bHP, int armor, int manaUsed, int poison, int shield, int regen){
		
		//tour du joueur
		//appliquer les effets
		
		pHP--;
		if(pHP==0){
			return;
		}
		
		shield--;
		if(shield==0){
			armor=0;
		}
		
		if(poison > 0){
			poison--;
			bHP -= 3;
		}
		
		if(regen > 0){
			regen--;
			pMana += 101;
		}
		
		if(bHP <= 0){ //le boss est mort par les effets, c'est fini
			System.out.println("win avec manaUsed = "+manaUsed);
			minMana = Math.min(manaUsed,minMana);
			return;
		}
		
		//lancer un sort

		for(int i=0; i<5; i++){ //pour tous les sorts existants

			if((i==2 && shield>0) || (i==3 && poison>0) || (i==4 && regen>0)){continue;}
			if(pMana < costs[i]){continue;}

			useSpell(i, pHP, pMana - costs[i], bHP, armor, manaUsed + costs[i], poison, shield, regen);



		}
	}
	
	private static void useSpell(int i, int pHP, int pMana, int bHP, int armor, int manaUsed, int poison, int shield, int regen){
		

		if(manaUsed > minMana){
			return;
		}
		
		//lancer un sort
		switch(i){
		case 0 : bHP-=4; break;
		case 1 : bHP -= 2; pHP+=2;	break;
		case 2 : armor = 7; shield = 6; break;
		case 3 : poison = 6; break;
		case 4 : regen = 5; break;
		default : break;
		}
		
		//tour du boss
		//appliquer les effets
		
		shield--;
		
		if(poison > 0){
			poison--;
			bHP -= 3;
		}
		
		if(regen > 0){
			regen--;
			pMana += 101;
		}
		
		if(bHP <= 0){ //le boss est mort par les effets, c'est fini
			System.out.println("win avec manaUsed = "+manaUsed);
			minMana = Math.min(minMana,manaUsed);
			return;
		}
		
		//le boss attaque
		
		pHP-=10-armor;
		if(pHP>0){
			//si c'est pas fini, on re fight
			fight(pHP, pMana, bHP, armor, manaUsed, poison, shield, regen);
		}
		
	}

}
