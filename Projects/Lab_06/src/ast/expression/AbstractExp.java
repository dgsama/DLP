package ast.expression;

import ast.AbstractASTNode;

public abstract class AbstractExp extends AbstractASTNode implements Expression {

	public AbstractExp(int line, int column) {
		super(line, column);
	}

}
