package ast.statements;

import java.util.List;

import ast.Expression;
import ast.Statement;

public class While implements Statement {

	private Expression condition;
	private List<Statement> statements;

	public While(Expression condition, List<Statement> statements) {
		super();
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
