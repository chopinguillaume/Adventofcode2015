import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Day08 {

	public static void main(String[] args) {

		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream("input/day8.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		
		int totalcount = 0;
        int computed = 0;
        int computed2 = 0;
		while (scan.hasNext()) {
            String val = scan.nextLine();
            totalcount += val.length();

            System.out.println(val);

            String withoutcomils = val.substring(1, val.length() - 1);
            System.out.println(withoutcomils);

            int toadd = 0;
            int toadd2 = 0;
            toadd += withoutcomils.length();
            toadd2 += val.length() + 4;
            
            while(withoutcomils.contains("\\\\")) {
                toadd -= 1;
                toadd2 += 2;
                System.out.println(val + " contains " + "\\\\" + " , removed 1 from total.");
                withoutcomils = withoutcomils.replaceFirst(Pattern.quote("\\\\"), "");
            }
            while(withoutcomils.contains("\\\"")) {
                toadd -= 1;
                toadd2 += 2;
                System.out.println(val + " contains " + "\\\"" + " , removed 1 from total.");
                withoutcomils = withoutcomils.replaceFirst(Pattern.quote("\\\""), "");
            }
            while(withoutcomils.contains("\\x")) {
                toadd -= 3;
                toadd2 += 1;
                System.out.println(val + " contains " + "\\x" + "..., removed 3 from total.");
                withoutcomils = withoutcomils.replaceFirst(Pattern.quote("\\x"), "");
            }
            computed += toadd;
            computed2 += toadd2;
            System.out.println("Added " + val.length() + " to total, added " + toadd + " to computed.");
        }
        System.out.println("Total found: " + totalcount);
        System.out.println("Computed: " + computed);
        System.out.println("Result: " + (totalcount-computed));
        System.out.println("Computed 2: "+ computed2);
        System.out.println("Result 2: " + (computed2 - totalcount));
    }
}
