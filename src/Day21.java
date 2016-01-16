import java.util.Collections;
import java.util.LinkedList;

public class Day21 {

	public static void main(String[] args) {
		
		final int enemyHP = 109;
		final int enemyDmg = 8;
		final int enemyArmor = 2;
		
		final int playerHP = 100;
		final int playerDmg = 0;
		final int playerArmor = 0;
		
		Item[] weapons = new Item[5];
		Item[] armors = new Item[5];
		Item[] rings = new Item[6];
		
		weapons[0] = new Item("Dagger",8,4,0);
		weapons[1] = new Item("Shortsword",10,5,0);
		weapons[2] = new Item("Warhammer",25,6,0);
		weapons[3] = new Item("Longsword",40,7,0);
		weapons[4] = new Item("Greataxe",74,8,0);
		
		armors[0] = new Item("Leather",13,0,1);
		armors[1] = new Item("Chainmail",31,0,2);
		armors[2] = new Item("Splintmail",53,0,3);
		armors[3] = new Item("Bandedmail",75,0,4);
		armors[4] = new Item("Platemail",102,0,5);
		
		rings[0] = new Item("Damage +1",25,1,0);
		rings[1] = new Item("Damage +2",50,2,0);
		rings[2] = new Item("Damage +3",100,3,0);
		rings[3] = new Item("Defense +1",20,0,1);
		rings[4] = new Item("Defense +2",40,0,2);
		rings[5] = new Item("Defense +3",80,0,3);
		
		int goldUsed = 0;
		int minGold = Integer.MAX_VALUE;
		boolean win = win(playerHP, playerDmg, playerArmor, enemyHP, enemyDmg, enemyArmor);
		
		//generation des stuffs
		LinkedList<Stuff> stuffs = listStuff(weapons, armors, rings);
		
		int coststuff=0;
		for (Stuff stuff : stuffs) {
			coststuff=0;
			for (int i = 0; i < stuff.items.length; i++) {
				Item item = stuff.items[i];
				if(item != null){
					coststuff += item.cost;
				}
			}
			stuff.cost = coststuff;
		}
		
		//tri des stuffs par prix croissants
		Collections.sort(stuffs);
		
		for (Stuff stuff : stuffs) {
			int pHP = playerHP, pAtk = playerDmg, pArmor = playerArmor;
			for (int i = 0; i < stuff.items.length; i++) {
				Item item = stuff.items[i];
				if(item != null){
					pAtk += item.dmg;
					pArmor += item.armor;
				}
			}
			if(!win(pHP, pAtk, pArmor, enemyHP, enemyDmg, enemyArmor)){
				System.out.println(stuff);				
			}
		}
		
	}
	
	private static LinkedList<Stuff> listStuff(Item[] weapons, Item[] armors, Item[] rings){
		LinkedList<Stuff> res = new LinkedList<>();
		
		for (int w = 0; w < weapons.length; w++) {
			Stuff curstuff = new Stuff();
			Item[] items = new Item[4];
			items[0] = weapons[w];
			curstuff.items = items;
			res.add(curstuff); // stuff avec 1 arme (pour toutes les armes)
			for (int r1 = 0; r1 < rings.length; r1++) {
				curstuff = new Stuff();
				items = new Item[4];
				items[0] = weapons[w];
				items[2] = rings[r1];
				curstuff.items = items;
				res.add(curstuff); // stuff avec 1 arme et 1 anneau (pour 1 arme et tous les anneaux)
				for (int r2 = r1; r2 < rings.length; r2++) {
					if(r2 != r1){
						curstuff = new Stuff();
						items = new Item[4];
						items[0] = weapons[w];
						items[2] = rings[r1];
						items[3] = rings[r2];
						curstuff.items = items;
						res.add(curstuff); // stuff avec 1 arme et 2 anneaux différents
					}
				}
			}
			for (int a = 0; a < armors.length; a++) {
				curstuff = new Stuff();
				items = new Item[4];
				items[0] = weapons[w];
				items[1] = armors[a];
				curstuff.items = items;
				res.add(curstuff); // stuff avec 1 arme et 1 armure
				for (int r1 = 0; r1 < rings.length; r1++) {
					curstuff = new Stuff();
					items = new Item[4];
					items[0] = weapons[w];
					items[1] = armors[a];
					items[2] = rings[r1];
					curstuff.items = items;
					res.add(curstuff); // stuff avec 1 arme, 1 armure et 1 anneau
					for (int r2 = r1; r2 < rings.length; r2++) {
						if(r2 != r1){
							curstuff = new Stuff();
							items = new Item[4];
							items[0] = weapons[w];
							items[1] = armors[a];
							items[2] = rings[r1];
							items[3] = rings[r2];
							curstuff.items = items;
							res.add(curstuff); // stuff avec 1 arme, 1 armure et 2 anneaux différents
						}
					}
				}
			}
			
		}
		
		return res;
	}
	
	private static boolean win(int pHP, int pAtk, int pArmor, int eHP, int eAtk, int eArmor){
		double nbToursJ = eHP / Math.max((pAtk - eArmor),1);
		double nbToursE = pHP / Math.max((eAtk - pArmor),1);
		
		return nbToursJ <= nbToursE;
	}
	
	static class Stuff implements Comparable<Stuff>{
		Item[] items;
		int cost;
		@Override
		public int compareTo(Stuff o) {
			return cost - o.cost;
		}
		
		@Override
		public String toString() {
			StringBuilder res = new StringBuilder();
			res.append(cost);
			for (int i = 0; i < items.length; i++) {
				if(items[i] != null){
					res.append(", "+items[i]);
				}
			}
			return res.toString();
		}
	}
	
	static class Item{
		String name;
		int cost;
		int dmg;
		int armor;
		
		public Item(String name, int cost, int dmg, int armor) {
			this.name = name;
			this.cost = cost;
			this.dmg = dmg;
			this.armor = armor;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}

}
