import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Day10 {

	public static void main(String[] args) {
		
		String input = "1113122113";
		
		Pattern pat = Pattern.compile("(\\d)\\1*");
		//le \d correspond à 1 chiffre
		//le (\d) est entre parenthèses pour constituer un groupe à répéter. c'est le groupe 1
		//le \1 correspond à la répétition du groupe 1 (* donc 0 à n fois)
		Matcher mat;
		
		long startTime = System.currentTimeMillis();
		
		for(int i=0; i<50; i++){
			mat = pat.matcher(input);
			StringBuilder build = new StringBuilder();
			
			while(mat.find()){
				build.append(mat.group().length());
				build.append(mat.group(1));
			}
			input = build.toString();
			
			if(i==39 || i==49){
				System.out.println("Pour l'etape "+i+", la taille est de "+input.length()+" et le temps de calcul est de "+(System.currentTimeMillis()-startTime)+"ms");
				}
		}
	}
	
	private static String nextStep(String input){
		if(input.length()==1){
			return 1+input;
		}
		String res = "";
		char prev;
		char cur = 0;
		int cpt=1;
		
		for(int i=1; i<input.length(); i++){
			prev = input.charAt(i-1);
			cur = input.charAt(i);
			if(prev == cur){
				cpt++;
			}
			else{
				res += ""+cpt+prev;
				cpt=1;
			}
		}
		res += ""+cpt+cur;
		
		return res;
		
	}

}
