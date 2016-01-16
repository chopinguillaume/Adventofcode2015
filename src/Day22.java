import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedList;

public class Day22 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		final Spell missile = new Spell("Missile", 53, 4, 0, 0, 0, 1);
		final Spell drain = new Spell("Drain", 73, 2, 0, 2, 0, 1);
		final Spell shield = new Spell("Shield", 113, 0, 0, 0, 7, 6);
		final Spell poison = new Spell("Poison", 173, 3, 0, 0, 0, 6);
		final Spell recharge = new Spell("Recharge", 229, 0, 101, 0, 0, 5);
		
		Spell[] spells = new Spell[5];
		spells[0] = missile;
		spells[1] = drain;
		spells[2] = shield;
		spells[3] = poison;
		spells[4] = recharge;
		
		
		Player playertest = new Player(50,500);
		Boss bosstest = new Boss(71,10);
		
		LinkedList<Spell> spellstest = new LinkedList<>();
		spellstest.add(recharge.clone());
		spellstest.add(poison.clone());
		spellstest.add(shield.clone());
		spellstest.add(drain.clone());
		spellstest.add(drain.clone());
		spellstest.add(drain.clone());
		spellstest.add(drain.clone());
		spellstest.add(drain.clone());
//		spellstest.add(missile.clone());
//		spellstest.add(poison.clone());
//		spellstest.add(missile.clone());
		
		int n = tryBattle(playertest, bosstest, spellstest);
		
		System.out.println("\nFin du combat : "+n);
		System.out.println(playertest);
		System.out.println(bosstest);
		
		System.exit(0);
		
		try {
			System.setOut(new PrintStream("outputDay22.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int eHP = 71;
//		eHP = 14;
		int eDmg = 10;
//		eDmg = 8;
		int pHP = 50;
//		pHP = 10;
		int pMana = 500;
//		pMana = 250;

		

		Boss boss = new Boss(eHP, eDmg);
		Player player = new Player(pHP, pMana);
		
		LinkedList<LinkedList<Spell>> listesNonTerminees = new LinkedList<>();
		LinkedList<LinkedList<Spell>> listesGagnantes = new LinkedList<>();
		LinkedList<LinkedList<Spell>> listesPerdantes = new LinkedList<>();
		
		for (int i = 0; i < spells.length; i++) {
			LinkedList<Spell> spellList = new LinkedList<>();
			spellList.add(spells[i].clone());
			listesNonTerminees.add(spellList);
		}
		
//		//debug override
//		LinkedList<Spell> spellList = new LinkedList<>();
//		spellList.add(spells[4].clone());
//		spellList.add(spells[2].clone());
//		listesNonTerminees = new LinkedList<>();
//		listesNonTerminees.add(spellList);

		while(listesNonTerminees.size() > 0){
			
			Iterator<LinkedList<Spell>> itListes = listesNonTerminees.iterator();
			
//			for (LinkedList<Spell> linkedList : listesNonTerminees) {
//				System.out.println(linkedList);
//			}
			
			
			LinkedList<LinkedList<Spell>> tmpliste = new LinkedList<>();
			while (itListes.hasNext()) {
				LinkedList<Spell> listeSorts = itListes.next();
				
				player = new Player(pHP, pMana);
				boss = new Boss(eHP, eDmg);
				
//				System.out.println("\n"+player); //debug
//				System.out.println(boss);
//				System.out.println(listeSorts);
				
				int outcome = tryBattle(player, boss, listeSorts);
				
				
				if(outcome == 0){
//					System.out.println("Outcome 0 sur la liste "+listeSorts);
					
					//on rajoute des sorts à la liste
					itListes.remove();
					for (int i = 0; i < spells.length; i++) {
						LinkedList<Spell> newList = new LinkedList<>();
						newList.addAll(listeSorts);
						newList.addLast(spells[i].clone());
						tmpliste.add((LinkedList<Spell>) newList.clone());
					}
				}
				else if(outcome == 1){
//					System.out.println("Outcome 1 sur la liste "+listeSorts);
					//ajouter la liste aux listes gagnantes
					listesGagnantes.add(listeSorts);
					itListes.remove();
				}
				else{
//					System.out.println("Outcome 2 sur la liste "+listeSorts);
//					System.out.println(player); //debug
//					System.out.println(boss);
					//ajouter la liste aux listes perdantes
					listesPerdantes.add(listeSorts);
					itListes.remove();
				}
			}
			listesNonTerminees.addAll(tmpliste);
		}
		
		System.out.println("******************* Listes Non Terminees = ");
		for (LinkedList<Spell> linkedList : listesNonTerminees) {
			System.out.println(linkedList);
		}
		System.out.println("******************* Listes Perdantes = ");
		for (LinkedList<Spell> linkedList : listesPerdantes) {
			System.out.println(linkedList);
		}
		System.out.println("******************* Listes Gagnantes = ");
		int minMana = Integer.MAX_VALUE;
		for (LinkedList<Spell> linkedList : listesGagnantes) {
			int mana = manaUsed(linkedList);
			minMana = Math.min(minMana,mana);
			System.out.println(mana+" : "+linkedList);
		}
		System.out.println("Min mana used to win = "+minMana);

	}
	
	
	private static int manaUsed(LinkedList<Spell> spells){
		int manaUsed = 0;
		
		for (Spell spell : spells) {
			manaUsed += spell.cost;
		}
		
		return manaUsed;
	}
	
	private static int tryBattle(Player p, Boss b, LinkedList<Spell> spells){
		//retourne 0 si la liste de sorts ne termine pas le combat, 1 si le joueur gagne, 2 si le joueur perd
		for (Spell spell : spells) {
			//tour du joueur
			System.out.println("\nTour du joueur : "+spell);
			System.out.println(p);
			System.out.println(b);
			p.applyEffects();
			b.applyEffects();
			if(b.hp <= 0){return 1;}
			if(p.hp <= 0){return 2;}
			p.apply(spell);
			b.apply(spell);
			if(b.hp <= 0){return 1;}
			if(p.hp <= 0){return 2;}
			
			//tour du boss
			System.out.println("\nTour du boss");
			System.out.println(p);
			System.out.println(b);
			p.applyEffects();
			b.applyEffects();
			if(b.hp <= 0){return 1;}
			if(p.hp <= 0){return 2;}
			p.hp -= (b.dmg - p.protection);
			if(b.hp <= 0){return 1;}
			if(p.hp <= 0){return 2;}
		}
		
		return 0;
	}

	private static boolean battle(Player p, Boss b, LinkedList<Spell> spells){
		System.out.println(p);
		System.out.println(b);
		int i=0;
		while(true) {
			Spell spell = null;
			if(i<spells.size()){
				spell = spells.get(i);
			}
			i++;
			System.out.println("\nPlayer's turn ");
			if(spell != null){
				System.out.println(spell);
			}
			//tour du joueur

			p.applyEffects();
			b.applyEffects();
			
			if(b.hp <= 0){
				System.out.println("Joueur a gagné");
				System.out.println(p);
				System.out.println(b);
				return true;
			}
			if(p.hp <= 0){
				System.out.println("Boss a gagné");
				System.out.println(p);
				System.out.println(b);
				return false;
			}
			
			if(spell != null && !p.apply(spell)){
				System.out.println("--------------Impossible de cast "+spell.name);
			}
			b.apply(spell);

			if(b.hp <= 0){
				System.out.println("Joueur a gagné");
				System.out.println(p);
				System.out.println(b);
				return true;
			}
			if(p.hp <= 0){
				System.out.println("Boss a gagné");
				System.out.println(p);
				System.out.println(b);
				return false;
			}
			System.out.println(p);
			System.out.println(b);

			//tour du boss

			System.out.println("\nBoss' turn");

			p.applyEffects();
			b.applyEffects();
			
			if(b.hp <= 0){
				System.out.println("Joueur a gagné");
				System.out.println(p);
				System.out.println(b);
				return true;
			}
			if(p.hp <= 0){
				System.out.println("Boss a gagné");
				System.out.println(p);
				System.out.println(b);
				return false;
			}
			
			p.hp -= (b.dmg - p.protection);

			if(b.hp <= 0){
				System.out.println("Joueur a gagné");
				System.out.println(p);
				System.out.println(b);
				return true;
			}
			if(p.hp <= 0){
				System.out.println("Boss a gagné");
				System.out.println(p);
				System.out.println(b);
				return false;
			}
			System.out.println(p);
			System.out.println(b);
		}
	}

	
	private static class Player{
		int hp;
		int mana;
		int protection;
		int shieldcd;
		int rechargecd;

		public Player(int hp, int mana) {
			this.hp = hp;
			this.mana = mana;
			this.protection = 0;
			this.shieldcd = 0;
			this.rechargecd = 0;
		}

		public boolean apply(Spell spell){
			boolean apply = false;
			if(spell != null){
				if(mana >= spell.cost){
					apply = true;
					
					mana -= spell.cost;
					
					switch(spell.name){
					case "Drain" : hp += spell.heal; break;
					case "Shield" : if(shieldcd == 0){shieldcd = spell.cd; protection = spell.shield;} break;
					case "Recharge" : if(rechargecd == 0){rechargecd = spell.cd;} break;
					default : break;
					}
				}
			}
			return apply;
		}

		public void applyEffects(){
			if(rechargecd > 1){
				mana += 101;
				rechargecd--;
			}
			if(shieldcd > 1){
				shieldcd--;
			}
			else{
				protection = 0;
			}
		}

		@Override
		public String toString() {
			return "Player has "+hp+"HP, "+protection+" armor and "+mana+" mana";
		}
	}

	private static class Boss{
		int hp;
		int dmg;
		Spell poison;

		public Boss(int hp, int dmg) {
			this.hp = hp;
			this.dmg = dmg;
			this.poison = null;
		}

		public void apply(Spell spell){
			if(spell != null){
				switch(spell.name){
				case "Poison" : if(poison == null){poison = spell;} break;
				case "Missile" : //idem Drain
				case "Drain" : hp -= spell.dmg; break;
				default : break;
				}
			}
		}

		public void applyEffects(){
			if(poison != null && poison.cd > 0){
				hp -= poison.dmg;
				poison.cd--;
			}
			if(poison != null && poison.cd <= 0){
				poison = null;
			}
		}

		@Override
		public String toString() {
			return "Boss has "+hp+" HP";
		}
	}

	private static class Spell{
		String name;
		int cost;
		int dmg;
		int recharge;
		int heal;
		int shield;
		int cd;

		public Spell(String name, int cost, int dmg, int recharge, int heal, int shield, int cd) {
			this.name = name;
			this.cost = cost;
			this.dmg = dmg;
			this.recharge = recharge;
			this.heal = heal;
			this.shield = shield;
			this.cd = cd;
		}

		public Spell clone(){
			return new Spell(name, cost, dmg, recharge, heal, shield, cd);
		}

		@Override
		public String toString() {
			return name;
		}
	}

}
