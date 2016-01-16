package day12;

public class expAttribute {

	int cpt;
	boolean red;
	
	public expAttribute(boolean red) {
		this.red = red;
		cpt=0;
	}
	
	public expAttribute(int cpt) {
		this.cpt = cpt;
		red = false;
	}
	
	public expAttribute() {
		red = false;
		cpt = 0;
	}
	
	
	
}
