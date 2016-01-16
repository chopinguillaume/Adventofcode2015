grammar day12;

@header{
package day12;
import java.util.HashMap;
}

@lexer::header {
package day12;
}

@members {
/** Map variable name to Integer object holding value */
HashMap memory = new HashMap();
}

doc returns [int cpt]:
	e=expr {cpt = e.cpt;}
;

field returns [expAttribute fie]:
	STRING ':' e=expr {fie = e;}
;

expr returns [expAttribute exp]:
	s=STRING {boolean red = s.getText().equals("\"red\""); exp = new expAttribute(red);}
	| i=INT {exp = new expAttribute(Integer.parseInt(i.getText()));}
	| st=struct {exp = st;}
	| ar=array {exp = new expAttribute(ar);}
;

struct returns [expAttribute str]:
	'{' f1=field {boolean red = f1.red; int cpt = f1.cpt;} 
	(',' f2=field {red = red || f2.red; cpt+= f2.cpt;}  )* '}' {if(red){str = new expAttribute(0);}else{str = new expAttribute(cpt);}}
;

array returns [int cpt]:
	'[' e1=expr {cpt = e1.cpt;} 
	(',' e2=expr   {cpt += e2.cpt;}  )* ']'
;


INT : '-'?('0'..'9')+;
STRING 	: '"' CHAR+ '"';
WS : (' '|'\t'|'\r'|'\n')+ {skip();} ;
CHAR	:	('a'..'z'|'A'..'Z');