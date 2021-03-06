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
//#line 21 "Parser.java"




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
   11,   11,   12,   12,    5,    2,    6,    6,   16,   17,
   17,   15,   15,    7,    7,    7,    9,   13,   13,    8,
    8,   14,   14,   18,   18,   18,   18,   18,   18,   18,
   22,   22,   21,   21,   21,   21,   21,   21,   23,   23,
   20,   20,   19,   19,   19,   19,   19,   19,   19,   19,
   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,
   19,   19,   19,   19,   19,   19,   19,
};
final static short yylen[] = {                            2,
    2,    1,    1,    1,    2,    0,    3,    3,    1,    1,
    2,    0,    4,    0,    6,    8,    9,    9,    2,    3,
    1,    1,    0,    1,    1,    1,    4,    1,    1,    3,
    1,    2,    0,    3,    5,    3,    1,    1,    4,    5,
    7,    5,   11,    9,    9,    7,    7,    5,    0,    1,
    3,    1,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    4,    4,    3,    4,
    2,    3,    2,    1,    1,    1,    1,
};
final static short yydefred[] = {                         6,
    0,    0,    0,   25,   26,    0,   24,    1,    5,    2,
    3,    4,    0,    0,    0,    0,    0,   14,    0,    0,
   31,    0,    0,    0,    0,    0,    0,    7,    0,    8,
    0,    0,    0,   21,    0,    0,    0,   28,   29,    0,
    0,   30,   27,   19,    0,    0,   12,    0,    0,    0,
   12,   20,    0,   15,   13,   12,    0,    9,   10,    0,
   11,    0,    0,    0,   74,   77,    0,    0,    0,    0,
    0,    0,   76,    0,    0,   16,    0,   32,    0,   37,
   38,    0,   18,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   17,    0,   34,    0,    0,   36,    0,    0,
    0,    0,    0,   72,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   69,    0,    0,    0,    0,    0,    0,   67,   39,   68,
   70,   40,   33,    0,   35,   33,   42,    0,    0,    0,
    0,   33,   46,   41,    0,    0,   33,   44,   45,    0,
   43,
};
final static short yydgoto[] = {                          1,
    2,    8,    9,   58,   59,   12,   32,   20,   14,   61,
   53,   26,   15,   62,   33,   34,   35,   78,   79,  116,
   80,   81,  117,
};
final static short yysindex[] = {                         0,
    0,  -73, -219,    0,    0, -112,    0,    0,    0,    0,
    0,    0, -247, -242,  -62,  -12,   -6,    0,    4,   23,
    0,   41, -222, -101,   10, -122, -101,    0, -211,    0,
  -31, -196,   28,    0,   29,  -48, -242,    0,    0,  -90,
   42,    0,    0,    0,  -32, -101,    0,   50,   73,  -30,
    0,    0, -113,    0,    0,    0, -113,    0,    0, -242,
    0,  -33, -113,  -15,    0,    0,  261,   58,  261,   65,
   71,   84,    0,  261,  261,    0,  207,    0,  651,    0,
    0,    3,    0,   85,  672,  261,  838,   83,  261,  261,
  261,   -5,  -44,   88,  710,  261,  261,  261,  261,  261,
  261,  261,  261,  261,  261,  261,  261,  261,  261,  261,
  261, -130,    0,  261,    0,   91,  108,    0,  261,  745,
   78,  775,  261,    0,  124,   -5,   -5,  124,   94,   94,
  808,   -5,   94,   94,  -27,  -27,  -44,  -44,  -44,  860,
    0,  109,   92,  838,   21,  100,   39,    0,    0,    0,
    0,    0,    0, -107,    0,    0,    0,   57,   75,   93,
  -94,    0,    0,    0,  113,  132,    0,    0,    0,  150,
    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   87,   89,    0,    0,    0,    0,   86,    0,
    0,    0,    0,  135,    0,    0,  135,    0,    0,    0,
    0,    0,    0,    0,  138,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  168,    0,    0,    0,  168,    0,    0,   87,
    0,    0,  168,    0,    0,    0,    0,  915,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  487,    0,  140,  -20,    0,    0,    0,
    0,  -28,  522,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  140,    0,   42,    0,    0,    0,    0,
    0,    0,    0,    0, 1025,   19,   55, 1112, 1002, 1032,
    0,   62, 1068, 1090,  642,  995,  552,  587,  617,    0,
    0,    0,  951,   30,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  187,    0,    0,    0,    0,    0,    0,
  243,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  185,  189,    0,   24,    9,  163,    0,
  -47,    0,  167,  -49,  172,  148,    0,  -89, 1301,  -63,
    0,    0,   90,
};
final static int YYTABLESIZE=1424;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         74,
   23,  112,   37,   57,   73,   88,   77,   64,   63,  110,
   18,   75,   73,   82,  108,   73,   19,   74,  112,  109,
   52,   21,   22,   52,   77,   13,  121,   24,   23,   75,
   73,  110,   73,   25,   31,   74,  108,  106,   52,  107,
  112,  109,   77,   27,   16,   48,  111,   75,   49,   38,
   36,   65,   42,   74,  105,  154,  104,  157,   17,   65,
   77,   43,   65,  111,   73,   75,   29,   44,   45,  163,
   51,   74,   46,   51,   47,  168,   60,   65,   77,   65,
   60,   28,   50,   75,   29,  111,   60,   64,   51,   74,
   51,   76,   56,   29,   66,   64,   77,   86,   64,   30,
   94,   75,   66,  158,   89,   66,  160,   74,   54,   83,
   90,   65,  166,   64,   77,   64,   29,  170,  146,   75,
   66,  119,   66,   91,  114,   74,  119,  113,  123,   31,
  110,   55,   77,  141,  119,  108,  106,   75,  107,  112,
  109,  118,    4,  153,   31,   74,    5,   64,  143,  151,
  152,    4,   77,    7,   66,    5,    6,   75,  155,  159,
  110,  156,    7,    4,   74,  108,  106,    5,  107,  112,
  109,   77,  165,   21,    7,   23,   75,   28,   22,   29,
   49,  161,   74,  105,  111,  104,   10,    3,   39,   77,
   11,    4,   40,   52,   75,    5,    6,  162,   41,    0,
   33,    0,    7,  142,    0,    0,    0,   33,    0,    0,
    0,    0,   33,    0,  111,    0,    0,  164,    0,   48,
    0,    0,    0,   65,   66,    0,   48,    0,    0,   67,
   68,   48,   69,   73,   70,  167,    0,   71,    0,   74,
   72,   65,   66,   73,    0,   73,   77,   67,   68,    0,
   69,   75,   70,   96,    0,   71,  169,    0,   72,   65,
   66,    0,    0,   73,    0,   67,   68,   99,   69,  100,
   70,  101,    0,   71,  171,   47,   72,   65,   66,    0,
   65,   73,   47,   67,   68,    0,   69,   47,   70,    0,
   65,   71,   33,   74,   72,   65,   66,    0,    0,   73,
   77,   67,   68,    0,   69,   75,   70,    0,    0,   71,
    0,   48,   72,   65,   66,    0,   64,   73,    0,   67,
   68,    0,   69,   66,   70,    0,   64,   71,    0,    0,
   72,   65,   66,   66,    0,   73,    0,   67,   68,    0,
   69,    0,   70,    0,    0,   71,    0,    0,   72,   65,
   66,    0,    0,   73,    0,   67,   68,    0,   69,    0,
   70,    0,    0,   71,    0,    0,   72,   47,    0,   65,
   66,   73,    0,    0,    0,   67,   68,    0,   69,    0,
   70,    0,    0,   71,    0,    0,   72,    0,   65,   66,
    0,   73,    0,    0,   67,   68,    0,   69,  100,   70,
  101,    0,   71,    0,    0,   72,   65,   66,    0,    0,
   73,    0,   67,   68,    0,   69,    0,   70,    0,    0,
   71,    0,    0,   72,   33,   33,    0,    0,   73,    0,
   33,   33,    0,   33,    0,   33,    0,    0,   33,    0,
    0,   33,    0,   48,   48,    0,   33,    0,    0,   48,
   48,    0,   48,    0,   48,    0,    0,   48,    0,    0,
   48,    0,    0,   65,   66,   48,    0,    0,    0,    0,
   84,    4,    0,    0,    0,    5,    0,    0,    0,    0,
    0,    0,    7,    0,    0,   73,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   47,
   47,    0,    0,    0,    0,   47,   47,    0,   47,    0,
   47,    0,    0,   47,    0,    0,   47,   65,   66,   75,
    0,   47,    0,   75,   84,    0,    0,   75,   75,   75,
   75,   75,   75,   75,    0,    0,    0,    0,    0,   73,
    0,    0,    0,    0,    0,   75,   75,   75,   75,    0,
    0,    0,    0,    0,   71,    0,    0,    0,   71,    0,
    0,    0,   71,   71,   71,   71,   71,    0,   71,    0,
    0,    0,    0,    0,    0,    0,    0,   75,    0,   75,
   71,   71,   71,   71,   54,    0,    0,    0,   54,    0,
    0,    0,   54,   54,   54,   54,   54,    0,   54,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   54,   54,   54,   54,   71,    0,    0,    0,    0,   55,
    0,    0,    0,   55,    0,    0,    0,   55,   55,   55,
   55,   55,    0,   55,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   54,   55,   55,   55,   55,   57,
    0,    0,    0,   57,    0,    0,    0,   57,   57,   57,
   57,   57,    0,   57,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   53,   57,   57,   57,   57,   55,
    0,    0,   53,  103,   53,   53,   53,  110,    0,    0,
    0,    0,  108,  106,    0,  107,  112,  109,    0,    0,
   53,   53,   53,   53,  103,    0,    0,    0,  110,   57,
  105,  102,  104,  108,  106,    0,  107,  112,  109,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  115,  105,    0,  104,   53,    0,    0,    0,    0,    0,
    0,  111,  103,    0,    0,   75,  110,    0,   75,    0,
  124,  108,  106,    0,  107,  112,  109,    0,   75,   75,
    0,   75,  111,   75,    0,    0,    0,    0,    0,  105,
    0,  104,    0,    0,    0,    0,    0,  103,    0,    0,
   71,  110,    0,   71,    0,  145,  108,  106,    0,  107,
  112,  109,    0,   71,   71,    0,   71,    0,   71,    0,
  111,    0,    0,    0,  105,    0,  104,  103,    0,    0,
   54,  110,    0,   54,    0,  147,  108,  106,    0,  107,
  112,  109,    0,   54,   54,    0,   54,    0,   54,    0,
    0,    0,    0,    0,  105,  111,  104,    0,    0,    0,
  103,    0,    0,    0,  110,   55,    0,    0,   55,  108,
  106,    0,  107,  112,  109,    0,    0,    0,   55,   55,
    0,   55,    0,   55,    0,  111,  149,  105,    0,  104,
  103,    0,    0,    0,  110,   57,    0,    0,   57,  108,
  106,    0,  107,  112,  109,    0,    0,    0,   57,   57,
    0,   57,  103,   57,    0,    0,  110,  105,  111,  104,
   53,  108,  106,   53,  107,  112,  109,    0,    0,   96,
    0,    0,   97,   53,   53,    0,   53,    0,   53,  105,
    0,  104,   98,   99,    0,  100,    0,  101,  111,    0,
   96,    0,    0,   97,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   98,   99,    0,  100,   75,  101,    0,
  111,   75,  150,    0,    0,    0,   75,   75,    0,   75,
   75,   75,    0,    0,    0,    0,    0,    0,   96,    0,
    0,   97,    0,    0,   75,   75,   75,    0,    0,    0,
    0,   98,   99,   70,  100,    0,  101,   70,    0,    0,
    0,    0,   70,   70,    0,   70,   70,   70,    0,    0,
    0,    0,    0,   96,    0,   75,   97,    0,    0,    0,
   70,   70,   70,    0,    0,    0,   98,   99,    0,  100,
    0,  101,    0,    0,    0,    0,    0,   56,    0,    0,
    0,    0,    0,   96,   63,   56,   97,   56,   56,   56,
    0,   70,   63,    0,    0,   63,   98,   99,    0,  100,
    0,  101,    0,   56,   56,   56,   56,   58,    0,    0,
   63,   63,   63,   63,   62,   58,   96,    0,   58,   97,
    0,    0,   62,    0,    0,   62,    0,    0,    0,   98,
   99,    0,  100,   58,  101,   58,    0,   56,    0,    0,
   62,   62,   62,   62,   63,    0,   96,    0,    0,   97,
   60,    0,    0,    0,    0,    0,    0,    0,   60,   98,
   99,   60,  100,    0,  101,    0,    0,   58,   96,    0,
    0,   97,   61,    0,   62,    0,   60,   60,   60,   60,
   61,   98,   99,   61,  100,    0,  101,    0,    0,    0,
    0,    0,    0,    0,   59,    0,    0,    0,   61,   61,
   61,   61,   59,    0,    0,   59,    0,    0,    0,    0,
   60,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   59,    0,   59,   75,    0,    0,   75,    0,    0,    0,
    0,    0,   61,    0,    0,    0,   75,   75,    0,   75,
    0,   75,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   59,    0,    0,    0,    0,   70,
    0,    0,   70,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   70,   70,    0,   70,    0,   70,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   56,    0,    0,   56,    0,    0,    0,
   63,    0,    0,   63,    0,    0,   56,   56,    0,   56,
    0,   56,    0,   63,   63,    0,   63,    0,   63,    0,
    0,    0,    0,   58,    0,    0,   58,    0,    0,    0,
   62,    0,    0,   62,    0,    0,   58,   58,    0,    0,
    0,    0,    0,   62,   62,    0,   62,    0,   62,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   60,    0,    0,   60,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   60,
   60,    0,   60,    0,   60,    0,    0,    0,   61,    0,
    0,   61,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   61,   61,    0,   61,    0,   61,   85,    0,   87,
   59,    0,    0,   59,   92,   93,    0,   95,    0,    0,
    0,    0,    0,   59,   59,    0,   87,    0,    0,  120,
   87,  122,    0,    0,    0,    0,  125,  126,  127,  128,
  129,  130,  131,  132,  133,  134,  135,  136,  137,  138,
  139,  140,    0,    0,   87,    0,    0,    0,    0,  144,
    0,    0,    0,  148,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   91,   46,  125,   51,   33,   69,   40,   57,   56,   37,
  123,   45,   41,   63,   42,   44,  264,   33,   46,   47,
   41,  264,   14,   44,   40,    2,   90,   40,   91,   45,
   59,   37,   61,   40,  257,   33,   42,   43,   59,   45,
   46,   47,   40,   40,  264,   37,   91,   45,   40,   26,
   41,   33,  264,   33,   60,  145,   62,  147,  278,   41,
   40,   93,   44,   91,   93,   45,   44,  264,   41,  159,
   41,   33,   44,   44,  123,  165,   53,   59,   40,   61,
   57,   59,   41,   45,   44,   91,   63,   33,   59,   33,
  123,  125,  123,   44,   33,   41,   40,   40,   44,   59,
   77,   45,   41,  153,   40,   44,  156,   33,   59,  125,
   40,   93,  162,   59,   40,   61,   44,  167,   41,   45,
   59,   44,   61,   40,   40,   33,   44,  125,   41,   44,
   37,   59,   40,  264,   44,   42,   43,   45,   45,   46,
   47,   59,  265,  123,   59,   33,  269,   93,   41,   41,
   59,  265,   40,  276,   93,  269,  270,   45,   59,  267,
   37,  123,  276,  265,   33,   42,   43,  269,   45,   46,
   47,   40,  267,  264,  276,   41,   45,   91,   41,   91,
   41,  125,   33,   60,   91,   62,    2,  261,   26,   40,
    2,  265,   26,   46,   45,  269,  270,  123,   27,   -1,
   33,   -1,  276,  114,   -1,   -1,   -1,   40,   -1,   -1,
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
  264,   -1,  266,  262,  268,   -1,  272,  271,   -1,   -1,
  274,  257,  258,  272,   -1,  279,   -1,  263,  264,   -1,
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
   -1,   -1,  276,   -1,   -1,  279,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,
  258,   -1,   -1,   -1,   -1,  263,  264,   -1,  266,   -1,
  268,   -1,   -1,  271,   -1,   -1,  274,  257,  258,   33,
   -1,  279,   -1,   37,  264,   -1,   -1,   41,   42,   43,
   44,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,  279,
   -1,   -1,   -1,   -1,   -1,   59,   60,   61,   62,   -1,
   -1,   -1,   -1,   -1,   33,   -1,   -1,   -1,   37,   -1,
   -1,   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,   93,
   59,   60,   61,   62,   33,   -1,   -1,   -1,   37,   -1,
   -1,   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   59,   60,   61,   62,   93,   -1,   -1,   -1,   -1,   33,
   -1,   -1,   -1,   37,   -1,   -1,   -1,   41,   42,   43,
   44,   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   93,   59,   60,   61,   62,   33,
   -1,   -1,   -1,   37,   -1,   -1,   -1,   41,   42,   43,
   44,   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   33,   59,   60,   61,   62,   93,
   -1,   -1,   41,   33,   43,   44,   45,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   59,   60,   61,   62,   33,   -1,   -1,   -1,   37,   93,
   60,   61,   62,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   59,   60,   -1,   62,   93,   -1,   -1,   -1,   -1,   -1,
   -1,   91,   33,   -1,   -1,  259,   37,   -1,  262,   -1,
   41,   42,   43,   -1,   45,   46,   47,   -1,  272,  273,
   -1,  275,   91,  277,   -1,   -1,   -1,   -1,   -1,   60,
   -1,   62,   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,
  259,   37,   -1,  262,   -1,   41,   42,   43,   -1,   45,
   46,   47,   -1,  272,  273,   -1,  275,   -1,  277,   -1,
   91,   -1,   -1,   -1,   60,   -1,   62,   33,   -1,   -1,
  259,   37,   -1,  262,   -1,   41,   42,   43,   -1,   45,
   46,   47,   -1,  272,  273,   -1,  275,   -1,  277,   -1,
   -1,   -1,   -1,   -1,   60,   91,   62,   -1,   -1,   -1,
   33,   -1,   -1,   -1,   37,  259,   -1,   -1,  262,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,  272,  273,
   -1,  275,   -1,  277,   -1,   91,   59,   60,   -1,   62,
   33,   -1,   -1,   -1,   37,  259,   -1,   -1,  262,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,  272,  273,
   -1,  275,   33,  277,   -1,   -1,   37,   60,   91,   62,
  259,   42,   43,  262,   45,   46,   47,   -1,   -1,  259,
   -1,   -1,  262,  272,  273,   -1,  275,   -1,  277,   60,
   -1,   62,  272,  273,   -1,  275,   -1,  277,   91,   -1,
  259,   -1,   -1,  262,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  272,  273,   -1,  275,   33,  277,   -1,
   91,   37,   93,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,  259,   -1,
   -1,  262,   -1,   -1,   60,   61,   62,   -1,   -1,   -1,
   -1,  272,  273,   33,  275,   -1,  277,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,  259,   -1,   91,  262,   -1,   -1,   -1,
   60,   61,   62,   -1,   -1,   -1,  272,  273,   -1,  275,
   -1,  277,   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,
   -1,   -1,   -1,  259,   33,   41,  262,   43,   44,   45,
   -1,   91,   41,   -1,   -1,   44,  272,  273,   -1,  275,
   -1,  277,   -1,   59,   60,   61,   62,   33,   -1,   -1,
   59,   60,   61,   62,   33,   41,  259,   -1,   44,  262,
   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,  272,
  273,   -1,  275,   59,  277,   61,   -1,   93,   -1,   -1,
   59,   60,   61,   62,   93,   -1,  259,   -1,   -1,  262,
   33,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   41,  272,
  273,   44,  275,   -1,  277,   -1,   -1,   93,  259,   -1,
   -1,  262,   33,   -1,   93,   -1,   59,   60,   61,   62,
   41,  272,  273,   44,  275,   -1,  277,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   33,   -1,   -1,   -1,   59,   60,
   61,   62,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   59,   -1,   61,  259,   -1,   -1,  262,   -1,   -1,   -1,
   -1,   -1,   93,   -1,   -1,   -1,  272,  273,   -1,  275,
   -1,  277,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,  259,
   -1,   -1,  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  272,  273,   -1,  275,   -1,  277,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,   -1,
  259,   -1,   -1,  262,   -1,   -1,  272,  273,   -1,  275,
   -1,  277,   -1,  272,  273,   -1,  275,   -1,  277,   -1,
   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,   -1,
  259,   -1,   -1,  262,   -1,   -1,  272,  273,   -1,   -1,
   -1,   -1,   -1,  272,  273,   -1,  275,   -1,  277,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,
  273,   -1,  275,   -1,  277,   -1,   -1,   -1,  259,   -1,
   -1,  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  272,  273,   -1,  275,   -1,  277,   67,   -1,   69,
  259,   -1,   -1,  262,   74,   75,   -1,   77,   -1,   -1,
   -1,   -1,   -1,  272,  273,   -1,   86,   -1,   -1,   89,
   90,   91,   -1,   -1,   -1,   -1,   96,   97,   98,   99,
  100,  101,  102,  103,  104,  105,  106,  107,  108,  109,
  110,  111,   -1,   -1,  114,   -1,   -1,   -1,   -1,  119,
   -1,   -1,   -1,  123,
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
"opt_list_fields : opt_list_fields type list_ident ';'",
"opt_list_fields :",
"struct_def : STRUCT '{' opt_list_fields '}' list_ident ';'",
"main : VOID MAIN '(' ')' '{' opt_list_local_var statements '}'",
"func_def : p_type ID '(' opt_list_param ')' '{' opt_list_local_var statements '}'",
"func_def : VOID ID '(' opt_list_param ')' '{' opt_list_local_var statements '}'",
"param : p_type ID",
"list_param : list_param ',' param",
"list_param : param",
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
"opt_list_exp :",
"opt_list_exp : list_exp",
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
"exp : exp '!' exp",
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

//#line 182 "../../src/parser/parser.y"

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
//#line 645 "Parser.java"
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
