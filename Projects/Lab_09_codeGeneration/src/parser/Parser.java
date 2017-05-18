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

public class Parser {

	boolean yydebug; // do I want debug output?
	int yynerrs; // number of errors so far
	int yyerrflag; // was there an error?
	int yychar; // the current working character

	// ########## MESSAGES ##########
	// ###############################################################
	// method: debug
	// ###############################################################
	void debug(String msg) {
		if (yydebug)
			System.out.println(msg);
	}

	// ########## STATE STACK ##########
	final static int YYSTACKSIZE = 500; // maximum stack size
	int statestk[] = new int[YYSTACKSIZE]; // state stack
	int stateptr;
	int stateptrmax; // highest index of stackptr
	int statemax; // state when highest index reached
	// ###############################################################
	// methods: state stack push,pop,drop,peek
	// ###############################################################

	final void state_push(int state) {
		try {
			stateptr++;
			statestk[stateptr] = state;
		} catch (ArrayIndexOutOfBoundsException e) {
			int oldsize = statestk.length;
			int newsize = oldsize * 2;
			int[] newstack = new int[newsize];
			System.arraycopy(statestk, 0, newstack, 0, oldsize);
			statestk = newstack;
			statestk[stateptr] = state;
		}
	}

	final int state_pop() {
		return statestk[stateptr--];
	}

	final void state_drop(int cnt) {
		stateptr -= cnt;
	}

	final int state_peek(int relative) {
		return statestk[stateptr - relative];
	}

	// ###############################################################
	// method: init_stacks : allocate and prepare stacks
	// ###############################################################
	final boolean init_stacks() {
		stateptr = -1;
		val_init();
		return true;
	}

	// ###############################################################
	// method: dump_stacks : show n levels of the stacks
	// ###############################################################
	void dump_stacks(int count) {
		int i;
		System.out.println("=index==state====value=     s:" + stateptr + "  v:" + valptr);
		for (i = 0; i < count; i++)
			System.out.println(" " + i + "    " + statestk[i] + "      " + valstk[i]);
		System.out.println("======================");
	}

	// ########## SEMANTIC VALUES ##########
	// ## **user defined:Object
	String yytext;// user variable to return contextual strings
	Object yyval; // used to return semantic vals from action routines
	Object yylval;// the 'lval' (result) I got from yylex()
	Object valstk[] = new Object[YYSTACKSIZE];
	int valptr;

	// ###############################################################
	// methods: value stack push,pop,drop,peek.
	// ###############################################################
	final void val_init() {
		yyval = new Object();
		yylval = new Object();
		valptr = -1;
	}

	final void val_push(Object val) {
		try {
			valptr++;
			valstk[valptr] = val;
		} catch (ArrayIndexOutOfBoundsException e) {
			int oldsize = valstk.length;
			int newsize = oldsize * 2;
			Object[] newstack = new Object[newsize];
			System.arraycopy(valstk, 0, newstack, 0, oldsize);
			valstk = newstack;
			valstk[valptr] = val;
		}
	}

	final Object val_pop() {
		return valstk[valptr--];
	}

	final void val_drop(int cnt) {
		valptr -= cnt;
	}

	final Object val_peek(int relative) {
		return valstk[valptr - relative];
	}

	final Object dup_yyval(Object val) {
		return val;
	}

	// #### end semantic value section ####
	public final static short INT_CONSTANT = 257;
	public final static short REAL_CONSTANT = 258;
	public final static short EQ = 259;
	public final static short POW = 260;
	public final static short VOID = 261;
	public final static short OR = 262;
	public final static short RETURN = 263;
	public final static short ID = 264;
	public final static short DOUBLE = 265;
	public final static short WRITE = 266;
	public final static short ELSE = 267;
	public final static short IF = 268;
	public final static short CHAR = 269;
	public final static short STRUCT = 270;
	public final static short READ = 271;
	public final static short AND = 272;
	public final static short NOT_EQ = 273;
	public final static short WHILE = 274;
	public final static short G_EQ = 275;
	public final static short INT = 276;
	public final static short L_EQ = 277;
	public final static short MAIN = 278;
	public final static short CHAR_CONSTANT = 279;
	public final static short INC = 280;
	public final static short DEC = 281;
	public final static short LESSTHANELSE = 282;
	public final static short CAST = 283;
	public final static short UNARY_MINUS = 284;
	public final static short YYERRCODE = 256;
	final static short yylhs[] = { -1, 0, 3, 3, 1, 1, 4, 4, 4, 10, 11, 11, 12, 12, 9, 2, 5, 5, 16, 16, 15, 15, 6, 6, 6,
			8, 13, 13, 13, 7, 7, 14, 14, 17, 17, 17, 17, 17, 17, 17, 21, 21, 20, 20, 20, 20, 20, 20, 22, 22, 19, 19, 18,
			18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, };
	final static short yylen[] = { 2, 2, 1, 1, 2, 0, 3, 3, 3, 1, 2, 0, 4, 0, 4, 8, 9, 9, 4, 2, 1, 0, 1, 1, 1, 4, 1, 1,
			1, 3, 1, 2, 0, 3, 3, 3, 1, 1, 4, 5, 7, 5, 11, 9, 9, 7, 7, 5, 1, 0, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 4, 4, 3, 4, 2, 3, 2, 1, 1, 1, 1, };
	final static short yydefred[] = { 5, 0, 0, 0, 23, 24, 0, 22, 1, 4, 2, 3, 0, 0, 0, 0, 0, 0, 13, 0, 0, 30, 0, 0, 0, 0,
			0, 0, 0, 6, 0, 7, 8, 0, 0, 0, 0, 0, 14, 26, 27, 28, 0, 0, 29, 25, 19, 0, 0, 11, 0, 0, 11, 0, 0, 12, 11, 0,
			18, 9, 0, 10, 0, 0, 0, 72, 75, 0, 0, 0, 0, 0, 0, 74, 0, 0, 0, 15, 31, 0, 36, 37, 0, 17, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 33, 0, 0, 35, 0, 0, 34, 0, 0, 70, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67, 0, 0, 0, 0, 0, 0, 38, 66, 68, 39, 32, 0, 32, 41, 0, 0, 0, 0,
			32, 45, 40, 0, 0, 32, 43, 44, 0, 42, };
	final static short yydgoto[] = { 1, 2, 8, 9, 59, 11, 60, 20, 13, 14, 61, 54, 27, 15, 62, 35, 36, 78, 79, 115, 80,
			81, 116, };
	final static short yysindex[] = { 0, 0, -152, -243, 0, 0, -119, 0, 0, 0, 0, 0, -261, -255, -255, -69, -14, -13, 0,
			-1, -42, 0, -36, 8, -211, -122, 17, -120, -122, 0, -195, 0, 0, -12, -182, 47, 50, -20, 0, 0, 0, 0, -90, 54,
			0, 0, 0, -19, -122, 0, 15, -17, 0, -157, -89, 0, 0, -89, 0, 0, -255, 0, -33, -89, -15, 0, 0, 261, 71, 261,
			79, 261, 82, 0, 261, 261, 207, 0, 0, 591, 0, 0, 3, 0, 85, 617, 261, 872, 18, 261, 24, 261, -5, -40, 86, 448,
			261, 261, 261, 261, 261, 261, 261, 261, 261, 261, 261, 261, 261, 261, 261, -134, 0, 261, 0, 88, 101, 0, 261,
			624, 0, 651, 261, 0, 124, -5, -5, 124, 94, 94, 687, 94, 94, -27, -27, -40, -40, -40, 730, 0, 110, 76, 872,
			21, 39, -40, 0, 0, 0, 0, 0, -115, 0, 0, 57, 75, 93, -112, 0, 0, 0, 113, 132, 0, 0, 0, 150, 0, };
	final static short yyrindex[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 66, 68, 69, 0, 0, 0, 0, 41, 0, 0, 0, 0, 0,
			122, 0, 0, 122, 0, 0, 0, 0, 0, 0, 0, 123, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 168, 0, 0, 168,
			0, 0, 66, 0, 0, 168, 0, 0, 0, 0, 765, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 486, 0, 127, -30, 0, 0,
			0, 0, -28, 512, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 127, 0, 134, 0, 0, 0, 0, 0, 0, 0,
			0, 834, 19, 55, 889, 843, 881, 0, 957, 985, 695, 935, 521, 547, 556, 0, 0, 0, 800, 12, 0, 0, 582, 0, 0, 0,
			0, 0, 187, 0, 0, 0, 0, 0, 243, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
	final static short yygindex[] = { 0, 0, 0, 0, 171, 0, 22, 31, 151, 152, 0, 49, 0, 161, -29, 163, 0, -68, 1179, 20,
			0, 0, 80, };
	final static int YYTABLESIZE = 1301;
	static short yytable[];

	static {
		yytable();
	}

	static void yytable() {
		yytable = new short[] { 74, 24, 30, 19, 18, 38, 111, 76, 30, 21, 109, 51, 75, 71, 51, 107, 71, 29, 74, 111, 108,
				16, 24, 31, 12, 76, 25, 26, 64, 51, 75, 71, 109, 71, 82, 17, 74, 107, 105, 28, 106, 111, 108, 76, 22,
				23, 33, 34, 75, 39, 34, 110, 30, 50, 74, 104, 50, 103, 37, 30, 64, 76, 118, 64, 110, 71, 75, 32, 118,
				44, 53, 50, 74, 50, 55, 151, 153, 117, 64, 76, 64, 45, 46, 120, 75, 30, 110, 159, 47, 88, 74, 90, 77,
				164, 48, 51, 63, 76, 94, 63, 30, 57, 75, 49, 52, 63, 56, 58, 74, 3, 83, 86, 64, 4, 63, 76, 63, 5, 6, 89,
				75, 154, 91, 156, 7, 113, 74, 122, 112, 162, 139, 109, 118, 76, 166, 149, 107, 105, 75, 106, 111, 108,
				141, 4, 150, 4, 74, 5, 63, 5, 6, 148, 155, 76, 7, 161, 7, 26, 75, 27, 28, 109, 152, 21, 20, 74, 107,
				105, 49, 106, 111, 108, 76, 10, 21, 48, 4, 75, 40, 41, 5, 6, 157, 74, 104, 110, 103, 7, 42, 0, 76, 43,
				0, 140, 0, 75, 0, 0, 158, 0, 0, 32, 0, 0, 0, 0, 0, 0, 32, 0, 0, 0, 0, 32, 0, 110, 0, 0, 160, 0, 47, 0,
				0, 0, 65, 66, 0, 47, 0, 0, 67, 68, 47, 69, 71, 70, 163, 0, 71, 0, 74, 72, 65, 66, 71, 0, 73, 76, 67, 68,
				0, 69, 75, 70, 96, 0, 71, 165, 0, 72, 65, 66, 0, 0, 73, 0, 67, 68, 99, 69, 100, 70, 101, 0, 71, 167, 46,
				72, 65, 66, 0, 64, 73, 46, 67, 68, 0, 69, 46, 70, 0, 64, 71, 32, 74, 72, 65, 66, 0, 0, 73, 76, 67, 68,
				0, 69, 75, 70, 0, 0, 71, 0, 47, 72, 65, 66, 0, 63, 73, 0, 67, 68, 0, 69, 0, 70, 0, 63, 71, 0, 0, 72, 65,
				66, 0, 0, 73, 0, 67, 68, 0, 69, 0, 70, 0, 0, 71, 0, 0, 72, 65, 66, 0, 0, 73, 0, 67, 68, 0, 69, 0, 70, 0,
				0, 71, 0, 0, 72, 46, 0, 65, 66, 73, 0, 0, 0, 67, 68, 0, 69, 0, 70, 0, 0, 71, 0, 0, 72, 0, 65, 66, 0, 73,
				0, 0, 67, 68, 0, 69, 100, 70, 101, 0, 71, 0, 0, 72, 65, 66, 0, 0, 73, 0, 67, 68, 0, 69, 0, 70, 0, 0, 71,
				0, 0, 72, 32, 32, 0, 0, 73, 0, 32, 32, 0, 32, 0, 32, 0, 0, 32, 0, 0, 32, 0, 47, 47, 0, 32, 0, 0, 47, 47,
				0, 47, 0, 47, 0, 0, 47, 0, 0, 47, 0, 0, 65, 66, 47, 0, 0, 0, 0, 84, 4, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 7,
				0, 109, 73, 0, 0, 123, 107, 105, 0, 106, 111, 108, 0, 0, 0, 0, 46, 46, 0, 0, 0, 0, 46, 46, 104, 46, 103,
				46, 0, 0, 46, 0, 0, 46, 65, 66, 0, 0, 46, 73, 0, 84, 0, 73, 73, 73, 73, 73, 73, 73, 0, 0, 0, 0, 0, 110,
				73, 0, 0, 0, 0, 73, 73, 73, 73, 69, 0, 0, 0, 69, 69, 69, 69, 69, 53, 69, 0, 0, 53, 53, 53, 53, 53, 0,
				53, 0, 0, 69, 69, 69, 69, 0, 0, 73, 0, 73, 53, 53, 53, 53, 54, 0, 0, 0, 54, 54, 54, 54, 54, 56, 54, 0,
				0, 56, 56, 56, 56, 56, 0, 56, 0, 69, 54, 54, 54, 54, 0, 0, 0, 0, 53, 56, 56, 56, 56, 65, 0, 0, 0, 65,
				65, 65, 65, 65, 109, 65, 0, 0, 0, 107, 105, 0, 106, 111, 108, 0, 54, 65, 65, 65, 65, 0, 0, 0, 0, 56, 0,
				104, 102, 103, 109, 0, 0, 0, 0, 107, 105, 109, 106, 111, 108, 143, 107, 105, 0, 106, 111, 108, 0, 0, 0,
				65, 114, 104, 0, 103, 0, 0, 110, 0, 104, 0, 103, 0, 109, 0, 0, 0, 144, 107, 105, 0, 106, 111, 108, 0, 0,
				0, 0, 0, 0, 0, 0, 96, 110, 0, 97, 104, 0, 103, 0, 110, 0, 0, 0, 0, 98, 99, 0, 100, 109, 101, 0, 0, 0,
				107, 105, 0, 106, 111, 108, 0, 52, 0, 52, 52, 52, 0, 110, 0, 0, 73, 146, 104, 73, 103, 0, 0, 0, 0, 52,
				52, 52, 52, 73, 73, 0, 73, 0, 73, 0, 0, 0, 109, 0, 0, 0, 69, 107, 105, 69, 106, 111, 108, 110, 0, 53, 0,
				0, 53, 69, 69, 0, 69, 52, 69, 104, 0, 103, 53, 53, 0, 53, 0, 53, 0, 0, 0, 73, 0, 0, 0, 54, 73, 73, 54,
				73, 73, 73, 0, 0, 56, 0, 0, 56, 54, 54, 110, 54, 147, 54, 73, 73, 73, 56, 56, 0, 56, 0, 56, 0, 0, 0, 68,
				0, 0, 0, 65, 68, 68, 65, 68, 68, 68, 0, 0, 96, 0, 0, 97, 65, 65, 73, 65, 0, 65, 68, 68, 68, 98, 99, 0,
				100, 0, 101, 0, 0, 0, 0, 0, 0, 57, 96, 0, 57, 97, 0, 0, 0, 96, 62, 0, 97, 62, 0, 98, 99, 68, 100, 57,
				101, 57, 98, 99, 0, 100, 0, 101, 62, 62, 62, 62, 0, 0, 0, 109, 96, 0, 0, 97, 107, 105, 0, 106, 111, 108,
				0, 0, 61, 98, 99, 61, 100, 57, 101, 0, 58, 0, 104, 58, 103, 0, 62, 0, 0, 0, 61, 61, 61, 61, 0, 0, 96, 0,
				58, 97, 58, 0, 0, 0, 52, 0, 0, 52, 0, 98, 99, 0, 100, 110, 101, 0, 0, 52, 52, 0, 52, 0, 52, 0, 61, 0,
				55, 0, 55, 55, 55, 0, 58, 0, 0, 0, 0, 0, 0, 96, 0, 0, 97, 0, 55, 55, 55, 55, 59, 0, 0, 59, 98, 99, 0,
				100, 0, 101, 0, 0, 0, 0, 0, 0, 0, 0, 59, 59, 59, 59, 0, 0, 0, 0, 73, 0, 60, 73, 55, 60, 0, 0, 0, 0, 0,
				0, 0, 73, 73, 0, 73, 0, 73, 0, 60, 60, 60, 60, 0, 0, 59, 0, 0, 0, 0, 0, 0, 0, 0, 68, 0, 0, 68, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 68, 68, 0, 68, 0, 68, 60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 57, 0, 0, 57, 0,
				0, 0, 0, 0, 62, 0, 0, 62, 57, 57, 0, 0, 0, 0, 0, 0, 0, 62, 62, 0, 62, 0, 62, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 96, 0, 0, 97, 0, 0, 0, 0, 0, 61, 0, 0, 61, 98, 99, 0, 100, 58, 101, 0, 58, 0, 61, 61, 0, 61, 0, 61,
				0, 0, 58, 58, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 55, 0, 0, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 55, 55, 0, 55, 0, 55, 0, 0, 0, 59, 0, 0, 59, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 59, 59, 0, 59, 0, 59, 0, 0, 0, 0, 0, 0, 0, 0, 0, 60, 0, 85, 60, 87, 0, 87, 0, 0, 92, 93, 95,
				0, 60, 60, 0, 60, 0, 60, 0, 0, 87, 0, 0, 119, 0, 121, 0, 0, 0, 0, 124, 125, 126, 127, 128, 129, 130,
				131, 132, 133, 134, 135, 136, 137, 138, 0, 0, 87, 0, 0, 0, 0, 142, 0, 0, 0, 145, };
	}

	static short yycheck[];

	static {
		yycheck();
	}

	static void yycheck() {
		yycheck = new short[] { 33, 91, 44, 264, 123, 125, 46, 40, 44, 264, 37, 41, 45, 41, 44, 42, 44, 59, 33, 46, 47,
				264, 91, 59, 2, 40, 40, 40, 57, 59, 45, 59, 37, 61, 63, 278, 33, 42, 43, 40, 45, 46, 47, 40, 13, 14,
				257, 25, 45, 27, 28, 91, 44, 41, 33, 60, 44, 62, 41, 44, 41, 40, 44, 44, 91, 93, 45, 59, 44, 264, 48,
				59, 33, 42, 59, 143, 144, 59, 59, 40, 61, 93, 264, 59, 45, 44, 91, 155, 41, 69, 33, 71, 125, 161, 44,
				41, 41, 40, 76, 44, 59, 52, 45, 123, 123, 56, 123, 264, 33, 261, 125, 40, 93, 265, 59, 40, 61, 269, 270,
				40, 45, 150, 40, 152, 276, 40, 33, 41, 125, 158, 264, 37, 44, 40, 163, 59, 42, 43, 45, 45, 46, 47, 41,
				265, 123, 265, 33, 269, 93, 269, 270, 41, 267, 40, 276, 267, 276, 91, 45, 91, 91, 37, 123, 41, 41, 33,
				42, 43, 41, 45, 46, 47, 40, 2, 264, 41, 265, 45, 27, 27, 269, 270, 125, 33, 60, 91, 62, 276, 27, -1, 40,
				28, -1, 113, -1, 45, -1, -1, 123, -1, -1, 33, -1, -1, -1, -1, -1, -1, 40, -1, -1, -1, -1, 45, -1, 91,
				-1, -1, 125, -1, 33, -1, -1, -1, 257, 258, -1, 40, -1, -1, 263, 264, 45, 266, 262, 268, 123, -1, 271,
				-1, 33, 274, 257, 258, 272, -1, 279, 40, 263, 264, -1, 266, 45, 268, 259, -1, 271, 125, -1, 274, 257,
				258, -1, -1, 279, -1, 263, 264, 273, 266, 275, 268, 277, -1, 271, 125, 33, 274, 257, 258, -1, 262, 279,
				40, 263, 264, -1, 266, 45, 268, -1, 272, 271, 125, 33, 274, 257, 258, -1, -1, 279, 40, 263, 264, -1,
				266, 45, 268, -1, -1, 271, -1, 125, 274, 257, 258, -1, 262, 279, -1, 263, 264, -1, 266, -1, 268, -1,
				272, 271, -1, -1, 274, 257, 258, -1, -1, 279, -1, 263, 264, -1, 266, -1, 268, -1, -1, 271, -1, -1, 274,
				257, 258, -1, -1, 279, -1, 263, 264, -1, 266, -1, 268, -1, -1, 271, -1, -1, 274, 125, -1, 257, 258, 279,
				-1, -1, -1, 263, 264, -1, 266, -1, 268, -1, -1, 271, -1, -1, 274, -1, 257, 258, -1, 279, -1, -1, 263,
				264, -1, 266, 275, 268, 277, -1, 271, -1, -1, 274, 257, 258, -1, -1, 279, -1, 263, 264, -1, 266, -1,
				268, -1, -1, 271, -1, -1, 274, 257, 258, -1, -1, 279, -1, 263, 264, -1, 266, -1, 268, -1, -1, 271, -1,
				-1, 274, -1, 257, 258, -1, 279, -1, -1, 263, 264, -1, 266, -1, 268, -1, -1, 271, -1, -1, 274, -1, -1,
				257, 258, 279, -1, -1, -1, -1, 264, 265, -1, -1, -1, 269, -1, -1, -1, -1, -1, -1, 276, -1, 37, 279, -1,
				-1, 41, 42, 43, -1, 45, 46, 47, -1, -1, -1, -1, 257, 258, -1, -1, -1, -1, 263, 264, 60, 266, 62, 268,
				-1, -1, 271, -1, -1, 274, 257, 258, -1, -1, 279, 37, -1, 264, -1, 41, 42, 43, 44, 45, 46, 47, -1, -1,
				-1, -1, -1, 91, 279, -1, -1, -1, -1, 59, 60, 61, 62, 37, -1, -1, -1, 41, 42, 43, 44, 45, 37, 47, -1, -1,
				41, 42, 43, 44, 45, -1, 47, -1, -1, 59, 60, 61, 62, -1, -1, 91, -1, 93, 59, 60, 61, 62, 37, -1, -1, -1,
				41, 42, 43, 44, 45, 37, 47, -1, -1, 41, 42, 43, 44, 45, -1, 47, -1, 93, 59, 60, 61, 62, -1, -1, -1, -1,
				93, 59, 60, 61, 62, 37, -1, -1, -1, 41, 42, 43, 44, 45, 37, 47, -1, -1, -1, 42, 43, -1, 45, 46, 47, -1,
				93, 59, 60, 61, 62, -1, -1, -1, -1, 93, -1, 60, 61, 62, 37, -1, -1, -1, -1, 42, 43, 37, 45, 46, 47, 41,
				42, 43, -1, 45, 46, 47, -1, -1, -1, 93, 59, 60, -1, 62, -1, -1, 91, -1, 60, -1, 62, -1, 37, -1, -1, -1,
				41, 42, 43, -1, 45, 46, 47, -1, -1, -1, -1, -1, -1, -1, -1, 259, 91, -1, 262, 60, -1, 62, -1, 91, -1,
				-1, -1, -1, 272, 273, -1, 275, 37, 277, -1, -1, -1, 42, 43, -1, 45, 46, 47, -1, 41, -1, 43, 44, 45, -1,
				91, -1, -1, 259, 59, 60, 262, 62, -1, -1, -1, -1, 59, 60, 61, 62, 272, 273, -1, 275, -1, 277, -1, -1,
				-1, 37, -1, -1, -1, 259, 42, 43, 262, 45, 46, 47, 91, -1, 259, -1, -1, 262, 272, 273, -1, 275, 93, 277,
				60, -1, 62, 272, 273, -1, 275, -1, 277, -1, -1, -1, 37, -1, -1, -1, 259, 42, 43, 262, 45, 46, 47, -1,
				-1, 259, -1, -1, 262, 272, 273, 91, 275, 93, 277, 60, 61, 62, 272, 273, -1, 275, -1, 277, -1, -1, -1,
				37, -1, -1, -1, 259, 42, 43, 262, 45, 46, 47, -1, -1, 259, -1, -1, 262, 272, 273, 91, 275, -1, 277, 60,
				61, 62, 272, 273, -1, 275, -1, 277, -1, -1, -1, -1, -1, -1, 41, 259, -1, 44, 262, -1, -1, -1, 259, 41,
				-1, 262, 44, -1, 272, 273, 91, 275, 59, 277, 61, 272, 273, -1, 275, -1, 277, 59, 60, 61, 62, -1, -1, -1,
				37, 259, -1, -1, 262, 42, 43, -1, 45, 46, 47, -1, -1, 41, 272, 273, 44, 275, 93, 277, -1, 41, -1, 60,
				44, 62, -1, 93, -1, -1, -1, 59, 60, 61, 62, -1, -1, 259, -1, 59, 262, 61, -1, -1, -1, 259, -1, -1, 262,
				-1, 272, 273, -1, 275, 91, 277, -1, -1, 272, 273, -1, 275, -1, 277, -1, 93, -1, 41, -1, 43, 44, 45, -1,
				93, -1, -1, -1, -1, -1, -1, 259, -1, -1, 262, -1, 59, 60, 61, 62, 41, -1, -1, 44, 272, 273, -1, 275, -1,
				277, -1, -1, -1, -1, -1, -1, -1, -1, 59, 60, 61, 62, -1, -1, -1, -1, 259, -1, 41, 262, 93, 44, -1, -1,
				-1, -1, -1, -1, -1, 272, 273, -1, 275, -1, 277, -1, 59, 60, 61, 62, -1, -1, 93, -1, -1, -1, -1, -1, -1,
				-1, -1, 259, -1, -1, 262, -1, -1, -1, -1, -1, -1, -1, -1, -1, 272, 273, -1, 275, -1, 277, 93, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 259, -1, -1, 262, -1, -1, -1, -1, -1, 259, -1, -1, 262,
				272, 273, -1, -1, -1, -1, -1, -1, -1, 272, 273, -1, 275, -1, 277, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, 259, -1, -1, 262, -1, -1, -1, -1, -1, 259, -1, -1, 262, 272, 273, -1, 275, 259, 277, -1, 262, -1,
				272, 273, -1, 275, -1, 277, -1, -1, 272, 273, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 259, -1, -1, 262, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, 272, 273, -1, 275, -1, 277, -1, -1, -1, 259, -1, -1, 262, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, 272, 273, -1, 275, -1, 277, -1, -1, -1, -1, -1, -1, -1, -1, -1, 259, -1, 67, 262, 69, -1,
				71, -1, -1, 74, 75, 76, -1, 272, 273, -1, 275, -1, 277, -1, -1, 86, -1, -1, 89, -1, 91, -1, -1, -1, -1,
				96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, -1, -1, 113, -1, -1, -1, -1, 118,
				-1, -1, -1, 122, };
	}

	final static short YYFINAL = 1;
	final static short YYMAXTOKEN = 284;
	final static String yyname[] = { "end-of-file", null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, "'!'", null, null, null, "'%'", null, null, "'('", "')'", "'*'", "'+'", "','", "'-'",
			"'.'", "'/'", null, null, null, null, null, null, null, null, null, null, null, "';'", "'<'", "'='", "'>'",
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, "'['", null, "']'", null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, "'{'", null, "'}'", null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			"INT_CONSTANT", "REAL_CONSTANT", "EQ", "POW", "VOID", "OR", "RETURN", "ID", "DOUBLE", "WRITE", "ELSE", "IF",
			"CHAR", "STRUCT", "READ", "AND", "NOT_EQ", "WHILE", "G_EQ", "INT", "L_EQ", "MAIN", "CHAR_CONSTANT", "INC",
			"DEC", "LESSTHANELSE", "CAST", "UNARY_MINUS", };
	final static String yyrule[] = { "$accept : program", "program : opt_def_glob_var main", "glob_def : var_def",
			"glob_def : func_def", "opt_def_glob_var : opt_def_glob_var glob_def", "opt_def_glob_var :",
			"var_def : p_type list_ident ';'", "var_def : array list_ident ';'", "var_def : struct_type list_ident ';'",
			"local_var_def : var_def", "opt_list_local_var : opt_list_local_var local_var_def", "opt_list_local_var :",
			"opt_list_fields : opt_list_fields type list_ident ';'", "opt_list_fields :",
			"struct_type : STRUCT '{' opt_list_fields '}'",
			"main : VOID MAIN '(' ')' '{' opt_list_local_var statements '}'",
			"func_def : p_type ID '(' opt_list_param ')' '{' opt_list_local_var statements '}'",
			"func_def : VOID ID '(' opt_list_param ')' '{' opt_list_local_var statements '}'",
			"list_param : list_param ',' p_type ID", "list_param : p_type ID", "opt_list_param : list_param",
			"opt_list_param :", "p_type : INT", "p_type : DOUBLE", "p_type : CHAR", "array : type '[' INT_CONSTANT ']'",
			"type : p_type", "type : array", "type : struct_type", "list_ident : list_ident ',' ID", "list_ident : ID",
			"statements : statements statement", "statements :", "statement : RETURN exp ';'",
			"statement : READ list_exp ';'", "statement : WRITE list_exp ';'", "statement : if_else",
			"statement : while", "statement : exp '=' exp ';'", "statement : ID '(' opt_list_exp ')' ';'",
			"while : WHILE '(' exp ')' '{' statements '}'", "while : WHILE '(' exp ')' statement",
			"if_else : IF '(' exp ')' '{' statements '}' ELSE '{' statements '}'",
			"if_else : IF '(' exp ')' '{' statements '}' ELSE statement",
			"if_else : IF '(' exp ')' statement ELSE '{' statements '}'",
			"if_else : IF '(' exp ')' statement ELSE statement", "if_else : IF '(' exp ')' '{' statements '}'",
			"if_else : IF '(' exp ')' statement", "opt_list_exp : list_exp", "opt_list_exp :",
			"list_exp : list_exp ',' exp", "list_exp : exp", "exp : exp '+' exp", "exp : exp '*' exp",
			"exp : exp '/' exp", "exp : exp '-' exp", "exp : exp '%' exp", "exp : exp EQ exp", "exp : exp NOT_EQ exp",
			"exp : exp '>' exp", "exp : exp '<' exp", "exp : exp L_EQ exp", "exp : exp G_EQ exp", "exp : exp AND exp",
			"exp : exp OR exp", "exp : '(' p_type ')' exp", "exp : exp '[' exp ']'", "exp : exp '.' ID",
			"exp : ID '(' opt_list_exp ')'", "exp : '-' exp", "exp : '(' exp ')'", "exp : '!' exp",
			"exp : INT_CONSTANT", "exp : ID", "exp : CHAR_CONSTANT", "exp : REAL_CONSTANT", };

	// #line 224 "../../src/parser/parser.y"

	// * Code
	// Members of the generated Parser class
	// We must implement at least:
	// - int yylex()
	// - void yyerror(String)

	// * Lexical analyzer
	private Scanner scanner;

	// * Invocation to the scanner
	private int yylex() {
		int token = 0;
		try {
			token = scanner.yylex();
			this.yylval = scanner.getYylval();
		} catch (Throwable e) {
			System.err.println(
					"Lexical error in line " + scanner.getLine() + " and column " + scanner.getColumn() + ":\n\t" + e);
		}
		return token;
	}

	// * Syntax error handler
	public void yyerror(String error) {
		ErrorHandler.getInstance().addError(new Err(scanner.getLine(), scanner.getColumn(),
				"Syntax error -> " + yylex() + " (" + scanner.yytext() + ")"));
	}

	// * Constructor
	public Parser(Scanner scanner) {
		this.scanner = scanner;
		// this.yydebug = true;
	}

	private ArrayType getArrayDef(Type type, int length, int line) {
		if (type instanceof ArrayType) {
			Type head = type;
			Type prev = type;
			Type actual = ((ArrayType) type).getTypeOf();
			while (actual instanceof ArrayType) {
				prev = actual;
				actual = ((ArrayType) actual).getTypeOf();
			}
			actual = (Type) new ArrayType(line, scanner.getColumn(), actual, length);
			((ArrayType) prev).setTypeOf(actual);

			return (ArrayType) head;

		} else {
			return new ArrayType(line, scanner.getColumn(), type, length);
		}
	}

	private void addVarDefs(List<Definition> defsList, Type type, List<String> idents, int line) {
		for (String id : idents) {
			defsList.add(new DefVar(line, scanner.getColumn(), type, id));
		}
	}

	private void addVarDefs(List<Definition> defsList, StructType type, List<String> idents, int line) {
		for (String id : idents) {
			defsList.add(new DefVar(line, scanner.getColumn(),
					new StructType(type.getLine(), type.getColumn(), type.getFieldsDefinitions(), id), id));
		}
	}

	private void mergeDefs(List<Definition> defsA, Object defB) {
		if (defB instanceof List) {
			for (Definition def : (List<Definition>) defB) {
				defsA.add(def);
			}
		} else {
			defsA.add((Definition) defB);
		}
	}

	private void addFieldDefs(List<Definition> defsList, Type type, List<String> idents, int line) {
		for (String s : idents) {
			defsList.add(new DefField(line, scanner.getColumn(), type, s));
		}
	}

	private ASTNode root;

	public ASTNode getRoot() {
		return this.root;
	}

	// #line 714 "Parser.java"
	// ###############################################################
	// method: yylexdebug : check lexer state
	// ###############################################################
	void yylexdebug(int state, int ch) {
		String s = null;
		if (ch < 0)
			ch = 0;
		if (ch <= YYMAXTOKEN) // check index bounds
			s = yyname[ch]; // now get it
		if (s == null)
			s = "illegal-symbol";
		debug("state " + state + ", reading " + ch + " (" + s + ")");
	}

	// The following are now global, to aid in error reporting
	int yyn; // next next thing to do
	int yym; //
	int yystate; // current parsing state from state table
	String yys; // current token string

	// ###############################################################
	// method: yyparse : parse input and execute indicated items
	// ###############################################################
	int yyparse() {
		boolean doaction;
		init_stacks();
		yynerrs = 0;
		yyerrflag = 0;
		yychar = -1; // impossible char forces a read
		yystate = 0; // initial state
		state_push(yystate); // save it
		val_push(yylval); // save empty value
		while (true) // until parsing is done, either correctly, or w/error
		{
			doaction = true;
			if (yydebug)
				debug("loop");
			// #### NEXT ACTION (from reduction table)
			for (yyn = yydefred[yystate]; yyn == 0; yyn = yydefred[yystate]) {
				if (yydebug)
					debug("yyn:" + yyn + "  state:" + yystate + "  yychar:" + yychar);
				if (yychar < 0) // we want a char?
				{
					yychar = yylex(); // get next token
					if (yydebug)
						debug(" next yychar:" + yychar);
					// #### ERROR CHECK ####
					if (yychar < 0) // it it didn't work/error
					{
						yychar = 0; // change it to default string (no -1!)
						if (yydebug)
							yylexdebug(yystate, yychar);
					}
				} // yychar<0
				yyn = yysindex[yystate]; // get amount to shift by (shift index)
				if ((yyn != 0) && (yyn += yychar) >= 0 && yyn <= YYTABLESIZE && yycheck[yyn] == yychar) {
					if (yydebug)
						debug("state " + yystate + ", shifting to state " + yytable[yyn]);
					// #### NEXT STATE ####
					yystate = yytable[yyn];// we are in a new state
					state_push(yystate); // save it
					val_push(yylval); // push our lval as the input for next
										// rule
					yychar = -1; // since we have 'eaten' a token, say we need
									// another
					if (yyerrflag > 0) // have we recovered an error?
						--yyerrflag; // give ourselves credit
					doaction = false; // but don't process yet
					break; // quit the yyn=0 loop
				}

				yyn = yyrindex[yystate]; // reduce
				if ((yyn != 0) && (yyn += yychar) >= 0 && yyn <= YYTABLESIZE && yycheck[yyn] == yychar) { // we
																											// reduced!
					if (yydebug)
						debug("reduce");
					yyn = yytable[yyn];
					doaction = true; // get ready to execute
					break; // drop down to actions
				} else // ERROR RECOVERY
				{
					if (yyerrflag == 0) {
						yyerror("syntax error");
						yynerrs++;
					}
					if (yyerrflag < 3) // low error count?
					{
						yyerrflag = 3;
						while (true) // do until break
						{
							if (stateptr < 0) // check for under & overflow here
							{
								yyerror("stack underflow. aborting..."); // note
																			// lower
																			// case
																			// 's'
								return 1;
							}
							yyn = yysindex[state_peek(0)];
							if ((yyn != 0) && (yyn += YYERRCODE) >= 0 && yyn <= YYTABLESIZE
									&& yycheck[yyn] == YYERRCODE) {
								if (yydebug)
									debug("state " + state_peek(0) + ", error recovery shifting to state "
											+ yytable[yyn] + " ");
								yystate = yytable[yyn];
								state_push(yystate);
								val_push(yylval);
								doaction = false;
								break;
							} else {
								if (yydebug)
									debug("error recovery discarding state " + state_peek(0) + " ");
								if (stateptr < 0) // check for under & overflow
													// here
								{
									yyerror("Stack underflow. aborting..."); // capital
																				// 'S'
									return 1;
								}
								state_pop();
								val_pop();
							}
						}
					} else // discard this token
					{
						if (yychar == 0)
							return 1; // yyabort
						if (yydebug) {
							yys = null;
							if (yychar <= YYMAXTOKEN)
								yys = yyname[yychar];
							if (yys == null)
								yys = "illegal-symbol";
							debug("state " + yystate + ", error recovery discards token " + yychar + " (" + yys + ")");
						}
						yychar = -1; // read another
					}
				} // end error recovery
			} // yyn=0 loop
			if (!doaction) // any reason not to proceed?
				continue; // skip action
			yym = yylen[yyn]; // get count of terminals on rhs
			if (yydebug)
				debug("state " + yystate + ", reducing " + yym + " by rule " + yyn + " (" + yyrule[yyn] + ")");
			if (yym > 0) // if count of rhs not 'nil'
				yyval = val_peek(yym - 1); // get current semantic value
			yyval = dup_yyval(yyval); // duplicate yyval if ParserVal is used as
										// semantic value
			switch (yyn) {
			// ########## USER-SUPPLIED ACTIONS ##########
			case 1:
			// #line 98 "../../src/parser/parser.y"
			{
				((List<Definition>) val_peek(1)).add((Definition) val_peek(0));
				this.root = new Program(scanner.getLine(), scanner.getColumn(), (List<Definition>) val_peek(1));
			}
				break;
			case 2:
			// #line 101 "../../src/parser/parser.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 3:
			// #line 102 "../../src/parser/parser.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 4:
			// #line 105 "../../src/parser/parser.y"
			{
				yyval = val_peek(1);
				mergeDefs((List<Definition>) yyval, val_peek(0));
			}
				break;
			case 5:
			// #line 106 "../../src/parser/parser.y"
			{
				yyval = new ArrayList<Definition>();
			}
				break;
			case 6:
			// #line 109 "../../src/parser/parser.y"
			{
				yyval = new ArrayList<Definition>();
				addVarDefs((List<Definition>) yyval, (Type) val_peek(2), (List<String>) val_peek(1), scanner.getLine());
			}
				break;
			case 7:
			// #line 110 "../../src/parser/parser.y"
			{
				yyval = new ArrayList<Definition>();
				addVarDefs((List<Definition>) yyval, (Type) val_peek(2), (List<String>) val_peek(1), scanner.getLine());
			}
				break;
			case 8:
			// #line 111 "../../src/parser/parser.y"
			{
				yyval = new ArrayList<Definition>();
				addVarDefs((List<Definition>) yyval, (StructType) val_peek(2), (List<String>) val_peek(1),
						scanner.getLine());
			}
				break;
			case 9:
			// #line 115 "../../src/parser/parser.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 10:
			// #line 118 "../../src/parser/parser.y"
			{
				yyval = val_peek(1);
				mergeDefs((List<Definition>) yyval, val_peek(0));
			}
				break;
			case 11:
			// #line 119 "../../src/parser/parser.y"
			{
				yyval = new ArrayList<Definition>();
			}
				break;
			case 12:
			// #line 122 "../../src/parser/parser.y"
			{
				yyval = val_peek(3);
				addFieldDefs((List<Definition>) yyval, (Type) val_peek(2), (List<String>) val_peek(1),
						scanner.getLine());
			}
				break;
			case 13:
			// #line 123 "../../src/parser/parser.y"
			{
				yyval = new ArrayList<Definition>();
			}
				break;
			case 14:
			// #line 127 "../../src/parser/parser.y"
			{
				yyval = new StructType(scanner.getLine(), scanner.getColumn(), (List<Definition>) val_peek(1));
			}
				break;
			case 15:
			// #line 130 "../../src/parser/parser.y"
			{
				yyval = new DefFunc(scanner.getLine(), scanner.getColumn(),
						(Type) new FuncType(scanner.getLine(), scanner.getColumn(), (Type) VoidType.getInstance(),
								(List<Definition>) new ArrayList()),
						"main", (List<Definition>) val_peek(2), (List<Statement>) val_peek(1));
			}
				break;
			case 16:
			// #line 133 "../../src/parser/parser.y"
			{
				yyval = new DefFunc(scanner.getLine(), scanner.getColumn(),
						(Type) new FuncType(scanner.getLine(), scanner.getColumn(), (Type) val_peek(8),
								(List<Definition>) val_peek(5)),
						(String) val_peek(7), (List<Definition>) val_peek(2), (List<Statement>) val_peek(1));
			}
				break;
			case 17:
			// #line 134 "../../src/parser/parser.y"
			{
				yyval = new DefFunc(scanner.getLine(), scanner.getColumn(),
						(Type) new FuncType(scanner.getLine(), scanner.getColumn(), (Type) VoidType.getInstance(),
								(List<Definition>) val_peek(5)),
						(String) val_peek(7), (List<Definition>) val_peek(2), (List<Statement>) val_peek(1));
			}
				break;
			case 18:
			// #line 139 "../../src/parser/parser.y"
			{
				((List<Definition>) yyval).add(
						new DefVar(scanner.getLine(), scanner.getColumn(), (Type) val_peek(1), (String) val_peek(0)));
				yyval = val_peek(3);
			}
				break;
			case 19:
			// #line 140 "../../src/parser/parser.y"
			{
				yyval = new ArrayList<Definition>();
				((List<Definition>) yyval).add(
						new DefVar(scanner.getLine(), scanner.getColumn(), (Type) val_peek(1), (String) val_peek(0)));
			}
				break;
			case 20:
			// #line 143 "../../src/parser/parser.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 21:
			// #line 144 "../../src/parser/parser.y"
			{
				yyval = new ArrayList<Definition>();
			}
				break;
			case 22:
			// #line 147 "../../src/parser/parser.y"
			{
				yyval = IntType.getInstance();
			}
				break;
			case 23:
			// #line 148 "../../src/parser/parser.y"
			{
				yyval = RealType.getInstance();
			}
				break;
			case 24:
			// #line 149 "../../src/parser/parser.y"
			{
				yyval = CharType.getInstance();
			}
				break;
			case 25:
			// #line 152 "../../src/parser/parser.y"
			{
				yyval = getArrayDef((Type) val_peek(3), (Integer) val_peek(1), scanner.getLine());
			}
				break;
			case 26:
			// #line 155 "../../src/parser/parser.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 27:
			// #line 156 "../../src/parser/parser.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 28:
			// #line 157 "../../src/parser/parser.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 29:
			// #line 160 "../../src/parser/parser.y"
			{
				((List<String>) yyval).add((String) val_peek(0));
				yyval = val_peek(2);
			}
				break;
			case 30:
			// #line 161 "../../src/parser/parser.y"
			{
				yyval = new ArrayList<String>();
				((List<String>) yyval).add((String) val_peek(0));
			}
				break;
			case 31:
			// #line 164 "../../src/parser/parser.y"
			{
				((List<Statement>) yyval).add((Statement) val_peek(0));
				yyval = val_peek(1);
			}
				break;
			case 32:
			// #line 165 "../../src/parser/parser.y"
			{
				yyval = new ArrayList<Statement>();
			}
				break;
			case 33:
			// #line 168 "../../src/parser/parser.y"
			{
				yyval = new Return(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(1));
			}
				break;
			case 34:
			// #line 169 "../../src/parser/parser.y"
			{
				yyval = new Read(scanner.getLine(), scanner.getColumn(), (List<Expression>) val_peek(1));
			}
				break;
			case 35:
			// #line 170 "../../src/parser/parser.y"
			{
				yyval = new Write(scanner.getLine(), scanner.getColumn(), (List<Expression>) val_peek(1));
			}
				break;
			case 36:
			// #line 171 "../../src/parser/parser.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 37:
			// #line 172 "../../src/parser/parser.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 38:
			// #line 173 "../../src/parser/parser.y"
			{
				yyval = new Assignment(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(3),
						(Expression) val_peek(1));
			}
				break;
			case 39:
			// #line 174 "../../src/parser/parser.y"
			{
				yyval = new InvocationSt(scanner.getLine(), scanner.getColumn(), (String) val_peek(4),
						(List<Expression>) val_peek(2));
			}
				break;
			case 40:
			// #line 177 "../../src/parser/parser.y"
			{
				yyval = new While(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(4),
						(List<Statement>) val_peek(1));
			}
				break;
			case 41:
			// #line 178 "../../src/parser/parser.y"
			{
				yyval = new While(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(2),
						(Statement) val_peek(0));
			}
				break;
			case 42:
			// #line 181 "../../src/parser/parser.y"
			{
				yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(8),
						(List<Statement>) val_peek(5), (List<Statement>) val_peek(1));
			}
				break;
			case 43:
			// #line 182 "../../src/parser/parser.y"
			{
				yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(6),
						(List<Statement>) val_peek(3), (Statement) val_peek(0));
			}
				break;
			case 44:
			// #line 183 "../../src/parser/parser.y"
			{
				yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(6),
						(Statement) val_peek(4), (List<Statement>) val_peek(1));
			}
				break;
			case 45:
			// #line 184 "../../src/parser/parser.y"
			{
				yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(4),
						(Statement) val_peek(2), (Statement) val_peek(0));
			}
				break;
			case 46:
			// #line 185 "../../src/parser/parser.y"
			{
				yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(4),
						(List<Statement>) val_peek(1), new ArrayList());
			}
				break;
			case 47:
			// #line 186 "../../src/parser/parser.y"
			{
				yyval = new IfElse(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(2),
						(Statement) val_peek(0), new ArrayList());
			}
				break;
			case 48:
			// #line 189 "../../src/parser/parser.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 49:
			// #line 190 "../../src/parser/parser.y"
			{
				yyval = new ArrayList<Expression>();
			}
				break;
			case 50:
			// #line 193 "../../src/parser/parser.y"
			{
				yyval = val_peek(2);
				((List<Expression>) yyval).add((Expression) val_peek(0));
			}
				break;
			case 51:
			// #line 194 "../../src/parser/parser.y"
			{
				yyval = new ArrayList<Expression>();
				((List<Expression>) yyval).add((Expression) val_peek(0));
			}
				break;
			case 52:
			// #line 197 "../../src/parser/parser.y"
			{
				yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(2),
						(Expression) val_peek(0), "+");
			}
				break;
			case 53:
			// #line 198 "../../src/parser/parser.y"
			{
				yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(2),
						(Expression) val_peek(0), "*");
			}
				break;
			case 54:
			// #line 199 "../../src/parser/parser.y"
			{
				yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(2),
						(Expression) val_peek(0), "/");
			}
				break;
			case 55:
			// #line 200 "../../src/parser/parser.y"
			{
				yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(2),
						(Expression) val_peek(0), "-");
			}
				break;
			case 56:
			// #line 201 "../../src/parser/parser.y"
			{
				yyval = new ArithmeticOperation(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(2),
						(Expression) val_peek(0), "%");
			}
				break;
			case 57:
			// #line 202 "../../src/parser/parser.y"
			{
				yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(2),
						(Expression) val_peek(0), "==");
			}
				break;
			case 58:
			// #line 203 "../../src/parser/parser.y"
			{
				yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(2),
						(Expression) val_peek(0), "!=");
			}
				break;
			case 59:
			// #line 204 "../../src/parser/parser.y"
			{
				yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(2),
						(Expression) val_peek(0), ">");
			}
				break;
			case 60:
			// #line 205 "../../src/parser/parser.y"
			{
				yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(2),
						(Expression) val_peek(0), "<");
			}
				break;
			case 61:
			// #line 206 "../../src/parser/parser.y"
			{
				yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(2),
						(Expression) val_peek(0), "<=");
			}
				break;
			case 62:
			// #line 207 "../../src/parser/parser.y"
			{
				yyval = new CompOperation(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(2),
						(Expression) val_peek(0), ">=");
			}
				break;
			case 63:
			// #line 208 "../../src/parser/parser.y"
			{
				yyval = new LogicOperation(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(2),
						(Expression) val_peek(0), "&&");
			}
				break;
			case 64:
			// #line 209 "../../src/parser/parser.y"
			{
				yyval = new LogicOperation(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(2),
						(Expression) val_peek(0), "||");
			}
				break;
			case 65:
			// #line 210 "../../src/parser/parser.y"
			{
				yyval = new Cast(scanner.getLine(), scanner.getColumn(), (Type) val_peek(2), (Expression) val_peek(0));
			}
				break;
			case 66:
			// #line 211 "../../src/parser/parser.y"
			{
				yyval = new ArrayAccess(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(3),
						(Expression) val_peek(1));
			}
				break;
			case 67:
			// #line 212 "../../src/parser/parser.y"
			{
				yyval = new StructAccess(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(2),
						(String) val_peek(0));
			}
				break;
			case 68:
			// #line 213 "../../src/parser/parser.y"
			{
				yyval = new InvocationExp(scanner.getLine(), scanner.getColumn(), (String) val_peek(3),
						(List<Expression>) val_peek(1));
			}
				break;
			case 69:
			// #line 214 "../../src/parser/parser.y"
			{
				yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(0));
			}
				break;
			case 70:
			// #line 215 "../../src/parser/parser.y"
			{
				yyval = val_peek(1);
			}
				break;
			case 71:
			// #line 216 "../../src/parser/parser.y"
			{
				yyval = new UnaryNot(scanner.getLine(), scanner.getColumn(), (Expression) val_peek(0));
			}
				break;
			case 72:
			// #line 217 "../../src/parser/parser.y"
			{
				yyval = new LiteralInt(scanner.getLine(), scanner.getColumn(), (Integer) val_peek(0));
			}
				break;
			case 73:
			// #line 218 "../../src/parser/parser.y"
			{
				yyval = new Variable(scanner.getLine(), scanner.getColumn(), (String) val_peek(0));
			}
				break;
			case 74:
			// #line 219 "../../src/parser/parser.y"
			{
				yyval = new LiteralChar(scanner.getLine(), scanner.getColumn(), (Character) val_peek(0));
			}
				break;
			case 75:
			// #line 220 "../../src/parser/parser.y"
			{
				yyval = new LiteralReal(scanner.getLine(), scanner.getColumn(), (Double) val_peek(0));
			}
				break;
			// #line 1163 "Parser.java"
			// ########## END OF USER-SUPPLIED ACTIONS ##########
			}// switch
				// #### Now let's reduce... ####
			if (yydebug)
				debug("reduce");
			state_drop(yym); // we just reduced yylen states
			yystate = state_peek(0); // get new state
			val_drop(yym); // corresponding value drop
			yym = yylhs[yyn]; // select next TERMINAL(on lhs)
			if (yystate == 0 && yym == 0)// done? 'rest' state and at first
											// TERMINAL
			{
				if (yydebug)
					debug("After reduction, shifting from state 0 to state " + YYFINAL + "");
				yystate = YYFINAL; // explicitly say we're done
				state_push(YYFINAL); // and save it
				val_push(yyval); // also save the semantic value of parsing
				if (yychar < 0) // we want another character?
				{
					yychar = yylex(); // get next character
					if (yychar < 0)
						yychar = 0; // clean, if necessary
					if (yydebug)
						yylexdebug(yystate, yychar);
				}
				if (yychar == 0) // Good exit (if lex returns 0 ;-)
					break; // quit the loop--all DONE
			} // if yystate
			else // else not done yet
			{ // get next state and push, for next yydefred[]
				yyn = yygindex[yym]; // find out where to go
				if ((yyn != 0) && (yyn += yystate) >= 0 && yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
					yystate = yytable[yyn]; // get new state
				else
					yystate = yydgoto[yym]; // else go to new defred
				if (yydebug)
					debug("after reduction, shifting from state " + state_peek(0) + " to state " + yystate + "");
				state_push(yystate); // going again, so push state & val...
				val_push(yyval); // for next action
			}
		} // main loop
		return 0;// yyaccept!!
	}
	// ## end of method parse() ######################################

	// ## run() --- for Thread #######################################
	/**
	 * A default run method, used for operating this parser object in the
	 * background. It is intended for extending Thread or implementing Runnable.
	 * Turn off with -Jnorun .
	 */
	public void run() {
		yyparse();
	}
	// ## end of method run() ########################################

	// ## Constructors ###############################################
	/**
	 * Default constructor. Turn off with -Jnoconstruct .
	 * 
	 */
	public Parser() {
		// nothing to do
	}

	/**
	 * Create a parser, setting the debug to true or false.
	 * 
	 * @param debugMe
	 *            true for debugging, false for no debug.
	 */
	public Parser(boolean debugMe) {
		yydebug = debugMe;
	}
	// ###############################################################

}
// ################### END OF CLASS ##############################
