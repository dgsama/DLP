// ************  User Code  ********************

package scanner;
import parser.Parser;

%%
// ************  Options ********************

// % debug // * For debugging purposes
%byaccj
%class Scanner
%public
%unicode
%line
%column

%{
// ************  Fields and methods ********************

// * To get the line number
public int getLine() { 
	// * JFlex starts in zero
	return this.yyline+1;
}

// * To get the column number
public int getColumn() { 
	// * JFlex starts in zero
	return yycolumn+1;
}

// * Semantic value of the token
private Object yylval;
public Object getYylval() {
	return this.yylval;
}


%}

// ************  Macros ********************
IntConstant = [0-9]*  // Is it correct?

%%
// ************  Lexical Rules ********************

// * Integer constant
{IntConstant}		{ this.yylval = new Integer(yytext());
         			  return Parser.INT_CONSTANT;  }

