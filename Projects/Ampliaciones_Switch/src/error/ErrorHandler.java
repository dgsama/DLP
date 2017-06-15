package error;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that store the error list of the execution. Implements Singleton
 * pattern to have only one list.
 * 
 * @author UO237464
 *
 */
public class ErrorHandler {

	private static ErrorHandler instance = null;
	private List<Err> errors;

	public ErrorHandler() {
		super();
		errors = new ArrayList<Err>();
	}

	/**
	 * Singleton implementation. Return an instance of the class to access it
	 * attributes. If it is the first time, it initializes the object.
	 * 
	 * @return ErrorHandler object.
	 */
	public static ErrorHandler getInstance() {
		if (instance == null) {
			instance = new ErrorHandler();
		}
		return instance;
	}

	public boolean anyError() {
		return errors.isEmpty();
	}

	/**
	 * Add errors to the list. If the error is repeated, it is not added.
	 * 
	 * @param eT
	 */
	public void addError(Err eT) {
		if (eT != null) {

			for (Err each : errors) {
				if (each.getLine() == eT.getLine() && each.getColumn() == eT.getColumn()
						&& each.getErrorMessage().toLowerCase().equals(eT.getErrorMessage().toLowerCase())) {
					return;
				}
			}

			errors.add(eT);
		}

	}

	/**
	 * Show in the console the errors in the list.
	 * 
	 * @param err
	 */
	public void showErrors(PrintStream err) {
		for (Err each : errors) {
			err.println(each.getErrorMessage() + ", line:" + each.getLine() + ", column: " + each.getColumn());
		}
	}

	/**
	 * Return the list of errors.
	 * 
	 * @return
	 */
	public List<Err> getErrorsList() {
		return errors;
	}

}
