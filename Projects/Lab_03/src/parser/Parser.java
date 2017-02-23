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
   11,   11,   12,   12,    5,    2,    6,    6,   16,   17,
   17,   15,   15,    7,    7,    7,    9,   13,   13,    8,
    8,   14,   14,   18,   18,   18,   18,   18,   18,   18,
   18,   21,   21,   22,   22,   20,   20,   19,   19,   19,
   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,
   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,
   19,
};
final static short yylen[] = {                            2,
    2,    1,    1,    1,    2,    0,    3,    3,    1,    1,
    2,    0,    4,    0,    6,    8,    9,    9,    2,    3,
    1,    1,    0,    1,    1,    1,    4,    1,    1,    3,
    1,    2,    0,    3,    5,    3,    8,   13,    1,    4,
    5,    8,    5,    0,    1,    3,    1,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    4,    4,    3,    4,    2,    3,    1,    1,    1,
    1,
};
final static short yydefred[] = {                         6,
    0,    0,    0,   25,   26,    0,   24,    1,    5,    2,
    3,    4,    0,    0,    0,    0,    0,   14,    0,    0,
   31,    0,    0,    0,    0,    0,    0,    7,    0,    8,
    0,    0,    0,   21,    0,    0,    0,   28,   29,    0,
    0,   30,   27,   19,    0,    0,   12,    0,    0,    0,
   12,   20,    0,   15,   13,   12,    0,    9,   10,    0,
   11,    0,    0,    0,   68,   71,    0,    0,    0,    0,
    0,    0,   70,    0,    0,   16,   32,    0,   39,    0,
   18,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   17,
    0,   34,    0,    0,   36,    0,    0,    0,    0,    0,
   67,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   64,    0,    0,
    0,    0,    0,    0,   62,   40,   63,   65,   41,   12,
   35,   12,   43,    0,    0,    0,    0,    0,   42,    0,
   12,    0,    0,   38,
};
final static short yydgoto[] = {                          1,
    2,    8,    9,   58,   59,   12,   60,   20,   14,   61,
   53,   26,   15,   62,   33,   34,   35,   77,   78,  113,
   79,  114,
};
final static short yysindex[] = {                         0,
    0,  174, -212,    0,    0,  -99,    0,    0,    0,    0,
    0,    0, -234, -210,  -23,   30,   40,    0,   42,  -38,
    0,  -28, -171, -180,   46, -122, -180,    0, -176,    0,
   -3, -167,   60,    0,   58,  -18, -210,    0,    0,  -89,
   66,    0,    0,    0,  -10, -180,    0,  -27,  -26,   -8,
    0,    0, -198,    0,    0,    0, -198,    0,    0, -210,
    0,  454, -198,  640,    0,    0,   79,   76,   79,   77,
   78,   80,    0,   79,  466,    0,    0,  131,    0,  669,
    0,   81,  152,   79,  318,   -4,   79,   79,   79,  -41,
   82,  190,   79,   79,   79,   79,   79,   79,   79,   79,
   79,   79,   79,   79,   79,   79,   79,   79, -142,    0,
   79,    0,   87,   92,    0,   79,  225,   12,  255,   79,
    0,  619,  591,  591,  619,  163,  163,  288,  591,  163,
  163,  236,  236,  -41,  -41,  -41,  340,    0,   95,   86,
  318,   25,   90,  681,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -198, -198,  706,  730, -117,    0,   28,
    0, -198,  750,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   61,   62,    0,    0,    0,    0,   -2,    0,
    0,    0,    0,  120,    0,    0,  120,    0,    0,    0,
    0,    0,    0,    0,  121,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  768,    0,    0,    0,  768,    0,    0,   61,
    0,    0,  768,    0,    0,    0,    0,  395,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -33,    0,  128,  -22,    0,    0,    0,    0,    2,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  128,    0,   60,    0,    0,    0,    0,    0,    0,    0,
    0,  505,  526,  699,  614,  482,  512,    0,  758,  548,
  570,  122,  475,   32,   67,   97,    0,    0,    0,  431,
  -21,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  768,  768,    0,    0,  788,    0,    0,
    0,  768,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  168,  169,    0,   57,   11,  146,    0,
  -15,    0,  153,  -56,  159,  134,    0,   43,  986,  -54,
    0,   85,
};
final static int YYTABLESIZE=1106;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         69,
   64,   23,   37,   69,  109,   29,   80,   69,   69,   69,
   69,   69,   69,   69,   86,   29,   29,   29,   47,   46,
   28,   47,   46,   18,   22,   69,   69,   69,   69,   19,
   30,   54,   55,  118,   66,   57,   47,   46,   66,  116,
   63,   31,   66,   66,   66,   66,   66,   48,   66,  108,
   49,   16,  143,   21,  115,  116,   31,   69,   13,   69,
   66,   66,   66,   66,   49,   17,    4,   23,   49,   24,
    5,    6,   49,   49,   49,   49,   49,    7,   49,   25,
   32,   27,   38,   32,    4,   31,   36,   42,    5,   43,
   49,   49,   49,   49,   66,    7,   44,  156,  157,   50,
   45,   46,   32,   50,   47,  163,   50,   50,   50,   50,
   50,   50,   51,   50,   56,   84,   87,   88,   75,   89,
  111,  138,  120,   74,   49,   50,   50,   50,   50,   52,
  116,   91,  140,   52,  154,  148,  155,   52,   52,   52,
   52,   52,    4,   52,  149,  162,    5,  150,  151,  160,
  161,   28,   29,    7,   48,   52,   52,   52,   52,   50,
   23,   22,   48,  100,   48,   48,   48,  107,   44,   10,
   11,   39,  105,  103,   21,  104,  109,  106,   40,   52,
   48,   48,   48,   48,  100,   41,  153,    0,  107,   52,
  102,   99,  101,  105,  103,  139,  104,  109,  106,  107,
    0,    0,    0,    0,  105,  103,    0,  104,  109,  106,
  112,  102,    0,  101,   48,    0,    0,    0,    0,    0,
    0,  108,  100,    0,    0,   69,  107,    0,   69,    0,
  121,  105,  103,    0,  104,  109,  106,    0,   69,   69,
    0,   69,  108,   69,    0,    0,    0,    0,    0,  102,
    0,  101,    0,  108,    0,    0,    0,  100,    0,    0,
   66,  107,    0,   66,    0,  142,  105,  103,    0,  104,
  109,  106,  107,   66,   66,    0,   66,  105,   66,    0,
  108,  109,  106,    0,  102,    0,  101,  100,    0,    0,
   49,  107,    0,   49,    0,  144,  105,  103,    0,  104,
  109,  106,    0,   49,   49,    0,   49,    0,   49,    0,
    0,    0,    0,    0,  102,  108,  101,    0,    0,    0,
  100,    0,    0,    0,  107,   50,  108,    0,   50,  105,
  103,    0,  104,  109,  106,   65,   66,    0,   50,   50,
    0,   50,   82,   50,    0,  108,  146,  102,    0,  101,
  100,    0,    0,    0,  107,   52,    0,   73,   52,  105,
  103,    0,  104,  109,  106,    0,    0,    0,   52,   52,
    0,   52,  100,   52,    0,    0,  107,  102,  108,  101,
   48,  105,  103,   48,  104,  109,  106,    0,    0,   93,
    0,    0,   94,   48,   48,    0,   48,    0,   48,  102,
    0,  101,   95,   96,    0,   97,    0,   98,  108,    0,
   93,    0,    0,   94,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   95,   96,    0,   97,   69,   98,    0,
  108,   69,  147,    0,    3,    0,   69,   69,    4,   69,
   69,   69,    5,    6,    0,    0,    0,    0,   93,    7,
    0,   94,    0,    0,   69,   69,   69,    0,    0,    0,
    0,   95,   96,   65,   97,    0,   98,   65,    0,    0,
    0,    0,   65,   65,    0,   65,   65,   65,    0,    0,
    0,    0,    0,   93,    0,   69,   94,    0,    0,    0,
   65,   65,   65,   75,    0,    0,   95,   96,   74,   97,
    0,   98,    0,    0,    0,   75,    0,   51,    0,    0,
   74,    0,    0,   93,   58,   51,   94,   51,   51,   51,
    0,   65,   58,    0,    0,   58,   95,   96,    0,   97,
    0,   98,    0,   51,   51,   51,   51,   53,    0,    0,
   58,   58,   58,   58,   57,   53,   93,    0,   53,   94,
    0,    0,   57,    0,    0,   57,    0,    0,   60,   95,
   96,    0,   97,   53,   98,   53,   60,   51,    0,   60,
   57,   57,   57,   57,   58,    0,   93,    0,   76,   94,
   55,    0,    0,    0,   60,    0,   60,    0,   55,   95,
   96,   55,   97,    0,   98,    0,    0,   53,   93,    0,
    0,   94,   56,    0,   57,    0,   55,   55,   55,   55,
   56,   95,   96,   56,   97,    0,   98,    0,   60,    0,
    0,    0,    0,    0,    0,    0,    0,  107,   56,   56,
   56,   56,  105,  103,    0,  104,  109,  106,    0,    0,
   55,    0,    0,    0,    0,    0,   54,    0,    0,    0,
  102,    0,  101,   69,   54,  107,   69,   54,    0,    0,
  105,  103,   56,  104,  109,  106,   69,   69,    0,   69,
    0,   69,   54,    0,   54,    0,    0,    0,  102,   75,
  101,  108,    0,    0,   74,    0,    0,    0,    0,   65,
    0,    0,   65,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   65,   65,    0,   65,   54,   65,   75,  108,
   65,   66,    0,   74,    0,    0,   67,   68,    0,   69,
   75,   70,   65,   66,   71,   74,    0,   72,    0,   82,
    4,   59,   73,   51,    5,    0,   51,    0,    0,   59,
   58,    7,   59,   58,   73,   75,   51,   51,    0,   51,
   74,   51,    0,   58,   58,    0,   58,   59,   58,   59,
    0,    0,    0,   53,   81,    0,   53,    0,    0,   75,
   57,    0,    0,   57,   74,    0,   53,   53,    0,    0,
    0,    0,    0,   57,   57,    0,   57,   60,   57,   75,
   61,   59,    0,  110,   74,    0,    0,   60,   61,    0,
    0,   61,    0,  152,    0,    0,   55,   33,    0,   55,
    0,    0,   33,    0,    0,    0,   61,    0,   61,   55,
   55,    0,   55,    0,   55,    0,    0,   37,   56,    0,
  158,   56,   37,    0,    0,    0,    0,    0,    0,    0,
    0,   56,   56,    0,   56,    0,   56,    0,    0,   93,
   61,    0,    0,    0,  159,    0,    0,    0,    0,    0,
    0,    0,    0,   96,    0,   97,    0,   98,    0,    0,
    0,    0,   54,    0,  164,   54,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   54,   54,    0,    0,    0,
    0,    0,   33,   97,    0,   98,   65,   66,    0,    0,
    0,    0,   67,   68,    0,   69,    0,   70,    0,    0,
   71,    0,   37,   72,    0,    0,    0,    0,   73,    0,
    0,    0,    0,    0,    0,   65,   66,    0,    0,    0,
    0,   67,   68,    0,   69,    0,   70,   65,   66,   71,
    0,    0,   72,   67,   68,    0,   69,   73,   70,    0,
    0,   71,    0,    0,   72,    0,    0,    0,    0,   73,
   59,    0,   65,   66,    0,    0,    0,    0,   67,   68,
   59,   69,    0,   70,    0,    0,   71,    0,    0,   72,
    0,    0,    0,    0,   73,    0,   65,   66,    0,    0,
    0,    0,   67,   68,    0,   69,    0,   70,    0,    0,
   71,    0,    0,   72,    0,    0,   65,   66,   73,    0,
    0,    0,   67,   68,    0,   69,    0,   70,    0,   61,
   71,    0,    0,   72,   33,   33,    0,    0,   73,   61,
   33,   33,    0,   33,    0,   33,    0,    0,   33,    0,
    0,   33,    0,    0,   37,   37,   33,    0,    0,    0,
   37,   37,   83,   37,   85,   37,    0,    0,   37,   90,
   92,   37,    0,    0,    0,    0,   37,    0,    0,   85,
    0,    0,  117,   85,  119,    0,    0,    0,  122,  123,
  124,  125,  126,  127,  128,  129,  130,  131,  132,  133,
  134,  135,  136,  137,    0,    0,   85,    0,    0,    0,
    0,  141,    0,    0,    0,  145,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   57,   91,  125,   37,   46,   44,   63,   41,   42,   43,
   44,   45,   46,   47,   69,   44,   44,   44,   41,   41,
   59,   44,   44,  123,   14,   59,   60,   61,   62,  264,
   59,   59,   59,   88,   33,   51,   59,   59,   37,   44,
   56,   44,   41,   42,   43,   44,   45,   37,   47,   91,
   40,  264,   41,  264,   59,   44,   59,   91,    2,   93,
   59,   60,   61,   62,   33,  278,  265,   91,   37,   40,
  269,  270,   41,   42,   43,   44,   45,  276,   47,   40,
   24,   40,   26,   27,  265,  257,   41,  264,  269,   93,
   59,   60,   61,   62,   93,  276,  264,  154,  155,   33,
   41,   44,   46,   37,  123,  162,   41,   41,   42,   43,
   44,   45,  123,   47,  123,   40,   40,   40,   40,   40,
   40,  264,   41,   45,   93,   59,   60,   61,   62,   33,
   44,   75,   41,   37,  150,   41,  152,   41,   42,   43,
   44,   45,  265,   47,   59,  161,  269,  123,   59,  267,
  123,   91,   91,  276,   33,   59,   60,   61,   62,   93,
   41,   41,   41,   33,   43,   44,   45,   37,   41,    2,
    2,   26,   42,   43,  264,   45,   46,   47,   26,   46,
   59,   60,   61,   62,   33,   27,  144,   -1,   37,   93,
   60,   61,   62,   42,   43,  111,   45,   46,   47,   37,
   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   59,   60,   -1,   62,   93,   -1,   -1,   -1,   -1,   -1,
   -1,   91,   33,   -1,   -1,  259,   37,   -1,  262,   -1,
   41,   42,   43,   -1,   45,   46,   47,   -1,  272,  273,
   -1,  275,   91,  277,   -1,   -1,   -1,   -1,   -1,   60,
   -1,   62,   -1,   91,   -1,   -1,   -1,   33,   -1,   -1,
  259,   37,   -1,  262,   -1,   41,   42,   43,   -1,   45,
   46,   47,   37,  272,  273,   -1,  275,   42,  277,   -1,
   91,   46,   47,   -1,   60,   -1,   62,   33,   -1,   -1,
  259,   37,   -1,  262,   -1,   41,   42,   43,   -1,   45,
   46,   47,   -1,  272,  273,   -1,  275,   -1,  277,   -1,
   -1,   -1,   -1,   -1,   60,   91,   62,   -1,   -1,   -1,
   33,   -1,   -1,   -1,   37,  259,   91,   -1,  262,   42,
   43,   -1,   45,   46,   47,  257,  258,   -1,  272,  273,
   -1,  275,  264,  277,   -1,   91,   59,   60,   -1,   62,
   33,   -1,   -1,   -1,   37,  259,   -1,  279,  262,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,  272,  273,
   -1,  275,   33,  277,   -1,   -1,   37,   60,   91,   62,
  259,   42,   43,  262,   45,   46,   47,   -1,   -1,  259,
   -1,   -1,  262,  272,  273,   -1,  275,   -1,  277,   60,
   -1,   62,  272,  273,   -1,  275,   -1,  277,   91,   -1,
  259,   -1,   -1,  262,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  272,  273,   -1,  275,   33,  277,   -1,
   91,   37,   93,   -1,  261,   -1,   42,   43,  265,   45,
   46,   47,  269,  270,   -1,   -1,   -1,   -1,  259,  276,
   -1,  262,   -1,   -1,   60,   61,   62,   -1,   -1,   -1,
   -1,  272,  273,   33,  275,   -1,  277,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,  259,   -1,   91,  262,   -1,   -1,   -1,
   60,   61,   62,   40,   -1,   -1,  272,  273,   45,  275,
   -1,  277,   -1,   -1,   -1,   40,   -1,   33,   -1,   -1,
   45,   -1,   -1,  259,   33,   41,  262,   43,   44,   45,
   -1,   91,   41,   -1,   -1,   44,  272,  273,   -1,  275,
   -1,  277,   -1,   59,   60,   61,   62,   33,   -1,   -1,
   59,   60,   61,   62,   33,   41,  259,   -1,   44,  262,
   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,   33,  272,
  273,   -1,  275,   59,  277,   61,   41,   93,   -1,   44,
   59,   60,   61,   62,   93,   -1,  259,   -1,  125,  262,
   33,   -1,   -1,   -1,   59,   -1,   61,   -1,   41,  272,
  273,   44,  275,   -1,  277,   -1,   -1,   93,  259,   -1,
   -1,  262,   33,   -1,   93,   -1,   59,   60,   61,   62,
   41,  272,  273,   44,  275,   -1,  277,   -1,   93,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   37,   59,   60,
   61,   62,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   93,   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,   -1,
   60,   -1,   62,  259,   41,   37,  262,   44,   -1,   -1,
   42,   43,   93,   45,   46,   47,  272,  273,   -1,  275,
   -1,  277,   59,   -1,   61,   -1,   -1,   -1,   60,   40,
   62,   91,   -1,   -1,   45,   -1,   -1,   -1,   -1,  259,
   -1,   -1,  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  272,  273,   -1,  275,   93,  277,   40,   91,
  257,  258,   -1,   45,   -1,   -1,  263,  264,   -1,  266,
   40,  268,  257,  258,  271,   45,   -1,  274,   -1,  264,
  265,   33,  279,  259,  269,   -1,  262,   -1,   -1,   41,
  259,  276,   44,  262,  279,   40,  272,  273,   -1,  275,
   45,  277,   -1,  272,  273,   -1,  275,   59,  277,   61,
   -1,   -1,   -1,  259,  125,   -1,  262,   -1,   -1,   40,
  259,   -1,   -1,  262,   45,   -1,  272,  273,   -1,   -1,
   -1,   -1,   -1,  272,  273,   -1,  275,  262,  277,   40,
   33,   93,   -1,  125,   45,   -1,   -1,  272,   41,   -1,
   -1,   44,   -1,  123,   -1,   -1,  259,   40,   -1,  262,
   -1,   -1,   45,   -1,   -1,   -1,   59,   -1,   61,  272,
  273,   -1,  275,   -1,  277,   -1,   -1,   40,  259,   -1,
  125,  262,   45,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  272,  273,   -1,  275,   -1,  277,   -1,   -1,  259,
   93,   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  273,   -1,  275,   -1,  277,   -1,   -1,
   -1,   -1,  259,   -1,  125,  262,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  272,  273,   -1,   -1,   -1,
   -1,   -1,  125,  275,   -1,  277,  257,  258,   -1,   -1,
   -1,   -1,  263,  264,   -1,  266,   -1,  268,   -1,   -1,
  271,   -1,  125,  274,   -1,   -1,   -1,   -1,  279,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,   -1,   -1,   -1,
   -1,  263,  264,   -1,  266,   -1,  268,  257,  258,  271,
   -1,   -1,  274,  263,  264,   -1,  266,  279,  268,   -1,
   -1,  271,   -1,   -1,  274,   -1,   -1,   -1,   -1,  279,
  262,   -1,  257,  258,   -1,   -1,   -1,   -1,  263,  264,
  272,  266,   -1,  268,   -1,   -1,  271,   -1,   -1,  274,
   -1,   -1,   -1,   -1,  279,   -1,  257,  258,   -1,   -1,
   -1,   -1,  263,  264,   -1,  266,   -1,  268,   -1,   -1,
  271,   -1,   -1,  274,   -1,   -1,  257,  258,  279,   -1,
   -1,   -1,  263,  264,   -1,  266,   -1,  268,   -1,  262,
  271,   -1,   -1,  274,  257,  258,   -1,   -1,  279,  272,
  263,  264,   -1,  266,   -1,  268,   -1,   -1,  271,   -1,
   -1,  274,   -1,   -1,  257,  258,  279,   -1,   -1,   -1,
  263,  264,   67,  266,   69,  268,   -1,   -1,  271,   74,
   75,  274,   -1,   -1,   -1,   -1,  279,   -1,   -1,   84,
   -1,   -1,   87,   88,   89,   -1,   -1,   -1,   93,   94,
   95,   96,   97,   98,   99,  100,  101,  102,  103,  104,
  105,  106,  107,  108,   -1,   -1,  111,   -1,   -1,   -1,
   -1,  116,   -1,   -1,   -1,  120,
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
"opt_list_local_var :",
"opt_list_fields : opt_list_fields type list_ident ';'",
"opt_list_fields :",
"struct_def : STRUCT '{' opt_list_fields '}' list_ident ';'",
"main : VOID MAIN '(' ')' '{' opt_list_local_var opt_statements '}'",
"func_def : p_type ID '(' opt_list_param ')' '{' opt_list_local_var opt_statements '}'",
"func_def : VOID ID '(' opt_list_param ')' '{' opt_list_local_var opt_statements '}'",
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
"opt_statements : opt_statements statement",
"opt_statements :",
"statement : RETURN exp ';'",
"statement : READ '(' list_exp ')' ';'",
"statement : WRITE list_exp ';'",
"statement : IF '(' exp ')' '{' opt_list_local_var opt_statements '}'",
"statement : IF '(' exp ')' '{' opt_list_local_var opt_statements '}' ELSE '{' opt_list_local_var opt_statements '}'",
"statement : while",
"statement : exp '=' exp ';'",
"statement : ID '(' opt_list_exp ')' ';'",
"while : WHILE '(' exp ')' '{' opt_list_local_var opt_statements '}'",
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

//#line 171 "../../src/parser/parser.y"

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
//#line 571 "Parser.java"
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
