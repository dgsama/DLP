package scanner;

import parser.Parser;
import java.io.FileReader;
import java.io.IOException;

import scanner.Scanner;

public class ScannerTest {

	public static void main(String args[]) throws IOException {
		String fileName = "input.txt";
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
		} catch (IOException io) {
			System.err.println("The file " + fileName + " could not be opened.");
			return;
		}

		Scanner scanner = new Scanner(fr);
		Parser parser = new Parser(scanner);
		int token, i = 0;
		while ((token = scanner.yylex()) != 0) {
			expectedTokens[i].assertEqual(scanner.getLine(), scanner.getColumn(), token, scanner.getYylval());
			i++;
		}
	}

	private static LexicalInfo[] expectedTokens = new LexicalInfo[] { new LexicalInfo(5, 1, Parser.INT, null),
			new LexicalInfo(5, 5, Parser.ID, "i"), new LexicalInfo(5, 6, ',', null),
			new LexicalInfo(5, 7, Parser.ID, "j"), new LexicalInfo(5, 8, ';', null),
			new LexicalInfo(8, 1, Parser.INT, null), new LexicalInfo(8, 5, Parser.ID, "f"),
			new LexicalInfo(8, 6, '(', null), new LexicalInfo(8, 7, Parser.INT, null),
			new LexicalInfo(8, 11, Parser.ID, "a"), new LexicalInfo(8, 12, ')', null),
			new LexicalInfo(8, 14, '{', null), new LexicalInfo(9, 2, Parser.RETURN, null),
			new LexicalInfo(9, 9, Parser.ID, "a"), new LexicalInfo(9, 10, ';', null), new LexicalInfo(10, 1, '}', null),
			new LexicalInfo(13, 1, Parser.VOID, null), new LexicalInfo(13, 6, Parser.MAIN, null),
			new LexicalInfo(13, 10, '(', null), new LexicalInfo(13, 11, ')', null), new LexicalInfo(13, 13, '{', null),
			new LexicalInfo(14, 2, Parser.INT, null), new LexicalInfo(14, 6, Parser.ID, "i"),
			new LexicalInfo(14, 7, ',', null), new LexicalInfo(14, 8, Parser.ID, "b"),
			new LexicalInfo(14, 9, ';', null), new LexicalInfo(15, 2, Parser.STRUCT, null),
			new LexicalInfo(15, 9, '{', null), new LexicalInfo(15, 11, Parser.INT, null),
			new LexicalInfo(15, 15, Parser.ID, "day"), new LexicalInfo(15, 18, ',', null),
			new LexicalInfo(15, 20, Parser.ID, "month"), new LexicalInfo(15, 25, ',', null),
			new LexicalInfo(15, 27, Parser.ID, "year"), new LexicalInfo(15, 31, ';', null),
			new LexicalInfo(15, 33, '}', null), new LexicalInfo(15, 35, Parser.ID, "myDate"),
			new LexicalInfo(15, 41, ';', null), new LexicalInfo(16, 2, Parser.INT, null),
			new LexicalInfo(16, 5, '[', null), new LexicalInfo(16, 6, Parser.INT_CONSTANT, 10),
			new LexicalInfo(16, 8, ']', null), new LexicalInfo(16, 10, Parser.ID, "v"),
			new LexicalInfo(16, 11, ';', null), new LexicalInfo(17, 2, Parser.DOUBLE, null),
			new LexicalInfo(17, 9, Parser.ID, "r"), new LexicalInfo(17, 10, ';', null),
			new LexicalInfo(18, 2, Parser.CHAR, null), new LexicalInfo(18, 7, Parser.ID, "c"),
			new LexicalInfo(18, 8, ';', null), new LexicalInfo(20, 2, Parser.ID, "i"),
			new LexicalInfo(20, 4, '=', null), new LexicalInfo(20, 6, Parser.INT_CONSTANT, 1),
			new LexicalInfo(20, 7, ';', null), new LexicalInfo(21, 2, Parser.ID, "r"),
			new LexicalInfo(21, 4, '=', null), new LexicalInfo(21, 6, Parser.REAL_CONSTANT, 12.3),
			new LexicalInfo(21, 11, '-', null), new LexicalInfo(21, 13, Parser.REAL_CONSTANT, 0.03412),
			new LexicalInfo(21, 22, '*', null), new LexicalInfo(21, 24, Parser.REAL_CONSTANT, 2.0),
			new LexicalInfo(21, 27, '+', null), new LexicalInfo(21, 29, Parser.REAL_CONSTANT, 0.34),
			new LexicalInfo(21, 33, '-', null), new LexicalInfo(21, 35, Parser.REAL_CONSTANT, 3000.0),
			new LexicalInfo(21, 38, ';', null), new LexicalInfo(22, 2, Parser.ID, "c"),
			new LexicalInfo(22, 4, '=', null), new LexicalInfo(22, 6, Parser.CHAR_CONSTANT, '~'),
			new LexicalInfo(22, 12, ';', null), new LexicalInfo(24, 2, Parser.ID, "b"),
			new LexicalInfo(24, 4, '=', null), new LexicalInfo(24, 6, Parser.ID, "v"),
			new LexicalInfo(24, 7, '[', null), new LexicalInfo(24, 8, Parser.INT_CONSTANT, 2),
			new LexicalInfo(24, 9, ']', null), new LexicalInfo(24, 10, ';', null),
			new LexicalInfo(25, 2, Parser.IF, null), new LexicalInfo(25, 5, '(', null),
			new LexicalInfo(25, 6, Parser.INT_CONSTANT, 1), new LexicalInfo(25, 8, Parser.AND, null),
			new LexicalInfo(25, 11, Parser.INT_CONSTANT, 2), new LexicalInfo(25, 13, Parser.OR, null),
			new LexicalInfo(25, 16, '!', null), new LexicalInfo(25, 17, Parser.INT_CONSTANT, 0),
			new LexicalInfo(25, 18, ')', null), new LexicalInfo(25, 20, '{', null),
			new LexicalInfo(26, 3, Parser.ID, "i"), new LexicalInfo(26, 5, '=', null),
			new LexicalInfo(26, 7, Parser.ID, "b"), new LexicalInfo(26, 8, ';', null),
			new LexicalInfo(27, 2, '}', null), new LexicalInfo(29, 2, Parser.WHILE, null),
			new LexicalInfo(29, 8, '(', null), new LexicalInfo(29, 9, Parser.ID, "i"),
			new LexicalInfo(29, 10, '<', null), new LexicalInfo(29, 11, Parser.INT_CONSTANT, 10),
			new LexicalInfo(29, 13, ')', null), new LexicalInfo(29, 15, '{', null),
			new LexicalInfo(30, 6, Parser.ID, "i"), new LexicalInfo(30, 7, '=', null),
			new LexicalInfo(30, 8, Parser.ID, "i"), new LexicalInfo(30, 9, '+', null),
			new LexicalInfo(30, 10, Parser.INT_CONSTANT, 1), new LexicalInfo(30, 11, ';', null),
			new LexicalInfo(31, 6, Parser.ID, "j"), new LexicalInfo(31, 7, '=', null),
			new LexicalInfo(31, 8, Parser.ID, "i"), new LexicalInfo(31, 9, ';', null),
			new LexicalInfo(32, 4, '}', null), new LexicalInfo(33, 1, '}', null), };
}

class LexicalInfo {
	public int line, column, token;
	public Object semanticValue;

	public LexicalInfo(int line, int column, int token, Object semanticValue) {
		this.line = line;
		this.column = column;
		this.token = token;
		this.semanticValue = semanticValue;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(this.line).append(", ").append(this.column).append(", ").append(this.token).append(", ")
				.append(this.semanticValue).append(")");
		return sb.toString();
	}

	public void assertEqual(int line, int column, int token, Object semanticValue) {
		assert this.line == line : "Assert failed in token " + this + ". Expected line " + this.line + ", obtained "
				+ line + ".";
		assert this.column == column : "Assert failed in token " + this + ". Expected column " + this.column
				+ ", obtained " + column + ".";
		assert this.token == token : "Assert failed in token " + this + ". Expected token " + this.token + ", obtained "
				+ token + ".";
		assert this.semanticValue == null ? true : this.semanticValue.equals(semanticValue) : "Assert failed in token "
				+ this + ". Expected semanticValue " + this.semanticValue + ", obtained " + semanticValue + ".";
	}
}
