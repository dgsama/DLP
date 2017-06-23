/* The following code was generated by JFlex 1.4.1 on 23/06/17 2:52 */

// ************  User Code  ********************

package scanner;
import parser.Parser;
import ast.AbstractASTNode;
import error.Err;
import error.ErrorHandler;

@SuppressWarnings("unused")


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 23/06/17 2:52 from the specification file
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
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\26\1\12\2\0\1\26\22\0\1\26\1\32\3\0\1\14"+
    "\1\33\1\22\2\14\1\13\1\20\1\15\1\17\1\21\1\11\1\1"+
    "\1\3\1\4\2\5\1\6\1\7\3\2\1\14\1\15\1\30\1\27"+
    "\1\31\1\14\1\0\4\10\1\16\25\10\1\14\1\23\2\14\2\0"+
    "\1\37\1\51\1\52\1\40\1\36\1\45\1\10\1\43\1\42\1\10"+
    "\1\55\1\44\1\54\1\24\1\47\2\10\1\35\1\46\1\25\1\50"+
    "\1\53\1\41\3\10\1\15\1\34\1\15\103\0\1\10\7\0\1\10"+
    "\3\0\1\10\3\0\1\10\1\0\1\10\6\0\1\10\6\0\1\10"+
    "\7\0\1\10\3\0\1\10\3\0\1\10\1\0\1\10\6\0\1\10"+
    "\uff05\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\2\2\1\3\1\4\1\5\2\4\1\6"+
    "\3\4\1\1\4\4\2\1\12\3\1\0\1\7\1\5"+
    "\1\0\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\2\7\2\0\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\3\3\1\25\3\3\1\26\7\3\2\7\3\0\1\27"+
    "\3\0\7\3\1\30\7\3\2\7\2\0\1\3\1\31"+
    "\1\32\7\3\1\33\1\34\1\35\1\36\3\3\1\37"+
    "\1\40\2\3\1\41\1\42\1\3\1\43\1\44\1\45"+
    "\1\46";

  private static int [] zzUnpackAction() {
    int [] result = new int[122];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\56\0\134\0\212\0\270\0\346\0\56\0\u0114"+
    "\0\56\0\56\0\u0142\0\u0170\0\u019e\0\u01cc\0\u01fa\0\u0228"+
    "\0\u0256\0\u0284\0\u02b2\0\u02e0\0\u030e\0\u033c\0\u036a\0\u0398"+
    "\0\u03c6\0\u03f4\0\u0422\0\u0450\0\u047e\0\u04ac\0\u04da\0\u019e"+
    "\0\u0508\0\u0536\0\56\0\56\0\56\0\56\0\56\0\56"+
    "\0\56\0\u0564\0\u0592\0\u05c0\0\u05ee\0\56\0\56\0\56"+
    "\0\56\0\56\0\56\0\u061c\0\u064a\0\u0678\0\u06a6\0\u06d4"+
    "\0\u0702\0\u0730\0\270\0\u075e\0\u078c\0\u07ba\0\u07e8\0\u0816"+
    "\0\u0844\0\u0872\0\u08a0\0\u08ce\0\u08fc\0\u092a\0\u0958\0\56"+
    "\0\u0986\0\u09b4\0\u09e2\0\u0a10\0\u0a3e\0\u0a6c\0\u0a9a\0\u0ac8"+
    "\0\u0af6\0\u0b24\0\270\0\u0b52\0\u0b80\0\u0bae\0\u0bdc\0\u0c0a"+
    "\0\u0c38\0\u0c66\0\56\0\u0c94\0\u0cc2\0\u0cf0\0\u0d1e\0\270"+
    "\0\270\0\u0d4c\0\u0d7a\0\u0da8\0\u0dd6\0\u0e04\0\u0e32\0\u0e60"+
    "\0\270\0\270\0\270\0\270\0\u0e8e\0\u0ebc\0\u0eea\0\270"+
    "\0\270\0\u0f18\0\u0f46\0\270\0\270\0\u0f74\0\270\0\270"+
    "\0\270\0\270";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[122];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
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
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\6\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\5\1\13\1\14\1\15\1\16\1\2\2\5"+
    "\1\7\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\1\26\1\5\1\27\1\30\1\31\3\5\1\32\2\5"+
    "\1\33\1\34\1\35\1\36\1\5\74\0\1\37\2\0"+
    "\1\40\14\0\1\37\20\0\7\4\6\0\1\37\2\0"+
    "\1\40\14\0\1\37\20\0\10\5\5\0\1\5\5\0"+
    "\2\5\7\0\21\5\11\0\1\41\1\0\1\42\13\0"+
    "\1\43\41\0\1\44\13\0\1\45\45\0\1\46\7\0"+
    "\1\47\46\0\1\50\6\0\1\51\27\0\1\52\6\53"+
    "\46\0\12\54\1\0\10\54\1\55\32\54\27\0\1\56"+
    "\55\0\1\57\55\0\1\60\55\0\1\61\61\0\1\62"+
    "\56\0\1\63\22\0\10\5\5\0\1\5\5\0\2\5"+
    "\7\0\1\5\1\64\17\5\1\0\10\5\5\0\1\5"+
    "\5\0\2\5\7\0\7\5\1\65\11\5\1\0\10\5"+
    "\5\0\1\5\5\0\2\5\7\0\1\5\1\66\10\5"+
    "\1\67\6\5\1\0\10\5\5\0\1\5\5\0\2\5"+
    "\7\0\1\70\5\5\1\71\12\5\1\0\10\5\5\0"+
    "\1\5\5\0\1\72\1\5\7\0\10\5\1\73\10\5"+
    "\1\0\10\5\5\0\1\5\5\0\1\5\1\74\7\0"+
    "\4\5\1\75\14\5\1\0\10\5\5\0\1\5\5\0"+
    "\2\5\7\0\1\76\20\5\1\0\10\5\5\0\1\5"+
    "\5\0\2\5\7\0\2\5\1\77\3\5\1\100\12\5"+
    "\1\0\10\5\5\0\1\5\5\0\2\5\7\0\12\5"+
    "\1\101\6\5\1\0\10\5\5\0\1\5\5\0\2\5"+
    "\7\0\2\5\1\102\16\5\1\0\1\103\6\104\7\0"+
    "\2\105\35\0\12\41\1\0\43\41\13\42\1\106\42\42"+
    "\16\0\1\107\17\0\1\107\20\0\7\53\6\0\1\107"+
    "\17\0\1\107\41\0\1\110\34\0\1\54\1\111\1\112"+
    "\1\113\3\111\12\0\1\110\1\0\2\54\31\0\10\5"+
    "\5\0\1\5\5\0\1\5\1\114\7\0\2\5\1\115"+
    "\16\5\1\0\10\5\5\0\1\5\5\0\2\5\7\0"+
    "\11\5\1\116\7\5\1\0\10\5\5\0\1\5\5\0"+
    "\2\5\7\0\10\5\1\117\10\5\1\0\10\5\5\0"+
    "\1\5\5\0\2\5\7\0\13\5\1\120\5\5\1\0"+
    "\10\5\5\0\1\5\5\0\2\5\7\0\5\5\1\121"+
    "\13\5\1\0\10\5\5\0\1\5\5\0\2\5\7\0"+
    "\5\5\1\122\13\5\1\0\10\5\5\0\1\5\5\0"+
    "\1\5\1\123\7\0\21\5\1\0\10\5\5\0\1\5"+
    "\5\0\2\5\7\0\1\124\20\5\1\0\10\5\5\0"+
    "\1\5\5\0\2\5\7\0\5\5\1\125\13\5\1\0"+
    "\10\5\5\0\1\5\5\0\2\5\7\0\1\5\1\126"+
    "\17\5\1\0\10\5\5\0\1\5\5\0\2\5\7\0"+
    "\11\5\1\127\7\5\1\0\10\5\5\0\1\5\5\0"+
    "\2\5\7\0\2\5\1\130\16\5\1\0\10\5\5\0"+
    "\1\5\5\0\2\5\7\0\5\5\1\131\13\5\1\0"+
    "\10\5\5\0\1\5\5\0\2\5\7\0\5\5\1\132"+
    "\13\5\21\0\1\133\35\0\7\104\11\0\1\133\35\0"+
    "\1\103\6\104\46\0\11\42\1\7\1\42\1\106\42\42"+
    "\1\0\1\133\6\134\7\0\2\135\36\0\7\54\12\0"+
    "\1\110\34\0\7\111\12\0\1\110\34\0\1\111\1\54"+
    "\3\111\1\136\1\54\12\0\1\110\34\0\10\5\5\0"+
    "\1\5\5\0\2\5\7\0\13\5\1\137\5\5\1\0"+
    "\10\5\5\0\1\5\5\0\2\5\7\0\3\5\1\140"+
    "\15\5\1\0\10\5\5\0\1\5\5\0\2\5\7\0"+
    "\1\5\1\141\17\5\1\0\10\5\5\0\1\5\5\0"+
    "\2\5\7\0\2\5\1\142\16\5\1\0\10\5\5\0"+
    "\1\5\5\0\2\5\7\0\14\5\1\143\4\5\1\0"+
    "\10\5\5\0\1\5\5\0\1\5\1\144\7\0\21\5"+
    "\1\0\10\5\5\0\1\5\5\0\2\5\7\0\7\5"+
    "\1\145\11\5\1\0\10\5\5\0\1\5\5\0\2\5"+
    "\7\0\13\5\1\146\5\5\1\0\10\5\5\0\1\5"+
    "\5\0\1\5\1\147\7\0\21\5\1\0\10\5\5\0"+
    "\1\5\5\0\2\5\7\0\2\5\1\150\16\5\1\0"+
    "\10\5\5\0\1\5\5\0\2\5\7\0\1\5\1\151"+
    "\17\5\1\0\10\5\5\0\1\5\5\0\2\5\7\0"+
    "\1\152\20\5\1\0\10\5\5\0\1\5\5\0\2\5"+
    "\7\0\3\5\1\153\15\5\1\0\10\5\5\0\1\5"+
    "\5\0\1\154\1\5\7\0\21\5\1\0\7\134\47\0"+
    "\1\133\6\134\47\0\1\54\1\0\5\54\12\0\1\110"+
    "\34\0\10\5\5\0\1\5\5\0\2\5\7\0\1\155"+
    "\20\5\1\0\10\5\5\0\1\5\5\0\2\5\7\0"+
    "\13\5\1\156\5\5\1\0\10\5\5\0\1\5\5\0"+
    "\2\5\7\0\7\5\1\157\11\5\1\0\10\5\5\0"+
    "\1\5\5\0\2\5\7\0\1\5\1\160\17\5\1\0"+
    "\10\5\5\0\1\5\5\0\2\5\7\0\1\5\1\161"+
    "\17\5\1\0\10\5\5\0\1\5\5\0\2\5\7\0"+
    "\15\5\1\162\3\5\1\0\10\5\5\0\1\5\5\0"+
    "\2\5\7\0\15\5\1\163\3\5\1\0\10\5\5\0"+
    "\1\5\5\0\2\5\7\0\20\5\1\164\1\0\10\5"+
    "\5\0\1\5\5\0\1\165\1\5\7\0\21\5\1\0"+
    "\10\5\5\0\1\5\5\0\2\5\7\0\7\5\1\166"+
    "\11\5\1\0\10\5\5\0\1\5\5\0\2\5\7\0"+
    "\1\5\1\167\17\5\1\0\10\5\5\0\1\5\5\0"+
    "\1\5\1\170\7\0\21\5\1\0\10\5\5\0\1\5"+
    "\5\0\2\5\7\0\6\5\1\171\12\5\1\0\10\5"+
    "\5\0\1\5\5\0\1\5\1\172\7\0\21\5";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4002];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\4\1\1\11\1\1\2\11\24\1\1\0"+
    "\2\1\1\0\7\11\2\1\2\0\6\11\21\1\3\0"+
    "\1\11\3\0\17\1\1\11\1\1\2\0\34\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[122];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
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
// ************  Fields and methods ********************

// * To get the line number
public int getLine() { 
	// * JFlex starts in zero
	return yyline+1;
}

// * To get the column number
public int getColumn() { 
	// * JFlex starts in zero
	return yycolumn+1;
}

// * Semantic value of the token
private Object yylval;
public Object getYylval() {
	return this.yylval;
}

// * CHAR CONSTANT METHOD
	public char convertToChar(String text){
		char[]chars = text.toCharArray();
		if(chars[1]=='\\'){
				if(chars[2]=='n'){
					return '\n';
				}else if(chars[2]=='t'){
					return '\t';
				}else if(isIntNexts(chars)){
					String aux ="";
					for(int i =2;i<chars.length;i++){
						aux+=chars[i];
					}
					return (char)Integer.parseInt(aux);
				}
		}else{
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
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Scanner(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Scanner(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 182) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzPushbackPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead < 0) {
      return true;
    }
    else {
      zzEndRead+= numRead;
      return false;
    }
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
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
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
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
        // peek one character ahead if it is \n (if we have counted one line too much)
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
        if (zzPeek) yyline--;
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
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 8: 
          { return Parser.DIV_ASSIG;
          }
        case 39: break;
        case 4: 
          { this.yylval = yytext();
					return (int)yytext().charAt(0);
          }
        case 40: break;
        case 21: 
          { return Parser.DO;
          }
        case 41: break;
        case 7: 
          { this.yylval = new Double(yytext());
				return Parser.REAL_CONSTANT;
          }
        case 42: break;
        case 19: 
          { return Parser.AND;
          }
        case 43: break;
        case 32: 
          { return Parser.WHILE;
          }
        case 44: break;
        case 3: 
          { this.yylval = yytext();
				return Parser.ID;
          }
        case 45: break;
        case 34: 
          { return Parser.RETURN;
          }
        case 46: break;
        case 24: 
          { return Parser.INT;
          }
        case 47: break;
        case 36: 
          { return Parser.STRUCT;
          }
        case 48: break;
        case 33: 
          { return Parser.BREAK;
          }
        case 49: break;
        case 15: 
          { return Parser.EQ;
          }
        case 50: break;
        case 23: 
          { this.yylval = convertToChar(yytext());
				return Parser.CHAR_CONSTANT;
          }
        case 51: break;
        case 22: 
          { return Parser.IF;
          }
        case 52: break;
        case 31: 
          { return Parser.WRITE;
          }
        case 53: break;
        case 30: 
          { return Parser.MAIN;
          }
        case 54: break;
        case 2: 
          { this.yylval = new Integer(yytext());
         			  return Parser.INT_CONSTANT;
          }
        case 55: break;
        case 16: 
          { return Parser.L_EQ;
          }
        case 56: break;
        case 10: 
          { return Parser.PROD_ASSIG;
          }
        case 57: break;
        case 6: 
          { this.yylval = yytext();
						return (int)yytext().charAt(0);
          }
        case 58: break;
        case 1: 
          { ErrorHandler.getInstance().addError(new Err(getLine(), getColumn(),("Lexical error -> " + yytext().toString())));
          }
        case 59: break;
        case 28: 
          { return Parser.CHAR;
          }
        case 60: break;
        case 11: 
          { return Parser.DEC;
          }
        case 61: break;
        case 13: 
          { return Parser.INC;
          }
        case 62: break;
        case 37: 
          { return Parser.SWITCH;
          }
        case 63: break;
        case 12: 
          { return Parser.SUB_ASSIG;
          }
        case 64: break;
        case 20: 
          { return Parser.OR;
          }
        case 65: break;
        case 38: 
          { return Parser.DEFAULT;
          }
        case 66: break;
        case 35: 
          { return Parser.DOUBLE;
          }
        case 67: break;
        case 17: 
          { return Parser.G_EQ;
          }
        case 68: break;
        case 14: 
          { return Parser.ADD_ASSIG;
          }
        case 69: break;
        case 25: 
          { return Parser.READ;
          }
        case 70: break;
        case 18: 
          { return Parser.NOT_EQ;
          }
        case 71: break;
        case 26: 
          { return Parser.ELSE;
          }
        case 72: break;
        case 9: 
          { return Parser.POW;
          }
        case 73: break;
        case 27: 
          { return Parser.CASE;
          }
        case 74: break;
        case 29: 
          { return Parser.VOID;
          }
        case 75: break;
        case 5: 
          { 
          }
        case 76: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return 0; }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
