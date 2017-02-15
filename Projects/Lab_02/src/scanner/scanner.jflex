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

// * CHAR CONSTANT METHOD
public Char convertChar(String text){
	String character = text.substring(1,(lexema.length -2));
	if(character.lenght>1){
		
	}
}


%}

// ************  Macros ********************
INT_CONSTANT = 0|[1-9][0-9]*  
LETTER = [a-zA-ZáéíóúÁÉÍÓÚñÑ]
IDENTS = {LETTER}({LETTER}*{INT_CONSTANT}*)*
COMMENT = "//" . *	
COMMENT_MULTILINE = "/*" ~ "*/"
OPERATOR = [+\-*%/\[\]\.<>=!\^\(\)]
SEPARATORS = [{};,]
EXPONENT = [eE](\-|"+")?{INT_CONSTANT}
REAL_CONSTANT=({INT_CONSTANT}{EXPONENT}?\.)|(\.{INT_CONSTANT}{EXPONENT}?)|({INT_CONSTANT}\.{INT_CONSTANT}{EXPONENT}?)
CHAR_CONSTANT = \'(\\[0-256]|.|\\n|\\t)\'
BLANKS = (\t|\n|" "|\r)

%%
// ************  Lexical Rules ********************

// * DOUBLE OPERATORS

"**"	{return Parser.POW;}
"=="	{return Parser.EQ;}
"<="	{return Parser.L_EQ;}
">="	{return Parser.G_EQ;}
"!="	{return Parser.NOT_EQ;}
"&&"	{return Parser.AND;}
"||"	{return Parser.OR;}


// * KEYWORDS

read	{return Parser.READ;}
write	{return Parser.WRITE;}
While	{return Parser.WHILE;}
if	{return Parser.IF;}
else	{return Parser.ELSE;}
int	{return Parser.INT;}	
double	{return Parser.DOUBLE;}
char	{return Parser.CHAR;}
struct	{return Parser.STRUCT;}
return	{return Parser.RETURN;}
void	{return Parser.VOID;}
main	{return Parser.MAIN;}

// * OPERATORS
{OPERATOR}		{return (int)yytext().charAt(0);}

// * Separators
{SEPARATORS}		{return (int)yytext().charAt(0);}

// * IDs
{IDENTS}		{this.yylval = yytext();
				return Parser.ID;}
// * Real Constants
{REAL_CONSTANT}		{this.yylval = new Double(yytext());
				return Parser.REAL_CONSTANT;}
// * Char constant
{CHAR_CONSTANT}		{this.yylval = convertChar(yytext());
				return Parser.CHAR_CONSTANT;}

// * Integer constant
{INT_CONSTANT}		{this.yylval = new Integer(yytext());
         			  return Parser.INT_CONSTANT;}

// * DO NOTHING
{BLANKS}		{}
{COMMENT}		{System.out.print("Comment");}
{COMMENT_MULTILINE}	{System.out.print("Multiline comment");}
.			{System.err.println("Error in line: ["+getLine()+"] column: ["+getColumn()+ "] --> "+yytext().toString() );}




