%{
// * Java declarations
//   This code is copied in the beginning of the generated Java file
import scanner.Scanner;
import java.util.ArrayList;
import java.util.List;

import ast.*;
import error.Err;
import error.ErrorHandler;
import ast.definition.DefField;
import ast.definition.DefFunc;
import ast.definition.DefVar;
import ast.definition.Definition;
import ast.expression.ArrayAccess;
import ast.expression.CallFunction;
import ast.expression.Cast;
import ast.expression.Expression;
import ast.expression.LiteralChar;
import ast.expression.LiteralInt;
import ast.expression.LiteralReal;
import ast.expression.StructAccess;
import ast.expression.Variable;
import ast.expression.binary.ArithmeticOperation;
import ast.expression.binary.CompOperation;
import ast.expression.binary.LogicOperation;
import ast.expression.unary.UnaryMinus;
import ast.expression.unary.UnaryNot;
import ast.statement.Assigment;
import ast.statement.CallFunc;
import ast.statement.IfElse;
import ast.statement.Read;
import ast.statement.Return;
import ast.statement.Statement;
import ast.statement.While;
import ast.statement.Write;
import ast.type.ArrayType;
import ast.type.CharType;
import ast.type.FuncType;
import ast.type.IntType;
import ast.type.StructType;
import ast.type.Type;
import ast.type.VoidType;
import ast.type.RealType;
import ast.type.MainType;
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
INC
DEC
%token 

//Lower precedence
%right '='
%left OR AND '!'
%left EQ NOT_EQ
%left L_EQ G_EQ '>' '<'
%left '+' '-'
%left INC DEC
%left '*' '/' '%'
%nonassoc LESSTHANELSE
%nonassoc ELSE
%right UNARY_MINUS
%nonassoc '[' ']'
%left '.'
%nonassoc '(' ')'



%%
// * Actions
program:	opt_def_glob_var main			{ ((List<Definition>)$1).add((Definition)$2); this.root = new Program(scanner.getLine(), scanner.getColumn(), (List<Definition>)$1); }
			;

glob_def:	var_def				{ $$ = $1; }
			|struct_def			{ $$ = $1; }
			|func_def			{ $$ = $1; }
			;

opt_def_glob_var:	opt_def_glob_var glob_def		{ $$ = $1; mergeDefs((List<Definition>)$$, $2); }
					|/**EMPTY**/					{ $$ = new ArrayList<Definition>(); }
					;

var_def:	p_type list_ident ';'		{ $$ = new ArrayList<Definition>(); addVarDefs((List<Definition>)$$, (Type)$1, (List<String>)$2, scanner.getLine()); }
			| array list_ident ';'		{ $$ = new ArrayList<Definition>(); addVarDefs((List<Definition>)$$, (Type)$1, (List<String>)$2, scanner.getLine()); }
			;
			

local_var_def:	var_def					{$$ = $1;}
				| struct_def			{$$ = $1;}
				;

opt_list_local_var:	opt_list_local_var local_var_def			{ $$ = $1; mergeDefs((List<Definition>)$$, $2); }
					|/**EMPTY**/								{ $$ = new ArrayList<Definition>(); }
					;

opt_list_fields:	opt_list_fields type list_ident ';'			{ $$ = $1; addFieldDefs((List<Definition>)$$, (Type)$2, (List<String>)$3, scanner.getColumn()); }
					|/**EMPTY**/								{ $$ = new ArrayList<Definition>(); }
					;

struct_def:		STRUCT '{' opt_list_fields '}' list_ident ';'						{ $$ = new ArrayList<Definition>(); addStructDefs((List<Definition>)$$, (List<Definition>)$3, (List<String>)$5, scanner.getLine()); }
				;

main:	VOID MAIN '(' ')' '{' opt_list_local_var statements '}'						{ $$ = new DefFunc(scanner.getLine(), scanner.getColumn(),(Type) new MainType(scanner.getLine(), scanner.getColumn(), (Type)VoidType.getInstance()), "main", (List<Definition>)$6, (List<Statement>)$7); }
		;

func_def:	p_type ID '(' opt_list_param ')' '{' opt_list_local_var statements '}'  { $$ = new DefFunc(scanner.getLine(), scanner.getColumn(), (Type)new FuncType(scanner.getLine(), scanner.getColumn(), (Type)$1, (List<Definition>)$4), (String)$2, (List<Definition>)$7, (List<Statement>)$8); }
		| VOID ID '(' opt_list_param ')' '{' opt_list_local_var statements '}'		{ $$ = new DefFunc(scanner.getLine(), scanner.getColumn(),(Type) new FuncType(scanner.getLine(), scanner.getColumn(), (Type)VoidType.getInstance(), (List<Definition>)$4), (String)$2, (List<Definition>)$7, (List<Statement>)$8); }
		;



list_param:	list_param ',' p_type ID					{ ((List<Definition>)$$).add(new DefVar(scanner.getLine(), scanner.getColumn(), (Type)$3, (String)$4)); $$ = $1; }
			|p_type ID									{ $$ = new ArrayList<Definition>();((List<Definition>)$$).add(new DefVar(scanner.getLine(), scanner.getColumn(), (Type)$1, (String)$2)); }
			;

opt_list_param:	list_param							{ $$ = $1;}
				|/**EMPTY**/						{ $$ = new ArrayList<Definition>(); }
				;

p_type:	INT											{ $$ = IntType.getInstance(); }
		|DOUBLE										{ $$ = RealType.getInstance(); }
		|CHAR										{ $$ = CharType.getInstance(); }
		;

array:	type '[' INT_CONSTANT ']'					{ $$ = getArrayDef((Type)$1, (Integer)$3, scanner.getLine()); }
		;
		
type:	p_type										{ $$ =$1;}
		|array										{ $$ =$1;}
		;

list_ident:		list_ident ',' ID					{ ((List<String>)$$).add((String)$3); $$ = $1;  }
				|ID									{ $$ = new ArrayList<String>(); ((List<String>)$$).add((String)$1); }
				;

statements:	statements statement					{ ((List<Statement>)$$).add((Statement)$2); $$ = $1; }
				|/**EMPTY**/						{ $$ = new ArrayList<Statement>(); }
				;

statement:		RETURN exp ';' 						{ $$ = new Return(scanner.getLine(), scanner.getColumn(), (Expression)$2); }
				| READ '(' list_exp ')' ';'			{ $$ = new Read(scanner.getLine(), scanner.getColumn(), (List<Expression>)$2); }
				| WRITE list_exp ';'				{ $$ = new Write(scanner.getLine(), scanner.getColumn(), (List<Expression>)$2); }
				| if_else							{ $$ = $1;}
				| while								{ $$ = $1;}		
				| exp '=' exp ';'					{ $$ = new Assigment(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3); }
				| ID '(' opt_list_exp ')' ';'		{ $$ = new CallFunc(scanner.getLine(), scanner.getColumn(), (String)$1, (List<Expression>)$3); }
				| exp INC ';' 							{ $$ = new Assigment(scanner.getLine(), scanner.getColumn(), (Expression)$1,(Expression) new ArithmeticOperation(scanner.getLine(), scanner.getColumn(),(Expression)$1,new LiteralInt(scanner.getLine(),scanner.getColumn(),1),'+'));}
				| exp DEC ';'                         { $$ = new Assigment(scanner.getLine(), scanner.getColumn(), (Expression)$1,(Expression) new ArithmeticOperation(scanner.getLine(), scanner.getColumn(),(Expression)$1,new LiteralInt(scanner.getLine(),scanner.getColumn(),1),'-'));}
				;
				
while:	WHILE '(' exp ')' '{' statements '}'		{ $$ = new While(scanner.getLine(), scanner.getColumn(), (Expression)$3, (List<Statement>)$6); }
		|WHILE '(' exp ')' statement				{ $$ = new While(scanner.getLine(), scanner.getColumn(), (Expression)$3, (List<Statement>)$5); }
		;
		
if_else:	IF '(' exp ')' '{' statements '}'	ELSE '{' statements '}'   { $$ = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)$3, (List<Statement>)$6, (List<Statement>)$10); }
			|IF '(' exp ')' '{' statements '}'	ELSE statement            { $$ = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)$3, (List<Statement>)$6, (Statement)$9); }
			|IF '(' exp ')' statement ELSE '{' statements '}'             { $$ = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)$3, (Statement)$5, (List<Statement>)$8); }
			|IF '(' exp ')' statement ELSE statement                      { $$ = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)$3, (Statement)$5, (Statement)$7); }
			|IF '(' exp ')' '{' statements '}' %prec LESSTHANELSE         { $$ = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)$3, (List<Statement>)$6, new ArrayList()); }
			|IF '(' exp ')' statement %prec LESSTHANELSE                  { $$ = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)$3, (Statement)$5, new ArrayList()); }
			;

opt_list_exp:	list_exp		{ $$ = $1; }
				|/**EMPTY**/		{ $$ = new ArrayList<Expression>(); }
				;

list_exp:	list_exp ',' exp   { $$ = $1; ((List<Expression>)$$).add((Expression)$3); }                   
			| exp              { $$ = new ArrayList<Expression>(); ((List<Expression>)$$).add((Expression)$1); }                   
			;                                     
 
exp:	exp '+' exp                               		{ $$ = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3, '+'); }
		| exp '*' exp                             		{ $$ = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3, '*'); }
		| exp '/' exp                             		{ $$ = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3, '/'); }
		| exp '-' exp                             		{ $$ = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3, '-'); }
		| exp '%' exp                             		{ $$ = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3, '%'); }
		| exp EQ exp                              		{ $$ = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3, "=="); }
		| exp NOT_EQ exp                          		{ $$ = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3, "!="); }
		| exp '>' exp									{ $$ = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3, ">"); }
		| exp '<' exp									{ $$ = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3, "<"); }
		| exp L_EQ exp									{ $$ = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3, "<="); }
		| exp G_EQ exp									{ $$ = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3, ">="); }
		| exp AND exp									{ $$ = new LogicOperation(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3, "&&"); }
		| exp OR exp									{ $$ = new LogicOperation(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3, "||"); }
		| '(' p_type ')' exp							{ $$ = new Cast(scanner.getLine(), scanner.getColumn(), (Type)$2, (Expression)$4); }
		| exp '[' exp ']'								{ $$ = new ArrayAccess(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3); }
		| exp '.' ID									{ $$ = new StructAccess(scanner.getLine(), scanner.getColumn(), (Expression)$1, (String)$3); }
		| ID '(' opt_list_exp ')'						{ $$ = new CallFunction(scanner.getLine(), scanner.getColumn(), (String)$1, (List<Expression>)$3); }
		| '-' exp					%prec UNARY_MINUS	{ $$ = new UnaryMinus(scanner.getLine(), scanner.getColumn(), (Expression)$2); }
		| '(' exp ')'									{ $$ = $2; }
		| '!' exp										{ $$ = new UnaryNot(scanner.getLine(), scanner.getColumn(), (Expression)$2); }
		| INT_CONSTANT									{ $$ = new LiteralInt(scanner.getLine(), scanner.getColumn(), (Integer)$1); }
		| ID											{ $$ = new Variable(scanner.getLine(), scanner.getColumn(), (String)$1); }
		| CHAR_CONSTANT									{ $$ = new LiteralChar(scanner.getLine(), scanner.getColumn(), (Character)$1); }
		| REAL_CONSTANT		  							{ $$ = new LiteralReal(scanner.getLine(), scanner.getColumn(), (Double)$1); }
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
ErrorHandler.getInstance().addError(new Err(scanner.getLine(), scanner.getColumn(),
				"Syntax error -> " + yylex() + " (" + scanner.yytext() + ")"));
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
		Type actual = ((ArrayType)type).getTypeOf();
		while(actual instanceof ArrayType) {
			prev = actual;
			actual = ((ArrayType)actual).getTypeOf();
		}
		actual = (Type) new ArrayType(line, scanner.getColumn(), actual, length);
		((ArrayType)prev).setTypeOf(actual);
		
		return (ArrayType)head;
	
	} else {
		return new ArrayType(line, scanner.getColumn(), type, length);
	}
}

private void addStructDefs(List<Definition> defsList, List<Definition> fields, List<String> idents, int line) {
	for(String id : idents) {
		defsList.add(new DefVar(line, scanner.getColumn(), (Type)new StructType(scanner.getLine(), scanner.getColumn(), fields), id));
	}
}


private void mergeDefs(List<Definition> defsA, Object defB) {
	if(defB instanceof List) {
		for(Definition def : (List<Definition>)defB) {
			defsA.add(def);
		}
	}
	else {
		defsA.add((Definition)defB);
	}
}

private void addVarDefs(List<Definition> defsList, Type type, List<String> idents, int line) {
	for(String id : idents) {
		defsList.add(new DefVar(line, scanner.getColumn(), type, id));
	}
}

private void addFieldDefs(List<Definition> defsList, Type type, List<String> idents, int line) {
	for(String s : idents) {
		defsList.add(new DefField(line, scanner.getColumn(), type, s));
	}
}
private ASTNode root;

public ASTNode getRoot() {
	return this.root;
}

