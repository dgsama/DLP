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
INT_CONSTANT = 0|[1-9][0-9]*  
LETTER = [a-zA-ZáéíóúÁÉÍÓÚñÑ]
IDENTS = {LETTER}({LETTER}*{INT_CONSTANT}*)*
COMMENT = "//" . *	
COMMENT_MULTILINE = "/*" ~ "*/"
OPERATOR = [+\-*%/\[\]\.<>=!\^]
SEPARATORS = [{};,]
EXPONENT = [eE](\-|"+")?{INT_CONSTANT}
REAL_CONSTANT={INT_CONSTANT}({EXPONENT}|(\.({INT_CONSTANT}{EXPONENT}?)?))
BLANKS = [\t\n \r]

%%
// ************  Lexical Rules ********************


// * Integer constant
{INT_CONSTANT}		{ this.yylval = new Integer(yytext());
         			  return Parser.INT_CONSTANT;  }

























