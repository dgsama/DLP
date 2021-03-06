package ast.statement;

import java.util.List;

import ast.expression.Expression;

public class While extends AbstractStatement {

	private Expression condition;
	private List<Statement> statements;

	public While(int line, int column, Expression condition, List<Statement> statements) {
		super(line, column);
		this.condition = condition;
		this.statements = statements;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

	@Override
	public String toString() {
		return "While [condition=" + condition + ", statements=" + statements + "]";
	}

}
