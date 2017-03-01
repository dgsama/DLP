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
//#line 60 "Parser.java"




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
public final static short LESSTHANELSE=280;
public final static short UNARY_MINUS=281;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    3,    3,    1,    1,    4,    4,   10,   10,
   11,   11,    5,    2,    6,    6,   14,   14,   13,   13,
    7,    7,    7,    9,   15,   15,    8,    8,   12,   12,
   16,   16,   16,   16,   16,   16,   16,   20,   20,   19,
   19,   19,   19,   19,   19,   21,   21,   18,   18,   17,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   17,   17,
};
final static short yylen[] = {                            2,
    2,    1,    1,    1,    2,    0,    3,    3,    1,    1,
    2,    0,    6,    8,    9,    9,    4,    2,    1,    0,
    1,    1,    1,    4,    1,    1,    3,    1,    2,    0,
    3,    5,    3,    1,    1,    4,    5,    7,    5,   11,
    9,    9,    7,    7,    5,    1,    0,    3,    1,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    4,    4,    3,    4,    2,    3,    2,    1,
    1,    1,    1,
};
final static short yydefred[] = {                         6,
    0,    0,    0,   22,   23,    0,   21,    1,    5,    2,
    3,    4,    0,    0,    0,    0,    0,   12,    0,    0,
   28,    0,    0,    0,    0,    0,    0,    7,    0,    8,
    0,    0,    0,    0,    0,    0,    9,   10,    0,   11,
    0,   27,   24,   18,    0,    0,   12,    0,    0,   12,
    0,    0,   13,   12,    0,   17,    0,    0,    0,   70,
   73,    0,    0,    0,    0,    0,    0,   72,    0,    0,
    0,   14,   29,    0,   34,   35,    0,   16,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   15,    0,   31,    0,
    0,   33,    0,    0,    0,    0,    0,   68,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   65,    0,    0,    0,    0,    0,    0,
   63,   36,   64,   66,   37,   30,    0,   32,   30,   39,
    0,    0,    0,    0,   30,   43,   38,    0,    0,   30,
   41,   42,    0,   40,
};
final static short yydgoto[] = {                          1,
    2,    8,    9,   37,   38,   12,   39,   20,   14,   40,
   26,   57,   33,   34,   15,   73,   74,  110,   75,   76,
  111,
};
final static short yysindex[] = {                         0,
    0, -152, -256,    0,    0, -119,    0,    0,    0,    0,
    0,    0, -255, -247,  -70,  -13,    4,    0,   11,  -10,
    0,   12, -205, -230,   34, -120, -230,    0, -188,    0,
    1, -181,   52,   51,  -25, -247,    0,    0, -247,    0,
   60,    0,    0,    0,  -19, -230,    0,   14,  -18,    0,
 -158,  -89,    0,    0,  -89,    0,  -33,  -89,  -15,    0,
    0,  261,   67,  261,   71,   79,   81,    0,  261,  261,
  207,    0,    0,  582,    0,    0,    3,    0,   82,  608,
  261,  800,   15,  261,  261,  261,   -5,  -44,   84,  448,
  261,  261,  261,  261,  261,  261,  261,  261,  261,  261,
  261,  261,  261,  261,  261, -141,    0,  261,    0,   83,
   88,    0,  261,  615,   47,  641,  261,    0,  124,   -5,
   -5,  124,   94,   94,  671,   94,   94,  -27,  -27,  -44,
  -44,  -44,  704,    0,   89,   73,  800,   21,   76,   39,
    0,    0,    0,    0,    0,    0, -133,    0,    0,    0,
   57,   75,   93, -125,    0,    0,    0,  113,  132,    0,
    0,    0,  150,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   56,   61,    0,    0,    0,    0,   18,    0,
    0,    0,    0,  102,    0,    0,  102,    0,    0,    0,
    0,    0,    0,  110,    0,    0,    0,    0,   56,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  168,    0,    0,  168,    0,    0,  168,    0,    0,
    0,    0,  730,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  486,    0,
  114,  -30,    0,    0,    0,    0,  -28,  512,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  114,    0,  116,
    0,    0,    0,    0,    0,    0,    0,    0,  361,   19,
   55,  834,  635,  860,    0,  890,  950,  695,  791,  521,
  547,  556,    0,    0,    0,  765,    9,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  187,    0,    0,    0,
    0,    0,    0,  243,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  152,  157,    0,   -1,   -8,    0,    0,
   35,   45,  133,    0,    0,  -71, 1089,  -61,    0,    0,
   65,
};
final static int YYTABLESIZE=1227;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         69,
   13,  106,   83,   18,   36,   22,   71,   16,   19,  104,
   49,   70,   69,   49,  102,   69,   21,   69,  106,  103,
   23,   17,   32,  115,   71,   32,   24,   48,   49,   70,
   69,  104,   69,   29,    4,   69,  102,  100,    5,  101,
  106,  103,   71,   25,   51,    7,  105,   70,   28,   48,
   27,   31,   48,   69,   99,   29,   98,   29,  113,   62,
   71,   28,   62,  105,   69,   70,  147,   48,  150,   89,
   30,   69,   53,  112,   35,   42,   28,   62,   71,   62,
  156,   52,   44,   70,   55,  105,  161,  139,   58,   69,
  113,   72,   45,   43,   46,   61,   71,   47,   61,   59,
   49,   70,   77,   50,   54,   56,   81,   69,    3,   78,
   84,   62,    4,   61,   71,   61,    5,    6,   85,   70,
   86,  108,  134,    7,  117,   69,  113,  107,  136,  144,
  104,  145,   71,  152,  148,  102,  100,   70,  101,  106,
  103,  158,   20,  146,    4,   69,   25,   61,    5,    6,
   19,   26,   71,   10,   47,    7,   46,   70,   11,   41,
  104,  149,    0,    0,   69,  102,  100,    0,  101,  106,
  103,   71,  135,    0,    0,    4,   70,    0,    0,    5,
    6,  154,   69,   99,  105,   98,    7,    0,    0,   71,
  151,    0,    0,  153,   70,    0,    0,  155,    0,  159,
   30,    0,    0,    0,  163,    0,    0,   30,    0,    0,
    0,    0,   30,    0,  105,    0,    0,  157,    0,   45,
    0,    0,    0,   60,   61,    0,   45,    0,    0,   62,
   63,   45,   64,   69,   65,  160,    0,   66,    0,   69,
   67,   60,   61,   69,    0,   68,   71,   62,   63,    0,
   64,   70,   65,   91,    0,   66,  162,    0,   67,   60,
   61,    0,    0,   68,    0,   62,   63,   94,   64,   95,
   65,   96,    0,   66,  164,   44,   67,   60,   61,    0,
   62,   68,   44,   62,   63,    0,   64,   44,   65,    0,
   62,   66,   30,   69,   67,   60,   61,    0,    0,   68,
   71,   62,   63,    0,   64,   70,   65,    0,    0,   66,
    0,   45,   67,   60,   61,    0,   61,   68,    0,   62,
   63,    0,   64,    0,   65,    0,   61,   66,    0,    0,
   67,   60,   61,    0,    0,   68,    0,   62,   63,    0,
   64,    0,   65,    0,    0,   66,    0,    0,   67,   60,
   61,    0,    0,   68,    0,   62,   63,    0,   64,    0,
   65,    0,    0,   66,    0,    0,   67,   44,    0,   60,
   61,   68,    0,    0,    0,   62,   63,    0,   64,    0,
   65,    0,    0,   66,    0,    0,   67,    0,   60,   61,
    0,   68,    0,    0,   62,   63,    0,   64,   95,   65,
   96,   55,   66,    0,   55,   67,   60,   61,    0,    0,
   68,    0,   62,   63,    0,   64,    0,   65,    0,   55,
   66,   55,    0,   67,   30,   30,    0,    0,   68,    0,
   30,   30,    0,   30,    0,   30,    0,    0,   30,    0,
    0,   30,    0,   45,   45,    0,   30,    0,    0,   45,
   45,    0,   45,   55,   45,    0,    0,   45,    0,    0,
   45,    0,    0,   60,   61,   45,    0,    0,    0,    0,
   79,    4,    0,    0,    0,    5,    0,    0,    0,    0,
    0,    0,    7,    0,  104,   68,    0,    0,  118,  102,
  100,    0,  101,  106,  103,    0,    0,    0,    0,   44,
   44,    0,    0,    0,    0,   44,   44,   99,   44,   98,
   44,    0,    0,   44,    0,    0,   44,   60,   61,    0,
    0,   44,   71,    0,   79,    0,   71,   71,   71,   71,
   71,   71,   71,    0,    0,    0,    0,    0,  105,   68,
    0,    0,    0,    0,   71,   71,   71,   71,   67,    0,
    0,    0,   67,   67,   67,   67,   67,   51,   67,    0,
    0,   51,   51,   51,   51,   51,    0,   51,    0,    0,
   67,   67,   67,   67,    0,    0,   71,    0,   71,   51,
   51,   51,   51,   52,    0,    0,    0,   52,   52,   52,
   52,   52,   54,   52,    0,    0,   54,   54,   54,   54,
   54,    0,   54,    0,   67,   52,   52,   52,   52,    0,
    0,    0,    0,   51,   54,   54,   54,   54,  104,   55,
    0,    0,   55,  102,  100,    0,  101,  106,  103,    0,
    0,    0,   55,   55,    0,    0,    0,    0,    0,   52,
    0,   99,   97,   98,  104,    0,    0,    0,   54,  102,
  100,  104,  101,  106,  103,  138,  102,  100,    0,  101,
  106,  103,    0,    0,    0,    0,  109,   99,    0,   98,
    0,    0,  105,    0,   99,   60,   98,  104,   60,    0,
    0,  140,  102,  100,    0,  101,  106,  103,    0,    0,
    0,    0,    0,   60,   60,   60,   60,    0,  105,    0,
   99,    0,   98,    0,    0,  105,   91,  104,    0,   92,
    0,    0,  102,  100,    0,  101,  106,  103,    0,   93,
   94,    0,   95,    0,   96,    0,    0,   60,    0,  142,
   99,  105,   98,    0,    0,   50,    0,   50,   50,   50,
  104,    0,    0,    0,   71,  102,  100,   71,  101,  106,
  103,    0,    0,   50,   50,   50,   50,   71,   71,    0,
   71,  105,   71,   99,    0,   98,   71,    0,    0,    0,
   67,   71,   71,   67,   71,   71,   71,    0,    0,   51,
    0,    0,   51,   67,   67,    0,   67,   50,   67,   71,
   71,   71,   51,   51,  105,   51,  143,   51,    0,    0,
    0,   66,    0,    0,    0,   52,   66,   66,   52,   66,
   66,   66,    0,    0,   54,    0,    0,   54,   52,   52,
   71,   52,    0,   52,   66,   66,   66,   54,   54,    0,
   54,   53,   54,   53,   53,   53,  104,    0,    0,    0,
   91,  102,  100,   92,  101,  106,  103,    0,    0,   53,
   53,   53,   53,   93,   94,   66,   95,    0,   96,   99,
    0,   98,    0,    0,    0,    0,   91,    0,    0,   92,
    0,    0,    0,   91,   56,    0,   92,   56,    0,   93,
   94,    0,   95,   53,   96,    0,   93,   94,    0,   95,
  105,   96,   56,   60,   56,    0,   60,    0,    0,   91,
   59,    0,   92,   59,    0,    0,   60,   60,    0,   60,
    0,   60,   93,   94,    0,   95,    0,   96,   59,   59,
   59,   59,    0,    0,    0,    0,   56,    0,    0,   91,
   57,    0,   92,   57,    0,    0,    0,    0,    0,    0,
    0,    0,   93,   94,    0,   95,    0,   96,   57,   57,
   57,   57,   59,   50,    0,    0,   50,    0,    0,    0,
    0,    0,   91,    0,    0,   92,   50,   50,    0,   50,
    0,   50,    0,    0,    0,   93,   94,    0,   95,    0,
   96,    0,   57,    0,    0,    0,    0,    0,   71,    0,
   58,   71,    0,   58,    0,    0,    0,    0,    0,    0,
    0,   71,   71,    0,   71,    0,   71,    0,   58,   58,
   58,   58,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   66,    0,    0,   66,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   66,   66,    0,   66,
    0,   66,   58,    0,    0,    0,    0,    0,    0,   53,
    0,    0,   53,    0,    0,    0,    0,    0,   91,    0,
    0,   92,   53,   53,    0,   53,    0,   53,    0,    0,
    0,   93,   94,    0,   95,    0,   96,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   56,    0,    0,   56,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   56,   56,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   59,    0,
    0,   59,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   59,   59,    0,   59,    0,   59,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   57,    0,
   80,   57,   82,    0,    0,    0,    0,   87,   88,   90,
    0,   57,   57,    0,   57,    0,   57,    0,    0,   82,
    0,    0,  114,   82,  116,    0,    0,    0,    0,  119,
  120,  121,  122,  123,  124,  125,  126,  127,  128,  129,
  130,  131,  132,  133,    0,    0,   82,    0,    0,    0,
    0,  137,    0,    0,    0,  141,    0,    0,   58,    0,
    0,   58,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   58,   58,    0,   58,    0,   58,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
    2,   46,   64,  123,  125,   14,   40,  264,  264,   37,
   41,   45,   41,   44,   42,   44,  264,   33,   46,   47,
   91,  278,   24,   85,   40,   27,   40,   36,   59,   45,
   59,   37,   61,   44,  265,   33,   42,   43,  269,   45,
   46,   47,   40,   40,   46,  276,   91,   45,   59,   41,
   40,  257,   44,   33,   60,   44,   62,   44,   44,   41,
   40,   44,   44,   91,   93,   45,  138,   59,  140,   71,
   59,   33,   59,   59,   41,  264,   59,   59,   40,   61,
  152,   47,  264,   45,   50,   91,  158,   41,   54,   33,
   44,  125,   41,   93,   44,   41,   40,  123,   44,   55,
   41,   45,   58,  123,  123,  264,   40,   33,  261,  125,
   40,   93,  265,   59,   40,   61,  269,  270,   40,   45,
   40,   40,  264,  276,   41,   33,   44,  125,   41,   41,
   37,   59,   40,  267,   59,   42,   43,   45,   45,   46,
   47,  267,   41,  123,  265,   33,   91,   93,  269,  270,
   41,   91,   40,    2,   41,  276,   41,   45,    2,   27,
   37,  123,   -1,   -1,   33,   42,   43,   -1,   45,   46,
   47,   40,  108,   -1,   -1,  265,   45,   -1,   -1,  269,
  270,  125,   33,   60,   91,   62,  276,   -1,   -1,   40,
  146,   -1,   -1,  149,   45,   -1,   -1,  123,   -1,  155,
   33,   -1,   -1,   -1,  160,   -1,   -1,   40,   -1,   -1,
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
  277,   41,  271,   -1,   44,  274,  257,  258,   -1,   -1,
  279,   -1,  263,  264,   -1,  266,   -1,  268,   -1,   59,
  271,   61,   -1,  274,  257,  258,   -1,   -1,  279,   -1,
  263,  264,   -1,  266,   -1,  268,   -1,   -1,  271,   -1,
   -1,  274,   -1,  257,  258,   -1,  279,   -1,   -1,  263,
  264,   -1,  266,   93,  268,   -1,   -1,  271,   -1,   -1,
  274,   -1,   -1,  257,  258,  279,   -1,   -1,   -1,   -1,
  264,  265,   -1,   -1,   -1,  269,   -1,   -1,   -1,   -1,
   -1,   -1,  276,   -1,   37,  279,   -1,   -1,   41,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,  257,
  258,   -1,   -1,   -1,   -1,  263,  264,   60,  266,   62,
  268,   -1,   -1,  271,   -1,   -1,  274,  257,  258,   -1,
   -1,  279,   37,   -1,  264,   -1,   41,   42,   43,   44,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   91,  279,
   -1,   -1,   -1,   -1,   59,   60,   61,   62,   37,   -1,
   -1,   -1,   41,   42,   43,   44,   45,   37,   47,   -1,
   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,   -1,
   59,   60,   61,   62,   -1,   -1,   91,   -1,   93,   59,
   60,   61,   62,   37,   -1,   -1,   -1,   41,   42,   43,
   44,   45,   37,   47,   -1,   -1,   41,   42,   43,   44,
   45,   -1,   47,   -1,   93,   59,   60,   61,   62,   -1,
   -1,   -1,   -1,   93,   59,   60,   61,   62,   37,  259,
   -1,   -1,  262,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,  272,  273,   -1,   -1,   -1,   -1,   -1,   93,
   -1,   60,   61,   62,   37,   -1,   -1,   -1,   93,   42,
   43,   37,   45,   46,   47,   41,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,   59,   60,   -1,   62,
   -1,   -1,   91,   -1,   60,   41,   62,   37,   44,   -1,
   -1,   41,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,   59,   60,   61,   62,   -1,   91,   -1,
   60,   -1,   62,   -1,   -1,   91,  259,   37,   -1,  262,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,  272,
  273,   -1,  275,   -1,  277,   -1,   -1,   93,   -1,   59,
   60,   91,   62,   -1,   -1,   41,   -1,   43,   44,   45,
   37,   -1,   -1,   -1,  259,   42,   43,  262,   45,   46,
   47,   -1,   -1,   59,   60,   61,   62,  272,  273,   -1,
  275,   91,  277,   60,   -1,   62,   37,   -1,   -1,   -1,
  259,   42,   43,  262,   45,   46,   47,   -1,   -1,  259,
   -1,   -1,  262,  272,  273,   -1,  275,   93,  277,   60,
   61,   62,  272,  273,   91,  275,   93,  277,   -1,   -1,
   -1,   37,   -1,   -1,   -1,  259,   42,   43,  262,   45,
   46,   47,   -1,   -1,  259,   -1,   -1,  262,  272,  273,
   91,  275,   -1,  277,   60,   61,   62,  272,  273,   -1,
  275,   41,  277,   43,   44,   45,   37,   -1,   -1,   -1,
  259,   42,   43,  262,   45,   46,   47,   -1,   -1,   59,
   60,   61,   62,  272,  273,   91,  275,   -1,  277,   60,
   -1,   62,   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,
   -1,   -1,   -1,  259,   41,   -1,  262,   44,   -1,  272,
  273,   -1,  275,   93,  277,   -1,  272,  273,   -1,  275,
   91,  277,   59,  259,   61,   -1,  262,   -1,   -1,  259,
   41,   -1,  262,   44,   -1,   -1,  272,  273,   -1,  275,
   -1,  277,  272,  273,   -1,  275,   -1,  277,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   93,   -1,   -1,  259,
   41,   -1,  262,   44,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  272,  273,   -1,  275,   -1,  277,   59,   60,
   61,   62,   93,  259,   -1,   -1,  262,   -1,   -1,   -1,
   -1,   -1,  259,   -1,   -1,  262,  272,  273,   -1,  275,
   -1,  277,   -1,   -1,   -1,  272,  273,   -1,  275,   -1,
  277,   -1,   93,   -1,   -1,   -1,   -1,   -1,  259,   -1,
   41,  262,   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  272,  273,   -1,  275,   -1,  277,   -1,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,   -1,  275,
   -1,  277,   93,   -1,   -1,   -1,   -1,   -1,   -1,  259,
   -1,   -1,  262,   -1,   -1,   -1,   -1,   -1,  259,   -1,
   -1,  262,  272,  273,   -1,  275,   -1,  277,   -1,   -1,
   -1,  272,  273,   -1,  275,   -1,  277,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  272,  273,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,   -1,
   -1,  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  272,  273,   -1,  275,   -1,  277,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,   -1,
   62,  262,   64,   -1,   -1,   -1,   -1,   69,   70,   71,
   -1,  272,  273,   -1,  275,   -1,  277,   -1,   -1,   81,
   -1,   -1,   84,   85,   86,   -1,   -1,   -1,   -1,   91,
   92,   93,   94,   95,   96,   97,   98,   99,  100,  101,
  102,  103,  104,  105,   -1,   -1,  108,   -1,   -1,   -1,
   -1,  113,   -1,   -1,   -1,  117,   -1,   -1,  259,   -1,
   -1,  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  272,  273,   -1,  275,   -1,  277,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=281;
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
"CHAR_CONSTANT","LESSTHANELSE","UNARY_MINUS",
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
"struct_def : STRUCT '{' opt_list_local_var '}' list_ident ';'",
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
"statement : READ '(' list_exp ')' ';'",
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

//#line 217 "../../src/parser/parser.y"

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

//#line 692 "Parser.java"
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
//#line 92 "../../src/parser/parser.y"
{ ((List<Definition>)val_peek(1)).add((Definition)val_peek(0)); this.root = new Program(scanner.getLine(), scanner.getColumn(), (List<Definition>)val_peek(1)); }
break;
case 2:
//#line 95 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 3:
//#line 96 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 4:
//#line 97 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 5:
//#line 100 "../../src/parser/parser.y"
{ yyval = val_peek(1); mergeDefs((List<Definition>)yyval, val_peek(0)); }
break;
case 6:
//#line 101 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 7:
//#line 104 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); addVarDefs((List<Definition>)yyval, (Type)val_peek(2), (List<String>)val_peek(1), scanner.getLine()); }
break;
case 8:
//#line 105 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); addVarDefs((List<Definition>)yyval, (Type)val_peek(2), (List<String>)val_peek(1), scanner.getLine()); }
break;
case 9:
//#line 109 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 10:
//#line 110 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 11:
//#line 113 "../../src/parser/parser.y"
{ yyval = val_peek(1); mergeDefs((List<Definition>)yyval, val_peek(0)); }
break;
case 12:
//#line 114 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 13:
//#line 121 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); addStructDefs((List<Definition>)yyval, (List<Definition>)val_peek(3), (List<String>)val_peek(1), scanner.getLine()); }
break;
case 14:
//#line 124 "../../src/parser/parser.y"
{ yyval = new DefFunc(scanner.getLine(), scanner.getColumn(),(Type) new MainType(scanner.getLine(), scanner.getColumn(), (Type)VoidType.getInstance()), "main", (List<Definition>)val_peek(2), (List<Statement>)val_peek(1)); }
break;
case 15:
//#line 127 "../../src/parser/parser.y"
{ yyval = new DefFunc(scanner.getLine(), scanner.getColumn(), (Type)new FuncType(scanner.getLine(), scanner.getColumn(), (Type)val_peek(8), (List<Definition>)val_peek(5)), (String)val_peek(7), (List<Definition>)val_peek(2), (List<Statement>)val_peek(1)); }
break;
case 16:
//#line 128 "../../src/parser/parser.y"
{ yyval = new DefFunc(scanner.getLine(), scanner.getColumn(),(Type) new FuncType(scanner.getLine(), scanner.getColumn(), (Type)VoidType.getInstance(), (List<Definition>)val_peek(5)), (String)val_peek(7), (List<Definition>)val_peek(2), (List<Statement>)val_peek(1)); }
break;
case 17:
//#line 133 "../../src/parser/parser.y"
{ ((List<Definition>)yyval).add(new DefVar(scanner.getLine(), scanner.getColumn(), (Type)val_peek(1), (String)val_peek(0))); yyval = val_peek(3); }
break;
case 18:
//#line 134 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>();((List<Definition>)yyval).add(new DefVar(scanner.getLine(), scanner.getColumn(), (Type)val_peek(1), (String)val_peek(0))); }
break;
case 19:
//#line 137 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 20:
//#line 138 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 21:
//#line 141 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 22:
//#line 142 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 23:
//#line 143 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 24:
//#line 146 "../../src/parser/parser.y"
{ yyval = getArrayDef((Type)val_peek(3), (Integer)val_peek(1), scanner.getLine()); }
break;
case 25:
//#line 149 "../../src/parser/parser.y"
{ yyval =val_peek(0);}
break;
case 26:
//#line 150 "../../src/parser/parser.y"
{ yyval =val_peek(0);}
break;
case 27:
//#line 153 "../../src/parser/parser.y"
{ ((List<String>)yyval).add((String)val_peek(0)); yyval = val_peek(2);  }
break;
case 28:
//#line 154 "../../src/parser/parser.y"
{ yyval = new ArrayList<String>(); ((List<String>)yyval).add((String)val_peek(0)); }
break;
case 29:
//#line 157 "../../src/parser/parser.y"
{ ((List<Statement>)yyval).add((Statement)val_peek(0)); yyval = val_peek(1); }
break;
case 30:
//#line 158 "../../src/parser/parser.y"
{ yyval = new ArrayList<Statement>(); }
break;
case 31:
//#line 161 "../../src/parser/parser.y"
{ yyval = new Return(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(1)); }
break;
case 32:
//#line 162 "../../src/parser/parser.y"
{ yyval = new Read(scanner.getLine(), scanner.getColumn(), (List<Expression>)val_peek(3)); }
break;
case 33:
//#line 163 "../../src/parser/parser.y"
{ yyval = new Write(scanner.getLine(), scanner.getColumn(), (List<Expression>)val_peek(1)); }
break;
case 34:
//#line 164 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 35:
//#line 165 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 36:
//#line 166 "../../src/parser/parser.y"
{ yyval = new Assigment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1)); }
break;
case 37:
//#line 167 "../../src/parser/parser.y"
{ yyval = new CallFunc(scanner.getLine(), scanner.getColumn(), (String)val_peek(4), (List<Expression>)val_peek(2)); }
break;
case 38:
//#line 170 "../../src/parser/parser.y"
{ yyval = new While(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (List<Statement>)val_peek(1)); }
break;
case 39:
//#line 171 "../../src/parser/parser.y"
{ yyval = new While(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (List<Statement>)val_peek(0)); }
break;
case 40:
//#line 174 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(8), (List<Statement>)val_peek(5), (List<Statement>)val_peek(1)); }
break;
case 41:
//#line 175 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(6), (List<Statement>)val_peek(3), (List<Statement>)val_peek(0)); }
break;
case 42:
//#line 176 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(6), (List<Statement>)val_peek(4), (List<Statement>)val_peek(1)); }
break;
case 43:
//#line 177 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (List<Statement>)val_peek(2), (List<Statement>)val_peek(0)); }
break;
case 44:
//#line 178 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (List<Statement>)val_peek(1), new ArrayList()); }
break;
case 45:
//#line 179 "../../src/parser/parser.y"
{ yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (List<Statement>)val_peek(0), new ArrayList()); }
break;
case 46:
//#line 182 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 47:
//#line 183 "../../src/parser/parser.y"
{ yyval = new ArrayList<Expression>(); }
break;
case 48:
//#line 186 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List<Expression>)yyval).add((Expression)val_peek(0)); }
break;
case 49:
//#line 187 "../../src/parser/parser.y"
{ yyval = new ArrayList<Expression>(); ((List<Expression>)yyval).add((Expression)val_peek(0)); }
break;
case 50:
//#line 190 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), '+'); }
break;
case 51:
//#line 191 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), '*'); }
break;
case 52:
//#line 192 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), '/'); }
break;
case 53:
//#line 193 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), '-'); }
break;
case 54:
//#line 194 "../../src/parser/parser.y"
{ yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), '%'); }
break;
case 55:
//#line 195 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "=="); }
break;
case 56:
//#line 196 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "!="); }
break;
case 57:
//#line 197 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), ">"); }
break;
case 58:
//#line 198 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "<"); }
break;
case 59:
//#line 199 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "<="); }
break;
case 60:
//#line 200 "../../src/parser/parser.y"
{ yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), ">="); }
break;
case 61:
//#line 201 "../../src/parser/parser.y"
{ yyval = new LogicOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "&&"); }
break;
case 62:
//#line 202 "../../src/parser/parser.y"
{ yyval = new LogicOperation(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0), "||"); }
break;
case 63:
//#line 203 "../../src/parser/parser.y"
{ yyval = new Cast(scanner.getLine(), scanner.getColumn(), (Type)val_peek(2), (Expression)val_peek(0)); }
break;
case 64:
//#line 204 "../../src/parser/parser.y"
{ yyval = new ArrayAccess(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1)); }
break;
case 65:
//#line 205 "../../src/parser/parser.y"
{ yyval = new StructAccess(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(0)); }
break;
case 66:
//#line 206 "../../src/parser/parser.y"
{ yyval = new CallFunction(scanner.getLine(), scanner.getColumn(), (String)val_peek(3), (List<Expression>)val_peek(1)); }
break;
case 67:
//#line 207 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 68:
//#line 208 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 69:
//#line 209 "../../src/parser/parser.y"
{ yyval = new UnaryNot(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 70:
//#line 210 "../../src/parser/parser.y"
{ yyval = new LiteralInt(scanner.getLine(), scanner.getColumn(), (Integer)val_peek(0)); }
break;
case 71:
//#line 211 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 72:
//#line 212 "../../src/parser/parser.y"
{ yyval = new LiteralChar(scanner.getLine(), scanner.getColumn(), (Character)val_peek(0)); }
break;
case 73:
//#line 213 "../../src/parser/parser.y"
{ yyval = new LiteralReal(scanner.getLine(), scanner.getColumn(), (Double)val_peek(0)); }
break;
//#line 1133 "Parser.java"
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
