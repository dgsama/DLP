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
	public char convertToChar(String text){
		char[]chars = text.toCharArray();
		if(chars[1]=='\\'){
				if(chars[2]=='n'){
					return '\n';
				}else if(chars[2]=='t'){
					return '\t';
				}else if(isIntNexts(chars)){
					String aux ="";
					for(int i =2;i<chars.length;i++){
						aux+=chars[i];
					}
					return (char)Integer.parseInt(aux);
				}
		}else{
			return chars[1];
		}
		return 0;
		
	}

	private boolean isIntNexts(char[] c) {
		for (int i = 2; i < c.length; i++) {
			if (Character.isDigit(c[i])) {
				return false;
			}
		}
		return true;
	}

%}

// ************  Macros ********************
INT_CONSTANT = 0|[1-9][0-9]*  
ASCII = 0|[1-9][0-9]?|1[0-9][0-9]|2[0-4][0-9]|25[0-6]
LETTER = [a-zA-ZáéíóúÁÉÍÓÚñÑ]
IDENTS = {LETTER}({LETTER}*{INT_CONSTANT}*)*
COMMENT = "//" . *	
COMMENT_MULTILINE = "/*" ~ "*/"
OPERATOR = [+\-*%/\[\]\.<>=!\^\(\)]
SEPARATORS = [{};,]
EXPONENT = [eE](\-|"+")?{INT_CONSTANT}
REAL_CONSTANT=({INT_CONSTANT}{EXPONENT}?\.)|(\.{INT_CONSTANT}{EXPONENT}?)|({INT_CONSTANT}\.{INT_CONSTANT}{EXPONENT}?)
CHAR_CONSTANT =\'(\\{ASCII}|.|\\n|\\t)\'
BLANKS = (\t|\n|" "|\r)

%%
// ************  Lexical Rules ********************
// * COMMENTS

{COMMENT}		{System.out.println("Comment");}
{COMMENT_MULTILINE}	{System.out.println("Multiline comment");}

// * DOUBLE OPERATORS

"**"	{this.yylval = yytext();	return Parser.POW;}
"=="	{this.yylval = yytext();	return Parser.EQ;}
"<="	{this.yylval = yytext();	return Parser.L_EQ;}
">="	{this.yylval = yytext();	return Parser.G_EQ;}
"!="	{this.yylval = yytext();	return Parser.NOT_EQ;}
"&&"	{this.yylval = yytext();	return Parser.AND;}
"||"	{this.yylval = yytext();	return Parser.OR;}


// * KEYWORDS

read	{this.yylval = yytext();	return Parser.READ;}
write	{this.yylval = yytext();	return Parser.WRITE;}
while	{this.yylval = yytext();	return Parser.WHILE;}
if		{this.yylval = yytext();	return Parser.IF;}
else	{this.yylval = yytext();	return Parser.ELSE;}
int		{this.yylval = yytext();	return Parser.INT;}	
double	{this.yylval = yytext();	return Parser.DOUBLE;}
char	{this.yylval = yytext();	return Parser.CHAR;}
struct	{this.yylval = yytext();	return Parser.STRUCT;}
return	{this.yylval = yytext();	return Parser.RETURN;}
void	{this.yylval = yytext();	return Parser.VOID;}
main	{this.yylval = yytext();	return Parser.MAIN;}

// * Char constant
{CHAR_CONSTANT}		{this.yylval = convertToChar(yytext());
				return Parser.CHAR_CONSTANT;}

// * OPERATORS
{OPERATOR}		{this.yylval = yytext();
					return (int)yytext().charAt(0);}

// * Separators
{SEPARATORS}		{this.yylval = yytext();
						return (int)yytext().charAt(0);}

// * IDs
{IDENTS}		{this.yylval = yytext();
				return Parser.ID;}
// * Real Constants
{REAL_CONSTANT}		{this.yylval = new Double(yytext());
				return Parser.REAL_CONSTANT;}

// * Integer constant
{INT_CONSTANT}		{this.yylval = new Integer(yytext());
         			  return Parser.INT_CONSTANT;}

// * DO NOTHING
{BLANKS}		{}
.			{System.err.println("Error in line: ["+getLine()+"] column: ["+getColumn()+ "] --> "+yytext().toString() );}




