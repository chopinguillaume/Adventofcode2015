import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day16 {

	public static void main(String[] args) {

		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream("input/day16.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

		Pattern p = Pattern.compile("((\\w)+):");
		Pattern pint = Pattern.compile("(\\d)+");

		LinkedList<Aunt> auntList = new LinkedList<>();
		int id=0;

		while(scan.hasNextLine()){
			id++;

			String input = scan.nextLine();

			Matcher m = p.matcher(input);
			Matcher mint = pint.matcher(input);

			Aunt aunt = new Aunt(id);

			m.find();
			mint.find();
			while(m.find()){
				mint.find();
				String field = m.group(1);
				int n = Integer.parseInt(mint.group());
				aunt.setField(field, n);
			}
			auntList.add(aunt);
			System.out.println(aunt);
		}

		for (Aunt aunt : auntList) {
			if(aunt.isCompatible()){
				System.out.println(aunt);
			}
		}

	}

	private static class Aunt{
		int id;
		int children = -1;
		int cats = -1;
		int samoyeds = -1;
		int pomeranians = -1;
		int akitas = -1;
		int vizslas = -1;
		int goldfish = -1;
		int trees = -1;
		int cars = -1;
		int perfumes = -1;

		public Aunt(int id) {
			this.id = id;
		}

		public void setField(String field, int n){
			switch(field){
			case "children": children = n; break;
			case "cats": cats = n; break;
			case "samoyeds": samoyeds = n; break;
			case "pomeranians": pomeranians = n; break;
			case "akitas": akitas = n; break;
			case "vizslas": vizslas = n; break;
			case "goldfish": goldfish = n; break;
			case "trees": trees = n; break;
			case "cars": cars = n; break;
			case "perfumes": perfumes = n; break;
			default : System.err.println("Champ inconnu : "+field); System.exit(0); break;
			}
		}

		public boolean isCompatible(){
			if(children != -1 && children != 3){
				return false;
			}
			if(cats != -1 && cats <= 7){
				return false;
			}
			if(samoyeds != -1 && samoyeds != 2){
				return false;
			}
			if(pomeranians != -1 && pomeranians >= 3){
				return false;
			}
			if(akitas != -1 && akitas != 0){
				return false;
			}
			if(vizslas != -1 && vizslas != 0){
				return false;
			}
			if(goldfish != -1 && goldfish >= 5){
				return false;
			}
			if(trees != -1 && trees <= 3){
				return false;
			}
			if(cars != -1 && cars != 2){
				return false;
			}
			if(perfumes != -1 && perfumes != 1){
				return false;
			}
			return true;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(""+id);
			if(children != -1){sb.append(", children:"+children);}
			if(cats != -1){sb.append(", cats:"+cats);}
			if(samoyeds != -1){sb.append(", samoyeds:"+samoyeds);}
			if(pomeranians != -1){sb.append(", pomeranians:"+pomeranians);}
			if(akitas != -1){sb.append(", akitas:"+akitas);}
			if(vizslas != -1){sb.append(", vizslas:"+vizslas);}
			if(goldfish != -1){sb.append(", goldfish:"+goldfish);}
			if(trees != -1){sb.append(", trees:"+trees);}
			if(cars != -1){sb.append(", cars:"+cars);}
			if(perfumes != -1){sb.append(", perfumes:"+perfumes);}
			
			return sb.toString();
		}

	}
}
