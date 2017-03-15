package ast.expression.unary;

import ast.expression.AbstractExp;
import ast.expression.Expression;
import semantic.Visitor;

public class UnaryMinus extends AbstractExp {

	private static final char operator = '-';
	private Expression expression;

	public UnaryMinus(int line, int column, Expression expression) {
		super(line, column);
		this.expression = expression;
	}

	public char getOperator() {
		return operator;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public String toString() {
		return "UnaryMinus [expression=" + expression + "]";
	}

}
