%{
// * Java declarations
//   This code is copied in the beginning of the generated Java file
import scanner.Scanner;
%}

// * Yacc declarations
//   Token definition
%token
CONSTANT
REAL_CONSTANT
EQ
POW
VOID
OR
RETURN
ID
DOUBLE
WRITE
ELSE
IF
CHAR
STRUCT
READ
AND
NOT_EQ
WHILE
G_EQ
INT
L_EQ
MAIN
CHAR_CONSTANT
%token 

//Lower precedence
%left '+' '-'
%left '*'
%right UNARY_MINUS



%%
// * Actions
expression: expression '+' expression
          | expression '*' expression
          |	expression '-' expression 
          | INT_CONSTANT				%prec UNARY_MINUS
          ;          

%%

// * Code
//   Members of the generated Parser class
// We must implement at least:
// - int yylex()
// - void yyerror(String)

// * Lexical analyzer
private Scanner scanner;

// * Invocation to the scanner
private int yylex () {
    int token=0;
    try { 
		token=scanner.yylex();
		this.yylval = scanner.getYylval(); 
    } catch(Throwable e) {
	    System.err.println ("Lexical error in line " + scanner.getLine()+
		" and column "+scanner.getColumn()+":\n\t"+e); 
    }
    return token;
}

// * Syntax error handler
public void yyerror (String error) {
    System.err.println ("Syntax error in line " + scanner.getLine()+
		" and column "+scanner.getColumn()+":\n\t"+error);
}

// * Constructor
public Parser(Scanner scanner) {
	this.scanner= scanner;
	//this.yydebug = true;
}
