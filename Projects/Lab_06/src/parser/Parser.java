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
import ast.type.MainType;
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
public final static short UNARY_MINUS=283;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    3,    3,    1,    1,    4,    4,   10,   10,
   11,   11,   12,   12,    5,    2,    6,    6,   16,   16,
   15,   15,    7,    7,    7,    9,   13,   13,    8,    8,
   14,   14,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   21,   21,   20,   20,   20,   20,   20,   20,   22,
   22,   19,   19,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,
};
final static short yylen[] = {                            2,
    2,    1,    1,    1,    2,    0,    3,    3,    1,    1,
    2,    0,    4,    0,    6,    8,    9,    9,    4,    2,
    1,    0,    1,    1,    1,    4,    1,    1,    3,    1,
    2,    0,    3,    3,    3,    1,    1,    4,    5,    3,
    3,    7,    5,   11,    9,    9,    7,    7,    5,    1,
    0,    3,    1,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    4,    4,    3,    4,
    2,    3,    2,    1,    1,    1,    1,
};
final static short yydefred[] = {                         6,
    0,    0,    0,   24,   25,    0,   23,    1,    5,    2,
    3,    4,    0,    0,    0,    0,    0,   14,    0,    0,
   30,    0,    0,    0,    0,    0,    0,    7,    0,    8,
    0,    0,    0,    0,    0,    0,   27,   28,    0,    0,
   29,   26,   20,    0,    0,   12,    0,    0,    0,   12,
    0,    0,   15,   13,   12,    0,   19,    9,   10,    0,
   11,    0,    0,    0,   74,   77,    0,    0,    0,    0,
    0,    0,   76,    0,    0,    0,   16,   31,    0,   36,
   37,    0,   18,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   17,    0,   33,    0,    0,   35,    0,
    0,   34,    0,    0,   72,    0,    0,    0,    0,    0,
    0,   40,   41,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   69,    0,    0,    0,    0,    0,   67,   38,
   68,   70,   39,   32,    0,   32,   43,    0,    0,    0,
    0,   32,   47,   42,    0,    0,   32,   45,   46,    0,
   44,
};
final static short yydgoto[] = {                          1,
    2,    8,    9,   58,   59,   12,   60,   20,   14,   61,
   52,   26,   15,   62,   33,   34,   78,   79,  117,   80,
   81,  118,
};
final static short yysindex[] = {                         0,
    0, -188, -255,    0,    0, -101,    0,    0,    0,    0,
    0,    0, -233, -212,   -4,   53,   56,    0,   59,   -9,
    0,   12, -157, -171,   60, -122, -171,    0, -161,    0,
   18, -151,   73,   72,   -2, -212,    0,    0,  -90,   77,
    0,    0,    0,   -1, -171,    0,   16,   24,    1,    0,
 -145, -207,    0,    0,    0, -207,    0,    0,    0, -212,
    0,  -33, -207,  -15,    0,    0,  261,   83,  261,   85,
  261,   87,    0,  261,  261,  207,    0,    0,  618,    0,
    0,    3,    0,   89,  788,  261,  983,   30,  261,   32,
  261,   -5,  -38,   90,  880,  261,  261,  261,  261,  261,
  261,   71,   76,  261,  261,  261,  261,  261,  261,  261,
  261,  261, -132,    0,  261,    0,   92,   96,    0,  261,
  902,    0,  936,  261,    0,  124,   -5,   -5,  124,  -26,
  -26,    0,    0,  943,  -26,  -26,  -32,  -32,  -38,  -38,
  -38,  976,    0,   98,   81,  983,   21,   39,    0,    0,
    0,    0,    0,    0, -133,    0,    0,   57,   75,   93,
 -126,    0,    0,    0,  113,  132,    0,    0,    0,  150,
    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   51,   54,    0,    0,    0,    0,   36,    0,
    0,    0,    0,  107,    0,    0,  107,    0,    0,    0,
    0,    0,    0,  108,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  168,    0,    0,    0,  168,    0,    0,    0,   51,
    0,    0,  168,    0,    0,    0,    0,  630,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  486,    0,  109,    5,    0,    0,    0,
    0,  376,  512,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  109,    0,  110,    0,    0,    0,
    0,    0,    0,    0,    0,  827,  433, 1018,  853, 1011,
 1034,    0,    0,    0, 1057, 1069,  679,  735,  539,  565,
  592,    0,    0,    0,  656,   26,    0,    0,    0,    0,
    0,    0,    0,    0,  187,    0,    0,    0,    0,    0,
  243,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  153,  154,    0,    2,  -12,  126,    0,
  -11,    0,  131,  -50,  133,    0, -114, 1284,   38,    0,
    0,   44,
};
final static int YYTABLESIZE=1408;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         74,
   23,   22,   36,   13,  111,   64,   76,  113,   16,  109,
  111,   75,   82,  113,  110,  109,  107,   74,  108,  113,
  110,   18,   17,   47,   76,   32,   48,   37,   32,   75,
   19,  111,  155,  157,   29,   74,  109,  107,   56,  108,
  113,  110,   76,   63,  163,   53,   51,   75,   53,   28,
  168,   21,  112,   74,  106,   29,  105,    4,  112,   29,
   76,    5,    6,   53,  112,   75,   52,   29,    7,   52,
   30,   74,    3,  120,   53,  120,    4,   94,   76,   30,
    5,    6,   54,   75,   52,  112,   23,    7,  119,   74,
  122,   77,   24,    4,   30,   25,   76,    5,   27,   31,
   35,   75,   41,  158,    7,  160,   88,   74,   90,   83,
   42,  166,   43,   44,   76,   45,  170,   49,   57,   75,
   46,   50,   86,   55,   89,   74,   91,  114,  115,  132,
  124,  143,   76,  159,  133,  120,  145,   75,  152,  153,
  165,   27,    4,  154,   28,   74,    5,   22,   21,   51,
   50,   38,   76,    7,   10,   11,   39,   75,  144,   40,
  111,  156,    0,    0,   74,  109,  107,    0,  108,  113,
  110,   76,    0,   21,    0,    0,   75,    0,    0,    0,
    0,  161,   74,  106,    0,  105,    0,    0,    0,   76,
    0,    0,    0,    0,   75,    0,    0,  162,    0,    0,
   32,    0,    0,    0,    0,    0,    0,   32,    0,    0,
    0,    0,   32,    0,  112,    0,    0,  164,    0,   49,
    0,    0,    0,   65,   66,    0,   49,    0,    0,   67,
   68,   49,   69,    0,   70,  167,    0,   71,    0,   74,
   72,   65,   66,    0,    0,   73,   76,   67,   68,    0,
   69,   75,   70,   96,    0,   71,  169,    0,   72,   65,
   66,    0,    0,   73,    0,   67,   68,   99,   69,  100,
   70,  101,    0,   71,  171,   48,   72,   65,   66,    0,
    0,   73,   48,   67,   68,    0,   69,   48,   70,    0,
    0,   71,   32,   74,   72,   65,   66,    0,    0,   73,
   76,   67,   68,    0,   69,   75,   70,    0,    0,   71,
    0,   49,   72,   65,   66,    0,    0,   73,    0,   67,
   68,    0,   69,    0,   70,    0,    0,   71,    0,    0,
   72,   65,   66,    0,    0,   73,    0,   67,   68,    0,
   69,    0,   70,    0,    0,   71,    0,    0,   72,   65,
   66,    0,    0,   73,    0,   67,   68,    0,   69,    0,
   70,    0,    0,   71,    0,    0,   72,   48,    0,   65,
   66,   73,    0,    0,    0,   67,   68,    0,   69,    0,
   70,    0,    0,   71,    0,    0,   72,    0,   65,   66,
    0,   73,    0,    0,   67,   68,    0,   69,  100,   70,
  101,    0,   71,    0,    0,   72,   65,   66,    0,    0,
   73,    0,   67,   68,    0,   69,   73,   70,    0,   73,
   71,    0,    0,   72,   32,   32,    0,    0,   73,    0,
   32,   32,    0,   32,   73,   32,   73,    0,   32,    0,
    0,   32,    0,   49,   49,    0,   32,    0,    0,   49,
   49,    0,   49,    0,   49,    0,    0,   49,    0,    0,
   49,    0,    0,   65,   66,   49,    0,    0,   73,    0,
   84,    4,    0,   66,    0,    5,   66,    0,    0,    0,
    0,    0,    7,    0,    0,   73,    0,    0,    0,    0,
    0,   66,    0,   66,    0,    0,    0,    0,    0,   48,
   48,    0,    0,    0,    0,   48,   48,    0,   48,    0,
   48,    0,    0,   48,    0,    0,   48,   65,   66,    0,
    0,   48,   75,    0,   84,   66,   75,   75,   75,   75,
   75,   75,   75,    0,    0,    0,    0,    0,    0,   73,
    0,    0,    0,    0,   75,   75,   75,   75,   71,    0,
    0,    0,   71,   71,   71,   71,   71,    0,   71,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   71,   71,   71,   71,    0,   55,   75,    0,   75,   55,
   55,   55,   55,   55,    0,   55,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   55,   55,   55,
   55,   56,    0,    0,   71,   56,   56,   56,   56,   56,
    0,   56,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   56,   56,   56,   56,    0,   58,    0,
    0,   55,   58,   58,   58,   58,   58,   73,   58,    0,
    0,    0,    0,    0,    0,    0,    0,   73,    0,    0,
   58,   58,   58,   58,  111,   73,   73,   56,    0,  109,
  107,    0,  108,  113,  110,    0,   75,    0,    0,    0,
    0,   75,   75,    0,   75,   75,   75,  106,  104,  105,
    0,    0,    0,    0,   58,    0,    0,    0,    0,   75,
   75,   75,   70,    0,   66,    0,    0,   70,   70,    0,
   70,   70,   70,    0,   66,    0,    0,    0,  112,    0,
    0,    0,   66,   66,    0,   70,   70,   70,    0,   54,
   75,   54,   54,   54,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   54,   54,   54,
   54,    0,    0,    0,   75,    0,   70,   75,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   75,   75,    0,
   75,    0,   75,    0,    0,   75,   75,    0,    0,    0,
   71,   54,    0,   71,    0,   57,    0,   57,   57,   57,
    0,    0,    0,   71,   71,    0,   71,    0,   71,    0,
    0,   71,   71,   57,   57,   57,   57,   55,    0,    0,
   55,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   55,   55,    0,   55,    0,   55,    0,    0,   55,   55,
    0,    0,    0,   56,  111,    0,   56,   57,    0,  109,
  107,    0,  108,  113,  110,    0,   56,   56,    0,   56,
    0,   56,    0,    0,   56,   56,  116,  106,    0,  105,
   58,    0,    0,   58,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   58,   58,    0,   58,   59,   58,    0,
   59,   58,   58,    0,    0,    0,   96,    0,  112,   97,
    0,    0,    0,    0,    0,   59,    0,   59,   75,   98,
   99,   75,  100,   60,  101,    0,   60,  102,  103,    0,
    0,   75,   75,    0,   75,    0,   75,    0,    0,   75,
   75,   60,    0,   60,   70,    0,  111,   70,    0,   59,
  125,  109,  107,    0,  108,  113,  110,   70,   70,    0,
   70,    0,   70,    0,    0,   70,   70,   54,  111,  106,
   54,  105,  147,  109,  107,   60,  108,  113,  110,    0,
   54,   54,    0,   54,    0,   54,    0,    0,   54,   54,
    0,  106,    0,  105,    0,    0,    0,    0,    0,    0,
  112,    0,  111,    0,    0,    0,  148,  109,  107,  111,
  108,  113,  110,    0,  109,  107,    0,  108,  113,  110,
    0,    0,  112,   57,    0,  106,   57,  105,    0,    0,
    0,  150,  106,    0,  105,    0,   57,   57,    0,   57,
    0,   57,  111,    0,   57,   57,    0,  109,  107,  111,
  108,  113,  110,    0,  109,  107,  112,  108,  113,  110,
    0,    0,    0,  112,    0,  106,    0,  105,    0,    0,
    0,    0,  106,    0,  105,    0,   96,    0,    0,   97,
    0,   64,    0,    0,   64,    0,    0,    0,   65,   98,
   99,   65,  100,    0,  101,    0,  112,    0,  151,   64,
   64,   64,   64,  112,   63,    0,   65,   63,   65,    0,
    0,    0,    0,    0,    0,   59,    0,    0,   59,    0,
    0,    0,   63,   63,   63,   63,    0,   61,   59,   59,
   61,    0,    0,   64,    0,    0,   59,   59,    0,   62,
   65,   60,   62,    0,   60,   61,   61,   61,   61,    0,
    0,    0,    0,    0,   60,   60,   63,   62,   62,   62,
   62,    0,   60,   60,    0,    0,    0,    0,   96,    0,
    0,   97,    0,    0,    0,    0,    0,    0,    0,   61,
    0,   98,   99,    0,  100,    0,  101,    0,    0,    0,
   96,   62,    0,   97,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   98,   99,    0,  100,    0,  101,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   96,    0,    0,   97,    0,    0,
    0,   96,    0,    0,   97,    0,    0,   98,   99,    0,
  100,    0,  101,    0,   98,   99,    0,  100,    0,  101,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   96,    0,    0,   97,    0,    0,
    0,   96,    0,    0,   97,    0,    0,   98,   99,    0,
  100,    0,  101,    0,   98,   99,    0,  100,    0,  101,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   64,
    0,    0,   64,    0,    0,    0,    0,    0,    0,   65,
    0,    0,   64,   64,    0,   64,    0,   64,    0,   65,
   64,   64,   63,    0,    0,   63,    0,   65,   65,    0,
    0,    0,    0,    0,    0,   63,   63,    0,   63,    0,
   63,    0,    0,   63,   63,   61,    0,    0,   61,    0,
    0,    0,    0,    0,    0,    0,    0,   62,   61,   61,
   62,   61,    0,   61,    0,    0,   61,   61,    0,    0,
   62,   62,    0,   62,    0,   62,    0,    0,   62,   62,
   85,    0,   87,    0,   87,    0,    0,   92,   93,   95,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   87,
    0,    0,  121,    0,  123,    0,    0,    0,    0,  126,
  127,  128,  129,  130,  131,    0,    0,  134,  135,  136,
  137,  138,  139,  140,  141,  142,    0,    0,   87,    0,
    0,    0,    0,  146,    0,    0,    0,  149,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   91,   14,  125,    2,   37,   56,   40,   46,  264,   42,
   37,   45,   63,   46,   47,   42,   43,   33,   45,   46,
   47,  123,  278,   36,   40,   24,   39,   26,   27,   45,
  264,   37,  147,  148,   44,   33,   42,   43,   50,   45,
   46,   47,   40,   55,  159,   41,   45,   45,   44,   59,
  165,  264,   91,   33,   60,   44,   62,  265,   91,   44,
   40,  269,  270,   59,   91,   45,   41,   44,  276,   44,
   59,   33,  261,   44,   59,   44,  265,   76,   40,   44,
  269,  270,   59,   45,   59,   91,   91,  276,   59,   33,
   59,  125,   40,  265,   59,   40,   40,  269,   40,  257,
   41,   45,  264,  154,  276,  156,   69,   33,   71,  125,
   93,  162,  264,   41,   40,   44,  167,   41,  264,   45,
  123,  123,   40,  123,   40,   33,   40,  125,   40,   59,
   41,  264,   40,  267,   59,   44,   41,   45,   41,   59,
  267,   91,  265,  123,   91,   33,  269,   41,   41,   41,
   41,   26,   40,  276,    2,    2,   26,   45,  115,   27,
   37,  123,   -1,   -1,   33,   42,   43,   -1,   45,   46,
   47,   40,   -1,  264,   -1,   -1,   45,   -1,   -1,   -1,
   -1,  125,   33,   60,   -1,   62,   -1,   -1,   -1,   40,
   -1,   -1,   -1,   -1,   45,   -1,   -1,  123,   -1,   -1,
   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,
   -1,   -1,   45,   -1,   91,   -1,   -1,  125,   -1,   33,
   -1,   -1,   -1,  257,  258,   -1,   40,   -1,   -1,  263,
  264,   45,  266,   -1,  268,  123,   -1,  271,   -1,   33,
  274,  257,  258,   -1,   -1,  279,   40,  263,  264,   -1,
  266,   45,  268,  259,   -1,  271,  125,   -1,  274,  257,
  258,   -1,   -1,  279,   -1,  263,  264,  273,  266,  275,
  268,  277,   -1,  271,  125,   33,  274,  257,  258,   -1,
   -1,  279,   40,  263,  264,   -1,  266,   45,  268,   -1,
   -1,  271,  125,   33,  274,  257,  258,   -1,   -1,  279,
   40,  263,  264,   -1,  266,   45,  268,   -1,   -1,  271,
   -1,  125,  274,  257,  258,   -1,   -1,  279,   -1,  263,
  264,   -1,  266,   -1,  268,   -1,   -1,  271,   -1,   -1,
  274,  257,  258,   -1,   -1,  279,   -1,  263,  264,   -1,
  266,   -1,  268,   -1,   -1,  271,   -1,   -1,  274,  257,
  258,   -1,   -1,  279,   -1,  263,  264,   -1,  266,   -1,
  268,   -1,   -1,  271,   -1,   -1,  274,  125,   -1,  257,
  258,  279,   -1,   -1,   -1,  263,  264,   -1,  266,   -1,
  268,   -1,   -1,  271,   -1,   -1,  274,   -1,  257,  258,
   -1,  279,   -1,   -1,  263,  264,   -1,  266,  275,  268,
  277,   -1,  271,   -1,   -1,  274,  257,  258,   -1,   -1,
  279,   -1,  263,  264,   -1,  266,   41,  268,   -1,   44,
  271,   -1,   -1,  274,  257,  258,   -1,   -1,  279,   -1,
  263,  264,   -1,  266,   59,  268,   61,   -1,  271,   -1,
   -1,  274,   -1,  257,  258,   -1,  279,   -1,   -1,  263,
  264,   -1,  266,   -1,  268,   -1,   -1,  271,   -1,   -1,
  274,   -1,   -1,  257,  258,  279,   -1,   -1,   93,   -1,
  264,  265,   -1,   41,   -1,  269,   44,   -1,   -1,   -1,
   -1,   -1,  276,   -1,   -1,  279,   -1,   -1,   -1,   -1,
   -1,   59,   -1,   61,   -1,   -1,   -1,   -1,   -1,  257,
  258,   -1,   -1,   -1,   -1,  263,  264,   -1,  266,   -1,
  268,   -1,   -1,  271,   -1,   -1,  274,  257,  258,   -1,
   -1,  279,   37,   -1,  264,   93,   41,   42,   43,   44,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,  279,
   -1,   -1,   -1,   -1,   59,   60,   61,   62,   37,   -1,
   -1,   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   59,   60,   61,   62,   -1,   37,   91,   -1,   93,   41,
   42,   43,   44,   45,   -1,   47,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,   61,
   62,   37,   -1,   -1,   93,   41,   42,   43,   44,   45,
   -1,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   59,   60,   61,   62,   -1,   37,   -1,
   -1,   93,   41,   42,   43,   44,   45,  262,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,   -1,   -1,
   59,   60,   61,   62,   37,  280,  281,   93,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   37,   -1,   -1,   -1,
   -1,   42,   43,   -1,   45,   46,   47,   60,   61,   62,
   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,   60,
   61,   62,   37,   -1,  262,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   -1,  272,   -1,   -1,   -1,   91,   -1,
   -1,   -1,  280,  281,   -1,   60,   61,   62,   -1,   41,
   91,   43,   44,   45,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,   61,
   62,   -1,   -1,   -1,  259,   -1,   91,  262,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,   -1,
  275,   -1,  277,   -1,   -1,  280,  281,   -1,   -1,   -1,
  259,   93,   -1,  262,   -1,   41,   -1,   43,   44,   45,
   -1,   -1,   -1,  272,  273,   -1,  275,   -1,  277,   -1,
   -1,  280,  281,   59,   60,   61,   62,  259,   -1,   -1,
  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  272,  273,   -1,  275,   -1,  277,   -1,   -1,  280,  281,
   -1,   -1,   -1,  259,   37,   -1,  262,   93,   -1,   42,
   43,   -1,   45,   46,   47,   -1,  272,  273,   -1,  275,
   -1,  277,   -1,   -1,  280,  281,   59,   60,   -1,   62,
  259,   -1,   -1,  262,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  272,  273,   -1,  275,   41,  277,   -1,
   44,  280,  281,   -1,   -1,   -1,  259,   -1,   91,  262,
   -1,   -1,   -1,   -1,   -1,   59,   -1,   61,  259,  272,
  273,  262,  275,   41,  277,   -1,   44,  280,  281,   -1,
   -1,  272,  273,   -1,  275,   -1,  277,   -1,   -1,  280,
  281,   59,   -1,   61,  259,   -1,   37,  262,   -1,   93,
   41,   42,   43,   -1,   45,   46,   47,  272,  273,   -1,
  275,   -1,  277,   -1,   -1,  280,  281,  259,   37,   60,
  262,   62,   41,   42,   43,   93,   45,   46,   47,   -1,
  272,  273,   -1,  275,   -1,  277,   -1,   -1,  280,  281,
   -1,   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,   -1,
   91,   -1,   37,   -1,   -1,   -1,   41,   42,   43,   37,
   45,   46,   47,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   91,  259,   -1,   60,  262,   62,   -1,   -1,
   -1,   59,   60,   -1,   62,   -1,  272,  273,   -1,  275,
   -1,  277,   37,   -1,  280,  281,   -1,   42,   43,   37,
   45,   46,   47,   -1,   42,   43,   91,   45,   46,   47,
   -1,   -1,   -1,   91,   -1,   60,   -1,   62,   -1,   -1,
   -1,   -1,   60,   -1,   62,   -1,  259,   -1,   -1,  262,
   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,   41,  272,
  273,   44,  275,   -1,  277,   -1,   91,   -1,   93,   59,
   60,   61,   62,   91,   41,   -1,   59,   44,   61,   -1,
   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,
   -1,   -1,   59,   60,   61,   62,   -1,   41,  272,  273,
   44,   -1,   -1,   93,   -1,   -1,  280,  281,   -1,   41,
   93,  259,   44,   -1,  262,   59,   60,   61,   62,   -1,
   -1,   -1,   -1,   -1,  272,  273,   93,   59,   60,   61,
   62,   -1,  280,  281,   -1,   -1,   -1,   -1,  259,   -1,
   -1,  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,
   -1,  272,  273,   -1,  275,   -1,  277,   -1,   -1,   -1,
  259,   93,   -1,  262,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  272,  273,   -1,  275,   -1,  277,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,
   -1,  259,   -1,   -1,  262,   -1,   -1,  272,  273,   -1,
  275,   -1,  277,   -1,  272,  273,   -1,  275,   -1,  277,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,
   -1,  259,   -1,   -1,  262,   -1,   -1,  272,  273,   -1,
  275,   -1,  277,   -1,  272,  273,   -1,  275,   -1,  277,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,
   -1,   -1,  262,   -1,   -1,   -1,   -1,   -1,   -1,  262,
   -1,   -1,  272,  273,   -1,  275,   -1,  277,   -1,  272,
  280,  281,  259,   -1,   -1,  262,   -1,  280,  281,   -1,
   -1,   -1,   -1,   -1,   -1,  272,  273,   -1,  275,   -1,
  277,   -1,   -1,  280,  281,  259,   -1,   -1,  262,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,  272,  273,
  262,  275,   -1,  277,   -1,   -1,  280,  281,   -1,   -1,
  272,  273,   -1,  275,   -1,  277,   -1,   -1,  280,  281,
   67,   -1,   69,   -1,   71,   -1,   -1,   74,   75,   76,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   86,
   -1,   -1,   89,   -1,   91,   -1,   -1,   -1,   -1,   96,
   97,   98,   99,  100,  101,   -1,   -1,  104,  105,  106,
  107,  108,  109,  110,  111,  112,   -1,   -1,  115,   -1,
   -1,   -1,   -1,  120,   -1,   -1,   -1,  124,
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
"statement : exp INC ';'",
"statement : exp DEC ';'",
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

//#line 739 "Parser.java"
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
//#line 98 "../../src/parser/parser.y"
{ ((List<Definition>)val_peek(1)).add((Definition)val_peek(0)); this.root = new Program(scanner.getLine(), scanner.getColumn(), (List<Definition>)val_peek(1)); }
break;
case 2:
//#line 101 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 3:
//#line 102 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 4:
//#line 103 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 5:
//#line 106 "../../src/parser/parser.y"
{ yyval = val_peek(1); mergeDefs((List<Definition>)yyval, val_peek(0)); }
break;
case 6:
//#line 107 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 7:
//#line 110 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); addVarDefs((List<Definition>)yyval, (Type)val_peek(2), (List<String>)val_peek(1), scanner.getLine()); }
break;
case 8:
//#line 111 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); addVarDefs((List<Definition>)yyval, (Type)val_peek(2), (List<String>)val_peek(1), scanner.getLine()); }
break;
case 9:
//#line 115 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 10:
//#line 116 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 11:
//#line 119 "../../src/parser/parser.y"
{ yyval = val_peek(1); mergeDefs((List<Definition>)yyval, val_peek(0)); }
break;
case 12:
//#line 120 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 13:
//#line 123 "../../src/parser/parser.y"
{ yyval = val_peek(3); addFieldDefs((List<Definition>)yyval, (Type)val_peek(2), (List<String>)val_peek(1), scanner.getColumn()); }
break;
case 14:
//#line 124 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 15:
//#line 127 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); addStructDefs((List<Definition>)yyval, (List<Definition>)val_peek(3), (List<String>)val_peek(1), scanner.getLine()); }
break;
case 16:
//#line 130 "../../src/parser/parser.y"
{ yyval = new DefFunc(scanner.getLine(), scanner.getColumn(),(Type) new MainType(scanner.getLine(), scanner.getColumn(), (Type)VoidType.getInstance()), "main", (List<Definition>)val_peek(2), (List<Statement>)val_peek(1)); }
break;
case 17:
//#line 133 "../../src/parser/parser.y"
{ yyval = new DefFunc(scanner.getLine(), scanner.getColumn(), (Type)new FuncType(scanner.getLine(), scanner.getColumn(), (Type)val_peek(8), (List<Definition>)val_peek(5)), (String)val_peek(7), (List<Definition>)val_peek(2), (List<Statement>)val_peek(1)); }
break;
case 18:
//#line 134 "../../src/parser/parser.y"
{ yyval = new DefFunc(scanner.getLine(), scanner.getColumn(),(Type) new FuncType(scanner.getLine(), scanner.getColumn(), (Type)VoidType.getInstance(), (List<Definition>)val_peek(5)), (String)val_peek(7), (List<Definition>)val_peek(2), (List<Statement>)val_peek(1)); }
break;
case 19:
//#line 139 "../../src/parser/parser.y"
{ ((List<Definition>)yyval).add(new DefVar(scanner.getLine(), scanner.getColumn(), (Type)val_peek(1), (String)val_peek(0))); yyval = val_peek(3); }
break;
case 20:
//#line 140 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>();((List<Definition>)yyval).add(new DefVar(scanner.getLine(), scanner.getColumn(), (Type)val_peek(1), (String)val_peek(0))); }
break;
case 21:
//#line 143 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 22:
//#line 144 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 23:
//#line 147 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 24:
//#line 148 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 25:
//#line 149 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 26:
//#line 152 "../../src/parser/parser.y"
{ yyval = getArrayDef((Type)val_peek(3), (Integer)val_peek(1), scanner.getLine()); }
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
//#line 174 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(Expression) new ArithmeticOperation(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),new LiteralInt(scanner.getLine(),scanner.getColumn(),1),'+'));}
break;
case 41:
//#line 175 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(Expression) new ArithmeticOperation(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),new LiteralInt(scanner.getLine(),scanner.getColumn(),1),'-'));}
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
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), '+'); }
break;
case 55:
//#line 199 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), '*'); }
break;
case 56:
//#line 200 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), '/'); }
break;
case 57:
//#line 201 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), '-'); }
break;
case 58:
//#line 202 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), '%'); }
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
//#line 1196 "Parser.java"
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
