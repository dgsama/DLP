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
//#line 61 "Parser.java"




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
public final static short UNARY_MINUS=283;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    3,    3,    1,    1,    4,    4,    4,   10,
   10,   11,   11,   12,   12,    5,    2,    6,    6,   16,
   16,   15,   15,    7,    7,    7,    9,   13,   13,   13,
    8,    8,   14,   14,   17,   17,   17,   17,   17,   17,
   17,   21,   21,   20,   20,   20,   20,   20,   20,   22,
   22,   19,   19,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,
};
final static short yylen[] = {                            2,
    2,    1,    1,    1,    2,    0,    3,    3,    2,    1,
    1,    2,    0,    4,    0,    6,    8,    9,    9,    4,
    2,    1,    0,    1,    1,    1,    4,    1,    1,    1,
    3,    1,    2,    0,    3,    3,    3,    1,    1,    4,
    5,    7,    5,   11,    9,    9,    7,    7,    5,    1,
    0,    3,    1,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    4,    4,    3,    4,
    2,    3,    2,    1,    1,    1,    1,
};
final static short yydefred[] = {                         6,
    0,    0,    0,   25,   26,    0,   24,    1,    5,    2,
    0,    4,    0,    0,    0,    0,    0,   15,    9,    0,
    0,   32,    0,    0,    0,    0,    0,    0,    7,    0,
    8,    0,    0,    0,    0,    0,    0,   30,   28,   29,
    0,    0,   31,   27,   21,    0,    0,   13,    0,    0,
    0,   13,    0,    0,   16,   14,   13,    0,   20,   10,
    0,    0,   12,    0,    0,    0,   74,   77,    0,    0,
    0,    0,    0,    0,   76,    0,    0,    0,   17,   33,
    0,   38,   39,    0,   19,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   18,    0,   35,    0,    0,   37,    0,
    0,   36,    0,    0,   72,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   69,    0,    0,    0,    0,    0,   67,   40,   68,   70,
   41,   34,    0,   34,   43,    0,    0,    0,    0,   34,
   47,   42,    0,    0,   34,   45,   46,    0,   44,
};
final static short yydgoto[] = {                          1,
    2,    8,    9,   60,   61,   12,   62,   21,   14,   63,
   54,   27,   15,   64,   34,   35,   80,   81,  117,   82,
   83,  118,
};
final static short yysindex[] = {                         0,
    0, -163, -223,    0,    0, -109,    0,    0,    0,    0,
  -35,    0, -227, -212,  -32,   26,   38,    0,    0,   40,
  -34,    0,  -10, -185, -182,   49, -120, -182,    0, -143,
    0,   29, -140,   86,   84,    8, -212,    0,    0,    0,
  -87,   92,    0,    0,    0,   11, -182,    0,   45,   55,
   12,    0, -127, -150,    0,    0,    0, -150,    0,    0,
  -35, -212,    0,  -13, -150,    5,    0,    0,  250,   98,
  250,   99,  250,  100,    0,  250,  250,  227,    0,    0,
  600,    0,    0,   23,    0,  102,  626,  250,  818,   57,
  250,   66,  250,  903,  -37,  103,  633,  250,  250,  250,
  250,  250,  250,  250,  250,  250,  250,  250,  250,  250,
  250,  250, -121,    0,  250,    0,  107,  105,    0,  250,
  659,    0,  666,  250,    0,  942,  903,  903,  942,  -24,
  -24,  724,  -24,  -24,   -7,   -7,  -37,  -37,  -37,  698,
    0,  111,   94,  818,   41,   60,    0,    0,    0,    0,
    0,    0, -112,    0,    0,   78,   96,  114, -110,    0,
    0,    0,  133,  152,    0,    0,    0,  171,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -89,    0,   67,   69,    0,    0,    0,    0,    0,   73,
    0,    0,    0,    0,  120,    0,    0,  120,    0,    0,
    0,    0,    0,    0,  121,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  189,    0,    0,    0,  189,    0,    0,
  -33,   67,    0,    0,  189,    0,    0,    0,    0,  757,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  474,    0,   92,    2,    0,
    0,    0,    0,   -8,  500,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   92,    0,  122,    0,    0,    0,
    0,    0,    0,    0,    0,  130,    3,   16,  149,  976,
 1003,    0, 1044, 1053,  809,  964,  539,  565,  574,    0,
    0,    0,  783,   32,    0,    0,    0,    0,    0,    0,
    0,    0,  207,    0,    0,    0,    0,    0,  300,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  163,    4,    0,    1,   28,  140,    0,
  -41,    0,  141,  -57,  142,    0,  -75, 1094,  -56,    0,
    0,   54,
};
final static int YYTABLESIZE=1330;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         11,
   66,   30,   13,   24,   37,   11,   11,   84,  113,   30,
   58,   11,  111,   18,   90,   65,   92,  109,  107,   76,
  108,  113,  110,   19,   29,   33,   78,   39,   33,  111,
   38,   77,   73,   30,  109,   73,   20,   76,  113,  110,
   16,   23,   53,   66,   78,   53,   66,   53,   31,   77,
   73,   22,   73,  112,   17,   76,   65,   30,   24,   65,
   53,   66,   78,   66,   49,   25,  112,   77,   50,  153,
  155,   32,   52,   76,   65,   52,   65,   26,   96,   28,
   78,  161,    4,  112,   73,   77,    5,  166,   30,   36,
   52,   11,   76,    7,  156,   66,  158,    3,   30,   78,
  120,    4,  164,   55,   77,    5,    6,  168,   65,  120,
   76,   79,    7,   56,    4,  119,   32,   78,    5,    6,
   43,   44,   77,   45,  122,    7,   46,   47,   76,   85,
   48,   32,   51,   52,   57,   78,   59,   88,   91,   93,
   77,  115,  141,  124,    4,  143,   76,  114,    5,    6,
  120,  150,  151,   78,  157,    7,  163,   28,   77,   29,
   23,   22,   50,  152,   10,   76,   40,   41,  142,   42,
   59,    3,   78,   59,    0,    3,   22,   77,    0,    3,
    3,    0,  154,    0,   76,    0,    3,    0,   59,   60,
   59,   78,   60,    0,    0,    0,   77,    0,    0,    0,
    0,    0,  159,   76,    0,    0,    0,   60,    0,   60,
   78,    0,    0,    0,    0,   77,    0,    0,  160,    0,
    0,   34,   59,   11,   11,    0,    0,    0,   34,   11,
   11,   11,   11,   34,   11,   11,   11,   11,  162,   49,
   11,   60,   11,   67,   68,   11,   49,    0,    0,   69,
   70,   49,   71,   73,   72,  165,    0,   73,    0,   76,
   74,   67,   68,   73,   66,   75,   78,   69,   70,    0,
   71,   77,   72,    0,   66,   73,  167,   65,   74,   67,
   68,    0,   76,   75,    0,   69,   70,   65,   71,   78,
   72,    0,    0,   73,   77,  169,   74,   67,   68,    0,
    0,   75,    0,   69,   70,    0,   71,    0,   72,    0,
    0,   73,    0,   34,   74,    0,   67,   68,    0,   75,
    0,    0,   69,   70,    0,   71,    0,   72,    0,    0,
   73,   49,   48,   74,   67,   68,    0,    0,   75,   48,
   69,   70,    0,   71,   48,   72,    0,    0,   73,    0,
    0,   74,   67,   68,    0,    0,   75,    0,   69,   70,
    0,   71,    0,   72,    0,    0,   73,    0,    0,   74,
   67,   68,    0,    0,   75,    0,   69,   70,    0,   71,
    0,   72,    0,    0,   73,    0,    0,   74,   59,   67,
   68,   59,   75,    0,    0,   69,   70,    0,   71,    0,
   72,   59,   59,   73,    0,    0,   74,   60,   67,   68,
   60,   75,    0,    0,   69,   70,    0,   71,    0,   72,
   60,   60,   73,    0,   48,   74,    0,   67,   68,    0,
   75,    0,    0,   69,   70,    0,   71,    0,   72,    0,
    0,   73,    0,    0,   74,   34,   34,    0,    0,   75,
    0,   34,   34,    0,   34,    0,   34,    0,    0,   34,
    0,    0,   34,   49,   49,    0,    0,   34,    0,   49,
   49,    0,   49,    0,   49,    0,    0,   49,    0,    0,
   49,    0,    0,   67,   68,   49,    0,    0,    0,    0,
   86,    4,    0,    0,    0,    5,    0,    0,    0,    0,
    0,    0,    7,    0,    0,   75,   67,   68,    0,    0,
   75,    0,    0,   86,   75,   75,   75,   75,   75,   75,
   75,    0,    0,    0,    0,    0,    0,    0,   75,    0,
    0,    0,   75,   75,   75,   75,   71,    0,    0,    0,
   71,   71,   71,   71,   71,    0,   71,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   48,   48,   71,   71,
   71,   71,   48,   48,   75,   48,   75,   48,    0,    0,
   48,    0,    0,   48,    0,   55,    0,    0,   48,   55,
   55,   55,   55,   55,    0,   55,    0,    0,    0,    0,
    0,    0,   71,    0,    0,    0,    0,   55,   55,   55,
   55,   56,    0,    0,    0,   56,   56,   56,   56,   56,
   58,   56,    0,    0,   58,   58,   58,   58,   58,    0,
   58,    0,    0,   56,   56,   56,   56,    0,    0,    0,
    0,   55,   58,   58,   58,   58,  111,    0,    0,    0,
    0,  109,  107,    0,  108,  113,  110,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   56,    0,  106,
  104,  105,  111,    0,    0,    0,   58,  109,  107,  111,
  108,  113,  110,  125,  109,  107,    0,  108,  113,  110,
    0,    0,    0,    0,  116,  106,    0,  105,    0,    0,
  112,    0,  106,    0,  105,  111,    0,    0,    0,  145,
  109,  107,  111,  108,  113,  110,  146,  109,  107,    0,
  108,  113,  110,    0,    0,    0,  112,    0,  106,    0,
  105,    0,    0,  112,    0,  106,    0,  105,    0,    0,
    0,    0,   75,    0,  111,   75,    0,    0,    0,  109,
  107,    0,  108,  113,  110,   75,   75,    0,   75,  112,
   75,    0,    0,    0,    0,    0,  112,  106,   71,  105,
  111,   71,    0,    0,    0,  109,  107,    0,  108,  113,
  110,   71,   71,    0,   71,    0,   71,    0,    0,    0,
    0,    0,  148,  106,    0,  105,    0,    0,  112,    0,
  149,    0,    0,   75,    0,    0,    0,   55,   75,   75,
   55,   75,   75,   75,    0,    0,    0,    0,    0,    0,
   55,   55,    0,   55,  112,   55,   75,   75,   75,   70,
    0,    0,    0,   56,   70,   70,   56,   70,   70,   70,
    0,    0,   58,    0,    0,   58,   56,   56,    0,   56,
    0,   56,   70,   70,   70,   58,   58,   75,   58,   54,
   58,   54,   54,   54,  111,    0,    0,    0,   98,  109,
  107,   99,  108,  113,  110,    0,    0,   54,   54,   54,
   54,  100,  101,   70,  102,    0,  103,  106,    0,  105,
    0,    0,    0,    0,   98,    0,    0,   99,    0,    0,
    0,   98,    0,    0,   99,    0,    0,  100,  101,    0,
  102,   54,  103,    0,  100,  101,    0,  102,  112,  103,
    0,    0,    0,    0,    0,    0,    0,   98,    0,    0,
   99,    0,    0,    0,   98,    0,    0,   99,    0,    0,
  100,  101,    0,  102,    0,  103,    0,  100,  101,  111,
  102,    0,  103,    0,  109,  107,    0,  108,  113,  110,
    0,    0,    0,    0,    0,    0,   98,    0,    0,   99,
    0,    0,  106,    0,  105,    0,    0,    0,    0,  100,
  101,    0,  102,    0,  103,    0,    0,    0,  111,    0,
    0,    0,   98,  109,  107,   99,  108,  113,  110,    0,
    0,    0,    0,  112,    0,  100,  101,    0,  102,    0,
  103,  106,    0,  105,   57,    0,   57,   57,   57,    0,
    0,    0,    0,    0,    0,   75,   64,    0,   75,   64,
    0,    0,   57,   57,   57,   57,    0,    0,   75,   75,
    0,   75,  112,   75,   64,   64,   64,   64,    0,    0,
    0,   70,    0,   63,   70,    0,   63,    0,    0,    0,
    0,    0,    0,    0,   70,   70,   57,   70,    0,   70,
    0,   63,   63,   63,   63,    0,    0,   54,   64,    0,
   54,    0,    0,    0,    0,    0,   98,    0,    0,   99,
   54,   54,    0,   54,   61,   54,    0,   61,    0,  100,
  101,    0,  102,   62,  103,   63,   62,    0,    0,    0,
    0,    0,   61,   61,   61,   61,    0,    0,    0,    0,
    0,   62,   62,   62,   62,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   61,    0,    0,    0,
    0,    0,    0,    0,    0,   62,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   98,   87,    0,   89,    0,   89,    0,    0,   94,
   95,   97,    0,    0,    0,  101,    0,  102,    0,  103,
    0,   89,    0,    0,  121,    0,  123,    0,    0,    0,
    0,  126,  127,  128,  129,  130,  131,  132,  133,  134,
  135,  136,  137,  138,  139,  140,    0,    0,   89,    0,
    0,    0,    0,  144,    0,    0,  102,  147,  103,    0,
    0,    0,   57,    0,    0,   57,    0,    0,    0,    0,
    0,    0,    0,    0,   64,   57,   57,   64,   57,    0,
   57,    0,    0,    0,    0,    0,    0,   64,   64,    0,
   64,    0,   64,    0,    0,    0,    0,    0,    0,    0,
    0,   63,    0,    0,   63,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   63,   63,    0,   63,    0,   63,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   61,    0,    0,   61,    0,    0,    0,    0,
    0,   62,    0,    0,   62,   61,   61,    0,   61,    0,
   61,    0,    0,    0,   62,   62,    0,   62,    0,   62,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   58,   91,    2,   91,  125,    2,   40,   65,   46,   44,
   52,   45,   37,  123,   71,   57,   73,   42,   43,   33,
   45,   46,   47,   59,   59,   25,   40,   27,   28,   37,
   27,   45,   41,   44,   42,   44,  264,   33,   46,   47,
  264,   14,   41,   41,   40,   44,   44,   47,   59,   45,
   59,  264,   61,   91,  278,   33,   41,   91,   91,   44,
   59,   59,   40,   61,   37,   40,   91,   45,   41,  145,
  146,  257,   41,   33,   59,   44,   61,   40,   78,   40,
   40,  157,  265,   91,   93,   45,  269,  163,   44,   41,
   59,  125,   33,  276,  152,   93,  154,  261,   44,   40,
   44,  265,  160,   59,   45,  269,  270,  165,   93,   44,
   33,  125,  276,   59,  265,   59,   44,   40,  269,  270,
  264,   93,   45,  264,   59,  276,   41,   44,   33,  125,
  123,   59,   41,  123,  123,   40,  264,   40,   40,   40,
   45,   40,  264,   41,  265,   41,   33,  125,  269,  270,
   44,   41,   59,   40,  267,  276,  267,   91,   45,   91,
   41,   41,   41,  123,    2,   33,   27,   27,  115,   28,
   41,  261,   40,   44,   -1,  265,  264,   45,   -1,  269,
  270,   -1,  123,   -1,   33,   -1,  276,   -1,   59,   41,
   61,   40,   44,   -1,   -1,   -1,   45,   -1,   -1,   -1,
   -1,   -1,  125,   33,   -1,   -1,   -1,   59,   -1,   61,
   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,  123,   -1,
   -1,   33,   93,  257,  258,   -1,   -1,   -1,   40,  263,
  264,  265,  266,   45,  268,  269,  270,  271,  125,   33,
  274,   93,  276,  257,  258,  279,   40,   -1,   -1,  263,
  264,   45,  266,  262,  268,  123,   -1,  271,   -1,   33,
  274,  257,  258,  272,  262,  279,   40,  263,  264,   -1,
  266,   45,  268,   -1,  272,  271,  125,  262,  274,  257,
  258,   -1,   33,  279,   -1,  263,  264,  272,  266,   40,
  268,   -1,   -1,  271,   45,  125,  274,  257,  258,   -1,
   -1,  279,   -1,  263,  264,   -1,  266,   -1,  268,   -1,
   -1,  271,   -1,  125,  274,   -1,  257,  258,   -1,  279,
   -1,   -1,  263,  264,   -1,  266,   -1,  268,   -1,   -1,
  271,  125,   33,  274,  257,  258,   -1,   -1,  279,   40,
  263,  264,   -1,  266,   45,  268,   -1,   -1,  271,   -1,
   -1,  274,  257,  258,   -1,   -1,  279,   -1,  263,  264,
   -1,  266,   -1,  268,   -1,   -1,  271,   -1,   -1,  274,
  257,  258,   -1,   -1,  279,   -1,  263,  264,   -1,  266,
   -1,  268,   -1,   -1,  271,   -1,   -1,  274,  259,  257,
  258,  262,  279,   -1,   -1,  263,  264,   -1,  266,   -1,
  268,  272,  273,  271,   -1,   -1,  274,  259,  257,  258,
  262,  279,   -1,   -1,  263,  264,   -1,  266,   -1,  268,
  272,  273,  271,   -1,  125,  274,   -1,  257,  258,   -1,
  279,   -1,   -1,  263,  264,   -1,  266,   -1,  268,   -1,
   -1,  271,   -1,   -1,  274,  257,  258,   -1,   -1,  279,
   -1,  263,  264,   -1,  266,   -1,  268,   -1,   -1,  271,
   -1,   -1,  274,  257,  258,   -1,   -1,  279,   -1,  263,
  264,   -1,  266,   -1,  268,   -1,   -1,  271,   -1,   -1,
  274,   -1,   -1,  257,  258,  279,   -1,   -1,   -1,   -1,
  264,  265,   -1,   -1,   -1,  269,   -1,   -1,   -1,   -1,
   -1,   -1,  276,   -1,   -1,  279,  257,  258,   -1,   -1,
   37,   -1,   -1,  264,   41,   42,   43,   44,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  279,   -1,
   -1,   -1,   59,   60,   61,   62,   37,   -1,   -1,   -1,
   41,   42,   43,   44,   45,   -1,   47,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,   59,   60,
   61,   62,  263,  264,   91,  266,   93,  268,   -1,   -1,
  271,   -1,   -1,  274,   -1,   37,   -1,   -1,  279,   41,
   42,   43,   44,   45,   -1,   47,   -1,   -1,   -1,   -1,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   59,   60,   61,
   62,   37,   -1,   -1,   -1,   41,   42,   43,   44,   45,
   37,   47,   -1,   -1,   41,   42,   43,   44,   45,   -1,
   47,   -1,   -1,   59,   60,   61,   62,   -1,   -1,   -1,
   -1,   93,   59,   60,   61,   62,   37,   -1,   -1,   -1,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,   60,
   61,   62,   37,   -1,   -1,   -1,   93,   42,   43,   37,
   45,   46,   47,   41,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,   59,   60,   -1,   62,   -1,   -1,
   91,   -1,   60,   -1,   62,   37,   -1,   -1,   -1,   41,
   42,   43,   37,   45,   46,   47,   41,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   91,   -1,   60,   -1,
   62,   -1,   -1,   91,   -1,   60,   -1,   62,   -1,   -1,
   -1,   -1,  259,   -1,   37,  262,   -1,   -1,   -1,   42,
   43,   -1,   45,   46,   47,  272,  273,   -1,  275,   91,
  277,   -1,   -1,   -1,   -1,   -1,   91,   60,  259,   62,
   37,  262,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,  272,  273,   -1,  275,   -1,  277,   -1,   -1,   -1,
   -1,   -1,   59,   60,   -1,   62,   -1,   -1,   91,   -1,
   93,   -1,   -1,   37,   -1,   -1,   -1,  259,   42,   43,
  262,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,
  272,  273,   -1,  275,   91,  277,   60,   61,   62,   37,
   -1,   -1,   -1,  259,   42,   43,  262,   45,   46,   47,
   -1,   -1,  259,   -1,   -1,  262,  272,  273,   -1,  275,
   -1,  277,   60,   61,   62,  272,  273,   91,  275,   41,
  277,   43,   44,   45,   37,   -1,   -1,   -1,  259,   42,
   43,  262,   45,   46,   47,   -1,   -1,   59,   60,   61,
   62,  272,  273,   91,  275,   -1,  277,   60,   -1,   62,
   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,
   -1,  259,   -1,   -1,  262,   -1,   -1,  272,  273,   -1,
  275,   93,  277,   -1,  272,  273,   -1,  275,   91,  277,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,
  262,   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,
  272,  273,   -1,  275,   -1,  277,   -1,  272,  273,   37,
  275,   -1,  277,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,
   -1,   -1,   60,   -1,   62,   -1,   -1,   -1,   -1,  272,
  273,   -1,  275,   -1,  277,   -1,   -1,   -1,   37,   -1,
   -1,   -1,  259,   42,   43,  262,   45,   46,   47,   -1,
   -1,   -1,   -1,   91,   -1,  272,  273,   -1,  275,   -1,
  277,   60,   -1,   62,   41,   -1,   43,   44,   45,   -1,
   -1,   -1,   -1,   -1,   -1,  259,   41,   -1,  262,   44,
   -1,   -1,   59,   60,   61,   62,   -1,   -1,  272,  273,
   -1,  275,   91,  277,   59,   60,   61,   62,   -1,   -1,
   -1,  259,   -1,   41,  262,   -1,   44,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  272,  273,   93,  275,   -1,  277,
   -1,   59,   60,   61,   62,   -1,   -1,  259,   93,   -1,
  262,   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,
  272,  273,   -1,  275,   41,  277,   -1,   44,   -1,  272,
  273,   -1,  275,   41,  277,   93,   44,   -1,   -1,   -1,
   -1,   -1,   59,   60,   61,   62,   -1,   -1,   -1,   -1,
   -1,   59,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  259,   69,   -1,   71,   -1,   73,   -1,   -1,   76,
   77,   78,   -1,   -1,   -1,  273,   -1,  275,   -1,  277,
   -1,   88,   -1,   -1,   91,   -1,   93,   -1,   -1,   -1,
   -1,   98,   99,  100,  101,  102,  103,  104,  105,  106,
  107,  108,  109,  110,  111,  112,   -1,   -1,  115,   -1,
   -1,   -1,   -1,  120,   -1,   -1,  275,  124,  277,   -1,
   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  259,  272,  273,  262,  275,   -1,
  277,   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,   -1,
  275,   -1,  277,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  259,   -1,   -1,  262,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  272,  273,   -1,  275,   -1,  277,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,   -1,   -1,
   -1,  259,   -1,   -1,  262,  272,  273,   -1,  275,   -1,
  277,   -1,   -1,   -1,  272,  273,   -1,  275,   -1,  277,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=283;
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
"CHAR_CONSTANT","INC","DEC","LESSTHANELSE","UNARY_MINUS",
};
final static String yyrule[] = {
"$accept : program",
"program : opt_def_glob_var main",
"glob_def : var_def",
"glob_def : struct_def",
"glob_def : func_def",
"opt_def_glob_var : opt_def_glob_var glob_def",
"opt_def_glob_var :",
"var_def : p_type list_ident ';'",
"var_def : array list_ident ';'",
"var_def : struct_def ';'",
"local_var_def : var_def",
"local_var_def : struct_def",
"opt_list_local_var : opt_list_local_var local_var_def",
"opt_list_local_var :",
"opt_list_fields : opt_list_fields type list_ident ';'",
"opt_list_fields :",
"struct_def : STRUCT '{' opt_list_fields '}' list_ident ';'",
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
"type : struct_def",
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
};

//#line 225 "../../src/parser/parser.y"

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
		defsList.add(new DefVar(line, scanner.getColumn(), (Type)new StructType(scanner.getLine(), scanner.getColumn(), fields, id), id));
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

//#line 719 "Parser.java"
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
//#line 102 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 5:
//#line 105 "../../src/parser/parser.y"
{ yyval = val_peek(1); mergeDefs((List<Definition>)yyval, val_peek(0)); }
break;
case 6:
//#line 106 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 7:
//#line 109 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); addVarDefs((List<Definition>)yyval, (Type)val_peek(2), (List<String>)val_peek(1), scanner.getLine()); }
break;
case 8:
//#line 110 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); addVarDefs((List<Definition>)yyval, (Type)val_peek(2), (List<String>)val_peek(1), scanner.getLine()); }
break;
case 9:
//#line 111 "../../src/parser/parser.y"
{ yyval = val_peek(1);}
break;
case 10:
//#line 115 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 11:
//#line 116 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 12:
//#line 119 "../../src/parser/parser.y"
{ yyval = val_peek(1); mergeDefs((List<Definition>)yyval, val_peek(0)); }
break;
case 13:
//#line 120 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 14:
//#line 123 "../../src/parser/parser.y"
{ yyval = val_peek(3); addFieldDefs((List<Definition>)yyval, (Type)val_peek(2), (List<String>)val_peek(1), scanner.getLine()); }
break;
case 15:
//#line 124 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 16:
//#line 128 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); addStructDefs((List<Definition>)yyval, (List<Definition>)val_peek(3), (List<String>)val_peek(1), scanner.getLine()); }
break;
case 17:
//#line 131 "../../src/parser/parser.y"
{ yyval = new DefFunc(scanner.getLine(), scanner.getColumn(),(Type) new FuncType(scanner.getLine(), scanner.getColumn(), (Type)VoidType.getInstance(), (List<Definition>) new ArrayList()), "main", (List<Definition>)val_peek(2), (List<Statement>)val_peek(1)); }
break;
case 18:
//#line 134 "../../src/parser/parser.y"
{ yyval = new DefFunc(scanner.getLine(), scanner.getColumn(), (Type)new FuncType(scanner.getLine(), scanner.getColumn(), (Type)val_peek(8), (List<Definition>)val_peek(5)), (String)val_peek(7), (List<Definition>)val_peek(2), (List<Statement>)val_peek(1)); }
break;
case 19:
//#line 135 "../../src/parser/parser.y"
{ yyval = new DefFunc(scanner.getLine(), scanner.getColumn(),(Type) new FuncType(scanner.getLine(), scanner.getColumn(), (Type)VoidType.getInstance(), (List<Definition>)val_peek(5)), (String)val_peek(7), (List<Definition>)val_peek(2), (List<Statement>)val_peek(1)); }
break;
case 20:
//#line 140 "../../src/parser/parser.y"
{ ((List<Definition>)yyval).add(new DefVar(scanner.getLine(), scanner.getColumn(), (Type)val_peek(1), (String)val_peek(0))); yyval = val_peek(3); }
break;
case 21:
//#line 141 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>();((List<Definition>)yyval).add(new DefVar(scanner.getLine(), scanner.getColumn(), (Type)val_peek(1), (String)val_peek(0))); }
break;
case 22:
//#line 144 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 23:
//#line 145 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 24:
//#line 148 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 25:
//#line 149 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 26:
//#line 150 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 27:
//#line 153 "../../src/parser/parser.y"
{ yyval = getArrayDef((Type)val_peek(3), (Integer)val_peek(1), scanner.getLine()); }
break;
case 28:
//#line 156 "../../src/parser/parser.y"
{ yyval =val_peek(0);}
break;
case 29:
//#line 157 "../../src/parser/parser.y"
{ yyval =val_peek(0);}
break;
case 30:
//#line 158 "../../src/parser/parser.y"
{ yyval =val_peek(0);}
break;
case 31:
//#line 161 "../../src/parser/parser.y"
{ ((List<String>)yyval).add((String)val_peek(0)); yyval = val_peek(2);  }
break;
case 32:
//#line 162 "../../src/parser/parser.y"
{ yyval = new ArrayList<String>(); ((List<String>)yyval).add((String)val_peek(0)); }
break;
case 33:
//#line 165 "../../src/parser/parser.y"
{ ((List<Statement>)yyval).add((Statement)val_peek(0)); yyval = val_peek(1); }
break;
case 34:
//#line 166 "../../src/parser/parser.y"
{ yyval = new ArrayList<Statement>(); }
break;
case 35:
//#line 169 "../../src/parser/parser.y"
{ yyval = new Return(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(1)); }
break;
case 36:
//#line 170 "../../src/parser/parser.y"
{ yyval = new Read(scanner.getLine(), scanner.getColumn(), (List<Expression>)val_peek(1)); }
break;
case 37:
//#line 171 "../../src/parser/parser.y"
{ yyval = new Write(scanner.getLine(), scanner.getColumn(), (List<Expression>)val_peek(1)); }
break;
case 38:
//#line 172 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 39:
//#line 173 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 40:
//#line 174 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1)); }
break;
case 41:
//#line 175 "../../src/parser/parser.y"
{ yyval = new InvocationSt(scanner.getLine(), scanner.getColumn(), (String)val_peek(4), (List<Expression>)val_peek(2)); }
break;
case 42:
//#line 178 "../../src/parser/parser.y"
{ yyval = new While(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (List<Statement>)val_peek(1)); }
break;
case 43:
//#line 179 "../../src/parser/parser.y"
{ yyval = new While(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Statement)val_peek(0)); }
break;
case 44:
//#line 182 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(8), (List<Statement>)val_peek(5), (List<Statement>)val_peek(1)); }
break;
case 45:
//#line 183 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(6), (List<Statement>)val_peek(3), (Statement)val_peek(0)); }
break;
case 46:
//#line 184 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(6), (Statement)val_peek(4), (List<Statement>)val_peek(1)); }
break;
case 47:
//#line 185 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (Statement)val_peek(2), (Statement)val_peek(0)); }
break;
case 48:
//#line 186 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (List<Statement>)val_peek(1), new ArrayList()); }
break;
case 49:
//#line 187 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Statement)val_peek(0), new ArrayList()); }
break;
case 50:
//#line 190 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 51:
//#line 191 "../../src/parser/parser.y"
{ yyval = new ArrayList<Expression>(); }
break;
case 52:
//#line 194 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List<Expression>)yyval).add((Expression)val_peek(0)); }
break;
case 53:
//#line 195 "../../src/parser/parser.y"
{ yyval = new ArrayList<Expression>(); ((List<Expression>)yyval).add((Expression)val_peek(0)); }
break;
case 54:
//#line 198 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "+"); }
break;
case 55:
//#line 199 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "*"); }
break;
case 56:
//#line 200 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "/"); }
break;
case 57:
//#line 201 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "-"); }
break;
case 58:
//#line 202 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "%"); }
break;
case 59:
//#line 203 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "=="); }
break;
case 60:
//#line 204 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "!="); }
break;
case 61:
//#line 205 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), ">"); }
break;
case 62:
//#line 206 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "<"); }
break;
case 63:
//#line 207 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "<="); }
break;
case 64:
//#line 208 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), ">="); }
break;
case 65:
//#line 209 "../../src/parser/parser.y"
{ yyval = new LogicOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "&&"); }
break;
case 66:
//#line 210 "../../src/parser/parser.y"
{ yyval = new LogicOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "||"); }
break;
case 67:
//#line 211 "../../src/parser/parser.y"
{ yyval = new Cast(scanner.getLine(), scanner.getColumn(), (Type)val_peek(2), (Expression)val_peek(0)); }
break;
case 68:
//#line 212 "../../src/parser/parser.y"
{ yyval = new ArrayAccess(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1)); }
break;
case 69:
//#line 213 "../../src/parser/parser.y"
{ yyval = new StructAccess(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(0)); }
break;
case 70:
//#line 214 "../../src/parser/parser.y"
{ yyval = new InvocationExp(scanner.getLine(), scanner.getColumn(), (String)val_peek(3), (List<Expression>)val_peek(1)); }
break;
case 71:
//#line 215 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 72:
//#line 216 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 73:
//#line 217 "../../src/parser/parser.y"
{ yyval = new UnaryNot(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 74:
//#line 218 "../../src/parser/parser.y"
{ yyval = new LiteralInt(scanner.getLine(), scanner.getColumn(), (Integer)val_peek(0)); }
break;
case 75:
//#line 219 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 76:
//#line 220 "../../src/parser/parser.y"
{ yyval = new LiteralChar(scanner.getLine(), scanner.getColumn(), (Character)val_peek(0)); }
break;
case 77:
//#line 221 "../../src/parser/parser.y"
{ yyval = new LiteralReal(scanner.getLine(), scanner.getColumn(), (Double)val_peek(0)); }
break;
//#line 1176 "Parser.java"
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
