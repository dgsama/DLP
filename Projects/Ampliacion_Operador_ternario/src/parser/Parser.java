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
public final static short CAST=283;
public final static short UNARY_MINUS=284;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    3,    1,    1,    4,    4,    4,   10,   11,
   11,   12,   12,    9,    2,    5,    5,   16,   16,   15,
   15,    6,    6,    6,    8,   13,   13,   13,    7,    7,
   14,   14,   17,   17,   17,   17,   17,   17,   17,   17,
   24,   24,   24,   24,   24,   24,   24,   24,   23,   23,
   21,   21,   20,   20,   20,   20,   20,   20,   22,   22,
   19,   19,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,
};
final static short yylen[] = {                            2,
    2,    1,    1,    2,    0,    3,    3,    3,    1,    2,
    0,    4,    0,    4,    8,    9,    9,    4,    2,    1,
    0,    1,    1,    1,    4,    1,    1,    1,    3,    1,
    2,    0,    3,    3,    3,    1,    1,    4,    5,    1,
    2,    2,    2,    1,    1,    3,    4,    1,    8,   10,
    7,    5,   11,    9,    9,    7,    7,    5,    1,    0,
    3,    1,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    4,    4,    3,    4,    2,
    3,    2,    1,    1,    1,    1,
};
final static short yydefred[] = {                         5,
    0,    0,    0,   23,   24,    0,   22,    1,    4,    2,
    3,    0,    0,    0,    0,    0,    0,   13,    0,    0,
   30,    0,    0,    0,    0,    0,    0,    0,    6,    0,
    7,    8,    0,    0,    0,    0,    0,   14,   26,   27,
   28,    0,    0,   29,   25,   19,    0,    0,   11,    0,
    0,   11,    0,    0,   12,   11,    0,   18,    9,    0,
   10,    0,    0,    0,   83,   86,    0,    0,    0,    0,
    0,    0,   85,    0,    0,    0,   15,   31,    0,   36,
   37,   40,    0,   17,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   16,    0,    0,   33,    0,    0,   35,
    0,    0,   34,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   78,    0,   81,    0,    0,    0,    0,    0,
    0,    0,   38,   77,   79,   39,   32,    0,   32,   52,
    0,    0,    0,    0,    0,   44,   45,   48,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   32,   56,   51,    0,    0,    0,    0,    0,    0,
    0,   49,    0,   32,   54,   55,    0,    0,   50,   53,
};
final static short yydgoto[] = {                          1,
    2,    8,    9,   59,   11,   60,   20,   13,   14,   61,
   54,   27,   15,   62,   35,   36,   78,   79,  118,   80,
   81,  119,   82,  169,
};
final static short yysindex[] = {                         0,
    0,  -99, -247,    0,    0,  -93,    0,    0,    0,    0,
    0, -240, -229, -229,  -48,   15,   39,    0,   51,  -15,
    0,   16,   36, -161, -100,   58, -117, -100,    0, -154,
    0,    0,   22, -140,   86,   89,   28,    0,    0,    0,
    0,  -89,  117,    0,    0,    0,   37, -100,    0,   41,
   49,    0, -125, -228,    0,    0, -228,    0,    0, -229,
    0,    7, -228,   25,    0,    0,  -30,  133,  -30,  138,
  -30,  140,    0,  -30,  -30,  223,    0,    0,  648,    0,
    0,    0,   43,    0,  141,  223,  675,  -30, 1104,   45,
  -30,   75,  -30, 1111,  -37,  142,  682,  -30,  -30,  -30,
  -30,  -30,  -30,  265,  -30,  -30,  -30,  -30,  -30,  -30,
  -30,  -30,  -82,    0,  -30,  739,    0,  143,  144,    0,
  -30,  839,    0,  919,  -30,  125, 1168, 1111, 1111, 1168,
   26,   26,  223,  926,   26,   26,   56,   56,  -37,  -37,
  -37,  947,    0,  149,    0,  132, 1104,   61,   80,  -37,
  247,  981,    0,    0,    0,    0,    0,  -75,    0,    0,
  -30,  154,  -30,  -30,  988,    0,    0,    0,  137,  136,
   98,  116,  134, 1104,  -30,  143,  143,  265,  247,  -30,
  -71,    0,    0,    0,  156, 1104,  145, 1009,  153,  172,
    0,    0,  -30,    0,    0,    0, 1016,  203,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  109,  110,  111,    0,    0,    0,    0,   82,    0,
    0,    0,    0,    0,  165,    0,    0,  165,    0,    0,
    0,    0,    0,    0,    0,  166,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  227,    0,    0,  227,    0,    0,  109,
    0,    0,  227,    0,    0,    0,    0, 1043,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  490,    0,    0,  167,   70,    0,
    0,    0,    0,  -40,  516,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  167,    0,    0,  168,    0,    0,
    0,    0,    0,    0,    0, 1076, 1211,   23,   96, 1278,
 1239, 1334,    0,    0, 1358, 1370, 1251, 1346,  543,  552,
  579,    0,    0,    0,    0, 1083,   77,    0,    0,  588,
    0,    0,    0,    0,    0,    0,    0,  -33,    0,    0,
    0, 1043,    0,    0,    0,    0,    0,    0,    0,  641,
    0,    0,    0,    3,  167,   64,   87,    0,    0,    0,
  -13,    0,    0,    0,    0,  105,    0,    0,    0,    0,
  615,    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  208,    0,   11,    9,  184,  186,    0,
   34,    0,  187,  -52,  188,    0, -115, 1577,  -55, -123,
 -102, -109, -101,   40,
};
final static int YYTABLESIZE=1770;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         58,
   82,   24,   74,   82,   64,  144,   58,   38,  113,   86,
   83,   58,   12,   90,   75,   92,   16,   82,   82,   57,
   82,   22,   23,   19,   58,   58,   57,  166,   30,   18,
   17,   57,  158,  160,   21,   34,    4,   39,   34,   74,
    5,    6,   24,   29,   57,   57,   76,    7,  167,  168,
   50,   75,   82,  112,   25,  166,  183,   74,   53,   30,
   41,   41,  111,   75,   76,  185,   75,  109,  107,   75,
  108,  113,  110,  195,   31,   74,  167,  168,   26,   30,
   75,   75,   76,   75,   30,   57,   96,   75,  121,   63,
   28,   58,  111,   74,   32,   33,   96,  109,   37,   55,
   76,  113,  110,  120,  171,   75,  173,  176,  177,   44,
   62,   57,   74,   62,   45,   75,  112,   61,  121,   76,
   61,   43,   43,   46,   75,   30,   47,   62,   62,  190,
   74,   77,   48,  123,   61,   61,   74,   76,   58,   74,
   30,  198,   75,   96,   42,   42,  112,    4,   74,   84,
   49,    5,    6,   74,   74,   76,   74,   51,    7,   52,
   75,    3,   46,   46,    4,    4,   74,  114,    5,    5,
    6,   56,   88,   76,   21,    7,    7,   91,   75,   93,
  115,  143,  125,  157,  146,   74,  121,  151,   74,  155,
  156,  172,   76,  175,  179,  189,  191,   75,  180,   26,
   27,   28,  159,  192,   74,   21,   20,   60,   59,   10,
   40,   76,   41,   42,    0,   43,   75,    0,  187,    0,
    0,   82,  181,   58,   58,    0,   65,   66,    0,   58,
   58,   82,   58,   85,   58,   74,    0,   58,  182,    0,
   58,    0,   76,   57,   57,   58,    0,   75,   73,   57,
   57,    0,   57,    0,   57,   74,    0,   57,  184,   32,
   57,    0,   86,   65,   66,   57,   32,   75,    0,   67,
   68,   32,   69,    0,   70,  194,    0,   71,    0,   74,
   72,   65,   66,    0,   75,   73,   76,   67,   68,    0,
   69,   75,   70,    0,   75,   71,  196,   74,   72,   65,
   66,    0,    0,   73,  133,   67,   68,    0,   69,   75,
   70,    0,    0,   71,    0,    0,   72,   65,   66,    0,
    0,   73,    0,   67,   68,    0,   69,  200,   70,    0,
    0,   71,    0,    0,   72,    0,   65,   66,    0,   73,
    0,    0,   67,   68,    0,   69,    0,   70,    0,    0,
   71,   32,    0,   72,   65,   66,    0,   74,   73,    0,
   67,   68,    0,   69,    0,   70,    0,   74,   71,    0,
    0,   72,   65,   66,    0,    0,   73,    0,   67,   68,
    0,   69,    0,   70,    0,    0,   71,    0,    0,   72,
   65,   66,    0,    0,   73,    0,   67,   68,    0,   69,
    0,   70,    0,    0,   71,    0,    0,   72,    0,   65,
   66,    0,   73,    0,    0,   67,   68,    0,   69,    0,
   70,    0,    0,   71,    0,    0,   72,    0,   65,   66,
    0,   73,    0,    0,   67,   68,    0,   69,    0,   70,
    0,    0,   71,    0,    0,   72,    0,    0,    0,    0,
   73,    0,    0,    0,    0,    0,    0,    0,    0,   65,
   66,    0,    0,    0,    0,   67,   68,    0,   69,    0,
   70,    0,    0,   71,    0,    0,   72,    0,    0,   65,
   66,   73,    0,   32,   32,    0,   85,    4,    0,   32,
   32,    5,   32,    0,   32,    0,    0,   32,    7,    0,
   32,   73,    0,   65,   66,   32,    0,    0,    0,  161,
  162,    0,  163,    0,   70,    0,    0,  164,    0,    0,
   72,   65,   66,    0,    0,   73,   84,    0,   85,    0,
   84,   84,   84,   84,   84,   84,   84,    0,    0,    0,
    0,    0,    0,   73,    0,    0,    0,   84,   84,   84,
   84,   84,   80,    0,    0,    0,   80,   80,   80,   80,
   80,    0,   80,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   80,   80,   80,   80,   80,    0,   64,
   84,    0,   84,   64,   64,   64,   64,   64,   65,   64,
    0,    0,   65,   65,   65,   65,   65,    0,   65,    0,
   64,   64,   64,   64,   64,    0,    0,    0,   80,   65,
   65,   65,   65,   65,    0,   67,    0,    0,    0,   67,
   67,   67,   67,   67,   76,   67,    0,    0,   76,   76,
   76,   76,   76,    0,   76,   64,   67,   67,   67,   67,
   67,    0,    0,    0,   65,   76,   76,   76,   76,   76,
    0,   79,    0,    0,    0,    0,   79,   79,    0,   79,
   79,   79,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   67,   47,   47,   79,   79,   79,   81,    0,    0,
   76,    0,   81,   81,  111,   81,   81,   81,    0,  109,
  107,    0,  108,  113,  110,    0,    0,    0,   81,   81,
   81,    0,   81,    0,    0,   79,    0,  106,  104,  105,
    0,  111,    0,    0,    0,    0,  109,  107,  111,  108,
  113,  110,  126,  109,  107,    0,  108,  113,  110,    0,
    0,   81,    0,  117,  106,    0,  105,    0,  112,    0,
    0,  106,    0,  105,    0,    0,    0,    0,   84,    0,
    0,   84,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   84,   84,    0,   84,  112,   84,    0,    0,    0,
    0,    0,  112,    0,   80,  111,    0,   80,    0,  145,
  109,  107,    0,  108,  113,  110,    0,   80,   80,    0,
   80,    0,   80,    0,    0,    0,    0,    0,  106,    0,
  105,   64,    0,    0,   64,    0,    0,    0,    0,    0,
   65,    0,    0,   65,   64,   64,    0,   64,    0,   64,
    0,    0,    0,   65,   65,    0,   65,    0,   65,  112,
    0,    0,    0,    0,    0,    0,    0,   67,    0,    0,
   67,    0,    0,    0,    0,    0,   76,    0,    0,   76,
   67,   67,    0,   67,    0,   67,    0,    0,    0,   76,
   76,    0,   76,    0,   76,    0,    0,    0,    0,    0,
    0,    0,    0,   79,    0,  111,   79,    0,    0,  148,
  109,  107,    0,  108,  113,  110,   79,   79,    0,   79,
    0,   79,    0,    0,    0,    0,    0,    0,  106,   81,
  105,    0,   81,    0,    0,    0,   98,    0,    0,   99,
    0,    0,   81,   81,    0,   81,    0,   81,    0,  100,
  101,    0,  102,    0,  103,    0,    0,    0,    0,  112,
    0,    0,    0,   98,    0,    0,   99,    0,    0,    0,
   98,    0,    0,   99,    0,    0,  100,  101,    0,  102,
    0,  103,    0,  100,  101,  111,  102,    0,  103,  149,
  109,  107,  111,  108,  113,  110,    0,  109,  107,    0,
  108,  113,  110,    0,    0,    0,    0,    0,  106,    0,
  105,    0,    0,  111,  153,  106,    0,  105,  109,  107,
    0,  108,  113,  110,    0,    0,    0,   98,    0,    0,
   99,    0,    0,    0,    0,    0,  106,    0,  105,  112,
  100,  101,    0,  102,    0,  103,  112,  111,    0,    0,
    0,  170,  109,  107,  111,  108,  113,  110,    0,  109,
  107,    0,  108,  113,  110,    0,    0,  112,    0,  154,
  106,    0,  105,    0,    0,  111,    0,  106,  178,  105,
  109,  107,  111,  108,  113,  110,    0,  109,  107,    0,
  108,  113,  110,    0,    0,    0,  193,    0,  106,    0,
  105,  112,    0,    0,  199,  106,    0,  105,  112,   84,
    0,    0,    0,    0,   84,   84,    0,   84,   84,   84,
    0,    0,    0,    0,    0,    0,    0,   98,    0,  112,
   99,    0,   84,   84,   84,    0,  112,    0,    0,    0,
  100,  101,   81,  102,    0,  103,    0,   81,   81,   79,
   81,   81,   81,    0,   79,   79,    0,   79,   79,   79,
    0,    0,    0,   84,    0,   81,   81,   81,    0,    0,
  111,    0,   79,   79,   79,  109,  107,  111,  108,  113,
  110,    0,  109,  107,    0,  108,  113,  110,    0,    0,
    0,    0,    0,  106,    0,  105,   81,    0,    0,    0,
  106,    0,  105,   79,    0,    0,    0,   98,    0,    0,
   99,    0,    0,    0,   98,    0,    0,   99,    0,    0,
  100,  101,    0,  102,  112,  103,    0,  100,  101,    0,
  102,  112,  103,    0,  111,   98,    0,    0,   99,  109,
  107,    0,  108,  113,  110,    0,    0,    0,  100,  101,
    0,  102,    0,  103,    0,    0,    0,  106,    0,  105,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   98,
    0,    0,   99,    0,    0,    0,   98,    0,    0,   99,
    0,   68,  100,  101,   68,  102,    0,  103,  112,  100,
  101,    0,  102,    0,  103,    0,    0,   98,   68,   68,
   99,   68,    0,    0,   98,    0,    0,   99,    0,   73,
  100,  101,   73,  102,    0,  103,    0,  100,  101,    0,
  102,   63,  103,   63,   63,   63,   73,   73,   73,   73,
   73,   84,    0,   68,   84,    0,    0,    0,   63,   63,
   63,   63,   63,    0,   84,   84,    0,   84,   69,   84,
    0,   69,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   73,    0,    0,   81,   69,   69,   81,   69,    0,
    0,   79,    0,   63,   79,    0,    0,   81,   81,    0,
   81,    0,   81,    0,   79,   79,    0,   79,    0,   79,
    0,    0,   98,    0,    0,   99,    0,    0,    0,   98,
   69,    0,    0,    0,   72,  100,  101,   72,  102,    0,
  103,    0,    0,  101,    0,  102,   66,  103,   66,   66,
   66,   72,   72,   72,   72,   72,    0,    0,   70,    0,
    0,   70,    0,   66,   66,   66,   66,   66,    0,    0,
   71,    0,    0,   71,    0,   70,   70,   70,   70,   70,
    0,    0,    0,    0,    0,    0,   72,   71,   71,   71,
   71,   71,    0,    0,    0,    0,    0,    0,   66,    0,
    0,    0,  102,    0,  103,    0,    0,    0,    0,    0,
   70,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   71,    0,    0,    0,    0,    0,    0,   68,
    0,    0,   68,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   68,   68,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   73,    0,    0,
   73,    0,    0,    0,    0,    0,    0,    0,    0,   63,
   73,   73,   63,   73,    0,   73,    0,    0,    0,    0,
    0,    0,   63,   63,    0,   63,    0,   63,    0,    0,
    0,    0,    0,    0,    0,    0,   69,    0,    0,   69,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   69,
   69,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   72,    0,    0,   72,    0,    0,    0,    0,
    0,    0,    0,    0,   66,   72,   72,   66,   72,    0,
   72,    0,    0,    0,    0,    0,   70,   66,   66,   70,
   66,    0,   66,    0,    0,    0,    0,    0,   71,   70,
   70,   71,   70,    0,   70,    0,    0,    0,    0,    0,
    0,   71,   71,   87,   71,   89,   71,   89,    0,    0,
   94,   95,   97,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  116,    0,   89,    0,    0,  122,    0,  124,
    0,    0,    0,    0,  127,  128,  129,  130,  131,  132,
  134,  135,  136,  137,  138,  139,  140,  141,  142,    0,
    0,   89,    0,    0,    0,    0,    0,  147,    0,    0,
    0,  150,    0,    0,    0,    0,    0,    0,    0,  152,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  165,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  174,    0,   89,
   89,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   89,    0,    0,  186,  165,  188,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  197,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   41,   91,   33,   44,   57,  115,   40,  125,   46,   40,
   63,   45,    2,   69,   45,   71,  264,   58,   59,   33,
   61,   13,   14,  264,   58,   59,   40,  151,   44,  123,
  278,   45,  148,  149,  264,   25,  265,   27,   28,   33,
  269,  270,   91,   59,   58,   59,   40,  276,  151,  151,
   42,   45,   93,   91,   40,  179,  172,   33,   48,   44,
   58,   59,   37,   41,   40,  175,   44,   42,   43,   45,
   45,   46,   47,  189,   59,   33,  179,  179,   40,   44,
   58,   59,   40,   61,   44,   52,   76,   45,   44,   56,
   40,  125,   37,   33,   59,  257,   86,   42,   41,   59,
   40,   46,   47,   59,  157,   45,  159,  163,  164,  264,
   41,  125,   33,   44,   93,   93,   91,   41,   44,   40,
   44,   58,   59,  264,   45,   44,   41,   58,   59,  182,
   33,  125,   44,   59,   58,   59,   41,   40,  264,   44,
   59,  194,   45,  133,   58,   59,   91,  265,   33,  125,
  123,  269,  270,   58,   59,   40,   61,   41,  276,  123,
   45,  261,   58,   59,  265,  265,   33,  125,  269,  269,
  270,  123,   40,   40,  264,  276,  276,   40,   45,   40,
   40,  264,   41,  123,   41,   33,   44,   63,   93,   41,
   59,  267,   40,   40,   58,  267,   41,   45,   63,   91,
   91,   91,  123,   59,   33,   41,   41,   41,   41,    2,
   27,   40,   27,   27,   -1,   28,   45,   -1,  179,   -1,
   -1,  262,  125,  257,  258,   -1,  257,  258,   -1,  263,
  264,  272,  266,  264,  268,   33,   -1,  271,  123,   -1,
  274,   -1,   40,  257,  258,  279,   -1,   45,  279,  263,
  264,   -1,  266,   -1,  268,   33,   -1,  271,  125,   33,
  274,   -1,   40,  257,  258,  279,   40,   45,   -1,  263,
  264,   45,  266,   -1,  268,  123,   -1,  271,   -1,   33,
  274,  257,  258,   -1,  262,  279,   40,  263,  264,   -1,
  266,   45,  268,   -1,  272,  271,  125,   33,  274,  257,
  258,   -1,   -1,  279,   40,  263,  264,   -1,  266,   45,
  268,   -1,   -1,  271,   -1,   -1,  274,  257,  258,   -1,
   -1,  279,   -1,  263,  264,   -1,  266,  125,  268,   -1,
   -1,  271,   -1,   -1,  274,   -1,  257,  258,   -1,  279,
   -1,   -1,  263,  264,   -1,  266,   -1,  268,   -1,   -1,
  271,  125,   -1,  274,  257,  258,   -1,  262,  279,   -1,
  263,  264,   -1,  266,   -1,  268,   -1,  272,  271,   -1,
   -1,  274,  257,  258,   -1,   -1,  279,   -1,  263,  264,
   -1,  266,   -1,  268,   -1,   -1,  271,   -1,   -1,  274,
  257,  258,   -1,   -1,  279,   -1,  263,  264,   -1,  266,
   -1,  268,   -1,   -1,  271,   -1,   -1,  274,   -1,  257,
  258,   -1,  279,   -1,   -1,  263,  264,   -1,  266,   -1,
  268,   -1,   -1,  271,   -1,   -1,  274,   -1,  257,  258,
   -1,  279,   -1,   -1,  263,  264,   -1,  266,   -1,  268,
   -1,   -1,  271,   -1,   -1,  274,   -1,   -1,   -1,   -1,
  279,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,
  258,   -1,   -1,   -1,   -1,  263,  264,   -1,  266,   -1,
  268,   -1,   -1,  271,   -1,   -1,  274,   -1,   -1,  257,
  258,  279,   -1,  257,  258,   -1,  264,  265,   -1,  263,
  264,  269,  266,   -1,  268,   -1,   -1,  271,  276,   -1,
  274,  279,   -1,  257,  258,  279,   -1,   -1,   -1,  263,
  264,   -1,  266,   -1,  268,   -1,   -1,  271,   -1,   -1,
  274,  257,  258,   -1,   -1,  279,   37,   -1,  264,   -1,
   41,   42,   43,   44,   45,   46,   47,   -1,   -1,   -1,
   -1,   -1,   -1,  279,   -1,   -1,   -1,   58,   59,   60,
   61,   62,   37,   -1,   -1,   -1,   41,   42,   43,   44,
   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   58,   59,   60,   61,   62,   -1,   37,
   91,   -1,   93,   41,   42,   43,   44,   45,   37,   47,
   -1,   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,
   58,   59,   60,   61,   62,   -1,   -1,   -1,   93,   58,
   59,   60,   61,   62,   -1,   37,   -1,   -1,   -1,   41,
   42,   43,   44,   45,   37,   47,   -1,   -1,   41,   42,
   43,   44,   45,   -1,   47,   93,   58,   59,   60,   61,
   62,   -1,   -1,   -1,   93,   58,   59,   60,   61,   62,
   -1,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   93,   58,   59,   60,   61,   62,   37,   -1,   -1,
   93,   -1,   42,   43,   37,   45,   46,   47,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   58,   59,
   60,   -1,   62,   -1,   -1,   91,   -1,   60,   61,   62,
   -1,   37,   -1,   -1,   -1,   -1,   42,   43,   37,   45,
   46,   47,   41,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   91,   -1,   59,   60,   -1,   62,   -1,   91,   -1,
   -1,   60,   -1,   62,   -1,   -1,   -1,   -1,  259,   -1,
   -1,  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  272,  273,   -1,  275,   91,  277,   -1,   -1,   -1,
   -1,   -1,   91,   -1,  259,   37,   -1,  262,   -1,   41,
   42,   43,   -1,   45,   46,   47,   -1,  272,  273,   -1,
  275,   -1,  277,   -1,   -1,   -1,   -1,   -1,   60,   -1,
   62,  259,   -1,   -1,  262,   -1,   -1,   -1,   -1,   -1,
  259,   -1,   -1,  262,  272,  273,   -1,  275,   -1,  277,
   -1,   -1,   -1,  272,  273,   -1,  275,   -1,  277,   91,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,
  262,   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,
  272,  273,   -1,  275,   -1,  277,   -1,   -1,   -1,  272,
  273,   -1,  275,   -1,  277,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  259,   -1,   37,  262,   -1,   -1,   41,
   42,   43,   -1,   45,   46,   47,  272,  273,   -1,  275,
   -1,  277,   -1,   -1,   -1,   -1,   -1,   -1,   60,  259,
   62,   -1,  262,   -1,   -1,   -1,  259,   -1,   -1,  262,
   -1,   -1,  272,  273,   -1,  275,   -1,  277,   -1,  272,
  273,   -1,  275,   -1,  277,   -1,   -1,   -1,   -1,   91,
   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,   -1,
  259,   -1,   -1,  262,   -1,   -1,  272,  273,   -1,  275,
   -1,  277,   -1,  272,  273,   37,  275,   -1,  277,   41,
   42,   43,   37,   45,   46,   47,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   60,   -1,
   62,   -1,   -1,   37,   59,   60,   -1,   62,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,  259,   -1,   -1,
  262,   -1,   -1,   -1,   -1,   -1,   60,   -1,   62,   91,
  272,  273,   -1,  275,   -1,  277,   91,   37,   -1,   -1,
   -1,   41,   42,   43,   37,   45,   46,   47,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   91,   -1,   93,
   60,   -1,   62,   -1,   -1,   37,   -1,   60,   61,   62,
   42,   43,   37,   45,   46,   47,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   58,   -1,   60,   -1,
   62,   91,   -1,   -1,   59,   60,   -1,   62,   91,   37,
   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,   -1,   91,
  262,   -1,   60,   61,   62,   -1,   91,   -1,   -1,   -1,
  272,  273,   37,  275,   -1,  277,   -1,   42,   43,   37,
   45,   46,   47,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   91,   -1,   60,   61,   62,   -1,   -1,
   37,   -1,   60,   61,   62,   42,   43,   37,   45,   46,
   47,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,   60,   -1,   62,   91,   -1,   -1,   -1,
   60,   -1,   62,   91,   -1,   -1,   -1,  259,   -1,   -1,
  262,   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,
  272,  273,   -1,  275,   91,  277,   -1,  272,  273,   -1,
  275,   91,  277,   -1,   37,  259,   -1,   -1,  262,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,  272,  273,
   -1,  275,   -1,  277,   -1,   -1,   -1,   60,   -1,   62,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,
   -1,   -1,  262,   -1,   -1,   -1,  259,   -1,   -1,  262,
   -1,   41,  272,  273,   44,  275,   -1,  277,   91,  272,
  273,   -1,  275,   -1,  277,   -1,   -1,  259,   58,   59,
  262,   61,   -1,   -1,  259,   -1,   -1,  262,   -1,   41,
  272,  273,   44,  275,   -1,  277,   -1,  272,  273,   -1,
  275,   41,  277,   43,   44,   45,   58,   59,   60,   61,
   62,  259,   -1,   93,  262,   -1,   -1,   -1,   58,   59,
   60,   61,   62,   -1,  272,  273,   -1,  275,   41,  277,
   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   93,   -1,   -1,  259,   58,   59,  262,   61,   -1,
   -1,  259,   -1,   93,  262,   -1,   -1,  272,  273,   -1,
  275,   -1,  277,   -1,  272,  273,   -1,  275,   -1,  277,
   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,   -1,  259,
   93,   -1,   -1,   -1,   41,  272,  273,   44,  275,   -1,
  277,   -1,   -1,  273,   -1,  275,   41,  277,   43,   44,
   45,   58,   59,   60,   61,   62,   -1,   -1,   41,   -1,
   -1,   44,   -1,   58,   59,   60,   61,   62,   -1,   -1,
   41,   -1,   -1,   44,   -1,   58,   59,   60,   61,   62,
   -1,   -1,   -1,   -1,   -1,   -1,   93,   58,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,
   -1,   -1,  275,   -1,  277,   -1,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,  259,
   -1,   -1,  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  272,  273,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,
  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,
  272,  273,  262,  275,   -1,  277,   -1,   -1,   -1,   -1,
   -1,   -1,  272,  273,   -1,  275,   -1,  277,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,
  273,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  259,  272,  273,  262,  275,   -1,
  277,   -1,   -1,   -1,   -1,   -1,  259,  272,  273,  262,
  275,   -1,  277,   -1,   -1,   -1,   -1,   -1,  259,  272,
  273,  262,  275,   -1,  277,   -1,   -1,   -1,   -1,   -1,
   -1,  272,  273,   67,  275,   69,  277,   71,   -1,   -1,
   74,   75,   76,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   86,   -1,   88,   -1,   -1,   91,   -1,   93,
   -1,   -1,   -1,   -1,   98,   99,  100,  101,  102,  103,
  104,  105,  106,  107,  108,  109,  110,  111,  112,   -1,
   -1,  115,   -1,   -1,   -1,   -1,   -1,  121,   -1,   -1,
   -1,  125,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  133,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  151,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  161,   -1,  163,
  164,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  175,   -1,   -1,  178,  179,  180,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  193,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=284;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'","'?'",null,null,null,null,null,null,null,null,null,null,
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
"statement : ternario",
"statement_sin : RETURN exp",
"statement_sin : READ list_exp",
"statement_sin : WRITE list_exp",
"statement_sin : if_else",
"statement_sin : while",
"statement_sin : exp '=' exp",
"statement_sin : ID '(' opt_list_exp ')'",
"statement_sin : ternario",
"ternario : '(' exp ')' '?' statement_sin ':' statement_sin ';'",
"ternario : exp '=' '(' exp ')' '?' exp ':' exp ';'",
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

//#line 241 "../../src/parser/parser.y"

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

//#line 828 "Parser.java"
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
//#line 96 "../../src/parser/parser.y"
{ ((List<Definition>)val_peek(1)).add((Definition)val_peek(0)); this.root = new Program(scanner.getLine(), scanner.getColumn(), (List<Definition>)val_peek(1)); }
break;
case 2:
//#line 99 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 3:
//#line 100 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 4:
//#line 103 "../../src/parser/parser.y"
{ yyval = val_peek(1); mergeDefs((List<Definition>)yyval, val_peek(0)); }
break;
case 5:
//#line 104 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 6:
//#line 107 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); addVarDefs((List<Definition>)yyval, (Type)val_peek(2), (List<String>)val_peek(1), scanner.getLine()); }
break;
case 7:
//#line 108 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); addVarDefs((List<Definition>)yyval, (Type)val_peek(2), (List<String>)val_peek(1), scanner.getLine()); }
break;
case 8:
//#line 109 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); addVarDefs((List<Definition>)yyval, (StructType)val_peek(2), (List<String>)val_peek(1), scanner.getLine());}
break;
case 9:
//#line 113 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 10:
//#line 116 "../../src/parser/parser.y"
{ yyval = val_peek(1); mergeDefs((List<Definition>)yyval, val_peek(0)); }
break;
case 11:
//#line 117 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 12:
//#line 120 "../../src/parser/parser.y"
{ yyval = val_peek(3); addFieldDefs((List<Definition>)yyval, (Type)val_peek(2), (List<String>)val_peek(1), scanner.getLine()); }
break;
case 13:
//#line 121 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 14:
//#line 125 "../../src/parser/parser.y"
{ yyval = new StructType(scanner.getLine(), scanner.getColumn(), (List<Definition>)val_peek(1));}
break;
case 15:
//#line 128 "../../src/parser/parser.y"
{ yyval = new DefFunc(scanner.getLine(), scanner.getColumn(),(Type) new FuncType(scanner.getLine(), scanner.getColumn(), (Type)VoidType.getInstance(), (List<Definition>) new ArrayList()), "main", (List<Definition>)val_peek(2), (List<Statement>)val_peek(1)); }
break;
case 16:
//#line 131 "../../src/parser/parser.y"
{ yyval = new DefFunc(scanner.getLine(), scanner.getColumn(), (Type)new FuncType(scanner.getLine(), scanner.getColumn(), (Type)val_peek(8), (List<Definition>)val_peek(5)), (String)val_peek(7), (List<Definition>)val_peek(2), (List<Statement>)val_peek(1)); }
break;
case 17:
//#line 132 "../../src/parser/parser.y"
{ yyval = new DefFunc(scanner.getLine(), scanner.getColumn(),(Type) new FuncType(scanner.getLine(), scanner.getColumn(), (Type)VoidType.getInstance(), (List<Definition>)val_peek(5)), (String)val_peek(7), (List<Definition>)val_peek(2), (List<Statement>)val_peek(1)); }
break;
case 18:
//#line 137 "../../src/parser/parser.y"
{ ((List<Definition>)yyval).add(new DefVar(scanner.getLine(), scanner.getColumn(), (Type)val_peek(1), (String)val_peek(0))); yyval = val_peek(3); }
break;
case 19:
//#line 138 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>();((List<Definition>)yyval).add(new DefVar(scanner.getLine(), scanner.getColumn(), (Type)val_peek(1), (String)val_peek(0))); }
break;
case 20:
//#line 141 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 21:
//#line 142 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 22:
//#line 145 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 23:
//#line 146 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 24:
//#line 147 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 25:
//#line 150 "../../src/parser/parser.y"
{ yyval = getArrayDef((Type)val_peek(3), (Integer)val_peek(1), scanner.getLine()); }
break;
case 26:
//#line 153 "../../src/parser/parser.y"
{ yyval =val_peek(0);}
break;
case 27:
//#line 154 "../../src/parser/parser.y"
{ yyval =val_peek(0);}
break;
case 28:
//#line 155 "../../src/parser/parser.y"
{ yyval =val_peek(0);}
break;
case 29:
//#line 158 "../../src/parser/parser.y"
{ ((List<String>)yyval).add((String)val_peek(0)); yyval = val_peek(2);  }
break;
case 30:
//#line 159 "../../src/parser/parser.y"
{ yyval = new ArrayList<String>(); ((List<String>)yyval).add((String)val_peek(0)); }
break;
case 31:
//#line 162 "../../src/parser/parser.y"
{ ((List<Statement>)yyval).add((Statement)val_peek(0)); yyval = val_peek(1); }
break;
case 32:
//#line 163 "../../src/parser/parser.y"
{ yyval = new ArrayList<Statement>(); }
break;
case 33:
//#line 166 "../../src/parser/parser.y"
{ yyval = new Return(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(1)); }
break;
case 34:
//#line 167 "../../src/parser/parser.y"
{ yyval = new Read(scanner.getLine(), scanner.getColumn(), (List<Expression>)val_peek(1)); }
break;
case 35:
//#line 168 "../../src/parser/parser.y"
{ yyval = new Write(scanner.getLine(), scanner.getColumn(), (List<Expression>)val_peek(1)); }
break;
case 36:
//#line 169 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 37:
//#line 170 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 38:
//#line 171 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1)); }
break;
case 39:
//#line 172 "../../src/parser/parser.y"
{ yyval = new InvocationSt(scanner.getLine(), scanner.getColumn(), (String)val_peek(4), (List<Expression>)val_peek(2)); }
break;
case 40:
//#line 174 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 41:
//#line 177 "../../src/parser/parser.y"
{ yyval = new Return(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 42:
//#line 178 "../../src/parser/parser.y"
{ yyval = new Read(scanner.getLine(), scanner.getColumn(), (List<Expression>)val_peek(0)); }
break;
case 43:
//#line 179 "../../src/parser/parser.y"
{ yyval = new Write(scanner.getLine(), scanner.getColumn(), (List<Expression>)val_peek(0)); }
break;
case 44:
//#line 180 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 45:
//#line 181 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 46:
//#line 182 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 47:
//#line 183 "../../src/parser/parser.y"
{ yyval = new InvocationSt(scanner.getLine(), scanner.getColumn(), (String)val_peek(3), (List<Expression>)val_peek(1)); }
break;
case 48:
//#line 185 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 49:
//#line 188 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(6), (Statement)val_peek(3), (Statement)val_peek(1));}
break;
case 50:
//#line 189 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(6), (Statement)(new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(9), (Expression)val_peek(3))),(Statement)(new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(9), (Expression)val_peek(1))));}
break;
case 51:
//#line 194 "../../src/parser/parser.y"
{ yyval = new While(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (List<Statement>)val_peek(1)); }
break;
case 52:
//#line 195 "../../src/parser/parser.y"
{ yyval = new While(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Statement)val_peek(0)); }
break;
case 53:
//#line 198 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(8), (List<Statement>)val_peek(5), (List<Statement>)val_peek(1)); }
break;
case 54:
//#line 199 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(6), (List<Statement>)val_peek(3), (Statement)val_peek(0)); }
break;
case 55:
//#line 200 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(6), (Statement)val_peek(4), (List<Statement>)val_peek(1)); }
break;
case 56:
//#line 201 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (Statement)val_peek(2), (Statement)val_peek(0)); }
break;
case 57:
//#line 202 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (List<Statement>)val_peek(1), new ArrayList()); }
break;
case 58:
//#line 203 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Statement)val_peek(0), new ArrayList()); }
break;
case 59:
//#line 206 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 60:
//#line 207 "../../src/parser/parser.y"
{ yyval = new ArrayList<Expression>(); }
break;
case 61:
//#line 210 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List<Expression>)yyval).add((Expression)val_peek(0)); }
break;
case 62:
//#line 211 "../../src/parser/parser.y"
{ yyval = new ArrayList<Expression>(); ((List<Expression>)yyval).add((Expression)val_peek(0)); }
break;
case 63:
//#line 214 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "+"); }
break;
case 64:
//#line 215 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "*"); }
break;
case 65:
//#line 216 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "/"); }
break;
case 66:
//#line 217 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "-"); }
break;
case 67:
//#line 218 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "%"); }
break;
case 68:
//#line 219 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "=="); }
break;
case 69:
//#line 220 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "!="); }
break;
case 70:
//#line 221 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), ">"); }
break;
case 71:
//#line 222 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "<"); }
break;
case 72:
//#line 223 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "<="); }
break;
case 73:
//#line 224 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), ">="); }
break;
case 74:
//#line 225 "../../src/parser/parser.y"
{ yyval = new LogicOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "&&"); }
break;
case 75:
//#line 226 "../../src/parser/parser.y"
{ yyval = new LogicOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "||"); }
break;
case 76:
//#line 227 "../../src/parser/parser.y"
{ yyval = new Cast(scanner.getLine(), scanner.getColumn(), (Type)val_peek(2), (Expression)val_peek(0)); }
break;
case 77:
//#line 228 "../../src/parser/parser.y"
{ yyval = new ArrayAccess(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1)); }
break;
case 78:
//#line 229 "../../src/parser/parser.y"
{ yyval = new StructAccess(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(0)); }
break;
case 79:
//#line 230 "../../src/parser/parser.y"
{ yyval = new InvocationExp(scanner.getLine(), scanner.getColumn(), (String)val_peek(3), (List<Expression>)val_peek(1)); }
break;
case 80:
//#line 231 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 81:
//#line 232 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 82:
//#line 233 "../../src/parser/parser.y"
{ yyval = new UnaryNot(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 83:
//#line 234 "../../src/parser/parser.y"
{ yyval = new LiteralInt(scanner.getLine(), scanner.getColumn(), (Integer)val_peek(0)); }
break;
case 84:
//#line 235 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 85:
//#line 236 "../../src/parser/parser.y"
{ yyval = new LiteralChar(scanner.getLine(), scanner.getColumn(), (Character)val_peek(0)); }
break;
case 86:
//#line 237 "../../src/parser/parser.y"
{ yyval = new LiteralReal(scanner.getLine(), scanner.getColumn(), (Double)val_peek(0)); }
break;
//#line 1321 "Parser.java"
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
