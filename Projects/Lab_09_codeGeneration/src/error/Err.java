package error;

import ast.type.ErrorType;

public class Err {

	private int line;
	private int column;
	private String errorMessage;

	public Err(int line, int column, String errorMessage) {
		super();
		this.line = line;
		this.column = column;
		this.errorMessage = errorMessage;
	}

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
