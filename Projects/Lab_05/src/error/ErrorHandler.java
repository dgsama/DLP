package error;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {

	private static ErrorHandler instance = null;
	private List<Err> errors;

	public ErrorHandler() {
		super();
		errors = new ArrayList<Err>();
	}

	public static ErrorHandler getInstance() {
		if (instance == null) {
			instance = new ErrorHandler();
		}
		return instance;
	}

	public boolean anyError() {
		return errors.isEmpty();
	}

	public void addError(Err eT) {
		if (eT != null) {
			errors.add(eT);
		}
	}

	public void showErrors(PrintStream err) {
		for (Err each : errors) {
			err.println(each.getErrorMessage() + ", line:" + each.getLine() + ", column: " + each.getColumn());
		}
	}

	public List<Err> getErrorsList() {
		return errors;
	}

}
