/* The following code was generated by JFlex 1.4.1 on 9/03/17 17:21 */

// ************  User Code  ********************

package scanner;

import parser.Parser;
import ast.AbstractASTNode;
import error.Err;
import error.ErrorHandler;

/**
 * This class is a scanner generated by <a href="http://www.jflex.de/">JFlex</a>
 * 1.4.1 on 9/03/17 17:21 from the specification file
 * <tt>scanner/scanner.jflex</tt>
 */
public class Scanner {

	/** This character denotes the end of file */
	public static final int YYEOF = -1;

	/** initial size of the lookahead buffer */
	private static final int ZZ_BUFFERSIZE = 16384;

	/** lexical states */
	public static final int YYINITIAL = 0;

	/**
	 * Translates characters to character classes
	 */
	private static final String ZZ_CMAP_PACKED = "\11\0\1\26\1\12\2\0\1\26\22\0\1\26\1\32\3\0\1\14"
			+ "\1\33\1\22\2\14\1\13\1\20\1\15\1\17\1\21\1\11\1\1" + "\1\3\1\4\2\5\1\6\1\7\3\2\1\0\1\15\1\30\1\27"
			+ "\1\31\2\0\4\10\1\16\25\10\1\14\1\23\2\14\2\0\1\37" + "\1\51\1\52\1\40\1\36\1\45\1\10\1\43\1\42\2\10\1\44"
			+ "\1\54\1\24\1\47\2\10\1\35\1\46\1\25\1\50\1\53\1\41" + "\3\10\1\15\1\34\1\15\103\0\1\10\7\0\1\10\3\0\1\10"
			+ "\3\0\1\10\1\0\1\10\6\0\1\10\6\0\1\10\7\0\1\10" + "\3\0\1\10\3\0\1\10\1\0\1\10\6\0\1\10\uff05\0";

	/**
	 * Translates characters to character classes
	 */
	private static final char[] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

	/**
	 * Translates DFA states to action switch labels.
	 */
	private static final int[] ZZ_ACTION = zzUnpackAction();

	private static final String ZZ_ACTION_PACKED_0 = "\1\0\1\1\2\2\1\3\1\4\1\5\2\4\1\6"
			+ "\3\4\1\1\4\4\2\1\11\3\1\0\1\7\1\5" + "\1\0\1\10\1\11\1\12\2\7\2\0\1\13\1\14"
			+ "\1\15\1\16\1\17\1\20\6\3\1\21\4\3\2\7" + "\3\0\1\22\3\0\6\3\1\23\4\3\2\7\2\0"
			+ "\1\3\1\24\1\25\4\3\1\26\1\27\1\30\2\3" + "\1\31\1\32\1\3\1\33\1\34\1\35";

	private static int[] zzUnpackAction() {
		int[] result = new int[99];
		int offset = 0;
		offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackAction(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			do
				result[j++] = value;
			while (--count > 0);
		}
		return j;
	}

	/**
	 * Translates a state to a row index in the transition table
	 */
	private static final int[] ZZ_ROWMAP = zzUnpackRowMap();

	private static final String ZZ_ROWMAP_PACKED_0 = "\0\0\0\55\0\132\0\207\0\264\0\341\0\55\0\u010e"
			+ "\0\55\0\55\0\u013b\0\u0168\0\u0195\0\u01c2\0\u01ef\0\u021c"
			+ "\0\u0249\0\u0276\0\u02a3\0\u02d0\0\u02fd\0\u032a\0\u0357\0\u0384"
			+ "\0\u03b1\0\u03de\0\u040b\0\u0438\0\u0465\0\u0492\0\u0195\0\u04bf"
			+ "\0\u04ec\0\55\0\55\0\55\0\u0519\0\u0546\0\u0573\0\u05a0"
			+ "\0\55\0\55\0\55\0\55\0\55\0\55\0\u05cd\0\u05fa"
			+ "\0\u0627\0\u0654\0\u0681\0\u06ae\0\264\0\u06db\0\u0708\0\u0735"
			+ "\0\u0762\0\u078f\0\u07bc\0\u07e9\0\u0816\0\u0843\0\55\0\u0870"
			+ "\0\u089d\0\u08ca\0\u08f7\0\u0924\0\u0951\0\u097e\0\u09ab\0\u09d8"
			+ "\0\264\0\u0a05\0\u0a32\0\u0a5f\0\u0a8c\0\55\0\u0ab9\0\u0ae6"
			+ "\0\u0b13\0\u0b40\0\264\0\264\0\u0b6d\0\u0b9a\0\u0bc7\0\u0bf4"
			+ "\0\264\0\264\0\264\0\u0c21\0\u0c4e\0\264\0\264\0\u0c7b" + "\0\264\0\264\0\264";

	private static int[] zzUnpackRowMap() {
		int[] result = new int[99];
		int offset = 0;
		offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackRowMap(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int high = packed.charAt(i++) << 16;
			result[j++] = high | packed.charAt(i++);
		}
		return j;
	}

	/**
	 * The transition table of the DFA
	 */
	private static final int[] ZZ_TRANS = zzUnpackTrans();

	private static final String ZZ_TRANS_PACKED_0 = "\1\2\1\3\6\4\1\5\1\6\1\7\1\10\1\11"
			+ "\1\12\1\5\1\13\1\14\1\15\1\16\1\2\2\5" + "\1\7\1\17\1\20\1\21\1\22\1\23\1\24\1\25"
			+ "\1\26\1\5\1\27\1\30\1\31\3\5\1\32\3\5" + "\1\33\1\34\1\35\73\0\1\36\2\0\1\37\14\0"
			+ "\1\36\17\0\7\4\6\0\1\36\2\0\1\37\14\0" + "\1\36\17\0\10\5\5\0\1\5\5\0\2\5\7\0"
			+ "\20\5\11\0\1\40\1\0\1\41\54\0\1\42\60\0" + "\1\43\55\0\1\44\35\0\1\45\6\46\45\0\12\47"
			+ "\1\0\10\47\1\50\31\47\27\0\1\51\54\0\1\52" + "\54\0\1\53\54\0\1\54\60\0\1\55\55\0\1\56"
			+ "\21\0\10\5\5\0\1\5\5\0\2\5\7\0\1\5" + "\1\57\16\5\1\0\10\5\5\0\1\5\5\0\2\5"
			+ "\7\0\7\5\1\60\10\5\1\0\10\5\5\0\1\5" + "\5\0\2\5\7\0\12\5\1\61\5\5\1\0\10\5"
			+ "\5\0\1\5\5\0\2\5\7\0\1\62\5\5\1\63" + "\11\5\1\0\10\5\5\0\1\5\5\0\1\64\1\5"
			+ "\7\0\10\5\1\65\7\5\1\0\10\5\5\0\1\5" + "\5\0\1\5\1\66\7\0\20\5\1\0\10\5\5\0"
			+ "\1\5\5\0\2\5\7\0\6\5\1\67\11\5\1\0" + "\10\5\5\0\1\5\5\0\2\5\7\0\12\5\1\70"
			+ "\5\5\1\0\10\5\5\0\1\5\5\0\2\5\7\0" + "\2\5\1\71\15\5\1\0\1\72\6\73\7\0\2\74"
			+ "\34\0\12\40\1\0\42\40\13\41\1\75\41\41\16\0" + "\1\76\17\0\1\76\17\0\7\46\6\0\1\76\17\0"
			+ "\1\76\40\0\1\77\33\0\1\47\1\100\1\101\1\102" + "\3\100\12\0\1\77\1\0\2\47\30\0\10\5\5\0"
			+ "\1\5\5\0\1\5\1\103\7\0\2\5\1\104\15\5" + "\1\0\10\5\5\0\1\5\5\0\2\5\7\0\11\5"
			+ "\1\105\6\5\1\0\10\5\5\0\1\5\5\0\2\5" + "\7\0\13\5\1\106\4\5\1\0\10\5\5\0\1\5"
			+ "\5\0\2\5\7\0\5\5\1\107\12\5\1\0\10\5" + "\5\0\1\5\5\0\2\5\7\0\5\5\1\110\12\5"
			+ "\1\0\10\5\5\0\1\5\5\0\1\5\1\111\7\0" + "\20\5\1\0\10\5\5\0\1\5\5\0\2\5\7\0"
			+ "\1\112\17\5\1\0\10\5\5\0\1\5\5\0\2\5" + "\7\0\2\5\1\113\15\5\1\0\10\5\5\0\1\5"
			+ "\5\0\2\5\7\0\5\5\1\114\12\5\1\0\10\5" + "\5\0\1\5\5\0\2\5\7\0\5\5\1\115\12\5"
			+ "\21\0\1\116\34\0\7\73\11\0\1\116\34\0\1\72" + "\6\73\45\0\11\41\1\7\1\41\1\75\41\41\1\0"
			+ "\1\116\6\117\7\0\2\120\35\0\7\47\12\0\1\77" + "\33\0\7\100\12\0\1\77\33\0\1\100\1\47\3\100"
			+ "\1\121\1\47\12\0\1\77\33\0\10\5\5\0\1\5" + "\5\0\2\5\7\0\13\5\1\122\4\5\1\0\10\5"
			+ "\5\0\1\5\5\0\2\5\7\0\3\5\1\123\14\5" + "\1\0\10\5\5\0\1\5\5\0\2\5\7\0\1\5"
			+ "\1\124\16\5\1\0\10\5\5\0\1\5\5\0\2\5" + "\7\0\14\5\1\125\3\5\1\0\10\5\5\0\1\5"
			+ "\5\0\1\5\1\126\7\0\20\5\1\0\10\5\5\0" + "\1\5\5\0\2\5\7\0\7\5\1\127\10\5\1\0"
			+ "\10\5\5\0\1\5\5\0\2\5\7\0\13\5\1\130" + "\4\5\1\0\10\5\5\0\1\5\5\0\2\5\7\0"
			+ "\1\131\17\5\1\0\10\5\5\0\1\5\5\0\2\5" + "\7\0\3\5\1\132\14\5\1\0\10\5\5\0\1\5"
			+ "\5\0\1\133\1\5\7\0\20\5\1\0\7\117\46\0" + "\1\116\6\117\46\0\1\47\1\0\5\47\12\0\1\77"
			+ "\33\0\10\5\5\0\1\5\5\0\2\5\7\0\1\134" + "\17\5\1\0\10\5\5\0\1\5\5\0\2\5\7\0"
			+ "\7\5\1\135\10\5\1\0\10\5\5\0\1\5\5\0" + "\2\5\7\0\1\5\1\136\16\5\1\0\10\5\5\0"
			+ "\1\5\5\0\2\5\7\0\1\5\1\137\16\5\1\0" + "\10\5\5\0\1\5\5\0\2\5\7\0\15\5\1\140"
			+ "\2\5\1\0\10\5\5\0\1\5\5\0\1\141\1\5" + "\7\0\20\5\1\0\10\5\5\0\1\5\5\0\2\5"
			+ "\7\0\1\5\1\142\16\5\1\0\10\5\5\0\1\5" + "\5\0\1\5\1\143\7\0\20\5";

	private static int[] zzUnpackTrans() {
		int[] result = new int[3240];
		int offset = 0;
		offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackTrans(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			value--;
			do
				result[j++] = value;
			while (--count > 0);
		}
		return j;
	}

	/* error codes */
	private static final int ZZ_UNKNOWN_ERROR = 0;
	private static final int ZZ_NO_MATCH = 1;
	private static final int ZZ_PUSHBACK_2BIG = 2;

	/* error messages for the codes above */
	private static final String ZZ_ERROR_MSG[] = { "Unkown internal scanner error", "Error: could not match input",
			"Error: pushback value was too large" };

	/**
	 * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
	 */
	private static final int[] ZZ_ATTRIBUTE = zzUnpackAttribute();

	private static final String ZZ_ATTRIBUTE_PACKED_0 = "\1\0\1\11\4\1\1\11\1\1\2\11\23\1\1\0"
			+ "\2\1\1\0\3\11\2\1\2\0\6\11\15\1\3\0" + "\1\11\3\0\13\1\1\11\1\1\2\0\22\1";

	private static int[] zzUnpackAttribute() {
		int[] result = new int[99];
		int offset = 0;
		offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackAttribute(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			do
				result[j++] = value;
			while (--count > 0);
		}
		return j;
	}

	/** the input device */
	private java.io.Reader zzReader;

	/** the current state of the DFA */
	private int zzState;

	/** the current lexical state */
	private int zzLexicalState = YYINITIAL;

	/**
	 * this buffer contains the current text to be matched and is the source of
	 * the yytext() string
	 */
	private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

	/** the textposition at the last accepting state */
	private int zzMarkedPos;

	/** the textposition at the last state to be included in yytext */
	private int zzPushbackPos;

	/** the current text position in the buffer */
	private int zzCurrentPos;

	/** startRead marks the beginning of the yytext() string in the buffer */
	private int zzStartRead;

	/**
	 * endRead marks the last character in the buffer, that has been read from
	 * input
	 */
	private int zzEndRead;

	/** number of newlines encountered up to the start of the matched text */
	private int yyline;

	/** the number of characters up to the start of the matched text */
	private int yychar;

	/**
	 * the number of characters from the last newline up to the start of the
	 * matched text
	 */
	private int yycolumn;

	/**
	 * zzAtBOL == true <=> the scanner is currently at the beginning of a line
	 */
	private boolean zzAtBOL = true;

	/** zzAtEOF == true <=> the scanner is at the EOF */
	private boolean zzAtEOF;

	/** denotes if the user-EOF-code has already been executed */
	private boolean zzEOFDone;

	/* user code: */
	// ************ Fields and methods ********************

	// * To get the line number
	public int getLine() {
		// * JFlex starts in zero
		return this.yyline + 1;
	}

	// * To get the column number
	public int getColumn() {
		// * JFlex starts in zero
		return yycolumn + 1;
	}

	// * Semantic value of the token
	private Object yylval;

	public Object getYylval() {
		return this.yylval;
	}

	// * CHAR CONSTANT METHOD
	public char convertToChar(String text) {
		char[] chars = text.toCharArray();
		if (chars[1] == '\\') {
			if (chars[2] == 'n') {
				return '\n';
			} else if (chars[2] == 't') {
				return '\t';
			} else if (isIntNexts(chars)) {
				String aux = "";
				for (int i = 2; i < chars.length; i++) {
					aux += chars[i];
				}
				return (char) Integer.parseInt(aux);
			}
		} else {
			return chars[1];
		}
		return 0;

	}

	private boolean isIntNexts(char[] c) {
		for (int i = 2; i < c.length; i++) {
			if (Character.isDigit(c[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Creates a new scanner There is also a java.io.InputStream version of this
	 * constructor.
	 *
	 * @param in
	 *            the java.io.Reader to read input from.
	 */
	public Scanner(java.io.Reader in) {
		this.zzReader = in;
	}

	/**
	 * Creates a new scanner. There is also java.io.Reader version of this
	 * constructor.
	 *
	 * @param in
	 *            the java.io.Inputstream to read input from.
	 */
	public Scanner(java.io.InputStream in) {
		this(new java.io.InputStreamReader(in));
	}

	/**
	 * Unpacks the compressed character translation table.
	 *
	 * @param packed
	 *            the packed character translation table
	 * @return the unpacked character translation table
	 */
	private static char[] zzUnpackCMap(String packed) {
		char[] map = new char[0x10000];
		int i = 0; /* index in packed string */
		int j = 0; /* index in unpacked array */
		while (i < 178) {
			int count = packed.charAt(i++);
			char value = packed.charAt(i++);
			do
				map[j++] = value;
			while (--count > 0);
		}
		return map;
	}

	/**
	 * Refills the input buffer.
	 *
	 * @return <code>false</code>, iff there was new input.
	 * 
	 * @exception java.io.IOException
	 *                if any I/O-Error occurs
	 */
	private boolean zzRefill() throws java.io.IOException {

		/* first: make room (if you can) */
		if (zzStartRead > 0) {
			System.arraycopy(zzBuffer, zzStartRead, zzBuffer, 0, zzEndRead - zzStartRead);

			/* translate stored positions */
			zzEndRead -= zzStartRead;
			zzCurrentPos -= zzStartRead;
			zzMarkedPos -= zzStartRead;
			zzPushbackPos -= zzStartRead;
			zzStartRead = 0;
		}

		/* is the buffer big enough? */
		if (zzCurrentPos >= zzBuffer.length) {
			/* if not: blow it up */
			char newBuffer[] = new char[zzCurrentPos * 2];
			System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
			zzBuffer = newBuffer;
		}

		/* finally: fill the buffer with new input */
		int numRead = zzReader.read(zzBuffer, zzEndRead, zzBuffer.length - zzEndRead);

		if (numRead < 0) {
			return true;
		} else {
			zzEndRead += numRead;
			return false;
		}
	}

	/**
	 * Closes the input stream.
	 */
	public final void yyclose() throws java.io.IOException {
		zzAtEOF = true; /* indicate end of file */
		zzEndRead = zzStartRead; /* invalidate buffer */

		if (zzReader != null)
			zzReader.close();
	}

	/**
	 * Resets the scanner to read from a new input stream. Does not close the
	 * old reader.
	 *
	 * All internal variables are reset, the old input stream <b>cannot</b> be
	 * reused (internal buffer is discarded and lost). Lexical state is set to
	 * <tt>ZZ_INITIAL</tt>.
	 *
	 * @param reader
	 *            the new input stream
	 */
	public final void yyreset(java.io.Reader reader) {
		zzReader = reader;
		zzAtBOL = true;
		zzAtEOF = false;
		zzEndRead = zzStartRead = 0;
		zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
		yyline = yychar = yycolumn = 0;
		zzLexicalState = YYINITIAL;
	}

	/**
	 * Returns the current lexical state.
	 */
	public final int yystate() {
		return zzLexicalState;
	}

	/**
	 * Enters a new lexical state
	 *
	 * @param newState
	 *            the new lexical state
	 */
	public final void yybegin(int newState) {
		zzLexicalState = newState;
	}

	/**
	 * Returns the text matched by the current regular expression.
	 */
	public final String yytext() {
		return new String(zzBuffer, zzStartRead, zzMarkedPos - zzStartRead);
	}

	/**
	 * Returns the character at position <tt>pos</tt> from the matched text.
	 * 
	 * It is equivalent to yytext().charAt(pos), but faster
	 *
	 * @param pos
	 *            the position of the character to fetch. A value from 0 to
	 *            yylength()-1.
	 *
	 * @return the character at position pos
	 */
	public final char yycharat(int pos) {
		return zzBuffer[zzStartRead + pos];
	}

	/**
	 * Returns the length of the matched text region.
	 */
	public final int yylength() {
		return zzMarkedPos - zzStartRead;
	}

	/**
	 * Reports an error that occured while scanning.
	 *
	 * In a wellformed scanner (no or only correct usage of yypushback(int) and
	 * a match-all fallback rule) this method will only be called with things
	 * that "Can't Possibly Happen". If this method is called, something is
	 * seriously wrong (e.g. a JFlex bug producing a faulty scanner etc.).
	 *
	 * Usual syntax/scanner level error handling should be done in error
	 * fallback rules.
	 *
	 * @param errorCode
	 *            the code of the errormessage to display
	 */
	private void zzScanError(int errorCode) {
		String message;
		try {
			message = ZZ_ERROR_MSG[errorCode];
		} catch (ArrayIndexOutOfBoundsException e) {
			message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
		}

		throw new Error(message);
	}

	/**
	 * Pushes the specified amount of characters back into the input stream.
	 *
	 * They will be read again by then next call of the scanning method
	 *
	 * @param number
	 *            the number of characters to be read again. This number must
	 *            not be greater than yylength()!
	 */
	public void yypushback(int number) {
		if (number > yylength())
			zzScanError(ZZ_PUSHBACK_2BIG);

		zzMarkedPos -= number;
	}

	/**
	 * Contains user EOF-code, which will be executed exactly once, when the end
	 * of file is reached
	 */
	private void zzDoEOF() throws java.io.IOException {
		if (!zzEOFDone) {
			zzEOFDone = true;
			yyclose();
		}
	}

	/**
	 * Resumes scanning until the next regular expression is matched, the end of
	 * input is encountered or an I/O-Error occurs.
	 *
	 * @return the next token
	 * @exception java.io.IOException
	 *                if any I/O-Error occurs
	 */
	public int yylex() throws java.io.IOException {
		int zzInput;
		int zzAction;

		// cached fields:
		int zzCurrentPosL;
		int zzMarkedPosL;
		int zzEndReadL = zzEndRead;
		char[] zzBufferL = zzBuffer;
		char[] zzCMapL = ZZ_CMAP;

		int[] zzTransL = ZZ_TRANS;
		int[] zzRowMapL = ZZ_ROWMAP;
		int[] zzAttrL = ZZ_ATTRIBUTE;

		while (true) {
			zzMarkedPosL = zzMarkedPos;

			boolean zzR = false;
			for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL; zzCurrentPosL++) {
				switch (zzBufferL[zzCurrentPosL]) {
				case '\u000B':
				case '\u000C':
				case '\u0085':
				case '\u2028':
				case '\u2029':
					yyline++;
					yycolumn = 0;
					zzR = false;
					break;
				case '\r':
					yyline++;
					yycolumn = 0;
					zzR = true;
					break;
				case '\n':
					if (zzR)
						zzR = false;
					else {
						yyline++;
						yycolumn = 0;
					}
					break;
				default:
					zzR = false;
					yycolumn++;
				}
			}

			if (zzR) {
				// peek one character ahead if it is \n (if we have counted one
				// line too much)
				boolean zzPeek;
				if (zzMarkedPosL < zzEndReadL)
					zzPeek = zzBufferL[zzMarkedPosL] == '\n';
				else if (zzAtEOF)
					zzPeek = false;
				else {
					boolean eof = zzRefill();
					zzEndReadL = zzEndRead;
					zzMarkedPosL = zzMarkedPos;
					zzBufferL = zzBuffer;
					if (eof)
						zzPeek = false;
					else
						zzPeek = zzBufferL[zzMarkedPosL] == '\n';
				}
				if (zzPeek)
					yyline--;
			}
			zzAction = -1;

			zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

			zzState = zzLexicalState;

			zzForAction: {
				while (true) {

					if (zzCurrentPosL < zzEndReadL)
						zzInput = zzBufferL[zzCurrentPosL++];
					else if (zzAtEOF) {
						zzInput = YYEOF;
						break zzForAction;
					} else {
						// store back cached positions
						zzCurrentPos = zzCurrentPosL;
						zzMarkedPos = zzMarkedPosL;
						boolean eof = zzRefill();
						// get translated positions and possibly new buffer
						zzCurrentPosL = zzCurrentPos;
						zzMarkedPosL = zzMarkedPos;
						zzBufferL = zzBuffer;
						zzEndReadL = zzEndRead;
						if (eof) {
							zzInput = YYEOF;
							break zzForAction;
						} else {
							zzInput = zzBufferL[zzCurrentPosL++];
						}
					}
					int zzNext = zzTransL[zzRowMapL[zzState] + zzCMapL[zzInput]];
					if (zzNext == -1)
						break zzForAction;
					zzState = zzNext;

					int zzAttributes = zzAttrL[zzState];
					if ((zzAttributes & 1) == 1) {
						zzAction = zzState;
						zzMarkedPosL = zzCurrentPosL;
						if ((zzAttributes & 8) == 8)
							break zzForAction;
					}

				}
			}

			// store back cached position
			zzMarkedPos = zzMarkedPosL;

			switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
			case 4: {
				this.yylval = yytext();
				return (int) yytext().charAt(0);
			}
			case 30:
				break;
			case 22: {
				return Parser.CHAR;
			}
			case 31:
				break;
			case 17: {
				return Parser.IF;
			}
			case 32:
				break;
			case 20: {
				return Parser.READ;
			}
			case 33:
				break;
			case 10: {
				return Parser.INC;
			}
			case 34:
				break;
			case 29: {
				return Parser.STRUCT;
			}
			case 35:
				break;
			case 24: {
				return Parser.MAIN;
			}
			case 36:
				break;
			case 15: {
				return Parser.AND;
			}
			case 37:
				break;
			case 27: {
				return Parser.RETURN;
			}
			case 38:
				break;
			case 18: {
				this.yylval = convertToChar(yytext());
				return Parser.CHAR_CONSTANT;
			}
			case 39:
				break;
			case 28: {
				return Parser.DOUBLE;
			}
			case 40:
				break;
			case 25: {
				return Parser.WRITE;
			}
			case 41:
				break;
			case 9: {
				return Parser.DEC;
			}
			case 42:
				break;
			case 21: {
				return Parser.ELSE;
			}
			case 43:
				break;
			case 16: {
				return Parser.OR;
			}
			case 44:
				break;
			case 23: {
				return Parser.VOID;
			}
			case 45:
				break;
			case 1: {
				ErrorHandler.getInstance()
						.addError(new Err(getLine(), getColumn(), ("Lexical error -> " + yytext().toString())));
			}
			case 46:
				break;
			case 8: {
				return Parser.POW;
			}
			case 47:
				break;
			case 7: {
				this.yylval = new Double(yytext());
				return Parser.REAL_CONSTANT;
			}
			case 48:
				break;
			case 11: {
				return Parser.EQ;
			}
			case 49:
				break;
			case 14: {
				return Parser.NOT_EQ;
			}
			case 50:
				break;
			case 6: {
				this.yylval = yytext();
				return (int) yytext().charAt(0);
			}
			case 51:
				break;
			case 26: {
				return Parser.WHILE;
			}
			case 52:
				break;
			case 13: {
				return Parser.G_EQ;
			}
			case 53:
				break;
			case 2: {
				this.yylval = new Integer(yytext());
				return Parser.INT_CONSTANT;
			}
			case 54:
				break;
			case 19: {
				return Parser.INT;
			}
			case 55:
				break;
			case 12: {
				return Parser.L_EQ;
			}
			case 56:
				break;
			case 3: {
				this.yylval = yytext();
				return Parser.ID;
			}
			case 57:
				break;
			case 5: {
			}
			case 58:
				break;
			default:
				if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
					zzAtEOF = true;
					zzDoEOF();
					{
						return 0;
					}
				} else {
					zzScanError(ZZ_NO_MATCH);
				}
			}
		}
	}

}
