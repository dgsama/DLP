package ast.statements;

import ast.Expression;
import ast.Statement;

public class Return implements Statement {
	private Expression expression;

	public Return(Expression expression) {
		super();
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "Return [expression=" + expression + "]";
	}

}
