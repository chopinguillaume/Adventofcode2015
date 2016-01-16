import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Day09 {

	public static void main(String[] args) {

		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream("input/day9.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// Initialisation du graphe

		Graphe graphe = new Graphe();

		while(scan.hasNext()){

			String input = scan.nextLine();

			String tokens="[ =]+";
			String[] parse = input.split(tokens);

			String depart = parse[0];
			String arrivee = parse[2];
			int distance = Integer.parseInt(parse[3]);

			Association newAssoc = new Association(depart,arrivee,distance);
			graphe.add(newAssoc);

		}
		System.out.println(graphe);

		//Generation des chemins

		LinkedList<String> villes = graphe.getVilles();
		
		LinkedList<LinkedList<String>> chemins = permutations(villes,0);
		
		int min = Integer.MAX_VALUE;
		int max = 0;
		int nbChemins = 0;
		for (LinkedList<String> chemin : chemins) {
			int dist = 0;
			for (int i=1; i<chemin.size(); i++) {
				String v1 = chemin.get(i-1);
				String v2 = chemin.get(i);
				System.out.print(v1+" -> ");
				dist += graphe.distanceEntre(v1, v2);
			}
			System.out.println(chemin.getLast()+" : "+dist);
			nbChemins++;
			min = Math.min(min,dist);
			max = Math.max(max, dist);
		}
		System.out.println("Nombre de chemins : "+nbChemins);
		System.out.println("min = "+min);
		System.out.println("max = "+max);


	}//Fin de main()

	@SuppressWarnings("unchecked")
	private static LinkedList<LinkedList<String>> permutations(LinkedList<String> liste, int step){
		LinkedList<LinkedList<String>> res = new LinkedList<>();

		if(liste.size()==1){
			res.add(liste);
			return res;
		}
		else{

			for (String s : liste) {
				String indentation = new String(new char[step]).replace("\0", "    "); //debug
				System.out.println(indentation+"-"+s); //debug

				LinkedList<String> list = (LinkedList<String>) liste.clone(); //copie de la liste d'entree
				list.remove(s); //on supprime l'element courant dans la recursivite
				LinkedList<LinkedList<String>> permRec = permutations(list, step+1); //appel recursif sur le reste de la liste

				for (LinkedList<String> perm : permRec) { //pour toutes les permutations du reste de la liste
					LinkedList<String> permC = (LinkedList<String>) perm.clone();
					permC.addFirst(s); //on ajoute la ville courante devant une permutation
					res.add(permC); //ce qui donne une permutation finale
				}

			}
			return res;
		}
	}

	static class Graphe{
		private LinkedList<Association> assocs = new LinkedList<>();

		public void add(Association a){
			if(!contains(a)){
				assocs.add(a);
			}
		}

		public void remove(Association a){
			for (Association as : assocs) {
				if(as.equals(a)){assocs.remove(as);}
			}
		}

		public boolean contains(Association a){
			for (Association as : assocs) {
				if(as.memeVilles(a)){return true;}
			}
			return false;
		}

		@Override
		public String toString() {
			String res = "";
			for (Association association : assocs) {
				res += association+"\n";
			}
			return res;
		}

		public Association chemin2Villes(String v1, String v2){
			for (Association as : assocs) {
				if ((as.v1.equals(v1) && as.v2.equals(v2)) || (as.v2.equals(v1) && as.v1.equals(v2))){
					return as;
				}
			}
			return null;
		}

		public int distanceEntre(String v1, String v2){
			Association assoc = chemin2Villes(v1,v2);
			if(assoc==null){System.out.println("Pas d'association entre "+v1+" et "+v2); System.exit(0);}
			return assoc.distance;
		}

		public LinkedList<String> getVilles(){
			LinkedList<String> liste = new LinkedList<>();
			for (Association as : assocs) {
				if(!liste.contains(as.v1)){
					liste.add(as.v1);
				}
				if(!liste.contains(as.v2)){
					liste.add(as.v2);
				}
			}
			return liste;
		}

	}

	static class Association{
		String v1;
		String v2;
		int distance;

		public Association(String v1, String v2, int distance) {
			this.v1 = v1; this.v2 = v2; this.distance = distance;
		}

		public boolean memeVilles(Association a){
			boolean res = (v1.equals(a.v1) && v2.equals(a.v2)) || (v1.equals(a.v2) && v2.equals(a.v1));
			return res;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Association)){return false;}
			else{
				Association a = (Association) obj;
				boolean res = (v1.equals(a.v1) && v2.equals(a.v2)) || (v1.equals(a.v2) && v2.equals(a.v1));
				res = res && distance == a.distance;
				return res;
			}
		}

		@Override
		public String toString() {
			return v1+" -> "+v2+" : "+distance;
		}
	}


}
