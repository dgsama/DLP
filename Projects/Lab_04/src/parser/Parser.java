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
public final static short UNARY_MINUS=280;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    3,    3,    1,    1,    4,    4,   10,   10,
   11,   12,    5,    2,    6,    6,   16,   17,   17,   15,
   15,    7,    7,    7,    9,   13,   13,    8,    8,   14,
   14,   18,   18,   18,   18,   18,   18,   18,   18,   21,
   21,   22,   22,   20,   20,   19,   19,   19,   19,   19,
   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,
   19,   19,   19,   19,   19,   19,   19,   19,   19,
};
final static short yylen[] = {                            2,
    2,    1,    1,    1,    2,    0,    3,    3,    1,    1,
    2,    4,    6,    8,    9,    9,    2,    3,    1,    1,
    0,    1,    1,    1,    4,    1,    1,    3,    1,    0,
    2,    5,    5,    5,    7,   11,    1,    4,    5,    7,
    5,    0,    1,    3,    1,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    4,
    4,    3,    4,    2,    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         6,
    0,    0,    0,    0,   23,   24,    0,   22,    1,    5,
    2,    3,    4,    0,    0,    0,    0,    0,    0,    0,
   29,    0,    0,    0,    0,    0,    0,    0,    7,    0,
    8,    0,    0,    0,    0,   19,    0,    0,    0,   26,
   27,    0,   28,   25,    0,   17,    0,    0,    0,    0,
    0,    0,    0,   18,    0,   13,   12,    9,   10,   11,
    0,    0,    0,   66,   69,    0,    0,    0,    0,    0,
    0,   68,    0,    0,   14,   31,    0,   37,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   16,   15,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   65,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   62,    0,    0,    0,
    0,    0,    0,    0,    0,   60,   38,   61,   32,    0,
   39,   34,   30,   33,   30,   41,   63,    0,    0,    0,
   40,    0,   30,    0,   36,
};
final static short yydgoto[] = {                          1,
    2,    9,   10,   58,   59,   13,   14,   22,   15,   60,
   52,   28,   16,   61,   35,   36,   37,   76,   77,  112,
   78,  113,
};
final static short yysindex[] = {                         0,
    0,  -81, -263, -244,    0,    0, -103,    0,    0,    0,
    0,    0,    0, -241, -241,  -53,    8,   16,   19,    0,
    0,  -43,  -26, -223,   26, -145, -145, -122,    0, -209,
    0,  -12,  -52, -192,   48,    0,   46,   64, -241,    0,
    0,  -89,    0,    0,    0,    0,  -16, -145,   -8,  -22,
    7,  -45,    0,    0,    0,    0,    0,    0,    0,    0,
  552,  -45,  -45,    0,    0,   77,   95,  121,  122,  126,
  129,    0,  -21,  757,    0,    0,  131,    0,  624,  636,
  -21,  -21,  -21,  -21,  -21,  -21,  130,  -39,   75,  161,
  -21,  -21,  -21,  -21,  -21,  -21,  -21,  -21,  -21,  -21,
  -21,  -21,  -21,  -21,  -21,  -21, -113,    0,    0,  168,
  432,  127,  138,   92,  208,  105,  255,  -21,  -21,    0,
   41,  453,  453,   41,   76,   76,  288,  453,   76,   76,
   -6,   -6,  -39,  -39,  -39,  318,    0,  113,  -21,  123,
  128,   58,  137,  597,  144,    0,    0,    0,    0,  432,
    0,    0,    0,    0,    0,    0,    0,  655,  689,  -68,
    0,   63,    0,  708,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  125,  136,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  159,  159,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  171,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  739,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  739,  739,    0,    0,    0,  340,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  176,    0,    0,    0,    0,  -33,    2,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  109,  177,    0,    0,    0,    0,    0,  176,    0,    0,
  505,    9,  104,  594,  526,  563,    0,  677,  570,  600,
  460,  496,   32,   67,   97,    0,    0,    0,    0,  411,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  111,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  766,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  217,  230,    0,  258,   15,  205,    0,
   27,    0,  206,  -57,  211,  187,    0,  102,  965,   14,
    0,  139,
};
final static int YYTABLESIZE=1104;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         67,
   30,   24,   39,   67,   79,   80,  107,   67,   67,   67,
   67,   67,   67,   67,   17,   29,   18,   30,   74,   20,
    5,   30,   21,   73,    6,   67,   67,   67,   67,   23,
  105,    8,   31,   32,   64,  103,   56,   24,   64,  107,
  104,   58,   64,   64,   64,   64,   64,   25,   64,   58,
   30,  106,   58,   50,   43,   26,   51,   67,   27,   67,
   64,   64,   64,   64,   47,   57,   33,   58,   47,   58,
   45,   46,   47,   47,   47,   47,   47,  105,   47,   62,
   44,   63,  103,  101,  106,  102,  107,  104,   47,   48,
   47,   47,   47,   47,   64,  158,  114,  159,  116,   48,
  100,   58,   99,   48,   49,  164,   53,   48,   48,   48,
   48,   48,  105,   48,   55,  119,   81,  103,  101,    5,
  102,  107,  104,    6,   47,   48,   48,   48,   48,   50,
    8,  106,  141,   50,   82,  139,   57,   50,   50,   50,
   50,   50,    5,   50,   57,  143,    6,   57,  139,   45,
  137,   44,   45,    8,   44,   50,   50,   50,   50,   48,
   83,   84,   57,   98,   57,   85,  106,  105,   86,  118,
  139,  149,  103,  101,   21,  102,  107,  104,  140,    3,
  153,  151,    4,    5,  157,  163,  152,    6,    7,   50,
  100,   97,   99,   98,    8,  154,   57,  105,  162,   21,
   98,  120,  103,  101,  105,  102,  107,  104,  138,  103,
  101,   20,  102,  107,  104,   26,   42,   43,   11,    5,
  100,  106,   99,    6,    7,   67,   27,  100,   67,   99,
    8,   12,   41,   42,   54,   64,   65,   38,   67,   67,
   98,   67,   87,   67,  105,  156,    0,    0,  142,  103,
  101,  106,  102,  107,  104,    0,  145,   72,  106,    0,
   64,   19,    0,   64,    0,    0,    0,  100,    0,   99,
   58,    0,    0,   64,   64,    0,   64,    0,   64,    0,
   58,    0,    0,   34,   34,   40,    0,   98,    0,    0,
   47,  105,    0,   47,    0,  144,  103,  101,  106,  102,
  107,  104,    0,   47,   47,   34,   47,    0,   47,    0,
    0,    0,    0,    0,  100,   95,   99,   96,    0,    0,
   98,    0,    0,    0,  105,   48,    0,    0,   48,  103,
  101,   89,  102,  107,  104,    0,    0,    0,   48,   48,
    0,   48,    0,   48,    0,  106,  147,  100,    0,   99,
   98,    0,    0,    0,  105,   50,    0,    0,   50,  103,
  101,    0,  102,  107,  104,   57,    0,    0,   50,   50,
    0,   50,   67,   50,    0,   57,   67,  100,  106,   99,
    0,   67,   67,    0,   67,   67,   67,    0,    0,   91,
    0,    0,   92,    0,    0,    0,    0,    0,    0,   67,
   67,   67,   93,   94,    0,   95,    0,   96,  106,    0,
  148,    0,    0,    0,    0,    0,    0,    0,    0,   91,
    0,    0,   92,    0,    0,    0,   91,    0,    0,   92,
   67,    0,   93,   94,    0,   95,    0,   96,    0,   93,
   94,    0,   95,   63,   96,    0,    0,   63,    0,    0,
    0,    0,   63,   63,    0,   63,   63,   63,    0,    0,
    0,    0,    0,    0,   98,    0,   91,    0,  105,   92,
   63,   63,   63,  103,  101,    0,  102,  107,  104,   93,
   94,    0,   95,    0,   96,    0,    0,    0,    0,  105,
    0,  100,   46,   99,  103,  101,    0,  102,  107,  104,
   46,   63,   46,   46,   46,    0,    0,    0,    0,    0,
    0,    0,  100,   91,   99,    0,   92,    0,   46,   46,
   46,   46,  106,    0,    0,    0,   93,   94,   49,   95,
    0,   96,    0,    0,    0,    0,   49,   51,   49,   49,
   49,    0,    0,  106,    0,   51,   91,    0,   51,   92,
    0,    0,   46,    0,   49,   49,   49,   49,   56,   93,
   94,    0,   95,   51,   96,   51,   56,    0,    0,   56,
    0,    0,    0,    0,    0,    0,   91,    0,    0,   92,
    0,    0,    0,    0,   56,   56,   56,   56,   49,   93,
   94,   74,   95,    0,   96,   55,   73,   51,   67,    0,
    0,   67,   53,   55,    0,    0,   55,    0,    0,    0,
   53,   67,   67,   53,   67,    0,   67,    0,   56,    0,
    0,   55,   55,   55,   55,    0,   52,    0,   53,   53,
   53,   53,   54,    0,   52,    0,   74,   52,    0,    0,
   54,   73,    0,   54,    0,    0,    0,    0,    0,    0,
    0,    0,   52,    0,   52,   55,    0,    0,   54,   54,
   54,   54,   53,   74,    0,    0,    0,    0,   73,   63,
    0,    0,   63,    0,    0,   74,   75,    0,    0,    0,
   73,    0,   63,   63,    0,   63,   52,   63,    0,    0,
   91,    0,   54,   92,   74,    0,    0,    0,    0,   73,
    0,    0,    0,   93,   94,    0,   95,    0,   96,   59,
    0,   91,    0,    0,    0,    0,    0,   59,   46,  155,
   59,   46,    0,    0,    0,   94,    0,   95,   74,   96,
    0,   46,   46,   73,   46,   59,   46,   59,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   74,  108,    0,
    0,    0,   73,    0,   49,    0,    0,   49,    0,    0,
  109,    0,    0,   51,    0,    0,   51,   49,   49,   59,
   49,    0,   49,    0,    0,    0,   51,   51,   30,  160,
    0,    0,    0,   30,   56,    0,    0,   56,    0,    0,
    0,    0,    0,    0,    0,    0,   74,   56,   56,    0,
   56,   73,   56,    0,    0,   35,    0,    0,   64,   65,
   35,    0,    0,  161,   66,   67,    0,   68,    0,   69,
    0,   55,   70,    0,   55,   71,    0,    0,   53,    0,
   72,   53,  165,    0,   55,   55,    0,   55,    0,   55,
    0,   53,   53,    0,   53,    0,   53,    0,    0,    0,
    0,    0,   52,   64,   65,   52,    0,    0,   54,   66,
   67,   54,   68,   30,   69,   52,   52,   70,    0,    0,
   71,   54,   54,    0,   54,   72,   54,    0,    0,    0,
   64,   65,    0,    0,    0,    0,   66,   67,    0,   68,
   35,   69,   64,   65,   70,    0,    0,   71,   66,   67,
    0,   68,   72,   69,    0,    0,   70,    0,    0,   71,
    0,   64,   65,    0,   72,    0,    0,   66,   67,    0,
   68,    0,   69,    0,    0,   70,    0,    0,   71,    0,
    0,    0,    0,   72,    0,    0,    0,    0,   59,    0,
    0,    0,    0,    0,    0,   64,   65,    0,   59,    0,
    0,   66,   67,    0,   68,    0,   69,    0,    0,   70,
    0,    0,   71,    0,   64,   65,    0,   72,    0,    0,
   66,   67,    0,   68,    0,   69,    0,    0,   70,    0,
    0,   71,    0,    0,    0,    0,   72,    0,    0,    0,
    0,    0,    0,    0,    0,   30,   30,    0,    0,    0,
    0,   30,   30,    0,   30,    0,   30,    0,    0,   30,
    0,    0,   30,   64,   65,    0,    0,   30,    0,    0,
   87,    5,   35,   35,    0,    6,    0,    0,   35,   35,
    0,   35,    8,   35,    0,   72,   35,   88,   90,   35,
    0,    0,    0,    0,   35,  110,  111,  111,  115,  111,
  117,    0,    0,    0,    0,  121,  122,  123,  124,  125,
  126,  127,  128,  129,  130,  131,  132,  133,  134,  135,
  136,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  111,  146,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  150,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   44,   91,  125,   37,   62,   63,   46,   41,   42,   43,
   44,   45,   46,   47,  278,   59,  261,   44,   40,  123,
  265,   44,  264,   45,  269,   59,   60,   61,   62,   15,
   37,  276,   59,  257,   33,   42,   59,   91,   37,   46,
   47,   33,   41,   42,   43,   44,   45,   40,   47,   41,
   44,   91,   44,   39,  264,   40,   42,   91,   40,   93,
   59,   60,   61,   62,   33,   59,   41,   59,   37,   61,
  123,  264,   41,   42,   43,   44,   45,   37,   47,   53,
   93,   55,   42,   43,   91,   45,   46,   47,   41,   44,
   59,   60,   61,   62,   93,  153,   83,  155,   85,   33,
   60,   93,   62,   37,   41,  163,  123,   41,   42,   43,
   44,   45,   37,   47,  123,   41,   40,   42,   43,  265,
   45,   46,   47,  269,   93,   59,   60,   61,   62,   33,
  276,   91,   41,   37,   40,   44,   33,   41,   42,   43,
   44,   45,  265,   47,   41,   41,  269,   44,   44,   41,
  264,   41,   44,  276,   44,   59,   60,   61,   62,   93,
   40,   40,   59,   33,   61,   40,   91,   37,   40,   40,
   44,   59,   42,   43,  264,   45,   46,   47,   41,  261,
  123,   59,  264,  265,   41,  123,   59,  269,  270,   93,
   60,   61,   62,   33,  276,   59,   93,   37,  267,   41,
   33,   41,   42,   43,   37,   45,   46,   47,   41,   42,
   43,   41,   45,   46,   47,   91,   41,   41,    2,  265,
   60,   91,   62,  269,  270,  259,   91,   60,  262,   62,
  276,    2,   28,   28,   48,  257,  258,   27,  272,  273,
   33,  275,  264,  277,   37,  144,   -1,   -1,   41,   42,
   43,   91,   45,   46,   47,   -1,  118,  279,   91,   -1,
  259,    4,   -1,  262,   -1,   -1,   -1,   60,   -1,   62,
  262,   -1,   -1,  272,  273,   -1,  275,   -1,  277,   -1,
  272,   -1,   -1,   26,   27,   28,   -1,   33,   -1,   -1,
  259,   37,   -1,  262,   -1,   41,   42,   43,   91,   45,
   46,   47,   -1,  272,  273,   48,  275,   -1,  277,   -1,
   -1,   -1,   -1,   -1,   60,  275,   62,  277,   -1,   -1,
   33,   -1,   -1,   -1,   37,  259,   -1,   -1,  262,   42,
   43,   74,   45,   46,   47,   -1,   -1,   -1,  272,  273,
   -1,  275,   -1,  277,   -1,   91,   59,   60,   -1,   62,
   33,   -1,   -1,   -1,   37,  259,   -1,   -1,  262,   42,
   43,   -1,   45,   46,   47,  262,   -1,   -1,  272,  273,
   -1,  275,   33,  277,   -1,  272,   37,   60,   91,   62,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,  259,
   -1,   -1,  262,   -1,   -1,   -1,   -1,   -1,   -1,   60,
   61,   62,  272,  273,   -1,  275,   -1,  277,   91,   -1,
   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,
   -1,   -1,  262,   -1,   -1,   -1,  259,   -1,   -1,  262,
   91,   -1,  272,  273,   -1,  275,   -1,  277,   -1,  272,
  273,   -1,  275,   33,  277,   -1,   -1,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,   -1,   33,   -1,  259,   -1,   37,  262,
   60,   61,   62,   42,   43,   -1,   45,   46,   47,  272,
  273,   -1,  275,   -1,  277,   -1,   -1,   -1,   -1,   37,
   -1,   60,   33,   62,   42,   43,   -1,   45,   46,   47,
   41,   91,   43,   44,   45,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   60,  259,   62,   -1,  262,   -1,   59,   60,
   61,   62,   91,   -1,   -1,   -1,  272,  273,   33,  275,
   -1,  277,   -1,   -1,   -1,   -1,   41,   33,   43,   44,
   45,   -1,   -1,   91,   -1,   41,  259,   -1,   44,  262,
   -1,   -1,   93,   -1,   59,   60,   61,   62,   33,  272,
  273,   -1,  275,   59,  277,   61,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,
   -1,   -1,   -1,   -1,   59,   60,   61,   62,   93,  272,
  273,   40,  275,   -1,  277,   33,   45,   93,  259,   -1,
   -1,  262,   33,   41,   -1,   -1,   44,   -1,   -1,   -1,
   41,  272,  273,   44,  275,   -1,  277,   -1,   93,   -1,
   -1,   59,   60,   61,   62,   -1,   33,   -1,   59,   60,
   61,   62,   33,   -1,   41,   -1,   40,   44,   -1,   -1,
   41,   45,   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   59,   -1,   61,   93,   -1,   -1,   59,   60,
   61,   62,   93,   40,   -1,   -1,   -1,   -1,   45,  259,
   -1,   -1,  262,   -1,   -1,   40,  125,   -1,   -1,   -1,
   45,   -1,  272,  273,   -1,  275,   93,  277,   -1,   -1,
  259,   -1,   93,  262,   40,   -1,   -1,   -1,   -1,   45,
   -1,   -1,   -1,  272,  273,   -1,  275,   -1,  277,   33,
   -1,  259,   -1,   -1,   -1,   -1,   -1,   41,  259,  123,
   44,  262,   -1,   -1,   -1,  273,   -1,  275,   40,  277,
   -1,  272,  273,   45,  275,   59,  277,   61,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   40,  125,   -1,
   -1,   -1,   45,   -1,  259,   -1,   -1,  262,   -1,   -1,
  125,   -1,   -1,  259,   -1,   -1,  262,  272,  273,   93,
  275,   -1,  277,   -1,   -1,   -1,  272,  273,   40,  125,
   -1,   -1,   -1,   45,  259,   -1,   -1,  262,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   40,  272,  273,   -1,
  275,   45,  277,   -1,   -1,   40,   -1,   -1,  257,  258,
   45,   -1,   -1,  125,  263,  264,   -1,  266,   -1,  268,
   -1,  259,  271,   -1,  262,  274,   -1,   -1,  259,   -1,
  279,  262,  125,   -1,  272,  273,   -1,  275,   -1,  277,
   -1,  272,  273,   -1,  275,   -1,  277,   -1,   -1,   -1,
   -1,   -1,  259,  257,  258,  262,   -1,   -1,  259,  263,
  264,  262,  266,  125,  268,  272,  273,  271,   -1,   -1,
  274,  272,  273,   -1,  275,  279,  277,   -1,   -1,   -1,
  257,  258,   -1,   -1,   -1,   -1,  263,  264,   -1,  266,
  125,  268,  257,  258,  271,   -1,   -1,  274,  263,  264,
   -1,  266,  279,  268,   -1,   -1,  271,   -1,   -1,  274,
   -1,  257,  258,   -1,  279,   -1,   -1,  263,  264,   -1,
  266,   -1,  268,   -1,   -1,  271,   -1,   -1,  274,   -1,
   -1,   -1,   -1,  279,   -1,   -1,   -1,   -1,  262,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,   -1,  272,   -1,
   -1,  263,  264,   -1,  266,   -1,  268,   -1,   -1,  271,
   -1,   -1,  274,   -1,  257,  258,   -1,  279,   -1,   -1,
  263,  264,   -1,  266,   -1,  268,   -1,   -1,  271,   -1,
   -1,  274,   -1,   -1,   -1,   -1,  279,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,   -1,   -1,   -1,
   -1,  263,  264,   -1,  266,   -1,  268,   -1,   -1,  271,
   -1,   -1,  274,  257,  258,   -1,   -1,  279,   -1,   -1,
  264,  265,  257,  258,   -1,  269,   -1,   -1,  263,  264,
   -1,  266,  276,  268,   -1,  279,  271,   73,   74,  274,
   -1,   -1,   -1,   -1,  279,   81,   82,   83,   84,   85,
   86,   -1,   -1,   -1,   -1,   91,   92,   93,   94,   95,
   96,   97,   98,   99,  100,  101,  102,  103,  104,  105,
  106,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  118,  119,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  139,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=280;
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
"CHAR_CONSTANT","UNARY_MINUS",
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
"opt_list_fields : opt_list_fields type list_ident ';'",
"struct_def : STRUCT '{' opt_list_fields '}' list_ident ';'",
"main : VOID MAIN '(' ')' '{' opt_list_local_var opt_statements '}'",
"func_def : ID p_type '(' opt_list_param ')' '{' opt_list_local_var opt_statements '}'",
"func_def : ID VOID '(' opt_list_param ')' '{' opt_list_local_var opt_statements '}'",
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
"opt_statements :",
"opt_statements : opt_statements statement",
"statement : RETURN '(' exp ')' ';'",
"statement : READ '(' list_exp ')' ';'",
"statement : WRITE '(' list_exp ')' ';'",
"statement : IF '(' exp ')' '{' opt_statements '}'",
"statement : IF '(' exp ')' '{' opt_statements '}' ELSE '{' opt_statements '}'",
"statement : while",
"statement : exp '=' exp ';'",
"statement : ID '(' opt_list_exp ')' ';'",
"while : WHILE '(' exp ')' '{' opt_statements '}'",
"while : WHILE '(' exp ')' statement",
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
"exp : INT_CONSTANT",
"exp : ID",
"exp : CHAR_CONSTANT",
"exp : REAL_CONSTANT",
};

//#line 168 "../../src/parser/parser.y"

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
//#line 567 "Parser.java"
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
