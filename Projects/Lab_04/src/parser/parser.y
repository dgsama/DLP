%{
// * Java declarations
//   This code is copied in the beginning of the generated Java file
import scanner.Scanner;
%}

// * Yacc declarations
//   Token definition
%token
INT_CONSTANT
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
%right '='
%left OR AND '!'
%left EQ NOT_EQ
%left L_EQ G_EQ '>' '<'
%left '+' '-'
%left '*' '/' '%'
%right UNARY_MINUS
%nonassoc '[' ']'
%left '.'
%nonassoc '(' ')'



%%
// * Actions
program:	opt_def_glob_var main
			;

glob_def:	var_def
			|struct_def
			|func_def
			;

opt_def_glob_var:	opt_def_glob_var glob_def
					|/**EMPTY**/
					;

var_def:	p_type list_ident ';'
			| array list_ident ';'
			;
			

local_var_def:	var_def
				| struct_def
				;

opt_list_local_var:	opt_list_local_var local_var_def
					;

opt_list_fields:	opt_list_fields type list_ident ';'
					;

struct_def:		STRUCT '{' opt_list_fields '}' list_ident ';'
				;

main:	VOID MAIN '(' ')' '{' opt_list_local_var opt_statements '}'
		;

func_def:	ID p_type '(' opt_list_param ')' '{' opt_list_local_var opt_statements '}'
		|ID VOID '(' opt_list_param ')' '{' opt_list_local_var opt_statements '}'
		;


param:	p_type ID
		;

list_param:	list_param ',' param
			|param
			;

opt_list_param:	list_param
				|/**EMPTY**/
				;

p_type:	INT
		|DOUBLE
		|CHAR
		;

array:	type '[' INT_CONSTANT ']'
		;
		
type:	p_type
		|array
		;

list_ident:		list_ident ',' ID
				|ID
				;

opt_statements:	/**EMPTY**/
					|opt_statements statement
				;

statement:		RETURN '(' exp ')' ';' 
				| READ '(' list_exp ')' ';'
				| WRITE '(' list_exp ')' ';'
				| IF '(' exp ')' '{' opt_statements '}'
				| IF '(' exp ')' '{' opt_statements '}' ELSE '{' opt_statements '}'
				|while
				| exp '=' exp ';'
				| ID '(' opt_list_exp ')' ';'
				;
				
while:	WHILE '(' exp ')' '{' opt_statements '}'
		|WHILE '(' exp ')' statement
		
opt_list_exp:	/**EMPTY**/
				|list_exp
				;

list_exp:	list_exp ',' exp
			| exp
			;

exp:	exp '+' exp
		| exp '*' exp
		| exp '/' exp
		| exp '-' exp
		| exp '%' exp
		| exp EQ exp
		| exp NOT_EQ exp
		| exp '>' exp
		| exp '<' exp
		| exp L_EQ exp
		| exp G_EQ exp
		| exp AND exp
		| exp OR exp
		| exp '!' exp
		| '(' p_type ')' exp
		| exp '[' exp ']'
		| exp '.' ID
		| ID '(' opt_list_exp ')'
		| '-' exp					%prec UNARY_MINUS
		| '(' exp ')'
		| INT_CONSTANT
		| ID
		| CHAR_CONSTANT
		| REAL_CONSTANT		  
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
