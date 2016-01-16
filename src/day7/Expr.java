package day7;

import java.util.HashMap;

enum Operateur {
	AND,OR,LSHIFT,RSHIFT,NOT,VALEUR,ASSIGN;
}

class Terme {
	String id;
	int valeur;
	
	public Terme(String id) {
		this.id = id;
	}
	
	public Terme(int valeur) {
		this.valeur = valeur;
	}
	
	public int getValue(HashMap<String,Expr> symTab, int cpt){
		if(id==null){
			return valeur;
		}
		else{
			return symTab.get(id).getValue(symTab, id, cpt);
		}
	}
	
	@Override
	public String toString() {
		String res="";
		if(id==null){
			res += valeur;
		}
		else{
			res = id;
		}
		return res;
	}
}

public class Expr {
	Operateur op;
	Terme a;
	Terme b;
	int valeur;
	int shift;
	
	public Expr(Operateur op, Terme a, Terme b, int valeur, int shift) {
		this.op = op;
		this.a = a;
		this.b = b;
		this.valeur = valeur;
		this.shift = shift;
	}
	
	public int getValue(HashMap<String,Expr> symTab, String debugID, int cpt){
		cpt++;
		System.out.println(this+" -> "+debugID);
//		if(cpt>40){
//			System.exit(0);
//		}
		
		int res = 0;
		switch(op){
		case AND: res = a.getValue(symTab,cpt) & b.getValue(symTab,cpt);
			break;
		case OR: res = a.getValue(symTab,cpt) | b.getValue(symTab,cpt);
			break;
		case LSHIFT: res = a.getValue(symTab,cpt) << shift;
			break;
		case RSHIFT: res = a.getValue(symTab,cpt) >> shift;
			break;
		case NOT: res = Day7.flipBits(a.getValue(symTab,cpt), 16);
			break;
		case VALEUR: res = valeur;
			break;
		case ASSIGN: res = a.getValue(symTab,cpt);
			break;
		default: System.err.println("OPERATEUR INCONNU : "+op); System.exit(0);
			break;
		}
		
		Expr newExpr = new Expr(Operateur.VALEUR, null, null, res, 0);
		symTab.replace(debugID, newExpr);
		
		return res;
	}
	
	@Override
	public String toString() {
		String res="";
		if(op==Operateur.VALEUR){
			res += valeur;
		}
		else if(op==Operateur.ASSIGN){
			res += a;
		}
		else if(op==Operateur.NOT){
			res = "not "+a;
		}
		else if(op==Operateur.LSHIFT || op==Operateur.RSHIFT){
			res = a+" "+op+" "+shift;
		}
		else{
			res = a+" "+op+" "+b;
		}
		return res;
	}
}
