import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19 {

	public static void main(String[] args) {

		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream("input/day19.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

		HashMap<String,LinkedList<String>> transitions = new HashMap<>();
		Pattern p = Pattern.compile("((\\w)+) => ((\\w)+)");

		while(scan.hasNextLine()){
			String trans = scan.nextLine();
			if(trans.equals("")){
				break;
			}
			Matcher m = p.matcher(trans);
			String key = null;
			String result = null;
			while(m.find()){
				key = m.group(1);
				result = m.group(3);
			}

			LinkedList<String> transKey = transitions.get(key);
			if(transKey==null){
				LinkedList<String> newTrans = new LinkedList<>();
				newTrans.add(result);
				transitions.put(key, newTrans);
			}
			else{
				transKey.add(result);
			}

		}

		System.out.println("Transitions : ");
		Set set = transitions.entrySet();
		for (Object obj : set) {
			System.out.println(obj);
		}
		System.out.println("------------------");

		String sequence = scan.nextLine();
		System.out.println("Sequence : "+sequence);

		System.out.println("------------------");

		Pattern p2 = Pattern.compile("([A-Za-z][a-z]?)");
		Matcher m2 = p2.matcher(sequence);
		HashSet<String> resultats = new HashSet<>();

		while(m2.find()){
			String element = m2.group();
			int i = m2.start();
			int j = m2.end();
			LinkedList<String> listelt = transitions.get(element);
			if(listelt != null){
				for (Object obj : listelt) {
					String newseq = sequence.substring(0, i) + obj + sequence.substring(j);
					resultats.add(newseq);
				}				
			}
		}
		System.out.println("Nombre de resultats differents = "+resultats.size());
		System.out.println("------------------");

		//Debut partie 2
		//l'idee est de parcourir la sequence en remplacant les parties droites de transitions par la partie gauche correspondant
		//Si on bloque, on shuffle les transitions et on recommence

		String tmp=sequence;
		int steps=0;
		int shuffleCount=0;
		List<String[]> transits = flattenTransitions(transitions);
		while(!tmp.equals("e")){

			String last = tmp;

			for (String[] strings : transits) {
				String key = strings[0];
				String res = strings[1];
				if(tmp.contains(res)){
					tmp = remplace(tmp, res, key, tmp.indexOf(res));
					steps++;
				}
			}
			if(last.equals(tmp) && (!tmp.equals("e"))){
				System.out.println(tmp);
				Collections.shuffle(transits);
				tmp = sequence;
				steps = 0;
				shuffleCount++;
			}
		}
		System.out.println(tmp);
		System.out.println("Part 2: Transforms taken = " + steps + " Reshuffled " + shuffleCount + " times.");

	}

	private static String remplace(String sequence, String res, String key, int i) {
		return sequence.substring(0,i) + key + sequence.substring(i + res.length());
	}

	private static LinkedList<String[]> flattenTransitions(HashMap<String,LinkedList<String>> transitions){
		LinkedList<String[]> res = new LinkedList<>();

		Set<Entry<String, LinkedList<String>>> set = transitions.entrySet();
		Iterator<Entry<String, LinkedList<String>>> it = set.iterator();

		while(it.hasNext()){
			Entry<String, LinkedList<String>> trans = it.next();
			String key = trans.getKey();
			LinkedList<String> transitkey = trans.getValue();
			for (String string : transitkey) {
				String[] tabres = {key,string};
				res.add(tabres);
			}
		}
		return res;
	}

}
