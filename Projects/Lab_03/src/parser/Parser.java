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
   22,   22,   21,   21,   21,   21,   21,   23,   23,   20,
   20,   19,   19,   19,   19,   19,   19,   19,   19,   19,
   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,
   19,   19,   19,   19,   19,   19,
};
final static short yylen[] = {                            2,
    2,    1,    1,    1,    2,    0,    3,    3,    1,    1,
    2,    0,    4,    0,    6,    8,    9,    9,    2,    3,
    1,    1,    0,    1,    1,    1,    4,    1,    1,    3,
    1,    2,    0,    3,    5,    3,    1,    1,    4,    5,
    7,    5,   11,    9,    9,    7,   12,    0,    1,    3,
    1,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    4,    4,    3,    4,    2,
    3,    2,    1,    1,    1,    1,
};
final static short yydefred[] = {                         6,
    0,    0,    0,   25,   26,    0,   24,    1,    5,    2,
    3,    4,    0,    0,    0,    0,    0,   14,    0,    0,
   31,    0,    0,    0,    0,    0,    0,    7,    0,    8,
    0,    0,    0,   21,    0,    0,    0,   28,   29,    0,
    0,   30,   27,   19,    0,    0,   12,    0,    0,    0,
   12,   20,    0,   15,   13,   12,    0,    9,   10,    0,
   11,    0,    0,    0,   73,   76,    0,    0,    0,    0,
    0,    0,   75,    0,    0,   16,    0,   32,    0,   37,
   38,    0,   18,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   17,    0,   34,    0,    0,   36,    0,    0,
    0,    0,    0,   71,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   68,    0,    0,    0,    0,    0,    0,   66,   39,   67,
   69,   40,   33,    0,   35,   33,   42,    0,    0,    0,
    0,   33,   46,   41,    0,    0,    0,   33,   44,    0,
   45,    0,    0,   43,    0,   47,
};
final static short yydgoto[] = {                          1,
    2,    8,    9,   58,   59,   12,   32,   20,   14,   61,
   53,   26,   15,   62,   33,   34,   35,   78,   79,  116,
   80,   81,  117,
};
final static short yysindex[] = {                         0,
    0, -134, -256,    0,    0, -106,    0,    0,    0,    0,
    0,    0, -240, -236,  -57,    6,   11,    0,   27,  -40,
    0,   -9, -188, -120,   42, -122, -120,    0, -184,    0,
   -6, -175,   70,    0,   54,  -11, -236,    0,    0,  -90,
   82,    0,    0,    0,   28, -120,    0,    1,   50,   29,
    0,    0, -255,    0,    0,    0, -255,    0,    0, -236,
    0,  -33, -255,  -15,    0,    0,  243,   94,  243,  110,
  119,  121,    0,  243,  243,    0,  207,    0,  615,    0,
    0,    3,    0,  124,  636,  243,  802,   59,  243,  243,
  243,   -5,  -44,  125,  674,  243,  243,  243,  243,  243,
  243,  243,  243,  243,  243,  243,  243,  243,  243,  243,
  243,  -97,    0,  243,    0,  126,  127,    0,  243,  709,
   15,  739,  243,    0,   31,   -5,   -5,   31,  525,  525,
  772,   -5,  525,  525,   16,   16,  -44,  -44,  -44,  824,
    0,  130,  117,  802,   21,  120,   39,    0,    0,    0,
    0,    0,    0,  -89,    0,    0,    0,   57,   75,   93,
 -162,    0,    0,    0,  113,  140,  132,    0,    0,  243,
    0,  150,  879,    0,  187,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   90,   95,    0,    0,    0,    0,   73,    0,
    0,    0,    0,  143,    0,    0,  143,    0,    0,    0,
    0,    0,    0,    0,  144,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  168,    0,    0,    0,  168,    0,    0,   90,
    0,    0,  168,    0,    0,    0,    0,  915,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  451,    0,  146,   41,    0,    0,    0,
    0,  -28,  486,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  146,    0,  147,    0,    0,    0,    0,
    0,    0,    0,    0,   96,   55,   80, 1096,  994, 1029,
    0, 1087, 1051, 1073,  606,  964,  516,  551,  581,    0,
    0,    0,  957,   60,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  189,  190,    0,   18,   12,  167,    0,
   74,    0,  170,    7,  172,  148,    0, -136, 1287,  -63,
    0,    0,   83,
};
final static int YYTABLESIZE=1457;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         74,
   23,  112,   37,   29,   72,   88,   77,   16,  154,    4,
  157,   75,   72,    5,    6,   72,   18,   74,   28,   13,
    7,   17,  163,   19,   77,   22,  121,   21,  169,   75,
   72,  110,   72,   23,   29,   74,  108,  106,  176,  107,
  112,  109,   77,   38,   29,   24,  111,   75,   48,   30,
   25,   49,  110,   74,  105,  146,  104,  108,  119,   54,
   77,  112,  109,   64,   72,   75,   27,  110,   31,   82,
   60,   74,  108,  106,   60,  107,  112,  109,   77,   42,
   60,   51,   36,   75,   51,  111,   43,   64,   44,   74,
  105,   76,  104,   29,   94,   64,   77,   46,   64,   51,
   50,   75,  119,   50,  165,  166,  111,   74,   55,   83,
   45,   47,   63,   64,   77,   64,   31,  118,   50,   75,
   63,  111,   50,   63,   57,   74,    3,  113,   57,   63,
    4,   31,   77,   86,    5,    6,   57,   75,   63,   57,
   63,    7,    4,  153,    4,   74,    5,   64,    5,   89,
   51,   56,   77,    7,   57,    7,   57,   75,   90,  158,
   91,  156,  160,  114,   74,  123,  141,  143,  167,  119,
  151,   77,   63,   21,  172,  152,   75,  159,  155,  170,
   28,  161,   74,   23,   22,   29,   48,   49,   57,   77,
   10,   11,   39,   52,   75,   40,  142,  162,   41,    0,
   33,    0,    0,    0,    0,    0,    0,   33,    0,    0,
    0,    0,   33,    0,    0,    0,    0,  164,    0,   74,
    0,    0,    0,   65,   66,    0,   77,    0,    0,   67,
   68,   75,   69,   72,   70,  168,    0,   71,    0,   74,
   72,   65,   66,   72,    0,   73,   77,   67,   68,    0,
   69,   75,   70,   96,    0,   71,  171,    0,   72,   65,
   66,    0,    0,   73,    0,   67,   68,   99,   69,  100,
   70,  101,    0,   71,  174,   74,   72,   65,   66,    0,
    0,   73,   77,   67,   68,    0,   69,   75,   70,    0,
    0,   71,   33,    0,   72,   65,   66,    0,    0,   73,
    0,   67,   68,    0,   69,  100,   70,  101,    0,   71,
    0,    0,   72,   65,   66,    0,   64,   73,    0,   67,
   68,    0,   69,    0,   70,    0,   64,   71,    0,    0,
   72,   65,   66,    0,    0,   73,    0,   67,   68,    0,
   69,   63,   70,    0,    0,   71,    0,    0,   72,   65,
   66,   63,    0,   73,   57,   67,   68,   57,   69,    0,
   70,    0,    0,   71,    0,    0,   72,   57,   57,   65,
   66,   73,    0,    0,    0,   67,   68,    0,   69,    0,
   70,    0,    0,   71,    0,    0,   72,    0,   65,   66,
    0,   73,    0,    0,   67,   68,    0,   69,    0,   70,
    0,    0,   71,    0,    0,   72,   65,   66,    0,    0,
   73,    0,   67,   68,    0,   69,    0,   70,    0,    0,
   71,    0,    0,   72,   33,   33,    0,    0,   73,    0,
   33,   33,    0,   33,    0,   33,    0,    0,   33,    0,
    0,   33,    0,   65,   66,    0,   33,    0,    0,   67,
   68,    0,   69,    0,   70,    0,    0,   71,    0,    0,
   72,    0,    0,   65,   66,   73,    0,    0,    0,    0,
   84,    4,    0,    0,    0,    5,    0,    0,    0,    0,
    0,    0,    7,   74,    0,   73,    0,   74,    0,    0,
    0,   74,   74,   74,   74,   74,   74,   74,    0,   65,
   66,    0,    0,    0,    0,    0,   84,    0,    0,   74,
   74,   74,   74,    0,    0,    0,    0,    0,   70,    0,
    0,   73,   70,    0,    0,    0,   70,   70,   70,   70,
   70,    0,   70,    0,    0,    0,    0,    0,    0,    0,
    0,   74,    0,   74,   70,   70,   70,   70,   53,    0,
    0,    0,   53,    0,    0,    0,   53,   53,   53,   53,
   53,  110,   53,    0,    0,    0,  108,  106,    0,  107,
  112,  109,    0,    0,   53,   53,   53,   53,   70,    0,
    0,    0,    0,   54,    0,    0,    0,   54,    0,    0,
    0,   54,   54,   54,   54,   54,    0,   54,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   53,   54,
   54,   54,   54,   56,    0,  111,    0,   56,    0,    0,
    0,   56,   56,   56,   56,   56,    0,   56,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   52,   56,
   56,   56,   56,   54,    0,    0,   52,  103,   52,   52,
   52,  110,    0,    0,    0,    0,  108,  106,    0,  107,
  112,  109,    0,    0,   52,   52,   52,   52,  103,    0,
    0,    0,  110,   56,  105,  102,  104,  108,  106,    0,
  107,  112,  109,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  115,  105,    0,  104,   52,    0,
    0,    0,    0,    0,    0,  111,  103,    0,    0,   74,
  110,    0,   74,    0,  124,  108,  106,    0,  107,  112,
  109,    0,   74,   74,    0,   74,  111,   74,    0,    0,
    0,    0,    0,  105,    0,  104,    0,    0,    0,    0,
    0,  103,    0,    0,   70,  110,    0,   70,    0,  145,
  108,  106,    0,  107,  112,  109,    0,   70,   70,    0,
   70,    0,   70,    0,  111,    0,    0,    0,  105,    0,
  104,  103,    0,    0,   53,  110,    0,   53,    0,  147,
  108,  106,    0,  107,  112,  109,    0,   53,   53,    0,
   53,    0,   53,    0,    0,    0,    0,    0,  105,  111,
  104,    0,    0,    0,  103,    0,    0,    0,  110,   54,
    0,    0,   54,  108,  106,    0,  107,  112,  109,    0,
    0,    0,   54,   54,    0,   54,    0,   54,    0,  111,
  149,  105,    0,  104,  103,    0,    0,    0,  110,   56,
    0,    0,   56,  108,  106,    0,  107,  112,  109,    0,
    0,    0,   56,   56,    0,   56,  103,   56,    0,    0,
  110,  105,  111,  104,   52,  108,  106,   52,  107,  112,
  109,    0,    0,   96,    0,    0,   97,   52,   52,    0,
   52,    0,   52,  105,    0,  104,   98,   99,    0,  100,
    0,  101,  111,    0,   96,    0,    0,   97,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   98,   99,    0,
  100,  103,  101,    0,  111,  110,  150,    0,    0,  175,
  108,  106,    0,  107,  112,  109,    0,    0,    0,    0,
    0,    0,   96,    0,    0,   97,    0,    0,  105,    0,
  104,    0,    0,    0,    0,   98,   99,   74,  100,    0,
  101,   74,    0,    0,    0,    0,   74,   74,    0,   74,
   74,   74,    0,    0,    0,    0,    0,   96,    0,  111,
   97,    0,    0,    0,   74,   74,   74,    0,    0,    0,
   98,   99,    0,  100,    0,  101,    0,    0,    0,   69,
    0,    0,    0,   69,    0,    0,   55,   96,   69,   69,
   97,   69,   69,   69,   55,   74,   55,   55,   55,    0,
   98,   99,    0,  100,    0,  101,   69,   69,   69,    0,
    0,    0,   55,   55,   55,   55,   62,    0,    0,    0,
   96,    0,    0,   97,   62,    0,    0,   62,    0,    0,
    0,    0,    0,   98,   99,    0,  100,   69,  101,    0,
    0,    0,   62,   62,   62,   62,   55,    0,    0,    0,
   96,   61,    0,   97,    0,    0,    0,    0,    0,   61,
    0,    0,   61,   98,   99,    0,  100,    0,  101,    0,
    0,    0,   96,   59,    0,   97,   62,   61,   61,   61,
   61,   59,    0,    0,   59,   98,   99,    0,  100,    0,
  101,    0,    0,    0,    0,   60,    0,    0,    0,   59,
   59,   59,   59,   60,    0,    0,   60,    0,    0,   65,
    0,   61,    0,    0,    0,    0,    0,   65,   58,    0,
   65,   60,   60,   60,   60,    0,   58,   96,    0,   58,
   97,    0,    0,   59,    0,   65,    0,   65,    0,    0,
   98,   99,    0,  100,   58,  101,   58,    0,    0,    0,
    0,    0,    0,    0,    0,   60,    0,    0,    0,    0,
    0,    0,    0,   74,    0,    0,   74,    0,    0,   65,
    0,    0,    0,    0,    0,    0,   74,   74,   58,   74,
    0,   74,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   69,    0,    0,   69,    0,
    0,    0,   55,    0,    0,   55,    0,    0,   69,   69,
    0,   69,    0,   69,    0,   55,   55,    0,   55,    0,
   55,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   62,    0,    0,   62,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   62,   62,    0,   62,    0,
   62,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   61,    0,    0,
   61,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   61,   61,    0,   61,    0,   61,    0,    0,    0,   59,
    0,    0,   59,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   59,   59,    0,   59,    0,   59,    0,    0,
    0,   60,    0,    0,   60,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   60,   60,    0,   60,   65,   60,
    0,    0,    0,   85,   58,   87,    0,   58,   65,    0,
   92,   93,    0,   95,    0,    0,    0,   58,   58,    0,
    0,    0,   87,    0,    0,  120,   87,  122,    0,    0,
    0,    0,  125,  126,  127,  128,  129,  130,  131,  132,
  133,  134,  135,  136,  137,  138,  139,  140,    0,    0,
   87,    0,    0,    0,    0,  144,    0,    0,    0,  148,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  173,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   91,   46,  125,   44,   33,   69,   40,  264,  145,  265,
  147,   45,   41,  269,  270,   44,  123,   33,   59,    2,
  276,  278,  159,  264,   40,   14,   90,  264,  165,   45,
   59,   37,   61,   91,   44,   33,   42,   43,  175,   45,
   46,   47,   40,   26,   44,   40,   91,   45,   37,   59,
   40,   40,   37,   33,   60,   41,   62,   42,   44,   59,
   40,   46,   47,   57,   93,   45,   40,   37,  257,   63,
   53,   33,   42,   43,   57,   45,   46,   47,   40,  264,
   63,   41,   41,   45,   44,   91,   93,   33,  264,   33,
   60,  125,   62,   44,   77,   41,   40,   44,   44,   59,
   41,   45,   44,   44,  267,  268,   91,   33,   59,  125,
   41,  123,   33,   59,   40,   61,   44,   59,   59,   45,
   41,   91,   41,   44,   51,   33,  261,  125,   33,   56,
  265,   59,   40,   40,  269,  270,   41,   45,   59,   44,
   61,  276,  265,  123,  265,   33,  269,   93,  269,   40,
  123,  123,   40,  276,   59,  276,   61,   45,   40,  153,
   40,  123,  156,   40,   33,   41,  264,   41,  162,   44,
   41,   40,   93,  264,  168,   59,   45,  267,   59,   40,
   91,  125,   33,   41,   41,   91,   41,   41,   93,   40,
    2,    2,   26,   46,   45,   26,  114,  123,   27,   -1,
   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,
   -1,   -1,   45,   -1,   -1,   -1,   -1,  125,   -1,   33,
   -1,   -1,   -1,  257,  258,   -1,   40,   -1,   -1,  263,
  264,   45,  266,  262,  268,  123,   -1,  271,   -1,   33,
  274,  257,  258,  272,   -1,  279,   40,  263,  264,   -1,
  266,   45,  268,  259,   -1,  271,  125,   -1,  274,  257,
  258,   -1,   -1,  279,   -1,  263,  264,  273,  266,  275,
  268,  277,   -1,  271,  125,   33,  274,  257,  258,   -1,
   -1,  279,   40,  263,  264,   -1,  266,   45,  268,   -1,
   -1,  271,  125,   -1,  274,  257,  258,   -1,   -1,  279,
   -1,  263,  264,   -1,  266,  275,  268,  277,   -1,  271,
   -1,   -1,  274,  257,  258,   -1,  262,  279,   -1,  263,
  264,   -1,  266,   -1,  268,   -1,  272,  271,   -1,   -1,
  274,  257,  258,   -1,   -1,  279,   -1,  263,  264,   -1,
  266,  262,  268,   -1,   -1,  271,   -1,   -1,  274,  257,
  258,  272,   -1,  279,  259,  263,  264,  262,  266,   -1,
  268,   -1,   -1,  271,   -1,   -1,  274,  272,  273,  257,
  258,  279,   -1,   -1,   -1,  263,  264,   -1,  266,   -1,
  268,   -1,   -1,  271,   -1,   -1,  274,   -1,  257,  258,
   -1,  279,   -1,   -1,  263,  264,   -1,  266,   -1,  268,
   -1,   -1,  271,   -1,   -1,  274,  257,  258,   -1,   -1,
  279,   -1,  263,  264,   -1,  266,   -1,  268,   -1,   -1,
  271,   -1,   -1,  274,  257,  258,   -1,   -1,  279,   -1,
  263,  264,   -1,  266,   -1,  268,   -1,   -1,  271,   -1,
   -1,  274,   -1,  257,  258,   -1,  279,   -1,   -1,  263,
  264,   -1,  266,   -1,  268,   -1,   -1,  271,   -1,   -1,
  274,   -1,   -1,  257,  258,  279,   -1,   -1,   -1,   -1,
  264,  265,   -1,   -1,   -1,  269,   -1,   -1,   -1,   -1,
   -1,   -1,  276,   33,   -1,  279,   -1,   37,   -1,   -1,
   -1,   41,   42,   43,   44,   45,   46,   47,   -1,  257,
  258,   -1,   -1,   -1,   -1,   -1,  264,   -1,   -1,   59,
   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   33,   -1,
   -1,  279,   37,   -1,   -1,   -1,   41,   42,   43,   44,
   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   91,   -1,   93,   59,   60,   61,   62,   33,   -1,
   -1,   -1,   37,   -1,   -1,   -1,   41,   42,   43,   44,
   45,   37,   47,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   59,   60,   61,   62,   93,   -1,
   -1,   -1,   -1,   33,   -1,   -1,   -1,   37,   -1,   -1,
   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,   59,
   60,   61,   62,   33,   -1,   91,   -1,   37,   -1,   -1,
   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   33,   59,
   60,   61,   62,   93,   -1,   -1,   41,   33,   43,   44,
   45,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   59,   60,   61,   62,   33,   -1,
   -1,   -1,   37,   93,   60,   61,   62,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   59,   60,   -1,   62,   93,   -1,
   -1,   -1,   -1,   -1,   -1,   91,   33,   -1,   -1,  259,
   37,   -1,  262,   -1,   41,   42,   43,   -1,   45,   46,
   47,   -1,  272,  273,   -1,  275,   91,  277,   -1,   -1,
   -1,   -1,   -1,   60,   -1,   62,   -1,   -1,   -1,   -1,
   -1,   33,   -1,   -1,  259,   37,   -1,  262,   -1,   41,
   42,   43,   -1,   45,   46,   47,   -1,  272,  273,   -1,
  275,   -1,  277,   -1,   91,   -1,   -1,   -1,   60,   -1,
   62,   33,   -1,   -1,  259,   37,   -1,  262,   -1,   41,
   42,   43,   -1,   45,   46,   47,   -1,  272,  273,   -1,
  275,   -1,  277,   -1,   -1,   -1,   -1,   -1,   60,   91,
   62,   -1,   -1,   -1,   33,   -1,   -1,   -1,   37,  259,
   -1,   -1,  262,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,  272,  273,   -1,  275,   -1,  277,   -1,   91,
   59,   60,   -1,   62,   33,   -1,   -1,   -1,   37,  259,
   -1,   -1,  262,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,  272,  273,   -1,  275,   33,  277,   -1,   -1,
   37,   60,   91,   62,  259,   42,   43,  262,   45,   46,
   47,   -1,   -1,  259,   -1,   -1,  262,  272,  273,   -1,
  275,   -1,  277,   60,   -1,   62,  272,  273,   -1,  275,
   -1,  277,   91,   -1,  259,   -1,   -1,  262,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,   -1,
  275,   33,  277,   -1,   91,   37,   93,   -1,   -1,   41,
   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,
   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,   60,   -1,
   62,   -1,   -1,   -1,   -1,  272,  273,   33,  275,   -1,
  277,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,   -1,  259,   -1,   91,
  262,   -1,   -1,   -1,   60,   61,   62,   -1,   -1,   -1,
  272,  273,   -1,  275,   -1,  277,   -1,   -1,   -1,   33,
   -1,   -1,   -1,   37,   -1,   -1,   33,  259,   42,   43,
  262,   45,   46,   47,   41,   91,   43,   44,   45,   -1,
  272,  273,   -1,  275,   -1,  277,   60,   61,   62,   -1,
   -1,   -1,   59,   60,   61,   62,   33,   -1,   -1,   -1,
  259,   -1,   -1,  262,   41,   -1,   -1,   44,   -1,   -1,
   -1,   -1,   -1,  272,  273,   -1,  275,   91,  277,   -1,
   -1,   -1,   59,   60,   61,   62,   93,   -1,   -1,   -1,
  259,   33,   -1,  262,   -1,   -1,   -1,   -1,   -1,   41,
   -1,   -1,   44,  272,  273,   -1,  275,   -1,  277,   -1,
   -1,   -1,  259,   33,   -1,  262,   93,   59,   60,   61,
   62,   41,   -1,   -1,   44,  272,  273,   -1,  275,   -1,
  277,   -1,   -1,   -1,   -1,   33,   -1,   -1,   -1,   59,
   60,   61,   62,   41,   -1,   -1,   44,   -1,   -1,   33,
   -1,   93,   -1,   -1,   -1,   -1,   -1,   41,   33,   -1,
   44,   59,   60,   61,   62,   -1,   41,  259,   -1,   44,
  262,   -1,   -1,   93,   -1,   59,   -1,   61,   -1,   -1,
  272,  273,   -1,  275,   59,  277,   61,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,   93,
   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,   93,  275,
   -1,  277,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,
   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,  272,  273,
   -1,  275,   -1,  277,   -1,  272,  273,   -1,  275,   -1,
  277,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  259,   -1,   -1,  262,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  272,  273,   -1,  275,   -1,
  277,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,
  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  272,  273,   -1,  275,   -1,  277,   -1,   -1,   -1,  259,
   -1,   -1,  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  272,  273,   -1,  275,   -1,  277,   -1,   -1,
   -1,  259,   -1,   -1,  262,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  272,  273,   -1,  275,  262,  277,
   -1,   -1,   -1,   67,  259,   69,   -1,  262,  272,   -1,
   74,   75,   -1,   77,   -1,   -1,   -1,  272,  273,   -1,
   -1,   -1,   86,   -1,   -1,   89,   90,   91,   -1,   -1,
   -1,   -1,   96,   97,   98,   99,  100,  101,  102,  103,
  104,  105,  106,  107,  108,  109,  110,  111,   -1,   -1,
  114,   -1,   -1,   -1,   -1,  119,   -1,   -1,   -1,  123,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  170,
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
"if_else : IF '(' exp ')' '{' statements '}' IF '(' exp ')' statement",
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

//#line 181 "../../src/parser/parser.y"

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
//#line 650 "Parser.java"
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
