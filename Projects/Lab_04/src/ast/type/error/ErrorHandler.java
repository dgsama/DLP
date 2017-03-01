package ast.type.error;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {

	private static ErrorHandler instance = null;
	private List<ErrorType> errors;

	public ErrorHandler() {
		super();
		errors = new ArrayList<ErrorType>();
	}
	
	public static ErrorHandler getInstance() {
		if (instance == null) {
			instance  = new ErrorHandler();
		}
		return instance;
	}

	public boolean anyError() {
		return errors.isEmpty();
	}

	public void addError(ErrorType eT) {
		if (eT != null) {
			errors.add(eT);
		}
	}
	
	public void showErrors(PrintStream out){
		for(ErrorType et: errors){
			out.append(et.toString() + "\n");
		}
		System.out.println(out.toString());;
	}

}
