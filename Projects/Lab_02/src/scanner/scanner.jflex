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
public char convertChar(String text){
	String result = text.replace("'","");
	if(result.charAt(0)=='\\'){
		if(result.charAt(1)=='n'){
			return '\n';
			}else if(result.charAt(1)=='t'){
			return '\t';}
			else if(isInt(result.replace("\\",""))){
				return (char) Integer.parseInt(result.replace("\\",""));
			}else{
			return result.charAt(1);}
	}else{
	return result.charAt(0);}
		
	}


private boolean isInt(String text){
if(text.isEmpty()){
	return false;
}else{
	for(char c: text.toCharArray()){
		if(!Character.isDigit(c)){
			return false;
			}
		}
	}return true;
}


%}

// ************  Macros ********************
INT_CONSTANT = 0|[1-9][0-9]*  
LETTER = [a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—]
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

read	{this.yylval = yytext();	return Parser.READ;}
write	{this.yylval = yytext();	return Parser.WRITE;}
While	{this.yylval = yytext();	return Parser.WHILE;}
if		{this.yylval = yytext();	return Parser.IF;}
else	{this.yylval = yytext();	return Parser.ELSE;}
int		{this.yylval = yytext();	return Parser.INT;}	
double	{this.yylval = yytext();	return Parser.DOUBLE;}
char	{this.yylval = yytext();	return Parser.CHAR;}
struct	{this.yylval = yytext();	return Parser.STRUCT;}
return	{this.yylval = yytext();	return Parser.RETURN;}
void	{this.yylval = yytext();	return Parser.VOID;}
main	{this.yylval = yytext();	return Parser.MAIN;}

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




