package ast.statements;

import java.util.List;

import ast.Expression;
import ast.Statement;

public class IfElse implements Statement {

	private Expression condition;
	private List<Statement> ifStatements;
	private List<Statement> elseStatements;

	public IfElse(Expression condition, List<Statement> ifStatements, List<Statement> elseStatements) {
		super();
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
