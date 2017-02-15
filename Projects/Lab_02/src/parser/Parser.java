
package parser;

import scanner.Scanner;

public class Parser {

	// * Tokens
	public final static int INT_CONSTANT = 257;
	public static final int REAL_CONSTANT = 258;
	public static final int EQ = 259;
	public static final int POW = 260;
	public static final int VOID = 261;
	public static final int OR = 262;
	public static final int RETURN = 263;
	public static final int ID = 264;
	public static final int DOUBLE = 265;
	public static final int WRITE = 266;
	public static final int ELSE = 267;
	public static final int IF = 268;
	public static final int CHAR = 269;
	public static final int STRUCT = 270;
	public static final int READ = 271;
	public static final int AND = 272;
	public static final int NOT_EQ = 273;
	public static final int WHILE = 274;
	public static final int G_EQ = 275;
	public static final int INT = 276;
	public static final int L_EQ = 277;
	public static final int MAIN = 278;
	public static final int CHAR_CONSTANT = 279;

	private Scanner scanner;

	public Parser(Scanner scanner) {
		this.scanner = scanner;
	}

}