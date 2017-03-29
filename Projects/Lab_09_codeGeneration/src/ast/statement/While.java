package ast.statement;

import java.util.ArrayList;
import java.util.List;

import ast.expression.Expression;
import semantic.Visitor;

public class While extends AbstractStatement {

	private Expression condition;
	private List<Statement> statements;

	public While(int line, int column, Expression condition, List<Statement> statements) {
		super(line, column);
		this.condition = condition;
		this.statements = statements;
	}

	public While(int line, int column, Expression condition, Statement statement) {
		super(line, column);
		this.condition = condition;
		this.statements = new ArrayList<>();
		statements.add(statement);
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
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public String toString() {
		return "While [condition=" + condition + ", statements=" + statements + "]";
	}

}
