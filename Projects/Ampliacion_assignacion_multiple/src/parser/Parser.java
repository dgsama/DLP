//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package parser;



//#line 2 "../../src/parser/parser.y"
/* * Java declarations*/
/*   This code is copied in the beginning of the generated Java file*/
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
import ast.expression.InvocationExp;
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
import ast.expression.AssignmentExpr;
import ast.statement.Assignment;
import ast.statement.InvocationSt;
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
//#line 62 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short INT_CONSTANT=257;
public final static short REAL_CONSTANT=258;
public final static short EQ=259;
public final static short POW=260;
public final static short VOID=261;
public final static short OR=262;
public final static short RETURN=263;
public final static short ID=264;
public final static short DOUBLE=265;
public final static short WRITE=266;
public final static short ELSE=267;
public final static short IF=268;
public final static short CHAR=269;
public final static short STRUCT=270;
public final static short READ=271;
public final static short AND=272;
public final static short NOT_EQ=273;
public final static short WHILE=274;
public final static short G_EQ=275;
public final static short INT=276;
public final static short L_EQ=277;
public final static short MAIN=278;
public final static short CHAR_CONSTANT=279;
public final static short INC=280;
public final static short DEC=281;
public final static short LESSTHANELSE=282;
public final static short CAST=283;
public final static short UNARY_MINUS=284;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    3,    1,    1,    4,    4,    4,   10,   11,
   11,   12,   12,    9,    2,    5,    5,   16,   16,   15,
   15,    6,    6,    6,    8,   13,   13,   13,    7,    7,
   14,   14,   17,   17,   17,   17,   17,   17,   17,   21,
   21,   20,   20,   20,   20,   20,   20,   22,   22,   19,
   19,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,
};
final static short yylen[] = {                            2,
    2,    1,    1,    2,    0,    3,    3,    3,    1,    2,
    0,    4,    0,    4,    8,    9,    9,    4,    2,    1,
    0,    1,    1,    1,    4,    1,    1,    1,    3,    1,
    2,    0,    3,    3,    3,    1,    1,    4,    5,    7,
    5,   11,    9,    9,    7,    7,    5,    1,    0,    3,
    1,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    4,    4,    3,    4,    2,    3,
    2,    1,    1,    1,    1,    3,
};
final static short yydefred[] = {                         5,
    0,    0,    0,   23,   24,    0,   22,    1,    4,    2,
    3,    0,    0,    0,    0,    0,    0,   13,    0,    0,
   30,    0,    0,    0,    0,    0,    0,    0,    6,    0,
    7,    8,    0,    0,    0,    0,    0,   14,   26,   27,
   28,    0,    0,   29,   25,   19,    0,    0,   11,    0,
    0,   11,    0,    0,   12,   11,    0,   18,    9,    0,
   10,    0,    0,    0,   72,   75,    0,    0,    0,    0,
    0,    0,   74,    0,    0,    0,   15,   31,    0,   36,
   37,    0,   17,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   16,    0,    0,   33,    0,    0,   35,    0,    0,
   34,    0,    0,   70,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   67,
    0,    0,    0,    0,    0,    0,    0,   38,   66,   68,
   39,   32,    0,   32,   41,    0,    0,    0,    0,   32,
   45,   40,    0,    0,   32,   43,   44,    0,   42,
};
final static short yydgoto[] = {                          1,
    2,    8,    9,   59,   11,   60,   20,   13,   14,   61,
   54,   27,   15,   62,   35,   36,   78,   79,  116,   80,
   81,  117,
};
final static short yysindex[] = {                         0,
    0, -152, -249,    0,    0, -106,    0,    0,    0,    0,
    0, -230, -229, -229,  -40,   18,   29,    0,   35,  -38,
    0,   32,   44, -198, -182,   36, -120, -182,    0, -183,
    0,    0,  -11, -171,   54,   60,  -18,    0,    0,    0,
    0,  -90,   66,    0,    0,    0,    6, -182,    0,   77,
   14,    0, -141, -135,    0,    0, -135,    0,    0, -229,
    0,  -33, -135,  -15,    0,    0,  261,  103,  261,  107,
  261,  111,    0,  261,  261,  207,    0,    0,  800,    0,
    0,    3,    0,  112,  591,  261,  868,   81,  261,   95,
  261,   -5,  -44,  114,  617,  261,  261,  261,  261,  261,
  261,  261,  261,  261,  261,  261,  261,  261,  261,  261,
 -105,    0,  261,  261,    0,  116,  122,    0,  261,  624,
    0,  650,  261,    0,  124,   -5,   -5,  124,  -23,  -23,
  676,  -23,  -23,   85,   85,  -44,  -44,  -44,  765,    0,
  123,  868,  109,  868,   21,   39,  -44,    0,    0,    0,
    0,    0,  -94,    0,    0,   57,   75,   93,  -92,    0,
    0,    0,  113,  132,    0,    0,    0,  150,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   87,   88,   89,    0,    0,    0,    0,   98,    0,
    0,    0,    0,    0,  140,    0,    0,  140,    0,    0,
    0,    0,    0,    0,    0,  144,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  168,    0,    0,  168,    0,    0,   87,
    0,    0,  168,    0,    0,    0,    0,  894,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  486,    0,  146,    5,    0,    0,    0,
    0,  -28,  512,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  146,    0,    0,  147,    0,    0,    0,    0,
    0,    0,    0,    0,  444,   19,   55, 1027,  957,  985,
    0,  992, 1020,  922,  948,  521,  547,  556,    0,    0,
    0,   26,  915,   30,    0,    0,  582,    0,    0,    0,
    0,    0,  187,    0,    0,    0,    0,    0,  243,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  189,    0,   25,   -3,  162,  165,    0,
  -48,    0,  166,  -54,  169,    0, -101, 1227,  -43,    0,
    0,   83,
};
final static int YYTABLESIZE=1350;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         74,
   24,  111,   64,   57,   38,   30,   76,   63,   82,   22,
   23,   75,   71,  109,   16,   71,   18,   74,  107,  105,
   29,  106,  111,  108,   76,   88,   12,   90,   17,   75,
   71,  109,   71,   19,   21,   74,  107,  105,   50,  106,
  111,  108,   76,  153,  155,   51,  110,   75,   51,   34,
   24,   39,   34,   74,  104,  161,  103,   25,   33,   64,
   76,  166,   64,   51,   71,   75,   76,  110,   26,   76,
   50,   74,   53,   50,   28,   30,   37,   64,   76,   64,
   44,   45,    4,   75,   76,  110,    5,   30,   50,   74,
   31,   77,   46,    7,   47,   63,   76,  156,   63,  158,
   94,   75,   32,   48,   49,  164,   51,   74,    3,   83,
  168,   64,    4,   63,   76,   63,    5,    6,   76,   75,
   30,  109,   58,    7,  119,   74,  107,  112,   52,    4,
  111,  108,   76,    5,    6,   55,   56,   75,  119,  118,
    7,   30,   86,  152,    4,   74,   89,   63,    5,    6,
   91,  113,   76,  121,  123,    7,   30,   75,  140,  119,
  109,  154,  143,  150,   74,  107,  105,  151,  106,  111,
  108,   76,  157,   21,  163,  110,   75,   26,   27,   28,
   21,  159,   74,  104,   20,  103,   49,   48,   40,   76,
   10,   41,   42,    0,   75,  141,   43,  160,    0,    0,
   32,    0,    0,    0,    0,    0,    0,   32,    0,    0,
    0,    0,   32,    0,  110,    0,    0,  162,    0,   47,
    0,    0,    0,   65,   66,    0,   47,    0,    0,   67,
   68,   47,   69,   71,   70,  165,    0,   71,    0,   74,
   72,   65,   66,   71,    0,   73,   76,   67,   68,    0,
   69,   75,   70,   96,    0,   71,  167,    0,   72,   65,
   66,    0,    0,   73,    0,   67,   68,   99,   69,  100,
   70,  101,    0,   71,  169,   46,   72,   65,   66,    0,
   64,   73,   46,   67,   68,    0,   69,   46,   70,    0,
   64,   71,   32,   74,   72,   65,   66,    0,    0,   73,
   76,   67,   68,    0,   69,   75,   70,    0,    0,   71,
    0,   47,   72,   65,   66,    0,   63,   73,    0,   67,
   68,    0,   69,    0,   70,    0,   63,   71,    0,    0,
   72,   65,   66,    0,    0,   73,    0,   67,   68,    0,
   69,    0,   70,    0,    0,   71,    0,    0,   72,   65,
   66,    0,    0,   73,    0,   67,   68,    0,   69,    0,
   70,    0,    0,   71,    0,    0,   72,   46,    0,   65,
   66,   73,    0,    0,    0,   67,   68,    0,   69,    0,
   70,    0,    0,   71,    0,    0,   72,    0,   65,   66,
    0,   73,    0,    0,   67,   68,    0,   69,  100,   70,
  101,    0,   71,    0,    0,   72,   65,   66,    0,    0,
   73,    0,   67,   68,    0,   69,    0,   70,    0,    0,
   71,    0,    0,   72,   32,   32,    0,    0,   73,    0,
   32,   32,    0,   32,    0,   32,    0,    0,   32,    0,
    0,   32,    0,   47,   47,    0,   32,    0,    0,   47,
   47,    0,   47,    0,   47,    0,    0,   47,    0,    0,
   47,    0,    0,   65,   66,   47,    0,    0,    0,    0,
   84,    4,    0,    0,    0,    5,    0,    0,    0,    0,
    0,    0,    7,    0,   57,   73,    0,   57,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   46,
   46,    0,   57,    0,   57,   46,   46,    0,   46,    0,
   46,    0,    0,   46,    0,    0,   46,   65,   66,    0,
    0,   46,   73,    0,   84,    0,   73,   73,   73,   73,
   73,   73,   73,    0,    0,    0,   57,    0,    0,   73,
    0,    0,    0,    0,   73,   73,   73,   73,   69,    0,
    0,    0,   69,   69,   69,   69,   69,   53,   69,    0,
    0,   53,   53,   53,   53,   53,    0,   53,    0,    0,
   69,   69,   69,   69,    0,    0,   73,    0,   73,   53,
   53,   53,   53,   54,    0,    0,    0,   54,   54,   54,
   54,   54,   56,   54,    0,    0,   56,   56,   56,   56,
   56,    0,   56,    0,   69,   54,   54,   54,   54,    0,
    0,    0,    0,   53,   56,   56,   56,   56,   65,    0,
    0,    0,   65,   65,   65,   65,   65,  109,   65,    0,
    0,    0,  107,  105,    0,  106,  111,  108,    0,   54,
   65,   65,   65,   65,    0,    0,    0,    0,   56,  115,
  104,  114,  103,  109,    0,    0,    0,  124,  107,  105,
  109,  106,  111,  108,  145,  107,  105,    0,  106,  111,
  108,    0,    0,    0,   65,    0,  104,  114,  103,    0,
    0,  110,    0,  104,  114,  103,  109,    0,    0,    0,
  146,  107,  105,    0,  106,  111,  108,    0,    0,    0,
    0,    0,   57,    0,    0,   57,    0,  110,    0,  104,
  114,  103,  109,    0,  110,   57,   57,  107,  105,    0,
  106,  111,  108,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  148,  104,  114,  103,    0,    0,
  110,    0,    0,    0,   73,    0,    0,   73,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   73,   73,    0,
   73,    0,   73,    0,    0,    0,  110,    0,    0,    0,
   69,    0,    0,   69,    0,    0,    0,    0,    0,   53,
    0,    0,   53,   69,   69,    0,   69,    0,   69,    0,
    0,    0,   53,   53,    0,   53,    0,   53,    0,    0,
    0,  109,    0,    0,    0,   54,  107,  105,   54,  106,
  111,  108,    0,    0,   56,    0,    0,   56,   54,   54,
    0,   54,    0,   54,  104,  114,  103,   56,   56,    0,
   56,    0,   56,    0,    0,    0,  109,    0,    0,    0,
   65,  107,  105,   65,  106,  111,  108,    0,    0,   96,
    0,    0,   97,   65,   65,  110,   65,  149,   65,  104,
  102,  103,   98,   99,    0,  100,    0,  101,    0,    0,
    0,    0,    0,    0,    0,   96,    0,    0,   97,    0,
    0,    0,   96,    0,    0,   97,    0,    0,   98,   99,
  110,  100,    0,  101,    0,   98,   99,    0,  100,    0,
  101,    0,    0,    0,  109,    0,    0,    0,   96,  107,
  105,   97,  106,  111,  108,    0,    0,    0,    0,    0,
    0,   98,   99,    0,  100,    0,  101,  104,  114,  103,
   73,    0,    0,    0,   96,   73,   73,   97,   73,   73,
   73,    0,    0,    0,    0,    0,    0,   98,   99,    0,
  100,   68,  101,   73,   73,   73,   68,   68,  110,   68,
   68,   68,   52,    0,   52,   52,   52,    0,    0,    0,
    0,    0,    0,    0,   68,   68,   68,    0,    0,    0,
   52,   52,   52,   52,   73,    0,    0,    0,   55,    0,
   55,   55,   55,    0,    0,    0,    0,   62,    0,    0,
   62,    0,    0,    0,    0,   68,   55,   55,   55,   55,
    0,    0,    0,    0,   52,   62,   62,   62,   62,    0,
    0,    0,    0,   96,    0,   61,   97,    0,   61,    0,
    0,    0,   59,    0,    0,   59,   98,   99,    0,  100,
   55,  101,    0,   61,   61,   61,   61,    0,    0,   62,
   59,   59,   59,   59,    0,    0,    0,    0,   96,    0,
   60,   97,    0,   60,    0,    0,    0,   58,    0,    0,
   58,   98,   99,    0,  100,    0,  101,   61,   60,   60,
   60,   60,    0,    0,   59,   58,    0,   58,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   60,    0,    0,    0,    0,    0,    0,   58,
    0,    0,    0,    0,    0,    0,   96,    0,    0,   97,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   98,
   99,    0,  100,    0,  101,    0,    0,    0,    0,    0,
    0,    0,   73,    0,    0,   73,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   73,   73,    0,   73,    0,
   73,    0,    0,   68,    0,    0,   68,    0,    0,    0,
   52,    0,    0,   52,    0,    0,   68,   68,    0,   68,
    0,   68,    0,   52,   52,    0,   52,    0,   52,    0,
    0,    0,    0,    0,    0,    0,   55,    0,    0,   55,
    0,    0,    0,    0,    0,   62,    0,    0,   62,   55,
   55,    0,   55,    0,   55,    0,    0,    0,   62,   62,
    0,   62,    0,   62,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   61,    0,    0,   61,    0,    0,    0,
   59,    0,    0,   59,    0,    0,   61,   61,    0,   61,
    0,   61,    0,   59,   59,    0,   59,    0,   59,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   60,    0,
    0,   60,    0,    0,    0,   58,    0,    0,   58,    0,
    0,   60,   60,   85,   60,   87,   60,   87,   58,   58,
   92,   93,   95,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   87,    0,    0,  120,    0,  122,    0,    0,
    0,    0,  125,  126,  127,  128,  129,  130,  131,  132,
  133,  134,  135,  136,  137,  138,  139,    0,    0,   87,
  142,    0,    0,    0,    0,  144,    0,    0,    0,  147,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   91,   46,   57,   52,  125,   44,   40,   56,   63,   13,
   14,   45,   41,   37,  264,   44,  123,   33,   42,   43,
   59,   45,   46,   47,   40,   69,    2,   71,  278,   45,
   59,   37,   61,  264,  264,   33,   42,   43,   42,   45,
   46,   47,   40,  145,  146,   41,   91,   45,   44,   25,
   91,   27,   28,   33,   60,  157,   62,   40,  257,   41,
   40,  163,   44,   59,   93,   45,   41,   91,   40,   44,
   41,   33,   48,   44,   40,   44,   41,   59,   40,   61,
  264,   93,  265,   45,   59,   91,  269,   44,   59,   33,
   59,  125,  264,  276,   41,   41,   40,  152,   44,  154,
   76,   45,   59,   44,  123,  160,   41,   33,  261,  125,
  165,   93,  265,   59,   40,   61,  269,  270,   93,   45,
   44,   37,  264,  276,   44,   33,   42,  125,  123,  265,
   46,   47,   40,  269,  270,   59,  123,   45,   44,   59,
  276,   44,   40,  123,  265,   33,   40,   93,  269,  270,
   40,   40,   40,   59,   41,  276,   59,   45,  264,   44,
   37,  123,   41,   41,   33,   42,   43,   59,   45,   46,
   47,   40,  267,  264,  267,   91,   45,   91,   91,   91,
   41,  125,   33,   60,   41,   62,   41,   41,   27,   40,
    2,   27,   27,   -1,   45,  113,   28,  123,   -1,   -1,
   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,
   -1,   -1,   45,   -1,   91,   -1,   -1,  125,   -1,   33,
   -1,   -1,   -1,  257,  258,   -1,   40,   -1,   -1,  263,
  264,   45,  266,  262,  268,  123,   -1,  271,   -1,   33,
  274,  257,  258,  272,   -1,  279,   40,  263,  264,   -1,
  266,   45,  268,  259,   -1,  271,  125,   -1,  274,  257,
  258,   -1,   -1,  279,   -1,  263,  264,  273,  266,  275,
  268,  277,   -1,  271,  125,   33,  274,  257,  258,   -1,
  262,  279,   40,  263,  264,   -1,  266,   45,  268,   -1,
  272,  271,  125,   33,  274,  257,  258,   -1,   -1,  279,
   40,  263,  264,   -1,  266,   45,  268,   -1,   -1,  271,
   -1,  125,  274,  257,  258,   -1,  262,  279,   -1,  263,
  264,   -1,  266,   -1,  268,   -1,  272,  271,   -1,   -1,
  274,  257,  258,   -1,   -1,  279,   -1,  263,  264,   -1,
  266,   -1,  268,   -1,   -1,  271,   -1,   -1,  274,  257,
  258,   -1,   -1,  279,   -1,  263,  264,   -1,  266,   -1,
  268,   -1,   -1,  271,   -1,   -1,  274,  125,   -1,  257,
  258,  279,   -1,   -1,   -1,  263,  264,   -1,  266,   -1,
  268,   -1,   -1,  271,   -1,   -1,  274,   -1,  257,  258,
   -1,  279,   -1,   -1,  263,  264,   -1,  266,  275,  268,
  277,   -1,  271,   -1,   -1,  274,  257,  258,   -1,   -1,
  279,   -1,  263,  264,   -1,  266,   -1,  268,   -1,   -1,
  271,   -1,   -1,  274,  257,  258,   -1,   -1,  279,   -1,
  263,  264,   -1,  266,   -1,  268,   -1,   -1,  271,   -1,
   -1,  274,   -1,  257,  258,   -1,  279,   -1,   -1,  263,
  264,   -1,  266,   -1,  268,   -1,   -1,  271,   -1,   -1,
  274,   -1,   -1,  257,  258,  279,   -1,   -1,   -1,   -1,
  264,  265,   -1,   -1,   -1,  269,   -1,   -1,   -1,   -1,
   -1,   -1,  276,   -1,   41,  279,   -1,   44,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,
  258,   -1,   59,   -1,   61,  263,  264,   -1,  266,   -1,
  268,   -1,   -1,  271,   -1,   -1,  274,  257,  258,   -1,
   -1,  279,   37,   -1,  264,   -1,   41,   42,   43,   44,
   45,   46,   47,   -1,   -1,   -1,   93,   -1,   -1,  279,
   -1,   -1,   -1,   -1,   59,   60,   61,   62,   37,   -1,
   -1,   -1,   41,   42,   43,   44,   45,   37,   47,   -1,
   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,   -1,
   59,   60,   61,   62,   -1,   -1,   91,   -1,   93,   59,
   60,   61,   62,   37,   -1,   -1,   -1,   41,   42,   43,
   44,   45,   37,   47,   -1,   -1,   41,   42,   43,   44,
   45,   -1,   47,   -1,   93,   59,   60,   61,   62,   -1,
   -1,   -1,   -1,   93,   59,   60,   61,   62,   37,   -1,
   -1,   -1,   41,   42,   43,   44,   45,   37,   47,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   93,
   59,   60,   61,   62,   -1,   -1,   -1,   -1,   93,   59,
   60,   61,   62,   37,   -1,   -1,   -1,   41,   42,   43,
   37,   45,   46,   47,   41,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   93,   -1,   60,   61,   62,   -1,
   -1,   91,   -1,   60,   61,   62,   37,   -1,   -1,   -1,
   41,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,   -1,  259,   -1,   -1,  262,   -1,   91,   -1,   60,
   61,   62,   37,   -1,   91,  272,  273,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   59,   60,   61,   62,   -1,   -1,
   91,   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,   -1,
  275,   -1,  277,   -1,   -1,   -1,   91,   -1,   -1,   -1,
  259,   -1,   -1,  262,   -1,   -1,   -1,   -1,   -1,  259,
   -1,   -1,  262,  272,  273,   -1,  275,   -1,  277,   -1,
   -1,   -1,  272,  273,   -1,  275,   -1,  277,   -1,   -1,
   -1,   37,   -1,   -1,   -1,  259,   42,   43,  262,   45,
   46,   47,   -1,   -1,  259,   -1,   -1,  262,  272,  273,
   -1,  275,   -1,  277,   60,   61,   62,  272,  273,   -1,
  275,   -1,  277,   -1,   -1,   -1,   37,   -1,   -1,   -1,
  259,   42,   43,  262,   45,   46,   47,   -1,   -1,  259,
   -1,   -1,  262,  272,  273,   91,  275,   93,  277,   60,
   61,   62,  272,  273,   -1,  275,   -1,  277,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,
   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,  272,  273,
   91,  275,   -1,  277,   -1,  272,  273,   -1,  275,   -1,
  277,   -1,   -1,   -1,   37,   -1,   -1,   -1,  259,   42,
   43,  262,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,
   -1,  272,  273,   -1,  275,   -1,  277,   60,   61,   62,
   37,   -1,   -1,   -1,  259,   42,   43,  262,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,   -1,
  275,   37,  277,   60,   61,   62,   42,   43,   91,   45,
   46,   47,   41,   -1,   43,   44,   45,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   60,   61,   62,   -1,   -1,   -1,
   59,   60,   61,   62,   91,   -1,   -1,   -1,   41,   -1,
   43,   44,   45,   -1,   -1,   -1,   -1,   41,   -1,   -1,
   44,   -1,   -1,   -1,   -1,   91,   59,   60,   61,   62,
   -1,   -1,   -1,   -1,   93,   59,   60,   61,   62,   -1,
   -1,   -1,   -1,  259,   -1,   41,  262,   -1,   44,   -1,
   -1,   -1,   41,   -1,   -1,   44,  272,  273,   -1,  275,
   93,  277,   -1,   59,   60,   61,   62,   -1,   -1,   93,
   59,   60,   61,   62,   -1,   -1,   -1,   -1,  259,   -1,
   41,  262,   -1,   44,   -1,   -1,   -1,   41,   -1,   -1,
   44,  272,  273,   -1,  275,   -1,  277,   93,   59,   60,
   61,   62,   -1,   -1,   93,   59,   -1,   61,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   93,
   -1,   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,
  273,   -1,  275,   -1,  277,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  272,  273,   -1,  275,   -1,
  277,   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,   -1,
  259,   -1,   -1,  262,   -1,   -1,  272,  273,   -1,  275,
   -1,  277,   -1,  272,  273,   -1,  275,   -1,  277,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,
   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,  272,
  273,   -1,  275,   -1,  277,   -1,   -1,   -1,  272,  273,
   -1,  275,   -1,  277,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,   -1,
  259,   -1,   -1,  262,   -1,   -1,  272,  273,   -1,  275,
   -1,  277,   -1,  272,  273,   -1,  275,   -1,  277,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,   -1,
   -1,  262,   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,
   -1,  272,  273,   67,  275,   69,  277,   71,  272,  273,
   74,   75,   76,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   86,   -1,   -1,   89,   -1,   91,   -1,   -1,
   -1,   -1,   96,   97,   98,   99,  100,  101,  102,  103,
  104,  105,  106,  107,  108,  109,  110,   -1,   -1,  113,
  114,   -1,   -1,   -1,   -1,  119,   -1,   -1,   -1,  123,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=284;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"INT_CONSTANT","REAL_CONSTANT",
"EQ","POW","VOID","OR","RETURN","ID","DOUBLE","WRITE","ELSE","IF","CHAR",
"STRUCT","READ","AND","NOT_EQ","WHILE","G_EQ","INT","L_EQ","MAIN",
"CHAR_CONSTANT","INC","DEC","LESSTHANELSE","CAST","UNARY_MINUS",
};
final static String yyrule[] = {
"$accept : program",
"program : opt_def_glob_var main",
"glob_def : var_def",
"glob_def : func_def",
"opt_def_glob_var : opt_def_glob_var glob_def",
"opt_def_glob_var :",
"var_def : p_type list_ident ';'",
"var_def : array list_ident ';'",
"var_def : struct_type list_ident ';'",
"local_var_def : var_def",
"opt_list_local_var : opt_list_local_var local_var_def",
"opt_list_local_var :",
"opt_list_fields : opt_list_fields type list_ident ';'",
"opt_list_fields :",
"struct_type : STRUCT '{' opt_list_fields '}'",
"main : VOID MAIN '(' ')' '{' opt_list_local_var statements '}'",
"func_def : p_type ID '(' opt_list_param ')' '{' opt_list_local_var statements '}'",
"func_def : VOID ID '(' opt_list_param ')' '{' opt_list_local_var statements '}'",
"list_param : list_param ',' p_type ID",
"list_param : p_type ID",
"opt_list_param : list_param",
"opt_list_param :",
"p_type : INT",
"p_type : DOUBLE",
"p_type : CHAR",
"array : type '[' INT_CONSTANT ']'",
"type : p_type",
"type : array",
"type : struct_type",
"list_ident : list_ident ',' ID",
"list_ident : ID",
"statements : statements statement",
"statements :",
"statement : RETURN exp ';'",
"statement : READ list_exp ';'",
"statement : WRITE list_exp ';'",
"statement : if_else",
"statement : while",
"statement : exp '=' exp ';'",
"statement : ID '(' opt_list_exp ')' ';'",
"while : WHILE '(' exp ')' '{' statements '}'",
"while : WHILE '(' exp ')' statement",
"if_else : IF '(' exp ')' '{' statements '}' ELSE '{' statements '}'",
"if_else : IF '(' exp ')' '{' statements '}' ELSE statement",
"if_else : IF '(' exp ')' statement ELSE '{' statements '}'",
"if_else : IF '(' exp ')' statement ELSE statement",
"if_else : IF '(' exp ')' '{' statements '}'",
"if_else : IF '(' exp ')' statement",
"opt_list_exp : list_exp",
"opt_list_exp :",
"list_exp : list_exp ',' exp",
"list_exp : exp",
"exp : exp '+' exp",
"exp : exp '*' exp",
"exp : exp '/' exp",
"exp : exp '-' exp",
"exp : exp '%' exp",
"exp : exp EQ exp",
"exp : exp NOT_EQ exp",
"exp : exp '>' exp",
"exp : exp '<' exp",
"exp : exp L_EQ exp",
"exp : exp G_EQ exp",
"exp : exp AND exp",
"exp : exp OR exp",
"exp : '(' p_type ')' exp",
"exp : exp '[' exp ']'",
"exp : exp '.' ID",
"exp : ID '(' opt_list_exp ')'",
"exp : '-' exp",
"exp : '(' exp ')'",
"exp : '!' exp",
"exp : INT_CONSTANT",
"exp : ID",
"exp : CHAR_CONSTANT",
"exp : REAL_CONSTANT",
"exp : exp '=' exp",
};

//#line 224 "../../src/parser/parser.y"

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

private void addVarDefs(List<Definition> defsList, Type type, List<String> idents, int line) {
	for(String id : idents) {
		defsList.add(new DefVar(line, scanner.getColumn(), type, id));
	}
}

private void addVarDefs(List<Definition> defsList, StructType type, List<String> idents, int line) {
	for(String id : idents) {
		defsList.add(new DefVar(line, scanner.getColumn(), new StructType(type.getLine(), type.getColumn(), type.getFieldsDefinitions(), id), id));
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


private void addFieldDefs(List<Definition> defsList, Type type, List<String> idents, int line) {
	for(String s : idents) {
		defsList.add(new DefField(line, scanner.getColumn(), type, s));
	}
}
private ASTNode root;

public ASTNode getRoot() {
	return this.root;
}

//#line 724 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 97 "../../src/parser/parser.y"
{ ((List<Definition>)val_peek(1)).add((Definition)val_peek(0)); this.root = new Program(scanner.getLine(), scanner.getColumn(), (List<Definition>)val_peek(1)); }
break;
case 2:
//#line 100 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 3:
//#line 101 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 4:
//#line 104 "../../src/parser/parser.y"
{ yyval = val_peek(1); mergeDefs((List<Definition>)yyval, val_peek(0)); }
break;
case 5:
//#line 105 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 6:
//#line 108 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); addVarDefs((List<Definition>)yyval, (Type)val_peek(2), (List<String>)val_peek(1), scanner.getLine()); }
break;
case 7:
//#line 109 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); addVarDefs((List<Definition>)yyval, (Type)val_peek(2), (List<String>)val_peek(1), scanner.getLine()); }
break;
case 8:
//#line 110 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); addVarDefs((List<Definition>)yyval, (StructType)val_peek(2), (List<String>)val_peek(1), scanner.getLine());}
break;
case 9:
//#line 114 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 10:
//#line 117 "../../src/parser/parser.y"
{ yyval = val_peek(1); mergeDefs((List<Definition>)yyval, val_peek(0)); }
break;
case 11:
//#line 118 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 12:
//#line 121 "../../src/parser/parser.y"
{ yyval = val_peek(3); addFieldDefs((List<Definition>)yyval, (Type)val_peek(2), (List<String>)val_peek(1), scanner.getLine()); }
break;
case 13:
//#line 122 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 14:
//#line 126 "../../src/parser/parser.y"
{ yyval = new StructType(scanner.getLine(), scanner.getColumn(), (List<Definition>)val_peek(1));}
break;
case 15:
//#line 129 "../../src/parser/parser.y"
{ yyval = new DefFunc(scanner.getLine(), scanner.getColumn(),(Type) new FuncType(scanner.getLine(), scanner.getColumn(), (Type)VoidType.getInstance(), (List<Definition>) new ArrayList()), "main", (List<Definition>)val_peek(2), (List<Statement>)val_peek(1)); }
break;
case 16:
//#line 132 "../../src/parser/parser.y"
{ yyval = new DefFunc(scanner.getLine(), scanner.getColumn(), (Type)new FuncType(scanner.getLine(), scanner.getColumn(), (Type)val_peek(8), (List<Definition>)val_peek(5)), (String)val_peek(7), (List<Definition>)val_peek(2), (List<Statement>)val_peek(1)); }
break;
case 17:
//#line 133 "../../src/parser/parser.y"
{ yyval = new DefFunc(scanner.getLine(), scanner.getColumn(),(Type) new FuncType(scanner.getLine(), scanner.getColumn(), (Type)VoidType.getInstance(), (List<Definition>)val_peek(5)), (String)val_peek(7), (List<Definition>)val_peek(2), (List<Statement>)val_peek(1)); }
break;
case 18:
//#line 138 "../../src/parser/parser.y"
{ ((List<Definition>)yyval).add(new DefVar(scanner.getLine(), scanner.getColumn(), (Type)val_peek(1), (String)val_peek(0))); yyval = val_peek(3); }
break;
case 19:
//#line 139 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>();((List<Definition>)yyval).add(new DefVar(scanner.getLine(), scanner.getColumn(), (Type)val_peek(1), (String)val_peek(0))); }
break;
case 20:
//#line 142 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 21:
//#line 143 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 22:
//#line 146 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 23:
//#line 147 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 24:
//#line 148 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 25:
//#line 151 "../../src/parser/parser.y"
{ yyval = getArrayDef((Type)val_peek(3), (Integer)val_peek(1), scanner.getLine()); }
break;
case 26:
//#line 154 "../../src/parser/parser.y"
{ yyval =val_peek(0);}
break;
case 27:
//#line 155 "../../src/parser/parser.y"
{ yyval =val_peek(0);}
break;
case 28:
//#line 156 "../../src/parser/parser.y"
{ yyval =val_peek(0);}
break;
case 29:
//#line 159 "../../src/parser/parser.y"
{ ((List<String>)yyval).add((String)val_peek(0)); yyval = val_peek(2);  }
break;
case 30:
//#line 160 "../../src/parser/parser.y"
{ yyval = new ArrayList<String>(); ((List<String>)yyval).add((String)val_peek(0)); }
break;
case 31:
//#line 163 "../../src/parser/parser.y"
{ ((List<Statement>)yyval).add((Statement)val_peek(0)); yyval = val_peek(1); }
break;
case 32:
//#line 164 "../../src/parser/parser.y"
{ yyval = new ArrayList<Statement>(); }
break;
case 33:
//#line 167 "../../src/parser/parser.y"
{ yyval = new Return(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(1)); }
break;
case 34:
//#line 168 "../../src/parser/parser.y"
{ yyval = new Read(scanner.getLine(), scanner.getColumn(), (List<Expression>)val_peek(1)); }
break;
case 35:
//#line 169 "../../src/parser/parser.y"
{ yyval = new Write(scanner.getLine(), scanner.getColumn(), (List<Expression>)val_peek(1)); }
break;
case 36:
//#line 170 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 37:
//#line 171 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 38:
//#line 172 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1)); }
break;
case 39:
//#line 173 "../../src/parser/parser.y"
{ yyval = new InvocationSt(scanner.getLine(), scanner.getColumn(), (String)val_peek(4), (List<Expression>)val_peek(2)); }
break;
case 40:
//#line 176 "../../src/parser/parser.y"
{ yyval = new While(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (List<Statement>)val_peek(1)); }
break;
case 41:
//#line 177 "../../src/parser/parser.y"
{ yyval = new While(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Statement)val_peek(0)); }
break;
case 42:
//#line 180 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(8), (List<Statement>)val_peek(5), (List<Statement>)val_peek(1)); }
break;
case 43:
//#line 181 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(6), (List<Statement>)val_peek(3), (Statement)val_peek(0)); }
break;
case 44:
//#line 182 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(6), (Statement)val_peek(4), (List<Statement>)val_peek(1)); }
break;
case 45:
//#line 183 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (Statement)val_peek(2), (Statement)val_peek(0)); }
break;
case 46:
//#line 184 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (List<Statement>)val_peek(1), new ArrayList()); }
break;
case 47:
//#line 185 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Statement)val_peek(0), new ArrayList()); }
break;
case 48:
//#line 188 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 49:
//#line 189 "../../src/parser/parser.y"
{ yyval = new ArrayList<Expression>(); }
break;
case 50:
//#line 192 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List<Expression>)yyval).add((Expression)val_peek(0)); }
break;
case 51:
//#line 193 "../../src/parser/parser.y"
{ yyval = new ArrayList<Expression>(); ((List<Expression>)yyval).add((Expression)val_peek(0)); }
break;
case 52:
//#line 196 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "+"); }
break;
case 53:
//#line 197 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "*"); }
break;
case 54:
//#line 198 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "/"); }
break;
case 55:
//#line 199 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "-"); }
break;
case 56:
//#line 200 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "%"); }
break;
case 57:
//#line 201 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "=="); }
break;
case 58:
//#line 202 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "!="); }
break;
case 59:
//#line 203 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), ">"); }
break;
case 60:
//#line 204 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "<"); }
break;
case 61:
//#line 205 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "<="); }
break;
case 62:
//#line 206 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), ">="); }
break;
case 63:
//#line 207 "../../src/parser/parser.y"
{ yyval = new LogicOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "&&"); }
break;
case 64:
//#line 208 "../../src/parser/parser.y"
{ yyval = new LogicOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "||"); }
break;
case 65:
//#line 209 "../../src/parser/parser.y"
{ yyval = new Cast(scanner.getLine(), scanner.getColumn(), (Type)val_peek(2), (Expression)val_peek(0)); }
break;
case 66:
//#line 210 "../../src/parser/parser.y"
{ yyval = new ArrayAccess(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1)); }
break;
case 67:
//#line 211 "../../src/parser/parser.y"
{ yyval = new StructAccess(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(0)); }
break;
case 68:
//#line 212 "../../src/parser/parser.y"
{ yyval = new InvocationExp(scanner.getLine(), scanner.getColumn(), (String)val_peek(3), (List<Expression>)val_peek(1)); }
break;
case 69:
//#line 213 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 70:
//#line 214 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 71:
//#line 215 "../../src/parser/parser.y"
{ yyval = new UnaryNot(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 72:
//#line 216 "../../src/parser/parser.y"
{ yyval = new LiteralInt(scanner.getLine(), scanner.getColumn(), (Integer)val_peek(0)); }
break;
case 73:
//#line 217 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 74:
//#line 218 "../../src/parser/parser.y"
{ yyval = new LiteralChar(scanner.getLine(), scanner.getColumn(), (Character)val_peek(0)); }
break;
case 75:
//#line 219 "../../src/parser/parser.y"
{ yyval = new LiteralReal(scanner.getLine(), scanner.getColumn(), (Double)val_peek(0)); }
break;
case 76:
//#line 220 "../../src/parser/parser.y"
{ yyval = new AssignmentExpr(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0)); }
break;
//#line 1177 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
