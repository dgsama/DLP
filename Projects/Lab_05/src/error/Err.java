package error;

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
