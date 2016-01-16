import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day11 {

	public static void main(String[] args) {
		
		String s = "vzbxkghb";
		int cpt=0;
		while(cpt<2){
			s = next(s);
			if(isSafe(s)){
				cpt++;
				System.out.println(s);
			}
		}
		
	}
	
	private static boolean contains2inArow(String s){
		Pattern p = Pattern.compile("([a-z])\\1");
		Matcher m = p.matcher(s);
		
		int i=0;
		while(m.find()){
			i++;
		}
		
		return i>=2;
	}
	
	private static boolean containsiol(String s){
		return s.contains("i") || s.contains("o") || s.contains("l");
	}
	
	private static boolean isSafe(String s){
		return !containsiol(s) && contains2inArow(s) && contains3ordered(s);
	}
	
	private static boolean contains3ordered(String s) {
		boolean res=false;
		for(int i=0; !res && i+2<s.length(); i++){
			res = s.charAt(i)+1 == s.charAt(i+1) && s.charAt(i)+2 == s.charAt(i+2);
		}
		return res;
	}
	
	private static String next(String s){
		int i=s.length()-1;
		boolean fini=false;
		while(i>=0 && !fini){
			char c = s.charAt(i);
			if(c == 'z'){
				c = 'a';
			}else{
				c++;
				fini = true;
			}
			s = s.substring(0,i)+c+s.substring(i+1);
			i--;
		}
		return s;
	}

}
