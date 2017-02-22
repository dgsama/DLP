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
public final static short opt_list_var=281;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    2,    2,    5,    5,    6,    7,    7,    4,    4,
    3,    3,    3,    8,    8,    9,    9,   10,   10,    1,
    1,   11,   11,   11,   11,   11,   11,   11,   11,   14,
   14,   13,   13,   12,   12,   12,   12,   12,   12,   12,
   12,   12,   12,   12,   12,   12,   12,   12,   12,   12,
   12,   12,   12,   12,   12,   12,   12,
};
final static short yylen[] = {                            2,
    8,    9,    9,    2,    0,    2,    3,    1,    1,    0,
    1,    1,    1,    4,    3,    1,    2,    3,    1,    0,
    2,    5,    5,    5,    7,   11,    7,    4,    5,    0,
    1,    3,    1,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    4,    4,    3,
    4,    2,    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,   20,    0,   54,   57,
    0,    0,    0,    0,    0,    0,   56,    0,    0,    1,
   21,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   12,   13,   11,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   53,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   50,
    0,    0,    0,    0,    0,    0,    0,    0,   48,   28,
   49,   22,    0,   29,   24,   20,   23,   20,   51,    0,
    0,    0,   27,    0,   20,    0,   26,
};
final static short yydgoto[] = {                          2,
    8,    0,   34,    0,    0,    0,    0,    0,    0,    0,
   21,   22,   55,   56,
};
final static short yysindex[] = {                      -260,
 -272,    0,  -25,  -34, -101, -249,    0,  552,    0,    0,
   -2,   11,   12,   14,   15,   16,    0,  -21,  694,    0,
    0,  131,  -21,  -21,  -21,  -21,  -21,  -21,   17,  -43,
    0,    0,    0,   18,  161,  -21,  -21,  -21,  -21,  -21,
  -21,  -21,  -21,  -21,  -21,  -21,  -21,  -21,  -21,  -21,
  -21, -198,  168,  432,   23,   30,  -39,  208,  -24,  255,
  -21,  -21,    0,   41,  453,  453,   41,   76,   76,  288,
  453,   76,   76,   -6,   -6,  -43,  -43,  -43,  318,    0,
   13,  -21,   21,   22,  -41,   31,  -27,   48,    0,    0,
    0,    0,  432,    0,    0,    0,    0,    0,    0,  624,
  636, -170,    0,  -18,    0,  655,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  340,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   57,    0,    0,    0,    0,  -33,    2,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -11,   58,    0,    0,    0,    0,    0,
   57,    0,    0,  505,    9,  104,  594,  526,  563,    0,
  606,  570,  600,  460,  496,   32,   67,   97,    0,    0,
    0,    0,  411,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   -7,    0,    0,    0,    0,    0,    0,    0,
    0,  675,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  -80,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  953,   -4,   45,
};
final static int YYTABLESIZE=1035;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         55,
    1,   84,   52,   55,   82,    3,    5,   55,   55,   55,
   55,   55,   55,   55,    4,  100,   86,  101,   19,   82,
   57,    6,   59,   18,  106,   55,   55,   55,   55,   33,
   50,    7,   33,   32,   52,   48,   32,   23,   52,   52,
   49,   46,   52,   52,   52,   52,   52,   51,   52,   46,
   24,   25,   46,   26,   27,   28,   61,   55,   62,   55,
   52,   52,   52,   52,   35,   80,   82,   46,   35,   46,
   83,   92,   35,   35,   35,   35,   35,   50,   35,   94,
   95,   96,   48,   46,   51,   47,   52,   49,   99,   97,
   35,   35,   35,   35,   52,   98,  104,   30,   31,   36,
   45,   46,   44,   36,  105,   88,    0,   36,   36,   36,
   36,   36,   50,   36,    0,    0,    0,   48,   46,    0,
   47,   52,   49,    0,   35,   36,   36,   36,   36,   38,
    0,   51,    0,   38,    0,    0,   45,   38,   38,   38,
   38,   38,    0,   38,   45,    0,    0,   45,    0,    0,
    0,    0,    0,    0,    0,   38,   38,   38,   38,   36,
    0,    0,   45,   43,   45,    0,   51,   50,    0,    0,
    0,    0,   48,   46,    0,   47,   52,   49,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   38,
   45,   42,   44,   43,    0,    0,   45,   50,    0,    0,
   43,   63,   48,   46,   50,   47,   52,   49,   81,   48,
   46,    0,   47,   52,   49,    0,    0,    0,    0,    0,
   45,   51,   44,    0,    0,   55,    0,   45,   55,   44,
    0,    0,    0,    0,    0,    9,   10,    0,   55,   55,
   43,   55,   29,   55,   50,    0,    0,    0,   85,   48,
   46,   51,   47,   52,   49,    0,    0,   17,   51,    0,
   52,    0,    0,   52,    0,    0,    0,   45,    0,   44,
   46,    0,    0,   52,   52,    0,   52,    0,   52,    0,
   46,    0,    0,    0,    0,    0,    0,   43,    0,    0,
   35,   50,    0,   35,    0,   87,   48,   46,   51,   47,
   52,   49,    0,   35,   35,    0,   35,    0,   35,    0,
    0,    0,    0,    0,   45,   40,   44,   41,    0,    0,
   43,    0,    0,    0,   50,   36,    0,    0,   36,   48,
   46,    0,   47,   52,   49,    0,    0,    0,   36,   36,
    0,   36,    0,   36,    0,   51,   90,   45,    0,   44,
   43,    0,    0,    0,   50,   38,    0,    0,   38,   48,
   46,    0,   47,   52,   49,   45,    0,    0,   38,   38,
    0,   38,   55,   38,    0,   45,   55,   45,   51,   44,
    0,   55,   55,    0,   55,   55,   55,    0,    0,   36,
    0,    0,   37,    0,    0,    0,    0,    0,    0,   55,
   55,   55,   38,   39,    0,   40,    0,   41,   51,    0,
   91,    0,    0,    0,    0,    0,    0,    0,    0,   36,
    0,    0,   37,    0,    0,    0,   36,    0,    0,   37,
   55,    0,   38,   39,    0,   40,    0,   41,    0,   38,
   39,    0,   40,   51,   41,    0,    0,   51,    0,    0,
    0,    0,   51,   51,    0,   51,   51,   51,    0,    0,
    0,    0,    0,    0,   43,    0,   36,    0,   50,   37,
   51,   51,   51,   48,   46,    0,   47,   52,   49,   38,
   39,    0,   40,    0,   41,    0,    0,    0,    0,   50,
    0,   45,   34,   44,   48,   46,    0,   47,   52,   49,
   34,   51,   34,   34,   34,    0,    0,    0,    0,    0,
    0,    0,   45,   36,   44,    0,   37,    0,   34,   34,
   34,   34,   51,    0,    0,    0,   38,   39,   37,   40,
    0,   41,    0,    0,    0,    0,   37,   39,   37,   37,
   37,    0,    0,   51,    0,   39,   36,    0,   39,   37,
    0,    0,   34,    0,   37,   37,   37,   37,   44,   38,
   39,    0,   40,   39,   41,   39,   44,    0,    0,   44,
    0,    0,    0,    0,    0,    0,   36,    0,    0,   37,
    0,    0,    0,    0,   44,   44,   44,   44,   37,   38,
   39,   19,   40,    0,   41,   43,   18,   39,   55,    0,
    0,   55,   41,   43,    0,    0,   43,    0,    0,    0,
   41,   55,   55,   41,   55,    0,   55,    0,   44,    0,
    0,   43,   43,   43,   43,    0,   40,    0,   41,   41,
   41,   41,   42,    0,   40,    0,    0,   40,   47,    0,
   42,    0,    0,   42,    0,    0,   47,    0,    0,   47,
    0,    0,   40,    0,   40,   43,    0,    0,   42,   42,
   42,   42,   41,   19,   47,    0,   47,    0,   18,   51,
    0,    0,   51,    0,    0,   19,   20,    0,    0,    0,
   18,    0,   51,   51,    0,   51,   40,   51,    0,    0,
   36,    0,   42,   37,   19,    0,    0,    0,   47,   18,
    0,    0,    0,   38,   39,    0,   40,    0,   41,    0,
    0,   36,    0,    0,   25,    0,    0,    0,   34,   25,
    0,   34,    0,    0,    0,   39,    0,   40,    0,   41,
    0,   34,   34,   19,   34,    0,   34,    0,   18,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  102,    0,
    0,    0,    0,    0,   37,    0,    0,   37,    0,    0,
  103,    0,    0,   39,    0,    0,   39,   37,   37,    0,
   37,    0,   37,    0,    0,    0,   39,   39,    0,  107,
    0,    0,    0,    0,   44,    0,    0,   44,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   44,   44,   25,
   44,    0,   44,    0,    0,    0,    0,    0,    9,   10,
    0,    0,    0,    0,   11,   12,    0,   13,    0,   14,
    0,   43,   15,    0,   43,   16,    0,    0,   41,    0,
   17,   41,    0,    0,   43,   43,    0,   43,    0,   43,
    0,   41,   41,    0,   41,    0,   41,    0,    0,    0,
    0,    0,   40,    0,    0,   40,    0,    0,   42,    0,
    0,   42,    0,    0,    0,   40,   40,   47,    0,    0,
    0,   42,   42,    0,   42,    0,   42,   47,    0,    0,
    9,   10,    0,    0,    0,    0,   11,   12,    0,   13,
    0,   14,    9,   10,   15,    0,    0,   16,   11,   12,
    0,   13,   17,   14,    0,    0,   15,    0,    0,   16,
    0,    9,   10,    0,   17,    0,    0,   11,   12,    0,
   13,    0,   14,    0,    0,   15,    0,    0,   16,    0,
    0,   25,   25,   17,    0,    0,    0,   25,   25,    0,
   25,    0,   25,    0,    0,   25,    0,    0,   25,    0,
    9,   10,    0,   25,    0,    0,    0,   29,   31,    0,
    0,    0,   32,    0,    0,    0,    0,    0,    0,   33,
   30,   35,   17,    0,    0,   53,   54,   54,   58,   54,
   60,    0,    0,    0,    0,    0,    0,    0,   64,   65,
   66,   67,   68,   69,   70,   71,   72,   73,   74,   75,
   76,   77,   78,   79,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   54,   89,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   93,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
  261,   41,   46,   37,   44,  278,   41,   41,   42,   43,
   44,   45,   46,   47,   40,   96,   41,   98,   40,   44,
   25,  123,   27,   45,  105,   59,   60,   61,   62,   41,
   37,  281,   44,   41,   33,   42,   44,   40,   37,   46,
   47,   33,   41,   42,   43,   44,   45,   91,   47,   41,
   40,   40,   44,   40,   40,   40,   40,   91,   41,   93,
   59,   60,   61,   62,   33,  264,   44,   59,   37,   61,
   41,   59,   41,   42,   43,   44,   45,   37,   47,   59,
   59,  123,   42,   43,   91,   45,   46,   47,   41,   59,
   59,   60,   61,   62,   93,  123,  267,   41,   41,   33,
   60,   93,   62,   37,  123,   61,   -1,   41,   42,   43,
   44,   45,   37,   47,   -1,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   93,   59,   60,   61,   62,   33,
   -1,   91,   -1,   37,   -1,   -1,   33,   41,   42,   43,
   44,   45,   -1,   47,   41,   -1,   -1,   44,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   59,   60,   61,   62,   93,
   -1,   -1,   59,   33,   61,   -1,   91,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,
   60,   61,   62,   33,   -1,   -1,   93,   37,   -1,   -1,
   33,   41,   42,   43,   37,   45,   46,   47,   41,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,
   60,   91,   62,   -1,   -1,  259,   -1,   60,  262,   62,
   -1,   -1,   -1,   -1,   -1,  257,  258,   -1,  272,  273,
   33,  275,  264,  277,   37,   -1,   -1,   -1,   41,   42,
   43,   91,   45,   46,   47,   -1,   -1,  279,   91,   -1,
  259,   -1,   -1,  262,   -1,   -1,   -1,   60,   -1,   62,
  262,   -1,   -1,  272,  273,   -1,  275,   -1,  277,   -1,
  272,   -1,   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,
  259,   37,   -1,  262,   -1,   41,   42,   43,   91,   45,
   46,   47,   -1,  272,  273,   -1,  275,   -1,  277,   -1,
   -1,   -1,   -1,   -1,   60,  275,   62,  277,   -1,   -1,
   33,   -1,   -1,   -1,   37,  259,   -1,   -1,  262,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,  272,  273,
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
   61,   62,   33,   -1,   41,   -1,   -1,   44,   33,   -1,
   41,   -1,   -1,   44,   -1,   -1,   41,   -1,   -1,   44,
   -1,   -1,   59,   -1,   61,   93,   -1,   -1,   59,   60,
   61,   62,   93,   40,   59,   -1,   61,   -1,   45,  259,
   -1,   -1,  262,   -1,   -1,   40,  125,   -1,   -1,   -1,
   45,   -1,  272,  273,   -1,  275,   93,  277,   -1,   -1,
  259,   -1,   93,  262,   40,   -1,   -1,   -1,   93,   45,
   -1,   -1,   -1,  272,  273,   -1,  275,   -1,  277,   -1,
   -1,  259,   -1,   -1,   40,   -1,   -1,   -1,  259,   45,
   -1,  262,   -1,   -1,   -1,  273,   -1,  275,   -1,  277,
   -1,  272,  273,   40,  275,   -1,  277,   -1,   45,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  125,   -1,
   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,
  125,   -1,   -1,  259,   -1,   -1,  262,  272,  273,   -1,
  275,   -1,  277,   -1,   -1,   -1,  272,  273,   -1,  125,
   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,  125,
  275,   -1,  277,   -1,   -1,   -1,   -1,   -1,  257,  258,
   -1,   -1,   -1,   -1,  263,  264,   -1,  266,   -1,  268,
   -1,  259,  271,   -1,  262,  274,   -1,   -1,  259,   -1,
  279,  262,   -1,   -1,  272,  273,   -1,  275,   -1,  277,
   -1,  272,  273,   -1,  275,   -1,  277,   -1,   -1,   -1,
   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,  259,   -1,
   -1,  262,   -1,   -1,   -1,  272,  273,  262,   -1,   -1,
   -1,  272,  273,   -1,  275,   -1,  277,  272,   -1,   -1,
  257,  258,   -1,   -1,   -1,   -1,  263,  264,   -1,  266,
   -1,  268,  257,  258,  271,   -1,   -1,  274,  263,  264,
   -1,  266,  279,  268,   -1,   -1,  271,   -1,   -1,  274,
   -1,  257,  258,   -1,  279,   -1,   -1,  263,  264,   -1,
  266,   -1,  268,   -1,   -1,  271,   -1,   -1,  274,   -1,
   -1,  257,  258,  279,   -1,   -1,   -1,  263,  264,   -1,
  266,   -1,  268,   -1,   -1,  271,   -1,   -1,  274,   -1,
  257,  258,   -1,  279,   -1,   -1,   -1,  264,  265,   -1,
   -1,   -1,  269,   -1,   -1,   -1,   -1,   -1,   -1,  276,
   18,   19,  279,   -1,   -1,   23,   24,   25,   26,   27,
   28,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   36,   37,
   38,   39,   40,   41,   42,   43,   44,   45,   46,   47,
   48,   49,   50,   51,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   61,   62,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   82,
};
}
final static short YYFINAL=2;
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
"CHAR_CONSTANT","UNARY_MINUS","opt_list_var",
};
final static String yyrule[] = {
"$accept : main",
"main : VOID MAIN '(' ')' '{' opt_list_var opt_statements '}'",
"func : ID p_type '(' opt_list_param ')' '{' opt_list_var opt_statements '}'",
"func : ID VOID '(' opt_list_param ')' '{' opt_list_var opt_statements '}'",
"opt_list_func : opt_list_func func",
"opt_list_func :",
"param : p_type ID",
"list_param : list_param ',' param",
"list_param : param",
"opt_list_param : list_param",
"opt_list_param :",
"p_type : INT",
"p_type : DOUBLE",
"p_type : CHAR",
"vector : vector '[' INT_CONSTANT ']'",
"vector : '[' INT_CONSTANT ']'",
"ident : ID",
"ident : vector ID",
"list_ident : list_ident ',' ident",
"list_ident : ident",
"opt_statements :",
"opt_statements : opt_statements statement",
"statement : RETURN '(' exp ')' ';'",
"statement : READ '(' list_exp ')' ';'",
"statement : WRITE '(' list_exp ')' ';'",
"statement : IF '(' exp ')' '{' opt_statements '}'",
"statement : IF '(' exp ')' '{' opt_statements '}' ELSE '{' opt_statements '}'",
"statement : WHILE '(' exp ')' '{' opt_statements '}'",
"statement : exp '=' exp ';'",
"statement : ID '(' opt_list_exp ')' ';'",
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

//#line 137 "../../src/parser/parser.y"

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
//#line 520 "Parser.java"
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
