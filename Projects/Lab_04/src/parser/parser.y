%{
// * Java declarations
//   This code is copied in the beginning of the generated Java file
import scanner.Scanner;
import ast.*;
import java.util.*;
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
%nonassoc LESSTHANELSE
%nonassoc ELSE
%right UNARY_MINUS
%nonassoc '[' ']'
%left '.'
%nonassoc '{' '}'
%nonassoc '(' ')'



%%
// * Actions
program:	opt_def_glob_var main			{ ((List<Definition>)$1).add((Definition)$2); this.root = new Program(lexico.getLine(), lexico.getColumn(), (List<Definition>)$1); }
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
					|/**EMPTY**/
					;

opt_list_fields:	opt_list_fields type list_ident ';'
					|/**EMPTY**/
					;

struct_def:		STRUCT '{' opt_list_fields '}' list_ident ';'						{ $$ = new ArrayList<Definition>(); addStructDefs((List<Definition>)$$, (List<Definition>)$3, (List<String>)$5, lexico.getLine()); }
				;

main:	VOID MAIN '(' ')' '{' opt_list_local_var statements '}'						{ $$ = new DefFunc(lexico.getLine(), lexico.getColumn(), new FuncType(lexico.getLine(), lexico.getColumn(), VoidType.getInstance()), "main", (List<Definition>)$6, (List<Statement>)$7); }
		;

func_def:	p_type ID '(' opt_list_param ')' '{' opt_list_local_var statements '}'
		| VOID ID '(' opt_list_param ')' '{' opt_list_local_var statements '}'
		;


param:	p_type ID									{ $$ = new ArrayList<Definition>(); ((List<Definition>)$$).add(new DefVar(lexico.getLine(), lexico.getColumn(), (Type)$1, (String)$2)); }
		;

list_param:	list_param ',' param					{ $$ = $1; DefVar local = $3; ((List<Definition>)$$).add(new DefVar(lexico.getLine(), lexico.getColumn(), (Type)local.getType(), (String)local.getIdent())); }
			|param									{ $$ = $1;}
			;

opt_list_param:	list_param							{ $$ = $1;}
				|/**EMPTY**/						{ $$ = new ArrayList<Definition>(); }
				;

p_type:	INT											{ $$ = IntType.getInstance(); }
		|DOUBLE										{ $$ = Type.getInstance(); }
		|CHAR										{ $$ = CharType.getInstance(); }
		;

array:	type '[' INT_CONSTANT ']'					{ $$ = getArrayDef((Type)$1, (Integer)$3, lexico.getLine()); }
		;
		
type:	p_type										{ $$ =$1;}
		|array										{ $$ =$1;}
		;

list_ident:		list_ident ',' ID					{ $$ = $1; ((List<String>)$$).add((String)$3); }
				|ID									{ $$ = new ArrayList<String>(); ((List<String>)$$).add((String)$1); }
				;

statements:	statements statement					{ $$ = $1; ((List<Statement>)$$).add((Statement)$2); }
				|/**EMPTY**/						{ $$ = new ArrayList<Statement>(); }
				;

statement:		RETURN exp ';' 						{ $$ = new Return(lexico.getLine(), lexico.getColumn(), (Expression)$2); }
				| READ '(' list_exp ')' ';'			{ $$ = new Read(lexico.getLine(), lexico.getColumn(), (List<Expression>)$2); }
				| WRITE list_exp ';'				{ $$ = new Write(lexico.getLine(), lexico.getColumn(), (List<Expression>)$2); }
				| if_else							{ $$ = $1;}
				| while								{ $$ = $1;}		
				| exp '=' exp ';'					{ $$ = new Assignment(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$3); }
				| ID '(' opt_list_exp ')' ';'		{ $$ = new CallFunc(lexico.getLine(), lexico.getColumn(), (String)$1, (List<Expression>)$3); }
				;
				
while:	WHILE '(' exp ')' '{' statements '}'		{ $$ = new While(lexico.getLine(), lexico.getColumn(), (Expression)$3, (List<Statement>)$6); }
		|WHILE '(' exp ')' statement				{ $$ = new While(lexico.getLine(), lexico.getColumn(), (Expression)$3, (Statement)$5); }
		;
		
if_else:	IF '(' exp ')' '{' statements '}'	ELSE '{' statements '}'   { $$ = new IfElse(lexico.getLine(), lexico.getColumn(), (Expression)$3, (List<Statement>)$6, (List<Statement>)$10); }
			|IF '(' exp ')' '{' statements '}'	ELSE statement            { $$ = new IfElse(lexico.getLine(), lexico.getColumn(), (Expression)$3, (List<Statement>)$6, (Statement)$9); }
			|IF '(' exp ')' statement ELSE '{' statements '}'             { $$ = new IfElse(lexico.getLine(), lexico.getColumn(), (Expression)$3, (Statement)$5, (List<Statement>)$8); }
			|IF '(' exp ')' statement ELSE statement                      { $$ = new IfElse(lexico.getLine(), lexico.getColumn(), (Expression)$3, (Statement)$5, (Statement)$7); }
			|IF '(' exp ')' '{' statements '}' %prec LESSTHANELSE         { $$ = new IfElse(lexico.getLine(), lexico.getColumn(), (Expression)$3, (List<Statement>)$6); }
			|IF '(' exp ')' statement %prec LESSTHANELSE                  { $$ = new IfElse(lexico.getLine(), lexico.getColumn(), (Expression)$3, (Statement)$5); }
			;

opt_list_exp:	list_exp		{ $$ = $1; }
				|/**EMPTY**/		{ $$ = new ArrayList<Expression>(); }
				;

list_exp:	list_exp ',' exp   { $$ = $1; ((List<Expression>)$$).add((Expression)$3); }                   
			| exp              { $$ = new ArrayList<Expression>(); ((List<Expression>)$$).add((Expression)$1); }                   
			;                                     
 
exp:	exp '+' exp                               		{ $$ = new ArithmeticOperation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$3, "+"); }
		| exp '*' exp                             		{ $$ = new ArithmeticOperation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$3, "*"); }
		| exp '/' exp                             		{ $$ = new ArithmeticOperation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$3, "/"); }
		| exp '-' exp                             		{ $$ = new ArithmeticOperation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$3, "-"); }
		| exp '%' exp                             		{ $$ = new ArithmeticOperation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$3, "%"); }
		| exp EQ exp                              		{ $$ = new CompOperation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$3, "=="); }
		| exp NOT_EQ exp                          		{ $$ = new CompOperation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$3, "!="); }
		| exp '>' exp									{ $$ = new CompOperation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$3, ">"); }
		| exp '<' exp									{ $$ = new CompOperation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$3, "<"); }
		| exp L_EQ exp									{ $$ = new CompOperation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$3, "<="); }
		| exp G_EQ exp									{ $$ = new CompOperation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$3, ">="); }
		| exp AND exp									{ $$ = new LogicOperation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$3, "&&"); }
		| exp OR exp									{ $$ = new LogicOperation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$3, "||"); }
		| exp '!' exp
		| '(' p_type ')' exp							{ $$ = new Cast(lexico.getLine(), lexico.getColumn(), (Type)$2, (Expression)$4); }
		| exp '[' exp ']'								{ $$ = new ArrayAccess(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$3); }
		| exp '.' ID									{ $$ = new StructAccess(lexico.getLine(), lexico.getColumn(), (Expression)$1, (String)$3); }
		| ID '(' opt_list_exp ')'						{ $$ = new CallFunction(lexico.getLine(), lexico.getColumn(), (String)$1, (List<Expression>)$3); }
		| '-' exp					%prec UNARY_MINUS	{ $$ = new UnaryMinus(lexico.getLine(), lexico.getColumn(), $2); }
		| '(' exp ')'									{ $$ = $2; }
		| '!' exp										{ $$ = new UnaryNot(lexico.getLine(), lexico.getColumn(), $2); }
		| INT_CONSTANT									{ $$ = new LiteralInt(lexico.getLine(), lexico.getColumn(), (Integer)$1); }
		| ID											{ $$ = new Variable(lexico.getLine(), lexico.getColumn(), (String)$1); }
		| CHAR_CONSTANT									{ $$ = new LiteralChar(lexico.getLine(), lexico.getColumn(), (Character)$1); }
		| REAL_CONSTANT		  							{ $$ = new LiteralReal(lexico.getLine(), lexico.getColumn(), (Double)$1); }
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

private ArrayType getArrayDef(Type type, int length, int line) {
	if(type instanceof ArrayType) {
		Type head = type;
		Type prev = type;
		Type actual = ((ArrayType)type).typeOf;
		while(actual instanceof ArrayType) {
			prev = actual;
			actual = ((ArrayType)actual).typeOf;
		}
		actual = (Type) new ArrayType(line, lexico.getColumn(), actual, length);
		((ArrayType)prev).typeOf = actual;
		
		return (ArrayType)head;
	
	} else {
		return new ArrayType(line, lexico.getColumn(), type, length);
	}
}

private void addStructDefs(List<Definition> defsList, List<Definition> fields, List<String> idents, int line) {
	for(String id : idents) {
		defsList.add(new VariableDef(line, lexico.getColumn(), new StructType(lexico.getLine(), lexico.getColumn(), fields), id));
	}
}
