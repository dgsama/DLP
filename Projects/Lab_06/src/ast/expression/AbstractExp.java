package ast.expression;

import ast.AbstractASTNode;
import ast.type.Type;

public abstract class AbstractExp extends AbstractASTNode implements Expression {

	private boolean lValue;
	private Type type;

	public AbstractExp(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean islValue() {
		return lValue;
	}

	@Override
	public void setLValue(boolean lValue) {
		this.lValue = lValue;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setType(Type type) {
		this.type = type;
	}

}
