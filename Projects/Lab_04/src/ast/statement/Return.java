package ast.statement;

import ast.expression.Expression;

public class Return extends AbstractStatement {
	private Expression expression;

	public Return(int line, int column, Expression expression) {
		super(line, column);
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
