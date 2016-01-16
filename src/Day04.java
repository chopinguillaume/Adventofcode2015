import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day04 {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String input = "yzbqklnj";
		String hashtext = null;
		boolean fini=false;
		int add = -1;
		String inputadd = null;
		
		while(!fini){
			add++;
			
			inputadd = input+add;
			byte[] tab = inputadd.getBytes("UTF-8");
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] mdtab = md.digest(tab);
			
			BigInteger bigInt = new BigInteger(1,mdtab);
			hashtext = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while(hashtext.length() < 32 ){
			  hashtext = "0"+hashtext;
			}
			
			fini = hashtext.substring(0, 6).equals("000000");
			
		}
		System.out.println(inputadd);
		System.out.println(hashtext);
		System.out.println(add);

	}

}
