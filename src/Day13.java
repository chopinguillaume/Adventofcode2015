import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Day13 {
	public static void main(String[] args) {

		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream("input/day13.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

		LinkedList<Association> assocs = new LinkedList<>();
		LinkedList<String> noms = new LinkedList<>();

		while(scan.hasNextLine()){
			String input = scan.nextLine();
			Association newAssoc = parseInput(input);
			assocs.add(newAssoc);
			if(!noms.contains(newAssoc.nom)){
				noms.add(newAssoc.nom);
			}
		}
		
		for (String nom : noms) {
			assocs.add(new Association("Moi",nom,0));
			assocs.add(new Association(nom,"Moi",0));
		}
		noms.add("Moi");
		
		Collections.sort(noms);
		Collections.sort(assocs);
		

		System.out.println("---------Noms---------");
		for (String nom : noms) {
			System.out.println(nom);
		}
		System.out.println("-----Associations-----");
		for (Association a : assocs) {
			System.out.println(a);
		}
		System.out.println("----------------------");

		LinkedList<LinkedList<String>> perms = permutations(noms,0);
		LinkedList<LinkedList<String>> cleanperms = cyclicPerms(perms, noms.get(0));

		int cpt=0;
		int max=Integer.MIN_VALUE;
		for(int i=0; i<cleanperms.size(); i++){
			cpt++;
			int joy = permJoy(assocs, cleanperms.get(i));
			if( joy > max){ //Print d'un nouveau max
				System.out.print(cpt+"\t");
				for (String n : cleanperms.get(i)) {
					System.out.print(n+"\t");
				}
				System.out.println(joy);
			}
			max = Math.max(max,joy);
		}
		System.out.println("Max = "+max);
	}

	private static int permJoy(LinkedList<Association> assocs, LinkedList<String> perm){
		int totalJoy=0;
		for(int i=0; i+1<perm.size(); i++){
			totalJoy += getJoy(assocs, perm.get(i), perm.get(i+1));
		}
		totalJoy += getJoy(assocs, perm.getLast(), perm.getFirst());
		return totalJoy;
	}

	private static int getJoy(LinkedList<Association> assocs, String name, String other) {
		int i=0;
		int res=0;
		Association a;
		while(i<assocs.size()){
			a = assocs.get(i);
			if(a.nom.equals(name) && a.autre.equals(other)){
				res+= a.joie;
			}
			if(a.nom.equals(other) && a.autre.equals(name)){
				res+= a.joie;
			}
			i++;
		}
		return res;
	}

	private static LinkedList<LinkedList<String>> cyclicPerms(LinkedList<LinkedList<String>> perms, String name){
		LinkedList<LinkedList<String>> res = new LinkedList<LinkedList<String>>();
		boolean stop = false;
		int i=0;
		while(!stop && i<perms.size()){
			LinkedList<String> cur = perms.get(i);
			if(cur.get(0).equals(name)){
				res.add(cur);
			}
			else{
				stop = true;
			}
			i++;
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	private static LinkedList<LinkedList<String>> permutations(LinkedList<String> liste, int step){
		LinkedList<LinkedList<String>> res = new LinkedList<>();

		if(liste.size()==1){
			res.add(liste);
			return res;
		}
		else{

			for (String s : liste) {
				//				String indentation = new String(new char[step]).replace("\0", "    "); //debug
				//				System.out.println(indentation+"-"+s); //debug

				LinkedList<String> list = (LinkedList<String>) liste.clone(); //copie de la liste d'entree
				list.remove(s); //on supprime l'element courant dans la recursivite
				LinkedList<LinkedList<String>> permRec = permutations(list, step+1); //appel recursif sur le reste de la liste

				for (LinkedList<String> perm : permRec) { //pour toutes les permutations du reste de la liste
					LinkedList<String> permC = (LinkedList<String>) perm.clone();
					permC.addFirst(s); //on ajoute  la ville courante devant une permutation
					res.add(permC); //ce qui donne unepermutation finale
				}

			}
			return res;
		}
	}

	static Association parseInput(String s){

		String[] splits = s.split("[ .]+");
		String nom = splits[0];
		String autre = splits[10];
		int joie = Integer.parseInt(splits[3]);
		if(splits[2].equals("lose")){
			joie = 0 - joie;
		}
		return new Association(nom, autre, joie);
	}

	static class Association implements Comparable{
		String nom;
		String autre;
		int joie;

		public Association(String name, String other, int happiness) {
			nom = name;
			autre = other;
			joie = happiness;
		}

		@Override
		public String toString() {
			return nom+" : "+autre+" -> "+joie;
		}

		@Override
		public int compareTo(Object arg0) {
			if(! (arg0 instanceof Association)){
				return 0;
			}
			else{
				return nom.compareTo(((Association) arg0).nom);
			}
		}
	}

}
