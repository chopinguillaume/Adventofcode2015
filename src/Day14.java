import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {

	public static void main(String[] args) {

		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream("input/day14.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		LinkedList<Reindeer> reindeersList = new LinkedList<>();
		Pattern p = Pattern.compile("(\\d)+");

		while(scan.hasNextLine()){
			
			String input = scan.nextLine();
			Matcher m = p.matcher(input);
			
			String name = input.split("[ ]+")[0];
			m.find();
			int speed = Integer.parseInt(m.group());
			m.find();
			int duration = Integer.parseInt(m.group());
			m.find();
			int rest = Integer.parseInt(m.group());
			
			Reindeer reindeer = new Reindeer(name,speed,duration,rest);
			reindeersList.add(reindeer);
		}
		
		int maxdistance = 0;
		int time=2503;
		for (Reindeer reindeer : reindeersList) {
			int distance = reindeer.distanceAtTime(time);
			maxdistance= Math.max(maxdistance, distance);
			System.out.println(reindeer+"\t"+distance);
		}
		System.out.println("Maxdistance = "+maxdistance+"\n");

		
		int timespent=0;
		while(timespent<time){
			maxdistance = 0;
			timespent++;
			for (Reindeer reindeer : reindeersList) {//calculer les distances
				reindeer.incrementTime();
				maxdistance = Math.max(maxdistance, reindeer.distance);
			}
			
			for (Reindeer reindeer : reindeersList) {//donner les points
				if(reindeer.distance == maxdistance){
					reindeer.incrementPoints();
				}
			}
		}
		
		int maxPoints=0;
		for (Reindeer reindeer : reindeersList) {
			System.out.println(reindeer);
			maxPoints = Math.max(maxPoints, reindeer.points);
		}
		System.out.println("Maxpoints = "+maxPoints);
	}
	
	private static class Reindeer{
		String name;
		final int speed;
		final int duration;
		final int rest;
		int points;
		int distance;
		int time;
		
		public Reindeer(String n, int s, int d, int r) {
			name=n;
			speed=s;
			duration=d;
			rest=r;
			points=0;
			distance=0;
			time=0;
		}
		
		public void incrementTime(){
			int n = time % (duration+rest);
			if(n<duration){//periode de course
				distance += speed;
			}
			time++;
		}
		
		public void incrementPoints(){
			points++;
		}
		
		public int distanceAtTime(int time){
			int distance=0;
			int nbC = time / (duration+rest);//nb de cycles complets possibles (course + repos)
			time = time % (duration+rest); //temps restant apres les cycles
			distance += nbC*speed*duration; //distance parcourue par les cycles complets
			//il ne reste plus de temps pour faire un cycle complet
			if(time>=duration){
				distance+= speed*duration;
				time -= duration;
			}
			else{
				distance+= speed*time;
				time = 0;
			}
			if(time>=rest){
				System.err.println("WTF");
			}
			return distance;
		}
		
		@Override
		public String toString() {
			return name+"\t"+speed+" km/s for "+duration+"s, rests "+rest+"s. "
					+ "Distance = "+distance+", Time = "+time+", Points = "+points;
		}
	}
}
