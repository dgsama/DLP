package error;

import ast.type.ErrorType;

/**
 * This class is an intermediate class to have the possibility of check if there
 * are any errors in the syntactic phase.
 * 
 * @author UO237464
 *
 */
public class Err {

	private int line;
	private int column;
	private String errorMessage;

	/**
	 * Constructor for syntactic errors.
	 * 
	 * @param line
	 * @param column
	 * @param errorMessage
	 */
	public Err(int line, int column, String errorMessage) {
		super();
		this.line = line;
		this.column = column;
		this.errorMessage = errorMessage;
	}

	/**
	 * Constructor for other errors, receives an ErrorType an them it parse the
	 * object.
	 * 
	 * @param errorType
	 */
	public Err(ErrorType errorType) {
		this.line = errorType.getLine();
		this.column = errorType.getColumn();
		this.errorMessage = errorType.getErrorMessage();
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
