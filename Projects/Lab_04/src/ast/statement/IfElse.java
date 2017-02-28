package ast.statement;

import java.util.List;

import ast.expression.Expression;

public class IfElse extends AbstractStatement {

	private Expression condition;
	private List<Statement> ifStatements;
	private List<Statement> elseStatements;

	public IfElse(int line, int column, Expression condition, List<Statement> ifStatements,
			List<Statement> elseStatements) {
		super(line, column);
		this.condition = condition;
		this.ifStatements = ifStatements;
		this.elseStatements = elseStatements;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public List<Statement> getIfStatements() {
		return ifStatements;
	}

	public void setIfStatements(List<Statement> ifStatements) {
		this.ifStatements = ifStatements;
	}

	public List<Statement> getElseStatements() {
		return elseStatements;
	}

	public void setElseStatements(List<Statement> elseStatements) {
		this.elseStatements = elseStatements;
	}

	@Override
	public String toString() {
		return "IfElse [condition=" + condition + ", ifStatements=" + ifStatements + ", elseStatements="
				+ elseStatements + "]";
	}

}
