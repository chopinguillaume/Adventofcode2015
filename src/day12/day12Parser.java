// $ANTLR 3.5.2 day12.g 2015-12-19 13:27:01

package day12;
import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class day12Parser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "CHAR", "INT", "STRING", "WS", 
		"','", "':'", "'['", "']'", "'{'", "'}'"
	};
	public static final int EOF=-1;
	public static final int T__8=8;
	public static final int T__9=9;
	public static final int T__10=10;
	public static final int T__11=11;
	public static final int T__12=12;
	public static final int T__13=13;
	public static final int CHAR=4;
	public static final int INT=5;
	public static final int STRING=6;
	public static final int WS=7;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public day12Parser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public day12Parser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return day12Parser.tokenNames; }
	@Override public String getGrammarFileName() { return "day12.g"; }


	/** Map variable name to Integer object holding value */
	HashMap memory = new HashMap();



	// $ANTLR start "doc"
	// day12.g:17:1: doc returns [int cpt] : e= expr ;
	public final int doc() throws RecognitionException {
		int cpt = 0;


		expAttribute e =null;

		try {
			// day12.g:17:22: (e= expr )
			// day12.g:18:2: e= expr
			{
			pushFollow(FOLLOW_expr_in_doc36);
			e=expr();
			state._fsp--;

			cpt = e.cpt;
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return cpt;
	}
	// $ANTLR end "doc"



	// $ANTLR start "field"
	// day12.g:21:1: field returns [expAttribute fie] : STRING ':' e= expr ;
	public final expAttribute field() throws RecognitionException {
		expAttribute fie = null;


		expAttribute e =null;

		try {
			// day12.g:21:33: ( STRING ':' e= expr )
			// day12.g:22:2: STRING ':' e= expr
			{
			match(input,STRING,FOLLOW_STRING_in_field51); 
			match(input,9,FOLLOW_9_in_field53); 
			pushFollow(FOLLOW_expr_in_field57);
			e=expr();
			state._fsp--;

			fie = e;
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return fie;
	}
	// $ANTLR end "field"



	// $ANTLR start "expr"
	// day12.g:25:1: expr returns [expAttribute exp] : (s= STRING |i= INT |st= struct |ar= array );
	public final expAttribute expr() throws RecognitionException {
		expAttribute exp = null;


		Token s=null;
		Token i=null;
		expAttribute st =null;
		int ar =0;

		try {
			// day12.g:25:32: (s= STRING |i= INT |st= struct |ar= array )
			int alt1=4;
			switch ( input.LA(1) ) {
			case STRING:
				{
				alt1=1;
				}
				break;
			case INT:
				{
				alt1=2;
				}
				break;
			case 12:
				{
				alt1=3;
				}
				break;
			case 10:
				{
				alt1=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// day12.g:26:2: s= STRING
					{
					s=(Token)match(input,STRING,FOLLOW_STRING_in_expr74); 
					boolean red = s.getText().equals("\"red\""); exp = new expAttribute(red);
					}
					break;
				case 2 :
					// day12.g:27:4: i= INT
					{
					i=(Token)match(input,INT,FOLLOW_INT_in_expr83); 
					exp = new expAttribute(Integer.parseInt(i.getText()));
					}
					break;
				case 3 :
					// day12.g:28:4: st= struct
					{
					pushFollow(FOLLOW_struct_in_expr92);
					st=struct();
					state._fsp--;

					exp = st;
					}
					break;
				case 4 :
					// day12.g:29:4: ar= array
					{
					pushFollow(FOLLOW_array_in_expr101);
					ar=array();
					state._fsp--;

					exp = new expAttribute(ar);
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return exp;
	}
	// $ANTLR end "expr"



	// $ANTLR start "struct"
	// day12.g:32:1: struct returns [expAttribute str] : '{' f1= field ( ',' f2= field )* '}' ;
	public final expAttribute struct() throws RecognitionException {
		expAttribute str = null;


		expAttribute f1 =null;
		expAttribute f2 =null;

		try {
			// day12.g:32:34: ( '{' f1= field ( ',' f2= field )* '}' )
			// day12.g:33:2: '{' f1= field ( ',' f2= field )* '}'
			{
			match(input,12,FOLLOW_12_in_struct116); 
			pushFollow(FOLLOW_field_in_struct120);
			f1=field();
			state._fsp--;

			boolean red = f1.red; int cpt = f1.cpt;
			// day12.g:34:2: ( ',' f2= field )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==8) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// day12.g:34:3: ',' f2= field
					{
					match(input,8,FOLLOW_8_in_struct127); 
					pushFollow(FOLLOW_field_in_struct131);
					f2=field();
					state._fsp--;

					red = red || f2.red; cpt+= f2.cpt;
					}
					break;

				default :
					break loop2;
				}
			}

			match(input,13,FOLLOW_13_in_struct139); 
			if(red){str = new expAttribute(0);}else{str = new expAttribute(cpt);}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return str;
	}
	// $ANTLR end "struct"



	// $ANTLR start "array"
	// day12.g:37:1: array returns [int cpt] : '[' e1= expr ( ',' e2= expr )* ']' ;
	public final int array() throws RecognitionException {
		int cpt = 0;


		expAttribute e1 =null;
		expAttribute e2 =null;

		try {
			// day12.g:37:24: ( '[' e1= expr ( ',' e2= expr )* ']' )
			// day12.g:38:2: '[' e1= expr ( ',' e2= expr )* ']'
			{
			match(input,10,FOLLOW_10_in_array154); 
			pushFollow(FOLLOW_expr_in_array158);
			e1=expr();
			state._fsp--;

			cpt = e1.cpt;
			// day12.g:39:2: ( ',' e2= expr )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==8) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// day12.g:39:3: ',' e2= expr
					{
					match(input,8,FOLLOW_8_in_array165); 
					pushFollow(FOLLOW_expr_in_array169);
					e2=expr();
					state._fsp--;

					cpt += e2.cpt;
					}
					break;

				default :
					break loop3;
				}
			}

			match(input,11,FOLLOW_11_in_array179); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return cpt;
	}
	// $ANTLR end "array"

	// Delegated rules



	public static final BitSet FOLLOW_expr_in_doc36 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_in_field51 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_field53 = new BitSet(new long[]{0x0000000000001460L});
	public static final BitSet FOLLOW_expr_in_field57 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_in_expr74 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INT_in_expr83 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_struct_in_expr92 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_array_in_expr101 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_12_in_struct116 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_field_in_struct120 = new BitSet(new long[]{0x0000000000002100L});
	public static final BitSet FOLLOW_8_in_struct127 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_field_in_struct131 = new BitSet(new long[]{0x0000000000002100L});
	public static final BitSet FOLLOW_13_in_struct139 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_10_in_array154 = new BitSet(new long[]{0x0000000000001460L});
	public static final BitSet FOLLOW_expr_in_array158 = new BitSet(new long[]{0x0000000000000900L});
	public static final BitSet FOLLOW_8_in_array165 = new BitSet(new long[]{0x0000000000001460L});
	public static final BitSet FOLLOW_expr_in_array169 = new BitSet(new long[]{0x0000000000000900L});
	public static final BitSet FOLLOW_11_in_array179 = new BitSet(new long[]{0x0000000000000002L});
}
