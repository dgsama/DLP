package ast.statements;

import ast.Expression;

public class UnaryMinus implements Expression {

	private static final char MINUS = '-';
	private Expression expression;

	public UnaryMinus(Expression expression) {
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
		return "UnaryMinus [expression=" + expression + "]";
	}

}
