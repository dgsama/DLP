package ast.type;

import error.Err;
import error.ErrorHandler;
import visitor.Visitor;

public class ErrorType extends AbstractType {

	public String errorMessage;

	public ErrorType(int line, int column, String errorMessage) {
		super(line, column);
		this.errorMessage = errorMessage;
		ErrorHandler.getInstance().addError(new Err(this));
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}
