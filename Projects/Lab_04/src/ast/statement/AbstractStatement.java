package ast.statement;

import ast.AbstractASTNode;

public class AbstractStatement extends AbstractASTNode implements Statement {

	public AbstractStatement(int line, int column) {
		super(line, column);
	}

}
