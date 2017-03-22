package ast.expression;

import ast.AbstractASTNode;

public abstract class AbstractExp extends AbstractASTNode implements Expression {

	private boolean lValue;

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
}
